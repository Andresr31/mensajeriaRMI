/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensajeriarmi.bodega;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mensajeriarmi.georreferenciacion.GeorreferenciadorRMI;
import mensajeriarmi.georreferenciacion.Procesador;
import mensajeriarmi.paquete.Paquete;
import mensajeriarmi.paquete.Ubicacion;

/**
 *
 * @author Otro
 */
public class ProcesadorAlmacenamiento extends Thread implements Serializable {

    private ArrayList<Paquete> bufferPaquetes;
    private String procesoActual;
    private BodegaRMI bodega;
    
    private final int TIEMPO_ALMACENAMIENTO = 20000;

    public ProcesadorAlmacenamiento(BodegaRMI b) {
        
        this.bufferPaquetes = new ArrayList<>();
        this.procesoActual = "N/A";
        this.bodega = b;
        
    }
    
    public void iniciar(){
        this.start();
    }
    
    public void agregarPaquete(Paquete p){
        this.bufferPaquetes.add(p);
    }
    
    public void procesar(){
        int i = 0;
        for(;;){
            if(bufferPaquetes.size() >=1){
                System.out.println("A punto de almacenar un paquete");
                try {
                    Thread.sleep(TIEMPO_ALMACENAMIENTO);
                    Paquete p = bufferPaquetes.get(i);
                    System.out.println("Almacenando paquete de: "+p.getNombreEmisor()+ "Para: "+p.getCiudadReceptor());
                    this.bodega.almacenarPaquete(p);
                  
                    this.bufferPaquetes.remove(p);
                    System.out.println("Quedan "+this.bufferPaquetes.size()+" por georreferenciar");
                    System.out.println("");
                } catch (InterruptedException ex) {
                    Logger.getLogger(Procesador.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }else{
                System.getProperties();
            }
        }
        
    }
    

    @Override
    public void run() {
        procesar();
    }

}
