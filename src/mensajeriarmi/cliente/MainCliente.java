
package mensajeriarmi.cliente;

/**
 *
 * @author Otro
 */
public class MainCliente {
   
    public static void main (String[] args){
        
        Cliente cliente = new Cliente("David y Andres");
        System.out.println(cliente.registrarPaquete(null));
        
    }
    
    
}
