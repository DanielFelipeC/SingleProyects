/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.ServerSocket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dfcastellanosc
 */
public class Robot {

    private static java.awt.Robot robot;

    /**
     * @param args the command line arguments
     * @throws java.awt.AWTException
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws AWTException, IOException {
        ServerSocket as = new ServerSocket(8080);
//    public void cerrarSesion2.0()static {
//        try {
//            robot = new java.awt.Robot();
//        } catch (AWTException ex) {
//            Logger.getLogger(Robot.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        robot.keyPress(KeyEvent.VK_WINDOWS);
//        robot.keyRelease(KeyEvent.VK_WINDOWS);
//        robot.delay(300);
//        robot.keyPress(KeyEvent.VK_RIGHT);
//        robot.keyRelease(KeyEvent.VK_RIGHT);
//        robot.delay(300);
//        robot.keyPress(KeyEvent.VK_RIGHT);
//        robot.keyRelease(KeyEvent.VK_RIGHT);
//        robot.delay(300);
//        robot.keyPress(KeyEvent.VK_UP);
//        robot.keyRelease(KeyEvent.VK_UP);
//        robot.delay(300);
//        robot.keyPress(KeyEvent.VK_UP);
//        robot.keyRelease(KeyEvent.VK_UP);
//        robot.delay(300);
//        robot.keyPress(KeyEvent.VK_UP);
//        robot.keyRelease(KeyEvent.VK_UP);
//        robot.delay(300);
//        robot.keyPress(KeyEvent.VK_ENTER);
//        robot.keyRelease(KeyEvent.VK_ENTER);
//}
    }

    public void apagarEquipo() throws IOException {
        Runtime.getRuntime().exec("shutdown -r -f");
    }

    public static void typeCharacter(java.awt.Robot yoRobot, String textoCool) {

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

                yoRobot.delay(250);

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

    public static void abrirGoogle() throws AWTException, IOException {
        String url = "http://www.google.com";
        java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
    }

    public static void notePadHack() throws AWTException, IOException {
        robot = new java.awt.Robot();
        Runtime.getRuntime().exec("C:\\Windows\\system32\\notepad.exe");
        robot.delay(400);

    }

    public static void bloquearEquipo() throws AWTException {
        robot = new java.awt.Robot();

        robot.keyPress(KeyEvent.VK_WINDOWS);
        robot.keyRelease(KeyEvent.VK_WINDOWS);

        robot.delay(400);

        robot.mouseMove(345, 695);

        robot.delay(600);

        robot.mouseMove(400, 645);

        robot.delay(500);

        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);

        robot.delay(200);
    }

    private final static int teclas[] = {
        KeyEvent.VK_E, KeyEvent.VK_S,
        KeyEvent.VK_T, KeyEvent.VK_A,
        KeyEvent.VK_S, KeyEvent.VK_SPACE,
        KeyEvent.VK_S, KeyEvent.VK_I,
        KeyEvent.VK_E, KeyEvent.VK_N,
        KeyEvent.VK_D, KeyEvent.VK_O,
        KeyEvent.VK_SPACE,
        KeyEvent.VK_H, KeyEvent.VK_A,
        KeyEvent.VK_C, KeyEvent.VK_K,
        KeyEvent.VK_E, KeyEvent.VK_A,
        KeyEvent.VK_D, KeyEvent.VK_O,
        KeyEvent.VK_SPACE,
        KeyEvent.VK_P,
        KeyEvent.VK_O, KeyEvent.VK_R,
        KeyEvent.VK_SPACE,
        KeyEvent.VK_H, KeyEvent.VK_A,
        KeyEvent.VK_C, KeyEvent.VK_K,
        KeyEvent.VK_E, KeyEvent.VK_R,
        KeyEvent.VK_M, KeyEvent.VK_A,
        KeyEvent.VK_N
    };

    public static Long codeHourCompare(Date hora) {
        int contId = 1;
        short horasDia = 0;
        short minutos = 0;
        long codigoFinal = 0;

        Calendar calendario = new GregorianCalendar();
        calendario.setTime(hora);
        int horaActual, minutosActual;

        horaActual = calendario.get(Calendar.HOUR_OF_DAY);
        minutosActual = calendario.get(Calendar.MINUTE);

        try {
            for (;;) {
                if (horasDia == 23 && minutos == 60) {
                    break;
                } else {
                    if (minutos == 60) {
                        horasDia++;
                        minutos = 0;
                    }

                    if (horaActual == horasDia && minutosActual == minutos) {
                        codigoFinal = contId;
                        break;
                    }

                    minutos++;
                    contId++;
                }
            }
            return codigoFinal;

        } catch (ArithmeticException | ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
