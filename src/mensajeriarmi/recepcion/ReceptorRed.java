package mensajeriarmi.recepcion;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import mensajeriarmi.bodega.Bodega;
import mensajeriarmi.georreferenciacion.Georreferenciador;

/**
 *
 * @author Otro
 */
public class ReceptorRed {

    private Registry registroRMI;

    public ReceptorRed(String host) {

        conectar(host);

    }

    public final void conectar(String host) {

        System.setProperty("java.security.policy", "client.policy");

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
            this.registroRMI = LocateRegistry.getRegistry(host);

        } catch (RemoteException e) {
            System.out.println("[Cliente] (RemoteException): " + e.getMessage());
        }
    }

    public Bodega asignarBodega() {

        Bodega bodega = null;
        try {
            bodega = (Bodega) this.registroRMI.lookup("Bodega");

        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        return bodega;
    }
    
    public Georreferenciador asignarGeorreferenciador() {

        Georreferenciador georreferenciador = null;
        try {
            georreferenciador = (Georreferenciador) this.registroRMI.lookup("Georreferenciador");

        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        return georreferenciador;
    }
    
    

}
