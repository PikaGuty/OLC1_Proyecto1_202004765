package Analizadores;

import java.util.LinkedList;

/**
 *
 * @author gohan
 */
public class AnalizarCadena {
    public static void analiza(LinkedList conj, LinkedList prueba, LinkedList automatas){
        System.out.println("********************** PRUEBAS **************************");
        for (int i = 0; i < prueba.size(); i++) {
            LinkedList act = (LinkedList) prueba.get(i);
            
            boolean existe =  false;
            LinkedList l=new LinkedList();
            
            for(int k=0; k<automatas.size();k++){
                //System.out.println("PRUEBA "+act.getFirst());
                String c1 = (String) act.getFirst();
                l = (LinkedList) automatas.get(k);
                String c2 = (String) l.getFirst();
                if(c1.equals(c2)){
                    System.out.println(automatas.get(k));
                    existe = true;
                }
            }
            if(!existe){
                System.out.println("NO EXISTEEEE");
                break;
            }
            
            for(int m=0; m<l.size();m++){
                System.out.println(l.get(m));
            }
                        
            
            System.out.println("Primero "+act.getFirst());
            System.out.println("Ultimo "+act.getLast());
            String Cadena =  (String) act.getLast();
            String[] cad = Cadena.split("");
            //UNIENDO
            String nCadena = "";
            for (int j = 1; j < cad.length-1; j++) {
                nCadena+=cad[j];
            }
            //SECCIONANDO
            String[] tCadena = (nCadena+"#FIN").split("");
            String actual = "", transicion = "", siguiente = "";
            
            for (int j = 0; j < tCadena.length; j++) {
                System.out.println(tCadena[j]);
            }
        }
        //System.out.println("********************** CONJUNTOS **************************");
        //for (int i = 0; i < conj.size(); i++) {
            //LinkedList act = (LinkedList) conj.get(i);
            //System.out.println(act.getFirst());
        //}
    }
}
