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
public class ParameterDefinition {
    public ParameterNameList ParameterNameList;
    public Mode Mode;
    public Type Type;
    public Expression Expression;
    public ParameterDefinition(ParameterNameList l, Mode m, Type t, Expression e) { 
        ParameterNameList=l; Mode=m; Type=t; Expression=e;
    }
}