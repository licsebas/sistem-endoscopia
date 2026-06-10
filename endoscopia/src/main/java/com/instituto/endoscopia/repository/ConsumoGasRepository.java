package com.instituto.endoscopia.repository;

import com.instituto.endoscopia.model.ConsumoGas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ConsumoGasRepository extends JpaRepository<ConsumoGas, Long> {
    // Esta línea mágica busca los consumos entre dos fechas para tu reporte
    List<ConsumoGas> findByFechaBetween(LocalDate inicio, LocalDate fin);
}