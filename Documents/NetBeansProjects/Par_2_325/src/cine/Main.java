package cine;

import cine.controlador.LoginController;
import cine.modelo.Cine;
import cine.persistencia.PersistenciaDatos;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    
    private Cine cine;
   
    @Override
    public void start(Stage primaryStage) {
        System.out.println("INICIANDO...");
        
        cine = PersistenciaDatos.cargar();
        
        if (cine == null) {
            cine = new Cine();
        } else {
            System.out.println(cine);
        }
        
        // Configurar ventana principal
        primaryStage.setTitle("Sistema de Cine");
        primaryStage.setResizable(false);
        
        // Mostrar vista de login
        LoginController loginController = new LoginController(primaryStage, cine);
        
        // Mostrar ventana
        primaryStage.show();
        
        // Guardar datos al cerrar la aplicación
        primaryStage.setOnCloseRequest(e -> {
            PersistenciaDatos.guardar(cine);
            System.out.println("Guardado exitosamente.");
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
