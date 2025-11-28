package cine.modelo;

import java.io.Serializable;

public class Sala implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int numero;
    private String pelicula;
    private String rutaImagen;
    private Butaca[][] butacas;
    private int filas;
    private int columnas;
    
    // Constructor vacíp
    public Sala() {
    }
    
    // Constructor con parametros
    public Sala(int numero, String pelicula, int filas, int columnas) {
        this.numero = numero;
        this.pelicula = pelicula;
        this.filas = filas;
        this.columnas = columnas;
        inicializarButacas();
    }
    
    private void inicializarButacas() {
        butacas = new Butaca[filas][columnas];
        String[] letrasFilas = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                butacas[i][j] = new Butaca(j + 1, letrasFilas[i]);
            }
        }
    }
    
    // Getters y Setters
    public int getNumero() {
        return numero;
    }
    
    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    public String getPelicula() {
        return pelicula;
    }
    
    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }
    
    public String getRutaImagen() {
        return rutaImagen;
    }
    
    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }
    
    public Butaca[][] getButacas() {
        return butacas;
    }
    
    public void setButacas(Butaca[][] butacas) {
        this.butacas = butacas;
    }
    
    public int getFilas() {
        return filas;
    }
    
    public int getColumnas() {
        return columnas;
    }
    
    public Butaca getButaca(int fila, int columna) {
        if (fila >= 0 && fila < filas && columna >= 0 && columna < columnas) {
            return butacas[fila][columna];
        }
        return null;
    }
    
    public boolean ocuparButaca(int fila, int columna) {
        Butaca butaca = getButaca(fila, columna);
        if (butaca != null && !butaca.isOcupada()) {
            butaca.ocupar();
            return true;
        }
        return false;
    }
    
    public int contarButacasLibres() {
        int libres = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (!butacas[i][j].isOcupada()) {
                    libres++;
                }
            }
        }
        return libres;
    }
    
    public int contarButacasOcupadas() {
        return (filas * columnas) - contarButacasLibres();
    }
    
    @Override
    public String toString() {
        return "Sala " + numero + " - " + pelicula + 
               " (Libres: " + contarButacasLibres() + "/" + (filas * columnas) + ")";
    }
}