package cine.vista;

import cine.modelo.Butaca;
import cine.modelo.Cliente;
import cine.modelo.Sala;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Vista para seleccionar butacas y comprar entrada
 */
public class CompraView {
    
    private Stage stage;
    private Cliente cliente;
    private Sala sala;
    private Label lblTitulo;
    private Label lblInfo;
    private GridPane gridButacas;
    private Button botonComprar;
    private Button botonVolver;
    private Button butacaSeleccionada = null;
    private int filaSeleccionada = -1;
    private int columnaSeleccionada = -1;
    
    public CompraView(Stage stage, Cliente cliente, Sala sala) {
        this.stage = stage;
        this.cliente = cliente;
        this.sala = sala;
        inicializar();
    }
    
    private void inicializar() {
        // Título
        lblTitulo = new Label("🎬 " + sala.getPelicula() + " - Sala " + sala.getNumero());
        lblTitulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        
        // Información
        lblInfo = new Label("Seleccione su butaca");
        lblInfo.setStyle("-fx-font-size: 14px; -fx-text-fill: #666;");
        
        // Pantalla del cine
        Label lblPantalla = new Label("🎥 PANTALLA 🎥");
        lblPantalla.setStyle("-fx-font-size: 16px; -fx-background-color: #333; -fx-text-fill: white; -fx-padding: 10;");
        lblPantalla.setMaxWidth(Double.MAX_VALUE);
        lblPantalla.setAlignment(Pos.CENTER);
        
        gridButacas = new GridPane();
        gridButacas.setHgap(5);
        gridButacas.setVgap(5);
        gridButacas.setAlignment(Pos.CENTER);
        gridButacas.setPadding(new Insets(20));
        
        crearButacas();
        
        // Leyenda
        HBox leyenda = crearLeyenda();
        
        // Botones
        botonComprar = new Button("Comprar Entrada - $2500");
        botonComprar.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px;");
        botonComprar.setPrefWidth(200);
        botonComprar.setDisable(true);
        
        botonVolver = new Button("Volver");
        botonVolver.setStyle("-fx-background-color: #757575; -fx-text-fill: white; -fx-font-size: 14px;");
        botonVolver.setPrefWidth(150);
        
        HBox hboxBotones = new HBox(10);
        hboxBotones.setAlignment(Pos.CENTER);
        hboxBotones.getChildren().addAll(botonComprar, botonVolver);
        
        VBox layout = new VBox(15);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(
            lblTitulo,
            lblInfo,
            lblPantalla,
            gridButacas,
            leyenda,
            hboxBotones
        );
        
        Scene scene = new Scene(layout, 700, 650);
        stage.setScene(scene);
        stage.setTitle("Seleccionar Butaca - Sistema de Cine");
    }
    
    private void crearButacas() {
        Butaca[][] butacas = sala.getButacas();
        String[] letras = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        
        // Encabezado con números de columna
        for (int j = 0; j < sala.getColumnas(); j++) {
            Label lblColumna = new Label(String.valueOf(j + 1));
            lblColumna.setStyle("-fx-font-weight: bold;");
            gridButacas.add(lblColumna, j + 1, 0);
        }
        
        // Crear botones de butacas
        for (int i = 0; i < sala.getFilas(); i++) {
            // Etiqueta de fila
            Label lblFila = new Label(letras[i]);
            lblFila.setStyle("-fx-font-weight: bold;");
            gridButacas.add(lblFila, 0, i + 1);
            
            for (int j = 0; j < sala.getColumnas(); j++) {
                Butaca butaca = butacas[i][j];
                Button btnButaca = new Button(letras[i] + (j + 1));
                btnButaca.setPrefSize(50, 40);
                
                final int fila = i;
                final int columna = j;
                
                if (butaca.isOcupada()) {
                    btnButaca.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");
                    btnButaca.setDisable(true);
                } else {
                    btnButaca.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
                    btnButaca.setOnAction(e -> seleccionarButaca(btnButaca, fila, columna));
                }
                
                gridButacas.add(btnButaca, j + 1, i + 1);
            }
        }
    }
    
    private void seleccionarButaca(Button boton, int fila, int columna) {
        // Deseleccionar butaca anterior
        if (butacaSeleccionada != null && butacaSeleccionada != boton) {
            butacaSeleccionada.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        }
        
        // Seleccionar nueva butaca
        butacaSeleccionada = boton;
        filaSeleccionada = fila;
        columnaSeleccionada = columna;
        
        boton.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white; -fx-border-color: black; -fx-border-width: 2;");
        
        Butaca butaca = sala.getButaca(fila, columna);
        lblInfo.setText("Butaca seleccionada: " + butaca.getIdentificador() + " - Precio: $2500");
        botonComprar.setDisable(false);
    }
    
    private HBox crearLeyenda() {
        HBox leyenda = new HBox(20);
        leyenda.setAlignment(Pos.CENTER);
        
        Button botonLibre = new Button("Libre");
        botonLibre.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        botonLibre.setDisable(true);
        
        Button botonOcupada = new Button("Ocupada");
        botonOcupada.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");
        botonOcupada.setDisable(true);
        
        Button botonSeleccionada = new Button("Seleccionada");
        botonSeleccionada.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white;");
        botonSeleccionada.setDisable(true);
        
        leyenda.getChildren().addAll(
            new Label("Leyenda:"),
            botonLibre,
            botonOcupada,
            botonSeleccionada
        );
        
        return leyenda;
    }
    
    // Getters
    public Button getBotonComprar() {
        return botonComprar;
    }
    
    public Button getBotonVolver() {
        return botonVolver;
    }
    
    public int getFilaSeleccionada() {
        return filaSeleccionada;
    }
    
    public int getColumnaSeleccionada() {
        return columnaSeleccionada;
    }
    
    public void actualizarButacas() {
        gridButacas.getChildren().clear();
        crearButacas();
        butacaSeleccionada = null;
        filaSeleccionada = -1;
        columnaSeleccionada = -1;
        botonComprar.setDisable(true);
        lblInfo.setText("Seleccione su butaca");
    }
}