/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

/**
 *
 * @author mijail
 */
@XmlRootElement
public class ElsifStatements {
    @XmlElement
    public List<ElsifStatement> ElsifStatements;
    
    public ElsifStatements() { 
        ElsifStatements = new ArrayList<ElsifStatement>(); 
    }
}