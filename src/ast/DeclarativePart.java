/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mijail
 */
@XmlRootElement
public class DeclarativePart {
    @XmlElement
    public List<DeclarativeItem> Items;
    
    public DeclarativePart(){ Items = new ArrayList<>(); }
}
