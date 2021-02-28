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
public class type {
    public static enum Types{
        HOJA,
        AND,               // .
        OR,                // |
        KLEENE,            // *
        MAS,               // +
        INTERROGACION,     // ?
        CADENA,            // "texto"
        CARACTERESPECIAL,  // \n \' \"
    };
}
