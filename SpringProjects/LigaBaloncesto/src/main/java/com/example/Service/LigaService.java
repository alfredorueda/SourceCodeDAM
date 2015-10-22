package com.example.Service;

import com.example.Model.Liga;
import com.example.Model.Temporada;
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

        //liga1.setTemporada(temporadaRepository.findByYear(2014).get(0));

    }
}
