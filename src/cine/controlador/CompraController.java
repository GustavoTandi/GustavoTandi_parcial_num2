package cine.controlador;

import cine.modelo.Cine;
import cine.modelo.Cliente;
import cine.modelo.Entrada;
import cine.modelo.Sala;
import cine.persistencia.PersistenciaDatos;
import cine.vista.CompraView;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import java.util.Optional;

public class CompraController {
    
    private CompraView vista;
    private Cine cine;
    private Cliente cliente;
    private Sala sala;
    private Stage stage;
    private SalaController salaController;
    
    public CompraController(Stage stage, Cine cine, Cliente cliente, Sala sala, SalaController salaController) {
        this.stage = stage;
        this.cine = cine;
        this.cliente = cliente;
        this.sala = sala;
        this.salaController = salaController;
        this.vista = new CompraView(stage, cliente, sala);
        
        configurarEventos();
    }
    
    private void configurarEventos() {
        // Botón de comprar
        vista.getBotonComprar().setOnAction(e -> comprarEntrada());
        
        // Botón de volver
        vista.getBotonVolver().setOnAction(e -> volver());
    }
    
    private void comprarEntrada() {
        int fila = vista.getFilaSeleccionada();
        int columna = vista.getColumnaSeleccionada();
        
        if (fila < 0 || columna < 0) {
            mostrarAlerta("Error", "Por favor seleccione una butaca", Alert.AlertType.WARNING);
            return;
        }
        
        // Confirmar compra
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar Compra");
        confirmacion.setHeaderText("¿Confirmar compra de entrada?");
        confirmacion.setContentText("Película: " + sala.getPelicula() + "\n" +
                                   "Sala: " + sala.getNumero() + "\n" +
                                   "Butaca: " + sala.getButaca(fila, columna).getIdentificador() + "\n" +
                                   "Precio: $2500");
        
        Optional<ButtonType> resultado = confirmacion.showAndWait();
        
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            // Realizar la compra
            Entrada entrada = cine.comprarEntrada(cliente, sala, fila, columna);
            
            if (entrada != null) {
                PersistenciaDatos.guardar(cine);
                
                mostrarTicket(entrada);
                
                vista.actualizarButacas();
            } else {
                mostrarAlerta("Error", "No se pudo completar la compra. La butaca puede estar ocupada.", Alert.AlertType.ERROR);
            }
        }
    }
   
    private void mostrarTicket(Entrada entrada) {
        Alert ticket = new Alert(Alert.AlertType.INFORMATION);
        ticket.setTitle("Compra Exitosa");
        ticket.setHeaderText("¡Entrada comprada exitosamente! 🎉");
        ticket.setContentText(entrada.toString() + "\n\n¡Disfruta tu película!");
        ticket.showAndWait();
    }
    
    private void volver() {
        // Actualizar la vista de salas antes de volver
        salaController.actualizarVista();
    }
    
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}