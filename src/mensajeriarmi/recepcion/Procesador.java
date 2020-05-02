/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensajeriarmi.recepcion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mensajeriarmi.paquete.Paquete;

/**
 *
 * @author Otro
 */
public class Procesador extends Thread implements Serializable {

    private ArrayList<Paquete> bufferPaquetes;
    private ReceptorPaquetesRMI receptor;
    private String procesoActual;
    
    private final int TIEMPO_REGISTRAR = 10000;

    public Procesador(ReceptorPaquetesRMI r) {
        
        this.bufferPaquetes = new ArrayList<>();
        this.receptor = r;
        this.procesoActual = "N/A";
        
    }
    
    public void iniciar(){
        this.start();
    }
    
    public void agregarPaquete(Paquete p){
        p.setEstado("RECIBIDO");
        this.bufferPaquetes.add(p);
        //System.out.println("Paquete agregado");
       // System.out.println(this.bufferPaquetes.size());
    }
    
    public void procesar(){
        int i = 0;
        for(;;){
            if(bufferPaquetes.size() >=1){
                System.out.println("A punto de procesar un paquete");
                try {
                    Paquete p = bufferPaquetes.get(i);
                    Thread.sleep(TIEMPO_REGISTRAR);
                    this.receptor.ubicarPaquete(p);
                    p.setEstado("GEORREFERENCIADO");
                    System.out.println("Procesando paquete: ");
                    String respuesta = "";
                    respuesta += p.getNombreEmisor() +"\n";
                    respuesta += p.getNombreReceptor()+"\n";
                    respuesta += p.getCiudadReceptor()+"\n";
                    respuesta += p.getDepartamentoReceptor()+"\n";
//                    respuesta += p.getUbicacionReceptor().getLatitud()+"\n";
//                    respuesta += p.getUbicacionReceptor().getLongitud()+"\n";
                    //this.receptor.almacenarPaquete(p);
                    //respuesta += "Almacenamiento: "+almacenamiento+"\n\n";
                    //respuesta = this.receptor.procesarPaquete(p);
                    System.out.println(respuesta);
                    this.procesoActual = respuesta;
                    this.bufferPaquetes.remove(p);
                    System.out.println("Quedan "+this.bufferPaquetes.size()+" por procesar");
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
