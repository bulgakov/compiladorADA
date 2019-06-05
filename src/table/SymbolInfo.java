/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table;

import type.Type;

/**
 *
 * @author mijail
 */
public class SymbolInfo {
    public String id;
    public String belonging;
    
    public Type type;
    public int offset;
    
    public boolean read;
    public boolean write;
    
    public SymbolInfo(String id, String belonging, Type type) {
        this.id = id;
        this.belonging = belonging;
        this.type = type;
        this.offset = -1;
        this.read = true;
        this.write = true;
    }
    
    public SymbolInfo(String id, String belonging, Type type, boolean read, boolean write) {
        this.id = id;
        this.belonging = belonging;
        this.type = type;
        this.offset = -1;
        this.read = read;
        this.write = write;
    }
    
    public String getId() { 
        return id;
    }
    
    public Type getType() {
        return type;
    }
    
    @Override
    public String toString() {
        return "id= " + id + "|type " + type + 
                "|belonging= " + belonging +
                "|offset= " + offset;
    }
}
