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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author estebanch
 */
@Entity
@Table(name = "bus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bus.findAll", query = "SELECT b FROM Bus b"),
    @NamedQuery(name = "Bus.findById", query = "SELECT b FROM Bus b WHERE b.id = :id"),
    @NamedQuery(name = "Bus.findByAsientos", query = "SELECT b FROM Bus b WHERE b.asientos = :asientos"),
    @NamedQuery(name = "Bus.findByNumero", query = "SELECT b FROM Bus b WHERE b.numero = :numero"),
    @NamedQuery(name = "Bus.findByNombresDuenio", query = "SELECT b FROM Bus b WHERE b.nombresDuenio = :nombresDuenio"),
    @NamedQuery(name = "Bus.findByPlaca", query = "SELECT b FROM Bus b WHERE b.placa = :placa")})
public class Bus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "asientos")
    private Integer asientos;
    @Size(max = 45)
    @Column(name = "numero")
    private String numero;
    @Size(max = 45)
    @Column(name = "nombres_duenio")
    private String nombresDuenio;
    @Size(max = 10)
    @Column(name = "placa")
    private String placa;
    @JoinColumn(name = "id_cooperativa", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cooperativa idCooperativa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "busId")
    private Collection<Boleto> boletoCollection;

    public Bus() {
    }

    public Bus(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAsientos() {
        return asientos;
    }

    public void setAsientos(Integer asientos) {
        this.asientos = asientos;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNombresDuenio() {
        return nombresDuenio;
    }

    public void setNombresDuenio(String nombresDuenio) {
        this.nombresDuenio = nombresDuenio;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Cooperativa getIdCooperativa() {
        return idCooperativa;
    }

    public void setIdCooperativa(Cooperativa idCooperativa) {
        this.idCooperativa = idCooperativa;
    }

    @XmlTransient
    public Collection<Boleto> getBoletoCollection() {
        return boletoCollection;
    }

    public void setBoletoCollection(Collection<Boleto> boletoCollection) {
        this.boletoCollection = boletoCollection;
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
        if (!(object instanceof Bus)) {
            return false;
        }
        Bus other = (Bus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Bus[ id=" + id + " ]";
    }
    
}
