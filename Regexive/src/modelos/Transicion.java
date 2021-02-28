/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Jeffry
 */
public class Transicion {
    public String valorHoja;
    public Set estadoDestino;
    public String nombreEstadoDestino;
    
    public Transicion(String valorHoja) {
        this.valorHoja = valorHoja;
        this.estadoDestino = new HashSet<>();
    }
    
    public String getValorHoja() {
        return valorHoja;
    }

    public void setValorHoja(String valorHoja) {
        this.valorHoja = valorHoja;
    }

    public Set getEstadoDestino() {
        return estadoDestino;
    }

    public void setEstadoDestino(Set estadoDestino) {
        this.estadoDestino = estadoDestino;
    }

    public String getNombreEstadoDestino() {
        return nombreEstadoDestino;
    }

    public void setNombreEstadoDestino(String nombreEstadoDestino) {
        this.nombreEstadoDestino = nombreEstadoDestino;
    }
 
}
