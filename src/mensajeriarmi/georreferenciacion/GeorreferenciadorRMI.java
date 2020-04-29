
package mensajeriarmi.georreferenciacion;

import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Carlos Andres Rojas
 * @author David Salgado Ospina
 */
public class GeorreferenciadorRMI {
    
    private Conexion conexion;

    public GeorreferenciadorRMI() {
        this.conexion = new Conexion();
        this.conectar();
    }
    
    //////////////////////////////////////////////////////////////////////
        
    public final void conectar(){
        this.conexion.conectar();
    }
    
     //////////////////////////////////////////////////////////////////////
    
    public String buscarCiudad(String ciudad, String departamento){
        String sql = "SELECT * FROM ubicaciones WHERE ciudad='"+ciudad+"' AND departamento ='"+departamento+"';";

        try (
                Statement stmt = this.conexion.getConexion().createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                System.out.println(rs.getString("latitud"));
                System.out.println(rs.getString("longitud"));
                System.out.println(rs.getString("departamento"));
                System.out.println(rs.getString("ciudad"));
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar todo: " + e.getMessage());
        }
        return null;
    }
    
    
    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
    public static void main(String[] args){
        GeorreferenciadorRMI g = new GeorreferenciadorRMI();
        g.buscarCiudad("MANIZALES", "CALDAS");
    }
    
    
}
