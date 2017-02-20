/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excel;

import java.util.Properties;

/**
 *
 * @author dfcastellanosc
 */
public class UsuerLocal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Runtime aplicacion = Runtime.getRuntime();
//        try {
//            aplicacion.exec("cmd.exe type nul > c:\\%time%");
////             Properties df = System.getProperties();
//////             System.out.println(df);
////            String version = System.getProperty("os.version";
////            System.out.println(System.getSecurityManager());
//        } catch (Exception e) {
//        }

        String Str = new String("Welcome to Tutorialspoint.com");

        System.out.print("Return Value :");
        System.out.println(Str.matches("(.*)Tutorials(.*)"));

        System.out.print("Return Value :");
        System.out.println(Str.matches("Tutorials"));

        System.out.print("Return Value :");
        System.out.println(Str.matches("(.*)Welcome"));

    }
}
