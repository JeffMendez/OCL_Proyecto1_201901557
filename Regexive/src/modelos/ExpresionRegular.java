/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Jeffry
 */
public class ExpresionRegular {
    private String nombreExpresion;
    private Nodo raizArbol;
    private ArrayList<Siguiente> listaSiguientes;
    private ArrayList<Estado> listaTransiciones = new ArrayList<Estado>();
    
    Map<Set<Integer>, Set<Transicion>> tablaEstados;
    
    public ExpresionRegular(String nombreExpresion, Nodo raizArbol, ArrayList<Siguiente> listaSiguientes) {
        this.nombreExpresion = nombreExpresion;
        this.raizArbol = raizArbol;
        this.listaSiguientes = listaSiguientes;
        generarEstados();
        generarTablaEstados();
    }
    
    public String analizarEntrada(String entrada, ArrayList<Conjunto> conjuntos) {
        String[] entradaSplit = entrada.split("");
        String ultimoCharProcesado = "";
        
        boolean valido = true;     
        String estadoActual = "S0";
             
        for (int i=0; i<entradaSplit.length; i++) {
            String caracter = entradaSplit[i];
            // Recorrer estados
            if (valido) {
                ultimoCharProcesado = entradaSplit[i];
                
                for(Estado estado: listaTransiciones) {
                    if (estado.valor.equals(estadoActual)) {
                        
                        for(Transicion transicion: estado.transiciones) {
                            String terminal = transicion.getValorHoja();
                            String estadoDestino = transicion.getNombreEstadoDestino();
                            
                            // Verificar si es conjunto
                            boolean esConj = false;
                            for(Conjunto conj : conjuntos) {
                                if (conj.nombre.equals(terminal)) {
                                    esConj = true;
                                    break;
                                }
                            }                         
                            if (esConj) {
                                for(Conjunto conj : conjuntos) {
                                    if (terminal.equals(conj.getNombre())) {
                                        // Buscar caracter en el intervalo del conjunto
                                        int caracterInt = (int) caracter.charAt(0);
                                        for (int k=0; k < conj.getElementos().size(); k++) {
                                            if (caracterInt == conj.getElementos().get(k)) {
                                                // Completar transición
                                                estadoActual = estadoDestino;
                                                valido = true;
                                                break;
                                            } else {
                                                valido = false;
                                            }
                                        }
                                        break;
                                    }
                                }
                            }
                            // Caracter especial
                            else if (terminal.equals("\\\"") || terminal.equals("\\\'") || terminal.equals("\\n")) {
                                
                                switch(terminal) {
                                    case "\\\"":
                                        if (caracter.equals("\"")) { valido = true; } 
                                        else { valido = false; }
                                        break;
                                    case "\\\'":
                                        if (caracter.equals("\'")) { valido = true; } 
                                        else { valido = false; }
                                        break;
                                    case "\\n":
                                        if (caracter.equals("\n")) { valido = true; } 
                                        else { valido = false; }
                                        break;
                                }
                                
                                if (caracter.equals(terminal)) {
                                    // Completar transición
                                    estadoActual = estadoDestino;
                                    valido = true;
                                } else {
                                    valido = false;
                                }
                            }
                            // Cadenas de texto
                            else {
                                int totalCadena = terminal.length();
                                String cadenaValidar = "";
                                for (int j=i; j<(i+totalCadena); j++) {
                                    cadenaValidar += entradaSplit[j];
                                    
                                    if (cadenaValidar.equals(terminal)) {
                                        // Completar transición
                                        estadoActual = estadoDestino;
                                        valido = true;
                                        i = j;
                                        break;
                                    }
                                }
                                
                                if (!cadenaValidar.equals(terminal)) {
                                    valido = false;      
                                }
                            }      
                            if (valido) { break; }
                        }
                        break;
                    }               
                }
            } else {
                break;
            }           
        } 
        
        String jsonResultado = "\n\t{\n";
        jsonResultado += "\t\t\"Valor\": \""+entrada+"\",\n";
        jsonResultado += "\t\t\"ExpresionRegular\": \""+nombreExpresion+"\",\n";
        
        if (valido) {
            boolean estadoAceptacion = false;
            // Comprobar si es de aceptacion el estado actual
            for(Estado estado: listaTransiciones) {
                if (estado.valor.equals(estadoActual)) {
                    estadoAceptacion = estado.isAceptacion();
                    break;
                }
            }     
            // Cadena valida armar json
            if (estadoAceptacion) {
                jsonResultado += "\t\t\"Resultado\": \"Cadena Válida\"\n";
            } else {
                jsonResultado += "\t\t\"Resultado\": \"Cadena Inválida\"\n";
            }
        } else {
            jsonResultado += "\t\t\"Resultado\": \"Cadena Inválida\"\n";
        }
        
        jsonResultado += "\t}";
         
        return jsonResultado;
    }
    
