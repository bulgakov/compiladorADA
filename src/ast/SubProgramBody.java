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
public class SubProgramBody extends DeclarativeItem {
    @XmlElement
    public SubProgramSpecification SubProgramSpecification;
    @XmlElement
    public DeclarativePart DeclarativePart;
    @XmlElement
    public SequenceOfStatements SequenceOfStatements;
    
    private SubProgramBody() {  // makes JAXB happy, will never be invoked
        this(null, null, null);   // ...therefore it doesn't matter what it creates
    }
    
    public SubProgramBody(SubProgramSpecification s, DeclarativePart d, SequenceOfStatements st) {
        SubProgramSpecification=s; DeclarativePart=d; SequenceOfStatements=st;
    }
}