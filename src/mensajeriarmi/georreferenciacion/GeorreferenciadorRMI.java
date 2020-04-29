
package mensajeriarmi.georreferenciacion;

import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import mensajeriarmi.paquete.Paquete;
import mensajeriarmi.paquete.Ubicacion;

/**
 * @author Carlos Andres Rojas
 * @author David Salgado Ospina
 */
public class GeorreferenciadorRMI implements Georreferenciador{
    
    private Conexion conexion;
    private GeorreferenciadorServer servidor;
    
    private ArrayList<Paquete> bufferPaquetes;

    public GeorreferenciadorRMI() {
        this.conexion = new Conexion();
        this.conectar();
        this.bufferPaquetes = new ArrayList<>();
        this.servidor = new GeorreferenciadorServer("127.0.0.1", this);
    }
    
    //////////////////////////////////////////////////////////////////////
        
    public final void conectar(){
        this.conexion.conectar();
    }
    
     //////////////////////////////////////////////////////////////////////
    
    public Ubicacion buscarCiudad(String ciudad, String departamento){
        String sql = "SELECT * FROM ubicaciones WHERE ciudad='"+ciudad+"' AND departamento ='"+departamento+"';";
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
    
    
    //////////////////////////////////////////////////////////////////////////

    @Override
    public Ubicacion georreferenciar(Paquete p) throws RemoteException {
        // Se carga al buffer y se georreferencian a partir de el
        System.out.println("Georreferenciar: "+p.getNombreEmisor());
        String ciudadReceptor = p.getCiudadReceptor().toUpperCase();
        String departamentoReceptor = p.getDepartamentoReceptor().toUpperCase();
        Ubicacion ubicacion = this.buscarCiudad(ciudadReceptor, departamentoReceptor);
        return ubicacion;
    }

    
    
}
