/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensajeriarmi.bodega;

import java.rmi.RemoteException;
import java.util.ArrayList;
import mensajeriarmi.cliente.Cliente;
import mensajeriarmi.paquete.Paquete;
import mensajeriarmi.paquete.Ubicacion;

/**
 * @author Carlos Andres Rojas
 * @author David Salgado Ospina
 */
public class BodegaRMI implements Bodega{
    
    private ArrayList<Paquete> paquetesAlmacenados;
    private ArrayList<Camion> camiones;
    
    private BodegaServidor servidor;
    private ProcesadorAlmacenamiento procesadorAlmacenamiento;
    private ProcesadorEnvio procesadorEnvio;
    
    private Despachador despachador;
    
    
    public BodegaRMI(){
        super();
        
        this.camiones = new ArrayList<>();
        this.paquetesAlmacenados = new ArrayList<>();
        
        
        this.servidor = new BodegaServidor("127.0.0.1",4410,this);
        
        this.procesadorAlmacenamiento = new ProcesadorAlmacenamiento(this);
        this.procesadorAlmacenamiento.iniciar();
        
        this.despachador = new Despachador(this);
        this.procesadorEnvio = new ProcesadorEnvio(this,this.despachador);
        this.procesadorEnvio.iniciar();
    }
    
    
    //sacar paquete SOLO AL ESTAR EN CAMION
    
    // Métodos remotos (vienen de la interfaz remota)
    //////////////////////////////////////////////////////////////////////////
    public String almacenarPaquete(Paquete p){
        // Manejo de buffer de almacenamiento
        p.setEstado("ALMACENADO");
        this.paquetesAlmacenados.add(p);
        System.out.println("Almacenando: "+p.getNombreEmisor());
        System.out.println("Ubicacion: "+p.getUbicacionReceptor().getLatitud());
        return "200:"+this.paquetesAlmacenados.size();
        
    }
    //////////////////////////////////////////////////////////////////////////

    public ArrayList<Paquete> getPaquetesAlmacenados() {
        return paquetesAlmacenados;
    }
    
    
    public void despacharPaquete(Paquete p){
        this.paquetesAlmacenados.remove(p);
    }
    
    
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public String almacenar(Paquete paquete) throws RemoteException {
        this.procesadorAlmacenamiento.agregarPaquete(paquete);
        return "200:"+(this.paquetesAlmacenados.size()+1);
    }

    @Override
    public String solicitarEnvio(Ubicacion ubicacion, double capacidadTotal, Cliente c) throws RemoteException {
        this.procesadorEnvio.agregarEnvios(ubicacion, capacidadTotal, c);
        System.out.println("Solicitaron un envio");
        return "--------- Hola Mundo BODEGA";
    }
    
    
}
