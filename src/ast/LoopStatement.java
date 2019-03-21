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
public class LoopStatement extends Statement {
    public IteratorPart Iterator;
    public SequenceOfStatements SequenceOfStatements;
    public LoopStatement(IteratorPart i, SequenceOfStatements s) { Iterator=i; SequenceOfStatements=s; }
}