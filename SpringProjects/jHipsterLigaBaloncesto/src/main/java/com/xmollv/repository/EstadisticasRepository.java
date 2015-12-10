package com.xmollv.repository;

import com.xmollv.domain.Estadisticas;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Estadisticas entity.
 */
public interface EstadisticasRepository extends JpaRepository<Estadisticas,Long> {

}
