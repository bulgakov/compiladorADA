/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import javax.xml.bind.annotation.XmlSeeAlso;

/**
 *
 * @author mijail
 */
@XmlSeeAlso({False.class,True.class,Null.class,LogicalExpression.class,RelationExpression.class,AritmeticExpression.class,NotExpression.class,UnaryExpression.class,CallExpression.class,StringLiteral.class,FloatLiteral.class,IntegerLiteral.class,IdentifierExpression.class})
public abstract class Expression {}