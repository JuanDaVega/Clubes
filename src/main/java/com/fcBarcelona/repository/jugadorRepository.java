package com.fcBarcelona.repository;

import com.fcBarcelona.modelo.jugador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface jugadorRepository extends JpaRepository<jugador, Long> {
    List<jugador> findByEquipoId(Long equipoId);  
}
