/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libertad6.jpa;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juan.cuello
 */
@Entity
@Table(name = "habilitacion_producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HabilitacionProducto.findAll", query = "SELECT h FROM HabilitacionProducto h"),
    @NamedQuery(name = "HabilitacionProducto.findByHabilitacionProductoId", query = "SELECT h FROM HabilitacionProducto h WHERE h.habilitacionProductoId = :habilitacionProductoId"),
    @NamedQuery(name = "HabilitacionProducto.findByFileHabilitacionProductoName", query = "SELECT h FROM HabilitacionProducto h WHERE h.fileHabilitacionProductoName = :fileHabilitacionProductoName")})
public class HabilitacionProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "HABILITACION_PRODUCTO_ID")
    private Integer habilitacionProductoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "FILE_HABILITACION_PRODUCTO_NAME")
    private String fileHabilitacionProductoName;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "FILE_HABILITACION_PRODUCTO")
    private byte[] fileHabilitacionProducto;
    @JoinColumn(name = "DATOS_LEGALES_ID", referencedColumnName = "DATOS_LEGALES_ID")
    @ManyToOne(optional = false)
    private DatosLegales datosLegalesId;

    public HabilitacionProducto() {
    }

    public HabilitacionProducto(Integer habilitacionProductoId) {
        this.habilitacionProductoId = habilitacionProductoId;
    }

    public HabilitacionProducto(Integer habilitacionProductoId, String fileHabilitacionProductoName, byte[] fileHabilitacionProducto) {
        this.habilitacionProductoId = habilitacionProductoId;
        this.fileHabilitacionProductoName = fileHabilitacionProductoName;
        this.fileHabilitacionProducto = fileHabilitacionProducto;
    }

    public Integer getHabilitacionProductoId() {
        return habilitacionProductoId;
    }

    public void setHabilitacionProductoId(Integer habilitacionProductoId) {
        this.habilitacionProductoId = habilitacionProductoId;
    }

    public String getFileHabilitacionProductoName() {
        return fileHabilitacionProductoName;
    }

    public void setFileHabilitacionProductoName(String fileHabilitacionProductoName) {
        this.fileHabilitacionProductoName = fileHabilitacionProductoName;
    }

    public byte[] getFileHabilitacionProducto() {
        return fileHabilitacionProducto;
    }

    public void setFileHabilitacionProducto(byte[] fileHabilitacionProducto) {
        this.fileHabilitacionProducto = fileHabilitacionProducto;
    }

    public DatosLegales getDatosLegalesId() {
        return datosLegalesId;
    }

    public void setDatosLegalesId(DatosLegales datosLegalesId) {
        this.datosLegalesId = datosLegalesId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (habilitacionProductoId != null ? habilitacionProductoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HabilitacionProducto)) {
            return false;
        }
        HabilitacionProducto other = (HabilitacionProducto) object;
        if ((this.habilitacionProductoId == null && other.habilitacionProductoId != null) || (this.habilitacionProductoId != null && !this.habilitacionProductoId.equals(other.habilitacionProductoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.libertad6.jpa.HabilitacionProducto[ habilitacionProductoId=" + habilitacionProductoId + " ]";
    }
    
}
