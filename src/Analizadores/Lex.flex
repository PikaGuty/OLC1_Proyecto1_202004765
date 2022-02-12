package Analizadores;
import static Analizadores.Tokens.*;

%%
%class Lex
%line
%char
%type Tokens
L=[a-zA-Z_]+
LU=[a-zA-Z]
D=[0-9]+
DU=[0-9]
S=[ |"!"|"\""|"#"|"$"|"%"|"&"|"\'"|"("|")"|"*"|"+"|","|"-"|"."|"/"|":"|";"|"<"|"="|">"|"?"|"@"|"["|"\\"|"]"|"^"|"_"|"`"|"{"|"|"|"}"]
espacio=[ \t\r]+
%{
    public String lexeme;
    public int linea, col;
%}

%init{
    yyline = 1;
    yychar = 0;
%init}

%%

//EXPRESION REGULAR                        CODIGO ASOCIADO                               QUE HACE

CONJ                                    {lexeme=yytext(); linea=yyline; col=yychar; return Reservadas;}       //IDENTIFICA PALABRAS RESERVADAS
"<!"(.*|\n)*"!>"                        {/*Ignore*/}                                                          //COMENTARIOS MULTINLINEA
"%%"(\n)+"%%"                           {lexeme=yytext(); linea=yyline; col=yychar; return SEPARADOR;}        //SEPARADOR
((\").*(\"))|((\').*(\'))               {lexeme=yytext(); linea=yyline; col=yychar; return STRING;}           //LEXEMA
{espacio}                               {/*Ignore*/}                                                          //IGNORA ESPACIOS
"//".*                                  {/*Ignore*/}                                                          //COMENTARIOS DE UNA LINEA
"->"                                    {lexeme=yytext(); linea=yyline; col=yychar; return FLECHA;}           //FLECHA
"~"                                     {lexeme=yytext(); linea=yyline; col=yychar; return TILD;}             //Curvita
"\n"                                    {lexeme=yytext(); yychar = 0; return Linea;}
";"                                     {lexeme=yytext(); linea=yyline; col=yychar; return PTCOMA;}            //PUNTO Y COMA
"{"                                     {lexeme=yytext(); linea=yyline; col=yychar; return LLAVEI;}            //LLAVE INICIAL
"}"                                     {lexeme=yytext(); linea=yyline; col=yychar; return LLAVEF;}            //LLAVE FINAL
"("                                     {lexeme=yytext(); linea=yyline; col=yychar; return PARENTESISI;}       //LLAVE INICIAL
")"                                     {lexeme=yytext(); linea=yyline; col=yychar; return PARENTESISF;}       //LLAVE FINAL
"["                                     {lexeme=yytext(); linea=yyline; col=yychar; return CORCHETEI;}         //LLAVE INICIAL
"]"                                     {lexeme=yytext(); linea=yyline; col=yychar; return CORCHETEF;}         //LLAVE FINAL
":"                                     {lexeme=yytext(); linea=yyline; col=yychar; return DPUNTOS;}           //DOS PUNTOS
"."                                     {lexeme=yytext(); linea=yyline; col=yychar; return PUNTO;}             //PUNTO
","                                     {lexeme=yytext(); linea=yyline; col=yychar; return COMA;}              //COMA
"*"                                     {lexeme=yytext(); linea=yyline; col=yychar; return ASTERISCO;}         //ASTERISCO
"|"                                     {lexeme=yytext(); linea=yyline; col=yychar; return BARRA;}             //BARRA
"+"                                     {lexeme=yytext(); linea=yyline; col=yychar; return MAS;}               //MAS
"?"                                     {lexeme=yytext(); linea=yyline; col=yychar; return INTERROG;}          //INTERROGACION

{S}                                     {lexeme=yytext(); linea=yyline; col=yychar; return SIMBOLO;}
{LU}                                    {lexeme=yytext(); linea=yyline; col=yychar; return LETRAU;}           //Letra Unica
{DU}                                    {lexeme=yytext(); linea=yyline; col=yychar; return DIGITU;}           //Digito Unica
{L}({L}|{D})*                           {lexeme=yytext(); linea=yyline; col=yychar; return Identificador;}    //IDENTIFICADOR

 . {return ERROR;}
