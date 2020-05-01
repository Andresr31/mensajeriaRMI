
package mensajeriarmi.paquete;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Carlos Andres Rojas Parra
 * @author David Salgado Ospina
 * 
 */
public class Paquete implements Serializable{
    
    private String nombreEmisor;
    private String ciudadEmisor;
    private String nombreReceptor;
    private String ciudadReceptor;
    private String departamentoReceptor;
    private Ubicacion ubicacionReceptor;
    private Date fechaRecibido;
    private Date fechaEnvio;
    private String horaRecibido;
    private String horaEnvio;
    private String estado; // RECIBIDO, ENVIADO, EN PROCESO
    private double peso;
    
    ///////////////////////////////////////////////////////////////////////////

    public Paquete() {
        this.nombreEmisor = "";
        this.ciudadEmisor ="";
        this.nombreReceptor="";
        this.ciudadReceptor="";
        this.departamentoReceptor="";
        this.ubicacionReceptor=new Ubicacion(0, 0);
        this.fechaRecibido=new Date();
        this.fechaEnvio= new Date();
        this.horaRecibido = "";
        this.horaEnvio = "";
        this.estado = ""; // RECIBIDO, ENVIADO, EN PROCESO
        this.peso = 0;
        
    }

    public Paquete(String nombreEmisor, String ciudadEmisor, String nombreReceptor, String ciudadReceptor, String departamentoReceptor, Date fechaRecibido, String horaRecibido, String estado, double peso) {
        this.nombreEmisor = nombreEmisor;
        this.ciudadEmisor = ciudadEmisor;
        this.nombreReceptor = nombreReceptor;
        this.ciudadReceptor = ciudadReceptor;
        this.departamentoReceptor = departamentoReceptor;
        this.fechaRecibido = fechaRecibido;
        this.horaRecibido = horaRecibido;
        this.estado = estado;
        this.peso = peso;
        
    }
    
    
    
    ///////////////////////////////////////////////////////////////////////////

    public String getNombreEmisor() {
        return nombreEmisor;
    }

    public void setNombreEmisor(String nombreEmisor) {
        this.nombreEmisor = nombreEmisor;
    }

    public String getCiudadEmisor() {
        return ciudadEmisor;
    }

    public void setCiudadEmisor(String ciudadEmisor) {
        this.ciudadEmisor = ciudadEmisor;
    }

    public String getNombreReceptor() {
        return nombreReceptor;
    }

    public void setNombreReceptor(String nombreReceptor) {
        this.nombreReceptor = nombreReceptor;
    }

    public String getCiudadReceptor() {
        return ciudadReceptor;
    }

    public void setCiudadReceptor(String ciudadReceptor) {
        this.ciudadReceptor = ciudadReceptor;
    }

    public String getDepartamentoReceptor() {
        return departamentoReceptor;
    }

    public void setDepartamentoReceptor(String departamentoReceptor) {
        this.departamentoReceptor = departamentoReceptor;
    }

    public Date getFechaRecibido() {
        return fechaRecibido;
    }

    public void setFechaRecibido(Date fechaRecibido) {
        this.fechaRecibido = fechaRecibido;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getHoraRecibido() {
        return horaRecibido;
    }

    public void setHoraRecibido(String horaRecibido) {
        this.horaRecibido = horaRecibido;
    }

    public String getHoraEnvio() {
        return horaEnvio;
    }

    public void setHoraEnvio(String horaEnvio) {
        this.horaEnvio = horaEnvio;
    }
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
    
    //////////////////////////////////////////////////////////////////////////
    
    public void ubicarReceptor(Ubicacion u){
        this.ubicacionReceptor = u;
    }

    public Ubicacion getUbicacionReceptor() {
        return ubicacionReceptor;
    }
    
    
    
    
    
}
