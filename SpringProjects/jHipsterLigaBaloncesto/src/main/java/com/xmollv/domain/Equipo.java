package com.xmollv.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.LocalDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Equipo.
 */
@Entity
@Table(name = "equipo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Equipo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "localidad")
    private String localidad;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @OneToOne(mappedBy = "captain")
    @JsonIgnore
    private Jugador jugador;

    @OneToMany(mappedBy = "equipoRelacion")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Jugador> jugadorRelacions = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "equipo_socio",
               joinColumns = @JoinColumn(name="equipos_id", referencedColumnName="ID"),
               inverseJoinColumns = @JoinColumn(name="socios_id", referencedColumnName="ID"))
    private Set<Socio> socios = new HashSet<>();

    @OneToOne    private Entrenador entrenador;

    @OneToOne    private Estadio estadio;

    @ManyToMany(mappedBy = "equipos")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Temporada> temporadas = new HashSet<>();

    @OneToMany(mappedBy = "equipoLocal")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Partido> partidoLocals = new HashSet<>();

    @OneToMany(mappedBy = "equipoVisitante")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Partido> partidoVisitantes = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Set<Jugador> getJugadorRelacions() {
        return jugadorRelacions;
    }

    public void setJugadorRelacions(Set<Jugador> jugadors) {
        this.jugadorRelacions = jugadors;
    }

    public Set<Socio> getSocios() {
        return socios;
    }

    public void setSocios(Set<Socio> socios) {
        this.socios = socios;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public Estadio getEstadio() {
        return estadio;
    }

    public void setEstadio(Estadio estadio) {
        this.estadio = estadio;
    }

    public Set<Temporada> getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(Set<Temporada> temporadas) {
        this.temporadas = temporadas;
    }

    public Set<Partido> getPartidoLocals() {
        return partidoLocals;
    }

    public void setPartidoLocals(Set<Partido> partidos) {
        this.partidoLocals = partidos;
    }

    public Set<Partido> getPartidoVisitantes() {
        return partidoVisitantes;
    }

    public void setPartidoVisitantes(Set<Partido> partidos) {
        this.partidoVisitantes = partidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Equipo equipo = (Equipo) o;
        return Objects.equals(id, equipo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Equipo{" +
            "id=" + id +
            ", nombre='" + nombre + "'" +
            ", localidad='" + localidad + "'" +
            ", fechaCreacion='" + fechaCreacion + "'" +
            '}';
    }
}
