/**
 * TableBeanReclamo
 *
 * @author juan.cuello
 */
package Reclamo;

import Configuracion.Database;
import com.itextpdf.text.Image;
import java.io.Serializable;
import java.sql.*;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class TableBeanReclamo implements Serializable {

    private String imageName;
    private String fechaRecepcionProveedor;
    private String imageResultado;
    private String proveedorId;
    private int imageId;

    private String NRO_CONFORMIDAD;
    private String NOMBRE_CLIENTE;
    private String PRODUCTO_NO_CONFORME;
    private String MOTIVO_RECLAMO;
    private String SUCURSAL;
    private String MEDIO_RECEPCION;
    private String NOMBRE_RECEPCIONISTA;
    private String MUESTRAS;
    private String MEDIDAS;
    private String FECHA_RECLAMO;
    private String NOMBRE_RECEPCION_PROVEEDOR;
    private String CAUSAS;
    private String ACCIONES;
    private String RESPONSABLE;

    private Image foto;

    private String imageID;

    private TableBeanReclamo selectedReclamo;

    public TableBeanReclamo getSelectedReclamo() {
        return selectedReclamo;
    }

    public void setSelectedReclamo(TableBeanReclamo selectedReclamo) {
        this.selectedReclamo = selectedReclamo;
    }

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }

    public Image getFoto() {
        return foto;
    }

    public void setFoto(Image foto) {
        this.foto = foto;
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

    public String getFECHA_RECLAMO() {
        return FECHA_RECLAMO;
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

    public String getMOTIVO_RECLAMO() {
        return MOTIVO_RECLAMO;
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

    public void setFECHA_RECLAMO(String FECHA_RECLAMO) {
        this.FECHA_RECLAMO = FECHA_RECLAMO;
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

    public int getImageid() {
        return imageId;
    }

    public void setImageid(int imageId) {
        this.imageId = imageId;
    }

    public String getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(String proveedorId) {
        this.proveedorId = proveedorId;
    }

    public String getFechaRecepcionProveedor() {
        return fechaRecepcionProveedor;
    }

    public void setFechaRecepcionProveedor(String fechaRecepcionProveedor) {
        this.fechaRecepcionProveedor = fechaRecepcionProveedor;
    }

    public String getImageResultado() {
        return imageResultado;
    }

    public void setImageResultado(String imageResultado) {
        this.imageResultado = imageResultado;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageLength() {
        return imageLength;
    }

    public void setImageLength(String imageLength) {
        this.imageLength = imageLength;
    }
    private String imageLength;

    Connection MySQLcon = null;
    Statement stmt = null;
    PreparedStatement ps;
    ResultSet rs;

    public List<TableBeanReclamo> getAllImage() {
        List<TableBeanReclamo> imageInfo = new ArrayList<TableBeanReclamo>();
        Connection con = Database.getConnection();
        try {
            stmt = con.createStatement();
            String strSql = "select IMAGE_FOTORECLAMO,IMAGE_FOTORECLAMO_NAME,PROVEEDOR_ID,FECHA_RECEPCION_PROVEEDOR,RECLAMOS_ID,NRO_CONFORMIDAD,NOMBRE_CLIENTE,PRODUCTO_NO_CONFORME,MOTIVO_RECLAMO,SUCURSAL,MEDIO_RECEPCION,NOMBRE_RECEPCIONISTA,MUESTRAS,MEDIDAS,FECHA_RECLAMO,NOMBRE_RECEPCION_PROVEEDOR,CAUSAS,ACCIONES,RESPONSABLE  from reclamos order by RECLAMOS_ID ";

            //System.err.println("*select all***" + strSql);
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                TableBeanReclamo tbl = new TableBeanReclamo();

                tbl.setImageName(rs.getString("IMAGE_FOTORECLAMO_NAME"));
                tbl.setProveedorId(rs.getString("PROVEEDOR_ID"));
                tbl.setFechaRecepcionProveedor(rs.getString("FECHA_RECEPCION_PROVEEDOR"));
                tbl.setImageID(rs.getString("RECLAMOS_ID"));

                //new
                tbl.setNRO_CONFORMIDAD(rs.getString("NRO_CONFORMIDAD"));
                tbl.setNOMBRE_CLIENTE(rs.getString("NOMBRE_CLIENTE"));
                tbl.setPRODUCTO_NO_CONFORME(rs.getString("PRODUCTO_NO_CONFORME"));
                tbl.setMOTIVO_RECLAMO(rs.getString("MOTIVO_RECLAMO"));
                tbl.setSUCURSAL(rs.getString("SUCURSAL"));
                tbl.setMEDIO_RECEPCION(rs.getString("MEDIO_RECEPCION"));
                tbl.setNOMBRE_RECEPCIONISTA(rs.getString("NOMBRE_RECEPCIONISTA"));
                tbl.setMUESTRAS(rs.getString("MUESTRAS"));
                tbl.setMEDIDAS(rs.getString("MEDIDAS"));
                tbl.setFECHA_RECLAMO(rs.getString("FECHA_RECLAMO"));
                tbl.setNOMBRE_RECEPCION_PROVEEDOR(rs.getString("NOMBRE_RECEPCION_PROVEEDOR"));
                tbl.setCAUSAS(rs.getString("CAUSAS"));
                tbl.setACCIONES(rs.getString("ACCIONES"));
                tbl.setRESPONSABLE(rs.getString("RESPONSABLE"));

                imageInfo.add(tbl);
            }
        } catch (SQLException e) {
            System.out.println("Exception in get All reclamos::" + e.getMessage());
        }
        return imageInfo;
    }

    public String borrarReclamo(String imageID) throws SQLException {
        Connection con = Database.getConnection();
        try {
            stmt = con.createStatement();
            String strSql = "DELETE FROM reclamos WHERE RECLAMOS_ID=" + imageID;
            //System.err.println("*select all***" + strSql);
            stmt.executeUpdate(strSql);
            System.out.println("RECLAMO borrado exitosamente" + imageID.toString());
            con.close();

            FacesMessage msgsuccess = new FacesMessage(FacesMessage.SEVERITY_WARN, "Reclamo", "Borrado exitosamente");
            FacesContext.getCurrentInstance().addMessage(null, msgsuccess);

            return null;
        } catch (SQLException e) {
            System.out.println("Exception in borrar Reclamo::" + e.getMessage());
            FacesMessage msgsuccess = new FacesMessage("Ocurrió un error borrando el reclamo.");
            FacesContext.getCurrentInstance().addMessage(null, msgsuccess);

            con.close();
        }
        return null;
    }

}
