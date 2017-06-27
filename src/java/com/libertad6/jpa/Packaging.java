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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juan.cuello
 */
@Entity
@Table(name = "packaging")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Packaging.findAll", query = "SELECT p FROM Packaging p"),
    @NamedQuery(name = "Packaging.findByPackagingId", query = "SELECT p FROM Packaging p WHERE p.packagingId = :packagingId"),
    @NamedQuery(name = "Packaging.findByMkt", query = "SELECT p FROM Packaging p WHERE p.mkt = :mkt"),
    @NamedQuery(name = "Packaging.findByProveedor", query = "SELECT p FROM Packaging p WHERE p.proveedor = :proveedor"),
    @NamedQuery(name = "Packaging.findByCalidad", query = "SELECT p FROM Packaging p WHERE p.calidad = :calidad"),
    @NamedQuery(name = "Packaging.findByFilePlanomecanicoName", query = "SELECT p FROM Packaging p WHERE p.filePlanomecanicoName = :filePlanomecanicoName"),
    @NamedQuery(name = "Packaging.findByFileArteName", query = "SELECT p FROM Packaging p WHERE p.fileArteName = :fileArteName"),
    @NamedQuery(name = "Packaging.findByImageFotoName", query = "SELECT p FROM Packaging p WHERE p.imageFotoName = :imageFotoName"),
    @NamedQuery(name = "Packaging.findByDesarrolloId", query = "SELECT p FROM Packaging p WHERE p.desarrolloId = :desarrolloId")})
public class Packaging implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PACKAGING_ID")
    private Integer packagingId;
    @Column(name = "MKT")
    private Boolean mkt;
    @Column(name = "PROVEEDOR")
    private Boolean proveedor;
    @Column(name = "CALIDAD")
    private Boolean calidad;
    @Size(max = 45)
    @Column(name = "FILE_PLANOMECANICO_NAME")
    private String filePlanomecanicoName;
    @Lob
    @Column(name = "FILE_PLANOMECANICO")
    private byte[] filePlanomecanico;
    @Size(max = 45)
    @Column(name = "FILE_ARTE_NAME")
    private String fileArteName;
    @Lob
    @Column(name = "FILE_ARTE")
    private byte[] fileArte;
    @Size(max = 45)
    @Column(name = "IMAGE_FOTO_NAME")
    private String imageFotoName;
    @Lob
    @Column(name = "IMAGE_FOTO")
    private byte[] imageFoto;
    @Column(name = "DESARROLLO_ID")
    private Integer desarrolloId;

    public Packaging() {
    }

    public Packaging(Integer packagingId) {
        this.packagingId = packagingId;
    }

    public Integer getPackagingId() {
        return packagingId;
    }

    public void setPackagingId(Integer packagingId) {
        this.packagingId = packagingId;
    }

    public Boolean getMkt() {
        return mkt;
    }

    public void setMkt(Boolean mkt) {
        this.mkt = mkt;
    }

    public Boolean getProveedor() {
        return proveedor;
    }

    public void setProveedor(Boolean proveedor) {
        this.proveedor = proveedor;
    }

    public Boolean getCalidad() {
        return calidad;
    }

    public void setCalidad(Boolean calidad) {
        this.calidad = calidad;
    }

    public String getFilePlanomecanicoName() {
        return filePlanomecanicoName;
    }

    public void setFilePlanomecanicoName(String filePlanomecanicoName) {
        this.filePlanomecanicoName = filePlanomecanicoName;
    }

    public byte[] getFilePlanomecanico() {
        return filePlanomecanico;
    }

    public void setFilePlanomecanico(byte[] filePlanomecanico) {
        this.filePlanomecanico = filePlanomecanico;
    }

    public String getFileArteName() {
        return fileArteName;
    }

    public void setFileArteName(String fileArteName) {
        this.fileArteName = fileArteName;
    }

    public byte[] getFileArte() {
        return fileArte;
    }

    public void setFileArte(byte[] fileArte) {
        this.fileArte = fileArte;
    }

    public String getImageFotoName() {
        return imageFotoName;
    }

    public void setImageFotoName(String imageFotoName) {
        this.imageFotoName = imageFotoName;
    }

    public byte[] getImageFoto() {
        return imageFoto;
    }

    public void setImageFoto(byte[] imageFoto) {
        this.imageFoto = imageFoto;
    }

    public Integer getDesarrolloId() {
        return desarrolloId;
    }

    public void setDesarrolloId(Integer desarrolloId) {
        this.desarrolloId = desarrolloId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (packagingId != null ? packagingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Packaging)) {
            return false;
        }
        Packaging other = (Packaging) object;
        if ((this.packagingId == null && other.packagingId != null) || (this.packagingId != null && !this.packagingId.equals(other.packagingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.libertad6.jpa.Packaging[ packagingId=" + packagingId + " ]";
    }
    
}
