/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import analizadores.Lexico;
import analizadores.Sintactico;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.filechooser.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import modelos.ErrorFile;
import modelos.ExpresionEvaluar;
import modelos.ExpresionRegular;
import modelos.FileTypeFilter;
import modelos.Grafo;
import regexive.Regexive;

/**
 *
 * @author Jeffry
 */
public class Menu extends javax.swing.JFrame {

    String nombreArchivo;
    String pathArchivo;
    Lexico lexico;
    Sintactico sintactico;

    public Menu() {
        initComponents();
        
        /*txtEntrada.setText("{\n" +
                    "\n" +
                    "<!\n" +
                    "	Este es un comentario multilinea\n" +
                    "	Si no da error, ya salio el proyecto\n" +
                    "!>\n" +
                    "CONJ: letra -> a~z; // declarando conjunto de letras desde a hasta z en minusculas\n" +
                    "CONJ: digito -> 0~3; // creamos el conjunto de digitos solo para 0, 1, 2 y 3\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "//agregamos Expresiones regulares\n" +
                    "identificador -> . {letra} * | \"_\" | {letra} {digito};\n" +
                    "decimales -> . +{digito} . \".\" + {digito};\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "%%\n" +
                    "%%\n" +
                    "identificador :  \"hola_soy_id_1\"; //correcto\n" +
                    "identificador :  \"HoLA\"; //incorrecto\n" +
                    "decimales : \"301.59\"; //incorrecto\n" +
                    "decimales: \"1200.31\";//correcto\n" +
                    "\n" +
                    "}");*/
        
        txtEntrada.setText("{\n" +
        "\n" +
        "////// CONJUNTOS\n" +
        "CONJ: letra -> a~z;\n" +
        "CONJ: digito -> 0~9;\n" +
        "CONJ: vocales -> a,e,i,o,u;\n" +
        "\n" +
        "/////// EXPRESIONES REGULARES\n" +
        "EXP5  -> . \"h\" . \\' * | \\\" | \\n {digito};\n " +
        "ExpReg1 -> . {letra} * | \"_\" | {letra} {digito};\n" +
        "ExpresionReg2 -> . {digito} . \".\" + {digito};\n" +
        "RegEx3 -> . {digito} * | \"_\" | {letra} {digito};\n" +
        "RegTest -> . {digito} . {letra} * | {letra} {digito};\n" +
        "\n" +
        "%%\n" +
        "%%\n" +
        "\n" +
        "EXP5 : \"h'\"9\"; \n" +
        "ExpReg1 : \"abLexemaCokoa\"; \n" +
        "ExpresionReg2 : \"34.44\";\n" +
        "\n" +
        "}");
        
        cmbImagenes.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarImagenes();
            }
        });
    }

    String pathImages;
    public void mostrarImagenes() {  
        pathImages = System.getProperty("user.dir") + "/archivos/";
        
        switch(cmbImagenes.getSelectedItem().toString()) {
            case "Arboles":
                pathImages += "ARBOLES_201901557/";
                break;
            case "Siguientes":
                pathImages += "SIGUIENTES_201901557/";
                break;
            case "Transiciones":
                pathImages += "TRANSICIONES_201901557/";
                break;
            case "Automatas":
                pathImages += "AFD_201901557/";
                break;
        }   
        visualizarGrafo(0);
        indexImg = 0;
    }
    
    int indexImg = 0;
    public void visualizarGrafo(int index) {
        for(int i=0; i<sintactico.listaExpresiones.size(); i++) {
            if (i==index) {
                String pathImg = pathImages + sintactico.listaExpresiones.get(i).getNombreExpresion() + ".png";
                labelImage.setIcon(new ImageIcon(pathImg));
                txtExpNombre.setText(sintactico.listaExpresiones.get(i).getNombreExpresion());
                break;
            }
        }
    }
    
    public void llenarListas() {
        String[] nombresExpr = new String[sintactico.listaExpresiones.size()];
        for(int i=0; i<sintactico.listaExpresiones.size(); i++) {
            nombresExpr[i] = sintactico.listaExpresiones.get(i).getNombreExpresion();
        }
        
        listArboles.setListData(nombresExpr);
        listAutomatas.setListData(nombresExpr);
        listSiguientes.setListData(nombresExpr);
        listTransiciones.setListData(nombresExpr);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtEntrada = new javax.swing.JTextArea();
        btnGenerarAutomata = new javax.swing.JButton();
        btnAnalizar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtCMD = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listArboles = new javax.swing.JList<>();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        listSiguientes = new javax.swing.JList<>();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        listTransiciones = new javax.swing.JList<>();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        listAutomatas = new javax.swing.JList<>();
        jLabel7 = new javax.swing.JLabel();
        cmbImagenes = new javax.swing.JComboBox<>();
        jScrollPane7 = new javax.swing.JScrollPane();
        labelImage = new javax.swing.JLabel();
        btnAnterior = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();
        txtExpNombre = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        btnNuevoArchivo = new javax.swing.JMenuItem();
        btnAbrirArchivo = new javax.swing.JMenuItem();
        btnGuardar = new javax.swing.JMenuItem();
        btnGuardarComo = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Archivo de entrada");

        txtEntrada.setColumns(20);
        txtEntrada.setRows(5);
        jScrollPane1.setViewportView(txtEntrada);

        btnGenerarAutomata.setText("Generar Automatas");
        btnGenerarAutomata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarAutomataActionPerformed(evt);
            }
        });

        btnAnalizar.setText("Analizar Entradas");
        btnAnalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizarActionPerformed(evt);
            }
        });

        jLabel2.setText("Salida");

        txtCMD.setEditable(false);
        txtCMD.setColumns(20);
        txtCMD.setRows(5);
        jScrollPane2.setViewportView(txtCMD);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("-> Arboles");

        listArboles.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Sin elementos" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listArboles.setToolTipText("");
        listArboles.setEnabled(false);
        jScrollPane3.setViewportView(listArboles);

        jLabel4.setText("-> Tabla de Siguientes");

        listSiguientes.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Sin elementos" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listSiguientes.setEnabled(false);
        jScrollPane4.setViewportView(listSiguientes);

        jLabel5.setText("-> Tabla de Transiciones");

        listTransiciones.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Sin elementos" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listTransiciones.setEnabled(false);
        jScrollPane5.setViewportView(listTransiciones);

        jLabel6.setText("-> Automatas");

        listAutomatas.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Sin elementos" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listAutomatas.setEnabled(false);
        jScrollPane6.setViewportView(listAutomatas);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane4)
                    .addComponent(jScrollPane5)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5))
                            .addComponent(jLabel6))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane6))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.setText("Visor de Imagenes:");

        cmbImagenes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Arboles", "Siguientes", "Transiciones", "Automatas" }));
        cmbImagenes.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cmbImagenesPropertyChange(evt);
            }
        });

        labelImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jScrollPane7.setViewportView(labelImage);

        btnAnterior.setText("Anterior");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });

        btnSiguiente.setText("Siguiente");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        txtExpNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtExpNombre.setText("....");

        jMenu1.setText("Archivo");

        btnNuevoArchivo.setText("Nuevo Archivo");
        btnNuevoArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoArchivoActionPerformed(evt);
            }
        });
        jMenu1.add(btnNuevoArchivo);

        btnAbrirArchivo.setText("Abrir Archivo");
        btnAbrirArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirArchivoActionPerformed(evt);
            }
        });
        jMenu1.add(btnAbrirArchivo);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jMenu1.add(btnGuardar);

        btnGuardarComo.setText("Guardar como....");
        btnGuardarComo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarComoActionPerformed(evt);
            }
        });
        jMenu1.add(btnGuardarComo);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnGenerarAutomata)
                                    .addGap(24, 24, 24)
                                    .addComponent(btnAnalizar))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbImagenes, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                        .addComponent(btnAnterior)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSiguiente)
                        .addGap(52, 52, 52))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtExpNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane7))
                        .addGap(23, 23, 23))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnGenerarAutomata)
                                    .addComponent(btnAnalizar)))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cmbImagenes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAnterior)
                            .addComponent(btnSiguiente))
                        .addGap(18, 18, 18)
                        .addComponent(txtExpNombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane7)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizarActionPerformed
        if (sintactico.listaExpresiones.size() > 0) {          
            String jsonResultados = "[";          
            for(int i=0; i<sintactico.listaExpEvaluar.size(); i++) {
                ExpresionEvaluar expresionEvaluar = sintactico.listaExpEvaluar.get(i);          
                // Buscar la expresion
                for(int j=0; j<sintactico.listaExpresiones.size(); j++) {
                    ExpresionRegular expr = sintactico.listaExpresiones.get(j);                
                    if (expr.getNombreExpresion().equals(expresionEvaluar.getNombreExpresion())) {
                        jsonResultados += expr.analizarEntrada(expresionEvaluar.getValor(), sintactico.listaConjuntos);
                        if (i+1 < sintactico.listaExpEvaluar.size()) {
                            jsonResultados += ",";
                        }
                        break;
                    }
                }
            }          
            jsonResultados += "\n]";
            
            try {
                String pathGrafo = System.getProperty("user.dir") + "/archivos/SALIDAS_201901557/";
                String nombreGrafo = "resultados";
        
                File crearJSON = new File(pathGrafo + nombreGrafo + ".json");      
                FileWriter writer;
                writer = new FileWriter(pathGrafo + nombreGrafo + ".json");
                writer.write(jsonResultados);
                writer.close();
                
                JOptionPane.showMessageDialog(null, "Archivo JSON generado");
            } catch (IOException ex) {
                System.out.println(ex);
            }        
        } else {
            JOptionPane.showMessageDialog(null, "No hay automatas generados para analizar");
        }   
    }//GEN-LAST:event_btnAnalizarActionPerformed

    private void btnGenerarAutomataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarAutomataActionPerformed
        try {
            System.out.println("Inicio analisis.....");
            
            lexico = new Lexico(new BufferedReader(new StringReader(txtEntrada.getText())));
            
            if (lexico.listaErrores.size() > 0) {
                String salidaCMD = txtCMD.getText();
                for(int i=0; i<lexico.listaErrores.size(); i++) {
                    ErrorFile error = lexico.listaErrores.get(i);
                    salidaCMD += "\nError léxico: " + error.getValor() + " , linea: " + error.getFila() + " , columna: " + error.getColumna();
                }
                txtCMD.setText(salidaCMD);
            } else {              
                sintactico = new Sintactico(lexico);
                sintactico.parse();
                
                if (sintactico.listaErrores.size() > 0) {
                    String salidaCMD = txtCMD.getText();
                    for(int i=0; i<sintactico.listaErrores.size(); i++) {
                        ErrorFile error = sintactico.listaErrores.get(i);
                        salidaCMD += "\nError sintáctico: " + error.getValor() + " , linea: " + error.getFila() + " , columna: " + error.getColumna();
                    }
                    txtCMD.setText(salidaCMD);
                }
                
                mostrarImagenes();
                llenarListas();
            }
            
            if (lexico.listaErrores.size() > 0 || sintactico.listaErrores.size() > 0) {
                Grafo grafo = new Grafo();
                grafo.generarReporteErrores(lexico.listaErrores, sintactico.listaErrores);
            }
            
            JOptionPane.showMessageDialog(null, "Automatas generados (Exceptuando errores)");
            System.out.println("Analisis finalizado");
        } catch (Exception ex) {
            Logger.getLogger(Regexive.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGenerarAutomataActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        indexImg--;      
        if (indexImg < 0) {
            indexImg++;
        }  
        visualizarGrafo(indexImg);
    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void btnNuevoArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoArchivoActionPerformed
        String numRandom = String.valueOf(new Random().nextInt(100));
        pathArchivo = System.getProperty("user.dir") + "/archivos/";
        nombreArchivo = "newRegex" + numRandom + ".olc";
        File nuevoArchivo = new File(pathArchivo + nombreArchivo);
        try {
            if (nuevoArchivo.createNewFile()) {
                txtEntrada.setEditable(true);
                this.setTitle("Regexive - " + nombreArchivo);
                JOptionPane.showMessageDialog(null, "Archivo creado:  " + nombreArchivo + "\nSe habilito la entrada de texto");
            } else {
                JOptionPane.showMessageDialog(null, "Error en la creación (permisos)");
            }
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }//GEN-LAST:event_btnNuevoArchivoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            FileWriter writer;
            writer = new FileWriter(pathArchivo + nombreArchivo);
            writer.write(txtEntrada.getText());
            writer.close();
            JOptionPane.showMessageDialog(null, "Archivo guardado exitosamente");
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnGuardarComoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarComoActionPerformed
        // Elegir nuevo nombre
        nombreArchivo = JOptionPane.showInputDialog("Escribe el nuevo nombre del archivo") + ".olc";
        File nuevoArchivo = new File(pathArchivo + nombreArchivo);
        try {
            if (nuevoArchivo.createNewFile()) {
                // Guardar en el los nuevos cambios
                FileWriter writer;
                writer = new FileWriter(pathArchivo + nombreArchivo);
                writer.write(txtEntrada.getText());
                writer.close();
                
                this.setTitle("Regexive - " + nombreArchivo);
                JOptionPane.showMessageDialog(null, "El archivo ha sido guardado con otro nombre");
            } else {
                JOptionPane.showMessageDialog(null, "Error en la creación (permisos)");
            }
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }//GEN-LAST:event_btnGuardarComoActionPerformed

    private void btnAbrirArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirArchivoActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(false);
        FileFilter olcFiltrer = new FileTypeFilter(".olc", "Files OLC");
        fileChooser.setFileFilter(olcFiltrer);
        
        if(fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                File archivo =  fileChooser.getSelectedFile();
                
                Scanner myReader = new Scanner(archivo);
                String contenido = "";
                while (myReader.hasNextLine()) {
                    contenido += myReader.nextLine() + "\n";    
                }
                myReader.close();
                
                nombreArchivo = archivo.getName();
                pathArchivo = archivo.getAbsolutePath().replace(nombreArchivo, "");
                txtEntrada.setText(contenido);
                txtEntrada.setEditable(true);
                this.setTitle("Regexive - " + nombreArchivo);

                System.out.println(nombreArchivo);
                System.out.println(pathArchivo);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnAbrirArchivoActionPerformed

    private void cmbImagenesPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cmbImagenesPropertyChange

    }//GEN-LAST:event_cmbImagenesPropertyChange

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        indexImg++;      
        if (indexImg >= sintactico.listaExpresiones.size()) {
            indexImg--;
        }  
        visualizarGrafo(indexImg);
    }//GEN-LAST:event_btnSiguienteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem btnAbrirArchivo;
    private javax.swing.JButton btnAnalizar;
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnGenerarAutomata;
    private javax.swing.JMenuItem btnGuardar;
    private javax.swing.JMenuItem btnGuardarComo;
    private javax.swing.JMenuItem btnNuevoArchivo;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JComboBox<String> cmbImagenes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel labelImage;
    private javax.swing.JList<String> listArboles;
    private javax.swing.JList<String> listAutomatas;
    private javax.swing.JList<String> listSiguientes;
    private javax.swing.JList<String> listTransiciones;
    private javax.swing.JTextArea txtCMD;
    private javax.swing.JTextArea txtEntrada;
    private javax.swing.JLabel txtExpNombre;
    // End of variables declaration//GEN-END:variables
}
