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
public class IfStatement extends Statement {
    @XmlElement
    public Expression Expression;
    @XmlElement
    public SequenceOfStatements SequenceOfStatements;
    @XmlElement
    public ElsifStatements ElsifStatements;
    @XmlElement
    public ElseStatement ElseStatement;
    
    private IfStatement() {  // makes JAXB happy, will never be invoked
        this(null, null, null, null);   // ...therefore it doesn't matter what it creates
    }
    
    public IfStatement(Expression e, SequenceOfStatements s, ElsifStatements elifs, ElseStatement els) {
        Expression=e; SequenceOfStatements=s; ElsifStatements=elifs; ElseStatement=els;
    }
}