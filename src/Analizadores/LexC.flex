package Analizadores;
import java_cup.runtime.Symbol;
import java.util.LinkedList;

%%
%class LexC
%type java_cup.runtime.Symbol

%cup
%full
%line
%char
%debug

%state comentario
%state verificar

L=[a-zA-Z_]+
LU=[a-zA-Z]
D=[0-9]+
DU=[0-9]
S=["_"|"-"|"/"|"."|","|"!"|"@"|"#"|"$"|"%"|"^"|"&"|"*"|"|"|"("|")"|"="|"+"|"\\"|":"|";"|">"|"?"|"`"|"{"|"}"|"["|"]"|"'"]
espacio=[\ \t\r\n\f]


%{
    boolean esComentario=false;
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }
    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }
%}
%%

//EXPRESION REGULAR                        CODIGO ASOCIADO                              

<YYINITIAL>CONJ                                    {return new Symbol(sym.Reservadas, yychar, yyline, yytext());}  
<YYINITIAL>"<"                                     {yybegin(verificar);}   
                                                                       //COMENTARIOS MULTINLINEA
<YYINITIAL>"%%"(\n)+(\t)*"%%"|"%%"                      {return new Symbol(sym.SEPARADOR, yychar, yyline, yytext());}                         //SEPARADOR

<YYINITIAL>{espacio}                               {/*Ignore*/}                                                                          //IGNORA ESPACIOS
<YYINITIAL>"//".*                                  {/*Ignore*/}                                                                          //COMENTARIOS DE UNA LINEA
<YYINITIAL>"->"                                    {return new Symbol(sym.FLECHA, yychar, yyline, yytext());}                            //FLECHA
<YYINITIAL>"~"                                     {return new Symbol(sym.TILD, yychar, yyline, yytext());}                              //Curvita

<YYINITIAL>";"                                     {return new Symbol(sym.PTCOMA, yychar, yyline, yytext()); }                            //PUNTO Y COMA
<YYINITIAL>"{"                                     {return new Symbol(sym.LLAVEI, yychar, yyline, yytext()); }                            //LLAVE INICIAL
<YYINITIAL>"}"                                     {return new Symbol(sym.LLAVEF, yychar, yyline, yytext()); }                            //LLAVE FINAL
<YYINITIAL>"("                                     {return new Symbol(sym.PARENTESISI, yychar, yyline, yytext()); }                       //LLAVE INICIAL
<YYINITIAL>")"                                     {return new Symbol(sym.PARENTESISF, yychar, yyline, yytext()); }                       //LLAVE FINAL
<YYINITIAL>"["                                     {return new Symbol(sym.CORCHETEI, yychar, yyline, yytext()); }                         //LLAVE INICIAL
<YYINITIAL>"]"                                     {return new Symbol(sym.CORCHETEF, yychar, yyline, yytext()); }                         //LLAVE FINAL
<YYINITIAL>":"                                     {return new Symbol(sym.DPUNTOS, yychar, yyline, yytext());}                           //DOS PUNTOS
<YYINITIAL>"."                                     {return new Symbol(sym.PUNTO, yychar, yyline, yytext()); }                             //PUNTO
<YYINITIAL>","                                     {return new Symbol(sym.COMA, yychar, yyline, yytext()); }                             //COMA
<YYINITIAL>"*"                                     {return new Symbol(sym.ASTERISCO, yychar, yyline, yytext()); }                         //ASTERISCO
<YYINITIAL>"|"                                     {return new Symbol(sym.BARRA, yychar, yyline, yytext()); }                             //BARRA
<YYINITIAL>"+"                                     {return new Symbol(sym.MAS, yychar, yyline, yytext()); }                               //MAS
<YYINITIAL>"?"                                     {return new Symbol(sym.INTERROG, yychar, yyline, yytext()); }                          //INTERROGACION
<YYINITIAL>"\\\""                                  {return new Symbol(sym.COMDOB, yychar, yyline, yytext()); }                          //INTERROGACION

<YYINITIAL>{S}                                     {return new Symbol(sym.SIMBOLO, yychar, yyline, yytext());}
<YYINITIAL>\"([^\"]*)\"|\'([^\']*)\'               {return new Symbol(sym.STRING, yychar, yyline, yytext());}   
<YYINITIAL>{LU}                                    {return new Symbol(sym.LETRAU, yychar, yyline, yytext());}                            //Letra Unica
<YYINITIAL>{DU}                                    {return new Symbol(sym.DIGITU, yychar, yyline, yytext());}                            //Digito Unica
<YYINITIAL>{L}({L}|{D})*                           {return new Symbol(sym.Identificador, yychar, yyline, yytext());}                     //IDENTIFICADOR

<YYINITIAL> . {Principal.NotErrorL("Léxico",yytext(), (yyline+1),(yycolumn+1));}

<verificar> {
^["!"]                                {yybegin(YYINITIAL);yypushback(1);return new Symbol(sym.SIMBOLO, yychar, yyline, yytext()); }
"!"                                 {yybegin(comentario);}

}
<comentario> {

"!"                                     {esComentario=true;}
">"                                     {if(esComentario){yybegin(YYINITIAL);}else{Principal.NotErrorL("Léxico",yytext(), (yyline+1),(yycolumn+1));}}  
[^\!\>]                                 {}
}


