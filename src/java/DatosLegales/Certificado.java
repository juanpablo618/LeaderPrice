
package DatosLegales;

/**
 *
 * @author juan.cuello
 */


import Configuracion.Database;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@ManagedBean
@SessionScoped

public class Certificado implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    
    private UploadedFile file;
    
    private String resultado;

    
    private Date fecha;
    
    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");

    
    private int PROVEEDOR_ID ;

    
     private String DESARROLLO_ID ;

    public String getDESARROLLO_ID() {
        return DESARROLLO_ID;
    }

    public void setDESARROLLO_ID(String DESARROLLO_ID) {
        this.DESARROLLO_ID = DESARROLLO_ID;
    }
    
    
    
    
    
    
    public int getPROVEEDOR_ID() {
        return PROVEEDOR_ID;
    }

    public void setPROVEEDOR_ID(int PROVEEDOR_ID) {
        this.PROVEEDOR_ID = PROVEEDOR_ID;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    
    
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    
    
    
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
 
    
    
    public String upload() {
        System.out.println("insertando certificado into datos_legales");
        String outcome = null;
        
        if (file != null) {
            
            if(file.getFileName() == null   || (file.getFileName().equals("")) )  {
                    System.out.println("No ingreso archivo");
              
                outcome = "/datoslegales.xhtml?faces-redirect=true";
                NoIngresoArchivo();
                return outcome;
            }else{

                    try {
                        System.out.println(file.getFileName());
                        InputStream fin2 = file.getInputstream();
                        
                        Connection con = Database.getConnection();
                        PreparedStatement pre = con.prepareStatement("insert into datos_legales (FILE_DATOLEGAL_NAME,FILE_DATOSLEGAL,vencimiento,TIPO,DESARROLLO_ID) values(?,?,?,?,?)");

                        pre.setString(1, file.getFileName().toString());
                        pre.setBinaryStream(2, fin2, file.getSize());
                        pre.setString(3,df.format(fecha));
                        pre.setString(4,"Certificado");
                        pre.setString(5,DESARROLLO_ID.toString());

                        pre.executeUpdate();
                        System.out.println("Inserting Successfully!-Certificado");
                        pre.close();
                        
                        outcome = "/datoslegales.xhtml?faces-redirect=true";
                        BienCreada(); 
                        return outcome;
                        
                    } catch (Exception e) {
                        System.out.println("Exception-File Upload. un certificado" + e.getMessage());

                        ArchivoGrande();
                        outcome = "/datoslegales.xhtml?faces-redirect=true";
                        return outcome;

                    }
            }        
        }
        else{
            ArchivoNoSeleccionado();
    
        }
        outcome = "/datoslegales.xhtml?faces-redirect=true";
        return outcome; 
    }

    public void BienCreada() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Certificado", "Creado exitosamente"));
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
