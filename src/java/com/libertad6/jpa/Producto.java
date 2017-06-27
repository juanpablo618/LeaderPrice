/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libertad6.jpa;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juan.cuello
 */
@Entity
@Table(name = "producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p"),
    @NamedQuery(name = "Producto.findByProductoId", query = "SELECT p FROM Producto p WHERE p.productoId = :productoId"),
    @NamedQuery(name = "Producto.findByNombre", query = "SELECT p FROM Producto p WHERE p.nombre = :nombre")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PRODUCTO_ID")
    private Integer productoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productoId")
    //private Collection<ControlProducto> controlProductoCollection;
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "productoId")
    private Collection<Desarrollo> desarrolloCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productoNoConforme")
    private Collection<Reclamos> reclamosCollection;

    public Producto() {
    }

    public Producto(Integer productoId) {
        this.productoId = productoId;
    }

    public Producto(Integer productoId, String nombre) {
        this.productoId = productoId;
        this.nombre = nombre;
    }

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //@XmlTransient
    //public Collection<ControlProducto> getControlProductoCollection() {
    //    return controlProductoCollection;
    //}

    //public void setControlProductoCollection(Collection<ControlProducto> controlProductoCollection) {
    //    this.controlProductoCollection = controlProductoCollection;
    //}

    @XmlTransient
    public Collection<Desarrollo> getDesarrolloCollection() {
        return desarrolloCollection;
    }

    public void setDesarrolloCollection(Collection<Desarrollo> desarrolloCollection) {
      this.desarrolloCollection = desarrolloCollection;
    }

    @XmlTransient
    public Collection<Reclamos> getReclamosCollection() {
        return reclamosCollection;
    }

    public void setReclamosCollection(Collection<Reclamos> reclamosCollection) {
        this.reclamosCollection = reclamosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productoId != null ? productoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.productoId == null && other.productoId != null) || (this.productoId != null && !this.productoId.equals(other.productoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return " " + nombre + "";
    }
    
}
