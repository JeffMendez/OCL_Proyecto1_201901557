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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jeffry
 */
public class followTable {
    
    String nombreExp;
    
    public followTable() {}
    
    public followTable(String nombreExp) {
        this.nombreExp = nombreExp;
    }
    
    public void append(int numNode, String lexeme, ArrayList flwList, ArrayList<ArrayList> table){
        for (ArrayList item : table){
            if( (int) item.get(0) == numNode && item.get(1) == lexeme ){
                for (Object flwItem : flwList){
                    if(! ((ArrayList)item.get(2)).contains((int)flwItem) ){
                       ((ArrayList)item.get(2)).add(flwItem);
                    }
                }
                return;
            }
        }
        ArrayList dato = new ArrayList();
        dato.add(numNode);
        dato.add(lexeme);
        dato.add(flwList);
        
        table.add(dato);
    }
    
    public ArrayList next(int numNode, ArrayList<ArrayList> table){
        ArrayList result = new ArrayList();
        for(ArrayList item : table){
            if( (int) item.get(0) == numNode ){
                result.add(item.get(1));
                result.add(((ArrayList)item.get(2)).clone());
                return result;
            }
        }
        result.add("");
        result.add(new ArrayList());
        return result;
    }
    
    public void crearGrafoSiguientes(ArrayList<ArrayList> table){
        String dotTable = "digraph T {\n" +
                            "aHtmlTable [\n" +
                            "   shape=plaintext\n" +
                            "   color=\"#283747\" fontcolor=\"#154360\" label=<\n" +
                            "\n" +
                            "   <table border='1' cellborder='1'>\n" +
                            "   <tr>\n" +
                            "      <td>Hoja</td>\n" +
                            "      <td>Numero</td>\n" +
                            "      <td>Siguientes</td>\n" +
                            "   </tr>";
        
        // Obtener ultimo valor -> es el primero en realidad xdd       
        int maxHoja = 0;
        for (int i=0; i < table.size(); i++) {          
            ArrayList item = table.get(i);
            Integer numeroHoja = (Integer) item.get(0);
            String valorHoja = (String) item.get(1);
            ArrayList<Integer> siguientes = (ArrayList<Integer>) item.get(2);
            
            dotTable += "\t<tr><td>" + valorHoja + "</td><td>" + numeroHoja + "</td><td>" + siguientes + "</td></tr>\n";                                              
            if (numeroHoja > maxHoja) { maxHoja = numeroHoja; }
        }
        
        dotTable += "<tr><td>#</td><td>" + (maxHoja+1) + "</td><td>--</td></tr>\t\n" +
                    "</table>\n" +
                        "\n" +
                        "   >]; \n" +
                        "\n" +
                        "}";
        
        try {
            String pathGrafo = System.getProperty("user.dir") + "/archivos/tabla_siguientes/";
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
    
    public void printTable(ArrayList<ArrayList> table){ 
        for (int i=0; i < table.size(); i++) {          
            ArrayList item = table.get(i);
            System.out.println(item.get(0) + " - " + item.get(1) + " - " + item.get(2) );
        }
    }
}
