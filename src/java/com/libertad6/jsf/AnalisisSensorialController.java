package com.libertad6.jsf;

import com.libertad6.jpa.AnalisisSensorial;
import com.libertad6.jsf.util.JsfUtil;
import com.libertad6.jsf.util.JsfUtil.PersistAction;
import com.libertad6.jpacontroller.AnalisisSensorialFacade;

import java.io.Serializable;
import java.util.List;
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

@Named("analisisSensorialController")
@SessionScoped
public class AnalisisSensorialController implements Serializable {

    @EJB
    private com.libertad6.jpacontroller.AnalisisSensorialFacade ejbFacade;
    private List<AnalisisSensorial> items = null;
    private AnalisisSensorial selected;

    public AnalisisSensorialController() {
    }

    public AnalisisSensorial getSelected() {
        return selected;
    }

    public void setSelected(AnalisisSensorial selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AnalisisSensorialFacade getFacade() {
        return ejbFacade;
    }

    public AnalisisSensorial prepareCreate() {
        selected = new AnalisisSensorial();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AnalisisSensorialCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AnalisisSensorialUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AnalisisSensorialDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<AnalisisSensorial> getItems() {
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

    public AnalisisSensorial getAnalisisSensorial(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<AnalisisSensorial> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<AnalisisSensorial> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = AnalisisSensorial.class)
    public static class AnalisisSensorialControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AnalisisSensorialController controller = (AnalisisSensorialController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "analisisSensorialController");
            return controller.getAnalisisSensorial(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof AnalisisSensorial) {
                AnalisisSensorial o = (AnalisisSensorial) object;
                return getStringKey(o.getAnalisisSensorialId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), AnalisisSensorial.class.getName()});
                return null;
            }
        }

    }

}
