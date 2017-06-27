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
@Table(name = "desarrollo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Desarrollo.findAll", query = "SELECT d FROM Desarrollo d"),
    @NamedQuery(name = "Desarrollo.findByDesarrolloId", query = "SELECT d FROM Desarrollo d WHERE d.desarrolloId = :desarrolloId"),
    @NamedQuery(name = "Desarrollo.findByFecha", query = "SELECT d FROM Desarrollo d WHERE d.fecha = :fecha"),
    @NamedQuery(name = "Desarrollo.findByTipoProyecto", query = "SELECT d FROM Desarrollo d WHERE d.tipoProyecto = :tipoProyecto"),
    @NamedQuery(name = "Desarrollo.findByCodigoComercial", query = "SELECT d FROM Desarrollo d WHERE d.codigoComercial = :codigoComercial"),
    @NamedQuery(name = "Desarrollo.findByUbicacionFisica", query = "SELECT d FROM Desarrollo d WHERE d.ubicacionFisica = :ubicacionFisica"),
    @NamedQuery(name = "Desarrollo.findByCodigoDesarrollo", query = "SELECT d FROM Desarrollo d WHERE d.codigoDesarrollo = :codigoDesarrollo")})
public class Desarrollo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DESARROLLO_ID")
    private Integer desarrolloId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "TIPO_PROYECTO")
    private String tipoProyecto;
    @Basic(optional = false)
    
    //@Min(4)
    //@Size(min = 1, max = 250)
    @Column(name = "CODIGO_COMERCIAL")
    private String codigoComercial;
    @Basic(optional = false)
    
    //@Size(min = 1, max = 250)
    @Column(name = "UBICACION_FISICA")
    private String ubicacionFisica;
    @Size(max = 50)
    @Column(name = "CODIGO_DESARROLLO")
    private String codigoDesarrollo;
    @JoinColumn(name = "PROVEEDOR_ID", referencedColumnName = "PROVEEDOR_ID")
    @ManyToOne
    private Proveedor proveedorId;
    @JoinColumn(name = "PRODUCTO_ID", referencedColumnName = "PRODUCTO_ID")
    @ManyToOne(optional = false)
    private Producto productoId;

    public Desarrollo() {
    }

    public Desarrollo(Integer desarrolloId) {
        this.desarrolloId = desarrolloId;
    }

    public Desarrollo(Integer desarrolloId, Date fecha, String tipoProyecto, String codigoComercial, String ubicacionFisica) {
        this.desarrolloId = desarrolloId;
        this.fecha = fecha;
        this.tipoProyecto = tipoProyecto;
        this.codigoComercial = codigoComercial;
        this.ubicacionFisica = ubicacionFisica;
    }

    public Integer getDesarrolloId() {
        return desarrolloId;
    }

    public void setDesarrolloId(Integer desarrolloId) {
        this.desarrolloId = desarrolloId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipoProyecto() {
        return tipoProyecto;
    }

    public void setTipoProyecto(String tipoProyecto) {
        this.tipoProyecto = tipoProyecto;
    }

    public String getCodigoComercial() {
        return codigoComercial;
    }

    public void setCodigoComercial(String codigoComercial) {
        this.codigoComercial = codigoComercial;
    }

    public String getUbicacionFisica() {
        return ubicacionFisica;
    }

    public void setUbicacionFisica(String ubicacionFisica) {
        this.ubicacionFisica = ubicacionFisica;
    }

    public String getCodigoDesarrollo() {
        return codigoDesarrollo;
    }

    public void setCodigoDesarrollo(String codigoDesarrollo) {
        this.codigoDesarrollo = codigoDesarrollo;
    }

    public Proveedor getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Proveedor proveedorId) {
        this.proveedorId = proveedorId;
    }

    public Producto getProductoId() {
        return productoId;
    }

    public void setProductoId(Producto productoId) {
        this.productoId = productoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (desarrolloId != null ? desarrolloId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Desarrollo)) {
            return false;
        }
        Desarrollo other = (Desarrollo) object;
        if ((this.desarrolloId == null && other.desarrolloId != null) || (this.desarrolloId != null && !this.desarrolloId.equals(other.desarrolloId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.libertad6.jpa.Desarrollo[ desarrolloId=" + desarrolloId + " ]";
    }
    
}
