/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import vistas.Menu;

/**
 *
 * @author Jeffry
 */
public class transitionTable {
    public ArrayList<ArrayList> states;
    public int cont;
    public String nombreExp;
    
    public transitionTable(node root, ArrayList tabla, ArrayList<node> leaves, String nombreExp) {
        this.states = new ArrayList();
        this.nombreExp = nombreExp;
        
        ArrayList datos = new ArrayList();
        datos.add("S0");
        datos.add(root.first);
        datos.add(new ArrayList());
        datos.add(false);
        
        this.states.add(datos);
        this.cont = 1;
        
        for(int i = 0; i < states.size(); i++){
            ArrayList state = states.get(i);
            ArrayList<Integer> elementos = (ArrayList) state.get(1);
            
            // TODO  Aqui se encuentra el bug
            for(int hoja : elementos){
                followTable sigTabla = new followTable();
                ArrayList lexemeNext = (ArrayList) sigTabla.next(hoja, tabla).clone();
                
                
                boolean existe = false;
                String found = "";
                
                for( ArrayList e : states ){
                    if(e.get(1).equals(lexemeNext.get(1))){
                        existe = true;
                        found = (String)e.get(0);
                        break;
                    }
                }
                
                if(!existe){
                    leave hojas = new leave();
                    if(hojas.isAccept(hoja, leaves)){
                        state.set(3, true);
                    }
                    if(lexemeNext.get(0)==""){
                        continue;
                    }
                    
                    ArrayList nuevo = new ArrayList();
                    nuevo.add("S"+cont);
                    nuevo.add(lexemeNext.get(1));
                    nuevo.add(new ArrayList());
                    nuevo.add(false);
                    
                    transicion trans = new transicion(state.get(0) + "", lexemeNext.get(0) + "", nuevo.get(0) + "");
                    ((ArrayList)state.get(2)).add(trans);
                    
                    cont += 1;
                    states.add(nuevo);
                
                }
                else{
                    leave hojas = new leave();
                    if(hojas.isAccept(hoja, leaves)){
                        state.set(3, true);
                    }
                    
                    boolean trans_exist = false;
                    
                    for(Object trans : (ArrayList)state.get(2)){
                        transicion t = (transicion) trans;
                        if(t.compare(state.get(0) + "", lexemeNext.get(0) + "")){
                            trans_exist = true;
                            break;
                        }
                    }
                    if(!trans_exist){
                        transicion trans = new transicion(state.get(0) + "", lexemeNext.get(0) + "", found + "");
                        ((ArrayList)state.get(2)).add(trans);
                    }
                }
            }
            
        }
    }
    
    public void crearGrafoTransiciones() {
        String dotTable = "digraph T {\n" +
                            "aHtmlTable [\n" +
                            "   shape=plaintext\n" +
                            "   color=\"#283747\" fontcolor=\"#154360\" label=<\n" +
                            "\n" +
                            "   <table border='1' cellborder='1'>\n" +
                            "   <tr>\n" +
                            "      <td>Estados</td>\n" +
                            "      <td>Terminales</td>\n" +
                            "   </tr>";
        
        for(ArrayList state : states){
            
            ArrayList<Integer> siguientes = (ArrayList<Integer>) state.get(1);
            boolean aceptacion = (boolean)state.get(3);
            
            // Recorrer transiciones
            for(Object tr : (ArrayList)state.get(2)){
                transicion t = (transicion) tr;
              
                String[] itemsTransicion = t.toString().split("->");
                String estadoOrigen = itemsTransicion[0].replace(" ", "");
                String terminal = itemsTransicion[1].replace(" ", "");
                String estadoDestino = itemsTransicion[2].replace(" ", "");
                
                if (aceptacion) {
                    dotTable += "\t<tr><td bgcolor=\"yellow\">" + estadoOrigen + siguientes + "</td><td>" + estadoDestino + " " + terminal + "</td></tr>\n";               
                } else {
                    dotTable += "\t<tr><td>" + estadoOrigen + siguientes + "</td><td>" + estadoDestino + " " + terminal + "</td></tr>\n";               
                }
            }
        }
        
        dotTable += "</table>\n" +
                        "\n" +
                        "   >]; \n" +
                        "\n" +
                        "}";
       
        try {
            String pathGrafo = System.getProperty("user.dir") + "/archivos/tabla_transiciones/";
            String nombreGrafo = nombreExp;
            
            // Guardar .dot           
            File crearDOT = new File(pathGrafo + nombreGrafo + ".dot");      
            FileWriter writer;
            writer = new FileWriter(pathGrafo + nombreGrafo + ".dot");
            writer.write(dotTable);
            writer.close();
            
            // Crear png
            String[] cmd = {"dot", "-Tpng", pathGrafo + nombreGrafo + ".dot", "-o", pathGrafo + nombreGrafo + ".png"};
            Runtime rt = Runtime.getRuntime();
            rt.exec(cmd);
            
        } catch (IOException ex) {
            Logger.getLogger(transitionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void crearGrafoAutomata() {
        String dotAFD = "digraph G {\n";
                
        for(ArrayList state : states){
            
            ArrayList<Integer> siguientes = (ArrayList<Integer>) state.get(1);
            boolean aceptacion = (boolean)state.get(3);
            
            // Recorrer transiciones
            for(Object tr : (ArrayList)state.get(2)){
                transicion t = (transicion) tr;
              
                String[] itemsTransicion = t.toString().split("->");
                String estadoOrigen = itemsTransicion[0].replace(" ", "");
                String terminal = itemsTransicion[1].replace(" ", "");
                String estadoDestino = itemsTransicion[2].replace(" ", "");
                
                if (terminal.charAt(0) == '{') {
                    dotAFD += "\t" + estadoOrigen + " -> " + estadoDestino + " [label=\"" + terminal + "\"]";
                } else {
                    dotAFD += "\t" + estadoOrigen + " -> " + estadoDestino + " [label=\"" + terminal.substring(1, terminal.length() -1) + "\"]";
                } 
            }
        }
        
        dotAFD += "\n" +
                    "}";
        
        try {
            String pathGrafo = System.getProperty("user.dir") + "/archivos/automatas/";
            String nombreGrafo = nombreExp;
            
            // Guardar .dot           
            File crearDOT = new File(pathGrafo + nombreGrafo + ".dot");      
            FileWriter writer;
            writer = new FileWriter(pathGrafo + nombreGrafo + ".dot");
            writer.write(dotAFD);
            writer.close();
            
            // Crear png
            String[] cmd = {"dot", "-Tpng", pathGrafo + nombreGrafo + ".dot", "-o", pathGrafo + nombreGrafo + ".png"};
            Runtime rt = Runtime.getRuntime();
            rt.exec(cmd);
            
        } catch (IOException ex) {
            Logger.getLogger(transitionTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void impTable(){
        for(ArrayList state : states){
            String tran = "[";
            for(Object tr : (ArrayList)state.get(2)){
                transicion t = (transicion) tr;
                tran += t.toString() + ", ";
            }
            tran += "]";           
            tran = tran.replace(", ]", "]");            
            System.out.println(state.get(0) + " " + state.get(1) + " " + tran + " " + state.get(3));
        }  
    }
}
