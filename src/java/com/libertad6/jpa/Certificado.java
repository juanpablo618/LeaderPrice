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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juan.cuello
 */
@Entity
@Table(name = "certificado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Certificado.findAll", query = "SELECT c FROM Certificado c"),
    @NamedQuery(name = "Certificado.findByCertificadoId", query = "SELECT c FROM Certificado c WHERE c.certificadoId = :certificadoId")})
public class Certificado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CERTIFICADO_ID")
    private Integer certificadoId;
    @JoinColumn(name = "DATOS_LEGALES_ID", referencedColumnName = "DATOS_LEGALES_ID")
    @ManyToOne(optional = false)
    private DatosLegales datosLegalesId;

    public Certificado() {
    }

    public Certificado(Integer certificadoId) {
        this.certificadoId = certificadoId;
    }

    public Integer getCertificadoId() {
        return certificadoId;
    }

    public void setCertificadoId(Integer certificadoId) {
        this.certificadoId = certificadoId;
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
        hash += (certificadoId != null ? certificadoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Certificado)) {
            return false;
        }
        Certificado other = (Certificado) object;
        if ((this.certificadoId == null && other.certificadoId != null) || (this.certificadoId != null && !this.certificadoId.equals(other.certificadoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.libertad6.jpa.Certificado[ certificadoId=" + certificadoId + " ]";
    }
    
}
