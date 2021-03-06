/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contac.audios.presentacion;

import contac.audios.modelo.dto.ControlArchivoIVRDTO;
import contac.audios.modelo.ejb.ControlArchivoIVREjb;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author pdgomezl
 */
@Named(value = "archivosErroneosController")
@SessionScoped
public class ArchivosErroneosController implements Serializable {

    private List<UploadedFile> nombresArchivosErroneos = new ArrayList<>();
    private String ruta;
    @Inject
    private CargueController cargueController;
    @EJB
    private ControlArchivoIVREjb ejbControlArchivoIvr;

    /**
     * Creates a new instance of ArchivosErroneosontroller
     */
    public ArchivosErroneosController() {
    }

    public List<UploadedFile> getNombresArchivosErroneos() {
        return nombresArchivosErroneos;
    }

    public void setNombresArchivosErroneos(List<UploadedFile> nombresArchivosErroneos) {
        this.nombresArchivosErroneos = nombresArchivosErroneos;
    }

    public String getSelected() {
        return ruta;
    }

    public void setSelected(String ruta) {
        this.ruta = ruta;
    }

    public void reemplazar(UploadedFile up) throws IOException, Exception {
        File file = new File(ruta + up.getFileName());
        ControlArchivoIVRDTO archivoIVRDTO;
        try {
            archivoIVRDTO = new ControlArchivoIVRDTO();
            if (file.exists()) {
                file.delete();
                // up.write(up.getFileName());
                file = new File(up.getFileName());
                file.renameTo(new File(ruta + up.getFileName()));
            }

            archivoIVRDTO.setSize(cargueController.informaticSize(up.getSize()));
            archivoIVRDTO.setAnexo(ruta + up.getFileName());
            archivoIVRDTO.setNombreArchivo(cargueController.extracOnlyName(up.getFileName()));
            archivoIVRDTO.setTipoArchivo(cargueController.extractExtension(up.getFileName()));
            archivoIVRDTO.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
            ejbControlArchivoIvr.insert(archivoIVRDTO);

            for (UploadedFile nombresArchivosErroneo : nombresArchivosErroneos) {
                if (nombresArchivosErroneo.equals(up)) {
                    nombresArchivosErroneos.remove(nombresArchivosErroneo);
                    break;
                }
            }
            JsfUtil.addSuccessMessage("Se remplazo correctamente el archivo " + up.getFileName());
        } catch (NotFoundException e) {
            JsfUtil.addWarningMessage("No se reconoce el archivo : " + up.getFileName());
        }
        /*int size = nombresArchivosErroneos.size();
        for (int i = 1; i <= size; i++) {
            if (nombresArchivosErroneos.get(i - 1).getFileName().equals(up.getFileName())) {
                nombresArchivosErroneos.remove(nombresArchivosErroneos.get(i - 1));
            }
        }*/
    }

}
