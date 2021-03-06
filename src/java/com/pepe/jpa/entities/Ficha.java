/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pepe.jpa.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Windows 8
 */
@Entity
@Table(name = "ficha")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ficha.findAll", query = "SELECT f FROM Ficha f"),
    @NamedQuery(name = "Ficha.findByIdFicha", query = "SELECT f FROM Ficha f WHERE f.idFicha = :idFicha"),
    @NamedQuery(name = "Ficha.findByCodigoFicha", query = "SELECT f FROM Ficha f WHERE f.codigoFicha = :codigoFicha"),
    @NamedQuery(name = "Ficha.findByFechaInicio", query = "SELECT f FROM Ficha f WHERE f.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Ficha.findByTrimestresLectiva", query = "SELECT f FROM Ficha f WHERE f.trimestresLectiva = :trimestresLectiva"),
    @NamedQuery(name = "Ficha.findByEstado", query = "SELECT f FROM Ficha f WHERE f.estado = :estado"),
    @NamedQuery(name = "Ficha.findByIdPrograma", query = "SELECT f FROM Ficha f WHERE f.idPrograma = :idPrograma")})
public class Ficha implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ficha")
    private Integer idFicha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "codigo_ficha")
    private String codigoFicha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "trimestres_lectiva")
    private short trimestresLectiva;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_programa")
    private int idPrograma;
    @JoinTable(name = "proyecto_has_ficha", joinColumns = {
        @JoinColumn(name = "id_ficha", referencedColumnName = "id_ficha")}, inverseJoinColumns = {
        @JoinColumn(name = "id_proyecto", referencedColumnName = "id_proyecto")})
    @ManyToMany
    private List<Proyecto> proyectoList;
    @JoinTable(name = "trimestre_has_ficha", joinColumns = {
        @JoinColumn(name = "id_fecha", referencedColumnName = "id_ficha")}, inverseJoinColumns = {
        @JoinColumn(name = "id_trimestre", referencedColumnName = "id_trimestre")})
    @ManyToMany
    private List<Trimestre> trimestreList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFicha")
    private List<Evento> eventoList;
    @JoinColumn(name = "id_centro_formacion", referencedColumnName = "id_centro_formacion")
    @ManyToOne(optional = false)
    private CentroFormacion idCentroFormacion;
    @JoinColumn(name = "id_tipo_oferta", referencedColumnName = "id_tipo_oferta")
    @ManyToOne(optional = false)
    private TipoOferta idTipoOferta;
    @JoinColumn(name = "id_jornada", referencedColumnName = "id_jornada")
    @ManyToOne(optional = false)
    private Jornada idJornada;
    @JoinColumns({
        @JoinColumn(name = "Programa_Codigo", referencedColumnName = "codigo"),
        @JoinColumn(name = "programa_version", referencedColumnName = "version")})
    @ManyToOne(optional = false)
    private Programa programa;
    @JoinColumn(name = "id_tipo_formacion", referencedColumnName = "id_tipo_formacion")
    @ManyToOne(optional = false)
    private TipoFormacion idTipoFormacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ficha")
    private List<UsuarioHasFicha> usuarioHasFichaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFicha")
    private List<GuiaAprendizaje> guiaAprendizajeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFicha")
    private List<Acompañamiento> acompañamientoList;

    public Ficha() {
    }

    public Ficha(Integer idFicha) {
        this.idFicha = idFicha;
    }

    public Ficha(Integer idFicha, String codigoFicha, Date fechaInicio, short trimestresLectiva, String estado, int idPrograma) {
        this.idFicha = idFicha;
        this.codigoFicha = codigoFicha;
        this.fechaInicio = fechaInicio;
        this.trimestresLectiva = trimestresLectiva;
        this.estado = estado;
        this.idPrograma = idPrograma;
    }

    public Integer getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(Integer idFicha) {
        this.idFicha = idFicha;
    }

    public String getCodigoFicha() {
        return codigoFicha;
    }

    public void setCodigoFicha(String codigoFicha) {
        this.codigoFicha = codigoFicha;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public short getTrimestresLectiva() {
        return trimestresLectiva;
    }

    public void setTrimestresLectiva(short trimestresLectiva) {
        this.trimestresLectiva = trimestresLectiva;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(int idPrograma) {
        this.idPrograma = idPrograma;
    }

    @XmlTransient
    public List<Proyecto> getProyectoList() {
        return proyectoList;
    }

    public void setProyectoList(List<Proyecto> proyectoList) {
        this.proyectoList = proyectoList;
    }

    @XmlTransient
    public List<Trimestre> getTrimestreList() {
        return trimestreList;
    }

    public void setTrimestreList(List<Trimestre> trimestreList) {
        this.trimestreList = trimestreList;
    }

    @XmlTransient
    public List<Evento> getEventoList() {
        return eventoList;
    }

    public void setEventoList(List<Evento> eventoList) {
        this.eventoList = eventoList;
    }

    public CentroFormacion getIdCentroFormacion() {
        return idCentroFormacion;
    }

    public void setIdCentroFormacion(CentroFormacion idCentroFormacion) {
        this.idCentroFormacion = idCentroFormacion;
    }

    public TipoOferta getIdTipoOferta() {
        return idTipoOferta;
    }

    public void setIdTipoOferta(TipoOferta idTipoOferta) {
        this.idTipoOferta = idTipoOferta;
    }

    public Jornada getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(Jornada idJornada) {
        this.idJornada = idJornada;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public TipoFormacion getIdTipoFormacion() {
        return idTipoFormacion;
    }

    public void setIdTipoFormacion(TipoFormacion idTipoFormacion) {
        this.idTipoFormacion = idTipoFormacion;
    }

    @XmlTransient
    public List<UsuarioHasFicha> getUsuarioHasFichaList() {
        return usuarioHasFichaList;
    }

    public void setUsuarioHasFichaList(List<UsuarioHasFicha> usuarioHasFichaList) {
        this.usuarioHasFichaList = usuarioHasFichaList;
    }

    @XmlTransient
    public List<GuiaAprendizaje> getGuiaAprendizajeList() {
        return guiaAprendizajeList;
    }

    public void setGuiaAprendizajeList(List<GuiaAprendizaje> guiaAprendizajeList) {
        this.guiaAprendizajeList = guiaAprendizajeList;
    }

    @XmlTransient
    public List<Acompañamiento> getAcompañamientoList() {
        return acompañamientoList;
    }

    public void setAcompañamientoList(List<Acompañamiento> acompañamientoList) {
        this.acompañamientoList = acompañamientoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFicha != null ? idFicha.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ficha)) {
            return false;
        }
        Ficha other = (Ficha) object;
        if ((this.idFicha == null && other.idFicha != null) || (this.idFicha != null && !this.idFicha.equals(other.idFicha))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pepe.jpa.entities.Ficha[ idFicha=" + idFicha + " ]";
    }
    
}
