
/**
 *
 * @author juan.cuello
 */

package Auditoria;


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
public class TableBeanAuditoria implements Serializable{

    private String imageID;
    private String imageName;
    private String imageFecha;
    private String imageResultado;
    private String imageNombre;
    private int desarrolloId;

    public int getDesarrolloId() {
        return desarrolloId;
    }

    public void setDesarrolloId(int desarrolloId) {
        this.desarrolloId = desarrolloId;
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

    public List<TableBeanAuditoria> getAllImage() {
        List<TableBeanAuditoria> imageInfo = new ArrayList<TableBeanAuditoria>();
        Connection con = Database.getConnection();
        try {
            stmt = con.createStatement();
            String strSql = "select FILE_INFORME_NAME,TIPO_AUDITORIA,resultado,fecha,AUDITORIA_ID,DESARROLLO_ID  from auditoria order by AUDITORIA_ID ";
            //System.err.println("*select all***" + strSql);
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                TableBeanAuditoria tbl = new TableBeanAuditoria();
                tbl.setImageName(rs.getString("FILE_INFORME_NAME"));
                tbl.setImageNombre(rs.getString("TIPO_AUDITORIA"));
                tbl.setImageResultado(rs.getString("resultado"));
                tbl.setImageFecha(rs.getString("fecha"));
                tbl.setImageID(rs.getString("AUDITORIA_ID"));
                tbl.setDesarrolloId(rs.getInt("DESARROLLO_ID"));
                imageInfo.add(tbl);
            }
        } catch (SQLException e) {
            System.out.println("Exception in getAllImage::" + e.getMessage());
        }
        return imageInfo;
    }
    
    
    
    public String borrarAuditoria(String imageID) throws SQLException {
        Connection con = Database.getConnection();
        try {
            stmt = con.createStatement();
            String strSql = "DELETE FROM auditoria WHERE auditoria_id=" + imageID;
            //System.err.println("*select all***" + strSql);
            stmt.executeUpdate(strSql);
            System.out.println("auditoria borrada exitosamente");
            con.close();

            FacesMessage msgsuccess = new FacesMessage(FacesMessage.SEVERITY_WARN, "Auditoría", "Borrada exitosamente");
            FacesContext.getCurrentInstance().addMessage(null, msgsuccess);

            return null;
        } catch (SQLException e) {
            System.out.println("Exception in Auditoria::" + e.getMessage());

            FacesMessage msgfail = new FacesMessage("No se pudo borrar la Auditoria.");
            FacesContext.getCurrentInstance().addMessage(null, msgfail);
            con.close();
        }
        return null;
    }
        
    
    
    public List<TableBeanAuditoria> filtrado(String desarrolloID) {
        List<TableBeanAuditoria> imageInfo2 = new ArrayList<TableBeanAuditoria>();
        Connection con = Database.getConnection();
        try {
            stmt = con.createStatement();
            String strSql = "select FILE_INFORME_NAME,TIPO_AUDITORIA,resultado,fecha,AUDITORIA_ID,DESARROLLO_ID  from auditoria  WHERE DESARROLLO_ID=" + desarrolloID;
            //System.err.println("*select all***" + strSql);
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {

                TableBeanAuditoria tbl = new TableBeanAuditoria();
                tbl.setImageName(rs.getString("FILE_INFORME_NAME"));
                tbl.setImageNombre(rs.getString("TIPO_AUDITORIA"));
                tbl.setImageResultado(rs.getString("resultado"));
                tbl.setImageFecha(rs.getString("fecha"));
                tbl.setImageID(rs.getString("AUDITORIA_ID"));
                tbl.setDesarrolloId(rs.getInt("DESARROLLO_ID"));

                imageInfo2.add(tbl);
            }
            con.close();

        } catch (SQLException e) {
            System.out.println("Exception in filtrado: auditoria:" + e.getMessage());
        }
        return imageInfo2;

    }//cierra metodo filtrado      
     
     
    
    
    
    
    
    
    
    
    
    }
    
    

