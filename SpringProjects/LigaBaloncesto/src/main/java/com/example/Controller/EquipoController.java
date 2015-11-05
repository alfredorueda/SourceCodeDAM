package com.example.Controller;

import com.example.Model.Equipo;
import com.example.Repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jhipster on 4/11/15.
 */
@RestController
@RequestMapping("/equipos")
public class EquipoController {

    @Autowired
    public EquipoRepository equipoRepository;

    //Save a team
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Equipo save(@RequestBody Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    // Get every team
    @RequestMapping(method = RequestMethod.GET)
    public List<Equipo> findAll() {
        List<Equipo> equipos = new ArrayList<>();
        Iterator<Equipo> iterator = equipoRepository.findAll().iterator();

        while(iterator.hasNext()) {
            equipos.add(iterator.next());
        }

        return equipos;
    }

    //Get one team
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Equipo findById(@PathVariable Long id){
        Equipo equipo = new Equipo();

        try {
            equipo = equipoRepository.findOne(id);
        } catch (Exception e){
            System.out.println(e);
        }
        return equipo;
    }

    //Delete one team. On the Equipo model, i've added a cascade property to delete the players in that team
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable Long id) {
        Equipo equipo = new Equipo();

        try {
            equipo = equipoRepository.findOne(id);
        } catch (Exception e){
            System.out.println(e);
        }

        equipoRepository.delete(id);
    }

    //Update a team
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Equipo updateById(@PathVariable Long id, @RequestBody Equipo equipo) {
        Equipo e1 = new Equipo();

        try {
            e1 = equipoRepository.findOne(id);
        } catch (Exception e){
            System.out.println(e);
        }

        return equipoRepository.save(equipo);
    }
}
