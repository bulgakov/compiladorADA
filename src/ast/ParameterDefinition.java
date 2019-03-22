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
public class ParameterDefinition {
    @XmlElement
    public ParameterNameList ParameterNameList;
    @XmlElement
    public Mode Mode;
    @XmlElement
    public Type Type;
    @XmlElement
    public Expression Expression;
    
    private ParameterDefinition() {  // makes JAXB happy, will never be invoked
        this(null, null, null, null);   // ...therefore it doesn't matter what it creates
    }
    
    public ParameterDefinition(ParameterNameList l, Mode m, Type t, Expression e) { 
        ParameterNameList=l; Mode=m; Type=t; Expression=e;
    }
}