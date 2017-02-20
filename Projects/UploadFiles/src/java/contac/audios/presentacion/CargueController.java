/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contac.audios.presentacion;

import contac.audios.modelo.dto.ControlArchivoIVRDTO;
import contac.audios.modelo.ejb.ControlArchivoIVREjb;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author pdgomezl
 */
@Named(value = "cargueController")
@RequestScoped
public class CargueController implements Serializable {

    @Inject
    private UrlController urlController;
    @Inject
    private ArchivosErroneosController archivosErroneosController;
    private final int unitKiloByteInByte = 1024;
    private final int unitMegaByteInKiloByte = 1048576;
    private UploadedFile file;
    private String urlToMove;
    private ControlArchivoIVRDTO archivoIVRDTO = null;
    @EJB
    private ControlArchivoIVREjb ejbControlArchivoIvr;
    private List<ControlArchivoIVRDTO> items;
    private String findBySomething;

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
    public void upload(FileUploadEvent event) throws IOException, SQLException, InterruptedException {
        //String ruta = urlController.getRuta();
        String url = urlController.getCarpeta();
        archivoIVRDTO = new ControlArchivoIVRDTO();
        try {
            if (event.getFile() != null) {
                file = event.getFile();
                try (FileOutputStream archivoOutput = new FileOutputStream(file.getFileName())) {
                    archivoOutput.write(file.getContents());
                    archivoOutput.flush();
                    archivoOutput.close();
                    archivoOutput.getChannel().close();
                }

                File archivoTemp = new File(file.getFileName());

                Path path = Paths.get(url);
                if (!Files.exists(path)) {
                    Files.createDirectories(path);
                }

                urlToMove = url + "\\" + archivoTemp.getName();

                archivoTemp = new File(urlToMove);
                if (archivoTemp.exists()) {

                    /*
                GenericObject generic = new GenericObject();
                generic.setNombre(file.getFileName());
                generic.setPeso(informaticSize(file.getSize()));
                generic.setRuta(ruta + ":\\" + carpeta + "\\");
                generic.setContent(file.getContents());
                     */
                    archivosErroneosController.getNombresArchivosErroneos().add(file);
                    archivosErroneosController.setSelected(url + "\\");
                    JsfUtil.addWarningMessage("El archivo " + file.getFileName() + " ya existe");

                    //archivoTemp.delete();
                    //archivoIVRDTO.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                } else {
                    archivoIVRDTO.setFechaRegistro(new Timestamp(System.currentTimeMillis()));
                    archivoIVRDTO.setSize(informaticSize(file.getSize()));
                    archivoIVRDTO.setAnexo(url + "\\" + file.getFileName());
                    archivoIVRDTO.setNombreArchivo(extracOnlyName(file.getFileName()));
                    archivoIVRDTO.setTipoArchivo(extractExtension(file.getFileName()));
                    archivoTemp = new File(file.getFileName());
                    archivoTemp.renameTo(new File(urlToMove));
                    //guardar archivo en bd - ejbControlArchivoIvr.insert(archivoIVRDTO);
                    JsfUtil.addSuccessMessage("Archivo subido y archivado ");
                }
            }
        } catch (OutOfMemoryError | ArrayIndexOutOfBoundsException | IllegalAccessError | InvalidPathException e) {
            JsfUtil.addErrorMessage("Ocurrio un error :/ !");
            JsfUtil.addErrorMessage("Asegurese de que la ruta no contenga caracteres especiales!");
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
    public String extractExtension(String toConvert) {
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
    public String extracOnlyName(String toConvert) {
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
    public List<ControlArchivoIVRDTO> getItems() throws SQLException {
        if (items == null) {
            items = ejbControlArchivoIvr.archivosAlmacenados();
        }
        return items;
    }

    /**
     * Permite descargar un archivo almacenado en el servidor
     *
     * @param archivoPath ruta del archivo
     * @param nombre nombre del archivo
     * @param tipo tipo del archivo
     * @return archivo para descargar de tipo streamedContent
     * @throws FileNotFoundException en caso de que la rutaespecificada o el
     * nombre del archivo esten mal no se reconocera estte y saltara la
     * exception de no encontrado
     * @throws IOException en caso de que ocurra un error interno como la falta
     * de permisos al intentar mover el archivo
     */
    public StreamedContent getArchivoDownl(String archivoPath, String nombre, String tipo) throws FileNotFoundException, IOException {
        InputStream nel;
        String archivo = nombre + "." + tipo;
        try {
            /*
        Path path = Paths.get("C:\\audios\\ProductosM.xhtml");
        byte[] data = Files.readAllBytes(path);
        byte[] bFile = new byte[(int) file.length()];
        fileInputStream = new FileInputStream(file);
        fileInputStream.read(data);
             */
            File fileFromDirectory = new File(archivoPath);
            if (archivo != null && fileFromDirectory.exists()) {
                nel = new FileInputStream(fileFromDirectory);
                return new DefaultStreamedContent(nel, "", archivo);
            } else {
                JsfUtil.addErrorMessage("Este Archivo ya no existe en la carpeta contenedora");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            JsfUtil.addWarningMessage("No se encuentra el archivo " + incompleteText(nombre, 20));
        }
        return null;
    }

    /**
     * Permite transformar un valor en bytes a su valor mas dependiente
     *
     * @param bytes valor en bytes
     * @return valor de tipo cadena en su valor dependiente (si el valor en
     * bytes sobre-pasa un KB entonces retornara MB)
     */
    public String informaticSize(long bytes) {
        double kb = bytes / 1024.0;
        double mb = bytes / 1048576.0;
        /*
        double gb = bytes / Math.pow(mb, 2);
        double tb = bytes / Math.pow(gb, 2);
         */
        DecimalFormat dec = new DecimalFormat("0.00");

        String size = "0 PTBytes";
        /*if (tb > 1) {
            size = dec.format(tb).concat(" TB");
        } else if (gb > 1) {
            size = dec.format(gb).concat(" GB");
        } else*/ if (mb > 1) {
            size = dec.format(mb).concat(" MB");
        } else if (kb > 1) {
            size = dec.format(kb).concat(" KB");
        } else {
            size = bytes + " B";
        }
        return size;
    }

    public static String incompleteText(String text, int max) {
        String incompleteText = text;
        if (text.length() > max) {
            incompleteText = "";
            short cont = 0;
            for (char runnerObject : text.toCharArray()) {
                if (cont == max) {
                    break;
                } else {
                    incompleteText += runnerObject;
                    cont++;
                }
            }
            return incompleteText + "...";
        }
        return incompleteText;
    }

    public int contCharacters(String string) {
        int cont = 0;
        for (Object object : string.toCharArray()) {
            cont++;
        }
        return cont;
    }

    public String getFindBySomething() throws SQLException {
        return findBySomething;
    }

    public void setFindBySomething(String findBySomething) throws SQLException {
        try {
            this.findBySomething = findBySomething;
            items = ejbControlArchivoIvr.findBy(findBySomething);
            if (items.isEmpty()) {
                items = ejbControlArchivoIvr.archivosAlmacenados();
                JsfUtil.addWarningMessage("No hay resultados");
            }
            this.findBySomething = null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void reset() {
        this.items = null;
        JsfUtil.addSuccessMessage("Actualizado");
    }

}
