package Analizadores;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

/**
 *
 * @author gohan
 */
public class AnalizarCadena {
    
    public static boolean encuentra = false;
    public static String buffer = "";
    public static String json = "[";
    public static boolean primero = true;
    
    public static String analiza(LinkedList conj, LinkedList prueba, LinkedList automatas){
        json = "[";
        primero = true;
        System.out.println("********************** PRUEBAS **************************");
        
        for (int i = 0; i < prueba.size(); i++) {
            LinkedList ListaAutomatas = new LinkedList();
            LinkedList expresionActual = (LinkedList) prueba.get(i);
            String actual = "", ultimo= "";
            buffer = "";
            LinkedList listaAutomatas = existeAutomata(automatas,ListaAutomatas, expresionActual);
            
            if (listaAutomatas!=null){
                String Cadena =  (String) expresionActual.getLast();
                String[] nCadena = seccionarCadena(Cadena);
                                
                //*********** ESTABLECIENDO PRIMER NODO DEL AFD ***********
                LinkedList est = (LinkedList) listaAutomatas.get(1);
                actual = (String) est.get(0);
                est = (LinkedList) listaAutomatas.getLast();
                ultimo = (String) est.get(1);
                System.out.println("EL ULTIMO ES "+ultimo);
                //*********************************************************
                for (int j = 0; j < listaAutomatas.size(); j++) {
                    System.out.println(listaAutomatas.get(j));
                }
                for (int j = 0; j < nCadena.length; j++) {
                    //System.out.println("-"+nCadena[j]);
                    System.out.println("BUFFER: "+buffer);
                    
                    if(!buffer.equals("")){
                        buffer+=nCadena[j];
                        nCadena[j]=buffer;
                    }
                    for (int k = 1; k < listaAutomatas.size(); k++) {
                        LinkedList Estado = (LinkedList) listaAutomatas.get(k);
                        String estadoAc = (String) Estado.get(0);

                        if(actual.equals(estadoAc)){
                            //System.out.println(Estado);
                            actual=continuar(Estado,nCadena[j],actual,conj);     
                            if(encuentra==true){
                                buffer="";
                                break;
                            }
                        }
                    }
                    if(!encuentra && buffer.equals("")){
                        buffer=nCadena[j];
                    }
                }
                if(!buffer.equals("")){
                    System.out.println("FALLO por que no se encontro string");
                }
            }else{
                System.out.println("NO EXISTE");
                //HACER ERROR DESPUES
            }
            if(buffer.equals("")&&actual.equals(ultimo)){
                if(primero){
                    json+="\n\t{\n"
                            + "\t\t\"Valor\":"+expresionActual.getLast()+",\n"
                            + "\t\t\"Expresion Regular\":\""+expresionActual.getFirst()+"\",\n"
                            + "\t\t\"Resultado\":"+"\"CADENA VALIDA\"\n"
                            +"\t}";
                    System.out.println("CADENA ACEPTADA");
                    primero=false;
                }else{
                    json+=",\n\t{\n"
                            + "\t\t\"Valor\":"+expresionActual.getLast()+",\n"
                            + "\t\t\"Expresion Regular\":\""+expresionActual.getFirst()+"\",\n"
                            + "\t\t\"Resultado\":"+"\"CADENA VALIDA\"\n"
                            +"\t}";
                    System.out.println("CADENA ACEPTADA");
                }
            }else{
                if(primero){
                    json+="\n\t{\n"
                            + "\t\t\"Valor\":"+expresionActual.getLast()+",\n"
                            + "\t\t\"Expresion Regular\":\""+expresionActual.getFirst()+"\",\n"
                            + "\t\t\"Resultado\":"+"\"CADENA INVALIDA\"\n"
                            +"\t}";
                    System.out.println("CADENA ACEPTADA");
                    primero=false;
                }else{
                    json+=",\n\t{\n"
                            + "\t\t\"Valor\":"+expresionActual.getLast()+",\n"
                            + "\t\t\"Expresion Regular\":\""+expresionActual.getFirst()+"\",\n"
                            + "\t\t\"Resultado\":"+"\"CADENA INVALIDA\"\n"
                            +"\t}";
                    System.out.println("CADENA ACEPTADA");
                }
            }
        }
        json+="\n]";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyyhhmmss");
        String nombre = dtf.format(LocalDateTime.now());
        try {
            String ruta = "SALIDAS_202004765\\SALIDA_"+nombre+".json";

            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(json);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
            return "Ocurrió un error al generar \"SALIDA_"+nombre+".json\"";
        }
        return "Tabla de Siguientes \"TablaSig_"+nombre+"\" generado correctamente";
    }
    
