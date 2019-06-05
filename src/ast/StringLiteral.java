/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import visitors.CGVisitor;
import visitors.TypeVisitor;
import visitors.Visitor;

/**
 *
 * @author mijai
 */
@XmlRootElement
public class StringLiteral extends Expression {
    @XmlElement
    public String s;
    
    private StringLiteral() {  // makes JAXB happy, will never be invoked
        this(null, 0, 0);   // ...therefore it doesn't matter what it creates
    }
    
    public StringLiteral(String s, int left, int right) { 
        super(left, right);
        this.s = s; 
    }
    
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public void accept(TypeVisitor v) {
        v.visit(this);
    }

    @Override
    public void accept(CGVisitor v) {
        v.visit(this);
    }
}
