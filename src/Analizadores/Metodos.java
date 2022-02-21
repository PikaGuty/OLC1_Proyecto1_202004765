package Analizadores;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.StringReader;
import java.io.IOException;
import java.util.LinkedList;


/**
 *
 * @author gohan
 */
public class Metodos {
    public static LinkedList<ObjAgrupado> arbolitos = new LinkedList();
    public static LinkedList<TSig> TablaSig = new LinkedList();
    public static String dot="";
    public static int con = 0;
    public static int noNodo = 0;
    public static String obtener(String texto) throws IOException{
        String sec="";
        try{
            String expr = (String) texto;
            Lex lexer = new Lex(new StringReader(expr));
            String resultado="";
            while (true) {
                Tokens token = lexer.yylex();
                if (token == null) {
                    return resultado;
                }
                switch (token) {
                    case Identificador:
                        
                        resultado += "  <Identificador>\t\t" + lexer.lexeme + "\n";
                        break;
                    case Reservadas:
                        //System.out.println(lexer.lexeme);
                        token = lexer.yylex();
                        //System.out.println(lexer.lexeme);
                        break;
                    case ERROR:
                        resultado += "  <Simbolo no definido>\n";
                        break;
                    default:
                        break;
                }
            }
        }catch(Exception e){
            System.out.println(e.getCause());
            return "";
        }
    }     

    public static LinkedList ordenarExpresionRegular(LinkedList ER){
        for (int i = 0; i < ER.size(); i++) {
            arbolitos = new LinkedList();
            String Expresion="";
            LinkedList Actual = (LinkedList) ER.get(i);
            //System.out.println("ACTUAL "+Actual);
            LinkedList puntero = (LinkedList) Actual.getLast();
            boolean primero=true;
            while(true){
                try{
                    if (primero){
                        Expresion += puntero.getFirst();
                        primero = false;
                    }else{
                        Expresion += ","+puntero.getFirst();
                    }
                    puntero.removeFirst();
                }catch(Exception e){
                    break;
                }
            }
            
            String cadena=Expresion;
            String[] Exp;
            boolean Final = true;
            while(true){
                Exp=cadena.split(",");
                //System.out.println(cadena);
                if (Exp.length>1){
                    cadena=analizar(Exp);
                }else if(Exp.length==1){
                    cadena=".,"+cadena+",{#FIN}";
                    Exp=cadena.split(",");
                    
                    cadena=analizar(Exp);
                    //System.out.println("CADENITA"+cadena);
                    break;
                    /*if (Final){
                        
                        Final=false;
                    }else{
                        break;
                    } */ 
                }else{
                    break;
                }
            }
            //System.out.println(cadena);
            recorrerArbolito(arbolitos.getFirst().getArbol());
            //graficarArbolito(arbolitos.getFirst().getArbol());
            //System.out.println("DOT");
            //System.out.println(dot);
            //System.out.println("******************************************");
            /*System.out.println("CADENA");
            System.out.println(arbolitos.getFirst().getCadena());
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println(arbolitos.getFirst().getArbol().contenido);
            System.out.println(arbolitos.getFirst().getArbol().izquierda.contenido);
            System.out.println(arbolitos.getFirst().getArbol().izquierda.izquierda.contenido);
            System.out.println(arbolitos.getFirst().getArbol().izquierda.izquierda.izquierda.contenido);
            System.out.println(arbolitos.getFirst().getArbol().izquierda.derecha.contenido);
            System.out.println(arbolitos.getFirst().getArbol().derecha.contenido);
            System.out.println(arbolitos.getFirst().getArbol().derecha.izquierda.contenido);*/
            Actual.removeLast();
            Actual.add(cadena);
            Actual.add(arbolitos.getFirst().getArbol());
        }
        return ER;
    }
    
