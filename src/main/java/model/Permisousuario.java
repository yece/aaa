/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author estebanch
 */
@Entity
@Table(name = "permisousuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permisousuario.findAll", query = "SELECT p FROM Permisousuario p"),
    @NamedQuery(name = "Permisousuario.findById", query = "SELECT p FROM Permisousuario p WHERE p.id = :id"),
    @NamedQuery(name = "Permisousuario.findByPermiso", query = "SELECT p FROM Permisousuario p WHERE p.permiso = :permiso"),
    @NamedQuery(name = "Permisousuario.findByTipo", query = "SELECT p FROM Permisousuario p WHERE p.tipo = :tipo")})
public class Permisousuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "permiso")
    private String permiso;
    @Size(max = 45)
    @Column(name = "tipo")
    private String tipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "permisousuarioId")
    private Collection<Clientepermisousuario> clientepermisousuarioCollection;

    public Permisousuario() {
    }

    public Permisousuario(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermiso() {
        return permiso;
    }

    public void setPermiso(String permiso) {
        this.permiso = permiso;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @XmlTransient
    public Collection<Clientepermisousuario> getClientepermisousuarioCollection() {
        return clientepermisousuarioCollection;
    }

    public void setClientepermisousuarioCollection(Collection<Clientepermisousuario> clientepermisousuarioCollection) {
        this.clientepermisousuarioCollection = clientepermisousuarioCollection;
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
        if (!(object instanceof Permisousuario)) {
            return false;
        }
        Permisousuario other = (Permisousuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Permisousuario[ id=" + id + " ]";
    }
    
}
