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
public class Thompson {
    public static LinkedList<grafo> grafos = new LinkedList();
    public static int contador=0;
    public static Nodo anterior1=null,anterior2=null;
    public static LinkedList<Nodon> listaGrafo = new LinkedList();
    public static LinkedList listaNodos = new LinkedList();
    public static String dot = "";
    
    public static class grafo{
        public String cadena;
        public Nodo primero;
        public Nodo ultimo;
        public grafo(String cnodo, Nodo prim, Nodo ulti){
            cadena=cnodo;
            primero = prim;
            ultimo = ulti;
        }
    }
    
    public static class Nodo{
        public LinkedList<sig> Siguientes;
        public String nNodo;
        
        public Nodo(String nodo){
            nNodo=nodo;
            Siguientes = new LinkedList();
        }
    }
    
    public static class Nodon{
        public String nNodo;
        public String sig;
        public String simbolo;
        
        public Nodon(String nodo, String trans ,String si){
            nNodo=nodo;
            sig=si;
            simbolo = trans;
        }
    }
    
    public static class sig{
        public Nodo nodoS;
        public String simbolo;
        
        public sig(String sim, Nodo nodo){
            nodoS=nodo;
            simbolo = sim;
        }
    }
    
    public static void grafica1(String A1, String Simbolo, String A2){
        //System.out.println("DERECHA "+A1);
        //System.out.println("SIMBOLO "+Simbolo);
        //System.out.println("IZQUIERDA "+A2);
        if("|".equals(Simbolo)){
            Nodo ni = new Nodo("S"+contador);
            contador++;
            Nodo nf = new Nodo("S"+contador);
            contador++;
            listaNodos.add(ni.nNodo);
            listaNodos.add(nf.nNodo);
            boolean existe1 = false;
            boolean asig = false;
            //System.out.println("Buscando: "+A1);
            for (int j = 0; j < grafos.size(); j++) {
                if (A1.equals(grafos.get(j).cadena)){
                    ni.Siguientes.add(new sig("Epsilon",grafos.get(j).primero));
                    listaGrafo.add(new Nodon(ni.nNodo,"Epsilon",grafos.get(j).primero.nNodo));
                    if (!asig){
                        grafos.get(j).ultimo.Siguientes.add(new sig("Epsilon",nf));
                        listaGrafo.add(new Nodon(grafos.get(j).ultimo.nNodo,"Epsilon",nf.nNodo));
                        asig=true;
                    }
                    grafos.remove(j);
                    existe1=true;
                }
            }
            if(!existe1){
                Nodo ns1 = new Nodo("S"+contador);
                contador++;
                listaNodos.add(ns1.nNodo);
                
                ni.Siguientes.add(new sig("Epsilon",ns1));
                listaGrafo.add(new Nodon(ni.nNodo,"Epsilon",ns1.nNodo));
                
                Nodo nss1 = new Nodo("S"+contador);
                contador++;
                listaNodos.add(nss1.nNodo);
                
                ns1.Siguientes.add(new sig(A1,nss1));
                listaGrafo.add(new Nodon(ns1.nNodo,A1,nss1.nNodo));
                
                nss1.Siguientes.add(new sig("Epsilon",nf));
                listaGrafo.add(new Nodon(nss1.nNodo,"Epsilon",nf.nNodo));
            }
            boolean existe2 = false;
            asig = false;
            //System.out.println("Buscando: "+A2);
            for (int j = 0; j < grafos.size(); j++) {
                if (A2.equals(grafos.get(j).cadena)){
                    ni.Siguientes.add(new sig("Epsilon",grafos.get(j).primero));
                    listaGrafo.add(new Nodon(ni.nNodo,"Epsilon",grafos.get(j).primero.nNodo));
                    
                    if (!asig){
                        grafos.get(j).ultimo.Siguientes.add(new sig("Epsilon",nf));
                        listaGrafo.add(new Nodon(grafos.get(j).ultimo.nNodo,"Epsilon",nf.nNodo));
                        asig=true;
                    }
                    
                    grafos.remove(j);
                    existe2=true;
                }
            }
            if(!existe2){
                Nodo ns2 = new Nodo("S"+contador);
                contador++;
                listaNodos.add(ns2.nNodo);
                
                ni.Siguientes.add(new sig("Epsilon",ns2));
                listaGrafo.add(new Nodon(ni.nNodo,"Epsilon",ns2.nNodo));
                
                Nodo nss2 = new Nodo("S"+contador);
                contador++;
                listaNodos.add(nss2.nNodo);
                
                ns2.Siguientes.add(new sig(A2,nss2));
                listaGrafo.add(new Nodon(ns2.nNodo,A2,nss2.nNodo));
                
                nss2.Siguientes.add(new sig("Epsilon",nf));
                listaGrafo.add(new Nodon(nss2.nNodo,"Epsilon",nf.nNodo));
            }
            //System.out.println("Guardando: "+"("+A1+Simbolo+A2+")");
            grafos.add(new grafo("("+A1+Simbolo+A2+")",ni,nf));
            
        }
        if(".".equals(Simbolo)){
            //System.out.println("ENTRE A .");
            Nodo ni = new Nodo("S"+contador);
            contador++;
            
            Nodo nf = new Nodo("S"+contador);
            contador++;
            
            Nodo ns1 = new Nodo("S"+contador);
            contador++;
            
            Nodo ns2 = new Nodo("S"+contador);
            contador++;
            
            boolean existe1 = false;
            boolean asig = false;
            //System.out.println("Buscando: "+A1);
            for (int j = 0; j < grafos.size(); j++) {
                if (A1.equals(grafos.get(j).cadena)){
                    ni=grafos.get(j).primero;
                    
                    ns1=grafos.get(j).ultimo;
                    asig=true;
                    
                    grafos.remove(j);
                    existe1=true;
                }
            }
            if(!existe1){
                listaNodos.add(ni.nNodo);
                listaNodos.add(ns1.nNodo);
                
                ni.Siguientes.add(new sig(A1,ns1));
                listaGrafo.add(new Nodon(ni.nNodo,A1,ns1.nNodo));
            }
            
            boolean existe2 = false;
            asig = false;
            //System.out.println("Buscando: "+A2);
            for (int j = 0; j < grafos.size(); j++) {
                if (A2.equals(grafos.get(j).cadena)){
                    ns2=grafos.get(j).primero;
                    listaGrafo.add(new Nodon(ns1.nNodo,"Epsilon",ns2.nNodo));
                    nf=grafos.get(j).ultimo;
                    asig=true;
                    
                    grafos.remove(j);
                    existe2=true;
                }
            }
            if(!existe2){
                listaNodos.add(ns2.nNodo);
                listaNodos.add(nf.nNodo);
                ns1.Siguientes.add(new sig("Epsilon",ns2));
                ns2.Siguientes.add(new sig(A2,nf));
                listaGrafo.add(new Nodon(ns1.nNodo,"Epsilon",ns2.nNodo));
                listaGrafo.add(new Nodon(ns2.nNodo,A2,nf.nNodo));
            }
            //System.out.println("Guardando: "+"("+A1+Simbolo+A2+")");
            grafos.add(new grafo("("+A1+Simbolo+A2+")",ni,nf));
            //System.out.println("HOLA "+ni.nNodo+" "+ns1.nNodo);
            //System.out.println("Siguientes");
            //for (int j = 0; j < listaGrafo.size(); j++) {
            //    System.out.println(listaGrafo.get(j).nNodo+"--"+listaGrafo.get(j).simbolo+"->"+listaGrafo.get(j).sig);
            //}
            
        }
        
    }
    
