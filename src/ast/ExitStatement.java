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
public class ExitStatement extends Statement {
    public Expression Expression;
    public ExitStatement(Expression e) { Expression=e; }
}