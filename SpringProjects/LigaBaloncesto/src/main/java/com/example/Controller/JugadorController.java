package com.example.Controller;

import com.example.Model.Jugador;
import com.example.Repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jhipster on 28/10/15.
 */
@RestController
@RequestMapping("/jugadores")
public class JugadorController {

    @Autowired
    public JugadorRepository jugadorRepository;

    // Get every developer.
    @RequestMapping(method = RequestMethod.GET)
    public List<Jugador> findAll() {
        List<Jugador> jugadores = new ArrayList<>();
        Iterator<Jugador> iterator = jugadorRepository.findAll().iterator();

        while(iterator.hasNext()) {
            jugadores.add(iterator.next());
        }

        return jugadores;
    }

    //Get one developer
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Jugador findById(@PathVariable Long id){
        Jugador jugador = new Jugador();

        try {
            jugador = jugadorRepository.findOne(id);
        } catch (Exception e){
            System.out.println(e);
        }
        return jugador;
    }

    //Save a player
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Jugador save(@RequestBody Jugador jugador) {
        return jugadorRepository.save(jugador);
    }
}
