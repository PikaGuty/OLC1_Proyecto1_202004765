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
public class transiciones {
    
    public static LinkedList<Estado> Transiciones = new LinkedList();
    public static LinkedList Encabezados = new LinkedList();
    public static LinkedList lista1 = new LinkedList();
    
    public static class Estado{
        public String NEstado;
        public LinkedList Siguientes;
        public LinkedList Entradas;
        public LinkedList Terminales;
        public LinkedList<LEstado> ListaE;
        
        public Estado(String Estado, LinkedList sig){
            NEstado = Estado;
            Siguientes = sig;
            Entradas = new LinkedList();
            Terminales = new LinkedList();
            ListaE = new LinkedList();
        }
    }
    
    public static class LEstado{
        public String NEstado;
        public String Token;
        
        public LEstado(String Estado, String tok){
            NEstado = Estado;
            Token = tok;
        }
    }
    
    public static void TTrans(LinkedList TSig){
        int contador = 1;
        LinkedList entradas = new LinkedList();
        entradas.add(TSig.size());

        for(int k = 0; k < TSig.size(); k++) {
            Metodos.TSig act = (Metodos.TSig) TSig.get(k);
            boolean ingresar = true;
            for(int i = 0; i < Transiciones.size(); i++) {
                String c1 = "",c2 = "";
                for(int j = 0; j < act.listaSig.size(); j++) {
                    c1+=act.listaSig.get(j);
                }
                for(int j = 0; j < Transiciones.get(i).Siguientes.size(); j++) {
                    c2+=Transiciones.get(i).Siguientes.get(j);
                }
                if(c1.equals(c2)){
                    ingresar = false;
                }
            }
            if (ingresar){
                Transiciones.add(new Estado("S"+contador,act.listaSig));
                contador++;
            }
        }
        System.out.println("TRANSICIONES");
        for(int i = 0; i < Transiciones.size(); i++) {
            System.out.print(Transiciones.get(i).NEstado);
            System.out.println(Transiciones.get(i).Siguientes);
        }
        boolean primero=true;
        
        for(int i = 0; i < Transiciones.size(); i++) {
            if (primero){
                //Transiciones.get(i).Entradas.add(1);
                primero=false;
                //entradas.add(1);
            }else{
                for(int j = 0; j < Transiciones.get(i-1).Siguientes.size(); j++){
                    boolean existe = false;
                    for(int k = 0; k < entradas.size(); k++){
                        String d1 = ""+Transiciones.get(i-1).Siguientes.get(j);
                        String d2 = ""+entradas.get(k);
                        if(d1.equals(d2)){
                            existe=true;
                        }
                    }
                    if(!existe){
                        Transiciones.get(i).Entradas.add(Transiciones.get(i-1).Siguientes.get(j));
                        entradas.add(Transiciones.get(i-1).Siguientes.get(j));
                    }
                }
            }
        }
        
        primero=true;
        
        for(int k = 0; k < TSig.size(); k++) {
            Metodos.TSig act = (Metodos.TSig) TSig.get(k);
            if(act.identificadorH==1){
                lista1=act.listaSig;
                Transiciones.get(0).Siguientes=lista1;
            }
        }
        System.out.println("LUSTA 1 "+lista1);
        for(int i = 0; i < Transiciones.size(); i++) {
            System.out.println("ENTRADA "+Transiciones.get(i).NEstado);
            //Transiciones.get(i).Siguientes.clear();
            System.out.println("LUSTA 1 "+lista1);
            if (primero){
                primero=false;
                Transiciones.get(i).Siguientes=lista1;
            }else{
                try{
                    System.out.println("METIENDO "+Transiciones.get(i).Entradas.get(0));
                    System.out.println("LLEGOOOOOOOOOOO");
                    for(int k = 0; k < TSig.size(); k++) {
                        Metodos.TSig act = (Metodos.TSig) TSig.get(k);
                        String i1=""+act.identificadorH;
                        String i2=""+Transiciones.get(i).Entradas.get(0);
                        if(i1.equals(i2)){
                            Transiciones.get(i).Siguientes=act.listaSig;
                        }
                    }
                }catch(Exception e){
                    System.out.println("ERROR EN "+Transiciones.get(i).NEstado);
                }
            }
        }
       
        
        for(int j = 1; j < TSig.size(); j++){
            boolean existe = false;
            for(int k = 0; k < entradas.size(); k++){
                String d2 = ""+entradas.get(k);
                if((j+"").equals(d2)){
                    existe=true;
                }
            }
            if(!existe){
                Transiciones.get(0).Entradas.add(j);
                entradas.add(j);
            }
        }
        
        Transiciones.addFirst(new Estado("S0",Transiciones.get(0).Entradas));
        LinkedList li = new LinkedList();
        li.add("-");
        Transiciones.add(new Estado("S"+contador,li));
        Transiciones.get(Transiciones.size()-1).Entradas.add(TSig.size());
        
        
        for(int i = 0; i < Transiciones.size(); i++) {
            for(int j = 0; j < Transiciones.get(i).Siguientes.size(); j++){
                for(int k = 0; k < TSig.size(); k++) {
                    Metodos.TSig act = (Metodos.TSig) TSig.get(k);
                    String n1 = ""+ Transiciones.get(i).Siguientes.get(j);
                    String n2 = act.identificadorH+"";
                    if(n1.equals(n2)){
                        for(int l = 0; l < Transiciones.size(); l++) {
                            for(int m = 0; m < Transiciones.get(l).Entradas.size(); m++){
                                String n3 = ""+Transiciones.get(l).Entradas.get(m);
                                if(n1.equals(n3)){
                                    Transiciones.get(i).ListaE.add(new LEstado(act.contenidoH+"",""+Transiciones.get(l).NEstado));
                                }
                            }
                        }
                    }
                }
            }
        }
        
        for(int i = 0; i < Transiciones.size(); i++) {
            System.out.println("ESTADO "+i);
            for(int j = 0; j < Transiciones.get(i).ListaE.size(); j++){
                System.out.println(Transiciones.get(i).ListaE.get(j).NEstado+", "+Transiciones.get(i).ListaE.get(j).Token);
            }  
        }
        
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
        //System.out.println("ENCABEZADO");
        //for(int k = 0; k < Encabezados.size(); k++) {
            //System.out.println(Encabezados.get(k));
        //}
        System.out.println("+++++++++++++++++++++++++++++");

        for(int i = 0; i < Transiciones.size(); i++) {
            System.out.println("Estado "+Transiciones.get(i).NEstado+" Entradas "+Transiciones.get(i).Entradas+" Siguientes "+Transiciones.get(i).Siguientes);
        }
    }
    
    public static String tablaEstados(LinkedList TablaS,Metodos.Nodo n, String nombre, String ER){
        transiciones.TTrans(TablaS);
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
"       node [shape = doublecircle]; S0 S"+(Transiciones.size()-1)+";\n" +
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
