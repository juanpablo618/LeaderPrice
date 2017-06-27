/** 
 *TableBeanPreAuditoria
 * @author juan.cuello
 */


package PreAuditoria;


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
public class TableBeanPreAuditoria implements Serializable{

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

    public List<TableBeanPreAuditoria> getAllImage() {
        List<TableBeanPreAuditoria> imageInfo = new ArrayList<TableBeanPreAuditoria>();
        Connection con = Database.getConnection();
        try {
            stmt = con.createStatement();
            String strSql = "select FILE_PREAUDITORIA_NAME,PRE_AUDITORIA_ID  from pre_auditoria order by PRE_AUDITORIA_ID ";
            //System.err.println("*select all***" + strSql);
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                TableBeanPreAuditoria tbl = new TableBeanPreAuditoria();
                tbl.setImageName(rs.getString("FILE_PREAUDITORIA_NAME"));
                tbl.setImageID(rs.getString("PRE_AUDITORIA_ID"));
                imageInfo.add(tbl);
            }
        } catch (SQLException e) {
            System.out.println("Exception in getAllImage::" + e.getMessage());
        }
        return imageInfo;
    }
    
    
    
    
            
   public String borrarPreAuditoria (String imageID) throws SQLException{
    Connection con = Database.getConnection();
    String outcome = null;
   
    try {
            stmt = con.createStatement();
            String strSql = "DELETE FROM pre_auditoria WHERE PRE_AUDITORIA_ID="+imageID;
            //System.err.println("*select all***" + strSql);
            stmt.executeUpdate(strSql);
            System.out.println("pre_auditoria borrada exitosamente");
            con.close();
            
            outcome = "/preauditoria.xhtml?faces-redirect=true";
            Borradoexitoso();
            return outcome;
            
        } catch (SQLException e) {
            System.out.println("Exception in Pre-Auditoria:" + e.getMessage());
            
            con.close();
            outcome = "/preauditoria.xhtml?faces-redirect=true";
            Borradofallido();
            return outcome;
            
        }
    }
            
            
      
     
    public List<TableBeanPreAuditoria> filtrado(String desarrolloID){
    List<TableBeanPreAuditoria> imageInfo2 = new ArrayList<TableBeanPreAuditoria>();
        Connection con = Database.getConnection();
        try {
            stmt = con.createStatement();
            String strSql = "select FILE_PREAUDITORIA_NAME,PRE_AUDITORIA_ID  from pre_auditoria WHERE DESARROLLO_ID="+desarrolloID;
            //System.err.println("*select all***" + strSql);
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                  TableBeanPreAuditoria tbl = new TableBeanPreAuditoria();
                tbl.setImageName(rs.getString("FILE_PREAUDITORIA_NAME"));
                tbl.setImageID(rs.getString("PRE_AUDITORIA_ID"));
                imageInfo2.add(tbl);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Exception in getAllImage2: pre auditoria:" + e.getMessage());
        }
        return imageInfo2;
        
    
    }//cierra metodo filtrado      
            
    public void Borradoexitoso() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Pre-Auditoría", "Ha sido eliminado exitosamente"));
    
    }
    
    public void Borradofallido() {
           FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Pre-Auditoría", "no se ha eliminado"));
    
    } 
            
            
            
            
}