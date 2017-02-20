
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilePermission;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.PermissionCollection;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.io.IOUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dfcastellanosc
 */
public class NewMain {

    public static void main(String[] args) throws IOException {

        FileInputStream myStream = new FileInputStream("D:\\logout.png");
        
        byte[] imageInBytes = IOUtils.toByteArray(myStream);
        
        System.out.println(imageInBytes);

    }

}
