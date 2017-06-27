/**
 *
 * @author juan.cuello
 */



package Reclamo;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean
@SessionScoped
public class ReclamoController implements Serializable{
    
    
    
    private static final long serialVersionUID = 1L;

     private String imageID;
     
     
     private String NRO_CONFORMIDAD;
    private String NOMBRE_CLIENTE ;
    private int PRODUCTO_NO_CONFORME;
    private String MOTIVO_RECLAMO;
    private String SUCURSAL;
    private String MEDIO_RECEPCION;
    private String NOMBRE_RECEPCIONISTA;
    private String MUESTRAS;
    private String MEDIDAS;
    private String NOMBRE_RECEPCION_PROVEEDOR;
    private String CAUSAS;
    private String ACCIONES;
    private String RESPONSABLE;
   
     private String IMAGE_FOTORECLAMO_NAME;
    
    private int PROVEEDOR_ID ;
    
    private String FECHA_RECLAMO;
     private String FECHA_RECEPCION_PROVEEDOR;
    
    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    
    DateFormat df2 = new SimpleDateFormat("yyyy/MM/dd");

    public int getPROVEEDOR_ID() {
        return PROVEEDOR_ID;
    }

    
    
    
    
    public String getNRO_CONFORMIDAD() {
        return NRO_CONFORMIDAD;
    }

    public String getNOMBRE_CLIENTE() {
        return NOMBRE_CLIENTE;
    }

    public int getPRODUCTO_NO_CONFORME() {
        return PRODUCTO_NO_CONFORME;
    }

    public String getMOTIVO_RECLAMO() {
        return MOTIVO_RECLAMO;
    }

    public String getSUCURSAL() {
        return SUCURSAL;
    }

    public String getMEDIO_RECEPCION() {
        return MEDIO_RECEPCION;
    }

    public String getNOMBRE_RECEPCIONISTA() {
        return NOMBRE_RECEPCIONISTA;
    }

    public String getMUESTRAS() {
        return MUESTRAS;
    }

    public String getMEDIDAS() {
        return MEDIDAS;
    }

    public String getNOMBRE_RECEPCION_PROVEEDOR() {
        return NOMBRE_RECEPCION_PROVEEDOR;
    }

    public String getCAUSAS() {
        return CAUSAS;
    }

    public String getACCIONES() {
        return ACCIONES;
    }

    public String getRESPONSABLE() {
        return RESPONSABLE;
    }

    public String getFECHA_RECLAMO() {
        return FECHA_RECLAMO;
    }

    public String getFECHA_RECEPCION_PROVEEDOR() {
        return FECHA_RECEPCION_PROVEEDOR;
    }

    public void setNRO_CONFORMIDAD(String NRO_CONFORMIDAD) {
        this.NRO_CONFORMIDAD = NRO_CONFORMIDAD;
    }

    public void setNOMBRE_CLIENTE(String NOMBRE_CLIENTE) {
        this.NOMBRE_CLIENTE = NOMBRE_CLIENTE;
    }

    public void setPRODUCTO_NO_CONFORME(int PRODUCTO_NO_CONFORME) {
        this.PRODUCTO_NO_CONFORME = PRODUCTO_NO_CONFORME;
    }

    public void setMOTIVO_RECLAMO(String MOTIVO_RECLAMO) {
        this.MOTIVO_RECLAMO = MOTIVO_RECLAMO;
    }

    public void setSUCURSAL(String SUCURSAL) {
        this.SUCURSAL = SUCURSAL;
    }

    public void setMEDIO_RECEPCION(String MEDIO_RECEPCION) {
        this.MEDIO_RECEPCION = MEDIO_RECEPCION;
    }

    public void setNOMBRE_RECEPCIONISTA(String NOMBRE_RECEPCIONISTA) {
        this.NOMBRE_RECEPCIONISTA = NOMBRE_RECEPCIONISTA;
    }

    public void setMUESTRAS(String MUESTRAS) {
        this.MUESTRAS = MUESTRAS;
    }

    public void setMEDIDAS(String MEDIDAS) {
        this.MEDIDAS = MEDIDAS;
    }

    public void setNOMBRE_RECEPCION_PROVEEDOR(String NOMBRE_RECEPCION_PROVEEDOR) {
        this.NOMBRE_RECEPCION_PROVEEDOR = NOMBRE_RECEPCION_PROVEEDOR;
    }

    public void setCAUSAS(String CAUSAS) {
        this.CAUSAS = CAUSAS;
    }

    public void setACCIONES(String ACCIONES) {
        this.ACCIONES = ACCIONES;
    }

    public void setRESPONSABLE(String RESPONSABLE) {
        this.RESPONSABLE = RESPONSABLE;
    }

    public void setPROVEEDOR_ID(int PROVEEDOR_ID) {
        this.PROVEEDOR_ID = PROVEEDOR_ID;
    }

    public void setFECHA_RECLAMO(String FECHA_RECLAMO) {
        this.FECHA_RECLAMO = FECHA_RECLAMO;
    }

    public void setFECHA_RECEPCION_PROVEEDOR(String FECHA_RECEPCION_PROVEEDOR) {
        this.FECHA_RECEPCION_PROVEEDOR = FECHA_RECEPCION_PROVEEDOR;
    }

    public String getIMAGE_FOTORECLAMO_NAME() {
        return IMAGE_FOTORECLAMO_NAME;
    }

    public void setIMAGE_FOTORECLAMO_NAME(String IMAGE_FOTORECLAMO_NAME) {
        this.IMAGE_FOTORECLAMO_NAME = IMAGE_FOTORECLAMO_NAME;
    }
 
                
     
    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }

     
     public String VERPdf( String imageID,String NRO_CONFORMIDAD, String NOMBRE_CLIENTE, int PRODUCTO_NO_CONFORME, String SUCURSAL, String MEDIO_RECEPCION, String NOMBRE_RECEPCIONISTA, String MUESTRAS, String CAUSAS, String ACCIONES, String RESPONSABLE, String FECHA_RECLAMO, String NOMBRE_RECEPCION_PROVEEDOR, int PROVEEDOR_ID, String FECHA_RECEPCION_PROVEEDOR, String MOTIVO_RECLAMO, String MEDIDAS ){
     
         
         setImageID(imageID);
        
     
         setNRO_CONFORMIDAD(NRO_CONFORMIDAD);
         
         setNOMBRE_CLIENTE(NOMBRE_CLIENTE);
         
         setPRODUCTO_NO_CONFORME(PRODUCTO_NO_CONFORME);
         
         setSUCURSAL(SUCURSAL);
         
         setMEDIO_RECEPCION(MEDIO_RECEPCION);
         
         
         setNOMBRE_RECEPCIONISTA(NOMBRE_RECEPCIONISTA);
         
         setMUESTRAS(MUESTRAS);
         
         setCAUSAS(CAUSAS);
         setACCIONES(ACCIONES);
         
         setRESPONSABLE(RESPONSABLE);
         
         setFECHA_RECLAMO(FECHA_RECLAMO);
         
         setNOMBRE_RECEPCION_PROVEEDOR(NOMBRE_RECEPCION_PROVEEDOR);
         
         setPROVEEDOR_ID(PROVEEDOR_ID);
         
         setFECHA_RECEPCION_PROVEEDOR(FECHA_RECEPCION_PROVEEDOR);
         
         setMOTIVO_RECLAMO(MOTIVO_RECLAMO);
                 
         setMEDIDAS(MEDIDAS);
         
         
     
     
         
     return null;
     }
     
     
    
    
    
    
    
}
