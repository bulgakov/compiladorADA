/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladorada;

import java.io.File;
import java.io.FileReader;
import java.io.StringWriter;
import java_cup.runtime.ComplexSymbolFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

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
            parser.parse();
            
            if(parser.AST != null)
                jaxbObjectToXML(parser.AST);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.exit(1);
        }
    }
    
    private static String jaxbObjectToXML(ast.Program ast) {
        String xmlString = "";
        try {
            File file = new File("Program.xml");
            if (file.exists()) file.delete();
            JAXBContext context = JAXBContext.newInstance(ast.Program.class);
            Marshaller m = context.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML

            StringWriter sw = new StringWriter();
            m.marshal(ast, file);
            m.marshal(ast, System.out);
            m.marshal(ast, sw);
            xmlString = sw.toString();

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xmlString;
    }
}
