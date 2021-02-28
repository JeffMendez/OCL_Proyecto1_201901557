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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jeffry
 */
public class Grafo {
    private ExpresionRegular expresion;

    public Grafo(ExpresionRegular expresion) {
        this.expresion = expresion;
    }
    
    public void generarGrafoArbol() {
        String dotArbol = "digraph G {";
        dotArbol += graficarNodo(expresion.getRaizArbol());
        dotArbol += "\n}";
        
        try {
            String pathGrafo = System.getProperty("user.dir") + "/archivos/ARBOLES_201901557/";
            String nombreGrafo = expresion.getNombreExpresion();
            
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
    
    public String graficarNodo(Nodo nodo) {
        // Recursividad
        String dotArbol = "";
        
        if (nodo.getIzquierdo() != null) {
            dotArbol += graficarNodo(nodo.getIzquierdo());
        }
        
        if (nodo.getDerecho() != null) {
            dotArbol += graficarNodo(nodo.getDerecho());
        }
        
        switch(nodo.getTipo()) {
            // Casos hojas
            case CARACTERESPECIAL:
                char caracter = nodo.getLexema().charAt(0);
                String salidaValor = "";
                switch(nodo.getLexema()) {                  
                    // Caracteres especiales
                    case "\\n":
                        salidaValor = "Salto de linea";
                        break;
                    case "\\\"":
                        salidaValor = "Comilla doble";
                        break;
                    case "\\\'":           
                        salidaValor = "Comilla simple";
                        break;
                }
                dotArbol += "\n" + nodo.hashCode() + " [shape=record label=\""+nodo.getPrimeros()+" |{ "+nodo.isAnulable()+" | "+salidaValor+" | "+nodo.getNumeroHoja()+" }| "+nodo.getUltimos()+"\"];";
                break;
            case CADENA: case CONJUNTO: case ACEPTACION:
                // Declarar hoja                    
                dotArbol += "\n" + nodo.hashCode() + " [shape=record label=\""+nodo.getPrimeros()+" |{ "+nodo.isAnulable()+" | "+nodo.getLexema()+" | "+nodo.getNumeroHoja()+" }| "+nodo.getUltimos()+"\"];";
                break;
            case OR:
                // Declarar nodo
                dotArbol += "\n" + nodo.hashCode() + " [shape=record label=\""+nodo.getPrimeros()+" |{ "+nodo.isAnulable()+" | OR | -- }| "+nodo.getUltimos()+"\"];";
                // Uniones
                dotArbol += "\n" + nodo.hashCode() + " -> " + nodo.getIzquierdo().hashCode();
                dotArbol += "\n" + nodo.hashCode() + " -> " + nodo.getDerecho().hashCode();
                break;
            case AND:
                // Declarar nodo
                dotArbol += "\n" + nodo.hashCode() + " [shape=record label=\""+nodo.getPrimeros()+" |{ "+nodo.isAnulable()+" | . | -- }| "+nodo.getUltimos()+"\"];";
                // Uniones
                dotArbol += "\n" + nodo.hashCode() + " -> " + nodo.getIzquierdo().hashCode();
                dotArbol += "\n" + nodo.hashCode() + " -> " + nodo.getDerecho().hashCode();
                break;
            case ASTERISCO:
                // Declarar nodo
                dotArbol += "\n" + nodo.hashCode() + " [shape=record label=\""+nodo.getPrimeros()+" |{ "+nodo.isAnulable()+" | * | -- }| "+nodo.getUltimos()+"\"];";
                // Uniones
                dotArbol += "\n" + nodo.hashCode() + " -> " + nodo.getIzquierdo().hashCode();
                break;
            case MAS:
                // Declarar nodo
                dotArbol += "\n" + nodo.hashCode() + " [shape=record label=\""+nodo.getPrimeros()+" |{ "+nodo.isAnulable()+" | + | -- }| "+nodo.getUltimos()+"\"];";
                // Uniones
                dotArbol += "\n" + nodo.hashCode() + " -> " + nodo.getIzquierdo().hashCode();
                break;
            case INTERROGACION:
                // Declarar nodo
                dotArbol += "\n" + nodo.hashCode() + " [shape=record label=\""+nodo.getPrimeros()+" |{ "+nodo.isAnulable()+" | ? | -- }| "+nodo.getUltimos()+"\"];";
                // Uniones
                dotArbol += "\n" + nodo.hashCode() + " -> " + nodo.getIzquierdo().hashCode();
                break;
        }
        
        return dotArbol;
    } 
    
    public void generarTablaSiguientes() {
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
        
        for(Siguiente hoja: expresion.getListaSiguientes()) {
            String valorHoja;           
            switch(hoja.getValorHoja()) {
                case "\\n":
                    valorHoja = "Salto de linea";
                    break;
                case "\\\"":
                    valorHoja = "Comilla doble";
                    break;
                case "\\\'":           
                    valorHoja = "Comilla simple";
                    break;
                default:
                    valorHoja = hoja.getValorHoja();
                    break;
            } 
            dotTable += "\t<tr><td>" + valorHoja + "</td><td>" + hoja.getNumeroHoja() + "</td><td>" + hoja.getSiguientes() + "</td></tr>\n";  
        }
        
        dotTable += "</table>\n" +
                        "\n" +
                        "   >]; \n" +
                        "\n" +
                        "}";
        
        try {
            String pathGrafo = System.getProperty("user.dir") + "/archivos/SIGUIENTES_201901557/";
            String nombreGrafo = expresion.getNombreExpresion();
            
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
    
    public void generarTablaTransiciones() {
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
        
        
        for(Estado estado: expresion.getListaTransiciones()) {
            String dotEstado = "";
            
            
            
            if (estado.isAceptacion()) {
                dotEstado = "<td bgcolor=\"yellow\"> " + estado.valor + " " + estado.siguientes+"</td>";
            } else {
                dotEstado = "<td>" + estado.valor + " " + estado.siguientes+"</td>";
            }
            
            String dotTransiciones = "";
            for(Transicion transicion: estado.transiciones) {  
                String terminal = "";
                switch(transicion.getValorHoja()) {
                    case "\\n":
                        terminal = "Salto de linea";
                    break;
                    case "\\\"":
                        terminal = "Comilla doble";
                        break;
                    case "\\\'":           
                        terminal = "Comilla simple";
                        break;
                    default:
                        terminal = transicion.getValorHoja();
                        break;
                }         
                dotTransiciones += "<td>"+transicion.getNombreEstadoDestino()+" "+terminal+"</td>"; 
            }
            dotTable += "<tr>" + dotEstado + dotTransiciones + "</tr>"; 
        }
               
        dotTable += "</table>\n" +
                        "\n" +
                        "   >]; \n" +
                        "\n" +
                        "}";
        
        try {
            String pathGrafo = System.getProperty("user.dir") + "/archivos/TRANSICIONES_201901557/";
            String nombreGrafo = expresion.getNombreExpresion();
            
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
}