    public static String generarImagen(String nombre){
        try {
            String ruta = "ARBOLES_202004765\\Arbol_"+nombre+".dot";

            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(dot);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
            return "Ocurrió un error al generar \"Arbol_"+nombre+".dot\"";
        }
        
        try {
            String cmd = "bin\\dot.exe -Tpng ARBOLES_202004765\\Arbol_"+nombre+".dot -o ARBOLES_202004765\\Arbol_"+nombre+".png";
            Runtime.getRuntime().exec(cmd); 
        } catch (IOException ioe) {
            System.out.println (ioe);
            return "Ocurrió un error al generar \"Arbol_"+nombre+".png\"";
        }
        return "Arbol \"Arbol_"+nombre+"\" generado correctamente";
    }
    
    public static void graficarArbolito(Nodo n){
        if (n!=null){
            //System.out.println(n.contenido);
            try{
                dot+= n.contenido +"->"+n.izquierda.contenido+";";
            }catch(Exception e){}
            
            try{
                dot+= n.contenido +"->"+n.derecha.contenido+";";
            }catch(Exception e){}
            
            graficarArbolito(n.izquierda);
            graficarArbolito(n.derecha);
        }
    }
    
    public static void grafica(Nodo n, String ER){
        dot="digraph structs {\n" +
        "    node [shape=record];\n"+
        "    label = \""+ER.replace("\"", "'")+"\";\n";
        crearNodos(n);
        unirNodos(n);
        dot+="}";
        System.out.println(dot);//.replace('l', 'p')
    }
    
    public static void llenarSig(Nodo n){
        if (n!=null){
            llenarSig(n.izquierda);
            llenarSig(n.derecha);
            if(n.izquierda!=null||n.derecha!=null){
                if("+".equals(n.contenido)||"*".equals(n.contenido)){
                    for (int i = 0; i < n.izquierda.Ultimos.size(); i++) {
                        for (int j = 0; j < n.izquierda.Primeros.size(); j++) {
                            for (int k = 0; k < TablaSig.size(); k++) {
                                int c1 = TablaSig.get(k).identificadorH;
                                int c2 = (int) n.izquierda.Ultimos.get(i);
                                if(c1==c2){
                                    
                                    boolean ingresar = true;
                                    for (int m = 0; m < TablaSig.get(k).listaSig.size(); m++) {
                                        int d1 = (int) n.izquierda.Primeros.get(j);
                                        int d2 = (int) TablaSig.get(k).listaSig.get(m);
                                        if(d1==d2){
                                            System.out.println("NO DEJO");
                                            ingresar=false;
                                        }
                                    }
                                    if(ingresar){
                                        TablaSig.get(k).listaSig.add(n.izquierda.Primeros.get(j));
                                    }
                                    
                                }                   
                            }
                        }  
                    }
                }
                if(".".equals(n.contenido)){
                    for (int i = 0; i < n.izquierda.Ultimos.size(); i++) {
                        for (int j = 0; j < n.derecha.Primeros.size(); j++) {
                            for (int k = 0; k < TablaSig.size(); k++) {
                                int c1 = TablaSig.get(k).identificadorH;
                                int c2 = (int) n.izquierda.Ultimos.get(i);
                                if(c1==c2){
                                    
                                    boolean ingresar = true;
                                    for (int m = 0; m < TablaSig.get(k).listaSig.size(); m++) {
                                        int d1 = (int) n.derecha.Primeros.get(j);
                                        int d2 = (int) TablaSig.get(k).listaSig.get(m);
                                        if(d1==d2){
                                            System.out.println("NO DEJO");
                                            ingresar=false;
                                        }
                                    }
                                    if(ingresar){
                                        TablaSig.get(k).listaSig.add(n.derecha.Primeros.get(j));
                                    }
                                    
                                }                   
                            }
                        }  
                    }
                }
            }
            if("{#FIN}".equals(n.contenido)){
                for (int i = 0; i < TablaSig.size(); i++) {
                    if("{#FIN}".equals(TablaSig.get(i).contenidoH)){
                        TablaSig.get(i).listaSig.add("-");
                    }                   
                }
            }
        }
        
    }
    
