
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dfcastellanosc
 */
public class qwerty {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         try{
          Runtime.getRuntime().exec("cmd /c start C:\\\"Users\\dfcastellanosc.SOPORTECOS\\Desktop\"\\registroempleados.xls");
          }catch(IOException  e){
              e.printStackTrace();
          }
    }

}
