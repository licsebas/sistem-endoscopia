package com.instituto.endoscopia.service;

import com.instituto.endoscopia.model.ConsumoHistorial;
import com.instituto.endoscopia.model.Insumo;
import com.instituto.endoscopia.repository.InsumoRepository;
import com.instituto.endoscopia.repository.ConsumoHistorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InsumoService {

    @Autowired
    private InsumoRepository insumoRepository;

    @Autowired
    private ConsumoHistorialRepository consumoHistorialRepository;

    public List<Insumo> obtenerTodo() {
        return insumoRepository.findAll();
    }

    // CAMBIO A DOUBLE
    public void registrarConsumo(Long id, Double cantidad, LocalDate fecha) {
        insumoRepository.findById(id).ifPresent(insumo -> {
            insumo.setStock(insumo.getStock() - cantidad);
            insumoRepository.save(insumo);

            ConsumoHistorial historial = new ConsumoHistorial();
            historial.setNombre(insumo.getNombre());
            historial.setCategoria(insumo.getCategoria());
            historial.setCantidad(cantidad); // Aquí espera un Double
            historial.setFecha(fecha);
            consumoHistorialRepository.save(historial);
        });
    }

    public List<ConsumoHistorial> obtenerReporte(LocalDate inicio, LocalDate fin) {
        return consumoHistorialRepository.findByFechaBetween(inicio, fin);
    }

    public Insumo agregarInsumo(Insumo nuevoInsumo) {
        nuevoInsumo.setStock(0.0); // CAMBIO A DOUBLE (0.0)
        return insumoRepository.save(nuevoInsumo);
    }

    // CAMBIO A DOUBLE
    public void modificarStockCritico(Long id, Double nuevoCritico) {
        insumoRepository.findById(id).ifPresent(insumo -> {
            insumo.setStockCritico(nuevoCritico);
            insumoRepository.save(insumo);
            System.out.println("Límite crítico modificado en BD: " + insumo.getNombre() + " - Nuevo límite: " + insumo.getStockCritico());
        });
    }

    public void editarNombreInsumo(Long id, String nuevoNombre) {
        insumoRepository.findById(id).ifPresent(insumo -> {
            insumo.setNombre(nuevoNombre);
            insumoRepository.save(insumo);
            System.out.println("Nombre de insumo modificado en BD. ID: " + id + " - Nuevo nombre: " + nuevoNombre);
        });
    }

    // CAMBIO A DOUBLE
    public void registrarConsumo(Long id, Double cantidad) {
        insumoRepository.findById(id).ifPresent(insumo -> {
            Double nuevoStock = insumo.getStock() - cantidad;
            insumo.setStock(nuevoStock);
            insumoRepository.save(insumo);
            System.out.println("Consumo registrado en BD: " + insumo.getNombre() + " - Stock restante: " + insumo.getStock());
        });
    }

    // CAMBIO A DOUBLE
    public void ajustarStock(Long id, Double nuevoStock) {
        insumoRepository.findById(id).ifPresent(insumo -> {
            insumo.setStock(nuevoStock);
            insumoRepository.save(insumo);
            System.out.println("Ajuste físico en BD: " + insumo.getNombre() + " - Stock corregido a: " + insumo.getStock());
        });
    }

    // CAMBIO A DOUBLE
    public void registrarIngreso(Long id, Double cantidad) {
        insumoRepository.findById(id).ifPresent(insumo -> {
            Double nuevoStock = insumo.getStock() + cantidad;
            insumo.setStock(nuevoStock);
            insumoRepository.save(insumo);
            System.out.println("Ingreso registrado en BD: " + insumo.getNombre() + " - Nuevo stock: " + insumo.getStock());
        });
    }

    public void eliminarInsumo(Long id) {
        insumoRepository.findById(id).ifPresent(insumo -> {
            insumo.setActivo(false);
            insumoRepository.save(insumo);
            System.out.println("Producto desactivado lógicamente: " + id);
        });
    }

    public void editarCategoria(Long id, String nuevaCategoria) {
        insumoRepository.findById(id).ifPresent(insumo -> {
            insumo.setCategoria(nuevaCategoria);
            insumoRepository.save(insumo);
        });
    }
}