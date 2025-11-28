package cine.vista;

import cine.modelo.Cliente;
import cine.modelo.Sala;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SalaView {
    
    private Stage stage;
    private Cliente clienteActual;
    private Label lblBienvenida;
    private ListView<Sala> listViewSalas;
    private Button botonSeleccionar;
    private Button botonMisEntradas;
    private Button botonCerrarSesion;
    private Label lblInfo;
    private Scene scene;
    
    public SalaView(Stage stage, Cliente cliente) {
        this.stage = stage;
        this.clienteActual = cliente;
        inicializar();
    }
    
    private void inicializar() {
        // Título principal
        Label lblTitulo = new Label("Seleccionar Película");
        lblTitulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
             
        lblBienvenida = new Label("Bienvenido/a: " + clienteActual.getNombre());
        lblBienvenida.setStyle("-fx-font-size: 14px; -fx-text-fill: #555;");
        
        Label lblSalas = new Label("Salas disponibles:");
        lblSalas.setStyle("-fx-font-weight: bold;");
        
        // ListView para mostrar las salas
        listViewSalas = new ListView<>();
        listViewSalas.setPrefHeight(300);
             
        // Información adicional sobre la sala seleccionada
        lblInfo = new Label("Seleccione una sala para ver las butacas disponibles");
        lblInfo.setStyle("-fx-text-fill: #666; -fx-font-style: italic;");
        
        // Botón para ver las butacas de la sala seleccionada
        botonSeleccionar = new Button("Ver Butacas");
        botonSeleccionar.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px;");
        botonSeleccionar.setPrefWidth(150);
        botonSeleccionar.setDisable(true);
        
        // Botón para ver las entradas compradas
        botonMisEntradas = new Button("Mis Entradas");
        botonMisEntradas.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white; -fx-font-size: 14px;");
        botonMisEntradas.setPrefWidth(150);
        
        botonCerrarSesion = new Button("Cerrar Sesión");
        botonCerrarSesion.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-size: 14px;");
        botonCerrarSesion.setPrefWidth(150);
        
        // Listener para habilitar el botón cuando se selecciona una sala
        listViewSalas.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            botonSeleccionar.setDisable(newVal == null);
            if (newVal != null) {
                lblInfo.setText("Sala: " + newVal.getPelicula() + " | Butacas libres: " + newVal.contarButacasLibres());
            }
        });
        
        HBox hboxBotones = new HBox(10);
        hboxBotones.setAlignment(Pos.CENTER);
        hboxBotones.getChildren().addAll(botonSeleccionar, botonMisEntradas, botonCerrarSesion);
        
        VBox layout = new VBox(15);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(
            lblTitulo,
            lblBienvenida,
            lblSalas,
            listViewSalas,
            lblInfo,
            hboxBotones
        );
        
        // Crear la escena
        this.scene = new Scene(layout, 500, 550);
        stage.setScene(scene);
        stage.setTitle("Seleccionar Sala - Sistema de Cine");
    }
    
    public void mostrar() {
        stage.setScene(this.scene);
        stage.setTitle("Seleccionar Sala - Sistema de Cine");
        stage.show();
    }
    
    public void cargarSalas(java.util.List<Sala> salas) {
        listViewSalas.getItems().clear();
        listViewSalas.getItems().addAll(salas);
    }
    
    public Sala getSalaSeleccionada() {
        return listViewSalas.getSelectionModel().getSelectedItem();
    }
    
    public void actualizarInfo() {
        Sala salaSeleccionada = getSalaSeleccionada();
        if (salaSeleccionada != null) {
            lblInfo.setText("Sala: " + salaSeleccionada.getPelicula() + " | Butacas libres: " + salaSeleccionada.contarButacasLibres());
        }
    }
    
    //GETTERS
    
    public Button getBotonSeleccionar() {
        return botonSeleccionar;
    }
    
    public Button getBotonMisEntradas() {
        return botonMisEntradas;
    }
    
    public Button getBotonCerrarSesion() {
        return botonCerrarSesion;
    }
    
    public Cliente getClienteActual() {
        return clienteActual;
    }
}