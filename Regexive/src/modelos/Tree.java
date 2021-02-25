/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.type.Types;

/**
 *
 * @author Jeffry
 */
public class Tree {
    
    node root;
    String nombreExp;

    public Tree( String er, ArrayList<node> leaves, ArrayList<ArrayList> table, String nombreExp ) {
        this.nombreExp = nombreExp;
        
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
    
    public void crearGrafoArbol() {
        String dotArbol = "digraph G {";
        dotArbol += graficarParte(this.root);
        dotArbol += "\n}";
        
        try {
            String pathGrafo = System.getProperty("user.dir") + "/archivos/ARBOLES_201901557/";
            String nombreGrafo = nombreExp;
            
            // Guardar .dot           
            File crearDOT = new File(pathGrafo + nombreGrafo + ".dot");      
            FileWriter writer;
            writer = new FileWriter(pathGrafo + nombreGrafo + ".dot");
            writer.write(dotArbol);
            writer.close();
            
            // Crear png
            String[] cmd = {"dot", "-Tpng", pathGrafo + nombreGrafo + ".dot", "-o", pathGrafo + nombreGrafo + ".png"};
            Runtime rt = Runtime.getRuntime();
            rt.exec(cmd);   
        } catch (IOException ex) {
            Logger.getLogger(transitionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String graficarParte(node leave) {
        String dotArbol = "";
        
        if (leave.left != null) {
            dotArbol += graficarParte((node)leave.left);
        }
        
        if (leave.right != null) {
            dotArbol += graficarParte((node)leave.right);
        }
        
        // Caso hoja
        switch(leave.type) {
            case HOJA:
                // Declarar hoja
                // id_hojanodo [shape=record label="<IZQ>|{Valor: .|Anulable:false\nPrimeros=1\nUltimos=5 }|<DER>"];
                if (leave.lexeme.equals("#")) {
                    dotArbol += "\n" + leave.hashCode() + " [shape=record label=\"<IZQ>|{" + leave.lexeme + "|Anulable:" + leave.anullable + "|Primeros=" + leave.first + "|Ultimos=" + leave.last + "}|<DER>\"];";
                } else {
                    dotArbol += "\n" + leave.hashCode() + " [shape=record label=\"<IZQ>|{" + leave.lexeme.substring(1, leave.lexeme.length()-1) + "|Anulable:" + leave.anullable + "|Primeros=" + leave.first + "|Ultimos=" + leave.last + "}|<DER>\"];";
                }
                break;
            case OR:
                // Declarar nodo
                dotArbol += "\n" + leave.hashCode() + " [shape=record label=\"<IZQ>|{OR" + "|Anulable:" + leave.anullable + "|Primeros=" + leave.first + "|Ultimos=" + leave.last + "}|<DER>\"];";
                // Uniones
                dotArbol += "\n" + leave.hashCode() + " -> " + leave.left.hashCode();
                dotArbol += "\n" + leave.hashCode() + " -> " + leave.right.hashCode();
                break;
            case AND:
                // Declarar nodo
                dotArbol += "\n" + leave.hashCode() + " [shape=record label=\"<IZQ>|{." + "|Anulable:" + leave.anullable + "|Primeros=" + leave.first + "|Ultimos=" + leave.last + "}|<DER>\"];";
                // Uniones
                dotArbol += "\n" + leave.hashCode() + " -> " + leave.left.hashCode();
                dotArbol += "\n" + leave.hashCode() + " -> " + leave.right.hashCode();
                break;
            case KLEENE:
                // Declarar nodo
                dotArbol += "\n" + leave.hashCode() + " [shape=record label=\"<IZQ>|{*" + "|Anulable:" + leave.anullable + "|Primeros=" + leave.first + "|Ultimos=" + leave.last + "}|<DER>\"];";
                // Uniones
                dotArbol += "\n" + leave.hashCode() + " -> " + leave.left.hashCode();
                break;
            case MAS:
                // Declarar nodo
                dotArbol += "\n" + leave.hashCode() + " [shape=record label=\"<IZQ>|{+" + "|Anulable:" + leave.anullable + "|Primeros=" + leave.first + "|Ultimos=" + leave.last + "}|<DER>\"];";
                // Uniones
                dotArbol += "\n" + leave.hashCode() + " -> " + leave.left.hashCode();
                break;
            case INTERROGACION:
                // Declarar nodo
                dotArbol += "\n" + leave.hashCode() + " [shape=record label=\"<IZQ>|{?" + "|Anulable:" + leave.anullable + "|Primeros=" + leave.first + "|Ultimos=" + leave.last + "}|<DER>\"];";
                // Uniones
                dotArbol += "\n" + leave.hashCode() + " -> " + leave.left.hashCode();
                break;
        }        
        return dotArbol;
    }
    
}
