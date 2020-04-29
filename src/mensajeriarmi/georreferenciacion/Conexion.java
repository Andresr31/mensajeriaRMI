/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensajeriarmi.georreferenciacion;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Conexion con la BD.
 * 
 * @author Carlos Andres Rojas
 * @author David Salgado Ospina
 * 
 * 
 */
public class Conexion {
    
    
    
    private Connection conn;
    
    public void conectar() {
        // SQLite connection string
        String url = "jdbc:sqlite:georreferenciaColombia.db";
        conn = null;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Conexión DB exitosa");
        } catch (SQLException e) {
            System.out.println("Falla en conexión DB: " + e.getMessage());
        }

    }
     
    public void closeConnection(Connection conn){
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("No se pudo cerrar la conexión");
        }
    }
    
    public Connection getConexion(){
        return this.conn;
    }
}