/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

import UpperEssential.UpperEssentialLookAndFeel;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.Properties;
import javax.mail.AuthenticationFailedException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

/**
 *
 * @author dfcastellanosc
 */
public class Mail {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MessagingException, UnsupportedLookAndFeelException, InterruptedException {

//        UIManager.setLookAndFeel(new UpperEssentialLookAndFeel());
        Login log = new Login();
//        log.setLocation(450, 200);

        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension ventana = log.getSize();
        log.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);

        URL url = ClassLoader.getSystemResource("mail/login.png");
        ImageIcon icon = new ImageIcon(url);
        log.setTitle("Login");

        log.setIconImage(icon.getImage());

        log.setVisible(true);

    }

    public static void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == 10) {

            System.out.println("enter");
        }
    }

    public static void enviarCorreo() throws MessagingException {
        java.util.Properties mailServerProperties;
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.host", "smtp.gmail.com");
        mailServerProperties.put("mail.smtp.socketFactory.port", "465");
        mailServerProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.port", "465");

        javax.mail.Session getMailSession = Session.getDefaultInstance(
                mailServerProperties,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("aqui el correo", "aqui el password");
            }
        });

        javax.mail.internet.MimeMessage generateMailMessage;
        generateMailMessage = new MimeMessage(getMailSession);
//Estableciendo el destino (TO)
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("aqui para quien va el correo"));

//Estableciendo el destino de la copia (CC)
        generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("aqui para quien la copia del correo"));

//Estableciendo el destino de la copia oculta (BCC)
//        generateMailMessage.addRecipient(Message.RecipientType.BCC, new InternetAddress("dfelipecast2597@hotmail.com"));
//Estableciendo el titulo del mensaje (subject)
        generateMailMessage.setSubject("Java Mail");

// Estableciendo el contenido del correo electronico enriquesido(HTML)
        String bodyEmail = "<div style=\"background: tomato;\n"
                + "  padding: 5px;\n"
                + "  width: 200px;\n"
                + "  height: 150px;\n"
                + "  margin-top: 10px;\n"
                + "  \n"
                + "  line-height: 150px;\n"
                + "  color: white;\n"
                + "  font-weight: bold;\n"
                + "  font-size: 15px;\n"
                + "  text-align: center;\">Correo desde gmail</div><p>Enviado de forma exitosa</p>";
        generateMailMessage.setContent(bodyEmail, "text/html");

//Finalmente se envia el correo
        javax.mail.Transport.send(generateMailMessage);
    }

    public static boolean validarCredencialesCorreo(String username, String password) {
        boolean result = false;
        try {
            Properties props;

            props = System.getProperties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            Session session = Session.getInstance(props, null);
            Transport transport = session.getTransport("smtp");
            transport.connect(username, password);
            transport.close();
            result = true;

        } catch (AuthenticationFailedException e) {
            System.out.println(e.toString() + "SMTP: Autenticacion Fallida");

        } catch (MessagingException e) {
            System.out.println(e.toString() + "SMTP: Messaging Exception Occurred");
        } catch (Exception e) {
            System.out.println(e.toString() + "SMTP: Exception Desconocida");
        }

        return result;
    }

}
