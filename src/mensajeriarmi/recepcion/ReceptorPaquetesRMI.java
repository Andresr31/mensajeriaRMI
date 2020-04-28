/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensajeriarmi.recepcion;

import java.rmi.RemoteException;
import mensajeriarmi.paquete.Paquete;

/**
 * @author Carlos Andres Rojas
 * @author David Salgado Ospina
 */
public class ReceptorPaquetesRMI implements ReceptorPaquetes{

    public ReceptorPaquetesRMI(){
        super();
    }
    
    
    // Métodos remotos (vienen de la interfaz remota)
    
    @Override
    public String registrarPaquete(Paquete paquete) 
            throws RemoteException 
    {
        return "hola mundo";
    } 
}
