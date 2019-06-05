/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import javax.xml.bind.annotation.XmlSeeAlso;
import visitors.CGVisitor;
import visitors.GlobalTableVisitor;
import visitors.TypeVisitor;
import visitors.Visitor;

/**
 *
 * @author mijail
 */
@XmlSeeAlso({ObjectDeclarativeItem.class, SubProgramBody.class})
public abstract class DeclarativeItem extends ASTNode {

    private DeclarativeItem()
    {
        this(0,0);
    }
    
    public DeclarativeItem(int left, int right) {
        super(left, right);
    }

    public abstract void accept(GlobalTableVisitor v);
    public abstract void accept(Visitor v);
    public abstract void accept(TypeVisitor v);
    public abstract void accept(CGVisitor v);
}