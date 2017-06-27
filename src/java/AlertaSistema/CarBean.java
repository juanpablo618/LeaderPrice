
package AlertaSistema;

/**
 *
 * @author juan.cuello
 */

import Configuracion.ApplicationProperties;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import Configuracion.Database;
import java.sql.*;
import java.util.*;
 
@ManagedBean
@SessionScoped
public class CarBean implements Serializable {
 
    ApplicationProperties aplicationproperties = new ApplicationProperties();   

    private static final long serialVersionUID = 6081417964063918994L;
    private static String USER_NAME = "cuello.juanpablo@gmail.com";  // GMail user name (just the part before "@gmail.com")
    private static String PASSWORD = "qjvprajksepgyejt"; // GMail password
    private  String RECIPIENT = aplicationproperties.getAlertaMailDlVencido();

    
    Connection MySQLcon = null;
    Statement stmt = null;
    PreparedStatement ps;
    ResultSet rs;
 
    public List<Car> getCars() throws ClassNotFoundException, SQLException {
 
        java.util.Date utilDate = new java.util.Date(); //fecha actual
          long lnMilisegundos = utilDate.getTime();
          java.sql.Date fecha = new java.sql.Date(lnMilisegundos);
          
          Calendar calendar = Calendar.getInstance();
          calendar.add(Calendar.DAY_OF_MONTH, 120);
          
          //calendar 2 a 60 días 
          Calendar calendar2 = Calendar.getInstance();
          //System.out.println("Fecha Actual: " + calendar2.getTime());  
          
          calendar2.add(Calendar.DAY_OF_MONTH, 60);
          //System.out.println("Fecha con 60 d: " + calendar2.getTime());
          
        List<Car> cars = new ArrayList<Car>();
        Connection con = Database.getConnection();
        try {
            stmt = con.createStatement();
            String strSql = "select DATOS_LEGALES_ID, DESARROLLO_ID, VENCIMIENTO, TIPO, FILE_DATOLEGAL_NAME from datos_legales";
        
        rs = stmt.executeQuery(strSql);
            while (rs.next()) {
            Car car = new Car();
            
            car.setCid(rs.getInt("DATOS_LEGALES_ID"));
            car.setDesarrollo(rs.getString("DESARROLLO_ID"));
            car.setVencimiento(rs.getDate("VENCIMIENTO"));
            car.setTipo(rs.getString("TIPO"));
            car.setFileName(rs.getString("FILE_DATOLEGAL_NAME"));
            
            if ( car.getVencimiento().before(calendar.getTime()) ){
                    // aca se vencio System.err.println("before 1: "+ car.getSpeed().toString());
                       cars.add(car);
                       System.out.println("get vencimiento carBean: ");
                       System.out.println(car.getVencimiento().toString());
                       System.out.println("Calendar time: ");
                       System.out.println(calendar.getTime().toString());
                       
                     }else{
                            if ( calendar.getTime().before(car.getVencimiento())){
                                                //aca todavía no se vencieron    System.err.println("before2: "+ car.getSpeed().toString());
                            }else{
                           // cars.add(car);
                                               System.err.println("before3: "+ car.getVencimiento().toString());
                                               //cars.add(car);
                            } 
                     }
            
           }//cierra el while
            con.close();
        } catch (SQLException e) {
            System.out.println("Exception in getAllImage::" + e.getMessage());
        }   
        System.out.println("cantidad de elementos que tiene la lista: "+cars.size());
        return cars;
        
    }//cierra metodo getCars
       
      
    
           
    public  String sendEmail() throws ClassNotFoundException, SQLException {
        List<Car> carstraidos = new ArrayList<Car>();
         carstraidos = getCars();
        
        String from = USER_NAME;
        String pass = PASSWORD;
        String[] to = { RECIPIENT }; // list of recipient email addresses
        String subject = "LISTA DE TODOS LOS DATOS LEGALES VENCIDOS";
        String body = carstraidos.toString();

        sendFromGMail(from, pass, to, subject, body);
        
        addMessage("EMAIL ENVIADO CORRECTAMENTE!!");
        return "car";
        
    }
    
    
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    
    
        private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }
    
    
    
}

