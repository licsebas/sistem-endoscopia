package com.instituto.endoscopia.repository;

import com.instituto.endoscopia.model.ConsumoGas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumoGasRepository extends JpaRepository<ConsumoGas, Long> {
}