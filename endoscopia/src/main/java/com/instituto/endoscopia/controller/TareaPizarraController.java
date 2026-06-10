package com.instituto.endoscopia.controller;
import com.instituto.endoscopia.model.TareaPizarra;
import com.instituto.endoscopia.repository.TareaPizarraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizarra")
@CrossOrigin(origins = "*")
public class TareaPizarraController {

    @Autowired
    private TareaPizarraRepository repo;

    @GetMapping
    public List<TareaPizarra> obtenerTodas() {
        return repo.findAll();
    }

    @PostMapping
    public TareaPizarra guardar(@RequestBody TareaPizarra tarea) {
        // Al tener el ID asignado, Spring Boot actualiza si ya existe, o lo crea si es nuevo
        return repo.save(tarea);
    }
}