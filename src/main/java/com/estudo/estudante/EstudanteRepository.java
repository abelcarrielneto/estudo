package com.estudo.estudante;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstudanteRepository extends JpaRepository<Estudante,Long> {
    @Query("SELECT s FROM Estudante s WHERE s.email = ?1")
    Optional<Estudante> findEstudanteByEmail(String email);

}
