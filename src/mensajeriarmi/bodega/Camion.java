/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensajeriarmi.bodega;

import java.util.ArrayList;
import mensajeriarmi.paquete.Paquete;
import mensajeriarmi.paquete.Ubicacion;

/**
 * @author Carlos Andres Rojas
 * @author David Salgado Ospina
 */
public class Camion {
    
    public ArrayList<Paquete> paquetes;
    public double capacidadTotal; // Peso maximo
    public double capacidadActual;
    public Ubicacion destino;

    public Camion(double capcidadTotal, Ubicacion destino) {
        this.capacidadTotal = capcidadTotal;
        this.destino = destino;
    }
    
    public boolean agregarPaquete(Paquete paquete){
        double capacidad = this.capacidadActual + paquete.getPeso();
        if(capacidad <= this.capacidadTotal){
            this.paquetes.add(paquete);
            this.capacidadActual += paquete.getPeso();
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public double getPesoRestante(){
        return this.capacidadTotal - this.capacidadActual;
    }
    
    
}
