/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contac.audios.presentacion;

import contac.audios.modelo.dto.ControlArchivoIVRDTO;
import contac.audios.modelo.ejb.ControlArchivoIVREjb;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.inject.Named;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author pdgomezl
 */
@Named(value = "cargueController")
@RequestScoped
public class CargueController implements Serializable {

    private final String CARPETA = "audios";
    private final String RUTA = "C:\\";
    private UploadedFile file;
    String urlToMove = "";
    private ControlArchivoIVRDTO archivoIVRDTO = null;
    @EJB
    private ControlArchivoIVREjb ejbControlArchivoIvr;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload(FileUploadEvent event) throws IOException, SQLException {
        archivoIVRDTO = new ControlArchivoIVRDTO();
        try {
            UploadedFile uploadFile = event.getFile();
            try (FileOutputStream archivoOutput = new FileOutputStream(uploadFile.getFileName())) {
                archivoOutput.write(uploadFile.getContents());
                archivoOutput.flush();
                archivoOutput.close();
                archivoOutput.getChannel().close();
            }

            File archivoTemp = new File(uploadFile.getFileName());

            Path path = Paths.get(RUTA + CARPETA);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            urlToMove = RUTA + CARPETA + "\\" + archivoTemp.getName();

            archivoIVRDTO.setAnexo(urlToNormiesEyes(RUTA + CARPETA + "/"));
            archivoIVRDTO.setNombreArchivo(extracOnlyName(uploadFile.getFileName()));
            archivoIVRDTO.setTipoArchivo(extractExtension(uploadFile.getFileName()));
            archivoIVRDTO.setFechaRegistro(new Date());
            /*
            System.out.println(archivoIVRDTO.toString());
            ejbControlArchivoIvr.insert(archivoIVRDTO);
             */

            archivoTemp = new File(urlToMove);
            if (archivoTemp.exists()) {
                archivoTemp.delete();
                archivoIVRDTO.setFechaModificacion(new Date());
            }
            archivoTemp = new File(uploadFile.getFileName());
            archivoTemp.renameTo(new File(urlToMove));
            ejbControlArchivoIvr.insert(archivoIVRDTO);

        } catch (IOException | OutOfMemoryError | ArrayIndexOutOfBoundsException | IllegalAccessError e) {
            System.out.println(e.getMessage());
        }

    }

    private String urlToNormiesEyes(String url) {
        String finalUrl = "";
        for (char value : url.toCharArray()) {
            if (value == '\\') {
                finalUrl += "/";
            } else {
                finalUrl += value;
            }
        }
        return finalUrl;
    }

    private String extractExtension(String toConvert) {
        String extrac = "";
        List<Character> toOrder = new ArrayList<>();
        List<Character> list = new ArrayList<>();
        for (char runner : toConvert.toCharArray()) {
            toOrder.add(runner);
        }
        for (int i = toOrder.size(); i > 0; i--) {
            if (toOrder.get(i - 1).equals('.')) {
                break;
            } else {
                extrac += toOrder.get(i - 1);
            }
        }
        for (Character character : extrac.toCharArray()) {
            list.add(character);
        }
        extrac = "";
        for (int i = list.size(); i > 0; i--) {
            extrac += list.get(i - 1);
        }

        return extrac;
    }

    private String extracOnlyName(String toConvert) {
        String extrac = "";
        for (char runner : toConvert.toCharArray()) {
            if (runner == '.') {
                break;
            } else {
                extrac += runner;
            }
        }
        return extrac;
    }

}
