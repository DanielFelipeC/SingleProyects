/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contac.audios.presentacion;

import contac.audios.modelo.dto.ControlArchivoIVRDTO;
import contac.audios.modelo.dto.FileDTO;
import contac.audios.modelo.ejb.ControlArchivoIVREjb;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
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
    private String urlToMove;
    private ControlArchivoIVRDTO archivoIVRDTO = null;
    @EJB
    private ControlArchivoIVREjb ejbControlArchivoIvr;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    /**
     * Recibe un objeto generico de tipo FileUploadEvent el cual se transformara
     * a uno de tipo File para obtener sus atributos y crear este en un
     * directorio por defecto c:/audios
     *
     * @param event objeto generico subido al aplicativo
     * @throws IOException en caso de que ocurra un error al intentar encontrar
     * el archivo,crearlo,moverlo
     * @throws SQLException en caso de que ocurra un error interno en la base de
     * datos como puede ser un dato que supera el limite establecido
     */
    public void upload(FileUploadEvent event) throws IOException, SQLException {
        archivoIVRDTO = new ControlArchivoIVRDTO();
        try {
            file = event.getFile();
            try (FileOutputStream archivoOutput = new FileOutputStream(file.getFileName())) {
                archivoOutput.write(file.getContents());
                archivoOutput.flush();
                archivoOutput.close();
                archivoOutput.getChannel().close();
            }

            File archivoTemp = new File(file.getFileName());

            Path path = Paths.get(RUTA + CARPETA);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            urlToMove = RUTA + CARPETA + "\\" + archivoTemp.getName();

            archivoTemp = new File(urlToMove);
            if (archivoTemp.exists()) {
                archivoTemp.delete();
                archivoIVRDTO.setNombreArchivo(extracOnlyName(file.getFileName()));
                archivoIVRDTO.setTipoArchivo(extractExtension(file.getFileName()));
                archivoIVRDTO.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
            } else {
                archivoIVRDTO.setAnexo(urlToNormiesEyes(RUTA + CARPETA + "/"));
                archivoIVRDTO.setNombreArchivo(extracOnlyName(file.getFileName()));
                archivoIVRDTO.setTipoArchivo(extractExtension(file.getFileName()));
                archivoIVRDTO.setFechaRegistro(new Timestamp(System.currentTimeMillis()));
            }
            archivoTemp = new File(file.getFileName());
            archivoTemp.renameTo(new File(urlToMove));
            ejbControlArchivoIvr.insert(archivoIVRDTO);

        } catch (IOException | OutOfMemoryError | ArrayIndexOutOfBoundsException | IllegalAccessError e) {
            System.out.println(e.getMessage());
        }
        file = null;
    }

    /**
     * Transforma una url que contenga back slash separator de tipo "\" a simple
     * slash separator "/"
     *
     * @param url url "malformada" para transformar a una de tipo simple slash
     * separator"/"
     * @return url de tipo String de forma simple slash separator
     */
    private String urlToNormiesEyes(String url) {
        String finalUrl = "";
        for (char value : url.toCharArray()) {
            if (value == '\\') {
                finalUrl += "/";
            } else {
                finalUrl += value;
            }
        }
        return finalUrl + file.getFileName();
    }

    /**
     * Extrae la extencion de el nombre de un archivo como .exe/.pdf/.txt
     *
     * @param toConvert Nombre del archivo
     * @return Extencion del archivo solicitado
     */
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

    /**
     * Recibe el nombre completo (cuando es obtenido directamente de un archivo
     * de tipo File) de un archivo para obtener solo su nombre
     *
     * @param toConvert Nombre del archivo
     * @return Nombre del archivo separado de su extencion
     */
    private String extracOnlyName(String toConvert) {
        String extrac = "";
        boolean found = false;
        List<Character> toOrder = new ArrayList<>();
        for (char runner : toConvert.toCharArray()) {
            toOrder.add(runner);
        }

        for (int i = toOrder.size(); i > 0; i--) {
            if (toOrder.get(i - 1).equals('.')) {
                found = true;
                toOrder.remove(i - 1);
                break;
            } else {
                toOrder.remove(i - 1);
            }
        }

        if (found) {
            for (char character : toOrder) {
                extrac += character;
            }
        }
        return extrac;
    }

    /**
     * Obtiene objetos los cuales contendran la informacion de los archivos
     * almacenados en la base de datos
     *
     * @return Lista de objetos con informacion de los archivos almacenados en
     * la base de datos
     * @throws SQLException en caso de que ocurra un error interno en la base de
     * datos como la insercion de un dato que sobrepasa el limite
     */
    public List<FileDTO> regresar() throws SQLException {
        return ejbControlArchivoIvr.archivosAlmacenados();
    }

}
