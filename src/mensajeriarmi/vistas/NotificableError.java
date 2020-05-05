/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensajeriarmi.vistas;

import mensajeriarmi.cliente.ClienteRMI;

/**
 *
 * @author Otro
 */
public class NotificableError extends Thread{
    
    private ClienteRMI cliente;
    private Principal ventana;
    private NotificacionError notificacionError;

    public NotificableError(ClienteRMI cliente, Principal v) {
        this.cliente = cliente;
        this.ventana = v;
        this.notificacionError = new NotificacionError(v, true);
    }
    
    
    
    public void notificar(){
        
        int i = 0;
        for(;;){
            if(cliente.getErrores().size() > (i)){
                this.notificacionError.cambiarMensaje(cliente.getErrores().get(i));
                this.notificacionError.setVisible(true);
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
