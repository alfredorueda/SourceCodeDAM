package com.example.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jhipster on 15/10/15.
 */
@Entity
public class Liga {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    protected Long id;

    @Column
    protected String nombre;

    @JsonIgnore
    @OneToMany(mappedBy = "liga", cascade = CascadeType.ALL)
    private Set<Temporada> temporadas = new HashSet<>();

    public Liga(){

    }

    public Liga(String nombre){
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Temporada> getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(Set<Temporada> temporadas) {
        this.temporadas = temporadas;
    }

    @Override
    public String toString() {
        return "Liga{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
