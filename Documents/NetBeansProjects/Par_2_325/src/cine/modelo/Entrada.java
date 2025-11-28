package cine.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Entrada implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private static int contadorId = 1;
    
    private int id;
    private Cliente cliente;
    private Sala sala;
    private Butaca butaca;
    private LocalDateTime fechaCompra;
    private double precio;
    
    // Constructor vacío
    public Entrada() {
    }
    
    // Constructor con parámetros
    public Entrada(Cliente cliente, Sala sala, Butaca butaca, double precio) {
        this.id = contadorId++;
        this.cliente = cliente;
        this.sala = sala;
        this.butaca = butaca;
        this.fechaCompra = LocalDateTime.now();
        this.precio = precio;
    }
    
    // Getters y Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public Sala getSala() {
        return sala;
    }
    
    public void setSala(Sala sala) {
        this.sala = sala;
    }
    
    public Butaca getButaca() {
        return butaca;
    }
    
    public void setButaca(Butaca butaca) {
        this.butaca = butaca;
    }
    
    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }
    
    public void setFechaCompra(LocalDateTime fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
    
    public double getPrecio() {
        return precio;
    }
    
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public static void setContadorId(int contador) {
        contadorId = contador;
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "Entrada #" + id + "\n" +
               "Cliente: " + cliente.getNombre() + "\n" +
               "Película: " + sala.getPelicula() + " (Sala " + sala.getNumero() + ")\n" +
               "Butaca: " + butaca.getIdentificador() + "\n" +
               "Fecha: " + fechaCompra.format(formatter) + "\n" +
               "Precio: $" + precio;
    }
}