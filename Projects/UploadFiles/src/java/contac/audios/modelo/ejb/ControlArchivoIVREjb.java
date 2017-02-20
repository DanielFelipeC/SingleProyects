/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contac.audios.modelo.ejb;

import contac.audios.modelo.dto.ControlArchivoIVRDTO;
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
    private static final String INSERT = "Insert Into ControlArchivoIVR (TipoArchivo,NombreArchivo,Anexo,FechaRegistro,tamanio) values (?,?,?,?,?)";
    private static final String UPDATE = "update ControlArchivoIVR set FechaModificacion = ?,tamanio=?,Anexo=? where NombreArchivo = ? and TipoArchivo = ?";
    private static final String FIND = "select * from ControlArchivoIVR where NombreArchivo = ? and TipoArchivo = ?";
    private static final String FIND_BY_NAME = "select * from ControlArchivoIVR WHERE NombreArchivo LIKE ? ORDER BY FechaRegistro DESC";
    private static final String FIND_ANEXO = "SELECT * from ControlArchivoIVR ORDER BY FechaRegistro DESC ;";

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
                prtm.setString(2, control.getSize());
                prtm.setString(3, control.getAnexo());
                prtm.setString(4, control.getNombreArchivo());
                prtm.setString(5, control.getTipoArchivo());
            } else {
                prtm = conex.prepareStatement(INSERT);
                prtm.setString(1, control.getTipoArchivo());
                prtm.setString(2, control.getNombreArchivo());
                prtm.setString(3, control.getAnexo());
                //-----------------
                prtm.setTimestamp(4, control.getFechaRegistro());
                //-----------------
                prtm.setString(5, control.getSize());
            }
            rs = prtm.executeUpdate();
            //System.out.println(control.toString());
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
            prtm = conexion.prepareStatement(FIND);
            prtm.setString(1, nombreArchivo);
            prtm.setString(2, extencion);

            rs = prtm.executeQuery();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
     * @see ControlArchivoIVRDTO POJO que contendra la informacion de los
     * archivos almacenado s en la base de datos
     * @return Lista de POJOs con la informacion de los archivos almacenados en
     * la base de datos
     * @throws SQLException en caso de que ocurra un error interno en la base de
     * datos como la insercion de un dato fuera del limite
     */
    public List<ControlArchivoIVRDTO> archivosAlmacenados() throws SQLException {
        List<ControlArchivoIVRDTO> listaArchivos = null;
        PreparedStatement prtm = null;
        ResultSet rs = null;

        try {
            conex = ConexionDBEjb.getConexionSqlServer();
            prtm = conex.prepareStatement(FIND_ANEXO);
            rs = prtm.executeQuery();
            if (!rs.wasNull()) {
                listaArchivos = new ArrayList<>();
                while (rs.next()) {
                    ControlArchivoIVRDTO archivo = new ControlArchivoIVRDTO();
                    archivo.setTipoArchivo(rs.getString(2));
                    archivo.setNombreArchivo(rs.getString(3));
                    archivo.setAnexo(rs.getString(4));
                    archivo.setSize(rs.getString(7));
                    archivo.setFechaRegistro(rs.getTimestamp(5));
                    archivo.setFechaModificacion(rs.getTimestamp(6));
                    listaArchivos.add(archivo);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionDBEjb.closeConnection(conex);
            ConexionDBEjb.closePreparedStatement(prtm);
            ConexionDBEjb.closeResultSet(rs);
        }
        return listaArchivos;
    }

    public List<ControlArchivoIVRDTO> findBy(String string) throws SQLException {
        List<ControlArchivoIVRDTO> listaArchivos = null;
        PreparedStatement prtm = null;
        ResultSet rs = null;

        try {
            conex = ConexionDBEjb.getConexionSqlServer();
            prtm = conex.prepareStatement(FIND_BY_NAME);
            prtm.setString(1, "%" + string + "%");
            rs = prtm.executeQuery();
            if (!rs.wasNull()) {
                listaArchivos = new ArrayList<>();
                while (rs.next()) {
                    ControlArchivoIVRDTO archivo = new ControlArchivoIVRDTO();
                    archivo.setTipoArchivo(rs.getString(2));
                    archivo.setNombreArchivo(rs.getString(3));
                    archivo.setAnexo(rs.getString(4));
                    archivo.setSize(rs.getString(7));
                    archivo.setFechaRegistro(rs.getTimestamp(5));
                    archivo.setFechaModificacion(rs.getTimestamp(6));
                    listaArchivos.add(archivo);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionDBEjb.closeConnection(conex);
            ConexionDBEjb.closePreparedStatement(prtm);
            ConexionDBEjb.closeResultSet(rs);
        }
        return listaArchivos;
    }
}
