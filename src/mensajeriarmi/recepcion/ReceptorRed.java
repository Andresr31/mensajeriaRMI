package mensajeriarmi.recepcion;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import mensajeriarmi.bodega.Bodega;
import mensajeriarmi.georreferenciacion.Georreferenciador;
import mensajeriarmi.paquete.Paquete;

/**
 *
 * @author Otro
 */
public class ReceptorRed {

    private Registry registroRMIGeo;
    private Registry registroRMIBod;

    public ReceptorRed(String hostBod, String hostGeo) {

        this.conectarBodega(hostBod);
        this.conectarGeoreferenciador(hostGeo);
        
    }

    public final void conectarBodega(String host) {

        System.setProperty("java.security.policy", "client.policy");

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
            this.registroRMIBod = LocateRegistry.getRegistry(host,4410);
        } catch (RemoteException e) {
            System.out.println("[Cliente] (RemoteException): " + e.getMessage());
        }
    }
    
    public final void conectarGeoreferenciador(String host) {

        System.setProperty("java.security.policy", "client.policy");

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
            this.registroRMIGeo = LocateRegistry.getRegistry(host,4400);
        } catch (RemoteException e) {
            System.out.println("[Cliente] (RemoteException): " + e.getMessage());
        }
    }

    public Bodega asignarBodega() {

        Bodega bodega = null;
        try {
            bodega = (Bodega) this.registroRMIBod.lookup("Bodega");

        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        return bodega;
    }
    
    public Georreferenciador asignarGeorreferenciador() {

        Georreferenciador georreferenciador = null;
        try {
            georreferenciador = (Georreferenciador) this.registroRMIGeo.lookup("Georreferenciador");

        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return georreferenciador;
    }
    
    

}
