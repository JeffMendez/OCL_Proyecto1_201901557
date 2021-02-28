/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.ArrayList;
import java.util.Set;

/**
 *
 * @author Jeffry
 */
public class Estado {
    public String valor;
    public Set siguientes;
    public boolean aceptacion;
    public ArrayList<Transicion> transiciones;

    public Estado(String valor, Set siguientes, boolean aceptacion, ArrayList<Transicion> transiciones) {
        this.valor = valor;
        this.siguientes = siguientes;
        this.aceptacion = aceptacion;
        this.transiciones = transiciones;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Set getSiguientes() {
        return siguientes;
    }

    public void setSiguientes(Set siguientes) {
        this.siguientes = siguientes;
    }

    public boolean isAceptacion() {
        return aceptacion;
    }

    public void setAceptacion(boolean aceptacion) {
        this.aceptacion = aceptacion;
    }

    public ArrayList<Transicion> getTransiciones() {
        return transiciones;
    }

    public void setTransiciones(ArrayList<Transicion> transiciones) {
        this.transiciones = transiciones;
    }
    
    

    
        
}
