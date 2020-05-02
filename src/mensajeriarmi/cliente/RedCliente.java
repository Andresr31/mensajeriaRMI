
package mensajeriarmi.cliente;

import java.io.Serializable;
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
public class RedCliente implements Serializable{

    private Registry registroRMIRecep;
    private Registry registroRMIBod;


    public RedCliente(String hostBod, String hostRecep) {
        
        this.conectarBodega(hostBod);
        this.conectarReceptor(hostRecep);
    }
    
    public final void conectarBodega(String host){
        
        System.setProperty("java.security.policy", "client.policy");
        
        if (System.getSecurityManager() == null)
           System.setSecurityManager(new SecurityManager());
        
        try {
            this.registroRMIBod = LocateRegistry.getRegistry(host, 4410);
            
        }catch(RemoteException e) 
        {
            System.out.println("[Cliente] (RemoteException): " + e.getMessage());
        }
    }
    
    public final void conectarReceptor(String host){
    
        System.setProperty("java.security.policy", "client.policy");
        
        if (System.getSecurityManager() == null)
           System.setSecurityManager(new SecurityManager());
        
        try {
            this.registroRMIRecep = LocateRegistry.getRegistry(host, 1099);
            
        }catch(RemoteException e) 
        {
            System.out.println("[Cliente] (RemoteException): " + e.getMessage());
        }
    }
    
    public ReceptorPaquetes asignarReceptorPaquetes(){
        
        ReceptorPaquetes receptor = null;
        
        try {
            
            receptor = (ReceptorPaquetes) this.registroRMIRecep.lookup("ReceptorPaquetes");
            
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return receptor;
    }
    
    public Bodega asignarBodega(){
        
        Bodega bodega = null;
        
        try {
            
            bodega = (Bodega) this.registroRMIBod.lookup("Bodega");
            
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return bodega;
        
    }    
    
    
    
    


    
}
