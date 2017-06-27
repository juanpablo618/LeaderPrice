
package DatosLegales;

/**
 *
 * @author juan.cuello
 */



import Configuracion.Database;
import java.io.Serializable;
import java.sql.*;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


@ManagedBean
@SessionScoped
public class TableBeanCertificado implements Serializable{
    
    private String imageName;
    private String imageFecha;
    private String imageResultado;
    private String imageNombre;
    private String imageID;
    private String desarrolloId;

    public String getDesarrolloId() {
        return desarrolloId;
    }

    public void setDesarrolloId(String desarrolloId) {
        this.desarrolloId = desarrolloId;
    }
    
    
    
    
    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }

    
    
    
    
    
    
    
    
    public String getImageNombre() {
        return imageNombre;
    }

    public void setImageNombre(String imageNombre) {
        this.imageNombre = imageNombre;
    }
    
    
    
    public String getImageFecha() {
        return imageFecha;
    }

    public String getImageResultado() {
        return imageResultado;
    }

    public void setImageFecha(String imageFecha) {
        this.imageFecha = imageFecha;
    }

    public void setImageResultado(String imageResultado) {
        this.imageResultado = imageResultado;
    }
    
    
    

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageLength() {
        return imageLength;
    }

    public void setImageLength(String imageLength) {
        this.imageLength = imageLength;
    }
    private String imageLength;

    Connection MySQLcon = null;
    Statement stmt = null;
    PreparedStatement ps;
    ResultSet rs;

    public List<TableBeanRne> getAllImage() {
        List<TableBeanRne> imageInfo = new ArrayList<TableBeanRne>();
        Connection con = Database.getConnection();
        try {
            stmt = con.createStatement();
            String strSql = "select FILE_DATOLEGAL_NAME,VENCIMIENTO,DATOS_LEGALES_ID,DESARROLLO_ID  from datos_legales WHERE TIPO='Certificado' order by DATOS_LEGALES_ID ";
            //System.err.println("*select all***" + strSql);
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                
                
                
                TableBeanRne tbl = new TableBeanRne();
                tbl.setImageName(rs.getString("FILE_DATOLEGAL_NAME"));
                tbl.setImageFecha(rs.getString("VENCIMIENTO"));
                tbl.setimageID(rs.getString("DATOS_LEGALES_ID"));
                tbl.setDesarrolloId(rs.getString("DESARROLLO_ID"));
                imageInfo.add(tbl);
            
            
            }
        } catch (SQLException e) {
            System.out.println("Exception in getAllImage::" + e.getMessage());
        }
        return imageInfo;
    }

    
        
    
    public String borrarCertificado (String imageID) throws SQLException{
    Connection con = Database.getConnection();
    String outcome = null;
        try {
            stmt = con.createStatement();
            String strSql = "DELETE FROM datos_legales WHERE DATOS_LEGALES_ID="+imageID;
            //System.err.println("*select all***" + strSql);
            stmt.executeUpdate(strSql);
            System.out.println("certificado borrado exitosamente");
            con.close();
            
            outcome = "/datoslegales.xhtml?faces-redirect=true";
            Borradoexitoso();
            return outcome;
            
        } catch (SQLException e) {
            System.out.println("Exception in getAllImage::" + e.getMessage());
            con.close();
            
            outcome = "/datoslegales.xhtml?faces-redirect=true";
            Borradofallido();
            return outcome;
            
        }
    }
    

    
    public List<TableBeanCertificado> filtrado(String desarrolloID){
    List<TableBeanCertificado> imageInfo2 = new ArrayList<TableBeanCertificado>();
        Connection con = Database.getConnection();
        try {
            stmt = con.createStatement();
            String strSql = "select FILE_DATOLEGAL_NAME,VENCIMIENTO,DATOS_LEGALES_ID,DESARROLLO_ID  from datos_legales WHERE TIPO='Certificado' AND DESARROLLO_ID="+desarrolloID;
            //System.err.println("*select all***" + strSql);
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                TableBeanCertificado tbl = new TableBeanCertificado();
                
                tbl.setImageName(rs.getString("FILE_DATOLEGAL_NAME"));
                tbl.setImageFecha(rs.getString("VENCIMIENTO"));
                tbl.setImageID(rs.getString("DATOS_LEGALES_ID"));
                tbl.setDesarrolloId(rs.getString("DESARROLLO_ID"));
                
                imageInfo2.add(tbl);
            
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Exception in FILTRADO de: Certificado:" + e.getMessage());
        }
        return imageInfo2;
    }//cierra metodo filtrado      
           
    
    public void Borradoexitoso() {
           FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Certificado", "Borrado exitosamente"));
    
    }
    
    public void Borradofallido() {
           FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Certificado", "no se pudo borrar"));
    
    }
    
    
    
    
    
}
