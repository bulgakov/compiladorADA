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
public class Identifier {
    @XmlElement
    public String id;
    
    private Identifier() {  // makes JAXB happy, will never be invoked
        this(null);   // ...therefore it doesn't matter what it creates
    }
    
    public Identifier(String i) { id=i; }
}