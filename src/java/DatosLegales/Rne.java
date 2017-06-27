package DatosLegales;

/**
 *RNE
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
public class Rne implements Serializable {
 
    private static final long serialVersionUID = 1L;
    private UploadedFile file;
    private String resultado;
    private Date fecha;
    private Date fechafantasma;
    private int PROVEEDOR_ID ;
    private String DESARROLLO_ID ;
    
    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    DateFormat dfantasma = new SimpleDateFormat("yyyy/MM/dd");

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

    public Date getFechafantasma() {
        return fechafantasma;
    }

    public void setFechafantasma(Date fechafantasma) {
        this.fechafantasma = fechafantasma;
    }

    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
 
    
    
    public String upload() {
        System.out.println("insertadno Rne into table datos_legales");
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

                        PreparedStatement pre = con.prepareStatement("insert into datos_legales (FILE_DATOLEGAL_NAME,FILE_DATOSLEGAL,fecha,TIPO,DESARROLLO_ID,VENCIMIENTO) values(?,?,?,?,?,?)");

                        pre.setString(1, file.getFileName().toString());
                        pre.setBinaryStream(2, fin2, file.getSize());

                        pre.setString(3,df.format(fecha));
                        pre.setString(4,"Rne");
                        pre.setString(5,DESARROLLO_ID.toString());

                         SimpleDateFormat dateformat3 = new SimpleDateFormat("yyyy/MM/dd");

                        Date date1 = dateformat3.parse("2340/05/07");

                        System.out.println("Date1 is: "+dateformat3.format(date1));

                        pre.setString(6, dateformat3.format(date1)  );

                        pre.executeUpdate();
                        System.out.println("Inserting Successfully!");
                        pre.close();
                        
                        
                       outcome = "/datoslegales.xhtml?faces-redirect=true";
                       BienCreada(); 
                      return outcome;
                        

                    } catch (Exception e) {
                        System.out.println("Exception-File Upload." + e.getMessage());

                        ArchivoGrande();
                        outcome = "/datoslegales.xhtml?faces-redirect=true";
                        return outcome;
                        

                    }
                    
            }     
                    
        }
        else{
        
            ArchivoNoSeleccionado();
        }
        outcome = "/faces/datoslegales.xhtml?faces-redirect=true";
        return outcome;
                        
    }
    
    public void BienCreada() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "RNE", "Creado exitosamente"));
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

