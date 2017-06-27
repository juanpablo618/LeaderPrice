
/**
 *Reclamo
 * 
 * @author juan.cuello
 */
    

package Reclamo;

import Configuracion.Database;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
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
 

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;



@ManagedBean
@SessionScoped
public class Reclamo implements Serializable {
 
    private static final long serialVersionUID = 1L;
    
    
    private UploadedFile file;
    
    
    //private String NRO_CONFORMIDAD;
    private Integer NRO_CONFORMIDAD;
    private String NOMBRE_CLIENTE ;
    private String PRODUCTO_NO_CONFORME;
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
   
    
    private String PROVEEDOR_ID ;
    
    private Date FECHA_RECLAMO;
     private Date FECHA_RECEPCION_PROVEEDOR;
    
    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    
    DateFormat df2 = new SimpleDateFormat("yyyy/MM/dd");

     
    public String getPROVEEDOR_ID() {
        return PROVEEDOR_ID;
    }

    public void setPROVEEDOR_ID(String PROVEEDOR_ID) {
        this.PROVEEDOR_ID = PROVEEDOR_ID;
    }
        
    public void setNRO_CONFORMIDAD(Integer NRO_CONFORMIDAD) {
        this.NRO_CONFORMIDAD = NRO_CONFORMIDAD;
    }

    public String getNOMBRE_CLIENTE() {
        return NOMBRE_CLIENTE;
    }