    public static String tablaSiguientes(Nodo n, String nombre, String ER){
        llenarSig(n);
        
        String tabla="digraph G { \n" +
        "  label=<\n" +
        " <TABLE border=\"5\" cellspacing=\"4\" cellpadding=\"10\" style=\"rounded\" bgcolor=\"green\" gradientangle=\"315\">\n" +
        "<TR>\n" +
        "<TD border=\"3\"  colspan=\"4\" bgcolor=\"orange\">"+ER+"</TD>\n" +
        "</TR>"+
        "<TR>\n" +
        "<TD border=\"3\"  colspan=\"2\" bgcolor=\"orange\">Hoja</TD>\n" +
        "<TD border=\"3\"  colspan=\"2\" bgcolor=\"orange\">Siguientes</TD>\n" +
        "</TR>";
        
        for (int i = 0; i < TablaSig.size(); i++) {
            boolean primero = true;
            tabla+="<TR>\n" +
                    "<TD border=\"3\"  bgcolor=\"gold\">"+TablaSig.get(i).contenidoH+"</TD>\n" +
                    "<TD border=\"3\"  bgcolor=\"gold\">"+TablaSig.get(i).identificadorH+"</TD>\n" +
                    "<TD border=\"3\"  colspan=\"2\" bgcolor=\"gold\">";
            for (int j = 0; j < TablaSig.get(i).listaSig.size(); j++) {
                //System.out.println("TIENE ESTO EL "+TablaSig.get(i).identificadorH+"  "+TablaSig.get(i).listaSig);
                if(primero){
                    tabla+=TablaSig.get(i).listaSig.get(j);
                    primero=false;
                }else{
                    tabla+=","+TablaSig.get(i).listaSig.get(j);
                }
            }
            tabla+="</TD>\n" +
                    "</TR>";
            
        }
        tabla+="</TABLE>>\n" +
        "\n" +
        "\n" +
        "}";
        System.out.println(tabla);
        try {
            String ruta = "SIGUIENTES_202004765\\TablaSig_"+nombre+".dot";

            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(tabla);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
            return "Ocurrió un error al generar \"TablaSig_"+nombre+".dot\"";
        }
        
        try {
            String cmd = "bin\\dot.exe -Tpng SIGUIENTES_202004765\\TablaSig_"+nombre+".dot -o SIGUIENTES_202004765\\TablaSig_"+nombre+".png";
            Runtime.getRuntime().exec(cmd); 
        } catch (IOException ioe) {
            System.out.println (ioe);
            return "Ocurrió un error al generar \"TablaSig_"+nombre+".png\"";
        }
        return "Tabla de Siguientes \"TablaSig_"+nombre+"\" generado correctamente";
    }
    
    public static void crearNodos(Nodo n){
        if (n!=null){
            if(!n.contenido.contains("\"")){
                
            }
            if(n.identificador!=0){
                dot+="    Nodo"+n.nNodo+" [label=\"{ "+n.anulable+" |{"+n.Primeros+"|<here> "+n.contenido.replace("\"", "'").replace("{", "").replace("}", "").replace("|","\\|")+" |"+n.Ultimos+"}| "+n.identificador+"}\" color=\"white\"];\n";
            }else{
                dot+="    Nodo"+n.nNodo+" [label=\"{ "+n.anulable+" |{"+n.Primeros+"|<here> "+n.contenido.replace("\"", "'").replace("{", "").replace("}", "").replace("|","\\|")+" |"+n.Ultimos+"}| }\" color=\"white\"];\n";
            }
            crearNodos(n.izquierda);
            crearNodos(n.derecha);
        }
    }
    
    public static void unirNodos(Nodo n){
        if (n!=null){
            if(n.izquierda!=null){
                dot+="    Nodo"+n.nNodo+":here -> Nodo"+n.izquierda.nNodo+":here [color=\"orange\"];\n";
            }
            if(n.derecha!=null){
                dot+="    Nodo"+n.nNodo+":here -> Nodo"+n.derecha.nNodo+":here [color=\"orange\"];\n";
            }
            unirNodos(n.izquierda);
            unirNodos(n.derecha);
        }
    }
    
