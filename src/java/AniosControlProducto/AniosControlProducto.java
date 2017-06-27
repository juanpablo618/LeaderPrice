
package AniosControlProducto;



import Auditoria.TableBeanAuditoria;
import Configuracion.Database;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;
import java.util.Date;
import java.util.List;


/**
 *
 * @author juan.cuello
 */

@ManagedBean
@SessionScoped
public class AniosControlProducto implements Serializable{
    private static final long serialVersionUID = 1L;

         private List<String> years = null;

    
        
    
Connection MySQLcon = null;
    Statement stmt = null;
    PreparedStatement ps;
    ResultSet rs;

    public List getAllYears() {
        Connection con = Database.getConnection();
        try {
            stmt = con.createStatement();
            String strSql = "select YEAR  from control_producto_2 order by control_producto_2_ID ";
            //System.err.println("*select all***" + strSql);
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                
                years.add(rs.getString("YEAR"));
                        
                }
        } catch (SQLException e) {
            System.out.println("Exception in la lista q devuelve años de control producto::" + e.getMessage());
        }
        return years;
    }
    
    
    
}
