package com.example.Service;

import com.example.Model.Equipo;
import com.example.Model.Liga;
import com.example.Model.Temporada;
import com.example.Repository.EquipoRepository;
import com.example.Repository.JugadorRepository;
import com.example.Repository.LigaRepository;
import com.example.Repository.TemporadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jhipster on 21/10/15.
 */
@Service
@Transactional
public class LigaService {

    @Autowired
    private LigaRepository ligaRepository;

    @Autowired
    private TemporadaRepository temporadaRepository;

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private JugadorRepository jugadorRepository;

    public void crearEntidades(){
        Liga liga1 = new Liga();
        liga1.setNombre("Liga1");
        ligaRepository.save(liga1);

        Temporada temporada1 = new Temporada();
        temporada1.setYear(2014);
        temporadaRepository.save(temporada1);

        Temporada temporada2 = new Temporada();
        temporada2.setYear(2015);
        temporadaRepository.save(temporada2);

        temporada1.setLiga(liga1);
        temporada2.setLiga(liga1);

        temporadaRepository.save(temporada1);
        temporadaRepository.save(temporada2);

        Equipo equipo1 = equipoRepository.findByNombreContaining("Equip1").get(0);
        equipo1.getTemporadas().add(temporada1);
        Equipo equipo2 = equipoRepository.findByNombreContaining("Equip2").get(0);
        equipo2.getTemporadas().add(temporada1);
        Equipo equipo3 = equipoRepository.findByNombreContaining("Equip3").get(0);
        equipo3.getTemporadas().add(temporada1);
        Equipo equipo4 = equipoRepository.findByNombreContaining("Equip4").get(0);
        equipo4.getTemporadas().add(temporada1);
        Equipo equipo5 = equipoRepository.findByNombreContaining("Equip5").get(0);
        equipo5.getTemporadas().add(temporada1);



        Equipo equipo6 = equipoRepository.findByNombreContaining("Equip1").get(0);
        equipo6.getTemporadas().add(temporada2);
        Equipo equipo7 = equipoRepository.findByNombreContaining("Equip2").get(0);
        equipo7.getTemporadas().add(temporada2);

        equipoRepository.save(equipo1);
        equipoRepository.save(equipo2);
        equipoRepository.save(equipo3);
        equipoRepository.save(equipo4);
        equipoRepository.save(equipo5);
        equipoRepository.save(equipo6);
        equipoRepository.save(equipo7);



        consultasEntidades();


    }

    public void consultasEntidades(){
        //Queries para el ejercicio 2
        //Consulta los equipos existentes en una localidad determinada.
        System.out.println("Equipos en una localidad: " + equipoRepository.findByLocalidad("Barcelona"));

        //Devuelve todos los jugadores de un equipo, a partir del nombre completo del equipo.
        System.out.println("Jugador de un equipo: " + jugadorRepository.findByEquipoNombre("Equip2"));

        //Devuelve todos los jugadores de un equipo, que además jueguen en la misma posición, por ejemplo, alero.
        System.out.println("Jugadores de un equipo + posicion: " + jugadorRepository.findByEquipoNombreAndPosicionCampo("Equip1", "Pivot"));

        //Devuelve el jugador que más canastas ha conseguido del total de jugadores
        System.out.println("Jugador con MAX canastas: " + jugadorRepository.findFirstByOrderByCanastasTotalesDesc());

        //Devuelve los cinco jugadores que más asistencias han efectuado
        System.out.println("5 jugadores con más asistencias: " + jugadorRepository.findFirst5ByOrderByAsistenciasTotalesDesc());

        //Devuelve el jugador que más canastas ha realizado de un equipo determinado como parámetro.
        System.out.println("Jugador con MAX canastas de un equipo: " + jugadorRepository.findByEquipoNombreCanastasTotalesDesc("Equip1").get(0));

    }
}
