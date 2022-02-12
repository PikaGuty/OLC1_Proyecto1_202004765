package Analizadores;

import java.io.StringReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author gohan
 */
public class Metodos {
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
                        System.out.println(lexer.lexeme);
                        token = lexer.yylex();
                        System.out.println(lexer.lexeme);
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

    public static void ordenarExpresionRegular(LinkedList ER){
        
        for (int i = 0; i < ER.size(); i++) {
            String Expresion="";
            LinkedList Actual = (LinkedList) ER.get(i);
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
            while(true){
                Exp=cadena.split(",");
                
                if (Exp.length>1){
                    cadena=analizar(Exp);
                }else{
                    break;
                }
            }
            System.out.println(cadena);
        }
    }
    
    public static String analizar(String[] Exp){
        boolean primero=true;
        String cadena = "";
        for(int i = 0; i < Exp.length; i++) {
            //System.out.println(Exp[i]);
            if ("|".equals(Exp[i])||".".equals(Exp[i])){
                i++;
                if(!"|".equals(Exp[i])&&!".".equals(Exp[i])&&!"*".equals(Exp[i])&&!"?".equals(Exp[i])&&!"+".equals(Exp[i])){
                    i++;
                    if(!"|".equals(Exp[i])&&!".".equals(Exp[i])&&!"*".equals(Exp[i])&&!"?".equals(Exp[i])&&!"+".equals(Exp[i])){
                        //System.out.println("("+Exp[i-1]+Exp[i-2]+Exp[i]+")");
                        if (primero){
                            cadena += "("+Exp[i-1]+Exp[i-2]+Exp[i]+")";
                            primero = false;
                        }else{
                            cadena += ","+"("+Exp[i-1]+Exp[i-2]+Exp[i]+")";
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
                        primero = false;
                    }else{
                        cadena += ","+Exp[i]+Exp[i-1];
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
