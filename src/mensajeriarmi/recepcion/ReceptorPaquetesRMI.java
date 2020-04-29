/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensajeriarmi.recepcion;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mensajeriarmi.bodega.Bodega;
import mensajeriarmi.georreferenciacion.Georreferenciador;
import mensajeriarmi.paquete.Paquete;
import mensajeriarmi.paquete.Ubicacion;

/**
 * @author Carlos Andres Rojas
 * @author David Salgado Ospina
 */
public class ReceptorPaquetesRMI implements ReceptorPaquetes{
    
    private ArrayList<Paquete> bufferPaquetes;
 
    private Georreferenciador georreferenciador;
    private Bodega bodega;
    
    private ReceptorServer servidor;
    private ReceptorRed red;

    public ReceptorPaquetesRMI(){
        super();
        this.bufferPaquetes = new ArrayList<>();
       
        
        
        this.servidor = new ReceptorServer("127.0.0.1", this);
        this.red = new ReceptorRed("127.0.0.1");
        
        this.asignarGeorreferenciador();
        this.asignarBodega();

    }
    
    
    // Métodos remotos (vienen de la interfaz remota)
    public Ubicacion consultarUbicacion(Paquete p){
        // Solicitar a georreferenciador ubicacion
        Ubicacion ubicacion = null;
        try {
            ubicacion = this.georreferenciador.georreferenciar(p);
        } catch (RemoteException ex) {
            System.out.println("Error al georreferenciar paquete \n" + ex.getMessage());
        }
        
        return ubicacion;
    }
    
    public String almacenarPaquete(Paquete p){
        String respuesta = null;
        try {
            // Solicitar a la bodega almacenar el paquete
            respuesta = this.bodega.almacenar(p);
            
        } catch (RemoteException ex) {
            Logger.getLogger(ReceptorPaquetesRMI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
    public String procesarPaquete(Paquete p){
        
        //Solicitar ubicación del paquete con consultar Ubicacion
        //Solicitar almacenar el paquete en la bodega
        // Cargar los paquetes que llegan en el buffer de paquetes con el sleep
        // Notificar proceso de paquetes
        System.out.println("Ubicando: "+p.getNombreEmisor());
        Ubicacion uPaquete = consultarUbicacion(p);
        p.ubicarReceptor(uPaquete);
        String almacenamiento = this.almacenarPaquete(p);
        
        String respuesta = "";
        respuesta += p.getNombreEmisor() +"\n";
        respuesta += p.getNombreReceptor()+"\n";
        respuesta += p.getCiudadReceptor()+"\n";
        respuesta += p.getDepartamentoReceptor()+"\n";
        respuesta += p.getUbicacionReceptor().getLatitud()+"\n";
        respuesta += p.getUbicacionReceptor().getLongitud()+"\n";
        respuesta += "Almacenamiento: "+almacenamiento+"\n\n";
        
        return respuesta;
    }
    
    ////////////////////////////////////////////////////////////////////////
    
    public void asignarBodega(){
        this.bodega = this.red.asignarBodega();
    }
    
    public void asignarGeorreferenciador(){
        this.georreferenciador = this.red.asignarGeorreferenciador();
    }
    
    ////////////////////////////////////////////////////////////////////////
    
    @Override
    public String registrarPaquete(Paquete paquete) 
            throws RemoteException 
    {
        return this.procesarPaquete(paquete);
    } 
    
    ////////////////////////////////////////////////////////////////////////

    public ArrayList<Paquete> getBufferPaquetes() {
        return bufferPaquetes;
    }

    public void setBufferPaquetes(ArrayList<Paquete> bufferPaquetes) {
        this.bufferPaquetes = bufferPaquetes;
    }

    public Georreferenciador getGeorreferenciador() {
        return georreferenciador;
    }

    public void setGeorreferenciador(Georreferenciador georreferenciador) {
        this.georreferenciador = georreferenciador;
    }

    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }

    public ReceptorServer getServidor() {
        return servidor;
    }

    public void setServidor(ReceptorServer servidor) {
        this.servidor = servidor;
    }

    public ReceptorRed getRed() {
        return red;
    }

    public void setRed(ReceptorRed red) {
        this.red = red;
    }
    
    ///////////////////////////////////////////////////////////////////////////
    
    
    
}
