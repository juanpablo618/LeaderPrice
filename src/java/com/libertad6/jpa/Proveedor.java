/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libertad6.jpa;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author juan.cuello
 */
@Entity
@Table(name = "proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p"),
    @NamedQuery(name = "Proveedor.findByProveedorId", query = "SELECT p FROM Proveedor p WHERE p.proveedorId = :proveedorId"),
    @NamedQuery(name = "Proveedor.findByRazonSocial", query = "SELECT p FROM Proveedor p WHERE p.razonSocial = :razonSocial"),
    @NamedQuery(name = "Proveedor.findByCuit", query = "SELECT p FROM Proveedor p WHERE p.cuit = :cuit"),
    @NamedQuery(name = "Proveedor.findByNombreContacto", query = "SELECT p FROM Proveedor p WHERE p.nombreContacto = :nombreContacto"),
    @NamedQuery(name = "Proveedor.findByAreaContacto", query = "SELECT p FROM Proveedor p WHERE p.areaContacto = :areaContacto"),
    @NamedQuery(name = "Proveedor.findByEmail", query = "SELECT p FROM Proveedor p WHERE p.email = :email"),
    @NamedQuery(name = "Proveedor.findByTelefono", query = "SELECT p FROM Proveedor p WHERE p.telefono = :telefono"),
    @NamedQuery(name = "Proveedor.findByDireccion", query = "SELECT p FROM Proveedor p WHERE p.direccion = :direccion")})
public class Proveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PROVEEDOR_ID")
    private Integer proveedorId;
    @Basic(optional = false)
    @NotNull(message="Campo Obligatorio")
    @Size(min = 1, max = 50)
    @Column(name = "RAZON_SOCIAL")
    private String razonSocial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CUIT")
    @Size(min=11,max=11, message="El Campo debe contener 11 dígitos")
    private String cuit;
    @Basic(optional = false)
    @NotNull(message="Campo Obligatorio")
    @Column(name = "NOMBRE_CONTACTO")
    private String nombreContacto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AREA_CONTACTO")
    private String areaContacto;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Email(message="Ingrese un e-mail valido")
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @NotNull(message="Solo valores numéricos y 4 digitos minimos")
    @Min(4)
    @Size(min = 4, max = 50, message="El Campo debe contener al menos 4 dígitos")
    @Column(name = "TELEFONO")
    private String telefono;
    @Basic(optional = false)
    @NotNull(message="Campo Direccion es obligatorio")
    @Size(min = 1, max = 50)
    @Column(name = "DIRECCION")
    private String direccion;
    @OneToMany(mappedBy = "proveedorId")
    private Collection<AcuerdoComercial> acuerdoComercialCollection;
    @OneToMany(mappedBy = "proveedorId")
    private Collection<Desarrollo> desarrolloCollection;

    public Proveedor() {
    }

    public Proveedor(Integer proveedorId) {
        this.proveedorId = proveedorId;
    }

    public Proveedor(Integer proveedorId, String razonSocial, String cuit, String nombreContacto, String areaContacto, String email, String telefono, String direccion) {
        this.proveedorId = proveedorId;
        this.razonSocial = razonSocial;
        this.cuit = cuit;
        this.nombreContacto = nombreContacto;
        this.areaContacto = areaContacto;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public Integer getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Integer proveedorId) {
        this.proveedorId = proveedorId;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getAreaContacto() {
        return areaContacto;
    }

    public void setAreaContacto(String areaContacto) {
        this.areaContacto = areaContacto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @XmlTransient
    public Collection<AcuerdoComercial> getAcuerdoComercialCollection() {
        return acuerdoComercialCollection;
    }

    public void setAcuerdoComercialCollection(Collection<AcuerdoComercial> acuerdoComercialCollection) {
        this.acuerdoComercialCollection = acuerdoComercialCollection;
    }

    @XmlTransient
    public Collection<Desarrollo> getDesarrolloCollection() {
        return desarrolloCollection;
    }

    public void setDesarrolloCollection(Collection<Desarrollo> desarrolloCollection) {
        this.desarrolloCollection = desarrolloCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proveedorId != null ? proveedorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.proveedorId == null && other.proveedorId != null) || (this.proveedorId != null && !this.proveedorId.equals(other.proveedorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return " " + razonSocial + " ";
    }
    
}
