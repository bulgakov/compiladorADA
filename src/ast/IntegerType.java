/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import javax.xml.bind.annotation.XmlRootElement;
import type.BaseType;
import visitors.Visitor;

/**
 *
 * @author mijail
 */
@XmlRootElement
public class IntegerType extends Type { 
    
    private IntegerType() { 
        this(0, 0);
    }
    
    public IntegerType(int left, int right) { 
        super(left, right);
        type = BaseType.INTEGER;
        super.type = type;
    } 
    
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}