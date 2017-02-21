/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dfcastellanosc
 */
public class Lareconcha {

    public static java.util.List<java.lang.String> findAll() {
        fghfgh.FindAllProfesionales_Service service = new fghfgh.FindAllProfesionales_Service();
        fghfgh.FindAllProfesionales port = service.getFindAllProfesionalesPort();
        return port.findAll();
    }
    
}
