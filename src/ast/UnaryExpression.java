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
public class UnaryExpression extends Expression {
    @XmlElement
    public String Operator;
    @XmlElement
    public Expression Exp1;
    
    private UnaryExpression() {  // makes JAXB happy, will never be invoked
        this(null, null);   // ...therefore it doesn't matter what it creates
    }
    
    public UnaryExpression(String op, Expression e1){ 
        Operator=op; Exp1=e1;
    }
}