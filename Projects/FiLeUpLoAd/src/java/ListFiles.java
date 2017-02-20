
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ListFiles {

    public void descargar() throws IOException {

        try {

            String url = "http://i.imgur.com/HZ1hq.jpg"; //dirección url del recurso a descargar
            String name = "lineadecodigo.jpg"; //nombre del archivo destino
            //Directorio destino para las descargas
            String folder = "descargas/";

//Crea el directorio de destino en caso de que no exista
            File dir = new File(folder);

            if (!dir.exists()) {
                if (!dir.mkdir()) {
                    return; // no se pudo crear la carpeta de de
                }
            }

            File file = new File(folder + name);

            URLConnection conn = new URL(url).openConnection();
            conn.connect();
            System.out.println("\nempezando descarga: \n");
            System.out.println(">> URL: " + url);
            System.out.println(">> Nombre: " + name);
            System.out.println(">> tamaño: " + conn.getContentLength() + " bytes");

            InputStream in = conn.getInputStream();
            OutputStream out = new FileOutputStream(file);

            int b = 0;
            while (b != -1) {
                b = in.read();
                if (b != -1) {
                    out.write(b);
                }
            }

            out.close();
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
