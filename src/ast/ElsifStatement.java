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
public class ElsifStatement {
    public Expression Expression;
    public SequenceOfStatements SequenceOfStatements;
    public ElsifStatement(Expression e, SequenceOfStatements s){ Expression=e; SequenceOfStatements=s; }
}