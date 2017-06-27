
package Packaging;

/**
 * TableBeanArtePackaging
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
public class TableBeanArtePackaging implements Serializable{

    private String imageID;
    private String imageName;
    private String imageFecha;
    private String imageResultado;
    private String imageNombre;

       
    
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

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }
    Connection MySQLcon = null;
    Statement stmt = null;
    PreparedStatement ps;
    ResultSet rs;

    public List<TableBeanArtePackaging> getAllImage() {
        List<TableBeanArtePackaging> imageInfo = new ArrayList<TableBeanArtePackaging>();
        Connection con = Database.getConnection();
        try {
            stmt = con.createStatement();
            String strSql = "select FILE_ARTE_NAME,PACKAGING_ID  from packaging WHERE TIPO='Arte' order by PACKAGING_ID ";
            //System.err.println("*select all***" + strSql);
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                TableBeanArtePackaging tbl = new TableBeanArtePackaging();
                tbl.setImageName(rs.getString("FILE_ARTE_NAME"));
                tbl.setImageID(rs.getString("PACKAGING_ID"));
                imageInfo.add(tbl);
            }
        } catch (SQLException e) {
            System.out.println("Exception in getAllImage::" + e.getMessage());
        }
        return imageInfo;
    }
    
    
    
    
    
    
     public String borrarArtePackaging (String imageID) throws SQLException{
    Connection con = Database.getConnection();
    String outcome = null;

        try {
            stmt = con.createStatement();
            String strSql = "DELETE FROM packaging WHERE PACKAGING_ID="+imageID;
            //System.err.println("*select all***" + strSql);
            stmt.executeUpdate(strSql);
            System.out.println("ARTE (Packing) borrada exitosamente");
            con.close();
            
            outcome = "/packaging.xhtml?faces-redirect=true";
            Borradoexitoso();
            return outcome;


        } catch (SQLException e) {
            System.out.println("Exception in Arte::" + e.getMessage());
            con.close();
            
            outcome = "/packaging.xhtml?faces-redirect=true";
            Borradofallido();
            return outcome;
        }
    }
    
    
     
    public List<TableBeanArtePackaging> filtrado(String desarrolloID){
    List<TableBeanArtePackaging> imageInfo2 = new ArrayList<TableBeanArtePackaging>();
        Connection con = Database.getConnection();
        try {
            stmt = con.createStatement();
            String strSql = "select FILE_ARTE_NAME,PACKAGING_ID  from packaging WHERE TIPO='Arte' AND DESARROLLO_ID="+desarrolloID;
            //System.err.println("*select all***" + strSql);
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                
                TableBeanArtePackaging tbl = new TableBeanArtePackaging();
                tbl.setImageName(rs.getString("FILE_ARTE_NAME"));
                tbl.setImageID(rs.getString("PACKAGING_ID"));
                 
                imageInfo2.add(tbl);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Exception in filtrado: ARTE:" + e.getMessage());
        }
        return imageInfo2;
        
    
    }//cierra metodo filtrado      
     
     
    public void Borradoexitoso() {
           FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Arte", "borrado exitosamente"));
    
    }
    
    public void Borradofallido() {
           FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Arte", "no se pudo borrar"));
    
    }
    
     
     
     
    
}