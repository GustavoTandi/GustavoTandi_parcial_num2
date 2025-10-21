/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ExpedicionesEspaciales;
import java.util.Objects;

/**
 *
 * @author Usuario
 */
public abstract class Nave {
    private final String nombre;
    private final int capacidadTripulacion;
    private final int anioLanzamiento;
    
    public Nave(String nombre, int capacidadTripulacion, int anioLanzamiento) {
        if (nombre == null || nombre.trim().isEmpty()){
            throw new IllegalArgumentException("El nombre no puede ser vacío.");
        }
        if (capacidadTripulacion < 0){
            throw new IllegalArgumentException("La capacidad de tripulación no puede ser negativa");
        }
        this.nombre = nombre.trim();
        this.capacidadTripulacion = capacidadTripulacion;
        this.anioLanzamiento = anioLanzamiento;    
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public int getCapacidadTripulacion(){
        return capacidadTripulacion;
    }
    
    public int getAnioLanzamiento(){
        return anioLanzamiento;
    }
    
    @Override
    public String toString(){
        return String.format("%s - Tripulacion: %d - Año: %d", nombre,capacidadTripulacion, anioLanzamiento);
    }
    
    @Override
    public int hashCode(){
        return Objects.hash(nombre.toLowerCase(), anioLanzamiento);
    }
    
    //Compara naves por nombre
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Nave nave = (Nave) obj;
        return this.anioLanzamiento == nave.anioLanzamiento
               && this.nombre.equalsIgnoreCase(nave.nombre);
    }
}