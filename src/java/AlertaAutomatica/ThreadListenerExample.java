package AlertaAutomatica;

/**
 *
 * @author juan.cuello
 */


import AlertaSistema.Car;

import Configuracion.ApplicationProperties;
import Configuracion.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import java.util.Calendar;



public class ThreadListenerExample implements ServletContextListener{
 
    
     private ScheduledExecutorService    scheduler    = null;

    public void contextInitialized(ServletContextEvent sce) {
        if ((scheduler == null) || (!scheduler.isTerminated())) {
            scheduler = Executors.newSingleThreadScheduledExecutor();
            scheduler.scheduleAtFixedRate(new ScheduledTask(), 0, 86400, TimeUnit.SECONDS);
            //para que sea por dia tiene que ser= 3600 por es esta en segundos.
            
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
        try {
            System.out.println("Scheduler Shutting down successfully " + new Date());
            scheduler.shutdown();
        } catch (Exception ex) {
        }
    }
}

class ScheduledTask extends TimerTask {
        
    
    ApplicationProperties aplicationproperties = new ApplicationProperties();   
                 
    private static String USER_NAME = "cuello.juanpablo@gmail.com";  // GMail user name (just the part before "@gmail.com")
    private static String PASSWORD = "qjvprajksepgyejt"; // GMail password
    private final String RECIPIENT = aplicationproperties.getAlertaMailDlVencido();

    
    Connection MySQLcon = null;
    Statement stmt = null;
    PreparedStatement ps;
    ResultSet rs;
 
        
    public List<Car> getCars() throws ClassNotFoundException, SQLException {
 
          java.util.Date utilDate = new java.util.Date(); //fecha actual
          long lnMilisegundos = utilDate.getTime();
          java.sql.Date fecha = new java.sql.Date(lnMilisegundos);
  
          Calendar calendar = Calendar.getInstance();
           System.out.println("Fecha Actual: " + calendar.getTime());  
          
           calendar.add(Calendar.DAY_OF_MONTH, 120);
           System.out.println("Fecha con 120 d: " + calendar.getTime());

           //calendar 2 a 60 días 
           Calendar calendar2 = Calendar.getInstance();
           System.out.println("Fecha Actual: " + calendar2.getTime());  
          
           calendar2.add(Calendar.DAY_OF_MONTH, 60);
           System.out.println("Fecha con 60 d: " + calendar2.getTime());
           
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
                          System.out.println("get speed");
                          System.out.println(car.getVencimiento().toString());
                        }else{
                               if ( calendar.getTime().before(car.getVencimiento() )){
                                                   //aca todavía no se vencieron    System.err.println("before2: "+ car.getSpeed().toString());
                               }else{
                              // cars.add(car);
                                                  System.err.println("before3: "+ car.getVencimiento().toString());
                                                  //cars.add(car);
                               } 
                        }

             
                //para 60 días
                if ( car.getVencimiento().before(calendar2.getTime()) ){
                       // aca se vencio System.err.println("before 1: "+ car.getSpeed().toString());
                        cars.add(car);
                          System.out.println("get speed");
                          System.out.println(car.getVencimiento().toString());

                        }else{
                               if ( calendar2.getTime().before(car.getVencimiento() )){
                                                   //aca todavía no se vencieron    System.err.println("before2: "+ car.getSpeed().toString());
                               }else{
                              //cars.add(car);
                                                  System.err.println("before3: "+ car.getVencimiento().toString());
                                                 // cars.add(car);
                               } 
                        }

           }//cierra el while
           con.close();
        } catch (SQLException e) {
            System.out.println("Exception in getCars:" + e.getMessage());
        }   
        return cars;
        
        }//cierra metodo getCars
       
      
    
           
    public  String sendEmail() throws ClassNotFoundException, SQLException {
        List<Car> carstraidos = new ArrayList<Car>();
         carstraidos = getCars();
        
        String from = USER_NAME;
        String pass = PASSWORD;
        String[] to = { RECIPIENT }; // list of recipient email addresses
        String subject = "DATOS LEGALES PROXIMOS A VENCER";
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
        
    
    public void run() {
        System.out.println(new Date());
        List<Car> carstraidos = new ArrayList<Car>();
        try {
            carstraidos = getCars();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ScheduledTask.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ScheduledTask.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String from = USER_NAME;
        String pass = PASSWORD;
        String[] to = { RECIPIENT }; // list of recipient email addresses
        String subject = "DATOS LEGALES PROXIMOS A VENCER - sistema Marca Propia";
        String body = carstraidos.toString();
        
        if(carstraidos.isEmpty()){
        
        System.out.println("EMAIL vacio");
        
        }else{
        
        sendFromGMail(from, pass, to, subject, body);
        System.out.println(RECIPIENT);
        System.out.println("EMAIL ENVIADO");
        
        }
    }
  
}