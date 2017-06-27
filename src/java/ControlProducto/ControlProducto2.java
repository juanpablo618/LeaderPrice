
package ControlProducto;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author juan.cuello
 */


@ManagedBean
@SessionScoped
public class ControlProducto2 implements Serializable{
    
    
     private static final long serialVersionUID = 1L;
     
     private List filteredCars;

    public ControlProducto2() {
    }

    public void setFilteredCars(List filteredCars) {
        this.filteredCars = filteredCars;
    }

    public List getFilteredCars() {
        return filteredCars;
    }
    
    
    
    
    
    
    
    
    
}
