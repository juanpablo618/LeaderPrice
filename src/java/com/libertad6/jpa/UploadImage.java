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
@Table(name = "upload_image")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UploadImage.findAll", query = "SELECT u FROM UploadImage u"),
    @NamedQuery(name = "UploadImage.findByImageId", query = "SELECT u FROM UploadImage u WHERE u.imageId = :imageId"),
    @NamedQuery(name = "UploadImage.findByImageName", query = "SELECT u FROM UploadImage u WHERE u.imageName = :imageName"),
    @NamedQuery(name = "UploadImage.findByNombre", query = "SELECT u FROM UploadImage u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "UploadImage.findByResultado", query = "SELECT u FROM UploadImage u WHERE u.resultado = :resultado"),
    @NamedQuery(name = "UploadImage.findByFecha", query = "SELECT u FROM UploadImage u WHERE u.fecha = :fecha"),
    @NamedQuery(name = "UploadImage.findByDesarrolloId", query = "SELECT u FROM UploadImage u WHERE u.desarrolloId = :desarrolloId")})
public class UploadImage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "image_id")
    private Integer imageId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "image_name")
    private String imageName;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "image")
    private byte[] image;
    @Size(max = 60)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "resultado")
    private String resultado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "DESARROLLO_ID")
    private Integer desarrolloId;

    public UploadImage() {
    }

    public UploadImage(Integer imageId) {
        this.imageId = imageId;
    }

    public UploadImage(Integer imageId, String imageName, byte[] image, String resultado, Date fecha) {
        this.imageId = imageId;
        this.imageName = imageName;
        this.image = image;
        this.resultado = resultado;
        this.fecha = fecha;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
        hash += (imageId != null ? imageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UploadImage)) {
            return false;
        }
        UploadImage other = (UploadImage) object;
        if ((this.imageId == null && other.imageId != null) || (this.imageId != null && !this.imageId.equals(other.imageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.libertad6.jpa.UploadImage[ imageId=" + imageId + " ]";
    }
    
}
