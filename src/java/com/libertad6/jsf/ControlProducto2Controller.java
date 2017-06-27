package com.libertad6.jsf;

import com.libertad6.jpa.ControlProducto2;
import com.libertad6.jsf.util.JsfUtil;
import com.libertad6.jsf.util.JsfUtil.PersistAction;
import com.libertad6.jpacontroller.ControlProducto2Facade;

import java.io.Serializable;
import java.util.Arrays;
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

@Named("controlProducto2Controller")
@SessionScoped
public class ControlProducto2Controller implements Serializable {

    @EJB
    private com.libertad6.jpacontroller.ControlProducto2Facade ejbFacade;
    private List<ControlProducto2> items = null;
    private ControlProducto2 selected;

    private final static int[] years;
    private List filteredCars;
 
         
     static {
        years = new int[4];
        years[0] = 2016;
                                                
    }

        public List getFilteredCars() {
        return filteredCars;
    }
    
    
    public List getYears() {
        return Arrays.asList(years);
    }
     
     
     
     
     
    
    public ControlProducto2Controller() {
    }

    public ControlProducto2 getSelected() {
        return selected;
    }

    public void setSelected(ControlProducto2 selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ControlProducto2Facade getFacade() {
        return ejbFacade;
    }

    public ControlProducto2 prepareCreate() {
        selected = new ControlProducto2();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ControlProducto2Created"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ControlProducto2Updated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ControlProducto2Deleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ControlProducto2> getItems() {
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

    public ControlProducto2 getControlProducto2(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<ControlProducto2> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ControlProducto2> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = ControlProducto2.class)
    public static class ControlProducto2ControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ControlProducto2Controller controller = (ControlProducto2Controller) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "controlProducto2Controller");
            return controller.getControlProducto2(getKey(value));
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
            if (object instanceof ControlProducto2) {
                ControlProducto2 o = (ControlProducto2) object;
                return getStringKey(o.getControlproducto2ID());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ControlProducto2.class.getName()});
                return null;
            }
        }

    }

}
