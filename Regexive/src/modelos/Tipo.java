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
public enum Tipo {
    OR,             // |
    AND,            // .
    ASTERISCO,      // *
    MAS,            // +
    INTERROGACION,   // ?
    CADENA,            // "texto"
    CARACTERESPECIAL,  // \n \' \"
    CONJUNTO,          // {digito}
    ACEPTACION;        // #
}