    public static void grafica2(String A1, String Simbolo){
        //System.out.println("DERECHA "+A1);
        //System.out.println("SIMBOLO "+Simbolo);

        if("*".equals(Simbolo)){
            Nodo ni = new Nodo("S"+contador);
            contador++;
            listaNodos.add(ni.nNodo);
            
            Nodo nf = new Nodo("S"+contador);
            contador++;
            listaNodos.add(nf.nNodo);
            
            boolean existe1 = false;
            boolean asig = false;
            //System.out.println("Buscando: "+A1);
            for (int j = 0; j < grafos.size(); j++) {
                if (A1.equals(grafos.get(j).cadena)){
                    ni.Siguientes.add(new sig("Epsilon",grafos.get(j).primero));
                    listaGrafo.add(new Nodon(ni.nNodo,"Epsilon",grafos.get(j).primero.nNodo));
                    
                    if (!asig){
                        grafos.get(j).ultimo.Siguientes.add(new sig("Epsilon",nf));
                        listaGrafo.add(new Nodon(grafos.get(j).ultimo.nNodo,"Epsilon",nf.nNodo));
                        asig=true;
                    }
                    
                    ni.Siguientes.add(new sig("Epsilon",nf));
                    listaGrafo.add(new Nodon(ni.nNodo,"Epsilon",nf.nNodo));
                    
                    grafos.get(j).ultimo.Siguientes.add(new sig("Epsilon",grafos.get(j).primero));
                    listaGrafo.add(new Nodon(grafos.get(j).ultimo.nNodo,"Epsilon",grafos.get(j).primero.nNodo));
                    
                    grafos.remove(j);
                    existe1=true;
                }
            }
            if(!existe1){
                Nodo ns1 = new Nodo("S"+contador);
                contador++;
                listaNodos.add(ns1.nNodo);
                
                ni.Siguientes.add(new sig("Epsilon",ns1));
                listaGrafo.add(new Nodon(ni.nNodo,"Epsilon",ns1.nNodo));
                
                Nodo nss1 = new Nodo("S"+contador);
                contador++;
                listaNodos.add(nss1.nNodo);
                
                ns1.Siguientes.add(new sig(A1,nss1));
                listaGrafo.add(new Nodon(ns1.nNodo,A1,nss1.nNodo));
                
                nss1.Siguientes.add(new sig("Epsilon",nf));
                listaGrafo.add(new Nodon(nss1.nNodo,"Epsilon",nf.nNodo));
                
                ni.Siguientes.add(new sig("Epsilon",nf));
                listaGrafo.add(new Nodon(ni.nNodo,"Epsilon",nf.nNodo));
                
                nss1.Siguientes.add(new sig("Epsilon",ns1));
                listaGrafo.add(new Nodon(nss1.nNodo,"Epsilon",ns1.nNodo));
            }
            //System.out.println("Guardando: "+"("+A1+Simbolo+")");
            grafos.add(new grafo("("+A1+Simbolo+")",ni,nf));
            
        }
        if("+".equals(Simbolo)){
            Nodo ni = new Nodo("S"+contador);
            contador++;
            listaNodos.add(ni.nNodo);
            
            Nodo nf = new Nodo("S"+contador);
            contador++;
            listaNodos.add(nf.nNodo);
            
            boolean existe1 = false;
            boolean asig = false;
            //System.out.println("Buscando: "+A1);
            for (int j = 0; j < grafos.size(); j++) {
                if (A1.equals(grafos.get(j).cadena)){
                    ni.Siguientes.add(new sig("Epsilon",grafos.get(j).primero));
                    listaGrafo.add(new Nodon(ni.nNodo,"Epsilon",grafos.get(j).primero.nNodo));
                    
                    if (!asig){
                        grafos.get(j).ultimo.Siguientes.add(new sig("Epsilon",nf));
                        listaGrafo.add(new Nodon(grafos.get(j).ultimo.nNodo,"Epsilon",nf.nNodo));
                        asig=true;
                    }
                    
                    //ni.Siguientes.add(new sig("Epsilon",nf));
                    //listaGrafo.add(new Nodon(ni.nNodo,"Epsilon",nf.nNodo));
                    
                    grafos.get(j).ultimo.Siguientes.add(new sig("Epsilon",grafos.get(j).primero));
                    listaGrafo.add(new Nodon(grafos.get(j).ultimo.nNodo,"Epsilon",grafos.get(j).primero.nNodo));
                    
                    grafos.remove(j);
                    existe1=true;
                }
            }
            if(!existe1){
                Nodo ns1 = new Nodo("S"+contador);
                contador++;
                listaNodos.add(ns1.nNodo);
                
                ni.Siguientes.add(new sig("Epsilon",ns1));
                listaGrafo.add(new Nodon(ni.nNodo,"Epsilon",ns1.nNodo));
                
                Nodo nss1 = new Nodo("S"+contador);
                contador++;
                listaNodos.add(nss1.nNodo);
                
                ns1.Siguientes.add(new sig(A1,nss1));
                listaGrafo.add(new Nodon(ns1.nNodo,A1,nss1.nNodo));
                
                nss1.Siguientes.add(new sig("Epsilon",nf));
                listaGrafo.add(new Nodon(nss1.nNodo,"Epsilon",nf.nNodo));
                
                //ni.Siguientes.add(new sig("Epsilon",nf));
                //listaGrafo.add(new Nodon(ni.nNodo,"Epsilon",nf.nNodo));
                
                nss1.Siguientes.add(new sig("Epsilon",ns1));
                listaGrafo.add(new Nodon(nss1.nNodo,"Epsilon",ns1.nNodo));
            }
            //System.out.println("Guardando: "+"("+A1+Simbolo+")");
            grafos.add(new grafo("("+A1+Simbolo+")",ni,nf));
            
        }
        if("?".equals(Simbolo)){
            Nodo ni = new Nodo("S"+contador);
            contador++;
            listaNodos.add(ni.nNodo);
            
            Nodo nf = new Nodo("S"+contador);
            contador++;
            listaNodos.add(nf.nNodo);
            
            boolean existe1 = false;
            boolean asig = false;
            //System.out.println("Buscando: "+A1);
            for (int j = 0; j < grafos.size(); j++) {
                if (A1.equals(grafos.get(j).cadena)){
                    ni.Siguientes.add(new sig("Epsilon",grafos.get(j).primero));
                    listaGrafo.add(new Nodon(ni.nNodo,"Epsilon",grafos.get(j).primero.nNodo));
                    
                    if (!asig){
                        grafos.get(j).ultimo.Siguientes.add(new sig("Epsilon",nf));
                        listaGrafo.add(new Nodon(grafos.get(j).ultimo.nNodo,"Epsilon",nf.nNodo));
                        asig=true;
                    }
                    
                    ni.Siguientes.add(new sig("Epsilon",nf));
                    listaGrafo.add(new Nodon(ni.nNodo,"Epsilon",nf.nNodo));
                    
                    //grafos.get(j).ultimo.Siguientes.add(new sig("Epsilon",grafos.get(j).primero));
                    //listaGrafo.add(new Nodon(grafos.get(j).ultimo.nNodo,"Epsilon",grafos.get(j).primero.nNodo));
                    
                    grafos.remove(j);
                    existe1=true;
                }
            }
            if(!existe1){
                Nodo ns1 = new Nodo("S"+contador);
                contador++;
                listaNodos.add(ns1.nNodo);
                
                ni.Siguientes.add(new sig("Epsilon",ns1));
                listaGrafo.add(new Nodon(ni.nNodo,"Epsilon",ns1.nNodo));
                
                Nodo nss1 = new Nodo("S"+contador);
                contador++;
                listaNodos.add(nss1.nNodo);
                
                ns1.Siguientes.add(new sig(A1,nss1));
                listaGrafo.add(new Nodon(ns1.nNodo,A1,nss1.nNodo));
                
                nss1.Siguientes.add(new sig("Epsilon",nf));
                listaGrafo.add(new Nodon(nss1.nNodo,"Epsilon",nf.nNodo));
                
                ni.Siguientes.add(new sig("Epsilon",nf));
                listaGrafo.add(new Nodon(ni.nNodo,"Epsilon",nf.nNodo));
                
                //nss1.Siguientes.add(new sig("Epsilon",ns1));
                //listaGrafo.add(new Nodon(nss1.nNodo,"Epsilon",ns1.nNodo));
            }
            //System.out.println("Guardando: "+"("+A1+Simbolo+")");
            grafos.add(new grafo("("+A1+Simbolo+")",ni,nf));
            
        }
    }
    