    public static String continuar(LinkedList Estado, String caracter, String actual, LinkedList conjuntos){
        encuentra = false;
        String transicion = (String) Estado.get(2);
        LinkedList permitidos = new LinkedList();
        //Verificando si es un conjunto
        if(transicion.contains("{")&&transicion.contains("}")){

            //System.out.print("ACORTANDO DE "+transicion);
            String[] tTrans = (transicion).split("");
            transicion = "";
            for (int k = 1; k < tTrans.length-1; k++) {
                transicion+=tTrans[k];
            }
            //System.out.println(" a "+transicion);
            if(!"#FIN".equals(transicion)){
                permitidos=verificarConjuntos(transicion,conjuntos);
                System.out.println(permitidos);
            }else{
                permitidos.add("#FIN");
            }
            
            for (int j = 0; j < permitidos.size(); j++) {
                if(caracter.equals(permitidos.get(j).toString())){
                    //System.out.println("SI PUEDE PASAR");
                    System.out.println("PASANDO DE "+Estado.get(0)+" a "+Estado.get(1));
                    actual = Estado.get(1).toString();
                    encuentra = true;
                    break;
                }else{
                    if(j==permitidos.size()-1){
                        //System.out.println("NO ECONTRO");
                    }
                }
            }
            
            //System.out.println("CONTENIDO DEL CONJUNTO "+transicion);
        }else{
            
            String[] tTrans = (transicion).split("");
            transicion = "";
            for (int k = 1; k < tTrans.length-1; k++) {
                transicion+=tTrans[k];
            }
            
            //System.out.println(" EN UN STRING "+transicion);
            permitidos.add(transicion);
            
            for (int j = 0; j < permitidos.size(); j++) {
                if(caracter.equals(permitidos.get(j).toString())){
                    //System.out.println("SI PUEDE PASAR");
                    //System.out.println("PASANDO DE "+Estado.get(0)+" a "+Estado.get(1));
                    actual = Estado.get(1).toString();
                    encuentra = true;
                    break;
                }else{
                    if(j==permitidos.size()-1){
                        System.out.println("NO ECONTRO");
                    }
                }
            }
        }
        return actual;
    }
    
