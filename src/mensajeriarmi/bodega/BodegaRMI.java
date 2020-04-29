/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensajeriarmi.bodega;

import java.rmi.RemoteException;
import java.util.ArrayList;
import mensajeriarmi.paquete.Paquete;

/**
 * @author Carlos Andres Rojas
 * @author David Salgado Ospina
 */
public class BodegaRMI implements Bodega{
    
    private ArrayList<Paquete> paquetesAlmacenados;
    private ArrayList<Paquete> bufferAlmacenamiento;
    private ArrayList<Paquete> bufferEnvio;
    private ArrayList<Camion> camiones;
    
    private BodegaServidor servidor;
    
    
    public BodegaRMI(){
        super();
        
        this.bufferAlmacenamiento = new ArrayList<>();
        this.bufferEnvio = new ArrayList<>();
        this.camiones = new ArrayList<>();
        this.paquetesAlmacenados = new ArrayList<>();
        
        
        this.servidor = new BodegaServidor("127.0.0.1",4410,this);
    }
    
    // Métodos remotos (vienen de la interfaz remota)
    //////////////////////////////////////////////////////////////////////////
    public String almacenarPaquete(Paquete p){
        // Manejo de buffer de almacenamiento
        this.paquetesAlmacenados.add(p);
        System.out.println("Almacenando: "+p.getNombreEmisor());
        return "200:"+this.paquetesAlmacenados.size();
        
    }
    
    
    
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public String almacenar(Paquete paquete) throws RemoteException {
        return this.almacenarPaquete(paquete);
    }

    @Override
    public String solicitarEnvio(Paquete paquete) throws RemoteException {
        return "hola mundo";
    }
    
}
