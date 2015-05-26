/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author estebanch
 */
@Entity
@Table(name = "asientovendido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asientovendido.findAll", query = "SELECT a FROM Asientovendido a"),
    @NamedQuery(name = "Asientovendido.findById", query = "SELECT a FROM Asientovendido a WHERE a.id = :id"),
    @NamedQuery(name = "Asientovendido.findByFechasalida", query = "SELECT a FROM Asientovendido a WHERE a.fechasalida = :fechasalida"),
    @NamedQuery(name = "Asientovendido.findByNroasiento", query = "SELECT a FROM Asientovendido a WHERE a.nroasiento = :nroasiento")})
public class Asientovendido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fechasalida")
    @Temporal(TemporalType.DATE)
    private Date fechasalida;
    @Column(name = "nroasiento")
    private Integer nroasiento;
    @JoinColumn(name = "ruta_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Ruta rutaId;

    public Asientovendido() {
    }

    public Asientovendido(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechasalida() {
        return fechasalida;
    }

    public void setFechasalida(Date fechasalida) {
        this.fechasalida = fechasalida;
    }

    public Integer getNroasiento() {
        return nroasiento;
    }

    public void setNroasiento(Integer nroasiento) {
        this.nroasiento = nroasiento;
    }

    public Ruta getRutaId() {
        return rutaId;
    }

    public void setRutaId(Ruta rutaId) {
        this.rutaId = rutaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asientovendido)) {
            return false;
        }
        Asientovendido other = (Asientovendido) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Asientovendido[ id=" + id + " ]";
    }
    
}
