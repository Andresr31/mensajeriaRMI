/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensajeriarmi.recepcion;

import java.rmi.*;
import mensajeriarmi.paquete.Paquete;

/**
 * @author Carlos Andres Rojas
 * @author David Salgado Ospina
 */
public interface ReceptorPaquetes extends Remote{
    public String registrarPaquete(Paquete paquete) 
            throws RemoteException;
}
