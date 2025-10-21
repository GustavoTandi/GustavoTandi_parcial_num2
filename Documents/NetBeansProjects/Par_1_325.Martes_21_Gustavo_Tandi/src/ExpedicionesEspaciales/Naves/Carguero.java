/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ExpedicionesEspaciales.Naves;

import ExpedicionesEspaciales.Nave;
import ExpedicionesEspaciales.Explorador;

/**
 *
 * @author Usuario
 */
public class Carguero extends Nave implements Explorador {
    private final int capacidadCarga;
    
    public Carguero(String nombre, int capacidadTripulacion, int anioLanzamiento, int capacidadCarga){
        super(nombre, capacidadTripulacion, anioLanzamiento);
        if (capacidadCarga < 100 || capacidadCarga > 500){
            throw new IllegalArgumentException("Capacidad de carga debe estar entre 100 y 500 tonelasas.");
        }
        this.capacidadCarga = capacidadCarga;
    }
    
    public int getCapacidadCarga(){
        return capacidadCarga;
    }
    
    @Override
    public void explorar(){
        System.out.println("El carguero " + getNombre() + " inicia misión con " + capacidadCarga +" toneladas de carga.");
    }
    
    @Override
    public String toString(){
        return super.toString() + " - Capacidad de carga: " + capacidadCarga + " toneladas";
    }
}
