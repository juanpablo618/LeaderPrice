/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlProducto;

/**
 *
 * @author juan
 */


import Configuracion.Database;
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
import java.util.List;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@ManagedBean
@SessionScoped
public class ControlProducto implements Serializable{
    
    private ControlProducto selected;

     private static final long serialVersionUID = 1L;
   
    private String ASPECTO;
    private String Producto;
    
    private String unidadeMedida;
    private Double ValorReferencia;
     private String Year;
      private String ValorYear;
            
    private String DESARROLLO_ID ;

    public ControlProducto(String ASPECTO, String Producto, String unidadeMedida, Double ValorReferencia, String Year, String ValorYear, String DESARROLLO_ID) {
        this.ASPECTO = ASPECTO;
        this.Producto = Producto;
        this.unidadeMedida = unidadeMedida;
        this.ValorReferencia = ValorReferencia;
        this.Year = Year;
        this.ValorYear = ValorYear;
        this.DESARROLLO_ID = DESARROLLO_ID;
    }

    
    
    
    
    public String getDESARROLLO_ID() {
        return DESARROLLO_ID;
    }

    public void setDESARROLLO_ID(String DESARROLLO_ID) {
        this.DESARROLLO_ID = DESARROLLO_ID;
    }
    
      
    public String getASPECTO() {
        return ASPECTO;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public Double getValorReferencia() {
        return ValorReferencia;
    }

    public String getYear() {
        return Year;
    }

    public String getValorYear() {
        return ValorYear;
    }

    public void setASPECTO(String ASPECTO) {
        this.ASPECTO = ASPECTO;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public void setValorReferencia(Double ValorReferencia) {
        this.ValorReferencia = ValorReferencia;
    }

    public void setYear(String Year) {
        this.Year = Year;
    }

    public void setValorYear(String ValorYear) {
        this.ValorYear = ValorYear;
    }

    
    protected void initializeEmbeddableKey() {
    }
    
    

    public ControlProducto getSelected() {
        return selected;
    }

    public String getProducto() {
        return Producto;
    }

    public void setProducto(String Producto) {
        this.Producto = Producto;
    }
    
    
    
    
    
    
    
    public void upload() {
        System.out.println("insertando un control de producto");
        
            try {
                
                Connection con = Database.getConnection();
                               
                PreparedStatement pre = con.prepareStatement("insert into control_producto_2 (ASPECTO,UNIDAD_DE_MEDIDA,VALOR_DE_REFERENCIA,YEAR,VALOR_YEAR,DESARROLLO_ID) values(?,?,?,?,?,?)");
                
                pre.setString(1, ASPECTO.toString());
                pre.setString(2, unidadeMedida.toString());
                pre.setString(3,ValorReferencia.toString());
                pre.setString(4,Year.toString());
                pre.setString(5,ValorYear.toString());
                pre.setString(6,DESARROLLO_ID.toString());
                
                
                
                pre.executeUpdate();
                FacesMessage msg = new FacesMessage("Control de Producto Cargado Exitosamente", pre + " is upload.");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            
                System.out.println("Inserting control de producto Successfully!");
                pre.close();
                
            } catch (Exception e) {
                System.out.println("Exception-File Upload." + e.getMessage());
                
                
                
            }
        }
        
    }
