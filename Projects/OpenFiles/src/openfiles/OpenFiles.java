/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openfiles;

import UpperEssential.UpperEssentialLookAndFeel;
import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author dfcastellanosc
 */
public class OpenFiles {

    private static JFileChooser selectorArchivos;

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws javax.swing.UnsupportedLookAndFeelException
     */
    public static void main(String[] args) throws IOException, UnsupportedLookAndFeelException, AWTException {
//        String fileLocal = "src/archivos/QUERYFINALBUZON.txt"; 
//        Runtime.getRuntime().exec("cmd /c start "+fileLocal);
        retSTring(" Runtime.getRuntime().exec(\"cmd /c start \"+fileLocal);");

    }

    public static void abrirArchivo() throws IOException, UnsupportedLookAndFeelException {

//        UIManager.setLookAndFeel(new UpperEssentialLookAndFeel());
        selectorArchivos = new JFileChooser();
        selectorArchivos.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos Java", "java");
        selectorArchivos.setFileFilter(filtro);

        selectorArchivos.showOpenDialog(selectorArchivos);

        File archivo = selectorArchivos.getSelectedFile();

        if (archivo != null) {
            Desktop.getDesktop().open(archivo);
            System.out.println(archivo.getAbsolutePath());
        } else {
            System.out.println("Nulo");
        }
    }

    public static void retSTring(String textoCool) throws AWTException {
        Robot yoRobot = new Robot();
        for (int i = 0; i < textoCool.length(); i++) {
            try {
                boolean upperCase = Character.isUpperCase(textoCool.charAt(i));
                String KeyVal = Character.toString(textoCool.charAt(i));
                String variableName = " ";
                if (KeyVal.equals(" ")) {
                    variableName = "VK_SPACE";
                } else {
                    variableName = "VK_" + KeyVal.toUpperCase();
                }
//                if (KeyVal.equals("@")) {
//                    variableName = "VK_AT";
//                }

                Class clazz = KeyEvent.class;
                Field field = clazz.getField(variableName);
                int keyCode = field.getInt(null);

                yoRobot.delay(100);

                if (upperCase) {
                    yoRobot.keyPress(KeyEvent.VK_SHIFT);
                }

                yoRobot.keyPress(keyCode);
                yoRobot.keyRelease(keyCode);

                if (upperCase) {
                    yoRobot.keyRelease(KeyEvent.VK_SHIFT);
                }
            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
                System.out.println(e);
            }
        }
    }

}
