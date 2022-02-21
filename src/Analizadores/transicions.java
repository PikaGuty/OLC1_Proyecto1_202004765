package Analizadores;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author gohan
 */
public class transicions {
    public static LinkedList<Estado> Transiciones = new LinkedList();
    public static LinkedList Encabezados = new LinkedList();
    
    public static void TTrans(LinkedList TSig){
        crearEstados(TSig);
        Encabezados = Encabezado(TSig);
        //System.out.println("Encabezados: "+Encabezado(TSig));
    }
    
    public static class LEstado{
        public String NEstado;
        public String Token;
        
        public LEstado(String Estado, String tok){
            NEstado = Estado;
            Token = tok;
        }
    }
    
    public static class Estado{
        public String nombreEstado;
        public LinkedList entradas;
        public LinkedList siguientes;
        //public LinkedList identificadores;
        public LinkedList<LEstado> ListaE;
        
        public Estado(String nEstado, LinkedList sig){
            nombreEstado = nEstado;
            siguientes = sig;
            entradas = new LinkedList();
            ListaE = new LinkedList();
        }

    }
    
    public static void crearEstados(LinkedList TSig){
        int contador = 1;

        for(int k = 0; k < TSig.size(); k++) {
            Metodos.TSig act = (Metodos.TSig) TSig.get(k);
            boolean ingresar = true;
            for(int i = 0; i < Transiciones.size(); i++) {
                String c1 = "",c2 = "";
                for(int j = 0; j < act.listaSig.size(); j++) {
                    c1+=act.listaSig.get(j);
                }
                for(int j = 0; j < Transiciones.get(i).siguientes.size(); j++) {
                    c2+=Transiciones.get(i).siguientes.get(j);
                }
                if(c1.equals(c2)){
                    ingresar = false;
                }
            }
            if (ingresar){
                Transiciones.add(new Estado("S"+contador,act.listaSig));
                for(int i = 0; i < Transiciones.size(); i++) {
                    if(Transiciones.get(i).nombreEstado.equals("S"+contador)){
                        Transiciones.get(i).entradas.add(act.identificadorH);
                    }
                }
                contador++;
            }else{
                for(int i = 0; i < Transiciones.size(); i++) {
                    String c1 = "",c2 = "";
                    for(int j = 0; j < act.listaSig.size(); j++) {
                        c1+=act.listaSig.get(j);
                    }
                    for(int j = 0; j < Transiciones.get(i).siguientes.size(); j++) {
                        c2+=Transiciones.get(i).siguientes.get(j);
                    }
                    if(c1.equals(c2)){
                        Transiciones.get(i).entradas.add(act.identificadorH);
                    }
                }
            }
        }
        
        LinkedList continua = new LinkedList();
        for(int j = 1; j < TSig.size(); j++){
            boolean existe = false;
            for(int i = 0; i < Transiciones.size(); i++) {
                for(int k = 0; k < Transiciones.get(i).siguientes.size(); k++){
                    String d2 = ""+Transiciones.get(i).siguientes.get(k);
                    if((j+"").equals(d2)){
                        existe=true;
                    }
                }
            }
            if(!existe){
                continua.add(j);
            }
        }
        Transiciones.addFirst(new Estado("S0",continua));
        
        
        
        
        for(int i = 0; i < Transiciones.size(); i++) {
            for(int j = 0; j < Transiciones.get(i).siguientes.size(); j++) {
                //System.out.println(Transiciones.get(i).siguientes.get(j));
            }
        }
        
        for(int i = 0; i < Transiciones.size(); i++) {
            for(int j = 0; j < Transiciones.get(i).siguientes.size(); j++){
                for(int k = 0; k < TSig.size(); k++) {
                    Metodos.TSig act = (Metodos.TSig) TSig.get(k);
                    String n1 = ""+ Transiciones.get(i).siguientes.get(j);
                    String n2 = act.identificadorH+"";
                    if(n1.equals(n2)){
                        for(int l = 0; l < Transiciones.size(); l++) {
                            for(int m = 0; m < Transiciones.get(l).entradas.size(); m++){
                                String n3 = ""+Transiciones.get(l).entradas.get(m);
                                if(n1.equals(n3)){
                                    Transiciones.get(i).ListaE.add(new LEstado(act.contenidoH+"",""+Transiciones.get(l).nombreEstado));
                                }
                            }
                        }
                    }
                }
            }
        }
        
        /*for(int i = 0; i < Transiciones.size(); i++) {
            System.out.print(Transiciones.get(i).nombreEstado);
            System.out.print(Transiciones.get(i).entradas);
            System.out.print(Transiciones.get(i).siguientes);
            //System.out.println(leer(Transiciones.get(i).ListaE));
            for(int j = 0; j < Transiciones.get(i).ListaE.size(); j++){
                System.out.println(Transiciones.get(i).ListaE.get(j).NEstado+", "+Transiciones.get(i).ListaE.get(j).Token);
            }
        }*/
    }
    
    
    public static LinkedList Encabezado(LinkedList TSig){
        LinkedList Encabezados = new LinkedList();
        for(int k = 0; k < TSig.size(); k++) {
            Metodos.TSig act = (Metodos.TSig) TSig.get(k);
            boolean ingresar=true;
            for(int i = 0; i < Encabezados.size(); i++) {
                String s1 = act.contenidoH;
                String s2 = (String) Encabezados.get(i);
                if(s1.equals(s2)){
                    ingresar=false;
                }
            }
            if(ingresar){
                Encabezados.add(act.contenidoH);
            }
        }
        return Encabezados;
    }
    
