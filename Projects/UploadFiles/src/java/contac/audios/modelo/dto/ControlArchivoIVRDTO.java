/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contac.audios.modelo.dto;

import java.sql.Timestamp;

/**
 *
 * @author pdgomezl
 */
public class ControlArchivoIVRDTO {

    private String tipoArchivo;
    private String nombreArchivo;
    private String Anexo;
    private Timestamp fechaRegistro;
    private Timestamp fechaModificacion;
    private String size;

    public String getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getAnexo() {
        return Anexo;
    }

    public void setAnexo(String Anexo) {
        this.Anexo = Anexo;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Timestamp getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Timestamp fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Nombre : " + this.nombreArchivo + " |  Tipo : " + tipoArchivo;
    }

}
