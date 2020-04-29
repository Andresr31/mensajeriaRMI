
package mensajeriarmi.cliente;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import mensajeriarmi.bodega.Bodega;
import mensajeriarmi.recepcion.ReceptorPaquetes;

/**
 *
 * @author Carlos Andres Rojas Parra
 * @author David Salgado Ospina
 * 
 */
public class RedCliente {

    private Registry registroRMI;

    public RedCliente(String host) {
        
        this.conectar(host);
    }
    
    public final void conectar(String host){
        
        System.setProperty("java.security.policy", "client.policy");
        
        if (System.getSecurityManager() == null)
           System.setSecurityManager(new SecurityManager());
        
        try {
            this.registroRMI = LocateRegistry.getRegistry(host);
            
        }catch(RemoteException e) 
        {
            System.out.println("[Cliente] (RemoteException): " + e.getMessage());
        }
    }
    
    public ReceptorPaquetes asignarReceptorPaquetes(){
        
        ReceptorPaquetes receptor = null;
        
        try {
            
            receptor = (ReceptorPaquetes) this.registroRMI.lookup("ReceptorPaquetes");
            
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return receptor;
    }
    
    public Bodega asignarBodega(){
        
        Bodega bodega = null;
        
        try {
            
            bodega = (Bodega) this.registroRMI.lookup("Bodega");
            
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return bodega;
        
    }    
    
    
    
    


    
}
