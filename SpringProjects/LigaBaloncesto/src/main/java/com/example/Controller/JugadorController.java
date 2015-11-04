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

    //Save a player
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Jugador save(@RequestBody Jugador jugador) {
        return jugadorRepository.save(jugador);
    }

    // Get every player
    @RequestMapping(method = RequestMethod.GET)
    public List<Jugador> findAll() {
        List<Jugador> jugadores = new ArrayList<>();
        Iterator<Jugador> iterator = jugadorRepository.findAll().iterator();

        while(iterator.hasNext()) {
            jugadores.add(iterator.next());
        }

        return jugadores;
    }

    //Get one player
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

    //Delete one player
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable Long id) {
        Jugador jugador = new Jugador();

        try {
            jugador = jugadorRepository.findOne(id);
        } catch (Exception e){
            System.out.println(e);
        }

        jugadorRepository.delete(id);
    }

    //Update a player
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Jugador updateById(@PathVariable Long id, @RequestBody Jugador jugador) {
        Jugador j1 = new Jugador();

        try {
            j1 = jugadorRepository.findOne(id);
        } catch (Exception e){
            System.out.println(e);
        }

        return jugadorRepository.save(jugador);
    }



}