    public static void parametrosArbolito(Nodo n){
        if (n!=null){
            parametrosArbolito(n.izquierda);
            parametrosArbolito(n.derecha);
            n.nNodo=noNodo;
            noNodo++;
            if("*".equals(n.contenido)||"?".equals(n.contenido)){
                n.anulable="A";
            }
            if(n.izquierda==null&&n.derecha==null){
                n.identificador=con;
                con++;
                TablaSig.add(new TSig(n.contenido,n.identificador));
            }
        }
    }
    
    public static void parametros2Arbolito(Nodo n){
        if (n!=null){
            parametros2Arbolito(n.izquierda);
            parametros2Arbolito(n.derecha);
            //System.out.println(n.contenido);
            if("|".equals(n.contenido)){
                if("A".equals(n.izquierda.anulable)||"A".equals(n.derecha.anulable)){
                    n.anulable="A";
                }
            }
            if(".".equals(n.contenido)){
                if("A".equals(n.izquierda.anulable)&&"A".equals(n.derecha.anulable)){
                    n.anulable="A";
                }
            }
            if("+".equals(n.contenido)){
                if("A".equals(n.izquierda.anulable)){
                    n.anulable="A";
                }
            }

            
        }
    }

    public static void listaPrimeros(Nodo n){
        if (n!=null){
            listaPrimeros(n.izquierda);
            listaPrimeros(n.derecha);
            
            if(n.izquierda==null&&n.derecha==null){
                n.Primeros.add(n.identificador);
                n.Ultimos.add(n.identificador);
            }
            if("|".equals(n.contenido)){
                for (int i = 0; i < n.izquierda.Primeros.size(); i++) {
                    n.Primeros.add(n.izquierda.Primeros.get(i)); 
                }
                for (int i = 0; i < n.derecha.Primeros.size(); i++) {
                    n.Primeros.add(n.derecha.Primeros.get(i));
                }
            }
            if(".".equals(n.contenido)){
                if ("A".equals(n.izquierda.anulable)){
                    for (int i = 0; i < n.izquierda.Primeros.size(); i++) {
                        n.Primeros.add(n.izquierda.Primeros.get(i)); 
                    }
                    for (int i = 0; i < n.derecha.Primeros.size(); i++) {
                        n.Primeros.add(n.derecha.Primeros.get(i));
                    }
                }else{
                    for (int i = 0; i < n.izquierda.Primeros.size(); i++) {
                        n.Primeros.add(n.izquierda.Primeros.get(i)); 
                    }
                }
            }
            if("*".equals(n.contenido)||"?".equals(n.contenido)||"+".equals(n.contenido)){
                for (int i = 0; i < n.izquierda.Primeros.size(); i++) {
                    n.Primeros.add(n.izquierda.Primeros.get(i)); 
                }
            }
            
        }
    }
    
    public static void listaUltimos(Nodo n){
        if (n!=null){
            listaUltimos(n.izquierda);
            listaUltimos(n.derecha);
            
            
            if("|".equals(n.contenido)){
                for (int i = 0; i < n.izquierda.Ultimos.size(); i++) {
                    n.Ultimos.add(n.izquierda.Ultimos.get(i)); 
                }
                for (int i = 0; i < n.derecha.Ultimos.size(); i++) {
                    n.Ultimos.add(n.derecha.Ultimos.get(i));
                }
            }
            if(".".equals(n.contenido)){
                if ("A".equals(n.derecha.anulable)){
                    for (int i = 0; i < n.izquierda.Ultimos.size(); i++) {
                        n.Ultimos.add(n.izquierda.Ultimos.get(i)); 
                    }
                    for (int i = 0; i < n.derecha.Ultimos.size(); i++) {
                        n.Ultimos.add(n.derecha.Ultimos.get(i));
                    }
                }else{
                    for (int i = 0; i < n.derecha.Ultimos.size(); i++) {
                        n.Ultimos.add(n.derecha.Ultimos.get(i)); 
                    }
                }
            }
            if("*".equals(n.contenido)||"?".equals(n.contenido)||"+".equals(n.contenido)){
                for (int i = 0; i < n.izquierda.Ultimos.size(); i++) {
                    n.Ultimos.add(n.izquierda.Ultimos.get(i)); 
                }
            }
            
        }
    }
    
