package cine.persistencia;

import cine.modelo.Cine;
import java.io.*;

//Clase para guardar y cargar el estado del cine
public class PersistenciaDatos {
    private static final String ARCHIVO = "cine.ser";
        
    public static boolean guardar(Cine cine) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(ARCHIVO))) {
            oos.writeObject(cine);
            System.out.println("Datos guardados exitosamente en " + ARCHIVO);
            return true;
        }catch (IOException e) {
            System.err.println("Error al guardar datos: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    public static Cine cargar() {
        File archivo = new File(ARCHIVO);
        
        // retorna null si el archivo no existe
        if (!archivo.exists()) {
            System.out.println("No hay ningún archivo, se creará uno nuevo.");
            return null;
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(archivo))) {
            Cine cine = (Cine) ois.readObject();
            System.out.println("Se cargaron los datos desde " + ARCHIVO);
            return cine;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar datos: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    public static boolean existeArchivo() {
        return new File(ARCHIVO).exists();
    }
    
    public static boolean eliminarArchivo() {
        File archivo = new File(ARCHIVO);
        if (archivo.exists()) {
            return archivo.delete();
        }
        return false;
    }    
}
