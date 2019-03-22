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
public class AssignStatement extends Statement {
    @XmlElement
    public Identifier Identifier;
    @XmlElement
    public Expression Expression;
    
    private AssignStatement() {  // makes JAXB happy, will never be invoked
        this(null, null);   // ...therefore it doesn't matter what it creates
    }
    
    public AssignStatement(Identifier i, Expression e){ Identifier=i; Expression=e; }
}