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
@Table(name = "ficha_tecnica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FichaTecnica.findAll", query = "SELECT f FROM FichaTecnica f"),
    @NamedQuery(name = "FichaTecnica.findByFichaTecnicaId", query = "SELECT f FROM FichaTecnica f WHERE f.fichaTecnicaId = :fichaTecnicaId"),
    @NamedQuery(name = "FichaTecnica.findByDesarrolloId", query = "SELECT f FROM FichaTecnica f WHERE f.desarrolloId = :desarrolloId"),
    @NamedQuery(name = "FichaTecnica.findByFileFichaproveedorName", query = "SELECT f FROM FichaTecnica f WHERE f.fileFichaproveedorName = :fileFichaproveedorName"),
    @NamedQuery(name = "FichaTecnica.findByFileFichalibertadName", query = "SELECT f FROM FichaTecnica f WHERE f.fileFichalibertadName = :fileFichalibertadName")})
public class FichaTecnica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "FICHA_TECNICA_ID")
    private Integer fichaTecnicaId;
    @Column(name = "DESARROLLO_ID")
    private Integer desarrolloId;
    @Size(max = 45)
    @Column(name = "FILE_FICHAPROVEEDOR_NAME")
    private String fileFichaproveedorName;
    @Lob
    @Column(name = "FILE_FICHAPROVEEDOR")
    private byte[] fileFichaproveedor;
    @Size(max = 45)
    @Column(name = "FILE_FICHALIBERTAD_NAME")
    private String fileFichalibertadName;
    @Lob
    @Column(name = "FILE_FICHALIBERTAD")
    private byte[] fileFichalibertad;

    public FichaTecnica() {
    }

    public FichaTecnica(Integer fichaTecnicaId) {
        this.fichaTecnicaId = fichaTecnicaId;
    }

    public Integer getFichaTecnicaId() {
        return fichaTecnicaId;
    }

    public void setFichaTecnicaId(Integer fichaTecnicaId) {
        this.fichaTecnicaId = fichaTecnicaId;
    }

    public Integer getDesarrolloId() {
        return desarrolloId;
    }

    public void setDesarrolloId(Integer desarrolloId) {
        this.desarrolloId = desarrolloId;
    }

    public String getFileFichaproveedorName() {
        return fileFichaproveedorName;
    }

    public void setFileFichaproveedorName(String fileFichaproveedorName) {
        this.fileFichaproveedorName = fileFichaproveedorName;
    }

    public byte[] getFileFichaproveedor() {
        return fileFichaproveedor;
    }

    public void setFileFichaproveedor(byte[] fileFichaproveedor) {
        this.fileFichaproveedor = fileFichaproveedor;
    }

    public String getFileFichalibertadName() {
        return fileFichalibertadName;
    }

    public void setFileFichalibertadName(String fileFichalibertadName) {
        this.fileFichalibertadName = fileFichalibertadName;
    }

    public byte[] getFileFichalibertad() {
        return fileFichalibertad;
    }

    public void setFileFichalibertad(byte[] fileFichalibertad) {
        this.fileFichalibertad = fileFichalibertad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fichaTecnicaId != null ? fichaTecnicaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FichaTecnica)) {
            return false;
        }
        FichaTecnica other = (FichaTecnica) object;
        if ((this.fichaTecnicaId == null && other.fichaTecnicaId != null) || (this.fichaTecnicaId != null && !this.fichaTecnicaId.equals(other.fichaTecnicaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.libertad6.jpa.FichaTecnica[ fichaTecnicaId=" + fichaTecnicaId + " ]";
    }
    
}
