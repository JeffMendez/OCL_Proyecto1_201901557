/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regexive;

import analizadores.Lexico;
import analizadores.Sintactico;
import java.io.BufferedReader;
import vistas.Menu;
import java.io.FileInputStream;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Jeffry
 */
public class Regexive {

    static public Menu menu_principal;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        menu_principal = new Menu();
        menu_principal.setTitle("Regexive");
        menu_principal.setVisible(true);
    }
}
