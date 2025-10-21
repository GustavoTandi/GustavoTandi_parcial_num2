/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ExpedicionesEspaciales.Naves;

import ExpedicionesEspaciales.Nave;
import ExpedicionesEspaciales.Explorador;
import ExpedicionesEspaciales.TipoMision;
/**
 *
 * @author Usuario
 */
public class NaveExploracion extends Nave implements Explorador {
    private final TipoMision tipoMision;
    
    public NaveExploracion(String nombre, int capacidadTripulacion, int anioLanzamiento, TipoMision tipoMision) {
        super(nombre, capacidadTripulacion, anioLanzamiento);
        if (tipoMision == null) throw new IllegalArgumentException("El tipo de misión no puedr ser nulo.");
        this.tipoMision = tipoMision;
    }
    
    public TipoMision getTipoMision(){
        return tipoMision;
    }
    
    @Override
    public void explorar(){
        System.out.println("La nave " +  getNombre() + " inicia misión de " + tipoMision + ".");              
    }
  
    @Override
    public String toString(){
        return super.toString() + " - Tipo de misión: " + tipoMision;
    }
}
