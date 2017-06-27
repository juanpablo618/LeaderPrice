
/**
 *
 * @author juan.cuello
 */


package Auditoria;


import Configuracion.Database;
import com.libertad6.jsf.util.JsfUtil;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;


@ManagedBean
@SessionScoped
public class Auditoria implements Serializable {
 
    private static final long serialVersionUID = 1L;
    private UploadedFile file;
    private String nombre;
    private String resultado;
    private Date fecha;
    private String auditoriaid;
    private String DESARROLLO_ID ;
    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");

    
    private List<Auditoria> items = null;
    
    public String getAuditoriaid() {
        return auditoriaid;
    }

    public void setAuditoriaid(String auditoriaid) {
        this.auditoriaid = auditoriaid;
    }

    public String getDESARROLLO_ID() {
        return DESARROLLO_ID;
    }

    public void setDESARROLLO_ID(String DESARROLLO_ID) {
        this.DESARROLLO_ID = DESARROLLO_ID;
    }
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
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
        System.out.println("Insertando Auditoria on table auditoria");
        String outcome = null;
        
        if (file != null) {
             if(file.getFileName() == null   || (file.getFileName().equals("")) )  {
                System.out.println("No ingreso archivo");
                
                outcome = "/Auditoria.xhtml?faces-redirect=true";
                NoIngresoArchivo();
                return outcome;
       
            }else{
                    try {
                        System.out.println(file.getFileName());
                        InputStream fin2 = file.getInputstream();

                        Connection con = Database.getConnection();
                        PreparedStatement pre = con.prepareStatement("insert into auditoria (FILE_INFORME_NAME,FILE_INFORME,TIPO_AUDITORIA,resultado,fecha,DESARROLLO_ID) values(?,?,?,?,?,?)");
                        
                        pre.setString(1, file.getFileName().toString());
                        pre.setBinaryStream(2, fin2, file.getSize());
                        pre.setString(3, nombre.toString());
                        pre.setString(4, resultado.toString());
                        pre.setString(5,df.format(fecha));
                        pre.setString(6,DESARROLLO_ID.toString());


                        pre.executeUpdate();
                        System.out.println("Inserting Auditoria Successfully!");
                        pre.close();
                        
                       outcome = "/Auditoria.xhtml?faces-redirect=true";
                       BienCreada(); 
                      return outcome;
                        
                    } catch (Exception e) {
                        System.out.println("Exception-File Upload." + e.getMessage());
                        
                        ArchivoGrande();
                        outcome = "/Auditoria.xhtml?faces-redirect=true";
                        return outcome;
                    }
             }    
             
        }
        else{
                ArchivoNoSeleccionado();
        }
        outcome = "/Auditoria.xhtml?faces-redirect=true";
        return outcome; 
    }
    
    public void BienCreada() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Auditoría", "Creada exitosamente"));
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
