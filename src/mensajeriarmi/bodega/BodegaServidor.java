/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensajeriarmi.bodega;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


/**
 *
 * @author Otro
 */
public class BodegaServidor  {
    
    private String ipServidor;
    private int puerto;
    
    private BodegaRMI bodega;

    public BodegaServidor(String ipServidor, int puerto, BodegaRMI bodega) {
        this.ipServidor = ipServidor;
        this.puerto = puerto;
        this.bodega = bodega;
        this.conectar();
    }
    
    public final void conectar() {

        System.out.println("[ServidorBodega] Establecer a través de cual interfaz de red del servidor ser recibirán peticiones.");
        System.setProperty("java.rmi.server.hostname", ipServidor);
        System.out.println("[ServidorGeorreferenciador] Especificando el nombre de la politica de seguridad.");

        System.setProperty("java.security.policy", "server.policy");
        System.out.println("[ServidorBodega] Estableciendo el manejador de seguridad.");

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
            System.out.println("[ServidorBodega] Instanciado el objeto que va a ser accedido remotamente.");
            Bodega b = this.bodega;
            System.out.println("[ServidorBodega] Creando el 'stub' del objeto que va a ser accedido remotamente.");

            Bodega stub = (Bodega) UnicastRemoteObject.exportObject(b, 0);
            // Puerto por defecto del RMI Registry: 1099
            System.out.println("[ServidorBodega] Iniciando su propio servicio de RMI Registry.");
            Registry registry = LocateRegistry.createRegistry(puerto);

            System.out.println("[ServidorBodega] Publicando el stub del objeto remoto en el RMI Registry.");

            registry.rebind("Bodega", stub);

            System.out.println("[ServidorBodega] Servidor listo!");
        } catch (RemoteException e) {
            System.out.println("[ServidorBodega] (exception): " + e.getMessage());
        }
    }

    ////////////////////////////////////////////////////////////////////////
            
    
    
    
}
