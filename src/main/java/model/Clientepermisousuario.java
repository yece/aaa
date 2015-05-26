/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author estebanch
 */
@Entity
@Table(name = "clientepermisousuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clientepermisousuario.findAll", query = "SELECT c FROM Clientepermisousuario c"),
    @NamedQuery(name = "Clientepermisousuario.findById", query = "SELECT c FROM Clientepermisousuario c WHERE c.id = :id")})
public class Clientepermisousuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "permisousuario_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Permisousuario permisousuarioId;
    @JoinColumn(name = "cliente_id_cliente", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cliente clienteIdCliente;

    public Clientepermisousuario() {
    }

    public Clientepermisousuario(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Permisousuario getPermisousuarioId() {
        return permisousuarioId;
    }

    public void setPermisousuarioId(Permisousuario permisousuarioId) {
        this.permisousuarioId = permisousuarioId;
    }

    public Cliente getClienteIdCliente() {
        return clienteIdCliente;
    }

    public void setClienteIdCliente(Cliente clienteIdCliente) {
        this.clienteIdCliente = clienteIdCliente;
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
        if (!(object instanceof Clientepermisousuario)) {
            return false;
        }
        Clientepermisousuario other = (Clientepermisousuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Clientepermisousuario[ id=" + id + " ]";
    }
    
}
