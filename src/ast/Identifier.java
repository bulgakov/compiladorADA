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
public class Identifier extends ASTNode {
    @XmlElement
    public String id;
    
    private Identifier() {  // makes JAXB happy, will never be invoked
        this(null,0,0);   // ...therefore it doesn't matter what it creates
    }
    
    public Identifier(String i, int left, int right) 
    {
        super(left,right);
        id=i; 
    }
    
    @Override
    public String toString() {
        return id;
    }
}