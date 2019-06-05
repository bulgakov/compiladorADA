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
public class Program extends ASTNode {
    @XmlElement
    public SubProgramBody SubProgramBody;
    
    private Program() {  // makes JAXB happy, will never be invoked
        this(null,0,0);
    }
    
    public Program(SubProgramBody s, int left, int right) { 
        super(left, right);
        SubProgramBody = s; 
    }
    
    public void accept(GlobalTableVisitor v) { 
        v.visit(this);
    }
    
    public void accept(Visitor v) {
        v.visit(this);
    }

    public void accept(TypeVisitor v) {
        v.visit(this);
    }

    public void accept(CGVisitor v) {
        v.visit(this);
    }
}