    public static String tablaEstados(LinkedList TablaS,Metodos.Nodo n, String nombre, String ER){
        Transiciones = new LinkedList();
        transicions.TTrans(TablaS);
        String tabla="digraph G { \n" +
        "label=<\n" +
        "<TABLE border=\"5\" cellspacing=\"4\" cellpadding=\"10\" style=\"rounded\" bgcolor=\"green\" gradientangle=\"315\">\n" +
        "<TR>\n" +
        "<TD border=\"3\"  colspan=\""+(Encabezados.size()+1)+"\" bgcolor=\"orange\">"+ER+"</TD>\n" +
        "</TR>\n" +
        "<TR>\n" +
        "<TD border=\"3\"  rowspan=\"2\" bgcolor=\"orange\">Estado</TD>\n" +
        "<TD border=\"3\"  colspan=\""+Encabezados.size()+"\" bgcolor=\"orange\">Terminales</TD>\n" +
        "</TR>\n";
        
        tabla+="<TR>\n";
        for(int k = 0; k < Encabezados.size(); k++) {
            tabla += "<TD border=\"3\" bgcolor=\"orange\">"+Encabezados.get(k)+"</TD>\n";
        }
        tabla+="</TR>\n";
        
        for(int i = 0; i < Transiciones.size(); i++) {
            tabla+="<TR>\n";
            if(i==Transiciones.size()-1){
                tabla+="<TD border=\"3\"  bgcolor=\"gold\">*S"+(i)+"</TD>";
            }else{
                tabla+="<TD border=\"3\"  bgcolor=\"gold\">S"+(i)+"</TD>";
            }
            
            for(int k = 0; k < Encabezados.size(); k++) {
                boolean aprobado = true;
                for(int j = 0; j < Transiciones.get(i).ListaE.size(); j++){
                    //System.out.println(Transiciones.get(i).ListaE.get(j).NEstado+", "+Transiciones.get(i).ListaE.get(j).Token);
                    String s1 = (String) Encabezados.get(k);
                    String s2 = (String) Transiciones.get(i).ListaE.get(j).NEstado;
                    if(s1.equals(s2)){
                        aprobado=false;
                        tabla += "<TD border=\"3\" bgcolor=\"gold\">"+Transiciones.get(i).ListaE.get(j).Token+"</TD>\n";
                    }
                }  
                if (aprobado){
                    tabla += "<TD border=\"3\" bgcolor=\"gold\">-</TD>\n";
                }
            }
            tabla+="</TR>\n";
        }
        
        tabla+="</TABLE>>\n" +
        "\n" +
        "\n" +
        "}";
        //System.out.println(tabla);
        try {
            String ruta = "TRANSICIONES_202004765\\TablaTrans_"+nombre+".dot";

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
            return "Ocurrió un error al generar \"TablaTrans_"+nombre+".dot\"";
        }
        
        try {
            String cmd = "bin\\dot.exe -Tpng TRANSICIONES_202004765\\TablaTrans_"+nombre+".dot -o TRANSICIONES_202004765\\TablaTrans_"+nombre+".png";
            Runtime.getRuntime().exec(cmd); 
        } catch (IOException ioe) {
            System.out.println (ioe);
            return "Ocurrió un error al generar \"TablaTrans_"+nombre+".png\"";
        }
        return "Tabla de Transiciones \"TablaSig_"+nombre+"\" generado correctamente";
    }
    
    public static String Automata(Metodos.Nodo n, String nombre, String ER){
        String tabla="digraph finite_state_machine {\n" +
"	fontname=\"Helvetica,Arial,sans-serif\"\n" +
"       label=\""+ER.replace("\"", "'")+"\""+
"	node [fontname=\"Helvetica,Arial,sans-serif\"]\n" +
"	edge [fontname=\"Helvetica,Arial,sans-serif\"]\n" +
"	rankdir=LR;\n" +
"	size=\"8,5\";\n"+
"       node [shape = doublecircle]; S"+(Transiciones.size()-1)+";\n" +
"	node [shape = circle];";
        

        for(int i = 0; i < Transiciones.size(); i++) {
            for(int j = 0; j < Transiciones.get(i).ListaE.size(); j++){
                String s2 = (String) Transiciones.get(i).ListaE.get(j).NEstado;
                tabla += "S"+i+" -> "+Transiciones.get(i).ListaE.get(j).Token+" [label = \""+s2.replace("\"", "'")+"\"];\n";
            }  
        }
        tabla+="}";
        //System.out.println(tabla);
        try {
            String ruta = "AFD_202004765\\AFD_"+nombre+".dot";

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
            return "Ocurrió un error al generar \"AFD_"+nombre+".dot\"";
        }
        
        try {
            String cmd = "bin\\dot.exe -Tpng AFD_202004765\\AFD_"+nombre+".dot -o AFD_202004765\\AFD_"+nombre+".png";
            Runtime.getRuntime().exec(cmd); 
        } catch (IOException ioe) {
            System.out.println (ioe);
            return "Ocurrió un error al generar \"AFD_"+nombre+".png\"";
        }
        return "Tabla de Transiciones \"AFD_"+nombre+"\" generado correctamente";
    }
}
