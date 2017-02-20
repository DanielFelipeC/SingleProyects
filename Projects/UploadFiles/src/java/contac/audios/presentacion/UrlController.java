/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contac.audios.presentacion;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author pdgomezl
 */
@Named(value = "urlController")
@SessionScoped
public class UrlController implements Serializable {

    private final String carpeta = System.getProperty("user.home") + "\\Desktop\\side deck\\";
    //private String ruta = "C";

    /**
     * Creates a new instance of UrlController
     */
    public UrlController() {
    }

    public String getCarpeta() {
        return carpeta;
    }
    /*
    public void setCarpeta(String carpeta) {
        this.carpeta = carpeta;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
     */
}
