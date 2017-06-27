/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libertad6.jpa;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juan.cuello
 */
@Entity
@Table(name = "datos_legales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DatosLegales.findAll", query = "SELECT d FROM DatosLegales d"),
    @NamedQuery(name = "DatosLegales.findByDatosLegalesId", query = "SELECT d FROM DatosLegales d WHERE d.datosLegalesId = :datosLegalesId"),
    @NamedQuery(name = "DatosLegales.findByDesarrolloId", query = "SELECT d FROM DatosLegales d WHERE d.desarrolloId = :desarrolloId"),
    @NamedQuery(name = "DatosLegales.findByFecha", query = "SELECT d FROM DatosLegales d WHERE d.fecha = :fecha"),
    @NamedQuery(name = "DatosLegales.findByVencimiento", query = "SELECT d FROM DatosLegales d WHERE d.vencimiento = :vencimiento"),
    @NamedQuery(name = "DatosLegales.findByFileDatolegalName", query = "SELECT d FROM DatosLegales d WHERE d.fileDatolegalName = :fileDatolegalName"),
    @NamedQuery(name = "DatosLegales.findByTipo", query = "SELECT d FROM DatosLegales d WHERE d.tipo = :tipo")})
public class DatosLegales implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DATOS_LEGALES_ID")
    private Integer datosLegalesId;
    @Column(name = "DESARROLLO_ID")
    private Integer desarrolloId;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "VENCIMIENTO")
    @Temporal(TemporalType.DATE)
    private Date vencimiento;
    @Size(max = 60)
    @Column(name = "FILE_DATOLEGAL_NAME")
    private String fileDatolegalName;
    @Lob
    @Column(name = "FILE_DATOSLEGAL")
    private byte[] fileDatoslegal;
    @Size(max = 60)
    @Column(name = "TIPO")
    private String tipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "datosLegalesId")
    private Collection<Certificado> certificadoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "datosLegalesId")
    private Collection<HabilitacionProducto> habilitacionProductoCollection;
    @OneToMany(mappedBy = "datosLegalesId")
    private Collection<Rne> rneCollection;

    public DatosLegales() {
    }

    public DatosLegales(Integer datosLegalesId) {
        this.datosLegalesId = datosLegalesId;
    }

    public Integer getDatosLegalesId() {
        return datosLegalesId;
    }

    public void setDatosLegalesId(Integer datosLegalesId) {
        this.datosLegalesId = datosLegalesId;
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

    public Date getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }

    public String getFileDatolegalName() {
        return fileDatolegalName;
    }

    public void setFileDatolegalName(String fileDatolegalName) {
        this.fileDatolegalName = fileDatolegalName;
    }

    public byte[] getFileDatoslegal() {
        return fileDatoslegal;
    }

    public void setFileDatoslegal(byte[] fileDatoslegal) {
        this.fileDatoslegal = fileDatoslegal;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @XmlTransient
    public Collection<Certificado> getCertificadoCollection() {
        return certificadoCollection;
    }

    public void setCertificadoCollection(Collection<Certificado> certificadoCollection) {
        this.certificadoCollection = certificadoCollection;
    }

    @XmlTransient
    public Collection<HabilitacionProducto> getHabilitacionProductoCollection() {
        return habilitacionProductoCollection;
    }

    public void setHabilitacionProductoCollection(Collection<HabilitacionProducto> habilitacionProductoCollection) {
        this.habilitacionProductoCollection = habilitacionProductoCollection;
    }

    @XmlTransient
    public Collection<Rne> getRneCollection() {
        return rneCollection;
    }

    public void setRneCollection(Collection<Rne> rneCollection) {
        this.rneCollection = rneCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (datosLegalesId != null ? datosLegalesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatosLegales)) {
            return false;
        }
        DatosLegales other = (DatosLegales) object;
        if ((this.datosLegalesId == null && other.datosLegalesId != null) || (this.datosLegalesId != null && !this.datosLegalesId.equals(other.datosLegalesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.libertad6.jpa.DatosLegales[ datosLegalesId=" + datosLegalesId + " ]";
    }
    
}
