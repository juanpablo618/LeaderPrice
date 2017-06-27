/**
 *
 * @author juan.cuello
 */


package CodigoComercialGenerado;



import Configuracion.Database;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
 



import java.sql.*;
import java.util.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;




@ManagedBean
@ViewScoped
public class CounterView implements Serializable {
     
    private int number ;
 
    private Date fecha;
 
    private String codigo;

    public void setNumber(int number) {
        this.number = number;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
 
    
    
    
    
    public String getCodigo() {
        return codigo;
    }
    
    public int getNumber() {
        return number;
    }
 
    
    
    public Date getFecha() {
        return fecha;
    }
    
    
    Connection MySQLcon = null;
    Statement stmt = null;
    PreparedStatement ps;
    ResultSet rs;
    
    public void increment() {
        
         java.util.Date utilDate = new java.util.Date(); //fecha actual
            long lnMilisegundos = utilDate.getTime();
                java.sql.Date sqlDate = new java.sql.Date(lnMilisegundos);
            System.out.println("sql.Date: "+sqlDate);
            
            
            //String strSql = "select DESARROLLO_ID  from DESARROLLO  order by DESARROLLO_ID DESC";
                
             //   codigo = sqlDate.toString() +  tbl.getNumber() + "a";
           Connection con = Database.getConnection();  
            
        try {
            stmt = con.createStatement();
            String strSql = "SELECT * FROM DESARROLLO ORDER BY DESARROLLO_ID DESC LIMIT 1";
            //System.err.println("*select all***" + strSql);
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                
                
                
                CounterView tbl = new CounterView();
                tbl.setNumber(rs.getInt("DESARROLLO_ID") + 1);
                
            codigo = sqlDate.toString() +  tbl.getNumber() + "a";
            }
        } catch (SQLException e) {
            System.out.println("Exception in getAllImage::" + e.getMessage());
        }
             
             
             
             
             
        // number++;
        //fecha = sqlDate;
        
       // codigo = sqlDate.toString() +  number++ + "a";
        
    }
}