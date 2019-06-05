/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import visitors.CGVisitor;
import visitors.GlobalTableVisitor;
import visitors.TypeVisitor;
import visitors.Visitor;

/**
 *
 * @author mijail
 */
@XmlRootElement
public class SubProgramBody extends DeclarativeItem {
    @XmlElement
    public SubProgramSpecification SubProgramSpecification;
    @XmlElement
    public DeclarativePart DeclarativePart;
    @XmlElement
    public Statements SequenceOfStatements;
    @XmlElement
    public Identifier OptIdentifier;
    
    private SubProgramBody() {  // makes JAXB happy, will never be invoked
        this(null,null,null,null,0,0);   // ...therefore it doesn't matter what it creates
    }
    
    public SubProgramBody(SubProgramSpecification s, DeclarativePart d, Statements st, Identifier oi, int left, int right) {
        super(left,right);
        SubProgramSpecification=s; 
        DeclarativePart=d; 
        SequenceOfStatements=st;
        OptIdentifier=oi;
    }
    
    @Override
    public void accept(GlobalTableVisitor v) { 
        v.visit(this);
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