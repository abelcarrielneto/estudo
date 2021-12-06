package com.estudo.estudante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EstudanteService {
    private final EstudanteRepository estudanteRepository;

    @Autowired
    public EstudanteService(EstudanteRepository estudanteRepository) {
        this.estudanteRepository = estudanteRepository;
    }

    public List<Estudante> getEstudante() {
        return estudanteRepository.findAll();

    }

    public void addNewEstudante(Estudante estudante) {
        Optional<Estudante> estudanteOptional = estudanteRepository
                .findEstudanteByEmail(estudante.getEmail());
        if (estudanteOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        estudanteRepository.save(estudante);
    }

    public void deleteEstudante(Long estudanteId) {
        boolean exists = estudanteRepository.existsById(estudanteId);
        if (!exists) {
            throw new IllegalStateException("estudante com id " + estudanteId + " não existe");
        }
        estudanteRepository.deleteById(estudanteId);
    }

    @Transactional
    public void updateEstudante(Long estudanteId,
                                String name,
                                String email) {
        Estudante estudante = estudanteRepository.findById(estudanteId)
                .orElseThrow(() -> new IllegalStateException(
                        "estudante com id " + estudanteId + " nao existe"));
        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(estudante.getNome(), name)) {
            estudante.setNome(name);
        }
        if (email != null &&
                email.length() > 0 &&
                !Objects.equals(estudante.getEmail(), email)) {
            Optional<Estudante> estudanteOptional = estudanteRepository.
                    findEstudanteByEmail(email);
            if (estudanteOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            estudante.setEmail(email);
        }

    }
}