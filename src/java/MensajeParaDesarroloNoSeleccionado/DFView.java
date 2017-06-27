/*
 * clase usada para mostrar un mensaje si todavia no eligió un desarrollo y quiere entrar a analisis sensorial.
 * 
/**
 *
 * @author juan.cuello
 */
 
package MensajeParaDesarroloNoSeleccionado;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import org.primefaces.context.RequestContext;
 
@ManagedBean(name = "dfView")
public class DFView {
         
    public void showMessage() {
        
        
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Por favor selecciona un desarrollo 1ro", "Echoes in eternity.");
         
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
}