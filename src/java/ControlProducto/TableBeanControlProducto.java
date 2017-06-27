
package ControlProducto;

/**
 *
 * @author juan
 */

import Configuracion.Database;
import java.sql.*;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


@ManagedBean
@SessionScoped
public class TableBeanControlProducto {
    
    private String aspecto;
    private String unidadMedida;
      private int      valorReferencia;
     private int       year ;
            private int valorYear ;

     private String imageID;

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }
           
            
            
    public String getAspecto() {
        return aspecto;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public int getValorReferencia() {
        return valorReferencia;
    }

    public int getYear() {
        return year;
    }

    public int getValorYear() {
        return valorYear;
    }

    public void setAspecto(String aspecto) {
        this.aspecto = aspecto;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public void setValorReferencia(int valorReferencia) {
        this.valorReferencia = valorReferencia;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setValorYear(int valorYear) {
        this.valorYear = valorYear;
    }
    
    
    
    
    
    
    
    
    
    
    Connection MySQLcon = null;
    Statement stmt = null;
    PreparedStatement ps;
    ResultSet rs;
    
    public List<TableBeanControlProducto> getAllImage() {
        List<TableBeanControlProducto> imageInfo = new ArrayList<TableBeanControlProducto>();
        Connection con = Database.getConnection();
        try {
            stmt = con.createStatement();
            String strSql = "select ASPECTO,UNIDAD_DE_MEDIDA,VALOR_DE_REFERENCIA,YEAR,VALOR_YEAR,control_producto_2_ID  from control_producto_2 order by control_producto_2_ID ";
            //System.err.println("*select all***" + strSql);
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                TableBeanControlProducto tbl = new TableBeanControlProducto();
                
                
                tbl.setAspecto(rs.getString("ASPECTO"));
                tbl.setUnidadMedida(rs.getString("UNIDAD_DE_MEDIDA"));
                tbl.setValorReferencia(rs.getInt("VALOR_DE_REFERENCIA"));
                tbl.setYear(rs.getInt("YEAR"));
                tbl.setValorYear(rs.getInt("VALOR_YEAR"));
                tbl.setImageID(rs.getString("control_producto_2_ID"));
                
                imageInfo.add(tbl);
                
                
                
                
            }
        } catch (SQLException e) {
            System.out.println("Exception in getAllImage::" + e.getMessage());
        }
        return imageInfo;
    }
    
    
    
    public String borrarControlProducto (String imageID) throws SQLException{
    Connection con = Database.getConnection();
        try {
            stmt = con.createStatement();
            String strSql = "DELETE FROM control_producto_2 WHERE control_producto_2_ID="+imageID;
            //System.err.println("*select all***" + strSql);
            stmt.executeUpdate(strSql);
            System.out.println("control de producto borrado exitosamente");
            con.close();
            
             FacesMessage msgsuccess = new FacesMessage("Control de Producto borrado exitosamente.");
            FacesContext.getCurrentInstance().addMessage(null, msgsuccess);
           
            
            return null;
        } catch (SQLException e) {
            System.out.println("Exception in Control de Producto:" + e.getMessage());
            
             FacesMessage msgfail = new FacesMessage("No se pudo borrar el Control de Producto.");
            FacesContext.getCurrentInstance().addMessage(null, msgfail);
          
            
            con.close();
        }
        return null;
    }
        
    }
    

    
    