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
@Table(name = "rutadestino")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rutadestino.findAll", query = "SELECT r FROM Rutadestino r"),
    @NamedQuery(name = "Rutadestino.findById", query = "SELECT r FROM Rutadestino r WHERE r.id = :id"),
    @NamedQuery(name = "Rutadestino.findByDestino", query = "SELECT r FROM Rutadestino r WHERE r.destino = :destino"),
    @NamedQuery(name = "Rutadestino.findByCosto", query = "SELECT r FROM Rutadestino r WHERE r.costo = :costo")})
public class Rutadestino implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "destino")
    private String destino;
    @Basic(optional = false)
    @NotNull
    @Column(name = "costo")
    private double costo;
    @JoinColumn(name = "id_ruta", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Ruta idRuta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rutadestinoId")
    private Collection<Boleto> boletoCollection;

    public Rutadestino() {
    }

    public Rutadestino(Integer id) {
        this.id = id;
    }

    public Rutadestino(Integer id, String destino, double costo) {
        this.id = id;
        this.destino = destino;
        this.costo = costo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public Ruta getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(Ruta idRuta) {
        this.idRuta = idRuta;
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
        if (!(object instanceof Rutadestino)) {
            return false;
        }
        Rutadestino other = (Rutadestino) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Rutadestino[ id=" + id + " ]";
    }
    
}
