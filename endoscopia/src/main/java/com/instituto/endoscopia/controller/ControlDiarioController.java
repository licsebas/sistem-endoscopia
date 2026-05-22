package com.instituto.endoscopia.controller;

import com.instituto.endoscopia.model.ControlDiario;
import com.instituto.endoscopia.model.Endoscopio;
import com.instituto.endoscopia.repository.ControlDiarioRepository;
import com.instituto.endoscopia.repository.EndoscopioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/control-diario")
@CrossOrigin(origins = "*")
public class ControlDiarioController {

    @Autowired
    private ControlDiarioRepository controlDiarioRepository;

    @PostMapping
    public ControlDiario guardarControl(@RequestBody ControlDiario control) {
        return controlDiarioRepository.save(control);
    }
    @Autowired
    private EndoscopioRepository endoscopioRepository;
    @PutMapping("/endoscopios/editar/{id}")
    public ResponseEntity<?> editarEndoscopio(@PathVariable Long id,
                                              @RequestParam String modelo,
                                              @RequestParam String serie) {
        try {
            // Buscamos el equipo en la base de datos
            Endoscopio equipo = endoscopioRepository.findById(id).orElseThrow();

            // Verificamos si el número de serie nuevo ya lo tiene OTRO equipo distinto
            boolean serieExiste = endoscopioRepository.findAll().stream()
                    .anyMatch(e -> e.getSerie().equalsIgnoreCase(serie) && !e.getId().equals(id));

            if (serieExiste) {
                return ResponseEntity.badRequest().body("Error: El número de serie ya está registrado en otro equipo.");
            }

            // Actualizamos los datos (internamente usamos alias para el modelo)
            equipo.setAlias(modelo);
            equipo.setSerie(serie);

            endoscopioRepository.save(equipo);
            return ResponseEntity.ok(equipo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar el equipo");
        }
    }
    @GetMapping
    public List<ControlDiario> obtenerTodos() {
        return controlDiarioRepository.findAll();
    }
    @DeleteMapping("/endoscopios/{id}")
    public ResponseEntity<?> eliminarEndoscopio(@PathVariable Long id) {
        try {
            endoscopioRepository.deleteById(id);
            return ResponseEntity.ok("Equipo eliminado correctamente.");
        } catch (Exception e) {
            // Si el equipo ya está en un control diario, la base de datos no dejará borrarlo
            return ResponseEntity.badRequest().body("No se puede eliminar porque ya tiene controles diarios registrados. Si ya no se usa, declárelo como Roto/Inactivo.");
        }
    }




}