package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Player;

import org.springframework.data.jpa.repository.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Spring Data JPA repository for the Player entity.
 */
public interface PlayerRepository extends JpaRepository<Player,Long> {
    List<Player> findByNameContaining(String name);
    List<Player> findAllByBasketsGreaterThanEqualOrderByBasketsDesc(Integer canastasTotales);
    List<Player> findAllByFechaNacimientoBetween(LocalDate startDate, LocalDate endDate);
}
