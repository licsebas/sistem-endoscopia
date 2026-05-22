package com.instituto.endoscopia.repository;

import com.instituto.endoscopia.model.ControlDiario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControlDiarioRepository extends JpaRepository<ControlDiario, Long> {
}