package cine.vista;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView {
    
    private Stage stage;
    private TextField txtEmail;
    private PasswordField txtContrasena;
    private TextField txtNombre;
    private Button botonLogin;
    private Button botonRegistrar;
    private Button botonCambiarModo;
    private Label lblMensaje;
    private VBox panelRegistro;
    private boolean modoRegistro = false;
    
    public LoginView(Stage stage) {
        this.stage = stage;
        inicializar();
    }
    
    private void inicializar() {
        // Título
        Label lblTitulo = new Label("🎬 Sistema de Cine");
        lblTitulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        
        // Campos de login
        Label lblEmail = new Label("Email:");
        txtEmail = new TextField();
        txtEmail.setPromptText("ejemplo@correo.com");
        
        Label lblContrasena = new Label("Contraseña:");
        txtContrasena = new PasswordField();
        txtContrasena.setPromptText("Ingrese su contraseña");
        
        // Campo de nombre
        Label lblNombre = new Label("Nombre completo:");
        txtNombre = new TextField();
        txtNombre.setPromptText("Juan Pérez");
        
        panelRegistro = new VBox(10);
        panelRegistro.getChildren().addAll(lblNombre, txtNombre);
        panelRegistro.setVisible(false);
        panelRegistro.setManaged(false);
        
        // Botones
        botonLogin = new Button("Iniciar Sesión");
        botonLogin.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px;");
        botonLogin.setPrefWidth(200);
        
        botonRegistrar = new Button("Registrarse");
        botonRegistrar.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-size: 14px;");
        botonRegistrar.setPrefWidth(200);
        botonRegistrar.setVisible(false);
        botonRegistrar.setManaged(false);
        
        botonCambiarModo = new Button("¿No tienes cuenta? Regístrate");
        botonCambiarModo.setStyle("-fx-background-color: transparent; -fx-text-fill: #2196F3; -fx-underline: true;");
        
        // Mensaje de error/éxito
        lblMensaje = new Label("");
        lblMensaje.setStyle("-fx-text-fill: red;");
        
        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));
        layout.getChildren().addAll(
            lblTitulo,
            lblEmail, txtEmail,
            lblContrasena, txtContrasena,
            panelRegistro,
            botonLogin,
            botonRegistrar,
            botonCambiarModo,
            lblMensaje
        );
        
        Scene scene = new Scene(layout, 400, 500);
        stage.setScene(scene);
        stage.setTitle("Login - Sistema de Cine");
    }
    
    public void cambiarModo() {
        modoRegistro = !modoRegistro;
        
        if (modoRegistro) {
            panelRegistro.setVisible(true);
            panelRegistro.setManaged(true);
            botonLogin.setVisible(false);
            botonLogin.setManaged(false);
            botonRegistrar.setVisible(true);
            botonRegistrar.setManaged(true);
            botonCambiarModo.setText("¿Ya tienes cuenta? Inicia sesión");
        } else {
            panelRegistro.setVisible(false);
            panelRegistro.setManaged(false);
            botonLogin.setVisible(true);
            botonLogin.setManaged(true);
            botonRegistrar.setVisible(false);
            botonRegistrar.setManaged(false);
            botonCambiarModo.setText("¿No tienes cuenta? Regístrate");
        }
        
        limpiarCampos();
    }
    
    public void mostrarMensaje(String mensaje, boolean esError) {
        lblMensaje.setText(mensaje);
        lblMensaje.setStyle(esError ? "-fx-text-fill: red;" : "-fx-text-fill: green;");
    }
    
    public void limpiarCampos() {
        txtEmail.clear();
        txtContrasena.clear();
        txtNombre.clear();
        lblMensaje.setText("");
    }
    
    // Getters
    public String getEmail() {
        return txtEmail.getText().trim();
    }
    
    public String getContrasena() {
        return txtContrasena.getText();
    }
    
    public String getNombre() {
        return txtNombre.getText().trim();
    }
    
    public Button getBotonLogin() {
        return botonLogin;
    }
    
    public Button getBotonRegistrar() {
        return botonRegistrar;
    }
    
    public Button getBotonCambiarModo() {
        return botonCambiarModo;
    }
    
    public boolean isModoRegistro() {
        return modoRegistro;
    }
}