    public static LinkedList verificarConjuntos(String trans, LinkedList conjuntos){
        String transicion = "";
        LinkedList permitidos = new LinkedList();
        for (int j = 0; j < conjuntos.size(); j++) {
            LinkedList sub = (LinkedList) conjuntos.get(j);
            if(trans.equals((String) sub.getFirst())){
                //HOMOGENIZANDO SEGUNDO ELEMENTO
                if("String".equals(sub.get(1).getClass().getSimpleName())){
                    transicion = (String) sub.get(1);
                }else if("LinkedList".equals(sub.get(1).getClass().getSimpleName())){
                    LinkedList ListaConj = (LinkedList) sub.get(1);
                    String lista = "";
                    boolean prim = true;
                    for (int f = 0; f < ListaConj.size(); f++) {
                        if(prim){
                            lista+=ListaConj.get(f);
                            prim = false;
                        }else{
                            lista+=","+ListaConj.get(f);
                        }
                    }
                    transicion = lista;
                }
                //System.out.println(sub.getFirst()+" -> "+transicion);
                String[] cantidad = (transicion).split("");
                if(cantidad.length==3&&transicion.contains("~")){
                    String[] caracterS = (transicion).split("~");
                    char caracter1 = caracterS[0].charAt(0); 
                    char caracter2 = caracterS[1].charAt(0); 
                    int c1 = caracter1;
                    int c2 = caracter2;
                    //System.out.println("CARACTER 1 "+caracter1+" "+c1);
                    //System.out.println("CARACTER 2 "+caracter2+" "+c2);

                    if(c1>=97&&c1<=122&&c2>=97&&c2<=122){//Minusculas
                        for(int ll=c1; ll <= c2; ll++){
                            char b = (char) ll;
                            permitidos.add(b);
                        }
                    }else if(c1>=65&&c1<=90&&c2>=65&&c2<=90){//Mayusculas
                        for(int ll=c1; ll <= c2; ll++){
                            char b = (char) ll;
                            permitidos.add(b);
                        }
                    }else if(c1>=48&&c1<=57&&c2>=48&&c2<=57){//Numeros
                        for(int ll=c1; ll <= c2; ll++){
                            char b = (char) ll;
                            permitidos.add(b);
                        }
                    }else if(c1>=32&&c1<=125&&c2>=32&&c2<=125){//CARACTERES
                        if(c1>=97&&c1<=122){                                        
                        }else if(c2>=97&&c2<=122){//Minusculas
                        }else if(c1>=65&&c1<=90){                                    
                        }else if(c2>=65&&c2<=90){//Mayusculas
                        }else if(c1>=48&&c1<=57){                                   
                        }else if(c2>=48&&c2<=57){
                        }else{
                            for(int ll=c1; ll <= c2; ll++){
                                char b = (char) ll;
                                permitidos.add(b);
                            }
                        }
                    }
                }else if(transicion.contains(",")){
                    String[] caracterS = (transicion).split(",");
                    char[] caracteres;
                    //System.out.println("CARACTERSSSSSSSSSSSSSSSSSSSSSSSSSS CONJ");
                    for (int h = 0; h < caracterS.length; h++) {
                        permitidos.add(caracterS[h]);
                    }
                }else if(transicion.contains("~")){
                    String[] caracterS = (transicion).split("~");
                    char caracter1 = caracterS[0].charAt(0); 
                    char caracter2 = caracterS[1].charAt(0); 
                    int c1 = caracter1;
                    int c2 = caracter2;
                    //System.out.println("CARACTER 1 "+caracter1+" "+c1);
                    //System.out.println("CARACTER 2 "+caracter2+" "+c2);

                    if(c1>=97&&c1<=122&&c2>=97&&c2<=122){//Minusculas
                        for(int ll=c1; ll <= c2; ll++){
                            char b = (char) ll;
                            permitidos.add(b);
                        }
                    }else if(c1>=65&&c1<=90&&c2>=65&&c2<=90){//Mayusculas
                        for(int ll=c1; ll <= c2; ll++){
                            char b = (char) ll;
                            permitidos.add(b);
                        }
                    }else if(c1>=48&&c1<=57&&c2>=48&&c2<=57){//Numeros
                        for(int ll=c1; ll <= c2; ll++){
                            char b = (char) ll;
                            permitidos.add(b);
                        }
                    }else if(c1>=32&&c1<=125&&c2>=32&&c2<=125){//CARACTERES
                        if(c1>=97&&c1<=122){                                        
                        }else if(c2>=97&&c2<=122){//Minusculas
                        }else if(c1>=65&&c1<=90){                                    
                        }else if(c2>=65&&c2<=90){//Mayusculas
                        }else if(c1>=48&&c1<=57){                                   
                        }else if(c2>=48&&c2<=57){
                        }else{
                            for(int ll=c1; ll <= c2; ll++){
                                char b = (char) ll;
                                permitidos.add(b);
                            }
                        }
                    }       
                }
                //System.out.println(permitidos);
                return permitidos;
            }
        }
        return null;
    }

    
    public static String[] seccionarCadena(String cadena){
        String[] cad = cadena.split("");
        //UNIENDO
        String nCadena = "";
        for (int j = 1; j < cad.length-1; j++) {
            nCadena+=cad[j];
        }
        String[] tCadena = (nCadena+"#FIN").split("");
        return tCadena;
    }
            
    public static LinkedList existeAutomata(LinkedList automatas, LinkedList listaAutomatas, LinkedList act){
        boolean existe = false;
        for(int k=0; k<automatas.size();k++){
            //System.out.println("PRUEBA "+act.getFirst());
            String c1 = (String) act.getFirst();
            listaAutomatas = (LinkedList) automatas.get(k);
            String c2 = (String) listaAutomatas.getFirst();
            if(c1.equals(c2)){
                //System.out.println(automatas.get(k));
                existe = true;
                return listaAutomatas;
            }
        }
        return null;
    }
}
