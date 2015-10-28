package com.example.Controller;

import com.example.Model.Jugador;
import com.example.Repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    //Get every developer. Something is wrong with the DB? Shows the first player and then loops over the same
    @RequestMapping(method = RequestMethod.GET)
    public List<Jugador> findAll() {
        List<Jugador> jugadores = new ArrayList<>();
        Iterator<Jugador> iterator = jugadorRepository.findAll().iterator();

        while (iterator.hasNext()) {
            jugadores.add(iterator.next());
        }

        return jugadores;
    }

    //Get one developer
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String findOne(@PathVariable Long id){
        String jugador;

        try {
            jugador = jugadorRepository.findOne(id).toString();
        } catch (Exception e){
            jugador = "PLayer not found. Check the console for more info";
            System.out.println(e);
        }
        return jugador;
    }
}
