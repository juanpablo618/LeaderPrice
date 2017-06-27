/**
 *
 * @author juan.cuello
 */
package Reclamo;

import Configuracion.ApplicationProperties;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import java.net.URL;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class ReclamoPdfBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String NRO_CONFORMIDAD;
    private String NOMBRE_CLIENTE;
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
    private String IMAGE_FOTORECLAMO_NAME;
    private String PROVEEDOR_ID;
    private Date FECHA_RECLAMO;
    private Date FECHA_RECEPCION_PROVEEDOR;

    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    DateFormat df2 = new SimpleDateFormat("yyyy/MM/dd");

    String orderNo;

    public String getPROVEEDOR_ID() {
        return PROVEEDOR_ID;
    }

    public String getNRO_CONFORMIDAD() {
        return NRO_CONFORMIDAD;
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

    public void setNRO_CONFORMIDAD(String NRO_CONFORMIDAD) {
        this.NRO_CONFORMIDAD = NRO_CONFORMIDAD;
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

    public void setPROVEEDOR_ID(String PROVEEDOR_ID) {
        this.PROVEEDOR_ID = PROVEEDOR_ID;
    }

    public void setFECHA_RECLAMO(Date FECHA_RECLAMO) {
        this.FECHA_RECLAMO = FECHA_RECLAMO;
    }

    public void setFECHA_RECEPCION_PROVEEDOR(Date FECHA_RECEPCION_PROVEEDOR) {
        this.FECHA_RECEPCION_PROVEEDOR = FECHA_RECEPCION_PROVEEDOR;
    }

    public String getIMAGE_FOTORECLAMO_NAME() {
        return IMAGE_FOTORECLAMO_NAME;
    }

    public void setIMAGE_FOTORECLAMO_NAME(String IMAGE_FOTORECLAMO_NAME) {
        this.IMAGE_FOTORECLAMO_NAME = IMAGE_FOTORECLAMO_NAME;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    Connection MySQLcon = null;
    Statement stmt = null;
    PreparedStatement ps;
    ResultSet rs;

    public String hacerPdf(String imageID, String NRO_CONFORMIDAD, String NOMBRE_CLIENTE, String PRODUCTO_NO_CONFORME, String SUCURSAL, String MEDIO_RECEPCION, String NOMBRE_RECEPCIONISTA, String MUESTRAS, String CAUSAS, String ACCIONES, String RESPONSABLE, String FECHA_RECLAMO, String NOMBRE_RECEPCION_PROVEEDOR, String PROVEEDOR, String FECHA_RECEPCION_PROVEEDOR, String MOTIVO_RECLAMO, String MEDIDAS) throws SQLException {

        System.out.println("haciendo pdf para ");
        System.out.println(imageID.toString());

        ApplicationProperties aplicationproperties = new ApplicationProperties();

        Document documento = new Document();
        FileOutputStream ficheroPdf;
        try {

            //SUMAR aplicationproperties.getCarpetaDeDescargaYAnotificacion() EN LA LINEA 397 + o - para que quede más lindo el mje al usuario
            ficheroPdf = new FileOutputStream(aplicationproperties.getCarpetaDeDescargaYAnotificacion() + imageID + "-" + NRO_CONFORMIDAD + "-" + PRODUCTO_NO_CONFORME + ".PDF");
            PdfWriter.getInstance(
                    documento,
                    ficheroPdf
            ).setInitialLeading(20);

        } catch (Exception ex) {
            System.out.println(ex.toString());
            FacesMessage msgfail = new FacesMessage("Problema al generar pdf, NO se ha creado su archivo.");
            FacesContext.getCurrentInstance().addMessage(null, msgfail);

        }
        try {
            documento.open();
            Paragraph parrafo1 = new Paragraph("Administrador de calidad");
            parrafo1.setAlignment(1);//el 1 es para centrar
            documento.add(parrafo1);
            Paragraph parrafo2 = new Paragraph("Libertad S.A");
            parrafo2.setAlignment(1);//el 1 es para centrar
            documento.add(parrafo2);
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" Reclamo N°: " + imageID));

            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            
            documento.add(new Paragraph(" Número de Conformidad: " + NRO_CONFORMIDAD));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));

            documento.add(new Paragraph(" Fecha del Reclamo: " + FECHA_RECLAMO));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            
            documento.add(new Paragraph(" Producto No Conforme: " + PRODUCTO_NO_CONFORME));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            
            documento.add(new Paragraph(" Cliente: " + NOMBRE_CLIENTE));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            
            
            documento.add(new Paragraph(" Perteneciente a Sucursal: " + SUCURSAL));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            
            documento.add(new Paragraph(" Motivo del reclamo: " + MOTIVO_RECLAMO));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            
            documento.add(new Paragraph(" Recepcionista Libertad: " + NOMBRE_RECEPCIONISTA));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            
            documento.add(new Paragraph(" Medidas adoptadas por Libertad: " + MEDIDAS));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));

            documento.add(new Paragraph(" Muestras adquiridas: " + MUESTRAS));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));

            documento.add(new Paragraph(" Medio de recepción: " + MEDIO_RECEPCION));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            
            documento.add(new Paragraph(" Proveedor a Cargo: " + PROVEEDOR));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));

            documento.add(new Paragraph(" Fecha de Envió al Proveedor: " + FECHA_RECEPCION_PROVEEDOR));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));

            documento.add(new Paragraph(" Recepcionó el Reclamo: " + NOMBRE_RECEPCION_PROVEEDOR));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));

            documento.add(new Paragraph(" Responsable de gestión del Reclamo: " + RESPONSABLE));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));

            
            documento.add(new Paragraph(" Causa/s: " + CAUSAS));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            
            documento.add(new Paragraph(" Acción/es a implementar por Proveedor: " + ACCIONES));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));


            documento.add(new Paragraph("Muestra Fotográfica"));

            String imageUrl = "http://localhost:8080/LeaderPrice/faces/DisplayImage?Image_id=" + imageID;

            Image image2 = Image.getInstance(new URL(imageUrl));
            image2.setAlignment(1);
            image2.scaleToFit(200f, 200f);
            documento.add(image2);

            //el numero indica la cantidad de Columnas
            // esto nos crea una tabla de 3 Columnas por dos Filas
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

            documento.add(tabla2);
            documento.close();

            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("PDF Generado en la carpeta:  " + aplicationproperties.getCarpetaDeDescargaYAnotificacion() + "."));

        } catch (Exception ex) {
            System.out.println(ex.toString());
            FacesMessage msgfail = new FacesMessage("Problema al generar pdf, NO se ha creado su archivo.");
            FacesContext.getCurrentInstance().addMessage(null, msgfail);

        }

        return null;

    }

}
