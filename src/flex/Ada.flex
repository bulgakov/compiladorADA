/*
    ADA 95
*/
package compiladorada;
import java_cup.runtime.Symbol;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.Location;

%%

%public
%cup
%class AdaScanner
%implements sym
%char
%line
%column
%ignorecase
//%debug

%{
  StringBuilder string = new StringBuilder();
  
  public AdaScanner(java.io.Reader in, ComplexSymbolFactory sf){
    this(in);
    symbolFactory = sf;
  }
  ComplexSymbolFactory symbolFactory;

  private Symbol symbol(String name, int sym) {
    return symbolFactory.newSymbol(name, sym, new Location(yyline+1,yycolumn+1,yychar), new Location(yyline+1,yycolumn+yylength(),yychar+yylength()));
  }

  private Symbol symbol(String name, int sym, Object val) {
    Location left = new Location(yyline+1,yycolumn+1,yychar);
    Location right= new Location(yyline+1,yycolumn+yylength(), yychar+yylength());
    return symbolFactory.newSymbol(name, sym, left, right,val);
  }
  private Symbol symbol(String name, int sym, Object val,int buflength) {
    Location left = new Location(yyline+1,yycolumn+yylength()-buflength,yychar+yylength()-buflength);
    Location right= new Location(yyline+1,yycolumn+yylength(), yychar+yylength());
    return symbolFactory.newSymbol(name, sym, left, right,val);
  }
  private void error(String message) {
    System.out.println("Error at line "+(yyline+1)+", column "+(yycolumn+1)+" : "+message);
  }

  private Symbol symbol(int type) {
    return new Symbol(type, yyline+1, yycolumn+1);
  }

  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline+1, yycolumn+1, value);
  }
%}

%eofval{
     return symbolFactory.newSymbol("EOF", sym.EOF, new Location(yyline+1,yycolumn+1,yychar), new Location(yyline+1,yycolumn+1,yychar+1));
%eofval}

/* Comunes */
Caracteres      = [^\r\n]
Espacios        = [ \t\f\b]
FinDeLinea      = (\r|\n|\r\n)

/* Comentarios */
Comentario      = "--" {Caracter}* {FinDeLinea}

/* identificador */
Identificador   = [a-zA-Z]+([_][a-zA-Z0-9]+)*

/* Literales */
IntegerLiteral  = [0-9]+  
FloatLiteral    = [0-9]+"."[0-9]*  
Cadena          = [^\r\n\"\\]
Caracter        = [^\r\n\'\\]

//faltan asignaciones (:=) e iniciación de tipo de variable (:)

/*Operadores*/
OPREL           = "=" | "/=" | "<" | "<=" | ">" | ">="
OPSUM           = "+" | "-"
OPMULT          = "*" | "/" 
OPLOG           = "and" | "or"
NOT             = "not"

%state STRING

%%

<YYINITIAL> {
    /* palabras reservadas */
    "put"                       { return symbol("PUT",sym.GET); }
    "get"                       { return symbol("GET",sym.PUT); }
    
    ","                         { return symbol("COMMA",sym.COMMA); }
    ";"                         { return symbol("SEMICOLON",sym.SEMICOLON); }
    ":"                         { return symbol("COLON",sym.COLON); }
    "("                         { return symbol("LPAREN",sym.LPAREN); }
    ")"                         { return symbol("RPAREN",sym.RPAREN); }
    
    "procedure"                 { return symbol("PROCEDURE",sym.PROCEDURE); }
    "function"                  { return symbol("FUNCTION",sym.FUNCTION); }
    "is"                        { return symbol("IS",sym.IS); }
    "return"                    { return symbol("RETURN",sym.RETURN); }
    "in"                        { return symbol("IN",sym.IN); }
    "out"                       { return symbol("OUT",sym.OUT); }
    "begin"                     { return symbol("BEGIN",sym.BEGIN); }
    "end"                       { return symbol("END",sym.END); }

    "if"                        { return symbol("IF",sym.IF); }
    "then"                      { return symbol("THEN",sym.THEN); }
    "else"                      { return symbol("ELSE",sym.ELSE); }
    "elsif"                     { return symbol("ELSIF",sym.ELSIF); }
    "while"                     { return symbol("WHILE",sym.WHILE); }
    "loop"                      { return symbol("LOOP",sym.LOOP); }
    "exit"                      { return symbol("EXIT",sym.EXIT); }
    "when"                      { return symbol("WHEN",sym.WHEN); }
    "for"                       { return symbol("FOR",sym.FOR); }
    ".."                        { return symbol("DOT_DOT",sym.DOT_DOT); }

    /* TIPOS */
    "boolean"                   { return symbol("BOOLEAN",sym.BOOLEAN); }
    "integer"                   { return symbol("INTEGER",sym.INTEGER); }
    "float"                     { return symbol("FLOAT",sym.FLOAT); }

    /* LITERALES */
    "null"                      { return symbol("NULL",sym.NULL); }
    "true"                      { return symbol("TRUE",sym.TRUE); }
    "false"                     { return symbol("FALSE",sym.FALSE); }

    /* operadores */
    ":="                        { return symbol("ASSIGN",sym.ASSIGN,yytext()); }
    {OPLOG}                     { return symbol("OPLOG",sym.OPLOG,yytext()); }
    {OPREL}                     { return symbol("OPREL",sym.OPREL,yytext()); }
    {OPSUM}                     { return symbol("OPSUM",sym.OPSUM,yytext()); }
    {OPMULT}                    { return symbol("OPMULT",sym.OPMULT,yytext()); }
    {NOT}                       { return symbol("NOT",sym.NOT,yytext()); }
    
    /* cadena literal */
    "\""                        { yybegin(STRING); string.setLength(0); }

    {IntegerLiteral}            { return symbol("INTEGER_LITERAL",sym.INTEGER_LITERAL, new Integer(yytext())); }
    {FloatLiteral}              { return symbol("FLOAT_LITERAL",sym.FLOAT_LITERAL, new Float(yytext())); }

    {Comentario}                { }
    {Espacios}+                 { }
    {FinDeLinea}+               { }

    /* identifiers */ 
    {Identificador}             { return symbol("IDENTIFIER",sym.IDENTIFIER, yytext()); }

    .                           { System.out.println("Caracter no valido \"" + yytext() + "\" at line " + yyline + ", column " + yycolumn); 
                                  return symbol("error",sym.error, yytext()); }
}

<STRING> {
    "\""                        { yybegin(YYINITIAL); return symbol("STRING_LITERAL",sym.STRING_LITERAL, string.toString()); }
  
    {Cadena}+                   { string.append( yytext() ); }
  
    /* escape sequences */
    "\"\""                      { string.append( "\"\"" ); }

    /* errores */
    \\.                         { System.out.println("Caracter no valido \""+yytext()+"\""); 
                                  return symbol("error",sym.error, yytext()); }
    {FinDeLinea}                { System.out.println("Cadena no finalizada"); 
                                  return symbol("error",sym.error, yytext()); }
}
/* errores */
.                               { System.out.println("Caracter no valido \"" + yytext() + "\" at line " + yyline + ", column " + yycolumn); 
                                  return symbol("error",sym.error, yytext()); }
