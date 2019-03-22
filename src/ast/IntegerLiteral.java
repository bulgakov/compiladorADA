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
 * @author mijail
 */
@XmlRootElement
public class IntegerLiteral extends Expression {
    @XmlElement
    public int i;
    
    private IntegerLiteral() {  // makes JAXB happy, will never be invoked
        this(0);   // ...therefore it doesn't matter what it creates
    }
    
    public IntegerLiteral(int i){ this.i = i;}
}