/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package type;

/**
 *
 * @author mijail
 */
public abstract class Type {
    public abstract boolean same(Type e);
    public abstract boolean assignable(Type e);
    @Override
    public abstract String toString();
}
