/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import javax.xml.bind.annotation.XmlSeeAlso;
import visitors.Visitor;

/**
 *
 * @author mijail
 */
@XmlSeeAlso({
    BooleanType.class,
    IntegerType.class,
    FloatType.class
})
public abstract class Type extends ASTNode {
    
    private Type() {
        this(0,0);
    }
    
    public Type(int left, int right){
        super(left, right);
    }
    
    public abstract void accept(Visitor v);
}