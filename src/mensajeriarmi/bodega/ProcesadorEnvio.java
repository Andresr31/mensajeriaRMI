/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensajeriarmi.bodega;

import java.io.Serializable;
import java.util.ArrayList;
import mensajeriarmi.paquete.Ubicacion;

/**
 *
 * @author Otro
 */
public class ProcesadorEnvio extends Thread implements Serializable {

    private ArrayList<Camion> bufferCamiones;
    private String procesoActual;
    private BodegaRMI bodega;
    
    private final int TIEMPO_ALMACENAMIENTO = 30000;

    public ProcesadorEnvio(BodegaRMI b) {
        
        this.bufferCamiones = new ArrayList<>();
        this.procesoActual = "N/A";
        this.bodega = b;
        
    }
    
    public void iniciar(){
        this.start();
    }
    
    public void agregarEnvios (Ubicacion u, double pesoTotal){
        Camion c = new Camion(pesoTotal, u);
        this.bufferCamiones.add(c);
    }
    
    public void procesar(){
        System.out.println("--------------Hola mundo");
    }

    @Override
    public void run() {
        procesar();
    }

}