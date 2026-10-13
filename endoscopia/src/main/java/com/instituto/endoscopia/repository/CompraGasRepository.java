package com.instituto.endoscopia.repository;

import com.instituto.endoscopia.model.CompraGas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraGasRepository extends JpaRepository<CompraGas, Long> {
}