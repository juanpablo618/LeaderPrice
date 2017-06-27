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
import javax.persistence.Lob;
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
@Table(name = "analisis_sensorial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AnalisisSensorial.findAll", query = "SELECT a FROM AnalisisSensorial a"),
    @NamedQuery(name = "AnalisisSensorial.findByAnalisisSensorialId", query = "SELECT a FROM AnalisisSensorial a WHERE a.analisisSensorialId = :analisisSensorialId"),
    @NamedQuery(name = "AnalisisSensorial.findByResultado", query = "SELECT a FROM AnalisisSensorial a WHERE a.resultado = :resultado"),
    @NamedQuery(name = "AnalisisSensorial.findByFecha", query = "SELECT a FROM AnalisisSensorial a WHERE a.fecha = :fecha"),
    @NamedQuery(name = "AnalisisSensorial.findByTipoAnalisis", query = "SELECT a FROM AnalisisSensorial a WHERE a.tipoAnalisis = :tipoAnalisis"),
    @NamedQuery(name = "AnalisisSensorial.findByFileAnalisissensorialName", query = "SELECT a FROM AnalisisSensorial a WHERE a.fileAnalisissensorialName = :fileAnalisissensorialName")})
public class AnalisisSensorial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ANALISIS_SENSORIAL_ID")
    private Integer analisisSensorialId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RESULTADO")
    private int resultado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "TIPO_ANALISIS")
    private String tipoAnalisis;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "FILE_ANALISISSENSORIAL_NAME")
    private String fileAnalisissensorialName;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "FILE_ANALISISSENSORIAL")
    private byte[] fileAnalisissensorial;

    public AnalisisSensorial() {
    }

    public AnalisisSensorial(Integer analisisSensorialId) {
        this.analisisSensorialId = analisisSensorialId;
    }

    public AnalisisSensorial(Integer analisisSensorialId, int resultado, Date fecha, String tipoAnalisis, String fileAnalisissensorialName, byte[] fileAnalisissensorial) {
        this.analisisSensorialId = analisisSensorialId;
        this.resultado = resultado;
        this.fecha = fecha;
        this.tipoAnalisis = tipoAnalisis;
        this.fileAnalisissensorialName = fileAnalisissensorialName;
        this.fileAnalisissensorial = fileAnalisissensorial;
    }

    public Integer getAnalisisSensorialId() {
        return analisisSensorialId;
    }

    public void setAnalisisSensorialId(Integer analisisSensorialId) {
        this.analisisSensorialId = analisisSensorialId;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipoAnalisis() {
        return tipoAnalisis;
    }

    public void setTipoAnalisis(String tipoAnalisis) {
        this.tipoAnalisis = tipoAnalisis;
    }

    public String getFileAnalisissensorialName() {
        return fileAnalisissensorialName;
    }

    public void setFileAnalisissensorialName(String fileAnalisissensorialName) {
        this.fileAnalisissensorialName = fileAnalisissensorialName;
    }

    public byte[] getFileAnalisissensorial() {
        return fileAnalisissensorial;
    }

    public void setFileAnalisissensorial(byte[] fileAnalisissensorial) {
        this.fileAnalisissensorial = fileAnalisissensorial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (analisisSensorialId != null ? analisisSensorialId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AnalisisSensorial)) {
            return false;
        }
        AnalisisSensorial other = (AnalisisSensorial) object;
        if ((this.analisisSensorialId == null && other.analisisSensorialId != null) || (this.analisisSensorialId != null && !this.analisisSensorialId.equals(other.analisisSensorialId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.libertad6.jpa.AnalisisSensorial[ analisisSensorialId=" + analisisSensorialId + " ]";
    }
    
}
