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
public class Conjunto {
    private String nombre;
    private ArrayList<Integer> elementos; // ASCII

    // Conjunto sin nombre (Temporal)
    public Conjunto() {
        this.nombre = "";
        this.elementos = new ArrayList<>();
    }
    
    // Para conjunto rango
    public Conjunto(Object nombreConj, Object limiteIzq, Object limiteDer) {
        this.nombre = nombreConj.toString();
        this.elementos = new ArrayList<>();     
        // [a-z]
        int asciiIzq = (int) limiteIzq.toString().charAt(0);
        int asciiDer = (int) limiteDer.toString().charAt(0);    
        // Agregar elementos intermedios
        for(int i = asciiIzq; i <= asciiDer; i++) {
            this.elementos.add(i);
        }
    }
    
    public void agregarElemento(Object elemento){
        int asciiElemento = (int)elemento.toString().charAt(0);
        this.elementos.add(asciiElemento);
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Integer> getElementos() {
        return elementos;
    }

    public void setElementos(ArrayList<Integer> elementos) {
        this.elementos = elementos;
    }
}