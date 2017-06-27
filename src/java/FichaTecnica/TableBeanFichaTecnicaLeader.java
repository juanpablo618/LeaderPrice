package FichaTecnica;

/**
 * TAble bean Ficha tecnica Leader !
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
public class TableBeanFichaTecnicaLeader implements Serializable{

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
            String strSql = "select FILE_FICHALIBERTAD_NAME,FICHA_TECNICA_ID  from ficha_tecnica WHERE TIPO='FichaMarcaPropia' order by FICHA_TECNICA_ID ";
            //System.err.println("*select all***" + strSql);
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                TableBeanFichaTecnica tbl = new TableBeanFichaTecnica();
                tbl.setImageName(rs.getString("FILE_FICHALIBERTAD_NAME"));
                tbl.setImageID(rs.getString("FICHA_TECNICA_ID"));
                imageInfo.add(tbl);
            }
        } catch (SQLException e) {
            System.out.println("Exception in getAllImage::" + e.getMessage());
        }
        return imageInfo;
    }
    
    
    
     public String borrarFichaTecnicaLeader (String imageID) throws SQLException{
    Connection con = Database.getConnection();
    String outcome = null;
    
        try {
            stmt = con.createStatement();
            String strSql = "DELETE FROM ficha_tecnica WHERE FICHA_TECNICA_ID="+imageID;
            //System.err.println("*select all***" + strSql);
            stmt.executeUpdate(strSql);
            System.out.println("FICHA TECNICA leader  borrada exitosamente");
            con.close();
            
            outcome = "/fichatecnica.xhtml?faces-redirect=true";
            Borradoexitoso();
            return outcome;

            

        } catch (SQLException e) {
            System.out.println("Exception in Ficha Tecnica leaderPrice(marcaPropia):" + e.getMessage());
            
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
            String strSql = "select FILE_FICHALIBERTAD_NAME,FICHA_TECNICA_ID  from ficha_tecnica WHERE TIPO='FichaMarcaPropia' AND DESARROLLO_ID="+desarrolloID;
            //System.err.println("*select all***" + strSql);
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                 TableBeanFichaTecnica tbl = new TableBeanFichaTecnica();
                tbl.setImageName(rs.getString("FILE_FICHALIBERTAD_NAME"));
                tbl.setImageID(rs.getString("FICHA_TECNICA_ID"));
                imageInfo2.add(tbl);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Exception in getAllImage2: ficha leader:" + e.getMessage());
        }
        return imageInfo2;
        
    
    }//cierra metodo filtrado
    
     
         public void Borradoexitoso() {
           FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Ficha Técnica Leader Price", "Ha sido eliminada exitosamente"));
    
    }
    
    public void Borradofallido() {
           FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ficha Técnica Leader Price", "no ha sido eliminada"));
    
    }
     
     
}