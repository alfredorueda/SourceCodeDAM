package com.example.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jhipster on 15/10/15.
 */
@Entity
public class Temporada {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    protected Long id;

    @Column
    protected Integer year;

    @ManyToMany
    private Set<Equipo> equipo = new HashSet<>();

    @ManyToOne
    private Liga liga;

    public Temporada(){

    }

    public Temporada(Integer year){
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Set<Equipo> getEquipo() {
        return equipo;
    }

    public void setEquipo(Set<Equipo> equipo) {
        this.equipo = equipo;
    }

    public Liga getLiga() {
        return liga;
    }

    public void setLiga(Liga liga) {
        this.liga = liga;
    }

    @Override
    public String toString() {
        return "Temporada{" +
                "id=" + id +
                ", year=" + year +
                '}';
    }
}
