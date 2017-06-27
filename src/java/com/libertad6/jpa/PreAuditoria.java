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
@Table(name = "pre_auditoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PreAuditoria.findAll", query = "SELECT p FROM PreAuditoria p"),
    @NamedQuery(name = "PreAuditoria.findByPreAuditoriaId", query = "SELECT p FROM PreAuditoria p WHERE p.preAuditoriaId = :preAuditoriaId"),
    @NamedQuery(name = "PreAuditoria.findByDesarrolloId", query = "SELECT p FROM PreAuditoria p WHERE p.desarrolloId = :desarrolloId"),
    @NamedQuery(name = "PreAuditoria.findByFilePreauditoriaName", query = "SELECT p FROM PreAuditoria p WHERE p.filePreauditoriaName = :filePreauditoriaName")})
public class PreAuditoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PRE_AUDITORIA_ID")
    private Integer preAuditoriaId;
    @Column(name = "DESARROLLO_ID")
    private Integer desarrolloId;
    @Size(max = 45)
    @Column(name = "FILE_PREAUDITORIA_NAME")
    private String filePreauditoriaName;
    @Lob
    @Column(name = "FILE_PREAUDITORIA")
    private byte[] filePreauditoria;

    public PreAuditoria() {
    }

    public PreAuditoria(Integer preAuditoriaId) {
        this.preAuditoriaId = preAuditoriaId;
    }

    public Integer getPreAuditoriaId() {
        return preAuditoriaId;
    }

    public void setPreAuditoriaId(Integer preAuditoriaId) {
        this.preAuditoriaId = preAuditoriaId;
    }

    public Integer getDesarrolloId() {
        return desarrolloId;
    }

    public void setDesarrolloId(Integer desarrolloId) {
        this.desarrolloId = desarrolloId;
    }

    public String getFilePreauditoriaName() {
        return filePreauditoriaName;
    }

    public void setFilePreauditoriaName(String filePreauditoriaName) {
        this.filePreauditoriaName = filePreauditoriaName;
    }

    public byte[] getFilePreauditoria() {
        return filePreauditoria;
    }

    public void setFilePreauditoria(byte[] filePreauditoria) {
        this.filePreauditoria = filePreauditoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (preAuditoriaId != null ? preAuditoriaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreAuditoria)) {
            return false;
        }
        PreAuditoria other = (PreAuditoria) object;
        if ((this.preAuditoriaId == null && other.preAuditoriaId != null) || (this.preAuditoriaId != null && !this.preAuditoriaId.equals(other.preAuditoriaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.libertad6.jpa.PreAuditoria[ preAuditoriaId=" + preAuditoriaId + " ]";
    }
    
}
