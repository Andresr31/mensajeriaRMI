
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
    private Date horaRecibido;
    private Date horaEnvio;
    private String estado; // RECIBIDO, ENVIADO, EN PROCESO
    private double peso;
    
    ///////////////////////////////////////////////////////////////////////////

    public Paquete() {
        
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

    public Date getHoraRecibido() {
        return horaRecibido;
    }

    public void setHoraRecibido(Date horaRecibido) {
        this.horaRecibido = horaRecibido;
    }

    public Date getHoraEnvio() {
        return horaEnvio;
    }

    public void setHoraEnvio(Date horaEnvio) {
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
