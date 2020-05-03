
package mensajeriarmi.cliente;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import mensajeriarmi.objetos.Bodega;
import mensajeriarmi.bodega.Camion;
import mensajeriarmi.objetos.Cliente;
import mensajeriarmi.paquete.Paquete;
import mensajeriarmi.paquete.Ubicacion;
import mensajeriarmi.objetos.ReceptorPaquetes;

/**
 *
 * @author Otro
 */
public class ClienteRMI implements Cliente{
    
    private ReceptorPaquetes receptorPaquetes;
    private Bodega bodega;
    private String nombre;
    
    private ArrayList<Camion> enviosRegistrados;
   
    private RedCliente red;

    /////////////////////////////////////////////////////////////////////////
    public ClienteRMI(String nombre) {
    
        this.nombre = nombre;
        this.red = new RedCliente("127.0.0.1", "127.0.0.1"); //Host Bodega, Host Receptor
        this.asignarReceptor();
        this.asignarBodega();
        this.enviosRegistrados = new ArrayList<>();
        
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
            p.setEstado("CREADO");
            return receptorPaquetes.registrarPaquete(p);
        } catch (RemoteException ex) {
            Logger.getLogger(ClienteRMI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String solicitarEnvio(Ubicacion u, double pesoTotal){
        try {
            return this.bodega.solicitarEnvio(u, pesoTotal,this);
        } catch (RemoteException ex) {
            Logger.getLogger(ClienteRMI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Camion> getEnviosRegistrados() {
        return enviosRegistrados;
    }
    
    //////////////////////////////////////////////////////////////////////////
    public void notificarEnvio(Camion c){
        this.enviosRegistrados.add(c);
        String mensaje = "Se ha despachado un envio con exito!\n\n para mas información abrir ver envios";
//        String mensaje = "Se despachó el camion: "+ c.getId() +" para la ubicación: \n"+"Latitud: "+c.getDestino().getLatitud()+"\n"+"Longitud: "+c.getDestino().getLongitud()+"\n";
//        mensaje += "\n"+"Paquetes enviados: \n";
//        for (Paquete paquete : c.getPaquetes()) {
//            mensaje += "- Emisor: "+paquete.getNombreEmisor()+" --- Receptor: "+paquete.getNombreReceptor()+" \n";
//        }
        JOptionPane.showMessageDialog(null, mensaje);
    }

    @Override
    public void notificar(Camion c) throws RemoteException {
        this.notificarEnvio(c);
    }
    
}
