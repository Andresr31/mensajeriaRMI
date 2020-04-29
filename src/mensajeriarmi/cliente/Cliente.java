
package mensajeriarmi.cliente;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mensajeriarmi.bodega.Bodega;
import mensajeriarmi.paquete.Paquete;
import mensajeriarmi.recepcion.ReceptorPaquetes;

/**
 *
 * @author Otro
 */
public class Cliente {
    
    private ReceptorPaquetes receptorPaquetes;
    private Bodega bodega;
    private String nombre;
    
    private RedCliente red;

    /////////////////////////////////////////////////////////////////////////
    public Cliente(String nombre) {
    
        this.nombre = nombre;
        this.red = new RedCliente("127.0.0.1");
        this.asignarReceptor();
        
    }
    /////////////////////////////////////////////////////////////////////////

    public ReceptorPaquetes getReceptorPaquetes() {
        return receptorPaquetes;
    }

    public void setReceptorPaquetes(ReceptorPaquetes receptorPaquetes) {
        this.receptorPaquetes = receptorPaquetes;
    }

    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public RedCliente getRed() {
        return red;
    }

    public void setRed(RedCliente red) {
        this.red = red;
    }
    
    
    /////////////////////////////////////////////////////////////////////////
    
    public void asignarReceptor(){
        this.receptorPaquetes = this.red.asignarReceptorPaquetes();
    }
    
    public void asignarBodega(){
        this.bodega = this.red.asignarBodega();
    }
    
    /////////////////////////////////////////////////////////////////////////
    
    public String registrarPaquete(Paquete p){
        try {
            return receptorPaquetes.registrarPaquete(p);
        } catch (RemoteException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    
}
