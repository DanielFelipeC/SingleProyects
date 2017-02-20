package manager;

import entities.UsuariosLinkinCRM;
import manager.util.JsfUtil;
import manager.util.JsfUtil.PersistAction;
import ejbs.UsuariosLinkinCRMFacade;
import entities.UserDTO;
import java.io.File;
import java.io.IOException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;

@Named("usuariosLinkinCRMController")
@SessionScoped
public class UsuariosLinkinCRMController implements Serializable {

    @EJB
    private ejbs.UsuariosLinkinCRMFacade ejbFacade;
    private List<UsuariosLinkinCRM> items = null;
    private UsuariosLinkinCRM selected;

    public UsuariosLinkinCRMController() {
    }

    public UsuariosLinkinCRM getSelected() {
        return selected;
    }


    public void setSelected(UsuariosLinkinCRM selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UsuariosLinkinCRMFacade getFacade() {
        return ejbFacade;
    }

    public UsuariosLinkinCRM prepareCreate() {
        selected = new UsuariosLinkinCRM();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UsuariosLinkinCRMCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UsuariosLinkinCRMUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UsuariosLinkinCRMDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<UsuariosLinkinCRM> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {

                    Double cs = Math.random();
                    Long azar = cs.longValue();

                    selected.setIDUsuarios(azar);
                    for (int i = 0; i < 10; i++) {

                        getFacade().edit(selected);
                    }

                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public UsuariosLinkinCRM getUsuariosLinkinCRM(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<UsuariosLinkinCRM> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<UsuariosLinkinCRM> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = UsuariosLinkinCRM.class)
    public static class UsuariosLinkinCRMControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsuariosLinkinCRMController controller = (UsuariosLinkinCRMController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuariosLinkinCRMController");
            return controller.getUsuariosLinkinCRM(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof UsuariosLinkinCRM) {
                UsuariosLinkinCRM o = (UsuariosLinkinCRM) object;
                return getStringKey(o.getIDUsuarios());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), UsuariosLinkinCRM.class.getName()});
                return null;
            }
        }

    }

    private List<UserDTO> personas = new ArrayList<UserDTO>();

    public List<UserDTO> getPersonas() {
        UserDTO per = new UserDTO();
        per.setNombres("Mito");
        per.setApellidos("Code");
        Calendar cal = Calendar.getInstance();
        cal.set(1991, 1, 21);
        per.setFechaNacimiento(cal.getTime());
        personas.add(per);

        per = new UserDTO();
        per.setNombres("Jaime");
        per.setApellidos("MD");
        cal = Calendar.getInstance();
        cal.set(1990, 5, 21);
        per.setFechaNacimiento(cal.getTime());
        personas.add(per);

        return personas;
    }

    public void setPersonas(List<UserDTO> personas) {
        this.personas = personas;
    }

    public void exportarPDF(ActionEvent actionEvent) throws JRException, IOException {

        try {

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("txtUsuario", "Daniel");

            String reportName = null;

            List<UsuariosLinkinCRM> cs = new ArrayList<>();
            if (selected == null) {
                JsfUtil.addErrorMessage("Seleccione un usuario para ver el reporte");
            } else {
                cs.add(selected);
                reportName = "Reporte" + selected.getNombreUsuario();
                File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/ReporteEjemplo2.jasper"));
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, new JRBeanCollectionDataSource(cs));

                HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                response.addHeader("Content-disposition", "attachment; filename=" + reportName + ".pdf");
                try (ServletOutputStream stream = response.getOutputStream()) {
                    JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

                    stream.flush();
                }
                FacesContext.getCurrentInstance().responseComplete();
            }

        } catch (Exception e) {
        }
    }

    public void exportarAllPDF(ActionEvent actionEvent) throws JRException, IOException {

        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("txtUsuario", "Daniel");

        items = ejbFacade.findAll();

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/ReporteEjemplo2.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, new JRBeanCollectionDataSource(items));

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=ReporteUserAll.pdf");
        try (ServletOutputStream stream = response.getOutputStream()) {
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

            stream.flush();
        }
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void verPDF(ActionEvent actionEvent) throws Exception {
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/ReporteEjemplo2.jasper"));

        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("txtUsuario", "Daniel");

        List<UsuariosLinkinCRM> cs = new ArrayList<>();
        if (selected == null) {
            JsfUtil.addErrorMessage("Seleccione un usuario para ver el reporte");
        } else {

            cs.add(selected);
            byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametros, new JRBeanCollectionDataSource(cs));
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();

            FacesContext.getCurrentInstance().responseComplete();
        }
    }

    public void exportarExcel(ActionEvent actionEvent) throws JRException, IOException {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("txtUsuario", "Daniel");

        items = ejbFacade.findAll();

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/ReporteEjemplo2.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, new JRBeanCollectionDataSource(items));

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=jsfReporte.xls");
        ServletOutputStream stream = response.getOutputStream();

        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
        exporter.exportReport();

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void exportarPPT(ActionEvent actionEvent) throws JRException, IOException {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("txtUsuario", "Daniel");

        items = ejbFacade.findAll();

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/ReporteEjemplo2.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, new JRBeanCollectionDataSource(items));

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=jsfReporte.ppt");
        ServletOutputStream stream = response.getOutputStream();

        JRPptxExporter exporter = new JRPptxExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
        exporter.exportReport();

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void exportarDOC(ActionEvent actionEvent) throws JRException, IOException {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("txtUsuario", "Daniel");

        items = ejbFacade.findAll();

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/ReporteEjemplo2.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, new JRBeanCollectionDataSource(items));

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=jsfReporte.doc");
        ServletOutputStream stream = response.getOutputStream();

        JRDocxExporter exporter = new JRDocxExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
        exporter.exportReport();

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void exportarHtml(ActionEvent actionEvent) throws JRException, IOException {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("txtUsuario", "Daniel");

        items = ejbFacade.findAll();

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/ReporteEjemplo2.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, new JRBeanCollectionDataSource(items));

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=jsfReporte.html");
        ServletOutputStream stream = response.getOutputStream();

        JRHtmlExporter exporter = new JRHtmlExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
        exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, "image?image=");
        exporter.exportReport();

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }

}
