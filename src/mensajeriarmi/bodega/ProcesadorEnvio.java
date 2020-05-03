/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensajeriarmi.bodega;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mensajeriarmi.cliente.ClienteRMI;
import mensajeriarmi.objetos.Cliente;
import mensajeriarmi.paquete.Paquete;
import mensajeriarmi.paquete.Ubicacion;

/**
 *
 * @author Otro
 */
public class ProcesadorEnvio extends Thread implements Serializable {

    private ArrayList<Camion> bufferCamiones;
    private ArrayList<ClienteRMI> clientesNotificables;
    private String procesoActual;
    private BodegaRMI bodega;
    
    private Despachador despachador;
    
    private final int TIEMPO_ENVIO = 30000;

    public ProcesadorEnvio(BodegaRMI b, Despachador d) {
        
        this.bufferCamiones = new ArrayList<>();
        this.clientesNotificables = new ArrayList<>();
        this.procesoActual = "N/A";
        this.bodega = b;
        this.despachador = d;
        
    }
    
    public void iniciar(){
        this.start();
    }
    
    public void agregarEnvios (Ubicacion u, double pesoTotal, ClienteRMI cl){
        Camion c = new Camion(pesoTotal, u);
        this.clientesNotificables.add(cl);
        this.bufferCamiones.add(c);
    }
    
    public void procesar(){
        int i = 0;
        for(;;){
            if(bufferCamiones.size() >=1){
                System.out.println("-> A punto de cargar un camion");
                try {
                    Thread.sleep(TIEMPO_ENVIO);
                    Camion c = bufferCamiones.get(i);
                    //System.out.println("Envia paquete de: "+p.getNombreEmisor()+ "Para: "+p.getCiudadReceptor());
                    //this.bodega.almacenarPaquete(p);
                    this.despachador.despachar(this.bodega.getPaquetesAlmacenados(), c.getDestino(), c);
                    System.out.println("-> Camión despachado con "+c.getPaquetes().size()+" paquetes");
                    //this.clientesNotificables.get(i).notificarEnvio(c);
                    this.bufferCamiones.remove(c);
                    this.clientesNotificables.remove(i);
                    this.bodega.almacenarRegistroCamion(c);
                    System.out.println("Quedan "+this.bufferCamiones.size()+" por cargar");
                    System.out.println("////////////////////////////////////////////");
                    
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
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