/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storedprocedure;

/**
 *
 * @author dfcastellanosc
 */
public class Profesional {
    
    private long idpro;
    private String nombre;
    private String apellido;
    private String numDocumento;

    public long getIdpro() {
        return idpro;
    }

    public void setIdpro(long idpro) {
        this.idpro = idpro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    @Override
    public String toString() {
        return "Profesional{" + "Id: " + idpro + ", Nombre: " + nombre + ", Apellido: " + apellido + ", Numero Documento: " + numDocumento + '}';
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }
    
}
