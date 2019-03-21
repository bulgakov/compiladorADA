/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

/**
 *
 * @author mijail
 */
public class NotExpression extends Expression {
    public Expression Exp;
    public NotExpression(Expression e) { Exp=e; }
}