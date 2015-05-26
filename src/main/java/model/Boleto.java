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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author estebanch
 */
@Entity
@Table(name = "boleto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Boleto.findAll", query = "SELECT b FROM Boleto b"),
    @NamedQuery(name = "Boleto.findById", query = "SELECT b FROM Boleto b WHERE b.id = :id"),
    @NamedQuery(name = "Boleto.findByCosto", query = "SELECT b FROM Boleto b WHERE b.costo = :costo"),
    @NamedQuery(name = "Boleto.findByEstado", query = "SELECT b FROM Boleto b WHERE b.estado = :estado"),
    @NamedQuery(name = "Boleto.findByFechaSalida", query = "SELECT b FROM Boleto b WHERE b.fechaSalida = :fechaSalida"),
    @NamedQuery(name = "Boleto.findByHoraSalida", query = "SELECT b FROM Boleto b WHERE b.horaSalida = :horaSalida"),
    @NamedQuery(name = "Boleto.findByFecha", query = "SELECT b FROM Boleto b WHERE b.fecha = :fecha"),
    @NamedQuery(name = "Boleto.findByHora", query = "SELECT b FROM Boleto b WHERE b.hora = :hora"),
    @NamedQuery(name = "Boleto.findByAsientos", query = "SELECT b FROM Boleto b WHERE b.asientos = :asientos")})
public class Boleto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "costo")
    private Double costo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_salida")
    @Temporal(TemporalType.DATE)
    private Date fechaSalida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora_salida")
    @Temporal(TemporalType.TIME)
    private Date horaSalida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "asientos")
    private String asientos;
    @JoinColumn(name = "rutadestino_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Rutadestino rutadestinoId;
    @JoinColumn(name = "cooperativa_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cooperativa cooperativaId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cliente userId;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cliente idCliente;
    @JoinColumn(name = "bus_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Bus busId;

    public Boleto() {
    }

    public Boleto(Integer id) {
        this.id = id;
    }

    public Boleto(Integer id, int estado, Date fechaSalida, Date horaSalida, Date fecha, Date hora, String asientos) {
        this.id = id;
        this.estado = estado;
        this.fechaSalida = fechaSalida;
        this.horaSalida = horaSalida;
        this.fecha = fecha;
        this.hora = hora;
        this.asientos = asientos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Date getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Date horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getAsientos() {
        return asientos;
    }

    public void setAsientos(String asientos) {
        this.asientos = asientos;
    }

    public Rutadestino getRutadestinoId() {
        return rutadestinoId;
    }

    public void setRutadestinoId(Rutadestino rutadestinoId) {
        this.rutadestinoId = rutadestinoId;
    }

    public Cooperativa getCooperativaId() {
        return cooperativaId;
    }

    public void setCooperativaId(Cooperativa cooperativaId) {
        this.cooperativaId = cooperativaId;
    }

    public Cliente getUserId() {
        return userId;
    }

    public void setUserId(Cliente userId) {
        this.userId = userId;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Bus getBusId() {
        return busId;
    }

    public void setBusId(Bus busId) {
        this.busId = busId;
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
        if (!(object instanceof Boleto)) {
            return false;
        }
        Boleto other = (Boleto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Boleto[ id=" + id + " ]";
    }
    
}
