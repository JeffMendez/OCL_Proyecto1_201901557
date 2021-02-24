/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import modelos.type.Types;

/**
 *
 * @author Jeffry
 */
public class Tree {
    
    node root;

    public Tree( String er, ArrayList<node> leaves, ArrayList<ArrayList> table ) {
        numLeave numHoja = new numLeave(er);
        Stack pila = new Stack();
          
        String[] erSplit = er.split("");  
        ArrayList<String> stringExpr = new ArrayList<>();
        
        for (int i=0; i<erSplit.length; i++) {
            switch (erSplit[i]){
                case "*": case ".": case "+": case "?": case "|": case "#":
                    stringExpr.add(erSplit[i]);
                    break;
                default:
                    // Conjunto
                    if(erSplit[i].equals("{")) {
                        String nombreConj = "{";
                        for (int j=i+1; i<erSplit.length; j++) { 
                            if (erSplit[j].equals("}")) {
                                i = j;
                                nombreConj += "}";
                                break;
                            } else {
                                nombreConj += erSplit[j];
                            }  
                        }
                        stringExpr.add(nombreConj);
                    }
                    // Posible cadena
                    if(erSplit[i].equals("\"")) {
                        String texto = "\"";
                        for (int j=i+1; i<erSplit.length; j++) { 
                            if (erSplit[j].equals("\"")) {
                                i = j;
                                texto += "\"";
                                break;
                            } else {
                                texto += erSplit[j];
                            }  
                        }
                        stringExpr.add(texto);
                    }
                    break;    
            }
        }   

        Collections.reverse(stringExpr);
        
        stringExpr.forEach((character) -> {
            switch (character) {
                case "|":
                    node lefto = (node) pila.pop();
                    node righto = (node) pila.pop();
                    
                    node no = new node(character, Types.OR, 0, lefto, righto, leaves, table);
                    pila.push(no);
                    
                    break;
                case ".":
                    node lefta = (node) pila.pop();
                    node righta = (node) pila.pop();
                    
                    node na = new node(character, Types.AND, 0, lefta, righta, leaves, table);
                    pila.push(na);
                    
                    break;
                case "*":
                    node unario = (node) pila.pop();
                    
                    node nk = new node(character, Types.KLEENE, 0, unario, null, leaves, table);
                    pila.push(nk);
                    
                    break;
                case "+":
                    node plus = (node) pila.pop();
                    
                    node nplus = new node(character, Types.MAS, 0, plus, null, leaves, table);
                    pila.push(nplus);
                    
                    break;
                case "?":
                    node interr = (node) pila.pop();
                    
                    node ninterr = new node(character, Types.INTERROGACION, 0, interr, null, leaves, table);
                    pila.push(ninterr);
                    
                    break;
                default:
                    node nd = new node(character, Types.HOJA, numHoja.getNum(), null, null, leaves, table);
                    pila.push(nd);
                    leave hoja = new leave();
                    hoja.addLeave(nd, leaves);
                    break;
            }
        });
        this.root = (node) pila.pop();
    }
    
    public node getRoot(){
        return this.root;
    }
    
}
