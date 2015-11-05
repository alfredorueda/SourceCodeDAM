package com.example.Controller;

import com.example.Model.Liga;
import com.example.Repository.LigaRepository;
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
@RequestMapping("/ligas")
public class LigaController {

    @Autowired
    public LigaRepository ligaRepository;

    //Save a league
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Liga save(@RequestBody Liga liga) {
        return ligaRepository.save(liga);
    }

    // Get every league
    @RequestMapping(method = RequestMethod.GET)
    public List<Liga> findAll() {
        List<Liga> ligas = new ArrayList<>();
        Iterator<Liga> iterator = ligaRepository.findAll().iterator();

        while(iterator.hasNext()) {
            ligas.add(iterator.next());
        }

        return ligas;
    }

    //Get one league
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Liga findById(@PathVariable Long id){
        Liga liga = new Liga();

        try {
            liga = ligaRepository.findOne(id);
        } catch (Exception e){
            System.out.println(e);
        }
        return liga;
    }

    //Delete one league. Added the cascade parameter to the mapped by on the Liga Model
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable Long id) {
        Liga liga = new Liga();

        try {
            liga = ligaRepository.findOne(id);
        } catch (Exception e){
            System.out.println(e);
        }

        ligaRepository.delete(id);
    }

    //Update a league.
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Liga updateById(@PathVariable Long id, @RequestBody Liga liga) {
        Liga l1 = new Liga();

        try {
            l1 = ligaRepository.findOne(id);
        } catch (Exception e){
            System.out.println(e);
        }

        return ligaRepository.save(liga);
    }
}