    public void generarTablaEstados() {
        int contEstados = 0;
        Iterator it = tablaEstados.keySet().iterator();
        
        while(it.hasNext()) {
            Set key = (HashSet) it.next();
            Integer estadoAceptacion = this.getRaizArbol().getDerecho().getNumeroHoja();
            
            
            String nombreEstado = "S" + contEstados;
            Set siguientes = key;
            boolean aceptacion = false;
            ArrayList<Transicion> transicionesEstado = new ArrayList<>();
            
            // Si es igual a estado de aceptacion
            if(key.contains(estadoAceptacion)) {              
                aceptacion = true;               
            }
            
            // Transiciones
            if (this.getTablaEstados().get(key).size() > 0) {
                ArrayList<Transicion> listaTransiciones = new ArrayList<>();
                listaTransiciones.addAll(this.getTablaEstados().get(key));
                
                for(Transicion transicion: listaTransiciones) {
                    if (transicion.getEstadoDestino().size() > 0) {
                        transicionesEstado.add(transicion);
                    }
                }
            }
            
            Estado nuevoEstado = new Estado(nombreEstado, siguientes, aceptacion, transicionesEstado);          
            contEstados++;
            listaTransiciones.add(nuevoEstado);
        }
 
        // Renombrar
        for (Estado estado: listaTransiciones) {
            for (Transicion transicion: estado.transiciones) {
                if (transicion.nombreEstadoDestino == null) {                 
                    for (Estado estadoBuscar: listaTransiciones) {     
                        if (transicion.estadoDestino.equals(estadoBuscar.siguientes)) {
                            transicion.nombreEstadoDestino = estadoBuscar.getValor();
                            break;
                        }
                    }
                }
            }
        }
    }
    
    public Map<Set<Integer>, Set<Transicion>> generarEstados() {
        this.tablaEstados = new HashMap<Set<Integer>, Set<Transicion>>();
        
        agregarEstado(raizArbol.getPrimeros(), listaSiguientes);
        
        return tablaEstados;
    }
      
    public void agregarEstado(ArrayList<Integer> estados, ArrayList<Siguiente> listaSiguientes) {
        ArrayList<String> listaHojas = new ArrayList<>();
        Set listaTemporal = new HashSet<>();
        
        for(Integer estado: estados) {
            // Buscar valor hoja
            for (Siguiente siguiente: listaSiguientes) {
                if (siguiente.getNumeroHoja() == estado) {
                    listaHojas.add(siguiente.getValorHoja());
                }
            }
        }
        // Quitar repetidos
        listaTemporal.addAll(listaHojas);
        listaHojas.clear();
        listaHojas.addAll(listaTemporal);
        listaTemporal.clear();
        Collections.sort(listaHojas);
        
        Set listaTransicion = new HashSet<>();
        for(String hoja: listaHojas) {
            listaTransicion.add(new Transicion(hoja));
        }
        
        for(Integer estado: estados) {
            String valorHoja = "";
            // Buscar valor hoja
            for (Siguiente siguiente: listaSiguientes) {
                if (siguiente.getNumeroHoja() == estado) {
                    valorHoja = siguiente.getValorHoja();
                }
            }
                
            ArrayList<Transicion> listaTransicionHoja = new ArrayList<>();
            listaTransicionHoja.addAll(listaTransicion);
            
            for (Transicion transicion: listaTransicionHoja) {
                if (transicion.getValorHoja().equals(valorHoja)) {                  
                    // Obtener siguientes
                    for (Siguiente siguiente: listaSiguientes) {
                        if (siguiente.getNumeroHoja() == estado) {
                            transicion.getEstadoDestino().addAll(siguiente.getSiguientes());
                            break;
                        }
                    }
                }
            }
            
            listaTransicion.clear();
            listaTransicion.addAll(listaTransicionHoja);
        }
        
        // Parte final
        listaTemporal.addAll(estados);
        this.tablaEstados.put(listaTemporal, listaTransicion);
        
        ArrayList<Transicion> listaTransicionesHojas = new ArrayList<>();
        listaTransicionesHojas.addAll(listaTransicion);
        
        for(Transicion transicion: listaTransicionesHojas) {
            Set estadoPendiente = transicion.getEstadoDestino();
            
            Iterator it = this.tablaEstados.keySet().iterator();
            Boolean ubicado = false;
        
            while(it.hasNext()) {
                Set key = (HashSet) it.next();
                if (key.containsAll(estadoPendiente) && key.size() == estadoPendiente.size()) {
                    ubicado = true;
                }
            }
            
            if (!ubicado && estadoPendiente.size() != 0) {
                ArrayList<Integer> estadoPen = new ArrayList<>();
                estadoPen.addAll(estadoPendiente);
                agregarEstado(estadoPen, listaSiguientes);
            }
        } 
    }
   
    public String getNombreExpresion() {
        return nombreExpresion;
    }

    public void setNombreExpresion(String nombreExpresion) {
        this.nombreExpresion = nombreExpresion;
    }

    public Nodo getRaizArbol() {
        return raizArbol;
    }

    public void setRaizArbol(Nodo raizArbol) {
        this.raizArbol = raizArbol;
    }

    public ArrayList<Siguiente> getListaSiguientes() {
        return listaSiguientes;
    }

    public void setListaSiguientes(ArrayList<Siguiente> listaSiguientes) {
        this.listaSiguientes = listaSiguientes;
    }

    public Map<Set<Integer>, Set<Transicion>> getTablaEstados() {
        return tablaEstados;
    }

    public void setTablaEstados(Map<Set<Integer>, Set<Transicion>> tablaEstados) {
        this.tablaEstados = tablaEstados;
    }

    public ArrayList<Estado> getListaTransiciones() {
        return listaTransiciones;
    }

    public void setListaTransiciones(ArrayList<Estado> listaTransiciones) {
        this.listaTransiciones = listaTransiciones;
    }
    
}
