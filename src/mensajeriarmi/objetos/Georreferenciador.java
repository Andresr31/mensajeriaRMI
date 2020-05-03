/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensajeriarmi.objetos;

import java.rmi.Remote;
import java.rmi.RemoteException;
import mensajeriarmi.paquete.Paquete;

/**
 * @author Carlos Andres Rojas
 * @author David Salgado Ospina
 */
public interface Georreferenciador extends Remote{
    
    public String georreferenciar(Paquete p, ReceptorPaquetes g)
            throws RemoteException;
    
    
    
}
