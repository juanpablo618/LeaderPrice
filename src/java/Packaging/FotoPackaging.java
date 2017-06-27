/**
 * FotoPackaging
 * @author juan.cuello
 */


package Packaging;


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
public class FotoPackaging implements Serializable {
 
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
        System.out.println("insertando foto de packing");
        String outcome = null;
        if (file != null) {
            
           if(file.getFileName() == null   || (file.getFileName().equals("")) )  {
                    System.out.println("No ingreso archivo");
              
                outcome = "/packaging.xhtml?faces-redirect=true";
                NoIngresoArchivo();
                return outcome;
       
           }else{

                    try {
                        System.out.println(file.getFileName());
                        InputStream fin2 = file.getInputstream();
                        Connection con = Database.getConnection();

                        PreparedStatement pre = con.prepareStatement("insert into packaging (IMAGE_FOTO_NAME,IMAGE_FOTO,DESARROLLO_ID,TIPO) values(?,?,?,?)");

                        pre.setString(1, file.getFileName().toString());
                        pre.setBinaryStream(2, fin2, file.getSize());
                        pre.setString(3,DESARROLLO_ID.toString());
                        pre.setString(4,"Foto");

                        pre.executeUpdate();
                        
                        System.out.println("Inserting Successfully!-foto packaging");
                        pre.close();
                        
                        outcome = "/packaging.xhtml?faces-redirect=true";
                       BienCreada(); 
                      return outcome;

                    } catch (Exception e) {
                        System.out.println("Exception-File Upload." + e.getMessage());
                            
                        ArchivoGrande();
                        outcome = "/packaging.xhtml?faces-redirect=true";
                        return outcome;       

                    }
           }           
            
        }
        else{
        ArchivoNoSeleccionado();
        }
        outcome = "/packaging.xhtml?faces-redirect=true";
        return outcome;
    }
    
    
    public void BienCreada() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Archivo de Foto", "Creado exitosamente"));
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

