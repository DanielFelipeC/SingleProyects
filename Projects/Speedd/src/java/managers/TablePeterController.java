package managers;

import entities.TablePeter;
import managers.util.JsfUtil;
import managers.util.JsfUtil.PersistAction;
import ejbs.TablePeterFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.commons.lang3.RandomStringUtils;

@Named("tablePeterController")
@SessionScoped
public class TablePeterController implements Serializable {

    @EJB
    private ejbs.TablePeterFacade ejbFacade;
    private List<TablePeter> items = null;
    private TablePeter selected;
    
    private List<String> tables;
    
    @PostConstruct
    public void init(){
        tables = new ArrayList<>();
        items = ejbFacade.findAll();
        for (TablePeter item : items) {
            tables.add(item.getTextoUno());
        }
    }

    public TablePeterController() {
    }

    public TablePeter getSelected() {
        return selected;
    }

    public void setSelected(TablePeter selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TablePeterFacade getFacade() {
        return ejbFacade;
    }

    public TablePeter prepareCreate() {
        selected = new TablePeter();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TablePeterCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void crearEjercito() {
        char[] arr = {'A', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'X', 'Y', 'Z'};
        String code;
        String code1;                   
        String code2;
        for (int i = 0; i < 100000; i++) {
            code = RandomStringUtils.random(10, arr);
            code1 = RandomStringUtils.random(11, arr);
            code2 = RandomStringUtils.random(12, arr);
            selected.setTextoUno(code);
            selected.setTextoDos(code1);
            selected.setTextoTres(code2);
            persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TablePeterCreated"));
            if (!JsfUtil.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
            }
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TablePeterUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TablePeterDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TablePeter> getItems() {
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
                    getFacade().edit(selected);
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

    public TablePeter getTablePeter(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<TablePeter> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TablePeter> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public List<String> getTables() {
        return tables;
    }

    public void setTables(List<String> tables) {
        this.tables = tables;
    }

    @FacesConverter(forClass = TablePeter.class)
    public static class TablePeterControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TablePeterController controller = (TablePeterController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tablePeterController");
            return controller.getTablePeter(getKey(value));
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
            if (object instanceof TablePeter) {
                TablePeter o = (TablePeter) object;
                return getStringKey(o.getLlavePrimaria());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TablePeter.class.getName()});
                return null;
            }
        }

    }

}
