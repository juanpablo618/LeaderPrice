/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libertad6.jpa;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juan.cuello
 */
@Entity
@Table(name = "reclamos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reclamos.findAll", query = "SELECT r FROM Reclamos r"),
    @NamedQuery(name = "Reclamos.findByReclamosId", query = "SELECT r FROM Reclamos r WHERE r.reclamosId = :reclamosId"),
    @NamedQuery(name = "Reclamos.findByNroConformidad", query = "SELECT r FROM Reclamos r WHERE r.nroConformidad = :nroConformidad"),
    @NamedQuery(name = "Reclamos.findByNombreCliente", query = "SELECT r FROM Reclamos r WHERE r.nombreCliente = :nombreCliente"),
    @NamedQuery(name = "Reclamos.findByMotivoReclamo", query = "SELECT r FROM Reclamos r WHERE r.motivoReclamo = :motivoReclamo"),
    @NamedQuery(name = "Reclamos.findBySucursal", query = "SELECT r FROM Reclamos r WHERE r.sucursal = :sucursal"),
    @NamedQuery(name = "Reclamos.findByMedioRecepcion", query = "SELECT r FROM Reclamos r WHERE r.medioRecepcion = :medioRecepcion"),
    @NamedQuery(name = "Reclamos.findByNombreRecepcionista", query = "SELECT r FROM Reclamos r WHERE r.nombreRecepcionista = :nombreRecepcionista"),
    @NamedQuery(name = "Reclamos.findByMuestras", query = "SELECT r FROM Reclamos r WHERE r.muestras = :muestras"),
    @NamedQuery(name = "Reclamos.findByMedidas", query = "SELECT r FROM Reclamos r WHERE r.medidas = :medidas"),
    @NamedQuery(name = "Reclamos.findByFechaReclamo", query = "SELECT r FROM Reclamos r WHERE r.fechaReclamo = :fechaReclamo"),
    @NamedQuery(name = "Reclamos.findByImageFotoreclamoName", query = "SELECT r FROM Reclamos r WHERE r.imageFotoreclamoName = :imageFotoreclamoName"),
    @NamedQuery(name = "Reclamos.findByProveedorId", query = "SELECT r FROM Reclamos r WHERE r.proveedorId = :proveedorId"),
    @NamedQuery(name = "Reclamos.findByNombreRecepcionProveedor", query = "SELECT r FROM Reclamos r WHERE r.nombreRecepcionProveedor = :nombreRecepcionProveedor"),
    @NamedQuery(name = "Reclamos.findByFechaRecepcionProveedor", query = "SELECT r FROM Reclamos r WHERE r.fechaRecepcionProveedor = :fechaRecepcionProveedor"),
    @NamedQuery(name = "Reclamos.findByCausas", query = "SELECT r FROM Reclamos r WHERE r.causas = :causas"),
    @NamedQuery(name = "Reclamos.findByAcciones", query = "SELECT r FROM Reclamos r WHERE r.acciones = :acciones"),
    @NamedQuery(name = "Reclamos.findByResponsable", query = "SELECT r FROM Reclamos r WHERE r.responsable = :responsable")})
