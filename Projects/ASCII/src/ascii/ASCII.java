/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ascii;

/**
 *
 * @author dfcastellanosc
 */
public class ASCII {

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        String sCadena = "empty-statement";
        for (int x = 0; x < sCadena.length(); x++) {
            System.out.println(sCadena.charAt(x) + " = " + sCadena.codePointAt(x));
        };
    }

}
