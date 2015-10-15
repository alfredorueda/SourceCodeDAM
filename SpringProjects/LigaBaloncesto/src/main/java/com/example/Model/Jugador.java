package com.example.Model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by jhipster on 8/10/15.
 */
@Entity
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    protected Long id;

    @Column
    protected String nombre;

    @Column
    protected Date fechaNacimiento;

    @Column
    @Min(0)
    protected Long canastasTotales;

    @Column
    @Min(0)
    protected Long asistenciasTotales;

    @Column
    @Min(0)
    protected Long rebotesTotales;

    @Column
    @NotNull
    protected String posicionCampo;

    //Afegit practica 2
    @ManyToOne
    private Equipo equipo;

    public Jugador(){
    }

    public Jugador(String nombre, Date fechaNacimiento, Long canastasTotales,
                   Long asistenciasTotales, Long rebotesTotales, String posicionCampo){
        this.nombre             = nombre;
        this.fechaNacimiento    = fechaNacimiento;
        this.canastasTotales    = canastasTotales;
        this.asistenciasTotales = asistenciasTotales;
        this.rebotesTotales     = rebotesTotales;
        this.posicionCampo      = posicionCampo;
    }

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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Long getCanastasTotales() {
        return canastasTotales;
    }

    public void setCanastasTotales(Long canastasTotales) {
        this.canastasTotales = canastasTotales;
    }

    public Long getAsistenciasTotales() {
        return asistenciasTotales;
    }

    public void setAsistenciasTotales(Long asistenciasTotales) {
        this.asistenciasTotales = asistenciasTotales;
    }

    public Long getRebotesTotales() {
        return rebotesTotales;
    }

    public void setRebotesTotales(Long rebotesTotales) {
        this.rebotesTotales = rebotesTotales;
    }

    public String getPosicionCampo() {
        return posicionCampo;
    }

    public void setPosicionCampo(String posicionCampo) {
        this.posicionCampo = posicionCampo;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", canastasTotales=" + canastasTotales +
                ", asistenciasTotales=" + asistenciasTotales +
                ", rebotesTotales=" + rebotesTotales +
                ", posicionCampo='" + posicionCampo + '\'' +
                ", equipo=" + equipo +
                '}';
    }


}
