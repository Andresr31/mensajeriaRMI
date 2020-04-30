/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensajeriarmi.georreferenciacion;

import java.rmi.Remote;
import java.rmi.RemoteException;
import mensajeriarmi.paquete.Paquete;
import mensajeriarmi.paquete.Ubicacion;
import mensajeriarmi.recepcion.ReceptorPaquetes;

/**
 * @author Carlos Andres Rojas
 * @author David Salgado Ospina
 */
public interface Georreferenciador extends Remote{
    
    public String georreferenciar(Paquete p, ReceptorPaquetes g)
            throws RemoteException;
    
    
    
}
