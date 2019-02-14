/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladorada;

/**
 *
 * @author mijai
 * 
 * OJO: CUP genera la interface con los simbolos.
 *      Sin gramatica generarlo a mano para que este listo a integrar con el CUPs
 */

public interface sym {
    /* terminals */
    public static final int EOF = 0;
    public static final int error = 1;
    public static int ABORT = 2;
    public static int ABS = 3;
    public static int ABSTRACT = 4;
    public static int ACCEPT = 5;
    public static int ACCESS = 6;
    public static int ALIASED = 7;
    public static int ALL = 8;
    public static int AND = 9;
    public static int ARRAY = 10;
    public static int AT = 11;
    public static int BEGIN = 12;
    public static int BODY = 13;
    public static int CASE = 14;
    public static int CONSTANT = 15;
    public static int DECLARE = 16;
    public static int DELAY = 17;
    public static int DELTA = 18;
    public static int DIGITS = 19;
    public static int DO = 20;
    public static int ELSE = 21;
    public static int ELSIF = 22;
    public static int END = 23;
    public static int ENTRY = 24;
    public static int EXCEPTION = 25;
    public static int EXIT = 26;
    public static int FOR = 27;
    public static int FUNCTION = 28;
    public static int GENERIC = 29;
    public static int GOTO = 30;
    public static int IF = 31;
    public static int IN = 32;
    public static int IS = 33;
    public static int LIMITED = 34;
    public static int LOOP = 35;
    public static int MOD = 36;
    public static int NEW = 37;
    public static int NOT = 38;
    public static int NULL = 39;
    public static int OF = 40;
    public static int OR = 41;
    public static int OTHERS = 42;
    public static int OUT = 43;
    public static int PACKAGE = 44;
    public static int PRAGMA = 45;
    public static int PRIVATE = 46;
    public static int PROCEDURE = 47;
    public static int PROTECTED = 48;
    public static int RAISE = 49;
    public static int RANGE = 50;
    public static int RECORD = 51;
    public static int REM = 52;
    public static int RENAMES = 53;
    public static int REQUEUE = 54;
    public static int RETURN = 55;
    public static int REVERSE = 56;
    public static int SELECT = 57;
    public static int SEPARATE = 58;
    public static int SUBTYPE = 59;
    public static int TAGGED = 60;
    public static int TASK = 61;
    public static int TERMINATE = 62;
    public static int THEN = 63;
    public static int TYPE = 64;
    public static int UNTIL = 65;
    public static int USE = 66;
    public static int WHEN = 67;
    public static int WHILE = 68;
    public static int WITH = 69;
    public static int XOR = 70;


    public static final String[] terminalNames = new String[] {
    "EOF",
    "error",
    "ABORT",
    "ABS",
    "ABSTRACT",
    "ACCEPT",
    "ACCESS",
    "ALIASED",
    "ALL",
    "AND",
    "ARRAY",
    "AT",
    "BEGIN",
    "BODY",
    "CASE",
    "CONSTANT",
    "DECLARE",
    "DELAY",
    "DELTA",
    "DIGITS",
    "DO",
    "ELSE",
    "ELSIF",
    "END",
    "ENTRY",
    "EXCEPTION",
    "EXIT",
    "FOR",
    "FUNCTION",
    "GENERIC",
    "GOTO",
    "IF",
    "IN",
    "IS",
    "LIMITED",
    "LOOP",
    "MOD",
    "NEW",
    "NOT",
    "NULL",
    "OF",
    "OR",
    "OTHERS",
    "OUT",
    "PACKAGE",
    "PRAGMA",
    "PRIVATE",
    "PROCEDURE",
    "PROTECTED",
    "RAISE",
    "RANGE",
    "RECORD",
    "REM",
    "RENAMES",
    "REQUEUE",
    "RETURN",
    "REVERSE",
    "SELECT",
    "SEPARATE",
    "SUBTYPE",
    "TAGGED",
    "TASK",
    "TERMINATE",
    "THEN",
    "TYPE",
    "UNTIL",
    "USE",
    "WHEN",
    "WHILE",
    "WITH",
    "XOR"
    };
}
