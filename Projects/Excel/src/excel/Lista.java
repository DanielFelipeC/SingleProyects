/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excel;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

/**
 *
 * @author dfcastellanosc
 */
public class Lista {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
//        String path = "C:/";
//
//        String files;
//        File folder = new File(path);
//        File[] listOfFiles = folder.listFiles();
//
//        for (int i = 0; i < listOfFiles.length; i++) {
//
//            if (listOfFiles[i].isFile()) {
//                files = listOfFiles[i].getName();
//                System.out.println(files);
//                Process p = Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL " + files);
//            }
//        }

        Calendar calendar = Calendar.getInstance();
// Obtenemos el valor del año, mes y día.
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);
        
        calendar.add(Calendar.DATE, 7);
        System.out.println(calendar.toString());
        System.out.println("AÑO ACTUAL: " + year);
        System.out.println("MES ACTUAL: " + month);
        System.out.println("DÍA ACTUAL: " + date);
    }

}
