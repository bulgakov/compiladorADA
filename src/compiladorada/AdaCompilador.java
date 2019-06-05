/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladorada;

import ast.Program;
import java.io.File;
import java.io.FileReader;
import java.io.StringWriter;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.Symbol;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import table.ErrorMsg;
import table.SymbolTable;
import visitors.ConstructTableSymbol;
import visitors.ICGVisitor;
import visitors.TypeCheckVisitor;

/**
 *
 * @author mijail
 */
public class AdaCompilador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            ComplexSymbolFactory csf = new ComplexSymbolFactory();
            AdaScanner scanner = new AdaScanner(new FileReader(args[0]), csf);
//            scanner.yybegin(0);
//            java_cup.runtime.Symbol token;
//            while((token=scanner.next_token()).sym != sym.EOF)
//                System.out.println(token.toString());
            AdaParser parser = new AdaParser(scanner,csf);
            Symbol root = parser.parse();
            
            if(root.value != null) {
                Program program = (Program)root.value;
                jaxbObjectToXML(program);
            
                ConstructTableSymbol construct = new ConstructTableSymbol(program);
                SymbolTable global = construct.Construct();

                TypeCheckVisitor typeCheck = new TypeCheckVisitor(global);
                program.accept(typeCheck);

                if (parser.error() || construct.error() || typeCheck.error()) {
                    for (ErrorMsg e : parser.getErrors())
                        System.err.println(e.toString());
                    for (ErrorMsg e : construct.getErrors())
                        System.err.println(e.toString());
                    for (ErrorMsg e : typeCheck.getErrors())
                        System.err.println(e.toString());
                    System.exit(1);
                }
                
                ICGVisitor cgVisitor = new ICGVisitor(global);
                //cgVisitor.visit(program); // Unfinished
            } else {
                for (ErrorMsg e : parser.getErrors())
                    System.err.println(e.toString());
                System.exit(1);
            }
        } catch (Exception e) {
            System.err.println("Unexpected compiler error: " + e.toString());
            e.printStackTrace(System.err);
            System.exit(1);
        }
    }
    
    private static String jaxbObjectToXML(ast.Program ast) {
        String xmlString = "";
        try {
            File file = new File("Program.xml");
            JAXBContext context = JAXBContext.newInstance(ast.Program.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML
            
            StringWriter sw = new StringWriter();
            m.marshal(ast, file);
            //m.marshal(ast, System.out);
            m.marshal(ast, sw);
            xmlString = sw.toString();

        } catch (JAXBException e) {
            System.err.println("Unexpected compiler error: " + e.toString());
            e.printStackTrace(System.err);
        }
        return xmlString;
    }
}
