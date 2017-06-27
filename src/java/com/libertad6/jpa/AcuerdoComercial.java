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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juan.cuello
 */
@Entity
@Table(name = "acuerdo_comercial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AcuerdoComercial.findAll", query = "SELECT a FROM AcuerdoComercial a"),
    @NamedQuery(name = "AcuerdoComercial.findByAcuerdoComercialId", query = "SELECT a FROM AcuerdoComercial a WHERE a.acuerdoComercialId = :acuerdoComercialId"),
    @NamedQuery(name = "AcuerdoComercial.findByFecha", query = "SELECT a FROM AcuerdoComercial a WHERE a.fecha = :fecha"),
    @NamedQuery(name = "AcuerdoComercial.findByFileAcuerdoName", query = "SELECT a FROM AcuerdoComercial a WHERE a.fileAcuerdoName = :fileAcuerdoName")})
public class AcuerdoComercial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ACUERDO_COMERCIAL_ID")
    private Integer acuerdoComercialId;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Size(max = 45)
    @Column(name = "FILE_ACUERDO_NAME")
    private String fileAcuerdoName;
    @Lob
    @Column(name = "FILE_ACUERDO")
    private byte[] fileAcuerdo;
    @JoinColumn(name = "PROVEEDOR_ID", referencedColumnName = "PROVEEDOR_ID")
    @ManyToOne
    private Proveedor proveedorId;

    public AcuerdoComercial() {
    }

    public AcuerdoComercial(Integer acuerdoComercialId) {
        this.acuerdoComercialId = acuerdoComercialId;
    }

    public Integer getAcuerdoComercialId() {
        return acuerdoComercialId;
    }

    public void setAcuerdoComercialId(Integer acuerdoComercialId) {
        this.acuerdoComercialId = acuerdoComercialId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getFileAcuerdoName() {
        return fileAcuerdoName;
    }

    public void setFileAcuerdoName(String fileAcuerdoName) {
        this.fileAcuerdoName = fileAcuerdoName;
    }

    public byte[] getFileAcuerdo() {
        return fileAcuerdo;
    }

    public void setFileAcuerdo(byte[] fileAcuerdo) {
        this.fileAcuerdo = fileAcuerdo;
    }

    public Proveedor getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Proveedor proveedorId) {
        this.proveedorId = proveedorId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (acuerdoComercialId != null ? acuerdoComercialId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AcuerdoComercial)) {
            return false;
        }
        AcuerdoComercial other = (AcuerdoComercial) object;
        if ((this.acuerdoComercialId == null && other.acuerdoComercialId != null) || (this.acuerdoComercialId != null && !this.acuerdoComercialId.equals(other.acuerdoComercialId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.libertad6.jpa.AcuerdoComercial[ acuerdoComercialId=" + acuerdoComercialId + " ]";
    }
    
}
