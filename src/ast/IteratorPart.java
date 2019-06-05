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
public class IteratorPart extends ASTNode {
    @XmlElement
    public String Iterator;
    @XmlElement
    public Identifier Identifier;
    @XmlElement
    public Expression Exp1;
    @XmlElement
    public Expression Exp2;
    
    private IteratorPart() {  // makes JAXB happy, will never be invoked
        this(null, null, null, null, 0, 0);   // ...therefore it doesn't matter what it creates
    }
    
    public IteratorPart(String i, Identifier id, Expression e1, Expression e2, int left, int right) {
        super(left, right);
        Iterator=i; 
        Identifier=id; 
        Exp1=e1; 
        Exp2=e2;
    }
}