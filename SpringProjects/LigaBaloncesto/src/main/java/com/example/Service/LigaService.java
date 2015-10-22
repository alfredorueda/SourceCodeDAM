package com.example.Service;

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

        temporada1.getEquipos().add(equipoRepository.findByNombreContaining("Equip1").get(0));
        temporada1.getEquipos().add(equipoRepository.findByNombreContaining("Equip2").get(0));
        temporada1.getEquipos().add(equipoRepository.findByNombreContaining("Equip3").get(0));
        temporada1.getEquipos().add(equipoRepository.findByNombreContaining("Equip4").get(0));
        temporada1.getEquipos().add(equipoRepository.findByNombreContaining("Equip5").get(0));


        temporada2.getEquipos().add(equipoRepository.findByNombreContaining("Equip4").get(0));
        temporada2.getEquipos().add(equipoRepository.findByNombreContaining("Equip5").get(0));

        temporadaRepository.save(temporada1);
        temporadaRepository.save(temporada2);

        consultasEntidades();


    }

    public void consultasEntidades(){
        //Queries
        //Consulta los equipos existentes en una localidad determinada.
        System.out.println(equipoRepository.findByLocalidad("Barcelona"));

        //Devuelve todos los jugadores de un equipo, a partir del nombre completo del equipo.
        System.out.println();

        //Devuelve todos los jugadores de un equipo, que además jueguen en la misma posición, por ejemplo, alero.


        //Devuelve el jugador que más canastas ha conseguido del total de jugadores


        //Devuelve los cinco jugadores que más asistencias han efectuado


        //Devuelve el jugador que más canastas ha realizado de un equipo determinado como parámetro.

    }
}
