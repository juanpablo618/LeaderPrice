package com.libertad6.jsf;

import com.libertad6.jpa.HabilitacionProducto;
import com.libertad6.jsf.util.JsfUtil;
import com.libertad6.jsf.util.JsfUtil.PersistAction;
import com.libertad6.jpacontroller.HabilitacionProductoFacade;

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

@Named("habilitacionProductoController")
@SessionScoped
public class HabilitacionProductoController implements Serializable {

    @EJB
    private com.libertad6.jpacontroller.HabilitacionProductoFacade ejbFacade;
    private List<HabilitacionProducto> items = null;
    private HabilitacionProducto selected;

    public HabilitacionProductoController() {
    }

    public HabilitacionProducto getSelected() {
        return selected;
    }

    public void setSelected(HabilitacionProducto selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private HabilitacionProductoFacade getFacade() {
        return ejbFacade;
    }

    public HabilitacionProducto prepareCreate() {
        selected = new HabilitacionProducto();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("HabilitacionProductoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("HabilitacionProductoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("HabilitacionProductoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<HabilitacionProducto> getItems() {
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

    public HabilitacionProducto getHabilitacionProducto(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<HabilitacionProducto> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<HabilitacionProducto> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = HabilitacionProducto.class)
    public static class HabilitacionProductoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            HabilitacionProductoController controller = (HabilitacionProductoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "habilitacionProductoController");
            return controller.getHabilitacionProducto(getKey(value));
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
            if (object instanceof HabilitacionProducto) {
                HabilitacionProducto o = (HabilitacionProducto) object;
                return getStringKey(o.getHabilitacionProductoId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), HabilitacionProducto.class.getName()});
                return null;
            }
        }

    }

}