public class Reclamos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RECLAMOS_ID")
    private Integer reclamosId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NRO_CONFORMIDAD")
    private int nroConformidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "NOMBRE_CLIENTE")
    private String nombreCliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "MOTIVO_RECLAMO")
    private String motivoReclamo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "SUCURSAL")
    private String sucursal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "MEDIO_RECEPCION")
    private String medioRecepcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "NOMBRE_RECEPCIONISTA")
    private String nombreRecepcionista;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MUESTRAS")
    private boolean muestras;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "MEDIDAS")
    private String medidas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_RECLAMO")
    @Temporal(TemporalType.DATE)
    private Date fechaReclamo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "IMAGE_FOTORECLAMO_NAME")
    private String imageFotoreclamoName;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "IMAGE_FOTORECLAMO")
    private byte[] imageFotoreclamo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PROVEEDOR_ID")
    private int proveedorId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "NOMBRE_RECEPCION_PROVEEDOR")
    private String nombreRecepcionProveedor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_RECEPCION_PROVEEDOR")
    @Temporal(TemporalType.DATE)
    private Date fechaRecepcionProveedor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "CAUSAS")
    private String causas;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "ACCIONES")
    private String acciones;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "RESPONSABLE")
    private String responsable;
    @JoinColumn(name = "PRODUCTO_NO_CONFORME", referencedColumnName = "PRODUCTO_ID")
    @ManyToOne(optional = false)
    private Producto productoNoConforme;

    public Reclamos() {
    }

    public Reclamos(Integer reclamosId) {
        this.reclamosId = reclamosId;
    }

    public Reclamos(Integer reclamosId, int nroConformidad, String nombreCliente, String motivoReclamo, String sucursal, String medioRecepcion, String nombreRecepcionista, boolean muestras, String medidas, Date fechaReclamo, String imageFotoreclamoName, byte[] imageFotoreclamo, int proveedorId, String nombreRecepcionProveedor, Date fechaRecepcionProveedor, String causas, String acciones, String responsable) {
        this.reclamosId = reclamosId;
        this.nroConformidad = nroConformidad;
        this.nombreCliente = nombreCliente;
        this.motivoReclamo = motivoReclamo;
        this.sucursal = sucursal;
        this.medioRecepcion = medioRecepcion;
        this.nombreRecepcionista = nombreRecepcionista;
        this.muestras = muestras;
        this.medidas = medidas;
        this.fechaReclamo = fechaReclamo;
        this.imageFotoreclamoName = imageFotoreclamoName;
        this.imageFotoreclamo = imageFotoreclamo;
        this.proveedorId = proveedorId;
        this.nombreRecepcionProveedor = nombreRecepcionProveedor;
        this.fechaRecepcionProveedor = fechaRecepcionProveedor;
        this.causas = causas;
        this.acciones = acciones;
        this.responsable = responsable;
    }

    public Integer getReclamosId() {
        return reclamosId;
    }

    public void setReclamosId(Integer reclamosId) {
        this.reclamosId = reclamosId;
    }

    public int getNroConformidad() {
        return nroConformidad;
    }

    public void setNroConformidad(int nroConformidad) {
        this.nroConformidad = nroConformidad;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getMotivoReclamo() {
        return motivoReclamo;
    }

    public void setMotivoReclamo(String motivoReclamo) {
        this.motivoReclamo = motivoReclamo;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getMedioRecepcion() {
        return medioRecepcion;
    }

    public void setMedioRecepcion(String medioRecepcion) {
        this.medioRecepcion = medioRecepcion;
    }

    public String getNombreRecepcionista() {
        return nombreRecepcionista;
    }

    public void setNombreRecepcionista(String nombreRecepcionista) {
        this.nombreRecepcionista = nombreRecepcionista;
    }

    public boolean getMuestras() {
        return muestras;
    }

    public void setMuestras(boolean muestras) {
        this.muestras = muestras;
    }

    public String getMedidas() {
        return medidas;
    }

    public void setMedidas(String medidas) {
        this.medidas = medidas;
    }

    public Date getFechaReclamo() {
        return fechaReclamo;
    }

    public void setFechaReclamo(Date fechaReclamo) {
        this.fechaReclamo = fechaReclamo;
    }

    public String getImageFotoreclamoName() {
        return imageFotoreclamoName;
    }

    public void setImageFotoreclamoName(String imageFotoreclamoName) {
        this.imageFotoreclamoName = imageFotoreclamoName;
    }

    public byte[] getImageFotoreclamo() {
        return imageFotoreclamo;
    }

    public void setImageFotoreclamo(byte[] imageFotoreclamo) {
        this.imageFotoreclamo = imageFotoreclamo;
    }

    public int getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(int proveedorId) {
        this.proveedorId = proveedorId;
    }

    public String getNombreRecepcionProveedor() {
        return nombreRecepcionProveedor;
    }

    public void setNombreRecepcionProveedor(String nombreRecepcionProveedor) {
        this.nombreRecepcionProveedor = nombreRecepcionProveedor;
    }

    public Date getFechaRecepcionProveedor() {
        return fechaRecepcionProveedor;
    }

    public void setFechaRecepcionProveedor(Date fechaRecepcionProveedor) {
        this.fechaRecepcionProveedor = fechaRecepcionProveedor;
    }

    public String getCausas() {
        return causas;
    }

    public void setCausas(String causas) {
        this.causas = causas;
    }

    public String getAcciones() {
        return acciones;
    }

    public void setAcciones(String acciones) {
        this.acciones = acciones;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public Producto getProductoNoConforme() {
        return productoNoConforme;
    }

    public void setProductoNoConforme(Producto productoNoConforme) {
        this.productoNoConforme = productoNoConforme;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reclamosId != null ? reclamosId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reclamos)) {
            return false;
        }
        Reclamos other = (Reclamos) object;
        if ((this.reclamosId == null && other.reclamosId != null) || (this.reclamosId != null && !this.reclamosId.equals(other.reclamosId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.libertad6.jpa.Reclamos[ reclamosId=" + reclamosId + " ]";
    }
    
}
