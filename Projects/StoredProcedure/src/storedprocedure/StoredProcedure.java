/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storedprocedure;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Parameter;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
import javax.swing.JOptionPane;
import org.eclipse.persistence.queries.StoredProcedureCall;

/**
 *
 * @author dfcastellanosc
 */
public class StoredProcedure {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {

        try {
            nombreProfesional("e");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(StoredProcedure.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void time() {
        Scanner sc = new Scanner(System.in);
        int num, hora, minutos, segundos, dia;
        System.out.println("Aqui los segundo .l.");
        num = sc.nextInt();
        dia = num / 86400;
        hora = num / 3600;
        minutos = (num - (3600 * hora)) / 60;
        segundos = num - ((hora * 3600) + (minutos * 60));
        System.out.println(dia + "dias " + hora + "h " + minutos + "m " + segundos + "s");

        for (int i = 0; i <= hora; i++) {
            System.out.println(i);
        }
    }

    public static void updateStoredProcedurePro(String name, String lastName, String documentUpd, String documentSearch) {
          
        Connection con = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();

            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            con = DriverManager.getConnection(passwordDB());

//            PreparedStatement prstmt = null;
//            ResultSet rs = null;
            CallableStatement callstmt = null;

            callstmt = con.prepareCall("EXEC updateEntPro ?,?,?,? ");

            callstmt.setString(1, name);
            callstmt.setString(2, lastName);
            callstmt.setString(3, documentUpd);
            callstmt.setString(4, documentSearch);
            callstmt.execute();

            System.out.println("Se actualizo el profesional");

            callstmt.close();
            con.close();

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void nombreProfesional(String nombre) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        Connection con = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();

            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            con = DriverManager.getConnection(passwordDB());

//            PreparedStatement prstmt = null;
            ResultSet rs = null;
            CallableStatement callstmt = null;

//            List<String> nombres = new ArrayList<>();
               callstmt = con.prepareCall("EXEC profesionalNombre ?");

            callstmt.setString(1, "%" + nombre + "%");
            rs = callstmt.executeQuery();

//            Profesional pro;
//            List<Profesional> capos = new ArrayList<>();
            while (rs.next()) {
//                pro = new Profesional();
//                pro.setIdpro(rs.getLong(1));
//                pro.setNombre(rs.getString(2));
//                pro.setApellido(rs.getString(3));
//                pro.setNumDocumento(rs.getString(4));
//                capos.add(pro);
//                System.out.println(pro.toString());
                System.out.println(rs.getString(1));
            }
//            System.out.println(capos.size());
            callstmt.close();
            con.close();

//            return nombres;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            System.out.println(e.getMessage());
        }
//        return null;
    }

    public static void storeProcedureJPASQLServer() {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("StoredProcedurePUSQLServer");
            EntityManager em = factory.createEntityManager();

            em.getTransaction().begin();
            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("profesionalNombre");
//            StoredProcedureCall storedProcedure = new StoredProcedureCall();

            storedProcedure.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
//            storedProcedure.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            storedProcedure.setParameter(1, "Daniel Francisco");

            storedProcedure.execute();

            Parameter resultado = storedProcedure.getParameter("NamePro");

            System.out.println(resultado.getName());

            em.getTransaction().commit();
            em.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void storeProcedureJDBCSQLServer() {
        Connection con = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();

            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            con = DriverManager.getConnection(passwordDB());

            PreparedStatement prstmt = null;
            ResultSet rs = null;
            CallableStatement callstmt = null;

            String nameIN = "admin2";

//          callstmt = con.prepareCall("{ call examUserSP(?,?) }");
            callstmt = con.prepareCall("exec examUserSP ?,?");

            callstmt.setString(1, nameIN);
            callstmt.setString(2, "example@gmial.com");

            callstmt.execute();

            System.out.println("Se ejecuto");

            closePreparedStatement(prstmt);
            closeResultSet(rs);
            con.close();

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void storeProcedureJPAMySQL() {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("StoredProcedurePU");;
            EntityManager em = factory.createEntityManager();

            em.getTransaction().begin();
            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("prodceureCaTa");

            storedProcedure.registerStoredProcedureParameter("nameT", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("descr", String.class, ParameterMode.OUT);
            storedProcedure.setParameter("nameT", "grupos");

            storedProcedure.execute();

            String descripcion = (String) storedProcedure.getOutputParameterValue("descr");

            System.out.println("Descripcion:" + descripcion);

            em.getTransaction().commit();
            em.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void storedProcedureJDBCMySQL() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String urlFinal = "jdbc:mysql://localhost:4306/connection?user=user&password=123";

            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            con = DriverManager.getConnection(urlFinal);

            PreparedStatement prstmt = null;
            ResultSet rs = null;
            CallableStatement callstmt = null;

            String nameIN = "grupos";

            callstmt = con.prepareCall("{call prodceureCaTa(?, ?)}");

            callstmt.setString(1, nameIN);
            callstmt.registerOutParameter(2, Types.VARCHAR);

            System.out.println("Llamando stored procedure prodceureCaTa('" + nameIN + "', ?)");
            callstmt.execute();

            String nameOUT = callstmt.getString(2);

            System.out.println("OUT / Descripcion = " + nameOUT);

            closePreparedStatement(prstmt);
            closeResultSet(rs);
            con.close();

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            System.out.println(e.getMessage());
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

    public static String passwordDB() {
        String urlFinal = "URLDataBase";
        return urlFinal;
    }
}
