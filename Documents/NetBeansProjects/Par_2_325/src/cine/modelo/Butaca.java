package cine.modelo;

import java.io.Serializable;

public class Butaca implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int numero;
    private String fila;
    private boolean ocupada;
    
    // Constructor vacío
    public Butaca() {
    }
    
    // Constructor con parámetros
    public Butaca(int numero, String fila) {
        this.numero = numero;
        this.fila = fila;
        this.ocupada = false;
    }
    
    // Getters y Setters
    public int getNumero() {
        return numero;
    }
    
    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    public String getFila() {
        return fila;
    }
    
    public void setFila(String fila) {
        this.fila = fila;
    }
    
    public boolean isOcupada() {
        return ocupada;
    }
    
    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }
    
    // Métodos de negocio
    public void ocupar() {
        this.ocupada = true;
    }
    
    public void liberar() {
        this.ocupada = false;
    }
    
    public String getIdentificador() {
        return fila + numero;
    }
    
    @Override
    public String toString() {
        return fila + numero + (ocupada ? " [X]" : " [ ]");
    }
}