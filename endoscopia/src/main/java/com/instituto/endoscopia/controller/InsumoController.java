package com.instituto.endoscopia.controller;

import com.instituto.endoscopia.model.Insumo;
import com.instituto.endoscopia.model.IngresoInsumo;
import com.instituto.endoscopia.model.ConsumoInsumo;
import com.instituto.endoscopia.repository.InsumoRepository;
import com.instituto.endoscopia.repository.IngresoInsumoRepository;
import com.instituto.endoscopia.repository.ConsumoInsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/insumos")
@CrossOrigin(origins = "*")
public class InsumoController {

    @Autowired private InsumoRepository insumoRepository;
    @Autowired private IngresoInsumoRepository ingresoInsumoRepository;
    @Autowired private ConsumoInsumoRepository consumoInsumoRepository;

    @GetMapping
    public List<Insumo> obtenerTodos() {
        return insumoRepository.findAll();
    }

    @PostMapping
    public Insumo crear(@RequestBody Insumo insumo) {
        // Por defecto, todo insumo nuevo nace "Activo"
        insumo.setActivo(true);
        return insumoRepository.save(insumo);
    }

    @PostMapping("/ingresar/{id}")
    public Insumo ingresarStock(@PathVariable Long id,
                                @RequestParam Integer cantidad,
                                @RequestParam String fecha,
                                @RequestParam(required = false) String numeroRemito) { // <-- 1. Nuevo parámetro opcional agregado aquí

        return insumoRepository.findById(id).map(insumo -> {
            // 1. Aumentamos el stock del insumo
            insumo.setStock(insumo.getStock() + cantidad);
            Insumo actualizado = insumoRepository.save(insumo);

            // 2. Creamos el registro para el historial
            IngresoInsumo registro = new IngresoInsumo();
            registro.setInsumoId(insumo.getId());
            registro.setInsumoNombre(insumo.getNombre());
            registro.setCantidad(cantidad);
            registro.setFechaIngreso(LocalDate.parse(fecha));

            // <-- 2. Guardamos el número de remito en el historial
            registro.setNumeroRemito(numeroRemito);

            ingresoInsumoRepository.save(registro);

            return actualizado;
        }).orElse(null);
    }
    @GetMapping("/ingresos")
    public List<IngresoInsumo> buscarIngresosPorPeriodo(@RequestParam String inicio, @RequestParam String fin) {
        LocalDate fechaInicio = LocalDate.parse(inicio);
        LocalDate fechaFin = LocalDate.parse(fin);
        return ingresoInsumoRepository.findByFechaIngresoBetween(fechaInicio, fechaFin);
    }

    @PostMapping("/consumir/{id}")
    public Insumo consumirStock(@PathVariable Long id, @RequestParam Integer cantidad, @RequestParam String fecha) {
        return insumoRepository.findById(id).map(insumo -> {
            insumo.setStock(insumo.getStock() - cantidad);
            Insumo actualizado = insumoRepository.save(insumo);

            ConsumoInsumo consumo = new ConsumoInsumo();
            consumo.setInsumoNombre(insumo.getNombre());
            consumo.setCantidad(cantidad);
            consumo.setFechaConsumo(LocalDate.parse(fecha));
            consumoInsumoRepository.save(consumo);

            return actualizado;
        }).orElse(null);
    }

    @GetMapping("/consumos")
    public List<ConsumoInsumo> obtenerConsumosPorPeriodo(@RequestParam String inicio, @RequestParam String fin) {
        LocalDate fechaInicio = LocalDate.parse(inicio);
        LocalDate fechaFin = LocalDate.parse(fin);
        return consumoInsumoRepository.findByFechaConsumoGreaterThanEqualAndFechaConsumoLessThanEqual(fechaInicio, fechaFin);
    }

    // ==========================================================
    // NUEVOS MÉTODOS PARA EL CATÁLOGO: SUSPENDER Y ELIMINAR REAL
    // ==========================================================

    @PostMapping("/estado/{id}")
    public void cambiarEstado(@PathVariable Long id) {
        insumoRepository.findById(id).ifPresent(insumo -> {
            // Si estaba Activo (true) pasa a falso, y viceversa
            boolean estadoActual = insumo.getActivo() != null ? insumo.getActivo() : true;
            insumo.setActivo(!estadoActual);
            insumoRepository.save(insumo);
        });
    }

    @DeleteMapping("/{id}")
    public void eliminarFisico(@PathVariable Long id) {
        // Borra permanentemente el insumo de la base de datos
        insumoRepository.deleteById(id);
    }

    // ==========================================================

    @PostMapping("/ajustar/{id}")
    public Insumo ajustarStock(@PathVariable Long id, @RequestParam Integer cantidad) {
        return insumoRepository.findById(id).map(insumo -> {
            insumo.setStock(cantidad);
            return insumoRepository.save(insumo);
        }).orElse(null);
    }

    @PostMapping("/editar-nombre/{id}")
    public void editarNombre(@PathVariable Long id, @RequestParam String nuevoNombre) {
        insumoRepository.findById(id).ifPresent(i -> { i.setNombre(nuevoNombre); insumoRepository.save(i); });
    }

    @PostMapping("/editar-categoria/{id}")
    public void editarCategoria(@PathVariable Long id, @RequestParam String nuevaCategoria) {
        insumoRepository.findById(id).ifPresent(i -> { i.setCategoria(nuevaCategoria); insumoRepository.save(i); });
    }

    @PostMapping("/modificar-critico/{id}")
    public void modificarCritico(@PathVariable Long id, @RequestParam Integer nuevoCritico) {
        insumoRepository.findById(id).ifPresent(i -> { i.setStockCritico(nuevoCritico); insumoRepository.save(i); });
    }
}