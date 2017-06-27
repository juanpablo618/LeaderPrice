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
@Table(name = "control_producto_2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ControlProducto2.findAll", query = "SELECT c FROM ControlProducto2 c"),
    @NamedQuery(name = "ControlProducto2.findByControlproducto2ID", query = "SELECT c FROM ControlProducto2 c WHERE c.controlproducto2ID = :controlproducto2ID"),
    @NamedQuery(name = "ControlProducto2.findByAspecto", query = "SELECT c FROM ControlProducto2 c WHERE c.aspecto = :aspecto"),
    @NamedQuery(name = "ControlProducto2.findByProductoId", query = "SELECT c FROM ControlProducto2 c WHERE c.productoId = :productoId"),
    @NamedQuery(name = "ControlProducto2.findByUnidadDeMedida", query = "SELECT c FROM ControlProducto2 c WHERE c.unidadDeMedida = :unidadDeMedida"),
    @NamedQuery(name = "ControlProducto2.findByValorDeReferencia", query = "SELECT c FROM ControlProducto2 c WHERE c.valorDeReferencia = :valorDeReferencia"),
    @NamedQuery(name = "ControlProducto2.findByYear", query = "SELECT c FROM ControlProducto2 c WHERE c.year = :year"),
    @NamedQuery(name = "ControlProducto2.findByValorYear", query = "SELECT c FROM ControlProducto2 c WHERE c.valorYear = :valorYear"),
    @NamedQuery(name = "ControlProducto2.findByDesarrolloId", query = "SELECT c FROM ControlProducto2 c WHERE c.desarrolloId = :desarrolloId")})
public class ControlProducto2 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "control_producto_2_ID")
    private Integer controlproducto2ID;
    @Size(max = 50)
    @Column(name = "ASPECTO")
    private String aspecto;
    
    @Column(name = "PRODUCTO_ID")
    private String productoId;
    
    @Size(max = 50)
    @Column(name = "UNIDAD_DE_MEDIDA")
    private String unidadDeMedida;

    @Column(name = "VALOR_DE_REFERENCIA")
    private String valorDeReferencia;
    
    @Column(name = "YEAR")
    private Integer year;
    @Column(name = "VALOR_YEAR")
    private Integer valorYear;
    @Column(name = "DESARROLLO_ID")
    private Integer desarrolloId;

    public ControlProducto2() {
    }

    public ControlProducto2(Integer controlproducto2ID) {
        this.controlproducto2ID = controlproducto2ID;
    }

    public Integer getControlproducto2ID() {
        return controlproducto2ID;
    }

    public void setControlproducto2ID(Integer controlproducto2ID) {
        this.controlproducto2ID = controlproducto2ID;
    }

    public String getAspecto() {
        return aspecto;
    }

    public void setAspecto(String aspecto) {
        this.aspecto = aspecto;
    }

    public String getProductoId() {
        return productoId;
    }

    public void setProductoId(String productoId) {
        this.productoId = productoId;
    }

    public String getUnidadDeMedida() {
        return unidadDeMedida;
    }

    public void setUnidadDeMedida(String unidadDeMedida) {
        this.unidadDeMedida = unidadDeMedida;
    }

    public String getValorDeReferencia() {
        return valorDeReferencia;
    }

    public void setValorDeReferencia(String valorDeReferencia) {
        this.valorDeReferencia = valorDeReferencia;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getValorYear() {
        return valorYear;
    }

    public void setValorYear(Integer valorYear) {
        this.valorYear = valorYear;
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
        hash += (controlproducto2ID != null ? controlproducto2ID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ControlProducto2)) {
            return false;
        }
        ControlProducto2 other = (ControlProducto2) object;
        if ((this.controlproducto2ID == null && other.controlproducto2ID != null) || (this.controlproducto2ID != null && !this.controlproducto2ID.equals(other.controlproducto2ID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.libertad6.jpa.ControlProducto2[ controlproducto2ID=" + controlproducto2ID + " ]";
    }
    
}
