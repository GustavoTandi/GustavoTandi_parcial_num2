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
public class CruceroEstelar extends Nave {
    private final int cantidadPasajeros;

    public CruceroEstelar(String nombre, int capacidadTripulacion, int anioLanzamiento, int cantidadPasajeros) {
        super(nombre, capacidadTripulacion, anioLanzamiento);
        if (cantidadPasajeros < 0) throw new IllegalArgumentException("La cantidad de pasajeros no puede ser negativa.");
        this.cantidadPasajeros = cantidadPasajeros;
    }

    public int getCantidadPasajeros() {
        return cantidadPasajeros;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" - Pasajeros: %d", cantidadPasajeros);
    }
}
