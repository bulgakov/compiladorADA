/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladorada;

import java.io.FileReader;
import java_cup.runtime.ComplexSymbolFactory;

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
            AdaScanner scanner = new AdaScanner(new java.io.FileReader(args[0]), csf);
//            scanner.yybegin(0);
//            java_cup.runtime.Symbol token;
//            while((token=scanner.next_token()).sym != sym.EOF)
//                System.out.println(token.toString());
            AdaParser parser = new AdaParser(scanner,csf);
            parser.parse();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.exit(1);
        }
    }
    
}
