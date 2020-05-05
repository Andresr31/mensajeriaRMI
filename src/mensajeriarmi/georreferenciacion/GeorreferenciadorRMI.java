
package mensajeriarmi.georreferenciacion;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mensajeriarmi.objetos.Georreferenciador;
import mensajeriarmi.paquete.Paquete;
import mensajeriarmi.paquete.Ubicacion;
import mensajeriarmi.objetos.ReceptorPaquetes;

/**
 * @author Carlos Andres Rojas
 * @author David Salgado Ospina
 */
public class GeorreferenciadorRMI implements Georreferenciador{
    
    private Conexion conexion;
    private GeorreferenciadorServer servidor;
    
    private ArrayList<Paquete> bufferPaquetes;
    
    private Procesador procesador;
    private ReceptorPaquetes receptor;

    public GeorreferenciadorRMI() {
        this.conexion = new Conexion();
        this.conectar();
        this.bufferPaquetes = new ArrayList<>();
        this.servidor = new GeorreferenciadorServer("127.0.0.1",4400, this);
        
        this.procesador = new Procesador(this);
        this.procesador.start();
    }
    
    //////////////////////////////////////////////////////////////////////
        
    public final void conectar(){
        this.conexion.conectar();
    }
    
     //////////////////////////////////////////////////////////////////////
    
    public Ubicacion buscarCiudad(String ciudad, String departamento){
        String sql = "SELECT * FROM ubicaciones WHERE ciudad='"+ciudad.toUpperCase()+"' AND departamento ='"+departamento.toUpperCase()+"';";
        Ubicacion u = new Ubicacion(0, 0);
        try (
                Statement stmt = this.conexion.getConexion().createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                u.setLatitud(rs.getDouble("latitud"));
                u.setLongitud(rs.getDouble("longitud"));
//                System.out.println(rs.getString("latitud"));
//                System.out.println(rs.getString("longitud"));
//                System.out.println(rs.getString("departamento"));
//                System.out.println(rs.getString("ciudad"));
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar todo: " + e.getMessage());
        }
        return u;
    }
    
    public void almacenarPaquete(Paquete p){
        try {
            this.receptor.almacenarPaquete(p);
        } catch (RemoteException ex) {
            Logger.getLogger(GeorreferenciadorRMI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void agregarError(String error) throws RemoteException{
        this.receptor.agregarError(error);
    }
    
    //////////////////////////////////////////////////////////////////////////

    @Override
    public String georreferenciar(Paquete p,ReceptorPaquetes r) throws RemoteException {
        // Se carga al buffer y se georreferencian a partir de el
        System.out.println("\n////////////////////////////////////////////\n");
        System.out.println("-> Georreferenciar con destino: "+p.getCiudadReceptor());
        System.out.println("-> Georreferenciar de: "+p.getCiudadEmisor());
        System.out.println("-> Georreferenciar para: "+p.getCiudadReceptor());
        this.receptor = r;
//        String ciudadReceptor = p.getCiudadReceptor().toUpperCase();
//        String departamentoReceptor = p.getDepartamentoReceptor().toUpperCase();
//        Ubicacion ubicacion = this.buscarCiudad(ciudadReceptor, departamentoReceptor);
        this.procesador.agregarPaquete(p);
        return "200";
    }

    
    
}
