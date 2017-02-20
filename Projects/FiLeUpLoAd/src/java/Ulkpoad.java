/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author pdgomezl
 */
@Named(value = "ulkpoadController")
@SessionScoped
public class Ulkpoad implements Serializable {

    private UploadedFile file;
    private boolean value1; 

    private final StreamedContent cd;

    public Ulkpoad() {
        InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/demo/images/w3logo.jpg");
        cd = new DefaultStreamedContent(stream, "image/jpg", "w3logo.jpg");
    
    }
    
    
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload(FileUploadEvent event) throws IOException {
        UploadedFile uploadFile = event.getFile();
        try (FileOutputStream archivoOutput = new FileOutputStream(uploadFile.getFileName())) {
            archivoOutput.write(uploadFile.getContents());
        }

        File f = new File(uploadFile.getFileName());

        if (f.renameTo(new File("C:\\Users\\dfcastellanosc.SOPORTECOS\\Desktop\\Connection F\\" + f.getName()))) {
            System.out.println("Archivo fue movido!");
        } else {
            System.out.println("Archivo no pudo ser movido!");
        }

    }

    public boolean isValue1() {
        return value1;
    }

    public void setValue1(boolean value1) {
        this.value1 = value1;
    }

    public StreamedContent getCd() {
        return cd;
    }

}
