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
public class ParameterDefinition extends ASTNode {
    @XmlElement
    public ParameterNameList ParameterNameList;
    @XmlElement
    public Mode Mode;
    @XmlElement
    public Type Type;
    @XmlElement
    public Expression Expression;
    
    private ParameterDefinition() {  // makes JAXB happy, will never be invoked
        this(null, null, null, null,0,0);   // ...therefore it doesn't matter what it creates
    }
    
    public ParameterDefinition(ParameterNameList l, Mode m, Type t, Expression e, int left, int right) { 
        super(left, right);
        this.ParameterNameList=l; 
        this.Mode=m; 
        this.Type=t; 
        this.Expression=e;
        this.type = t.type;
        for(Identifier id : ParameterNameList.Identifiers)
            id.type = t.type;
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