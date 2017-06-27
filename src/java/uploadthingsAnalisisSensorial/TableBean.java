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
import java.io.Serializable;
import java.sql.*;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author juan cuello
 */
@ManagedBean
@SessionScoped
public class TableBean implements Serializable{

    private String imageID;
    private String imageName;
    private String imageFecha;
    private String imageResultado;
    private String imageNombre;
    private List<TableBean> filteredCars;
    
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

    public List<TableBean> getAllImage() throws SQLException {
        List<TableBean> imageInfo = new ArrayList<TableBean>();
        Connection con = Database.getConnection();
        try {
            stmt = con.createStatement();
            String strSql = "select Image_name,nombre,resultado,fecha,image_id  from upload_image order by image_id ";
            //System.err.println("*select all***" + strSql);
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                TableBean tbl = new TableBean();
                tbl.setImageName(rs.getString("Image_name"));
                tbl.setImageNombre(rs.getString("nombre"));
                tbl.setImageResultado(rs.getString("resultado")); 
                tbl.setImageFecha(rs.getString("fecha"));
                tbl.setImageID(rs.getString("image_id"));
                imageInfo.add(tbl);
            }
            con.close();
        } catch (SQLException e) {
            
            System.out.println("Exception in getAllImage::" + e.getMessage());
            con.close();
        }
        return imageInfo;
    }
    
    
    public String borrarAnalisisSensorial (String imageID) throws SQLException{
    Connection con = Database.getConnection();
    String outcome = null;
        
        try {
            stmt = con.createStatement();
            String strSql = "DELETE FROM upload_image WHERE image_id="+imageID;
            //System.err.println("*select all***" + strSql);
            stmt.executeUpdate(strSql);
            System.out.println("analisis sensorial borrado exitosamente");
            con.close();
            
            outcome = "/analisisSensorial.xhtml?faces-redirect=true";
            Borradoexitoso();
            return outcome;

        } catch (SQLException e) {
            System.out.println("Exception in Analisis Sensorial:" + e.getMessage());
            con.close();
            
            outcome = "/analisisSensorial.xhtml?faces-redirect=true";
            Borradofallido();
            return outcome;
            
        }

    }
    
    
    
    
    public List<TableBean> filtrado(String desarrolloID){
    List<TableBean> imageInfo2 = new ArrayList<TableBean>();
        Connection con = Database.getConnection();
        try {
            stmt = con.createStatement();
            String strSql = "select Image_name,nombre,resultado,fecha,image_id  from upload_image WHERE DESARROLLO_ID="+desarrolloID;
            //System.err.println("*select all***" + strSql);
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                TableBean tbl = new TableBean();
                tbl.setImageName(rs.getString("Image_name"));
                tbl.setImageNombre(rs.getString("nombre"));
                tbl.setImageResultado(rs.getString("resultado")); 
                tbl.setImageFecha(rs.getString("fecha"));
                tbl.setImageID(rs.getString("image_id"));
                imageInfo2.add(tbl);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Exception in Analisis sensorial::" + e.getMessage());
        }
        System.out.println("en  el metodo filtrado a punto de retornar analisisSensorial");
        return imageInfo2;
        
    }//cierra metodo filtrado

    
    
    public void Borradoexitoso() {
           FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Análisis Sensorial", "Borrado exitosamente"));
    
    }
    
    public void Borradofallido() {
           FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Analisis Sensorial", "no se pudo borrar"));
    
    }
    
    
    
    
}