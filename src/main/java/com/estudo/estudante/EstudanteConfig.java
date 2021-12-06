package com.estudo.estudante;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class EstudanteConfig {
    @Bean
    CommandLineRunner commandLineRunner(
            EstudanteRepository repository){
        return args -> {
            Estudante abel = new Estudante(
                    "Abel",
                    "abel.neto@gmail.com",
                    LocalDate.of(1992, Month.MARCH,11)
            );
            Estudante alex = new Estudante(
                    "Alex",
                    "alex.silva@gmail.com",
                    LocalDate.of(1999, Month.MARCH,9)
            );
            repository.saveAll(
                    List.of(abel,alex)
            );
        };
    }

}
