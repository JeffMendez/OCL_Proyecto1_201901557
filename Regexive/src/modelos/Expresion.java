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
public class Expresion {
    private String nombre;
    private node raiz;
    private ArrayList<ArrayList> transiciones;

    public Expresion(String nombre, node raiz, ArrayList<ArrayList> transiciones) {
        this.nombre = nombre;
        this.raiz = raiz;
        this.transiciones = transiciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public node getRaiz() {
        return raiz;
    }

    public void setRaiz(node raiz) {
        this.raiz = raiz;
    }

    public ArrayList<ArrayList> getTransiciones() {
        return transiciones;
    }

    public void setTransiciones(ArrayList<ArrayList> transiciones) {
        this.transiciones = transiciones;
    }
    
    public String analizarEntrada(String entrada, ArrayList<Conjunto> conjuntos) {
        String[] entradaSplit = entrada.split("");
        String ultimoCharProcesado = "";
        
        boolean valido = true;     
        String estadoActual = "S0";
             
        for (int i=0; i<entradaSplit.length; i++) {
            String caracter = entradaSplit[i];
            // Recorrer estados
            if (valido) {
                ultimoCharProcesado = entradaSplit[i];
                for(ArrayList state : transiciones) {
                    if (state.get(0).equals(estadoActual)) {
                        // Recorrer transiciones
                        for(Object tr : (ArrayList)state.get(2)){
                            transicion t = (transicion) tr;

                            String[] itemsTransicion = t.toString().split("->");
                            String estadoOrigen = itemsTransicion[0].replace(" ", "");
                            String terminal = itemsTransicion[1].replace(" ", "");
                            String estadoDestino = itemsTransicion[2].replace(" ", "");

                            // Conjunto
                            if (terminal.charAt(0) == '{') {
                                String nombreConj = terminal.substring(1, terminal.length()-1);

                                for(Conjunto conj : conjuntos) {
                                    if (nombreConj.equals(conj.getNombre())) {
                                        // Buscar caracter en el intervalo del conjunto
                                        int caracterInt = (int) caracter.charAt(0);
                                        for (int k=0; k < conj.getElementos().size(); k++) {
                                            if (caracterInt == conj.getElementos().get(k)) {
                                                // Completar transición
                                                estadoActual = estadoDestino;
                                                valido = true;
                                                break;
                                            } else {
                                                valido = false;
                                            }
                                        }
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        break;
                    }               
                }
            } else {
                break;
            }           
        } 
        
        System.out.println(valido);
        System.out.println(ultimoCharProcesado);
        
        if (valido) {
            boolean estadoAceptacion = false;
            // Comprobar si es de aceptacion el estado actual
            for(ArrayList state : transiciones){
                if (state.get(0).equals(estadoActual)) {
                    estadoAceptacion = (boolean)state.get(3);
                    break;
                }
            }       
            // Cadena valida armar json
            if (estadoAceptacion) {
                System.out.println("Aceptacion: " + estadoActual);
            }
        }
        
        System.out.println("Analizar esto: " + entrada);    
        return "{}";
    }
}
