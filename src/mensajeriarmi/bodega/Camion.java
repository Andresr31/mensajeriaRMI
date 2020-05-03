/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensajeriarmi.bodega;

import java.io.Serializable;
import java.util.ArrayList;
import mensajeriarmi.paquete.Paquete;
import mensajeriarmi.paquete.Ubicacion;

/**
 * @author Carlos Andres Rojas
 * @author David Salgado Ospina
 */
public class Camion implements Serializable{
    
    private ArrayList<Paquete> paquetes;
    private double capacidadTotal; // Peso maximo
    private double capacidadActual;
    private Ubicacion destino;
    private int id;
    
    public static int autoincremento;

    public Camion(double capcidadTotal, Ubicacion destino) {
        this.capacidadTotal = capcidadTotal;
        this.destino = destino;
        this.paquetes = new ArrayList<>();
        Camion.autoincremento ++;
        this.id = Camion.autoincremento;
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

    public ArrayList<Paquete> getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(ArrayList<Paquete> paquetes) {
        this.paquetes = paquetes;
    }

    public double getCapacidadTotal() {
        return capacidadTotal;
    }

    public void setCapacidadTotal(double capacidadTotal) {
        this.capacidadTotal = capacidadTotal;
    }

    public double getCapacidadActual() {
        return capacidadActual;
    }

    public void setCapacidadActual(double capacidadActual) {
        this.capacidadActual = capacidadActual;
    }

    public Ubicacion getDestino() {
        return destino;
    }

    public void setDestino(Ubicacion destino) {
        this.destino = destino;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
