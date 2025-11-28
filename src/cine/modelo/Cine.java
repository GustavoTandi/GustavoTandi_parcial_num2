package cine.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// Clase principal que contiene las salas, clientes y entradas
public class Cine implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private List<Sala> salas;
    private List<Cliente> clientes;
    private List<Entrada> entradas;
    
    public Cine(){
        this.salas = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.entradas = new ArrayList<>();
        inicializarSalas();
    }
    
    //Inicializar salas
    private void inicializarSalas() {
        salas.add(new Sala(1, "Harry Potter y la piedra filosofal", 8, 10));
        salas.add(new Sala(2, "Dragon Ball Super: Super Hero",6, 8));
        salas.add(new Sala(3, "One Piece Red", 10, 12));
        salas.add(new Sala(4, "Avengers: Infinity War",7, 10));
    }
    
    public Cliente login(String email, String contrasena) {
    // Reutiliza el método buscarEmail que ya tienes
    Cliente cliente = buscarEmail(email); 

    // Valida mail y contraseña
    if (cliente != null && cliente.getContrasena().equals(contrasena)) {
        return cliente; 
    }
    
    return null; 
}
    
    //Getters y Setters
    public List<Sala> getSalas(){
        return salas;
    }
    
    public void setSalas(List<Sala> salas){
        this.salas = salas;
    }
    
    public List<Cliente> getCLientes(){
        return clientes;
    }
    
    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
    
    public List<Entrada> getEntradas(){
        return entradas;
    }
    
    public void setEntradas(List<Entrada> entradas){
        this.entradas = entradas;
    }
    
    public boolean registrarCliente(Cliente cliente) {
        // Verificar si el email ya esta registrado
        if (buscarEmail(cliente.getEmail()) != null) {
            return false;
        }
        clientes.add(cliente);
        return true;
    }
    
    public Cliente buscarEmail(String email) {
        for (Cliente c : clientes) {
            if (c.getEmail().equals(email)){
                return c;
            }
        }
        return null;
    }
    
    public Sala getNumeroSala(int numero) {
        for (Sala s : salas) {
            if(s.getNumero() == numero) {
                return s;
            }
        }
        return null;
    }
    
    public Entrada comprarEntrada(Cliente cliente, Sala sala, int fila, int columna) {
        Butaca butaca = sala.getButaca(fila, columna);
        
        if (butaca != null && !butaca.isOcupada()) {
            butaca.ocupar();
            Entrada entrada = new Entrada(cliente, sala, butaca, 1500.0);
            entradas.add(entrada);
            return entrada;
        }
        return null;
    }
    
    public List<Entrada> getEntradasDeCliente(Cliente cliente) {
        List<Entrada> entradasCliente = new ArrayList<>();
        for (Entrada e : entradas) {
            if(e.getCliente().getEmail().equals(cliente.getEmail())) {
                entradasCliente.add(e);
            }
        }
        return entradasCliente;
    }
    
    @Override
    public String toString() {
        return "Cine con " + salas.size() + " salas, " + 
                clientes.size() + " clientes registrados y " +
                entradas.size() + " entradas vendidas.";
    }
}
