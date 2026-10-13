package com.instituto.endoscopia.repository;

import com.instituto.endoscopia.model.StockGasInicial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockGasInicialRepository extends JpaRepository<StockGasInicial, Long> {
}