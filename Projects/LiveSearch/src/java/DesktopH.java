
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dfcastellanosc
 */
public class DesktopH {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        File path = new File("C:\\Users\\dfcastellanosc.SOPORTECOS\\Downloads\\Packt.JavaScript.Functional.Programming.for.JavaScript.Developers.B01LD8K5DY.pdf");
        Desktop.getDesktop().open(path);
    }

}
