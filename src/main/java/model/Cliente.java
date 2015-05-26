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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author estebanch
 */
@Entity
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findById", query = "SELECT c FROM Cliente c WHERE c.id = :id"),
    @NamedQuery(name = "Cliente.findByCedula", query = "SELECT c FROM Cliente c WHERE c.cedula = :cedula"),
    @NamedQuery(name = "Cliente.findByNombres", query = "SELECT c FROM Cliente c WHERE c.nombres = :nombres"),
    @NamedQuery(name = "Cliente.findByApellidos", query = "SELECT c FROM Cliente c WHERE c.apellidos = :apellidos"),
    @NamedQuery(name = "Cliente.findByNickname", query = "SELECT c FROM Cliente c WHERE c.nickname = :nickname"),
    @NamedQuery(name = "Cliente.findByEmail", query = "SELECT c FROM Cliente c WHERE c.email = :email"),
    @NamedQuery(name = "Cliente.findByClave", query = "SELECT c FROM Cliente c WHERE c.clave = :clave")})
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 28)
    @Column(name = "cedula")
    private String cedula;
    @Size(max = 45)
    @Column(name = "nombres")
    private String nombres;
    @Size(max = 45)
    @Column(name = "apellidos")
    private String apellidos;
    @Size(max = 45)
    @Column(name = "nickname")
    private String nickname;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "clave")
    private String clave;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Boleto> boletoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCliente")
    private Collection<Boleto> boletoCollection1;
    @JoinColumn(name = "tiposusuario_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tiposusuario tiposusuarioId;
    @JoinColumn(name = "id_cooperativa", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cooperativa idCooperativa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clienteIdCliente")
    private Collection<Clientepermisousuario> clientepermisousuarioCollection;

    public Cliente() {
    }

    public Cliente(Integer id) {
        this.id = id;
    }

    public Cliente(Integer id, String email, String clave) {
        this.id = id;
        this.email = email;
        this.clave = clave;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @XmlTransient
    public Collection<Boleto> getBoletoCollection() {
        return boletoCollection;
    }

    public void setBoletoCollection(Collection<Boleto> boletoCollection) {
        this.boletoCollection = boletoCollection;
    }

    @XmlTransient
    public Collection<Boleto> getBoletoCollection1() {
        return boletoCollection1;
    }

    public void setBoletoCollection1(Collection<Boleto> boletoCollection1) {
        this.boletoCollection1 = boletoCollection1;
    }

    public Tiposusuario getTiposusuarioId() {
        return tiposusuarioId;
    }

    public void setTiposusuarioId(Tiposusuario tiposusuarioId) {
        this.tiposusuarioId = tiposusuarioId;
    }

    public Cooperativa getIdCooperativa() {
        return idCooperativa;
    }

    public void setIdCooperativa(Cooperativa idCooperativa) {
        this.idCooperativa = idCooperativa;
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
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Cliente[ id=" + id + " ]";
    }
    
}
