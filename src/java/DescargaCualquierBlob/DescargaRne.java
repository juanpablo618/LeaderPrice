/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DescargaCualquierBlob;

/**
 *
 * @author juan.cuello
 */



import java.io.InputStream;
import java.io.Serializable;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@SessionScoped

public class DescargaRne {
    
    private String FILE_DATOLEGAL_NAME;
    
    private Blob FILE_DATOSLEGAL ;

    private StreamedContent file;

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }
    
    
    
    
    
    public String getFILE_DATOLEGAL_NAME() {
        return FILE_DATOLEGAL_NAME;
    }

    public Blob getFILE_DATOSLEGAL() {
        return FILE_DATOSLEGAL;
    }

    public void setFILE_DATOLEGAL_NAME(String FILE_DATOLEGAL_NAME) {
        this.FILE_DATOLEGAL_NAME = FILE_DATOLEGAL_NAME;
    }

    public void setFILE_DATOSLEGAL(Blob FILE_DATOSLEGAL) {
        this.FILE_DATOSLEGAL = FILE_DATOSLEGAL;
    }
    
    
    
    
    
    
    
    
    Connection MySQLcon = null;
    Statement stmt = null;
    PreparedStatement ps;
    ResultSet rs;

    
    public void DescargaRne() {
        System.out.println("descargaaa");
       
        Connection con = Database.getConnection();
            try {
            stmt = con.createStatement();
            String strSql = "select FILE_DATOLEGAL_NAME,FILE_DATOSLEGAL  from datos_legales WHERE DATOS_LEGALES_ID='1' order by DATOS_LEGALES_ID " ;
            //System.err.println("*select all***" + strSql);
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                
        
                DescargaRne tbl = new DescargaRne();
                
                
                tbl.setFILE_DATOLEGAL_NAME(rs.getString("FILE_DATOLEGAL_NAME"));
                tbl.setFILE_DATOSLEGAL(rs.getBlob("FILE_DATOSLEGAL"));
                file = (StreamedContent) rs.getBlob("FILE_DATOSLEGAL");
                
                    }
        } catch (SQLException e) {
            System.out.println("Exception in getAllImage::" + e.getMessage());
        }
      
        
}
       
}
       