package com.instituto.endoscopia.repository;

import com.instituto.endoscopia.model.ConsumoInsumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ConsumoInsumoRepository extends JpaRepository<ConsumoInsumo, Long> {
    List<ConsumoInsumo> findByFechaConsumoGreaterThanEqualAndFechaConsumoLessThanEqual(LocalDate inicio, LocalDate fin);
}