    public static void recorrerArbolito(Nodo n){
        if (n!=null){
            //System.out.println("Contenido: "+n.contenido+" Anulable? "+n.anulable+" Identificador "+n.identificador+" Primeros: "+n.Primeros+" Ultimos: "+n.Ultimos+"\n");

            recorrerArbolito(n.izquierda);
            recorrerArbolito(n.derecha);
        }
    }
    
    public static class TSig{
        public String contenidoH;
        public int identificadorH;
        public LinkedList listaSig;
        
        public TSig(String contenido, int identificador){
            contenidoH = contenido;
            identificadorH = identificador;
            listaSig = new LinkedList();
        }
    }
    
    public static class Nodo{
    
        public Nodo derecha;
        public Nodo izquierda;
        public int llave;
        public int identificador;
        public int nNodo;
        public String anulable;
        public LinkedList Primeros;
        public LinkedList Ultimos;
        public String contenido;
        
        public Nodo(String con){

            derecha=null;
            izquierda=null;
            contenido=con;
            nNodo=0;
            Primeros=new LinkedList();
            Ultimos=new LinkedList();
            anulable = "N";
            identificador = 0;
        }

    }
    
    private static class ObjAgrupado{
        private Nodo arbol;
        private String cadena;

        public ObjAgrupado(Nodo arbol, String cadena) {
            this.arbol = arbol;
            this.cadena = cadena;
        }

        public Nodo getArbol() {
            return arbol;
        }

        public String getCadena() {
            return cadena;
        }

        public void setArbol(Nodo arbol) {
            this.arbol = arbol;
        }

