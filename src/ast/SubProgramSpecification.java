/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import type.ProcType;
import type.Void;
import type.Type;
import visitors.CGVisitor;
import visitors.TypeVisitor;
import visitors.Visitor;

/**
 *
 * @author mijail
 */
@XmlRootElement
public class SubProgramSpecification extends ASTNode {
    @XmlElement
    public String SubProgram;
    @XmlElement
    public Identifier Identifier;
    @XmlElement
    public ParameterList ParameterList;
    @XmlElement
    public ast.Type ReturnType;
    
    private SubProgramSpecification() {  // makes JAXB happy, will never be invoked
        this(null,null,null,null,0,0);   // ...therefore it doesn't matter what it creates
    }
    
    public SubProgramSpecification(String s, Identifier i, ParameterList p, ast.Type r, int left, int right){
        super(left, right);
        this.SubProgram=s;
        this.Identifier=i;
        this.ParameterList=p;
        this.ReturnType=r;
        
        List<Type> params = new ArrayList<Type>();
        for(ParameterDefinition pd : p.Parameters)
            for(Identifier pid : pd.ParameterNameList.Identifiers)
                params.add(pid.type);
        this.Identifier.type = new ProcType(null, this.Identifier.toString(), this.ReturnType == null ? Void.VOID : this.ReturnType.type, params);
    }
    
    public void accept(Visitor v) {
        v.visit(this);
    }
}