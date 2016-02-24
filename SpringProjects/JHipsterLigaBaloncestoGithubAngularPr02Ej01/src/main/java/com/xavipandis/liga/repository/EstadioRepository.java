package com.xavipandis.liga.repository;

import com.xavipandis.liga.domain.Estadio;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Estadio entity.
 */
public interface EstadioRepository extends JpaRepository<Estadio,Long> {

}
