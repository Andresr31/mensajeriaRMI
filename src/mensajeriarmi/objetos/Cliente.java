/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensajeriarmi.objetos;

import java.rmi.Remote;
import java.rmi.RemoteException;
import mensajeriarmi.bodega.Camion;

/**
 *
 * @author Otro
 */
public interface Cliente extends Remote{
    
    public void notificar(Camion c)
            throws RemoteException;
    
}
