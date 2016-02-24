package com.xavipandis.liga.repository;

import com.xavipandis.liga.domain.Temporada;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Temporada entity.
 */
public interface TemporadaRepository extends JpaRepository<Temporada,Long> {

    @Query("select distinct temporada from Temporada temporada left join fetch temporada.equipos")
    List<Temporada> findAllWithEagerRelationships();

    @Query("select temporada from Temporada temporada left join fetch temporada.equipos where temporada.id =:id")
    Temporada findOneWithEagerRelationships(@Param("id") Long id);

}
