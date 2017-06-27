
/**
 * 
 * @author juan.cuello
 * 
 * Pre auditoria
 */

package PreAuditoria;

import Configuracion.Database;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

@ManagedBean
@SessionScoped
public class PreAuditoria implements Serializable {
 
    private static final long serialVersionUID = 1L;
    private UploadedFile file;
    private String nombre;

    
     private String DESARROLLO_ID ;

    public String getDESARROLLO_ID() {
        return DESARROLLO_ID;
    }

    public void setDESARROLLO_ID(String DESARROLLO_ID) {
        this.DESARROLLO_ID = DESARROLLO_ID;
    }
   
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
 
    
    
    public String upload() {
        System.out.println("insertando preAuditoria - into table pre_auditoria");
        String outcome = null;
        
        if (file != null) {
            
            if(file.getFileName() == null   || (file.getFileName().equals("")) )  {
                    System.out.println("No ingreso archivo");
              
                outcome = "/preauditoria.xhtml?faces-redirect=true";
                NoIngresoArchivo();
                return outcome;
            }else{

            
                    try {
                        System.out.println(file.getFileName());
                        InputStream fin2 = file.getInputstream();

                        Connection con = Database.getConnection();

                        PreparedStatement pre = con.prepareStatement("insert into pre_auditoria (FILE_PREAUDITORIA_NAME,FILE_PREAUDITORIA,DESARROLLO_ID) values(?,?,?)");

                        pre.setString(1, file.getFileName().toString());
                        pre.setBinaryStream(2, fin2, file.getSize());
                        pre.setString(3, DESARROLLO_ID.toString());

                        pre.executeUpdate();
                        System.out.println("Inserting Successfully!");
                        pre.close();
                        
                        
                        outcome = "/preauditoria.xhtml?faces-redirect=true";
                        BienCreada(); 
                        return outcome;
                        
                        

                    } catch (Exception e) {
                        System.out.println("Exception-File Upload." + e.getMessage());

                        ArchivoGrande();
                        outcome = "/preauditoria.xhtml?faces-redirect=true";
                        return outcome;
                        

                    }
            }     
                    
        }
        else{
                ArchivoNoSeleccionado();
        }
        outcome = "/faces/preauditoria.xhtml?faces-redirect=true";
        return outcome;
                        
    }
    
    public void BienCreada() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PreAuditoria", "Ha sido creada exitosamente"));
    }
    
    public void NoIngresoArchivo() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "NO ingreso Archivo--"+ "NO se ha almacenado."));
    } 
     
     public void ArchivoGrande() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Archivo demasiado grande"+ " NO se ha almacenado."));
    }
    
     public void ArchivoNoSeleccionado() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "No se ha seleccionado archivo"+ " NO se ha almacenado."));
    }
     
}
