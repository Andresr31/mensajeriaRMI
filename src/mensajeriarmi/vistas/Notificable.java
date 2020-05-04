/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensajeriarmi.vistas;

import mensajeriarmi.bodega.Camion;
import mensajeriarmi.cliente.ClienteRMI;

/**
 *
 * @author Otro
 */
public class Notificable extends Thread{
    
    private ClienteRMI cliente;
    private Principal ventana;
    private Notificacion notificacion;

    public Notificable(ClienteRMI cliente, Principal v) {
        this.cliente = cliente;
        this.ventana = v;
        this.notificacion = new Notificacion(v, true);
    }
    
    
    
    public void notificar(){
        
        int i = 0;
        for(;;){
            if(cliente.getEnviosRegistrados().size() > (i)){
                this.notificacion.setVisible(true);
                i++;
            }else{
                System.getProperties();
            }
        }
        
    }
    
    
    @Override
    public void run(){
        notificar();
        
    }
    
}
