/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import javax.xml.bind.annotation.XmlSeeAlso;
import visitors.CGVisitor;
import visitors.TypeVisitor;
import visitors.Visitor;

/**
 *
 * @author mijail
 */
@XmlSeeAlso({
    False.class, 
    True.class, 
    LogicalExpression.class, 
    RelationExpression.class, 
    AritmeticExpression.class, 
    NotExpression.class, 
    UnaryExpression.class, 
    CallExpression.class, 
    StringLiteral.class, 
    FloatLiteral.class, 
    IntegerLiteral.class, 
    IdentifierExpression.class})
public abstract class Expression extends ASTNode {
    
    private Expression() {
        this(0,0);
    }
    
    public Expression (int left, int right) {
        super(left, right);
    } 
    
    public abstract void accept(Visitor v);
    public abstract void accept(TypeVisitor v);
    public abstract void accept(CGVisitor v);
}