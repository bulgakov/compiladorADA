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
public class SubProgramSpecification {
    public String SubProgram;
    public Identifier Identifier;
    public ParametersDefs ParametersDefs;
    public Type ReturnType;
    public SubProgramSpecification(String s, Identifier i, ParametersDefs p, Type r){
        SubProgram=s;Identifier=i;ParametersDefs=p;ReturnType=r;
    }
}