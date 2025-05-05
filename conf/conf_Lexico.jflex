package unam.fes.aragon.compilador;
import java_cup.runtime.*;
import java.util.LinkedList;
import unam.fes.aragon.codigo.TError;
import unam.fes.aragon.codigo.TablaSimbolos;

%%
%{

    private TablaSimbolos tabla;
    public Analizador_Lexico(java.io.InputStream in, TablaSimbolos t){
        this(in);
        this.tabla = t;
    }
    public int getYyline(){
        return yyline;
    }
    public int getYy_currentPos(){
        return yy_currentPos - 1;
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

// Expresiones regulares
ID = [a-zA-Z_][a-zA-Z_0-9]*
INT = [0-9]+

%%
<YYINITIAL> "+"         { System.out.println("Reconocio "+yytext()+" mas"); return new Symbol(Simbolos.MAS, yycolumn, yyline); }
<YYINITIAL> "-"         { System.out.println("Reconocio "+yytext()+" menos"); return new Symbol(Simbolos.MENOS, yycolumn, yyline); }
<YYINITIAL> "*"         { System.out.println("Reconocio "+yytext()+" por"); return new Symbol(Simbolos.POR, yycolumn, yyline); }
<YYINITIAL> "/"         { System.out.println("Reconocio "+yytext()+" div"); return new Symbol(Simbolos.ENTRE, yycolumn, yyline); }
<YYINITIAL> ";"         { System.out.println("Reconocio "+yytext()+" punto y coma"); return new Symbol(Simbolos.PUNTOYCOMA, yycolumn, yyline); }
<YYINITIAL> "("         { System.out.println("Reconocio "+yytext()+" para"); return new Symbol(Simbolos.LPAREN, yycolumn, yyline); }
<YYINITIAL> ")"         { System.out.println("Reconocio "+yytext()+" parc"); return new Symbol(Simbolos.RPAREN, yycolumn, yyline); }
<YYINITIAL> ">"         { System.out.println("Reconocio "+yytext()+" mayor"); return new Symbol(Simbolos.MAYOR, yycolumn, yyline); }
<YYINITIAL> ">="        { System.out.println("Reconocio "+yytext()+" mayorigual"); return new Symbol(Simbolos.MAYORIGUAL, yycolumn, yyline); }
<YYINITIAL> "<"         { System.out.println("Reconocio "+yytext()+" menor"); return new Symbol(Simbolos.MENOR, yycolumn, yyline); }
<YYINITIAL> "<="        { System.out.println("Reconocio "+yytext()+" menorigual"); return new Symbol(Simbolos.MENORIGUAL, yycolumn, yyline); }
<YYINITIAL> "="         { System.out.println("Reconocio "+yytext()+" asigna"); return new Symbol(Simbolos.ASIG, yycolumn, yyline); }
<YYINITIAL> "PRINT"     { System.out.println("Reconocio "+yytext()+" print"); return new Symbol(Simbolos.PRINT, yycolumn, yyline); }

<YYINITIAL> {ID} {
    Datos s;
    if((s = tabla.buscar(yytext())) == null){
        s = tabla.insertar(yytext());
    }
    System.out.println("Reconocio "+yytext()+" ID");
    return new Symbol(Simbolos.ID, yycolumn, yyline, s);
}

<YYINITIAL> {INT} {
    System.out.println("Reconocio "+yytext()+" numero");
    return new Symbol(Simbolos.NUMERO, yycolumn, yyline, Integer.parseInt(yytext()));
}

[ \t\r\n\f] {/* espacios en blanco, ignorar */}

. {
    System.out.println("Error Lexico "+yytext()+" Linea "+yyline+" Columna "+yycolumn);
    TError datos = new TError(yytext(), yyline, yycolumn, "Error Lexico", "Simbolo no existe en el lenguaje");
    TablaEL.add(datos);
}
