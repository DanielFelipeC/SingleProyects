/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contac.audios.modelo.ejb;

import contac.audios.modelo.dto.ControlArchivoIVRDTO;
import contac.audios.modelo.ejb.conexion.jdbc.ConexionDBEjb;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public void insert(ControlArchivoIVRDTO control) throws SQLException {
        try {
            conex = ConexionDBEjb.getConeccionSqlServer();
            PreparedStatement prtm;
            int rs = 0;
            if (lazyTruncate(conex, control.getNombreArchivo(), control.getTipoArchivo())) {
                prtm = conex.prepareStatement(UPDATE);
                Date dateModificaion = new Date(control.getFechaModificacion().getTime());
                prtm.setDate(1, dateModificaion);
                prtm.setString(2, control.getNombreArchivo());
                prtm.setString(3, control.getTipoArchivo());
                rs = prtm.executeUpdate();
            } else {
                prtm = conex.prepareStatement(INSERT);
                prtm.setString(1, control.getTipoArchivo());
                prtm.setString(2, control.getNombreArchivo());
                prtm.setString(3, control.getAnexo());
                //-----------------
                if (control.getFechaRegistro() != null) {
                    Date dateRegistro = new Date(control.getFechaRegistro().getTime());
                    prtm.setDate(4, dateRegistro);
                } else {
                    prtm.setDate(4, null);
                }

                //-----------------
                rs = prtm.executeUpdate();
            }
            System.out.println(control.toString());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean lazyTruncate(Connection conexion, String nombreArchivo, String extencion) throws SQLException {
        conex = conexion;
        PreparedStatement prtm;
        ResultSet rs = null;

        prtm = conex.prepareStatement(FIND);
        prtm.setString(1, nombreArchivo);
        prtm.setString(2, extencion);

        rs = prtm.executeQuery();

        if (rs.next()) {
            return true;
        } else {
            return false;
        }

    }
}
