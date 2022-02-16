package Analizadores;

public class Arbol {
    Nodo raiz;
    public Arbol(){
        raiz = null;
    }
    
    public void insertar(int i, Object fruta){
        Nodo n = new Nodo(i);
        n.contenido=fruta;
        
        if (raiz==null) {
            raiz=n;
        }else{
            Nodo aux = raiz;
            while(aux != null){
                n.padre = aux;
                if (n.llave >= aux.llave) {
                    aux=aux.derecha;
                }else{
                    aux=aux.izquierda;
                }
            }
            if (n.llave < n.padre.llave) {
                n.padre.izquierda = n;
            }else{
                n.padre.derecha=n;
            }
        }
    }
    
    
    public void recorrer(Nodo n){
        if (n != null) {
            recorrer(n.izquierda);
            System.out.println("Indice " +n.llave+" fruta " + n.contenido);
            recorrer(n.derecha);
        }
    }
    
  
    private class Nodo{
    
        public Nodo padre;
        public Nodo derecha;
        public Nodo izquierda;
        public int llave;
        public Object contenido;
        
        public Nodo(int indice){
            llave = indice;
            derecha=null;
            izquierda=null;
            padre=null;
            contenido=null;
        }
    }
}
