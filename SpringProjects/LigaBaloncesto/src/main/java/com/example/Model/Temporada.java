package com.example.Model;

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

    /*@ManyToMany
    private Set<Equipo> = new HashSet<>();*/

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

    @Override
    public String toString() {
        return "Temporada{" +
                "id=" + id +
                ", year=" + year +
                '}';
    }
}
