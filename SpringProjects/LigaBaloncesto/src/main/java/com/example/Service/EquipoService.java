package com.example.Service;

import com.example.Model.Equipo;
import com.example.Repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jhipster on 14/10/15.
 */
@Service
@Transactional
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    public void testEquipo(){

        Equipo equipo = new Equipo();
        equipo.setNombre("Nombre Equipo");
        equipoRepository.save(equipo);

    }
}
