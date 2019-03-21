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
public class ProcedureCallStatement extends Statement {
    public Identifier Identifier;
    public ParameterPart ParameterPart;
    public ProcedureCallStatement(Identifier i, ParameterPart p) { Identifier=i; ParameterPart=p;}
}