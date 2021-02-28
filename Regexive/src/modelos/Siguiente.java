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
public class Siguiente {
    private int numeroHoja;
    private String valorHoja;
    private ArrayList<Integer> siguientes;

    public Siguiente(int numeroHoja, String valorHoja) {
        this.numeroHoja = numeroHoja;
        this.valorHoja = valorHoja;
        this.siguientes = new ArrayList<>();
    }
    
    public void agregarSiguiente(int siguiente) {
        this.siguientes.add(siguiente);
    }

    public int getNumeroHoja() {
        return numeroHoja;
    }

    public void setNumeroHoja(int numeroHoja) {
        this.numeroHoja = numeroHoja;
    }

    public String getValorHoja() {
        return valorHoja;
    }

    public void setValorHoja(String valorHoja) {
        this.valorHoja = valorHoja;
    }

    public ArrayList<Integer> getSiguientes() {
        return siguientes;
    }

    public void setSiguientes(ArrayList<Integer> siguientes) {
        this.siguientes = siguientes;
    }
    
}
