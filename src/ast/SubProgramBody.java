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
public class SubProgramBody extends DeclarativeItem {
    public SubProgramSpecification SubProgramSpecification;
    public DeclarativePart DeclarativePart;
    public SequenceOfStatements SequenceOfStatements;
    public SubProgramBody(SubProgramSpecification s, DeclarativePart d, SequenceOfStatements st) {
        SubProgramSpecification=s; DeclarativePart=d; SequenceOfStatements=st;
    }
}