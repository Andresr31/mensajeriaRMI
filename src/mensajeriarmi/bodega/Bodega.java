/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensajeriarmi.bodega;

import mensajeriarmi.paquete.Paquete;
import java.rmi.*;
import mensajeriarmi.paquete.Ubicacion;

/**
 * @author Carlos Andres Rojas
 * @author David Salgado Ospina
 */
public interface Bodega extends Remote{
    
    public String almacenar(Paquete paquete)
            throws RemoteException;
    
    //agregar parametro => ubicacion ubicacion 
    public String solicitarEnvio(Ubicacion ubicacion, double capacidadCamion)
            throws RemoteException;
}
