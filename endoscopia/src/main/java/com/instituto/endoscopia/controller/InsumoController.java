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
        insumo.setActivo(true);
        if(insumo.getStock() == null) insumo.setStock(0.0);
        return insumoRepository.save(insumo);
    }

    @PostMapping("/ingresar/{id}")
    public Insumo ingresarStock(@PathVariable Long id,
                                @RequestParam Double cantidad, // CAMBIO A DOUBLE
                                @RequestParam String fecha,
                                @RequestParam(required = false) String numeroRemito) {

        return insumoRepository.findById(id).map(insumo -> {
            insumo.setStock(insumo.getStock() + cantidad);
            Insumo actualizado = insumoRepository.save(insumo);

            IngresoInsumo registro = new IngresoInsumo();
            registro.setInsumoId(insumo.getId());
            registro.setInsumoNombre(insumo.getNombre());
            registro.setCantidad(cantidad);
            registro.setFechaIngreso(LocalDate.parse(fecha));
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
    public Insumo consumirStock(@PathVariable Long id, @RequestParam Double cantidad, @RequestParam String fecha) { // CAMBIO A DOUBLE
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

    @PostMapping("/estado/{id}")
    public void cambiarEstado(@PathVariable Long id) {
        insumoRepository.findById(id).ifPresent(insumo -> {
            boolean estadoActual = insumo.getActivo() != null ? insumo.getActivo() : true;
            insumo.setActivo(!estadoActual);
            insumoRepository.save(insumo);
        });
    }

    @DeleteMapping("/{id}")
    public void eliminarFisico(@PathVariable Long id) {
        insumoRepository.deleteById(id);
    }

    @PostMapping("/ajustar/{id}")
    public Insumo ajustarStock(@PathVariable Long id, @RequestParam Double cantidad) { // CAMBIO A DOUBLE
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
    public void modificarCritico(@PathVariable Long id, @RequestParam Double nuevoCritico) { // CAMBIO A DOUBLE
        insumoRepository.findById(id).ifPresent(i -> { i.setStockCritico(nuevoCritico); insumoRepository.save(i); });
    }
}