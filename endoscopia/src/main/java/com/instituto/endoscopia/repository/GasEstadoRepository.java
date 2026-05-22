package com.instituto.endoscopia.repository;

import com.instituto.endoscopia.model.GasEstado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface GasEstadoRepository extends JpaRepository<GasEstado, Long> {
    // Es vital que este método se llame así para que el Controller funcione
    Optional<GasEstado> findByTipo(String tipo);
}