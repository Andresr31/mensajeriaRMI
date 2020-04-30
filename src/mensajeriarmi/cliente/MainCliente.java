
package mensajeriarmi.cliente;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import mensajeriarmi.paquete.Paquete;
import mensajeriarmi.vistas.Principal;
import mensajeriarmi.vistas.RegistroCliente;

/**
 *
 * @author Otro
 */
public class MainCliente {
   
    public static void main (String[] args){
        
        //System.out.println(new Date().getHours()+":"+new Date().getMinutes()+":"+new Date().getSeconds());
        Principal ventana = new Principal();
        RegistroCliente registro = new RegistroCliente(ventana, true, ventana);
        registro.setVisible(true);
//        Cliente cliente = new Cliente("David y Andres");
//        
//        Paquete p = new Paquete();
//        p.setNombreEmisor("David");
//        p.setNombreReceptor("Andres");
//        p.setCiudadEmisor("Manizales");
//        p.setCiudadReceptor("Chinchina");
//        p.setDepartamentoReceptor("Caldas");
//        p.setPeso(20);
//        System.out.println(cliente.registrarPaquete(p));
//        
//        
//        Paquete p1 = new Paquete();
//        p1.setNombreEmisor("Andres");
//        p1.setNombreReceptor("David");
//        p1.setCiudadEmisor("Chinchina");
//        p1.setCiudadReceptor("Manizales");
//        p1.setDepartamentoReceptor("Caldas");
//        p1.setPeso(30);
//        System.out.println(cliente.registrarPaquete(p1));
        
    }
    
    
}
