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
public class OutMode extends Mode { 
    
    private OutMode() {
        this(0,0);
    }
    
    public OutMode(int left, int right){
        super(left, right);
    } 
}