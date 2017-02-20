/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contac.audios.modelo.dto;

import java.util.Date;

/**
 *
 * @author pdgomezl
 */
public class ControlArchivoIVRDTO {

    private String tipoArchivo;
    private String nombreArchivo;
    private String Anexo;
    private Date fechaRegistro;
    private Date fechaModificacion;

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

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
    
    @Override
    public String toString(){
    return "Nombre : "+this.nombreArchivo +" |  Tipo : "+tipoArchivo;
    }

}