    public String getPRODUCTO_NO_CONFORME() {
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

    public Date getFECHA_RECLAMO() {
        return FECHA_RECLAMO;
    }

    public Date getFECHA_RECEPCION_PROVEEDOR() {
        return FECHA_RECEPCION_PROVEEDOR;
    }

    public Integer getNRO_CONFORMIDAD() {
        return NRO_CONFORMIDAD;
    }

    

    public void setNOMBRE_CLIENTE(String NOMBRE_CLIENTE) {
        this.NOMBRE_CLIENTE = NOMBRE_CLIENTE;
    }

    public void setPRODUCTO_NO_CONFORME(String PRODUCTO_NO_CONFORME) {
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

    public void setFECHA_RECLAMO(Date FECHA_RECLAMO) {
        this.FECHA_RECLAMO = FECHA_RECLAMO;
    }

    public void setFECHA_RECEPCION_PROVEEDOR(Date FECHA_RECEPCION_PROVEEDOR) {
        this.FECHA_RECEPCION_PROVEEDOR = FECHA_RECEPCION_PROVEEDOR;
    }

    
        
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
 
    
    
    public String upload() {
        System.out.println("insertando reclamo into reclamos");
        String outcome = null;
        
        
        if (file != null) {
            
            if(file.getFileName() == null   || (file.getFileName().equals("")) )  {
                    System.out.println("No ingreso archivo");
              
                FacesMessage msgfail3 = new FacesMessage("NO ingreso Archivo por favor seleccione uno", file.getFileName() + "NO se ha almacenado.");
                FacesContext.getCurrentInstance().addMessage(null, msgfail3);
            }else{

                    try {
                        System.out.println(file.getFileName());
                        InputStream fin2 = file.getInputstream();

                        Connection con = Database.getConnection();

                        PreparedStatement pre = con.prepareStatement("insert into reclamos (IMAGE_FOTORECLAMO_NAME,IMAGE_FOTORECLAMO,NRO_CONFORMIDAD,NOMBRE_CLIENTE,PRODUCTO_NO_CONFORME,MOTIVO_RECLAMO,SUCURSAL,MEDIO_RECEPCION,NOMBRE_RECEPCIONISTA,MUESTRAS,MEDIDAS,FECHA_RECLAMO,PROVEEDOR_ID,NOMBRE_RECEPCION_PROVEEDOR,FECHA_RECEPCION_PROVEEDOR,CAUSAS,ACCIONES,RESPONSABLE) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                        pre.setString(1, file.getFileName().toString());
                        pre.setBinaryStream(2, fin2, file.getSize());

                        pre.setString(3, NRO_CONFORMIDAD.toString());

                        pre.setString(4, NOMBRE_CLIENTE.toString());

                        pre.setString(5, PRODUCTO_NO_CONFORME.toString());

                        pre.setString(6, MOTIVO_RECLAMO.toString());

                        pre.setString(7, SUCURSAL.toString());

                        pre.setString(8, MEDIO_RECEPCION.toString());

                        pre.setString(9, NOMBRE_RECEPCIONISTA.toString());

                        pre.setString(10, MUESTRAS.toString());

                        pre.setString(11, MEDIDAS.toString());

                       // pre.setString(12, FECHA_RECLAMO.toString());
                       pre.setString(12,df.format(FECHA_RECLAMO));


                        pre.setString(13, PROVEEDOR_ID.toString());


                        pre.setString(14, NOMBRE_RECEPCION_PROVEEDOR.toString());

                        //pre.setString(15, FECHA_RECEPCION_PROVEEDOR.toString());
                        pre.setString(15,df2.format(FECHA_RECEPCION_PROVEEDOR));

                        pre.setString(16, CAUSAS.toString());
                        pre.setString(17, ACCIONES.toString());
                        pre.setString(18, RESPONSABLE.toString());

                       // pre.setString(19, DESARROLLO_ID.toString());

                        pre.executeUpdate();
                        System.out.println("Inserting Successfully!");
                        pre.close();
                        FacesMessage msg = new FacesMessage("Imagen Cargada Exitosamente", file.getFileName() + " se ha cargado.");
                        FacesContext.getCurrentInstance().addMessage(null, msg);
                        
                        outcome = "/faces/Reclamo.xhtml?faces-redirect=true&amp;includeViewParams=true";
                        return outcome;
                        

                    } catch (Exception e) {
                        System.out.println("Exception-File Upload. en reclamo" + e.getMessage());

                         FacesMessage msgfail = new FacesMessage("Imagen demasiado grande", file.getFileName() + " NO se ha almacenado.");
                        FacesContext.getCurrentInstance().addMessage(null, msgfail);
                        outcome = "/faces/Reclamo.xhtml?faces-redirect=true&amp;includeViewParams=true";
                        return outcome;
                        

                    }
            }    
        }
        else{
        FacesMessage msg = new FacesMessage("Debe adjuntar un archivo!");
                FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        outcome = "/faces/Reclamo.xhtml?faces-redirect=true&amp;includeViewParams=true";
                        return outcome;
                        
    }
    
       
    
    public void hacerpdf() throws DocumentException, FileNotFoundException {
            
        Document documento = new Document();  
                        FileOutputStream ficheroPdf;
                        try 
                        {
                         ficheroPdf = new FileOutputStream("C:\\Users\\juan.cuello\\Desktop\\ejemplo.PDF");
                         PdfWriter.getInstance(
                                               documento, 
                                               ficheroPdf
                                               ).setInitialLeading(20);
                        }
                        catch (Exception ex) 
                        {
                         System.out.println(ex.toString());
                        }
                        try{
                            documento.open();
                            documento.add(new Paragraph("Administrador de calidad" ));
                            Paragraph parrafo2 = new Paragraph("Libertad S.A");
                            parrafo2.setAlignment(1);//el 1 es para centrar
                            documento.add(parrafo2);
                            documento.add(new Paragraph(" "));
                            PdfPTable tabla = new PdfPTable(3);
                            //el numero indica la cantidad de Columnas
                            tabla.addCell("celda1");
                            tabla.addCell("celda2");
                            tabla.addCell("celda3");
                            tabla.addCell("celda4");
                            tabla.addCell("celda5");
                            tabla.addCell("celda6");
                            // esto nos crea una tabla de 3 Columnas por dos Filas
                            documento.add(tabla);
                            documento.add(new Paragraph(" "));
                            PdfPTable tabla2 = new PdfPTable(3);
                            PdfPCell celda = new PdfPCell(
                                               new Paragraph("celda \nmodificada")
                                                         );
                            celda.setColspan(2);
                           //cantidad de columnas que ocupa esta celda
                            celda.setRowspan(2);
                           //cantidad de filas que ocupa esta celda
                            tabla2.addCell(celda);
                            tabla2.addCell("celda5");
                            tabla2.addCell("celda6");
                            tabla2.addCell("celda7");
                            tabla2.addCell("celda8");
                            tabla2.addCell("celda9");
                            documento.add(tabla2);
                            documento.close();
                        }catch(Exception ex){
                            System.out.println(ex.toString());
                        }
     
           }
    
    
    
    
}
