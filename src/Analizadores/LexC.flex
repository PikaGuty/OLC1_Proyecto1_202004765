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

L=[a-zA-Z_]+
LU=[a-zA-Z]
D=[0-9]+
DU=[0-9]
S=["_"|"-"|"/"|"."|","|"!"|"@"|"#"|"$"|"%"|"^"|"&"|"*"|"|"|"("|")"|"="|"+"|"\\"|":"|";"|"<"|">"|"?"|"`"|"{"|"}"|"["|"]"|"'"]
espacio=[ \t\r\n]+


%{
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }
    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }
%}
%%

//EXPRESION REGULAR                        CODIGO ASOCIADO                              

CONJ                                    {return new Symbol(sym.Reservadas, yychar, yyline, yytext());}  
"<!"(.*|\n)*"!>"                        {/*Ignore*/}                                                                          //COMENTARIOS MULTINLINEA
"%%"(\n)+"%%"|"%%"                      {return new Symbol(sym.SEPARADOR, yychar, yyline, yytext());}                         //SEPARADOR

{espacio}                               {/*Ignore*/}                                                                          //IGNORA ESPACIOS
"//".*                                  {/*Ignore*/}                                                                          //COMENTARIOS DE UNA LINEA
"->"                                    {return new Symbol(sym.FLECHA, yychar, yyline, yytext());}                            //FLECHA
"~"                                     {return new Symbol(sym.TILD, yychar, yyline, yytext());}                              //Curvita

";"                                     {return new Symbol(sym.PTCOMA, yychar, yyline, yytext()); }                            //PUNTO Y COMA
"{"                                     {return new Symbol(sym.LLAVEI, yychar, yyline, yytext()); }                            //LLAVE INICIAL
"}"                                     {return new Symbol(sym.LLAVEF, yychar, yyline, yytext()); }                            //LLAVE FINAL
"("                                     {return new Symbol(sym.PARENTESISI, yychar, yyline, yytext()); }                       //LLAVE INICIAL
")"                                     {return new Symbol(sym.PARENTESISF, yychar, yyline, yytext()); }                       //LLAVE FINAL
"["                                     {return new Symbol(sym.CORCHETEI, yychar, yyline, yytext()); }                         //LLAVE INICIAL
"]"                                     {return new Symbol(sym.CORCHETEF, yychar, yyline, yytext()); }                         //LLAVE FINAL
":"                                     {return new Symbol(sym.DPUNTOS, yychar, yyline, yytext());}                           //DOS PUNTOS
"."                                     {return new Symbol(sym.PUNTO, yychar, yyline, yytext()); }                             //PUNTO
","                                     {return new Symbol(sym.COMA, yychar, yyline, yytext()); }                             //COMA
"*"                                     {return new Symbol(sym.ASTERISCO, yychar, yyline, yytext()); }                         //ASTERISCO
"|"                                     {return new Symbol(sym.BARRA, yychar, yyline, yytext()); }                             //BARRA
"+"                                     {return new Symbol(sym.MAS, yychar, yyline, yytext()); }                               //MAS
"?"                                     {return new Symbol(sym.INTERROG, yychar, yyline, yytext()); }                          //INTERROGACION
"\\\""                                  {return new Symbol(sym.COMDOB, yychar, yyline, yytext()); }                          //INTERROGACION

{S}                                     {return new Symbol(sym.SIMBOLO, yychar, yyline, yytext());}
\"([^\"]*)\"|\'([^\']*)\'               {return new Symbol(sym.STRING, yychar, yyline, yytext());}   
{LU}                                    {return new Symbol(sym.LETRAU, yychar, yyline, yytext());}                            //Letra Unica
{DU}                                    {return new Symbol(sym.DIGITU, yychar, yyline, yytext());}                            //Digito Unica
{L}({L}|{D})*                           {return new Symbol(sym.Identificador, yychar, yyline, yytext());}                     //IDENTIFICADOR

 . {String errLex = "Error léxico : '"+yytext()+"' en la línea: "+(yyline+1)+" y columna: "+(yycolumn+1);
        Principal.NotErrorL(errLex);}


