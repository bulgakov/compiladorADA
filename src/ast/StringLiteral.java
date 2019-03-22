/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mijai
 */
@XmlRootElement
public class StringLiteral extends Expression {
    @XmlElement
    public String s;
    
    private StringLiteral() {  // makes JAXB happy, will never be invoked
        this(null);   // ...therefore it doesn't matter what it creates
    }
    
    public StringLiteral(String s) { this.s = s; }
}
