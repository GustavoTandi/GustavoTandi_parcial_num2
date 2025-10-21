/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ExpedicionesEspaciales;

/**
 *
 * @author Usuario
 */
import ExpedicionesEspaciales.Naves.*;
import ExpedicionesEspaciales.Nave;
import java.util.Scanner;
import java.util.LinkedHashSet;
import java.util.Set;

public class AgenciaEspacial {
    //Mantiene el orden y evita duplicados
    private final Set<Nave> naves = new LinkedHashSet<>();
    private final Scanner sc = new Scanner(System.in);
    
    public void iniciarMenu(){
        int opcion;
        do{
            System.out.println("Seleccione una opcion");
            System.out.println("1. Agregar nave");
            System.out.println("2. Mostrar naves");
            System.out.println("3.Iniciar exploracion");
            System.out.println("0. Salir");
            opcion = sc.nextInt();
            sc.nextLine();
            
            switch (opcion){
                case 1 -> agregarNave();
                case 2 -> mostrarNaves();
                case 3 -> iniciarExploracion();
                case 0 -> System.out.println("Saliste del sistema");
                default -> System.out.println("Opción inválida");
            }
        }while (opcion != 0);
    }
    
    public void agregarNave(){
        System.out.println("Seleccione el tipo de nave:");
        System.out.println("1. Nave de exploracion");
        System.out.println("2. Carguero");
        System.out.println("3. Crucero estelar");
        int tipo = sc.nextInt();
        sc.nextLine();
        
        System.out.println("Nombre: ");
        String nombre = sc.nextLine();
        
        System.out.println("Capacidad de tripulacion: ");
        int tripulacion = sc.nextInt();
        
        System.out.println("Anio de lanzamiento: ");
        int anio = sc.nextInt();
        sc.nextLine();
        
        Nave nuevaNave = null;
        
        try {
            switch (tipo) {
                case 1 ->{
                    System.out.println("Mision disponible:");
                    for (TipoMision m : TipoMision.values()){
                        System.out.println("-" + m);
                    }
                    System.out.println("Ingrese misión:");
                    String misionStr = sc.nextLine().toUpperCase();
                    TipoMision tipoMision = TipoMision.valueOf(misionStr);
                    nuevaNave = new NaveExploracion(nombre, tripulacion, anio, tipoMision);
                }
                case 2 ->{
                    System.out.println("Capacidad (100-500)");
                    int carga = sc.nextInt();
                    nuevaNave = new Carguero(nombre, tripulacion, anio, carga);
                }
                case 3 ->{
                    System.out.println("Cantidad de pasajeros: ");
                    int pasajeros = sc.nextInt();
                    nuevaNave = new CruceroEstelar(nombre, tripulacion, anio, pasajeros);
                }
                default -> System.out.println("Tipo no válido");
            }
            
            if (nuevaNave != null){
                //Para detectar duplicados
                if (naves.contains(nuevaNave)){
                    throw new NaveDuplicadaException(nuevaNave.getNombre(), nuevaNave.getAnioLanzamiento());
                }
                naves.add(nuevaNave);
                System.out.println("La nave ha sido agregada: " + nuevaNave.getNombre());
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (NaveDuplicadaException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void mostrarNaves(){
        System.out.println("\n Naves registradas");
        if (naves.isEmpty()){
            System.out.println("No hay naves resgistradas");
            return;
        }
        for (Nave n : naves){
            System.out.println(n.toString());
        }
    }
    
    public void iniciarExploracion(){
        System.out.println("\n Iniciando exploracion para naves habilitadas ");
        if(naves.isEmpty()){
            System.out.println("No hay naves");
            return;
        }
        
        for (Nave n : naves){
            //Verifica si la nave implementa la interfaz Explorador
            if (n instanceof Explorador){
                ((Explorador)n).explorar();
            }else{
                System.out.println("El crucero " + n.getNombre() + " solo transporta pasajeros.");
            }
        }
    }
}
