package Sessiones;



import LoginThings.SessionBean;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
 
 
@ManagedBean
@SessionScoped
public class ElegirDesarrolloBean implements Serializable {
 
    
    private String desarrollousando;
    private String productousando;
    private String codigoDesarrollousando;

    public String getCodigoDesarrollousando() {
        return codigoDesarrollousando;
    }

    public void setCodigoDesarrollousando(String codigoDesarrollousando) {
        this.codigoDesarrollousando = codigoDesarrollousando;
    }
    
    
    
    
    
    
    
    public String getProductousando() {
        return productousando;
    }

    public void setProductousando(String productousando) {
        this.productousando = productousando;
    }
    
    
    
   
    public String getDesarrollousando() {
        return desarrollousando;
    }

    public void setDesarrollousando(String desarrollousando) {
        this.desarrollousando = desarrollousando;
    }
    
    
    
    
   public String desarrolloEligiendo() {
            HttpSession session = SessionBean.getSession();
            session.setAttribute("desarrollousandoahora", desarrollousando);
            
            return null;
        
    }
    
     
   public String desarrolloEligiendo22(String desarrolloid, String productoid, String codigoDesarrollo) {
            HttpSession session = SessionBean.getSession();
            
            HttpSession session22 = SessionBean.getSession();
            
            HttpSession session3 = SessionBean.getSession();
            
            session.setAttribute("desarrollousandoahora", this.desarrollousando = desarrolloid);
            session22.setAttribute("productousandoahora", this.productousando = productoid);
            session3.setAttribute("codigoDesarrollousandoahora", this.codigoDesarrollousando = codigoDesarrollo);
            
            
            return null;
        
    }
    
    
    public void validardesarrollo(String desarrollousado){
		
                if(desarrollousado==null){
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "What we do in life", "Echoes in eternity.");
         
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                }else{
                     FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "WHAT", "WHAT WHAT WHAT WHAT.");
         
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                    
                }
        
        }
   
    public void save() {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Usted ha seleccionado " + desarrollousando + " " ));
    }
   
   
   
}



