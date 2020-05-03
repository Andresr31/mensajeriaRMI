
package mensajeriarmi.objetos;

import mensajeriarmi.recepcion.*;
import java.rmi.*;
import mensajeriarmi.paquete.Paquete;

/**
 * @author Carlos Andres Rojas
 * @author David Salgado Ospina
 */
public interface ReceptorPaquetes extends Remote{
    public String registrarPaquete(Paquete paquete) 
            throws RemoteException;
    
    public String almacenarPaquete(Paquete p)
            throws RemoteException;
}


