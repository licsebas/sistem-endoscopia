package com.instituto.endoscopia.repository;

import com.instituto.endoscopia.model.Insumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsumoRepository extends JpaRepository<Insumo, Long>{
// Al extender de JpaRepository, Java automáticamente nos regala métodos como:
    // save() -> Para guardar un nuevo insumo
    // findAll() -> Para traer la lista completa
    // deleteById() -> Para borrar
    // ¡No necesitas escribir NINGÚN código aquí adentro por ahora!


}
