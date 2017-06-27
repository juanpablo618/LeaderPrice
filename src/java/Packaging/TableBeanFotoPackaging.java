
/**
 * TableBeanFotoPackaging
 * @author juan.cuello
 */

package Packaging;


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
public class TableBeanFotoPackaging implements Serializable{

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

    public List<TableBeanFotoPackaging> getAllImage() {
        List<TableBeanFotoPackaging> imageInfo = new ArrayList<TableBeanFotoPackaging>();
        Connection con = Database.getConnection();
        try {
            stmt = con.createStatement();
            String strSql = "select IMAGE_FOTO_NAME,PACKAGING_ID  from packaging WHERE TIPO='Foto' order by PACKAGING_ID ";
            //System.err.println("*select all***" + strSql);
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                TableBeanFotoPackaging tbl = new TableBeanFotoPackaging();
                tbl.setImageName(rs.getString("IMAGE_FOTO_NAME"));
                tbl.setImageID(rs.getString("PACKAGING_ID"));
                imageInfo.add(tbl);
            }
        } catch (SQLException e) {
            System.out.println("Exception in getAllImage::" + e.getMessage());
        }
        return imageInfo;
    }
    
    
    
    
    
     public String borrarFotoPackaging (String imageID) throws SQLException{
    Connection con = Database.getConnection();
        String outcome = null;

        try {
            stmt = con.createStatement();
            String strSql = "DELETE FROM packaging WHERE PACKAGING_ID="+imageID;
            //System.err.println("*select all***" + strSql);
            stmt.executeUpdate(strSql);
            System.out.println("FOTO (Packing) borrada exitosamente");
            con.close();
            
            
            outcome = "/packaging.xhtml?faces-redirect=true";
            Borradoexitoso();
            return outcome;
            
        } catch (SQLException e) {
            System.out.println("Exception in foto::" + e.getMessage());
            con.close();
            
            outcome = "/packaging.xhtml?faces-redirect=true";
            Borradofallido();
            return outcome;
        }
       
    }
    
    
    
    
    public List<TableBeanFotoPackaging> filtrado(String desarrolloID){
    List<TableBeanFotoPackaging> imageInfo2 = new ArrayList<TableBeanFotoPackaging>();
        Connection con = Database.getConnection();
        try {
            stmt = con.createStatement();
            String strSql = "select IMAGE_FOTO_NAME,PACKAGING_ID  from packaging WHERE TIPO='Foto' AND DESARROLLO_ID="+desarrolloID;
            //System.err.println("*select all***" + strSql);
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                
                TableBeanFotoPackaging tbl = new TableBeanFotoPackaging();
                tbl.setImageName(rs.getString("IMAGE_FOTO_NAME"));
                tbl.setImageID(rs.getString("PACKAGING_ID"));
                
                imageInfo2.add(tbl);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Exception in filtrado: FOTO:" + e.getMessage());
        }
        return imageInfo2;
        
    
    }//cierra metodo filtrado      
     
    
    public void Borradoexitoso() {
           FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Foto", "borrada exitosamente"));
    
    }
    
    public void Borradofallido() {
           FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Foto", "no se pudo borrar"));
    
    }
    
}