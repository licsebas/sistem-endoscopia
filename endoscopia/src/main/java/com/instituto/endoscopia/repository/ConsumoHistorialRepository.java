package com.instituto.endoscopia.repository;

import com.instituto.endoscopia.model.ConsumoHistorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ConsumoHistorialRepository extends JpaRepository<ConsumoHistorial, Long> {
    // ¡Magia de Spring Boot! Él creará la consulta SQL para filtrar por fechas
    List<ConsumoHistorial> findByFechaBetween(LocalDate inicio, LocalDate fin);
}