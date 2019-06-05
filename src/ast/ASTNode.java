/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import type.Type;

/**
 *
 * @author mijail
 */
public abstract class ASTNode {
    protected int left;
    protected int right;
    
    protected int depth;
    
    public Type type;
    
    public ASTNode(int left, int right) {
        this.left = left;
        this.right = right;
        depth = 0;
    }
    
    public int getLeft(){
        return left;
    }
    
    public int getRight(){
        return right;
    }
    
    public int getDepth(){
        return depth;
    } 
}
