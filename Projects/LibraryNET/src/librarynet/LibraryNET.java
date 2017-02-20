/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarynet;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

/**
 *
 * @author dfcastellanosc
 */
public class LibraryNET {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.net.UnknownHostException
     */
    public static void main(String[] args) throws IOException {
        probarConexion("www.google.com", 80);
    }

    public static void urlArchivo(String rutaArchivo) throws MalformedURLException {
        File fichero = new File(rutaArchivo);
        URL urlFile = fichero.toURL();
        System.out.println(urlFile);
    }

    /**
     *
     * @param url de la pagina para probar conexion
     * @param puerto para probar conexion por defecto dejar 80
     * @throws IOException
     */
    public static void probarConexion(String url, int puerto) throws IOException {
        Socket f = new Socket(url, puerto);

        try {
            if (f.isConnected()) {
                System.out.println("Conexion establecida con: " + url + " atraves del puerto " + puerto);
            }
        } catch (Exception e) {
            System.out.println("No se pudo establecer conexion con: " + url + " atraves del puerto " + puerto);
            System.out.println(e.getMessage());
        }
    }

}
