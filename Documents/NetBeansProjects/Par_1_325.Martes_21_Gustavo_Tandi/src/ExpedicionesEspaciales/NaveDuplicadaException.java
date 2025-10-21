/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ExpedicionesEspaciales;

/**
 *
 * @author Usuario
 */
public class NaveDuplicadaException extends Exception {
    public NaveDuplicadaException(String nombre, int anio) {
        super(String.format("Ya existe una nave con nombre '%s' y año %d", nombre, anio));
    }
}
