
package mensajeriarmi.georreferenciacion;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


/**
 *
 * @author Otro
 */
public class GeorreferenciadorServer {
    
    private String ipServidor;
    private GeorreferenciadorRMI georreferenciadorI;

    public GeorreferenciadorServer(String ipServidor, GeorreferenciadorRMI georreferenciador) {
        this.ipServidor = ipServidor;
        this.georreferenciadorI = georreferenciador;
        this.conectar();
    }
    
    public final void conectar() {

        System.out.println("[ServidorGeorreferenciador] Establecer a través de cual interfaz de red del servidor ser recibirán peticiones.");
        System.setProperty("java.rmi.server.hostname", ipServidor);
        System.out.println("[ServidorGeorreferenciador] Especificando el nombre de la politica de seguridad.");

        System.setProperty("java.security.policy", "server.policy");
        System.out.println("[ServidorGeorreferenciador] Estableciendo el manejador de seguridad.");

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
            System.out.println("[ServidorGeorreferenciador] Instanciado el objeto que va a ser accedido remotamente.");
            Georreferenciador georreferenciador = this.georreferenciadorI;
            System.out.println("[ServidorGeorreferenciador] Creando el 'stub' del objeto que va a ser accedido remotamente.");

            Georreferenciador stub = (Georreferenciador) UnicastRemoteObject.exportObject(georreferenciador, 0);
            // Puerto por defecto del RMI Registry: 1099
            System.out.println("[ServidorGeorreferenciador] Iniciando su propio servicio de RMI Registry.");
            Registry registry = LocateRegistry.createRegistry(4400);

            System.out.println("[ServidorGeorreferenciador] Publicando el stub del objeto remoto en el RMI Registry.");

            registry.rebind("Georreferenciador", stub);

            System.out.println("[ServidorGeorreferenciador] Servidor listo!");
        } catch (RemoteException e) {
            System.out.println("[ServidorGeorreferenciador] (exception): " + e.getMessage());
        }
    }

    ////////////////////////////////////////////////////////////////////////

    
}
