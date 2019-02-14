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
Comentario      = "--" {Caracter}* {FinDeLinea}?

/* identificador */
Identificador   = [a-zA-Z][a-zA-Z0-9_]*

/* Literales */
IntegerLiteral  = 0 | [1-9][0-9]*  
Cadena          = [^\r\n\"\\]
Caracter        = [^\r\n\'\\]

/*Operadores*/
OPREL           = ">" | "<" | "=" | ">=" | "<=" | "/="
OPSUMA          = "+" | "-"
OPMULT          = "*" | "/" 

OPLOG           = "or" | "and" | "xor"

%state STRING

%%

<YYINITIAL> {
    /* palabras reservadas */
    "abort"                     { return symbol("ABORT",sym.ABORT); }
    "abs"                       { return symbol("ABS",sym.ABS); }
    "abstract"                  { return symbol("ABSTRACT",sym.ABSTRACT); }
    "accept"                    { return symbol("ACCEPT",sym.ACCEPT); }
    "access"                    { return symbol("ACCESS",sym.ACCESS); }
    "aliased"                   { return symbol("ALIASED",sym.ALIASED); }
    "all"                       { return symbol("ALL",sym.ALL); }
    "array"                     { return symbol("ARRAY",sym.ARRAY); }
    "at"                        { return symbol("AT",sym.AT); }

    "begin"                     { return symbol("BEGIN",sym.BEGIN); }
    "body"                      { return symbol("BODY",sym.BODY); }

    "case"                      { return symbol("CASE",sym.CASE); }
    "constant"                  { return symbol("CONSTANT",sym.CONSTANT); }

    "declare"                   { return symbol("DECLARE",sym.DECLARE); }
    "delay"                     { return symbol("DELAY",sym.DELAY); }
    "delta"                     { return symbol("DELTA",sym.DELTA); }
    "digits"                    { return symbol("DIGITS",sym.DIGITS); }
    "do"                        { return symbol("DO",sym.DO); }

    "else"                      { return symbol("ELSE",sym.ELSE); }
    "elsif"                     { return symbol("ELSIF",sym.ELSIF); }
    "end"                       { return symbol("END",sym.END); }
    "entry"                     { return symbol("ENTRY",sym.ENTRY); }
    "exit"                      { return symbol("EXIT",sym.EXIT); }

    "for"                       { return symbol("FOR",sym.FOR); }
    "function"                  { return symbol("FUNCTION",sym.FUNCTION); }

    "generic"                   { return symbol("GENERIC",sym.GENERIC); }
    "goto"                      { return symbol("GOTO",sym.GOTO); }

    "if"                        { return symbol("IF",sym.IF); }
    "in"                        { return symbol("IN",sym.IN); }
    "is"                        { return symbol("IS",sym.IS); }

    "limited"                   { return symbol("LIMITED",sym.LIMITED); }
    "loop"                      { return symbol("LOOP",sym.LOOP); }

    "mod"                       { return symbol("MOD",sym.MOD); }
    "new"                       { return symbol("NEW",sym.NEW); }
    "not"                       { return symbol("NOT",sym.NOT); }
    "null"                      { return symbol("NULL",sym.NULL); }

    "of"                        { return symbol("OF",sym.OF); }
    "others"                    { return symbol("OTHERS",sym.OTHERS); }
    "out"                       { return symbol("OUT",sym.OUT); }

    "package"                   { return symbol("PACKAGE",sym.PACKAGE); }
    "pragma"                    { return symbol("PRAGMA",sym.PRAGMA); }
    "private"                   { return symbol("PRIVATE",sym.PRIVATE); }
    "procedure"                 { return symbol("PROCEDURE",sym.PROCEDURE); }
    "protected"                 { return symbol("PROTECTED",sym.PROTECTED); }

    "raise"                     { return symbol("RAISE",sym.RAISE); }
    "range"                     { return symbol("RANGE",sym.RANGE); }
    "record"                    { return symbol("RECORD",sym.RECORD); }
    "rem"                       { return symbol("REM",sym.REM); }
    "renames"                   { return symbol("RENAMES",sym.RENAMES); }
    "requeue"                   { return symbol("REQUEUE",sym.REQUEUE); }
    "return"                    { return symbol("RETURN",sym.RETURN); }
    "reverse"                   { return symbol("REVERSE",sym.REVERSE); }

    "select"                    { return symbol("SELECT",sym.SELECT); }
    "separate"                  { return symbol("SEPARATE",sym.SEPARATE); }
    "subtype"                   { return symbol("SUBTYPE",sym.SUBTYPE); }

    "tagged"                    { return symbol("TAGGED",sym.TAGGED); }
    "task"                      { return symbol("TASK",sym.TASK); }
    "terminate"                 { return symbol("TERMINATE",sym.TERMINATE); }
    "then"                      { return symbol("THEN",sym.THEN); }
    "type"                      { return symbol("TYPE",sym.TYPE); }

    "until"                     { return symbol("UNTIL",sym.UNTIL); }
    "use"                       { return symbol("USE",sym.USE); }
    "when"                      { return symbol("WHEN",sym.WHEN); }
    "while"                     { return symbol("WHILE",sym.WHILE); }
    "with"                      { return symbol("WITH",sym.WITH); }
    
    /* separadores */
    "("                         { return symbol("LPAREN",sym.LPAREN); }
    ")"                         { return symbol("RPAREN",sym.RPAREN); }
    ","                         { return symbol("COMMA",sym.COMMA); }
    ";"                         { return symbol("PCOMMA",sym.PCOMMA); }
    //"."                       { return symbol("DOT",sym.DOT); }

    /* operadores */
    "+"                         { return symbol("PLUS",sym.PLUS); }
    "-"                         { return symbol("MINUS",sym.MINUS); }
    "*"                         { return symbol("MULT",sym.MULT); }
    "/"                         { return symbol("DIV",sym.DIV); }
    "="                         { return symbol("EQ",sym.EQ); }
    "/="                        { return symbol("NOTEQ",sym.NOTEQ); }
    ">"                         { return symbol("GT",sym.GT); }
    "<"                         { return symbol("LT",sym.LT); }
    "<="                        { return symbol("LTEQ",sym.LTEQ); }
    ">="                        { return symbol("GTEQ",sym.GTEQ); }

    "and"                       { return symbol("AND",sym.AND); }
    "or"                        { return symbol("OR",sym.OR); }
    "xor"                       { return symbol("XOR",sym.XOR); }

    
    /* cadena literal */
    "\""                        { yybegin(STRING); string.setLength(0); }

    {IntegerLiteral}            { return symbol("INTEGERLITERAL",sym.INTEGERLITERAL, new Integer(yytext())); }

    {Comentario}                { }
    {Espacios}                  { }
    {FinDeLinea}{Espacios}*{FinDeLinea}*  { return symbol("EOL",sym.EOL); }

    /* identifiers */ 
    {Identificador}             { return symbol("IDENTIFIER",sym.IDENTIFIER, yytext()); }

    "&"                         { return symbol("AMP",sym.AMP); }
    "_"                         { return symbol("UNDERSCORE",sym.UNDERSCORE); }
    .                           { System.out.println("Caracter no valido \"" + yytext() + "\" at line " + yyline + ", column " + yycolumn); 
                                  return symbol("error",sym.error, yytext()); }
}

<STRING> {
    "\""                        { yybegin(YYINITIAL); if (string.length() == 1) return symbol("CHARLITERAL",sym.CHARLITERAL, string.toString()); else return symbol("STRINGLITERAL",sym.STRINGLITERAL, string.toString()); }
  
    {Cadena}+                   { string.append( yytext() ); }
  
    /* escape sequences */
    "\\b"                       { string.append( '\b' ); }
    "\\t"                       { string.append( '\t' ); }
    "\\n"                       { string.append( '\n' ); }
    "\\f"                       { string.append( '\f' ); }
    "\\r"                       { string.append( '\r' ); }
    "\\\""                      { string.append( '\"' ); }
    "\\'"                       { string.append( '\'' ); }
    "\\\\"                      { string.append( '\\' ); }

    /* errores */
    \\.                         { System.out.println("Caracter no valido \""+yytext()+"\""); 
                                  return symbol("error",sym.error, yytext()); }
    {FinDeLinea}                { System.out.println("Cadena no finalizada"); 
                                  return symbol("error",sym.error, yytext()); }
}
/* errores */
.                               { System.out.println("Caracter no valido \"" + yytext() + "\" at line " + yyline + ", column " + yycolumn); 
                                  return symbol("error",sym.error, yytext()); }
