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
    
    public Grafo() {}
    
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
            System.out.println(ex);
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
    
    public void generarAFN() {
        //String dotArbol = "digraph G {\n\trankdir=LR\n\t0\n\t0 -> 1 [label=\"ϵ\"]"; // Se declara el nodo 0
        String dotArbol = "digraph G {\n\trankdir=LR\n\t0"; // Se declara el nodo 0
        dotArbol += graficarThompson(expresion.getRaizArbol(), expresion.getRaizArbol().getTipo());
        dotArbol += "\n}";
        
        try {
            String pathGrafo = System.getProperty("user.dir") + "/archivos/AFND_201901557/";
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
            System.out.println(ex);
        }
    }
    
    int countThompson = 0; // Contador de nodos
    public String graficarThompson(Nodo nodo, Tipo tipoAnterior) {
        // Recursividad
        String dotArbol = "";
        
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
                
                if (tipoAnterior != Tipo.AND) {
                    countThompson += 2;
                    dotArbol += "\n\t" + (countThompson-1) + " -> " + countThompson + " [label=\""+ salidaValor +"\"]";
                } else {
                    countThompson++;
                    dotArbol += "\n\t" + (countThompson-1) + " -> " + countThompson + " [label=\""+ salidaValor +"\"]";
                }
                break;
            case CADENA: case CONJUNTO:
                if (tipoAnterior != Tipo.AND) {
                    countThompson += 2;
                    dotArbol += "\n\t" + (countThompson-1) + " -> " + countThompson + " [label=\""+ nodo.getLexema() +"\"]";
                } else {
                    countThompson++;
                    dotArbol += "\n\t" + (countThompson-1) + " -> " + countThompson + " [label=\""+ nodo.getLexema() +"\"]";
                }
                //countThompson += 2;
                //dotArbol += "\n\t" + (countThompson-1) + " -> " + countThompson + " [label=\""+ nodo.getLexema() +"\"]";
                break;
            case ACEPTACION:
                dotArbol += "\n\t" + countThompson + " [shape=doublecircle]";
                //dotArbol += "\n\t" + (countThompson-1) + " -> " + countThompson + " [label=\"ϵ\"]";
                break;
            case OR:
                int auxInicioOr;
                int auxFinalUno;
                int auxFinalDos;
                
                countThompson++;
                
                auxInicioOr = countThompson;
                dotArbol += "\n\t" + countThompson + " -> " + (countThompson+1) + " [label=\"ϵ\"]";
                dotArbol += graficarThompson(nodo.getIzquierdo(), nodo.getTipo());
                auxFinalUno = countThompson;
                
                dotArbol += "\n\t" + auxInicioOr + " -> " + (countThompson+1) + " [label=\"ϵ\"]";
                dotArbol += graficarThompson(nodo.getDerecho(), nodo.getTipo());
                auxFinalDos = countThompson;
                
                countThompson++;
                dotArbol += "\n\t" + auxFinalUno + " -> " + countThompson + " [label=\"ϵ\"]";
                dotArbol += "\n\t" + auxFinalDos + " -> " + countThompson + " [label=\"ϵ\"]";
                break;
            case AND:
                dotArbol += graficarThompson(nodo.getIzquierdo(), nodo.getTipo());
                dotArbol += graficarThompson(nodo.getDerecho(), nodo.getTipo());
                
                /*Tipo tipoSiguiente = nodo.getDerecho().getTipo();
                if (tipoSiguiente != Tipo.AND) {
                    dotArbol += "\n\t" + countThompson + " -> " + (countThompson+1) + " [label=\"ϵ\"]";
                }*/         
                break;
            case ASTERISCO:
                int auxInicioA;
                //countThompson++;
                auxInicioA = countThompson;
                
                // Conexion a proximo grafo
                dotArbol += "\n\t" + countThompson + " -> " + (countThompson+1) + " [label=\"ϵ\"]";
                dotArbol += graficarThompson(nodo.getIzquierdo(), nodo.getTipo());
                dotArbol += "\n\t" + countThompson + " -> " + (countThompson+1) + " [label=\"ϵ\"]";
                
                // Pueden ser mas por lo cual conexion de regreso a expresion 
                dotArbol += "\n\t" + countThompson + " -> " + (auxInicioA+1) + " [label=\"ϵ\"]";              
                // Se cancela por lo cual conexion principio a fin    
                dotArbol += "\n\t" + auxInicioA + " -> " + (countThompson+1) + " [label=\"ϵ\"]";
                countThompson++;                      
                break;
            case MAS:
                int auxInicioM;
                //countThompson++;
                auxInicioM = countThompson;

                // Conexion a proximo grafo
                dotArbol += "\n\t" + countThompson + " -> " + (countThompson+1) + " [label=\"ϵ\"]";
                dotArbol += graficarThompson(nodo.getIzquierdo(), nodo.getTipo());
                dotArbol += "\n\t" + countThompson + " -> " + (countThompson+1) + " [label=\"ϵ\"]";
                          
                // Pueden ser mas por lo cual conexion de regreso a expresion 
                dotArbol += "\n\t" + countThompson + " -> " + (auxInicioM+1) + " [label=\"ϵ\"]";
                countThompson++; 
                break;
            case INTERROGACION:
                int auxInicioI;
                //countThompson++;
                auxInicioI = countThompson;

                // Conexion a proximo grafo
                dotArbol += "\n\t" + countThompson + " -> " + (countThompson+1) + " [label=\"ϵ\"]";
                dotArbol += graficarThompson(nodo.getIzquierdo(), nodo.getTipo());
                dotArbol += "\n\t" + countThompson + " -> " + (countThompson+1) + " [label=\"ϵ\"]";
                            
                // Se cancela por lo cual conexion principio a fin    
                dotArbol += "\n\t" + auxInicioI + " -> " + (countThompson+1) + " [label=\"ϵ\"]";
                countThompson++;                      
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
            System.out.println(ex);
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
            System.out.println(ex);
        }
    }
    
    public void generarAFD() {
        String dotAFD = "digraph G {\n\trankdir=LR\n";           
        
        for(Estado estado: expresion.getListaTransiciones()) {                 
            if (estado.isAceptacion()) {
                dotAFD += "\t" + estado.getValor() + " [shape=\"doublecircle\"]"; 
            }
            
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
                dotAFD += "\t" + estado.getValor() + " -> " + transicion.getNombreEstadoDestino() + " [label=\"" + terminal + "\"]";
            }           
        }
        
        dotAFD += "\n" +
                    "}";
        
        try {
            String pathGrafo = System.getProperty("user.dir") + "/archivos/AFD_201901557/";
            String nombreGrafo = expresion.getNombreExpresion();
            
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
            System.out.println(ex);
        }
    }
    
    public void generarReporteErrores(ArrayList<ErrorFile> erroresLexicos, ArrayList<ErrorFile> erroresSintacticos) {
        String dotErrors = "digraph T {\n" +
                            "aHtmlTable [\n" +
                            "   shape=plaintext\n" +
                            "   color=\"#283747\" fontcolor=\"#154360\" label=<\n" +
                            "\n" +
                            "   <table border='1' cellborder='1'>\n" +
                            "   <tr>\n" +
                            "      <td>#</td>\n" +
                            "      <td>Tipo de Error</td>\n" +
                            "      <td>Lexema</td>\n" +
                            "      <td>Línea</td>\n" +
                            "      <td>Columna</td>\n" +
                            "   </tr>";
        
        int contErr = 1;       
        for(ErrorFile errLex: erroresLexicos) {
            dotErrors += "\t<tr><td>" + contErr + "</td><td>" + errLex.getTipoError() + "</td><td>" + errLex.getValor() + "</td><td>" + errLex.getFila() + "</td><td>" + errLex.getColumna() + "</td></tr>\n";  
            contErr++;
        }     
        
        for(ErrorFile errorSin: erroresSintacticos) {
            dotErrors += "\t<tr><td>" + contErr + "</td><td>" + errorSin.getTipoError() + "</td><td>" + errorSin.getValor() + "</td><td>" + errorSin.getFila() + "</td><td>" + errorSin.getColumna() + "</td></tr>\n";  
            contErr++;
        }
        
        dotErrors += "</table>\n" +
                        "\n" +
                        "   >]; \n" +
                        "\n" +
                        "}";
        
        try {
            String pathGrafo = System.getProperty("user.dir") + "/archivos/ERRORES_201901557/";
            String nombreGrafo = "errores";
            
            // Guardar .dot           
            File crearDOT = new File(pathGrafo + nombreGrafo + ".dot");      
            FileWriter writer;
            writer = new FileWriter(pathGrafo + nombreGrafo + ".dot");
            writer.write(dotErrors);
            writer.close();
            
            // Crear png
            String[] cmd = {"dot", "-Tpng", pathGrafo + nombreGrafo + ".dot", "-o", pathGrafo + nombreGrafo + ".png"};
            Runtime rt = Runtime.getRuntime();
            rt.exec(cmd);        
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
