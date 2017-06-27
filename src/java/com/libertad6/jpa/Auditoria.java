
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juan.cuello
 */
@Entity
@Table(name = "auditoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Auditoria.findAll", query = "SELECT a FROM Auditoria a"),
    @NamedQuery(name = "Auditoria.findByAuditoriaId", query = "SELECT a FROM Auditoria a WHERE a.auditoriaId = :auditoriaId"),
    @NamedQuery(name = "Auditoria.findByFecha", query = "SELECT a FROM Auditoria a WHERE a.fecha = :fecha"),
    @NamedQuery(name = "Auditoria.findByTipoAuditoria", query = "SELECT a FROM Auditoria a WHERE a.tipoAuditoria = :tipoAuditoria"),
    @NamedQuery(name = "Auditoria.findByResultado", query = "SELECT a FROM Auditoria a WHERE a.resultado = :resultado"),
    @NamedQuery(name = "Auditoria.findByFileInformeName", query = "SELECT a FROM Auditoria a WHERE a.fileInformeName = :fileInformeName"),
    @NamedQuery(name = "Auditoria.findByDesarrolloId", query = "SELECT a FROM Auditoria a WHERE a.desarrolloId = :desarrolloId")})
public class Auditoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "AUDITORIA_ID")
    private Integer auditoriaId;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Size(max = 45)
    @Column(name = "TIPO_AUDITORIA")
    private String tipoAuditoria;
    @Size(max = 45)
    @Column(name = "RESULTADO")
    private String resultado;
    @Size(max = 45)
    @Column(name = "FILE_INFORME_NAME")
    private String fileInformeName;
    @Lob
    @Column(name = "FILE_INFORME")
    private byte[] fileInforme;
    @Column(name = "DESARROLLO_ID")
    private Integer desarrolloId;

    public Auditoria() {
    }

    public Auditoria(Integer auditoriaId) {
        this.auditoriaId = auditoriaId;
    }

    public Integer getAuditoriaId() {
        return auditoriaId;
    }

    public void setAuditoriaId(Integer auditoriaId) {
        this.auditoriaId = auditoriaId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipoAuditoria() {
        return tipoAuditoria;
    }

    public void setTipoAuditoria(String tipoAuditoria) {
        this.tipoAuditoria = tipoAuditoria;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getFileInformeName() {
        return fileInformeName;
    }

    public void setFileInformeName(String fileInformeName) {
        this.fileInformeName = fileInformeName;
    }

    public byte[] getFileInforme() {
        return fileInforme;
    }

    public void setFileInforme(byte[] fileInforme) {
        this.fileInforme = fileInforme;
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
        hash += (auditoriaId != null ? auditoriaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Auditoria)) {
            return false;
        }
        Auditoria other = (Auditoria) object;
        if ((this.auditoriaId == null && other.auditoriaId != null) || (this.auditoriaId != null && !this.auditoriaId.equals(other.auditoriaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.libertad6.jpa.Auditoria[ auditoriaId=" + auditoriaId + " ]";
    }
    
}
