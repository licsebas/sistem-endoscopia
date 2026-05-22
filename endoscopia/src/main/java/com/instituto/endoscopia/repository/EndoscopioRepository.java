package com.instituto.endoscopia.repository;

import com.instituto.endoscopia.model.Endoscopio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EndoscopioRepository extends JpaRepository<Endoscopio, Long> {
}