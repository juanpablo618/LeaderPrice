/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uploadthingsAnalisisSensorial;

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
public class UploadImage implements Serializable {
 
    private static final long serialVersionUID = 1L;
    private UploadedFile file;
    private String nombre;
    private String resultado;
    private Date fecha;
    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    private String DESARROLLO_ID ;
    private String imageID;
      
     

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
        System.out.println("Subiendo analisis sensorial into upload_image");
        String outcome = null;
        
        if (file != null) {
            
            if(file.getFileName() == null   || (file.getFileName().equals("")) )  {
                    System.out.println("No ingreso archivo");
              
                outcome = "/analisisSensorial.xhtml?faces-redirect=true";
                NoIngresoArchivo();
                return outcome;
       
            }else{

                    try {
                        
                        System.out.println("Nombre del archivo: "+ file.getFileName());
                        InputStream fin2 = file.getInputstream();

                        Connection con = Database.getConnection();
                        PreparedStatement pre = con.prepareStatement("insert into upload_image (image_name,image,nombre,resultado,fecha,DESARROLLO_ID) values(?,?,?,?,?,?)");

                        pre.setString(1, file.getFileName().toString());
                        pre.setBinaryStream(2, fin2, file.getSize());
                        pre.setString(3, nombre.toString());
                        pre.setString(4, resultado.toString());
                        pre.setString(5,df.format(fecha));
                        pre.setString(6,DESARROLLO_ID.toString());
                        pre.executeUpdate();

                        System.out.println("Inserting Successfully!");
                        pre.close();

                        outcome = "/analisisSensorial.xhtml?faces-redirect=true";
                         BienCreada(); 
                        return outcome;
                      

                    } catch (Exception e) {
                        System.out.println("Exception-File Upload." + e.getMessage());
                        
                        ArchivoGrande();
                        outcome = "/analisisSensorial.xhtml?faces-redirect=true";
                        return outcome;
                    }
            }
        }
        else{
                ArchivoNoSeleccionado();
        }
        outcome = "/analisisSensorial.xhtml?faces-redirect=true";
        return outcome;
    }
    
    public void BienCreada() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Análisis Sensorial", "Creado exitosamente"));
    }
    
    public void NoIngresoArchivo() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "NO ingreso Archivo "+ "NO se ha almacenado."));
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
