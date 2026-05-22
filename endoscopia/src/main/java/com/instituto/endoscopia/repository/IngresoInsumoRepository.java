package com.instituto.endoscopia.repository;

import com.instituto.endoscopia.model.IngresoInsumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface IngresoInsumoRepository extends JpaRepository<IngresoInsumo, Long> {
    // CAMBIAMOS ESTA LÍNEA PARA QUE BUSQUE ENTRE DOS FECHAS
    List<IngresoInsumo> findByFechaIngresoBetween(LocalDate inicio, LocalDate fin);
}