package com.example.Repository;

import com.example.Model.Equipo;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by jhipster on 14/10/15.
 */
public interface EquipoRepository extends PagingAndSortingRepository<Equipo, Long> {
    public List<Equipo> findByNombreContaining(String nombre);
}
