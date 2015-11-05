package com.example.Controller;

import com.example.Model.Temporada;
import com.example.Repository.TemporadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jhipster on 5/11/15.
 */
@RestController
@RequestMapping("/temporadas")
public class TemporadaController {

    @Autowired
    public TemporadaRepository temporadaRepository;

    //Save a season
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Temporada save(@RequestBody Temporada temporada) {
        return temporadaRepository.save(temporada);
    }

    // Get every season
    @RequestMapping(method = RequestMethod.GET)
    public List<Temporada> findAll() {
        List<Temporada> temporadas = new ArrayList<>();
        Iterator<Temporada> iterator = temporadaRepository.findAll().iterator();

        while(iterator.hasNext()) {
            temporadas.add(iterator.next());
        }

        return temporadas;
    }

    //Get one season
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Temporada findById(@PathVariable Long id){
        Temporada temporada = new Temporada();

        try {
            temporada = temporadaRepository.findOne(id);
        } catch (Exception e){
            System.out.println(e);
        }
        return temporada;
    }

    //Delete one season.
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable Long id) {
        Temporada temporada = new Temporada();

        try {
            temporada = temporadaRepository.findOne(id);
        } catch (Exception e){
            System.out.println(e);
        }

        temporadaRepository.delete(id);
    }

    //Update a season
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Temporada updateById(@PathVariable Long id, @RequestBody Temporada temporada) {
        Temporada t1 = new Temporada();

        try {
            t1 = temporadaRepository.findOne(id);
        } catch (Exception e){
            System.out.println(e);
        }

        return temporadaRepository.save(temporada);
    }
}
