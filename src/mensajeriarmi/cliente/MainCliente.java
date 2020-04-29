
package mensajeriarmi.cliente;

import mensajeriarmi.paquete.Paquete;

/**
 *
 * @author Otro
 */
public class MainCliente {
   
    public static void main (String[] args){
        
        Cliente cliente = new Cliente("David y Andres");
        
        Paquete p = new Paquete();
        p.setNombreEmisor("David");
        p.setNombreReceptor("Andres");
        p.setCiudadEmisor("Manizales");
        p.setCiudadReceptor("Chinchina");
        p.setDepartamentoReceptor("Caldas");
        p.setPeso(20);
        System.out.println(cliente.registrarPaquete(p));
        
    }
    
    
}
