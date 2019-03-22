/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mijail
 */
@XmlRootElement
public class SubProgramSpecification {
    @XmlElement
    public String SubProgram;
    @XmlElement
    public Identifier Identifier;
    @XmlElement
    public ParametersDefs ParametersDefs;
    @XmlElement
    public Type ReturnType;
    
    private SubProgramSpecification() {  // makes JAXB happy, will never be invoked
        this(null, null, null, null);   // ...therefore it doesn't matter what it creates
    }
    
    public SubProgramSpecification(String s, Identifier i, ParametersDefs p, Type r){
        SubProgram=s;Identifier=i;ParametersDefs=p;ReturnType=r;
    }
}