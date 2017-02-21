/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consumirrest;

/**
 *
 * @author dfcastellanosc
 */
public class ConsumirRest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        NewJerseyClient ksdjgniof = new NewJerseyClient();
        
        String manco = ksdjgniof.nombreSocio(String.class, "Petser");
        
        System.out.println(manco);
    }
    
}
