/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AniosControlProducto;

/**
 *
 * @author juan.cuello
 */

import ControlProducto.ControlProducto;
 import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="dtFilterView")
@ViewScoped
public class FilterView implements Serializable {
     
    private List<ControlProducto> cars;
     
    private List<ControlProducto> filteredCars;
     
    @ManagedProperty("#{carService}")
    private CarService service;
 
    
     
    public boolean filterByPrice(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if(filterText == null||filterText.equals("")) {
            return true;
        }
         
        if(value == null) {
            return false;
        }
         
        return ((Comparable) value).compareTo(Integer.valueOf(filterText)) > 0;
    }
     
    public List<String> getBrands() {
        return service.getBrands();
    }
     
    public List<String> getColors() {
        return service.getColors();
    }
     
    public List<ControlProducto> getCars() {
        return cars;
    }
 
    public List<ControlProducto> getFilteredCars() {
        return filteredCars;
    }
 
    public void setFilteredCars(List<ControlProducto> filteredCars) {
        this.filteredCars = filteredCars;
    }
 
    public void setService(CarService service) {
        this.service = service;
    }
}