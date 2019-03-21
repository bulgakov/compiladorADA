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
public class AssignStatement extends Statement {
    public Identifier Identifier;
    public Expression Expression;
    public AssignStatement(Identifier i, Expression e){ Identifier=i; Expression=e; }
}