        public void setCadena(String cadena) {
            this.cadena = cadena;
        }
    }
    
        
    public static String analizar(String[] Exp){
        boolean primero=true;
        String cadena = "";
        for(int i = 0; i < Exp.length; i++) {
            //System.out.println(Exp[i]);
            if ("|".equals(Exp[i])||".".equals(Exp[i])){
                //System.out.println("OBTUVE: "+Exp[i]+" A la derecha "+Exp[i+2]+" A la izquierda "+Exp[i+1]);
                i++;
                if(!"|".equals(Exp[i])&&!".".equals(Exp[i])&&!"*".equals(Exp[i])&&!"?".equals(Exp[i])&&!"+".equals(Exp[i])){
                    i++;
                    if(!"|".equals(Exp[i])&&!".".equals(Exp[i])&&!"*".equals(Exp[i])&&!"?".equals(Exp[i])&&!"+".equals(Exp[i])){
                        //System.out.println("("+Exp[i-1]+Exp[i-2]+Exp[i]+")");
                        if (primero){
                            cadena += "("+Exp[i-1]+Exp[i-2]+Exp[i]+")";
                            Nodo n = new Nodo(""+Exp[i-2]);
                            Nodo ni = new Nodo(""+Exp[i-1]);
                            Nodo nd = new Nodo(""+Exp[i]);
                            
                            if((""+Exp[i-1]).contains("(")||(""+Exp[i-1]).contains("+")||(""+Exp[i-1]).contains("*")){
                                for (int j = 0; j < arbolitos.size(); j++) {
                                    if (Exp[i-1].equals(arbolitos.get(j).cadena)){
                                        n.izquierda=arbolitos.get(j).arbol;
                                        arbolitos.remove(j);
                                    }
                                }
                            }else{
                                n.izquierda=ni;
                            }

                            if((""+Exp[i]).contains("(")||(""+Exp[i]).contains("+")||(""+Exp[i]).contains("*")){
                                for (int j = 0; j < arbolitos.size(); j++) {
                                    if (Exp[i].equals(arbolitos.get(j).cadena)){
                                        n.derecha=arbolitos.get(j).arbol;
                                        arbolitos.remove(j);
                                    }
                                }
                            }else{
                                n.derecha=nd;
                            }
                            
                            arbolitos.add(new ObjAgrupado(n,"("+Exp[i-1]+Exp[i-2]+Exp[i]+")"));
                            primero = false;

                        }else{
                            cadena += ","+"("+Exp[i-1]+Exp[i-2]+Exp[i]+")";
                            Nodo n = new Nodo(""+Exp[i-2]);
                            Nodo ni = new Nodo(""+Exp[i-1]);
                            Nodo nd = new Nodo(""+Exp[i]);
                            
                            if((""+Exp[i-1]).contains("(")||(""+Exp[i-1]).contains("+")||(""+Exp[i-1]).contains("*")){
                                for (int j = 0; j < arbolitos.size(); j++) {
                                    if (Exp[i-1].equals(arbolitos.get(j).cadena)){
                                        n.izquierda=arbolitos.get(j).arbol;
                                        arbolitos.remove(j);
                                    }
                                }
                            }else{
                                n.izquierda=ni;
                            }

                            if((""+Exp[i]).contains("(")||(""+Exp[i]).contains("+")||(""+Exp[i]).contains("*")){
                                for (int j = 0; j < arbolitos.size(); j++) {
                                    if (Exp[i].equals(arbolitos.get(j).cadena)){
                                        n.derecha=arbolitos.get(j).arbol;
                                        arbolitos.remove(j);
                                    }
                                }
                            }else{
                                n.derecha=nd;
                            }
                            
                            arbolitos.add(new ObjAgrupado(n,"("+Exp[i-1]+Exp[i-2]+Exp[i]+")"));
                        }
                    }else{
                        i--;
                        i--;
                        if (primero){
                            cadena += Exp[i];
                            primero = false;
                        }else{
                            cadena += ","+Exp[i];
                        }
                    }
                }else{
                    i--;
                    if (primero){
                        cadena += Exp[i];
                        primero = false;
                    }else{
                        cadena += ","+Exp[i];
                    }
                }
            }else if("*".equals(Exp[i])||"?".equals(Exp[i])||"+".equals(Exp[i])){
                i++;
                if(!"|".equals(Exp[i])&&!".".equals(Exp[i])&&!"*".equals(Exp[i])&&!"?".equals(Exp[i])&&!"+".equals(Exp[i])){
                    if (primero){
                        cadena += Exp[i]+Exp[i-1];
                        Nodo n = new Nodo(""+Exp[i-1]);
                        Nodo ni = new Nodo(""+Exp[i]);

                        if((""+Exp[i]).contains("(")||(""+Exp[i]).contains("+")||(""+Exp[i]).contains("*")){
                            for (int j = 0; j < arbolitos.size(); j++) {
                                if (Exp[i].equals(arbolitos.get(j).cadena)){
                                    n.izquierda=arbolitos.get(j).arbol;
                                    arbolitos.remove(j);
                                }
                            }
                        }else{
                            n.izquierda=ni;
                        }
                        arbolitos.add(new ObjAgrupado(n,Exp[i]+Exp[i-1]+""));
                            
                        primero = false;
                    }else{
                        cadena += ","+Exp[i]+Exp[i-1];
                        Nodo n = new Nodo(""+Exp[i-1]);
                        Nodo ni = new Nodo(""+Exp[i]);

                        if((""+Exp[i]).contains("(")||(""+Exp[i]).contains("+")||(""+Exp[i]).contains("*")){
                            for (int j = 0; j < arbolitos.size(); j++) {
                                if (Exp[i].equals(arbolitos.get(j).cadena)){
                                    n.izquierda=arbolitos.get(j).arbol;
                                    arbolitos.remove(j);
                                }
                            }
                        }else{
                            n.izquierda=ni;
                        }
                        arbolitos.add(new ObjAgrupado(n,Exp[i]+Exp[i-1]+""));
                    }
                    
                }else{
                    i--;
                    if (primero){
                        cadena += Exp[i];
                        primero = false;
                    }else{
                        cadena += ","+Exp[i];
                    }
                }
            }else{
               if (primero){
                        cadena += Exp[i];
                        primero = false;
                    }else{
                        cadena += ","+Exp[i];
                    } 
            }
        }
        return cadena;
    }
}
