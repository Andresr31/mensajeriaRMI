package mensajeriarmi.recepcion;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import mensajeriarmi.objetos.ReceptorPaquetes;

/**
 *
 * @author Otro
 */
public class ReceptorServer {

    private String ipServidor;
    private ReceptorPaquetesRMI receptorP;

    public ReceptorServer(String ipServidor, ReceptorPaquetesRMI r) {
        this.ipServidor = ipServidor;
        this.receptorP = r;
        conectar();
    }

    ///////////////////////////////////////////////////////////////////
    public final void conectar() {

        System.out.println("[ServidorReceptor] Establecer a través de cual interfaz de red del servidor ser recibirán peticiones.");
        System.setProperty("java.rmi.server.hostname", ipServidor);
        System.out.println("[ServidorReceptor] Especificando el nombre de la politica de seguridad.");

        System.setProperty("java.security.policy", "server.policy");
        System.out.println("[ServidorReceptor] Estableciendo el manejador de seguridad.");

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
            System.out.println("[ServidorReceptor] Instanciado el objeto que va a ser accedido remotamente.");
            ReceptorPaquetes receptor = this.receptorP;
            System.out.println("[ServidorReceptor] Creando el 'stub' del objeto que va a ser accedido remotamente.");

            ReceptorPaquetes stub = (ReceptorPaquetes) UnicastRemoteObject.exportObject(receptor, 0);
            // Puerto por defecto del RMI Registry: 1099
            System.out.println("[ServidorReceptor] Iniciando su propio servicio de RMI Registry.");
            Registry registry = LocateRegistry.createRegistry(1099);

            System.out.println("[ServidorReceptor] Publicando el stub del objeto remoto en el RMI Registry.");

            registry.rebind("ReceptorPaquetes", stub);

            System.out.println("[ServidorReceptor] Servidor listo!");
        } catch (RemoteException e) {
            System.out.println("[ServidorReceptor] (exception): " + e.getMessage());
        }
    }

    ////////////////////////////////////////////////////////////////////////

    public String getIpServidor() {
        return ipServidor;
    }

    public void setIpServidor(String ipServidor) {
        this.ipServidor = ipServidor;
    }

    public ReceptorPaquetesRMI getReceptorP() {
        return receptorP;
    }

    public void setReceptorP(ReceptorPaquetesRMI receptorP) {
        this.receptorP = receptorP;
    }
    
    
    
}
