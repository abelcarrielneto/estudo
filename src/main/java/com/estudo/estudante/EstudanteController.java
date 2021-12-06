package com.estudo.estudante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping("/estudante")
public class EstudanteController {
    private final EstudanteService estudanteService; //referencia de EstudanteService
    @Autowired //esta falando que EstudanteService deve ser conectado automaticamente entao sera instanciado e injetado no construtor public EstudanteController
    public EstudanteController(EstudanteService estudanteService) {
        this.estudanteService = estudanteService;
    }

    @GetMapping
    public List<Estudante> getEstudante() {
        return estudanteService.getEstudante();
    }
    @PostMapping
    public void registrarNovoEstudante(@RequestBody Estudante estudante){
        estudanteService.addNewEstudante(estudante);
    }
    @DeleteMapping("{estudanteId}")
    public void deletarEstudante(@PathVariable("estudanteId") Long estudanteId){
        estudanteService.deleteEstudante(estudanteId);
    }
    @PutMapping("{estudanteId}")
    public void updateEstudante(
            @PathVariable("estudanteId") Long estudanteId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email){
        estudanteService.updateEstudante(estudanteId, name, email);
    }
}
