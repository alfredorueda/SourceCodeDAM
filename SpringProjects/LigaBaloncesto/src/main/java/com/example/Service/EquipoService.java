package com.example.Service;

import com.example.Model.Equipo;
import com.example.Repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by jhipster on 14/10/15.
 */
@Service
@Transactional
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    public void testEquipo(){

        Calendar calendar1 = GregorianCalendar.getInstance();
        calendar1.set(1999, Calendar.JANUARY, 03);

        Equipo equipo = new Equipo();
        equipo.setNombre("Equip1");
        equipo.setFechaCreacion(calendar1.getTime());
        equipo.setLocalidad("Barcelona");
        equipoRepository.save(equipo);

    }
}
