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
public class Nodo {
    private Nodo izquierdo;
    private Nodo derecho;
  
    private String lexema;
    private Tipo tipo;
    private int numeroHoja;
    private boolean anulable;
    private ArrayList<Integer> primeros;
    private ArrayList<Integer> ultimos;

    public Nodo(String lexema, Tipo tipo, int numeroHoja, boolean anulable) {
        this.lexema = lexema;
        this.tipo = tipo;
        this.numeroHoja = numeroHoja;
        this.anulable = anulable;
        this.primeros = new ArrayList<>();
        this.ultimos = new ArrayList<>();
    }
    
    public void calcularAnulable() {     
        if (this.getTipo() == Tipo.OR) {
            if (izquierdo.isAnulable() || derecho.isAnulable()) {
                this.setAnulable(true);
            }
        }
        
        if (this.getTipo() == Tipo.AND) {
            if (izquierdo.isAnulable() && derecho.isAnulable()) {
                this.setAnulable(true);
            }
        }
        
        if (this.getTipo() == Tipo.MAS) {
            if (izquierdo.isAnulable()) {
                this.setAnulable(false);
            }
        }
    }
    
    public void calcularPrimerosUltimos() {
        // | -> si es izq anulable se unen, sino solo izq
        if (this.getTipo() == Tipo.OR) {
            ArrayList<Integer> nuevosPrimeros = new ArrayList();
            nuevosPrimeros.addAll(this.izquierdo.primeros);
            nuevosPrimeros.addAll(this.derecho.primeros);
            
            ArrayList<Integer> nuevosUltimos = new ArrayList();
            nuevosUltimos.addAll(this.izquierdo.ultimos);
            nuevosUltimos.addAll(this.derecho.ultimos);
            
            this.setPrimeros(nuevosPrimeros);
            this.setUltimos(nuevosUltimos);
        }
        // . -> si der es anulable se mezclan sino solo der
        if (this.getTipo() == Tipo.AND) {
            if (this.izquierdo.isAnulable()) {
                ArrayList<Integer> nuevosPrimeros = new ArrayList<>();
                nuevosPrimeros.addAll(this.izquierdo.primeros);
                nuevosPrimeros.addAll(this.derecho.primeros);
                this.setPrimeros(nuevosPrimeros);
            } else {
                ArrayList<Integer> nuevosPrimeros = new ArrayList<>();
                nuevosPrimeros.addAll(this.izquierdo.primeros);
                this.setPrimeros(nuevosPrimeros);
            }
            
            if (this.derecho.isAnulable()) {
                ArrayList<Integer> nuevosUltimos = new ArrayList();
                nuevosUltimos.addAll(this.izquierdo.ultimos);
                nuevosUltimos.addAll(this.derecho.ultimos);
                this.setUltimos(nuevosUltimos);
            } else {
                ArrayList<Integer> nuevosUltimos = new ArrayList();
                nuevosUltimos.addAll(this.derecho.ultimos);
                this.setUltimos(nuevosUltimos);
            }
        }
    }
    
    // Nodo completo
    public Nodo(Object dato, Nodo izquierdo, Nodo derecho) {
        this.izquierdo = izquierdo;
        this.derecho = derecho;
    }
    
    // Nodo vacio
    public Nodo(Object dato) {
        this.izquierdo = null;
        this.derecho = null;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public int getNumeroHoja() {
        return numeroHoja;
    }

    public void setNumeroHoja(int numeroHoja) {
        this.numeroHoja = numeroHoja;
    }

    public boolean isAnulable() {
        return anulable;
    }

    public void setAnulable(boolean anulable) {
        this.anulable = anulable;
    }

    public Nodo getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(Nodo izquierdo) {
        this.izquierdo = izquierdo;
    }

    public Nodo getDerecho() {
        return derecho;
    }

    public void setDerecho(Nodo derecho) {
        this.derecho = derecho;
    }

    public void agregarPrimero(int primero){
        this.primeros.add(primero);
    }
    
    public void agregarUltimo(int ultimo){
        this.ultimos.add(ultimo);
    }

    public ArrayList<Integer> getPrimeros() {
        return primeros;
    }

    public void setPrimeros(ArrayList<Integer> primeros) {
        this.primeros = primeros;
    }

    public ArrayList<Integer> getUltimos() {
        return ultimos;
    }

    public void setUltimos(ArrayList<Integer> ultimos) {
        this.ultimos = ultimos;
    }
 
}
