
package AcuerdoComercial;

/**
 *AcuerdoComercialAcuerdoComercial
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




@ManagedBean
@SessionScoped
public class AcuerdoComercial implements Serializable {
 
    private static final long serialVersionUID = 1L;
    
    
    private UploadedFile file;
    
    private String resultado;

    
    private Date fecha;
    
    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");

    
    private int PROVEEDOR_ID ;
    
    

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
        System.out.println("Subiendo un acuerdo comercial into acuerdo_comercial");
        String outcome = null;

        if (file != null) {
            if(file.getFileName() == null   || (file.getFileName().equals("")) )  {
                    System.out.println("No ingreso archivo");
              
                outcome = "/acuerdocomercial.xhtml?faces-redirect=true";
                NoIngresoArchivo();
                return outcome;
            
            }else{
                try {
                    System.out.println(file.getFileName());
                    InputStream fin2 = file.getInputstream();

                    Connection con = Database.getConnection();
                    PreparedStatement pre = con.prepareStatement("insert into acuerdo_comercial (FILE_ACUERDO_NAME,FILE_ACUERDO,proveedor_id,fecha) values(?,?,?,?)");

                    pre.setString(1, file.getFileName().toString());
                    pre.setBinaryStream(2, fin2, file.getSize());
                    pre.setString(3, resultado.toString());
                    pre.setString(4,df.format(fecha));
                    pre.executeUpdate();
                    pre.close();
                    System.out.println("Inserting Successfully!-Acuerdo Comercial");
                    
                    outcome = "/acuerdocomercial.xhtml?faces-redirect=true";
                    BienCreada(); 
                    return outcome;
                    
                    

                } catch (Exception e) {
                    System.out.println("Exception-File Upload." + e.getMessage());

                        ArchivoGrande();
                        outcome = "/acuerdocomercial.xhtml?faces-redirect=true";
                        return outcome;
                        

                }
            }    
        }
        else{
        
            ArchivoNoSeleccionado();
        }
        outcome = "/acuerdocomercial.xhtml?faces-redirect=true";
        return outcome;
                        
    }
    
    public void BienCreada() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Acuerdo Comercial", "creado exitosamente"));
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
