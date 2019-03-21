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
public class AritmeticExpression extends Expression {
    public String Operator;
    public Expression Exp1;
    public Expression Exp2;
    public AritmeticExpression(String op, Expression e1, Expression e2){ 
        Operator=op; Exp1=e1; Exp2=e2;
    }
}