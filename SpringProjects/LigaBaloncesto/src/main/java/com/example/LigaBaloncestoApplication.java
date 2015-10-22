package com.example;

import com.example.Service.EquipoService;
import com.example.Service.JugadorService;
import com.example.Service.LigaService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LigaBaloncestoApplication {

    private static JugadorService jugadorService;
    private static EquipoService equipoService;
    private static LigaService ligaService;

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(LigaBaloncestoApplication.class, args);

        equipoService = context.getBean(EquipoService.class);
        equipoService.testEquipo();

        jugadorService = context.getBean(JugadorService.class);
        jugadorService.testJugador();

        ligaService = context.getBean(LigaService.class);
        ligaService.crearEntidades();


    }
}
