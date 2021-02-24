/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.ArrayList;

/**
 *
 * @author Jeffry
 */
public class leave {
    public void addLeave(node nodo, ArrayList<node> leaves){
        leaves.add(nodo);
    }
    
    public node getLeave(int numLeave, ArrayList<node> leaves){
        for (node item : leaves) {
            if(item.number == numLeave) return item;
        }
        return null;
    }
    
    public boolean isAccept(int numLeave, ArrayList<node> leaves){
        for (node item : leaves) {
            if(item.number == numLeave) return item.accept;
        }
        return false;
    }
}
