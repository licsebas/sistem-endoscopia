package com.instituto.endoscopia.repository;

import com.instituto.endoscopia.model.ConteoGas; // Importamos el modelo correcto
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConteoGasRepository extends JpaRepository<ConteoGas, Long> {
}