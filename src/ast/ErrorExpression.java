/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import visitors.CGVisitor;
import visitors.TypeVisitor;
import visitors.Visitor;

/**
 *
 * @author mijai
 */
public class ErrorExpression extends Expression {

    private ErrorExpression() {  // makes JAXB happy, will never be invoked
        this(0, 0);   // ...therefore it doesn't matter what it creates
    }
    
    public ErrorExpression(int left, int right) {
        super(left, right);
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
