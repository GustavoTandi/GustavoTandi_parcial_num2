package cine.controlador;

import cine.modelo.Cine;
import cine.modelo.Cliente;
import cine.vista.LoginView;
import cine.vista.SalaView;
import javafx.stage.Stage;

public class LoginController {
    
    private LoginView vista;
    private Cine cine;
    private Stage stage;
    
    public LoginController(Stage stage, Cine cine) {
        this.stage = stage;
        this.cine = cine;
        this.vista = new LoginView(stage);
        
        configurarEventos();
    }
    
    private void configurarEventos() {
        // Botón de login
        vista.getBotonLogin().setOnAction(e -> login());
        
        // Boton de registro
        vista.getBotonRegistrar().setOnAction(e -> registrar());
        
        // Botón para cambiar modo
        vista.getBotonCambiarModo().setOnAction(e -> vista.cambiarModo());
    }
    
    private void login() {
        String email = vista.getEmail();
        String contrasena = vista.getContrasena();
        
        // Validaciones
        if (email.isEmpty() || contrasena.isEmpty()) {
            vista.mostrarMensaje("Por favor complete todos los campos", true);
            return;
        }
        
        // Intentar login
        Cliente cliente = cine.login(email, contrasena);
        
        if (cliente != null) {
            vista.mostrarMensaje("¡Bienvenido/a " + cliente.getNombre() + "!", false);
            
            SalaController salaController = new SalaController(stage, cine, cliente);
        } else {
            vista.mostrarMensaje("Email o contraseña incorrectos", true);
        }
    }
    
    private void registrar() {
        String nombre = vista.getNombre();
        String email = vista.getEmail();
        String contrasena = vista.getContrasena();
        
        // Validaciones
        if (nombre.isEmpty() || email.isEmpty() || contrasena.isEmpty()) {
            vista.mostrarMensaje("Por favor complete todos los campos", true);
            return;
        }
        
        if (!email.contains("@")) {
            vista.mostrarMensaje("Email inválido", true);
            return;
        }
        
        if (contrasena.length() < 4) {
            vista.mostrarMensaje("La contraseña debe tener al menos 4 caracteres", true);
            return;
        }
        
        // Crear cliente
        Cliente nuevoCliente = new Cliente(nombre, email, contrasena);
        
        if (cine.registrarCliente(nuevoCliente)) {
            vista.mostrarMensaje("Registro exitoso", false);
            
            // Cambiar a login
            vista.cambiarModo();
        } else {
            vista.mostrarMensaje("El email ya está registrado", true);
        }
    }
}