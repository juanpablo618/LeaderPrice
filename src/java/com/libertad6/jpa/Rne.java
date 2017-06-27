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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juan.cuello
 */
@Entity
@Table(name = "rne")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rne.findAll", query = "SELECT r FROM Rne r"),
    @NamedQuery(name = "Rne.findByRneId", query = "SELECT r FROM Rne r WHERE r.rneId = :rneId"),
    @NamedQuery(name = "Rne.findByFileRneName", query = "SELECT r FROM Rne r WHERE r.fileRneName = :fileRneName")})
public class Rne implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RNE_ID")
    private Integer rneId;
    @Size(max = 45)
    @Column(name = "FILE_RNE_NAME")
    private String fileRneName;
    @Lob
    @Column(name = "FILE_RNE")
    private byte[] fileRne;
    @JoinColumn(name = "DATOS_LEGALES_ID", referencedColumnName = "DATOS_LEGALES_ID")
    @ManyToOne
    private DatosLegales datosLegalesId;

    public Rne() {
    }

    public Rne(Integer rneId) {
        this.rneId = rneId;
    }

    public Integer getRneId() {
        return rneId;
    }

    public void setRneId(Integer rneId) {
        this.rneId = rneId;
    }

    public String getFileRneName() {
        return fileRneName;
    }

    public void setFileRneName(String fileRneName) {
        this.fileRneName = fileRneName;
    }

    public byte[] getFileRne() {
        return fileRne;
    }

    public void setFileRne(byte[] fileRne) {
        this.fileRne = fileRne;
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
        hash += (rneId != null ? rneId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rne)) {
            return false;
        }
        Rne other = (Rne) object;
        if ((this.rneId == null && other.rneId != null) || (this.rneId != null && !this.rneId.equals(other.rneId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.libertad6.jpa.Rne[ rneId=" + rneId + " ]";
    }
    
}
