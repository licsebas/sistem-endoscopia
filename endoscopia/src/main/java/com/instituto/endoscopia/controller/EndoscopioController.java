package com.instituto.endoscopia.controller;

import com.instituto.endoscopia.model.Endoscopio;
import com.instituto.endoscopia.repository.EndoscopioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/control/endoscopios")
@CrossOrigin(origins = "*")
public class EndoscopioController {

    @Autowired
    private EndoscopioRepository endoscopioRepository;

    @GetMapping
    public List<Endoscopio> obtenerTodos() {
        return endoscopioRepository.findAll();
    }

    @PostMapping
    public Endoscopio guardar(@RequestBody Endoscopio endoscopio) {
        return endoscopioRepository.save(endoscopio);
    }

    @DeleteMapping("/{id}")
    public void desactivar(@PathVariable Long id) {
        endoscopioRepository.findById(id).ifPresent(e -> {
            e.setActivo(false);
            endoscopioRepository.save(e);
        });
    }

    @PostMapping("/{id}/reparar")
    public void enviarAReparacion(@PathVariable Long id, @RequestParam String motivo) {
        endoscopioRepository.findById(id).ifPresent(e -> {
            e.setEstado("En reparación");
            String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            e.getHistorialReparaciones().add(fecha + " - Rotura: " + motivo);
            endoscopioRepository.save(e);
        });
    }

    @PostMapping("/{id}/alta-reparacion")
    public void volverAOperativo(@PathVariable Long id) {
        endoscopioRepository.findById(id).ifPresent(e -> {
            e.setEstado("Operativo");
            String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            e.getHistorialReparaciones().add(fecha + " - Regreso de reparación. Equipo Operativo.");
            endoscopioRepository.save(e);
        });
    }

    // ====================================================================
    // NUEVO MÉTODO: Permite editar los datos si cargaste mal la serie o el nombre
    // ====================================================================
    @PutMapping("/editar/{id}")
    public Endoscopio editarEquipo(@PathVariable Long id, @RequestBody Endoscopio datosNuevos) {
        return endoscopioRepository.findById(id).map(e -> {
            e.setAlias(datosNuevos.getAlias());
            e.setSerie(datosNuevos.getSerie());
            e.setTipo(datosNuevos.getTipo());
            return endoscopioRepository.save(e);
        }).orElse(null);
    }





}