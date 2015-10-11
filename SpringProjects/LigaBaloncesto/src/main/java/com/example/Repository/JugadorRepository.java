package com.example.Repository;

import com.example.Model.Jugador;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by jhipster on 8/10/15.
 */
public interface JugadorRepository extends PagingAndSortingRepository<Jugador, Long> {
    public List<Jugador> findByNombreContaining(String nombre);
    public List<Jugador> findByCanastasTotalesGreaterThanEqual(Long canastasTotales);
    public List<Jugador> findByAsistenciasTotalesBetween(Long minCanastas, Long maxCanastas);
    public List<Jugador> findByPosicionCampo(String posicionCampo);
    public List<Jugador> findByFechaNacimientoBefore(Date fechaNacimiento);
    public List<Jugador> findByCanastasTotalesGreaterThanEqualAndFechaNacimientoBefore(Long canastasTotales, Date fechaNacimiento);
}
