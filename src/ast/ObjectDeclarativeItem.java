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
public class ObjectDeclarativeItem extends DeclarativeItem {
    @XmlElement
    public IdentifierList IdentifierList;
    @XmlElement
    public Type Type;
    @XmlElement
    public Expression Expression;
    
    private ObjectDeclarativeItem() {  // makes JAXB happy, will never be invoked
        this(null, null, null);   // ...therefore it doesn't matter what it creates
    }
    
    public ObjectDeclarativeItem(IdentifierList i, Type t, Expression e){
        IdentifierList=i;Type=t;Expression=e;
    }
}