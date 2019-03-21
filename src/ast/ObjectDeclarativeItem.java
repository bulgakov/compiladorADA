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
public class ObjectDeclarativeItem extends DeclarativeItem {
    public IdentifierList IdentifierList;
    public Type Type;
    public Expression Expression;
    
    public ObjectDeclarativeItem(IdentifierList i, Type t, Expression e){
        IdentifierList=i;Type=t;Expression=e;
    }
}