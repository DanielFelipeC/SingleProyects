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

    /**
     * Establece la conexion a la base de datos
     *
     * @return conexion a la base de datos en sql server
     */
    public static Connection getConexionSqlServer() {
        Connection conexion = null;
        try {
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String a = "\\";
            String urlFinal = "jdbc:sqlserver://10.99.219.183" + a + "baseinfocs;databaseName=baseControlAudiosIvr;user=sa6;password=Zxcv123456**;";
            conexion = DriverManager.getConnection(urlFinal);
        } catch (Exception e) {
            System.err.println("No hay conexion" + e.getMessage());
        }
        return conexion;
    }

    /**
     * Cierra la conexion establecida con la base de datos
     *
     * @param con conexion previamene iniciada sql server
     */
    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            System.err.println("Error al cerrar la conexion" + e.getMessage());
        }
    }

    /**
     * Cierra el enlace de datos de interfaz de usuario, el desplazamiento hacia
     * delante y hacia atrás, y la actualización de datos en la base de datos.
     *
     * @param rs enlace entre la interfaz de usuario y la abse de datos sql
     * server
     */
    public static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            System.err.println("Error al cerrar el resultset" + e.getMessage());
        }
    }

    /**
     * Cierra la La sentencia SQL contenida en un objeto PreparedStatement
     *
     * @param sentencia sentencia sql PreparedStatement
     */
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
