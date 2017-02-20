/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contac.audios.modelo.ejb.conexion.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.ejb.Stateless;

/**
 *
 * @author pdgomezl
 */
@Stateless
public class ConexionDBEjb {

    public static Connection getConeccionSqlServer() {
        Connection conexion = null;
        try {
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String a = "\\";
            String urlFinal = "jdbc:sqlserver://10.99.219.183" + a + "baseinfocs;databaseName=baseControlAudiosIvr;user=sa6;password=Zxcv123456**;";
            conexion = DriverManager.getConnection(urlFinal);
            System.out.println("Se conecto");
        } catch (Exception e) {
            System.err.println("No hay conexion" + e.getMessage());
        }
        return conexion;
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
                System.out.println("Se cerro coneccion");
            }
        } catch (Exception e) {
            System.err.println("Error al cerrar la conexion" + e.getMessage());
        }
    }

    public static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
                System.out.println("Se cerro statement");
            }
        } catch (Exception e) {
            System.err.println("Error al cerrar el resultset" + e.getMessage());
        }
    }

    public static void closePreparedStatement(PreparedStatement sentencia) {
        try {
            if (sentencia != null) {
                sentencia.close();
            }
        } catch (Exception e) {
            System.err.println("Error al cerrar el prepared statement" + e.getMessage());
        }
    }

}
