package com.instituto.endoscopia.repository;

import com.instituto.endoscopia.model.ConsumoGas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ConsumoGasRepository extends JpaRepository<ConsumoGas, Long> {
    // Esta instrucción es mucho más precisa y segura que el "Between"
    List<ConsumoGas> findByFechaGreaterThanEqualAndFechaLessThanEqual(LocalDate inicio, LocalDate fin);
}