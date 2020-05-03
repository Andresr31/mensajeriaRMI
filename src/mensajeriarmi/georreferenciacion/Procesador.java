/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensajeriarmi.georreferenciacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mensajeriarmi.paquete.Paquete;
import mensajeriarmi.paquete.Ubicacion;
import mensajeriarmi.recepcion.ReceptorPaquetes;


/**
 *
 * @author Otro
 */
public class Procesador extends Thread implements Serializable {

    private ArrayList<Paquete> bufferPaquetes;
    private GeorreferenciadorRMI georreferenciador;
    private String procesoActual;
    
    private final int TIEMPO_GEORREFERENCIACION = 5000;

    public Procesador(GeorreferenciadorRMI g) {
        
        this.bufferPaquetes = new ArrayList<>();
        this.georreferenciador = g;
        this.procesoActual = "N/A";
        
    }
    
    public void iniciar(){
        this.start();
    }
    
    public void agregarPaquete(Paquete p){
        this.bufferPaquetes.add(p);
        //System.out.println("Paquete agregado");
       // System.out.println(this.bufferPaquetes.size());
    }
    
    public void procesar(){
        int i = 0;
        for(;;){
            if(bufferPaquetes.size() >=1){
                System.out.println("-> A punto de georreferenciar un paquete");
                try {
                    Thread.sleep(TIEMPO_GEORREFERENCIACION);
                    System.out.println("-> Geoferrenciando paquete: ");
                    Paquete p = bufferPaquetes.get(i);
                    Ubicacion result = this.georreferenciador.buscarCiudad(p.getCiudadReceptor(), p.getDepartamentoReceptor());
                    //System.out.println();
                    System.out.println("--> Longitud: " + result.getLongitud());
                    p.ubicarReceptor(result);
                    System.out.println("--> Latitud: "+p.getUbicacionReceptor().getLatitud());
                    this.georreferenciador.almacenarPaquete(p);
                    this.bufferPaquetes.remove(p);
                    System.out.println("-> Quedan "+this.bufferPaquetes.size()+" por georreferenciar");
                    System.out.println("////////////////////////////////////////////");
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
