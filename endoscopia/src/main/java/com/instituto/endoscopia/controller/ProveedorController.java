package com.instituto.endoscopia.controller;

import com.instituto.endoscopia.model.Proveedor;
import com.instituto.endoscopia.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
@CrossOrigin(origins = "*")
public class ProveedorController {

    @Autowired
    private ProveedorRepository proveedorRepository;

    // Obtener la lista de proveedores
    @GetMapping
    public List<Proveedor> obtenerTodos() {
        return proveedorRepository.findAll();
    }

    // Guardar o actualizar un proveedor
    @PostMapping
    public Proveedor guardar(@RequestBody Proveedor proveedor) {
        if (proveedor.getActivo() == null) proveedor.setActivo(true);
        return proveedorRepository.save(proveedor);
    }

    // Suspender o Activar un proveedor
    @PostMapping("/estado/{id}")
    public void cambiarEstado(@PathVariable Long id) {
        proveedorRepository.findById(id).ifPresent(p -> {
            p.setActivo(!p.getActivo());
            proveedorRepository.save(p);
        });
    }
}