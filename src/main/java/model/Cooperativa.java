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
import javax.persistence.Lob;
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
@Table(name = "cooperativa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cooperativa.findAll", query = "SELECT c FROM Cooperativa c"),
    @NamedQuery(name = "Cooperativa.findById", query = "SELECT c FROM Cooperativa c WHERE c.id = :id"),
    @NamedQuery(name = "Cooperativa.findByNombreComercial", query = "SELECT c FROM Cooperativa c WHERE c.nombreComercial = :nombreComercial"),
    @NamedQuery(name = "Cooperativa.findByRazonSocial", query = "SELECT c FROM Cooperativa c WHERE c.razonSocial = :razonSocial"),
    @NamedQuery(name = "Cooperativa.findByTelefonos", query = "SELECT c FROM Cooperativa c WHERE c.telefonos = :telefonos"),
    @NamedQuery(name = "Cooperativa.findByEmail", query = "SELECT c FROM Cooperativa c WHERE c.email = :email")})
public class Cooperativa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre_comercial")
    private String nombreComercial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "razon_social")
    private String razonSocial;
    @Size(max = 45)
    @Column(name = "telefonos")
    private String telefonos;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "email")
    private String email;
    @Lob
    @Column(name = "mision")
    private byte[] mision;
    @Lob
    @Column(name = "vision")
    private byte[] vision;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCooperativa")
    private Collection<Bus> busCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cooperativaId")
    private Collection<Boleto> boletoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCooperativa")
    private Collection<Cliente> clienteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cooperativaId")
    private Collection<Ruta> rutaCollection;

    public Cooperativa() {
    }

    public Cooperativa(Integer id) {
        this.id = id;
    }

    public Cooperativa(Integer id, String nombreComercial, String razonSocial) {
        this.id = id;
        this.nombreComercial = nombreComercial;
        this.razonSocial = razonSocial;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getMision() {
        return mision;
    }

    public void setMision(byte[] mision) {
        this.mision = mision;
    }

    public byte[] getVision() {
        return vision;
    }

    public void setVision(byte[] vision) {
        this.vision = vision;
    }

    @XmlTransient
    public Collection<Bus> getBusCollection() {
        return busCollection;
    }

    public void setBusCollection(Collection<Bus> busCollection) {
        this.busCollection = busCollection;
    }

    @XmlTransient
    public Collection<Boleto> getBoletoCollection() {
        return boletoCollection;
    }

    public void setBoletoCollection(Collection<Boleto> boletoCollection) {
        this.boletoCollection = boletoCollection;
    }

    @XmlTransient
    public Collection<Cliente> getClienteCollection() {
        return clienteCollection;
    }

    public void setClienteCollection(Collection<Cliente> clienteCollection) {
        this.clienteCollection = clienteCollection;
    }

    @XmlTransient
    public Collection<Ruta> getRutaCollection() {
        return rutaCollection;
    }

    public void setRutaCollection(Collection<Ruta> rutaCollection) {
        this.rutaCollection = rutaCollection;
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
        if (!(object instanceof Cooperativa)) {
            return false;
        }
        Cooperativa other = (Cooperativa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Cooperativa[ id=" + id + " ]";
    }
    
}
