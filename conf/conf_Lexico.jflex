package unam.fes.aragon.compilador;
import java_cup.runtime.*;
import java.util.LinkedList;
import unam.fes.aragon.codigoLR0.elemnetosTablas.Datos;
import unam.fes.aragon.compilador.TError;
import unam.fes.aragon.codigoLR0.TablaSimbolos;

%%
%{
    private TablaSimbolos tabla;

    public Analizador_Lexico(java.io.Reader in, TablaSimbolos t){
        this.yyreset(in);
        this.tabla = t;
    }

    public static LinkedList<TError> TablaEL = new LinkedList<TError>();
%}

%class Analizador_Lexico
%cupsym Simbolos
%public
%char
%cup
%line
%column
%ignorecase
%type java_cup.runtime.Symbol
%eofval{
    return new Symbol(Simbolos.EOF, new String("Fin del archivo"));
%eofval}
%full
%unicode

ID = [a-zA-Z_][a-zA-Z_0-9]*
INT = [0-9]+

%%
<YYINITIAL> "+"         { return new Symbol(Simbolos.MAS, yycolumn, yyline); }
<YYINITIAL> "-"         { return new Symbol(Simbolos.MENOS, yycolumn, yyline); }
<YYINITIAL> "*"         { return new Symbol(Simbolos.POR, yycolumn, yyline); }
<YYINITIAL> "/"         { return new Symbol(Simbolos.ENTRE, yycolumn, yyline); }
<YYINITIAL> ";"         { return new Symbol(Simbolos.PUNTOYCOMA, yycolumn, yyline); }
<YYINITIAL> "("         { return new Symbol(Simbolos.LPAREN, yycolumn, yyline); }
<YYINITIAL> ")"         { return new Symbol(Simbolos.RPAREN, yycolumn, yyline); }
<YYINITIAL> ">"         { return new Symbol(Simbolos.MAYOR, yycolumn, yyline); }
<YYINITIAL> ">="        { return new Symbol(Simbolos.MAYORIGUAL, yycolumn, yyline); }
<YYINITIAL> "<"         { return new Symbol(Simbolos.MENOR, yycolumn, yyline); }
<YYINITIAL> "<="        { return new Symbol(Simbolos.MENORIGUAL, yycolumn, yyline); }
<YYINITIAL> "="         { return new Symbol(Simbolos.ASIG, yycolumn, yyline); }
<YYINITIAL> "PRINT"     { return new Symbol(Simbolos.PRINT, yycolumn, yyline); }
<YYINITIAL> {ID} {
    String nombre = yytext();
    Datos dato = tabla.buscar(nombre);
    if (dato == null) {
        dato = tabla.insertar(nombre);
    }
    return new Symbol(Simbolos.ID, yycolumn, yyline, dato);
}
<YYINITIAL> {INT}       { return new Symbol(Simbolos.NUMERO, yycolumn, yyline, yytext());}
[ \t\r\n\f] {/* ignorar espacios */}

. {
    TError datos = new TError(yytext(), yyline, yycolumn, "Error Lexico", "Simbolo no existe en el lenguaje");
    TablaEL.add(datos);}
