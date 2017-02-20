/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contac.audios.modelo.ejb;

import contac.audios.modelo.dto.ControlArchivoIVRDTO;
import contac.audios.modelo.dto.FileDTO;
import contac.audios.modelo.ejb.conexion.jdbc.ConexionDBEjb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author pdgomezl
 */
@Stateless
public class ControlArchivoIVREjb {

    private Connection conex;
    private static final String INSERT = "Insert Into ControlArchivoIVR (TipoArchivo,NombreArchivo,Anexo,FechaRegistro) values (?,?,?,?)";
    private static final String UPDATE = "update ControlArchivoIVR set FechaModificacion = ? where NombreArchivo = ? and TipoArchivo = ?";
    private static final String FIND = "select * from ControlArchivoIVR where NombreArchivo = ? and TipoArchivo = ?";
    private static final String FIND_ANEXO = "SELECT * from ControlArchivoIVR;";

    /**
     * Inserta los datos de un objeto nuevo subido a la base de datos sql server
     *
     * @param control es la implementacion de la tabla que contendra los datos
     * de los archivos que se carguen
     * @throws SQLException en caso de que ocurra un error interno en la base de
     * datos como la insercion de un dato fuera del limite
     *
     */
    public void insert(ControlArchivoIVRDTO control) throws SQLException {
        PreparedStatement prtm = null;
        try {
            conex = ConexionDBEjb.getConexionSqlServer();
            int rs = 0;
            if (lazyTruncate(conex, control.getNombreArchivo(), control.getTipoArchivo())) {
                prtm = conex.prepareStatement(UPDATE);
                prtm.setTimestamp(1, control.getFechaModificacion());
                prtm.setString(2, control.getNombreArchivo());
                prtm.setString(3, control.getTipoArchivo());
                rs = prtm.executeUpdate();
            } else {
                prtm = conex.prepareStatement(INSERT);
                prtm.setString(1, control.getTipoArchivo());
                prtm.setString(2, control.getNombreArchivo());
                prtm.setString(3, control.getAnexo());
                //-----------------
                prtm.setTimestamp(4, control.getFechaRegistro());
                //-----------------
                rs = prtm.executeUpdate();
            }
            System.out.println(control.toString());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionDBEjb.closeConnection(conex);
            ConexionDBEjb.closePreparedStatement(prtm);
        }
    }

    /**
     * Indica si un archivo que se esta intentando subir a la base de datos ya
     * existe en caso de que sea cierto retorna verdadero
     *
     * @param conexion la conexion establecida con la base de datos sql server
     * @param nombreArchivo nombre del archivo el cual se esta intentado subir a
     * la base de datos para realizar su busqueda
     * @param extencion extenciaon del archivo el cual se esta intentando subir
     * a la base de datos para realizar su busqueda
     * @return verdadero en caso de que el archivo buscado ya exista en la base
     * de datos
     * @throws SQLException en caso de que ocurra un error interno en la base de
     * datos como la insercion de un dato fuera del limite
     */
    private boolean lazyTruncate(Connection conexion, String nombreArchivo, String extencion) throws SQLException {
        PreparedStatement prtm = null;
        ResultSet rs = null;
        try {
            conex = conexion;

            prtm = conex.prepareStatement(FIND);
            prtm.setString(1, nombreArchivo);
            prtm.setString(2, extencion);

            rs = prtm.executeQuery();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionDBEjb.closeConnection(conex);
            ConexionDBEjb.closePreparedStatement(prtm);
            ConexionDBEjb.closeResultSet(rs);
        }
        if (rs == null) {
            return false;
        }
        return rs.next();
    }

    /**
     * Obtiene los datos de los archivos almacenados en la base de datos y los
     * almacena en un POJO
     *
     * @see FileDTO POJO que contendra la informacion de los archivos almacenado
     * s en la base de datos
     * @return Lista de POJOs con la informacion de los archivos almacenados en
     * la base de datos
     * @throws SQLException en caso de que ocurra un error interno en la base de
     * datos como la insercion de un dato fuera del limite
     */
    public List<FileDTO> archivosAlmacenados() throws SQLException {
        List<FileDTO> listaArchivos = null;
        PreparedStatement prtm = null;
        ResultSet rs = null;

        try {
            conex = ConexionDBEjb.getConexionSqlServer();
            FileDTO file;
            prtm = conex.prepareStatement(FIND_ANEXO);
            rs = prtm.executeQuery();
            if (!rs.wasNull()) {
                listaArchivos = new ArrayList<>();
                while (rs.next()) {
                    file = new FileDTO();
                    file.setTipo(rs.getString(2));
                    file.setNombre(rs.getString(3));
                    file.setUbicacion(rs.getString(4));
                    listaArchivos.add(file);
                }
            }
        } catch (SQLException e) {

        } finally {
            ConexionDBEjb.closeConnection(conex);
            ConexionDBEjb.closePreparedStatement(prtm);
            ConexionDBEjb.closeResultSet(rs);
        }
        return listaArchivos;
    }
}
