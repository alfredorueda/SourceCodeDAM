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

        Equipo equipo1 = new Equipo();
        equipo1.setNombre("Equip1");
        equipo1.setFechaCreacion(calendar1.getTime());
        equipo1.setLocalidad("Barcelona");
        equipoRepository.save(equipo1);

        Calendar calendar2 = GregorianCalendar.getInstance();
        calendar2.set(2000, Calendar.JANUARY, 03);

        Equipo equipo2 = new Equipo();
        equipo2.setNombre("Equip2");
        equipo2.setFechaCreacion(calendar2.getTime());
        equipo2.setLocalidad("Madrid");
        equipoRepository.save(equipo2);

        Calendar calendar3 = GregorianCalendar.getInstance();
        calendar3.set(2001, Calendar.JANUARY, 03);

        Equipo equipo3 = new Equipo();
        equipo3.setNombre("Equip3");
        equipo3.setFechaCreacion(calendar3.getTime());
        equipo3.setLocalidad("Valencia");
        equipoRepository.save(equipo3);

        Calendar calendar4 = GregorianCalendar.getInstance();
        calendar4.set(2002, Calendar.JANUARY, 03);

        Equipo equipo4 = new Equipo();
        equipo4.setNombre("Equip4");
        equipo4.setFechaCreacion(calendar4.getTime());
        equipo4.setLocalidad("Sevilla");
        equipoRepository.save(equipo4);

        Calendar calendar5 = GregorianCalendar.getInstance();
        calendar5.set(2003, Calendar.JANUARY, 03);

        Equipo equipo5 = new Equipo();
        equipo5.setNombre("Equip5");
        equipo5.setFechaCreacion(calendar5.getTime());
        equipo5.setLocalidad("Barcelona");
        equipoRepository.save(equipo5);

    }
}
