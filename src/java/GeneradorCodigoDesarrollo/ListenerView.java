
/**
 *
 * @author juan.cuello
 */

package GeneradorCodigoDesarrollo;

import CodigoComercialGenerado.CounterView;
import Configuracion.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.faces.bean.ManagedBean;
 
@ManagedBean
public class ListenerView {
     
    private String text;
    private String text2;
    private String textcodigoNros;

    public String getTextcodigoNros() {
        return textcodigoNros;
    }

    public void setTextcodigoNros(String textcodigoNros) {
        this.textcodigoNros = textcodigoNros;
    }
    
    
    
    
    public String getText2() {
        return text2;
    }

    
    
    
    
    Connection MySQLcon = null;
    Statement stmt = null;
    PreparedStatement ps;
    ResultSet rs;
    public void setText2(String text2) {
        
        java.util.Date utilDate = new java.util.Date(); //fecha actual
            long lnMilisegundos = utilDate.getTime();
                java.sql.Date sqlDate = new java.sql.Date(lnMilisegundos);
            System.out.println("sql.Date1: "+sqlDate);
            String textonros = sqlDate.toString() +"-"+"1";
            Calendar fecha = new GregorianCalendar();
            int anio = fecha.get(Calendar.YEAR);
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
                 
            textonros = anio +"-"+  String.format("%03d", tbl.getNumber()) +"-" ;
            }
        } catch (SQLException e) {
            System.out.println("Exception in LISTENERVIEW: GENERADOR DE CODIGO:setText2" + e.getMessage());
        }
        
        System.out.println("textos nros1: "+textonros.toString());
        
        this.text2 = textonros + text2 ;
    }
  
    
    
    
    
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
     
    public void handleKeyEvent() {
        text = text.toUpperCase();
        
    }
    
     public void handleKeyEventjuan() {
        text2 = text2 ;
        
    }
     
     
     public void increment() {
        
         java.util.Date utilDate = new java.util.Date(); //fecha actual
            long lnMilisegundos = utilDate.getTime();
                java.sql.Date sqlDate = new java.sql.Date(lnMilisegundos);
            System.out.println("sql.Date: "+sqlDate);
            Calendar fecha = new GregorianCalendar();
            int anio = fecha.get(Calendar.YEAR);
            
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
                
            textcodigoNros = anio + "-" +  String.format("%03d",tbl.getNumber()) + "-" ;
            }
        } catch (SQLException e) {
            System.out.println("Exception in getAllImage::" + e.getMessage());
        }
             
             
             
             
             
        // number++;
        //fecha = sqlDate;
        
       // codigo = sqlDate.toString() +  number++ + "a";
        
    }
     
     
     
     
     
     
     
     
}