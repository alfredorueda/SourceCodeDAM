package com.example.Repository;

import com.example.Model.Jugador;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by jhipster on 8/10/15.
 */
public interface JugadorRepository extends PagingAndSortingRepository<Jugador, Long> {
    List<Jugador> findByNombreContaining(String nombre);
    List<Jugador> findByCanastasTotalesGreaterThanEqual(Long canastasTotales);
    List<Jugador> findByAsistenciasTotalesBetween(Long minCanastas, Long maxCanastas);
    List<Jugador> findByPosicionCampo(String posicionCampo);
    List<Jugador> findByFechaNacimientoBefore(Date fechaNacimiento);
    List<Jugador> findByCanastasTotalesGreaterThanEqualAndFechaNacimientoBefore(Long canastasTotales, Date fechaNacimiento);


    List<Jugador> findByEquipoNombre(String equipoNombre);
    List<Jugador> findByEquipoNombreAndPosicionCampo(String equipoNombre, String posicionCampo);
    Jugador findFirstByOrderByCanastasTotalesDesc();
    List<Jugador> findFirst5ByOrderByAsistenciasTotalesDesc();

    @Query("SELECT j FROM Jugador j WHERE j.equipo.nombre = :equipoNombre ORDER BY j.canastasTotales DESC")
    List<Jugador> findByEquipoNombreCanastasTotalesDesc(@Param("equipoNombre") String equipoNombre);

}
