
package AcuerdoComercial;

/**
 *
 * @author juan.cuello
 * 
 * TableBeanAcuerdoComercial
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
public class TableBeanAcuerdoComercial implements Serializable {


    private String imageName;
    private String imageFecha;
    private String imageResultado;
    private String imageNombre;
    private int imageID;

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
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

    public List<TableBeanAcuerdoComercial> getAllImage() {
        List<TableBeanAcuerdoComercial> imageInfo = new ArrayList<TableBeanAcuerdoComercial>();
        Connection con = Database.getConnection();
        try {
            stmt = con.createStatement();
            String strSql = "select FILE_ACUERDO_NAME,PROVEEDOR_ID,fecha,ACUERDO_COMERCIAL_ID  from acuerdo_comercial order by ACUERDO_COMERCIAL_ID ";
            //System.err.println("*select all***" + strSql);
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                TableBeanAcuerdoComercial tbl = new TableBeanAcuerdoComercial();
                tbl.setImageName(rs.getString("FILE_ACUERDO_NAME"));
                tbl.setImageNombre(rs.getString("PROVEEDOR_ID"));
                tbl.setImageFecha(rs.getString("fecha"));
                tbl.setImageID(rs.getInt("ACUERDO_COMERCIAL_ID"));
                imageInfo.add(tbl);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Exception in getAllImage::" + e.getMessage());
            
        }
        return imageInfo;
    }
    
    
    public String borrarAcuerdoComercial (String imageID) throws SQLException{
    Connection con = Database.getConnection();
        try {
            stmt = con.createStatement();
            String strSql = "DELETE FROM acuerdo_comercial WHERE ACUERDO_COMERCIAL_ID="+imageID;
            //System.err.println("*select all***" + strSql);
            stmt.executeUpdate(strSql);
            FacesMessage msg = new FacesMessage("Acuedo Comercial Borrado Exitosamente", imageID + " is deleted.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            System.out.println("acuerdo_comercial borrado exitosamente:" +imageID);
            con.close();
            
            FacesMessage msgsuccess = new FacesMessage("Acuerdo Comercial borrado exitosamente");
            FacesContext.getCurrentInstance().addMessage(null, msgsuccess);
          
            return null;
        } catch (SQLException e) {
            System.out.println("Exception in Acuerdo Comercial:" + e.getMessage());
            
                 FacesMessage msgfail = new FacesMessage("No se pudo borrar Acuerdo Comercial");
            FacesContext.getCurrentInstance().addMessage(null, msgfail);
         
            con.close();
        }
        return null;
    }
    
    
    
}