package FichaTecnica;

/**
 *  TableBeanFichaTecnicaTableBeanFichaTecnicaTableBeanFichaTecnica
 * @author juan.cuello
 */

import java.io.Serializable;
import java.sql.*;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


@ManagedBean
@SessionScoped
public class TableBeanFichaTecnica implements Serializable{

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

    public List<TableBeanFichaTecnica> getAllImage() {
        List<TableBeanFichaTecnica> imageInfo = new ArrayList<TableBeanFichaTecnica>();
        Connection con = Database.getConnection();
        try {
            stmt = con.createStatement();
            String strSql = "select FILE_FICHAPROVEEDOR_NAME,FICHA_TECNICA_ID  from ficha_tecnica WHERE TIPO='FichaProveedor' order by FICHA_TECNICA_ID ";
            //System.err.println("*select all***" + strSql);
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                TableBeanFichaTecnica tbl = new TableBeanFichaTecnica();
                tbl.setImageName(rs.getString("FILE_FICHAPROVEEDOR_NAME"));
                tbl.setImageID(rs.getString("FICHA_TECNICA_ID"));
                imageInfo.add(tbl);
            }
        } catch (SQLException e) {
            System.out.println("Exception in getAllImage::" + e.getMessage());
        }
        return imageInfo;
    }
    
    
    
    
    
    public String borrarFichaTecnica (String imageID) throws SQLException{
    Connection con = Database.getConnection();
    String outcome = null;

    try {
            stmt = con.createStatement();
            String strSql = "DELETE FROM ficha_tecnica WHERE FICHA_TECNICA_ID="+imageID;
            //System.err.println("*select all***" + strSql);
            stmt.executeUpdate(strSql);
            System.out.println("FICHA TECNICA  borrada exitosamente");
            con.close();
            
            outcome = "/fichatecnica.xhtml?faces-redirect=true";
            Borradoexitoso();
            return outcome;

            
        } catch (SQLException e) {
            System.out.println("Exception in Ficha Tecnica::" + e.getMessage());
            
            con.close();
            outcome = "/fichatecnica.xhtml?faces-redirect=true";
            Borradofallido();
            return outcome;

            
        }

    }
    
    
    
    public List<TableBeanFichaTecnica> filtrado(String desarrolloID){
    List<TableBeanFichaTecnica> imageInfo2 = new ArrayList<TableBeanFichaTecnica>();
        Connection con = Database.getConnection();
        try {
            stmt = con.createStatement();
            String strSql = "select FILE_FICHAPROVEEDOR_NAME,FICHA_TECNICA_ID  from ficha_tecnica WHERE TIPO='FichaProveedor' AND DESARROLLO_ID="+desarrolloID;
            //System.err.println("*select all***" + strSql);
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                 TableBeanFichaTecnica tbl = new TableBeanFichaTecnica();
                tbl.setImageName(rs.getString("FILE_FICHAPROVEEDOR_NAME"));
                tbl.setImageID(rs.getString("FICHA_TECNICA_ID"));
                imageInfo2.add(tbl);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Exception in Ficha Tecnica Proveedor::" + e.getMessage());
        }
        return imageInfo2;
        
    
    }//cierra metodo filtrado
    
    public void Borradoexitoso() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Ficha Técnica Proveedor", "Ha sido eliminado exitosamente"));
    
    }
    
    public void Borradofallido() {
           FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ficha Técnica Proveedor", "No ha sido eliminado"));
    
    }
    
    
}