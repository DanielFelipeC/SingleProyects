/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examplesmain;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.util.Scanner;

/**
 *
 * @author dfcastellanosc
 */
public class ExamplesMain {

    /**
     * @param args the command line arguments
     * @throws java.awt.AWTException
     */
    public static void main(String[] args) throws AWTException {
        
        Scanner tec = new Scanner(System.in);
        String sdf = tec.nextLine();
  
        typeCharacter(sdf);

    }
    
    public static void typeCharacter(String textoCool) throws AWTException {
        
        Robot yoRobot = new java.awt.Robot();

        for (int i = 0; i < textoCool.length(); i++) {
            try {
                boolean upperCase = Character.isUpperCase(textoCool.charAt(i));
                String KeyVal = Character.toString(textoCool.charAt(i));
                String variableName = "";
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

}