    public static String grafica(Nodo n, String ER, String nombre){
        dot="digraph structs {\n" +
        "    node [shape=circle];\n"+
        "    rankdir=LR;"+
        "    label = \""+ER.replace("\"", "'")+"\";\n";
        Nodo Remplazo1 = n;
        Nodo Remplazo2 = n;
        crearNodos(Remplazo1);
        unirNodos(Remplazo2);
        dot+="}";
        //System.out.println(dot);//.replace('l', 'p')
        try {
            String ruta = "AFND_202004765\\AFND_"+nombre+".dot";

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
            return "Ocurrió un error al generar \"AFND_"+nombre+".dot\"";
        }
        
        try {
            String cmd = "bin\\dot.exe -Tpng AFND_202004765\\AFND_"+nombre+".dot -o AFND_202004765\\AFND_"+nombre+".png";
            Runtime.getRuntime().exec(cmd); 
        } catch (IOException ioe) {
            System.out.println (ioe);
            return "Ocurrió un error al generar \"AFND_"+nombre+".png\"";
        }
        return "Tabla de Siguientes \"AFND_"+nombre+"\" generado correctamente";
    }
    
    public static void crearNodos(Nodo n){
        for (int j = 0; j < listaNodos.size(); j++) {
            dot+="    Nodo"+listaNodos.get(j)+" [label=\""+listaNodos.get(j)+"\" ];\n";
        }
    }
    
    public static void unirNodos(Nodo n){
        for (int j = 0; j < listaGrafo.size(); j++) {
            dot+="    Nodo"+listaGrafo.get(j).nNodo+" -> Nodo"+listaGrafo.get(j).sig+"[label=\""+listaGrafo.get(j).simbolo.replace("\"", "'")+"\" color=\"orange\"];\n";
        }
    }
}
