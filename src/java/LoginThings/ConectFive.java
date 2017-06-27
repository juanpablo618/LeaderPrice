
/**
 *
 * @author juan.cuello
 */


package LoginThings;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConectFive {
 
    public static void main(String[] args)
    {
        Connection con = null;
        PreparedStatement ps = null;
 
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select uname, password from Users where uname = ? and password = ?");
            
            ResultSet rs = ps.executeQuery();
 
            if (rs.next()) {
                //result found, means valid inputs
              
                System.out.println(rs.toString());
                
            }
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            
        } finally {
            DataConnect.close(con);
        }
        
    }
    
}


 