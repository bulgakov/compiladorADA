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
public class ReturnStatement extends Statement {
    public Expression Expression;
    public ReturnStatement(Expression e) { Expression=e; }
}