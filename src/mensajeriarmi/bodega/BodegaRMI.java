/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensajeriarmi.bodega;

import java.rmi.RemoteException;
import mensajeriarmi.paquete.Paquete;

/**
 * @author Carlos Andres Rojas
 * @author David Salgado Ospina
 */
public class BodegaRMI implements Bodega{
    
    public BodegaRMI(){
        super();
    }
    
    // Métodos remotos (vienen de la interfaz remota)
    
    @Override
    public String almacenar(Paquete paquete) throws RemoteException {
        return "hola mundo";
    }

    @Override
    public String solicitarEnvio(Paquete paquete) throws RemoteException {
        return "hola mundo";
    }
    
}
