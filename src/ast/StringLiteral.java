/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

/**
 *
 * @author mijai
 */
public class StringLiteral extends Expression {
    public String s;
    public StringLiteral(String s) { this.s = s; }
}
