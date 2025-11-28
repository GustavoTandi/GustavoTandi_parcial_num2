package cine.controlador;

import cine.modelo.Cine;
import cine.modelo.Cliente;
import cine.modelo.Entrada;
import cine.modelo.Sala;
import cine.persistencia.PersistenciaDatos;
import cine.vista.SalaView;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.util.List;

public class SalaController {
    
    private SalaView vista;
    private Cine cine;
    private Cliente cliente;
    private Stage stage;
    
    public SalaController(Stage stage, Cine cine, Cliente cliente) {
        this.stage = stage;
        this.cine = cine;
        this.cliente = cliente;
        this.vista = new SalaView(stage, cliente);
        
        configurarEventos();
        cargarSalas();
    }

    private void configurarEventos() {
        // Botón para ver butacas de la sala seleccionada
        vista.getBotonSeleccionar().setOnAction(e -> verButacas());
        
        // Botón para ver las entradas compradas
        vista.getBotonMisEntradas().setOnAction(e -> mostrarMisEntradas());
        
        // Botón de cerrar sesión
        vista.getBotonCerrarSesion().setOnAction(e -> cerrarSesion());
    }

    private void cargarSalas() {
        vista.cargarSalas(cine.getSalas());
    }

    private void verButacas() {
        Sala salaSeleccionada = vista.getSalaSeleccionada();
        
        // Validar que se haya seleccionado una sala
        if (salaSeleccionada == null) {
            mostrarAlerta("Error", "Por favor seleccione una sala", Alert.AlertType.WARNING);
            return;
        }
        
        // Navegar a la vista de compra pasando el mismo stage
        CompraController compraController = new CompraController(
            stage, 
            cine, 
            cliente, 
            salaSeleccionada, 
            this  // Pasamos referencia a este controlador para poder volver
        );
    }
    
    private void mostrarMisEntradas() {
        List<Entrada> entradas = cine.getEntradasDeCliente(cliente);
        
        if (entradas.isEmpty()) {
            mostrarAlerta(
                "Mis Entradas", 
                "No tienes entradas compradas aún", 
                Alert.AlertType.INFORMATION
            );
            return;
        }
        
        // Construir el texto con todas las entradas
        StringBuilder sb = new StringBuilder();
        sb.append("Tus entradas compradas:\n\n");
        
        for (Entrada entrada : entradas) {
            sb.append(entrada.toString());
            sb.append("\n═══════════════════════════════\n\n");
        }
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mis Entradas");
        alert.setHeaderText("Total de entradas: " + entradas.size());
        
        // Crear TextArea para mostrar todas las entradas
        TextArea textArea = new TextArea(sb.toString());
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setPrefSize(450, 400);
        
        alert.getDialogPane().setContent(textArea);
        alert.showAndWait();
    }
    
    private void cerrarSesion() {
        PersistenciaDatos.guardar(cine);
        
        LoginController loginController = new LoginController(stage, cine);
        
        System.out.println("Sesión cerrada. Usuario: " + cliente.getNombre());
    }
    

    public void actualizarVista() {
        cargarSalas();
        vista.actualizarInfo();
        vista.mostrar();
    }
    
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}