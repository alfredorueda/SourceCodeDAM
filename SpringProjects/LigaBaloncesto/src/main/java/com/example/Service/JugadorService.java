package com.example.Service;

import com.example.Model.Equipo;
import com.example.Model.Jugador;
import com.example.Repository.EquipoRepository;
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

    @Autowired
    private EquipoRepository equipoRepository;

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
        Equipo equipo1 = equipoRepository.findByNombreContaining("Equip1").get(0);
        jugador1.setEquipo(equipo1);
        jugadorRepository.save(jugador1);

        Jugador jugador2 = new Jugador();
        jugador2.setNombre("Xavi");
        jugador2.setFechaNacimiento(calendar2.getTime());
        jugador2.setCanastasTotales(500L);
        jugador2.setAsistenciasTotales(300L);
        jugador2.setRebotesTotales(90L);
        jugador2.setPosicionCampo("Pivot");
        Equipo equipo2 = equipoRepository.findByNombreContaining("Equip2").get(0);
        jugador2.setEquipo(equipo2);
        jugadorRepository.save(jugador2);

        Jugador jugador3 = new Jugador();
        jugador3.setNombre("Marc");
        jugador3.setFechaNacimiento(calendar3.getTime());
        jugador3.setCanastasTotales(100L);
        jugador3.setAsistenciasTotales(150L);
        jugador3.setRebotesTotales(2L);
        jugador3.setPosicionCampo("Base");
        Equipo equipo3 = equipoRepository.findByNombreContaining("Equip3").get(0);
        jugador3.setEquipo(equipo3);
        jugadorRepository.save(jugador3);

        Jugador jugador4 = new Jugador();
        jugador4.setNombre("Pepe");
        jugador4.setFechaNacimiento(calendar4.getTime());
        jugador4.setCanastasTotales(20L);
        jugador4.setAsistenciasTotales(15L);
        jugador4.setRebotesTotales(1L);
        jugador4.setPosicionCampo("Alero");
        Equipo equipo4 = equipoRepository.findByNombreContaining("Equip4").get(0);
        jugador4.setEquipo(equipo4);
        jugadorRepository.save(jugador4);

        Jugador jugador5 = new Jugador();
        jugador5.setNombre("Juan");
        jugador5.setFechaNacimiento(calendar5.getTime());
        jugador5.setCanastasTotales(900L);
        jugador5.setAsistenciasTotales(500L);
        jugador5.setRebotesTotales(200L);
        jugador5.setPosicionCampo("Pivot");
        Equipo equipo5 = equipoRepository.findByNombreContaining("Equip5").get(0);
        jugador5.setEquipo(equipo5);
        jugadorRepository.save(jugador5);

        //Exercise 2. Adding a few more players because :shrug:
        Jugador jugador6 = new Jugador("Jugador6", calendar5.getTime(), 100L, 200L, 300L, "Alero");
        jugador6.setEquipo(equipoRepository.findByNombreContaining("Equip1").get(0));
        jugadorRepository.save(jugador6);

        Jugador jugador7 = new Jugador("Jugador7", calendar4.getTime(), 200L, 200L, 300L, "Pivot");
        jugador7.setEquipo(equipoRepository.findByNombreContaining("Equip1").get(0));
        jugadorRepository.save(jugador7);

        Jugador jugador8 = new Jugador("Jugador8", calendar3.getTime(), 100L, 500L, 300L, "Base");
        jugador8.setEquipo(equipoRepository.findByNombreContaining("Equip1").get(0));
        jugadorRepository.save(jugador8);

        Jugador jugador9 = new Jugador("Jugador9", calendar5.getTime(), 10L, 5L, 20L, "Base");
        jugador9.setEquipo(equipoRepository.findByNombreContaining("Equip1").get(0));
        jugadorRepository.save(jugador9);

        Jugador jugador10 = new Jugador("Jugador10", calendar5.getTime(), 10L, 5L, 20L, "Pivot");
        jugador10.setEquipo(equipoRepository.findByNombreContaining("Equip2").get(0));
        jugadorRepository.save(jugador10);

        Jugador jugador11 = new Jugador("Jugador11", calendar4.getTime(), 100L, 500L, 200L, "Alero");
        jugador11.setEquipo(equipoRepository.findByNombreContaining("Equip3").get(0));
        jugadorRepository.save(jugador11);

        Jugador jugador12 = new Jugador("Jugador12", calendar3.getTime(), 200L, 300L, 2000L, "Base");
        jugador12.setEquipo(equipoRepository.findByNombreContaining("Equip4").get(0));
        jugadorRepository.save(jugador12);

        Jugador jugador13 = new Jugador("Jugador13", calendar2.getTime(), 1000L, 900L, 700L, "Pivot");
        jugador13.setEquipo(equipoRepository.findByNombreContaining("Equip5").get(0));
        jugadorRepository.save(jugador13);

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
