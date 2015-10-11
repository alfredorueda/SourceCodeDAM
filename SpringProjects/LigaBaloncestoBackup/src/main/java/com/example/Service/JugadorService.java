package com.example.Service;

import com.example.Model.Jugador;
import com.example.Repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by jhipster on 8/10/15.
 */
@Service
@Transactional
public class JugadorService {

    @Autowired
    private JugadorRepository jugadorRepository;

    public void testJugador(){

        Calendar calendar1 = GregorianCalendar.getInstance();
        calendar1.set(1994, Calendar.SEPTEMBER, 2);

        Calendar calendar2 = GregorianCalendar.getInstance();
        calendar2.set(1993, Calendar.MARCH, 20);

        Calendar calendar3 = GregorianCalendar.getInstance();
        calendar3.set(1994, Calendar.OCTOBER, 24);

        Calendar calendar4 = GregorianCalendar.getInstance();
        calendar4.set(2005, Calendar.JANUARY, 03);

        Calendar calendar5 = GregorianCalendar.getInstance();
        calendar5.set(1999, Calendar.AUGUST, 9);

        Jugador jugador1 = new Jugador();
        jugador1.setNombre("Tania");
        jugador1.setFechaNacimiento(calendar1.getTime());
        jugador1.setCanastasTotales(100L);
        jugador1.setAsistenciasTotales(250L);
        jugador1.setRebotesTotales(50L);
        jugador1.setPosicionCampo("Alero");
        jugadorRepository.save(jugador1);

        Jugador jugador2 = new Jugador();
        jugador2.setNombre("Xavi");
        jugador2.setFechaNacimiento(calendar2.getTime());
        jugador2.setCanastasTotales(500L);
        jugador2.setAsistenciasTotales(300L);
        jugador2.setRebotesTotales(90L);
        jugador2.setPosicionCampo("Pivot");
        jugadorRepository.save(jugador2);

        Jugador jugador3 = new Jugador();
        jugador3.setNombre("Marc");
        jugador3.setFechaNacimiento(calendar3.getTime());
        jugador3.setCanastasTotales(100L);
        jugador3.setAsistenciasTotales(150L);
        jugador3.setRebotesTotales(2L);
        jugador3.setPosicionCampo("Base");
        jugadorRepository.save(jugador3);

        Jugador jugador4 = new Jugador();
        jugador4.setNombre("Pepe");
        jugador4.setFechaNacimiento(calendar4.getTime());
        jugador4.setCanastasTotales(20L);
        jugador4.setAsistenciasTotales(15L);
        jugador4.setRebotesTotales(1L);
        jugador4.setPosicionCampo("Alero");
        jugadorRepository.save(jugador4);

        Jugador jugador5 = new Jugador();
        jugador5.setNombre("Juan");
        jugador5.setFechaNacimiento(calendar5.getTime());
        jugador5.setCanastasTotales(900L);
        jugador5.setAsistenciasTotales(500L);
        jugador5.setRebotesTotales(200L);
        jugador5.setPosicionCampo("Pivot");
        jugadorRepository.save(jugador5);


        // Methods

        //Buscar jugadores por nombre, de manera que no sea necesario introducir el nombre completo.
        System.out.println(jugadorRepository.findByNombreContaining("Tania"));

        //Buscar jugadores que hayan conseguido un número mayor o igual a un número de canastas especificado como parámetro.
        System.out.println(jugadorRepository.findByCanastasTotalesGreaterThanEqual(200L));

        //Buscar jugadores que hayan efectuado un número de asistencias dentro de un rango especificado como parámetro (El rango se específica mediante un valor mínimo y un valor máximo).
        System.out.println(jugadorRepository.findByAsistenciasTotalesBetween(100L,300L));

        //Buscar jugadores que pertenezcan a una posición específica, por ejemplo: base
        System.out.println(jugadorRepository.findByPosicionCampo("Pivot"));

        //Buscar jugadores que hayan nacido en una fecha anterior a una fecha especificada como parámetro.
        System.out.println(jugadorRepository.findByFechaNacimientoBefore(calendar3.getTime()));

        //Combinar los apartados B y E: es decir, la consulta ha de devolver los jugadores que hayan conseguido un
        //número total de canastas mayor o igual a un parámetro, y además que he nacido en una fecha anterior a una fecha especificada como parámetro.
        System.out.println(jugadorRepository.findByCanastasTotalesGreaterThanEqualAndFechaNacimientoBefore(260L, calendar3.getTime()));

    }

}
