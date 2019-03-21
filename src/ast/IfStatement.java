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
public class IfStatement extends Statement {
    public Expression Expression;
    public SequenceOfStatements SequenceOfStatements;
    public ElsifStatements ElsifStatements;
    public ElseStatement ElseStatement;
    public IfStatement(Expression e, SequenceOfStatements s, ElsifStatements elifs, ElseStatement els) {
        Expression=e; SequenceOfStatements=s; ElsifStatements=elifs; ElseStatement=els;
    }
}