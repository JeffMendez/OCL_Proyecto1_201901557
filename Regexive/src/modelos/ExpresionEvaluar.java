/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author Jeffry
 */
public class ExpresionEvaluar {
    
    private String nombreExpresion;
    private String valor;

    public ExpresionEvaluar() {}
    
    public ExpresionEvaluar(String nombreExpresion, String valor) {
        this.nombreExpresion = nombreExpresion;
        this.valor = valor;
    }

    public String getNombreExpresion() {
        return nombreExpresion;
    }

    public void setNombreExpresion(String nombreExpresion) {
        this.nombreExpresion = nombreExpresion;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
}
