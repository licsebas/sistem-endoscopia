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

    // 1. Inyectamos la conexión a la base de datos (nuestro Repositorio)
    // CORRECCIÓN: Eliminamos la palabra "static"
    @Autowired
    private InsumoRepository insumoRepository;

    // 2. Traer todos los productos: ahora usamos .findAll() que viene de JPA
    public List<Insumo> obtenerTodo() {
        return insumoRepository.findAll();
    }
    // 1. Agrega este nuevo cable debajo del insumoRepository
    @Autowired
    private ConsumoHistorialRepository consumoHistorialRepository;

    // 2. REEMPLAZA tu método registrarConsumo por este nuevo que incluye la fecha:
    public void registrarConsumo(Long id, int cantidad, LocalDate fecha) {
        insumoRepository.findById(id).ifPresent(insumo -> {
            insumo.setStock(insumo.getStock() - cantidad);
            insumoRepository.save(insumo);

            // Guardamos el ticket histórico del consumo
            ConsumoHistorial historial = new ConsumoHistorial();
            historial.setNombre(insumo.getNombre());
            historial.setCategoria(insumo.getCategoria());
            historial.setCantidad(cantidad);
            historial.setFecha(fecha);
            consumoHistorialRepository.save(historial);
        });
    }

    // 3. AGREGA este nuevo método al final para pedir el reporte:
    public List<ConsumoHistorial> obtenerReporte(LocalDate inicio, LocalDate fin) {
        return consumoHistorialRepository.findByFechaBetween(inicio, fin);
    }

    // 3. Guardar un producto nuevo: usamos .save()
    public Insumo agregarInsumo(Insumo nuevoInsumo) {
        // Todo producto nuevo nace con stock inicial en cero
        nuevoInsumo.setStock(0);

        // ¡Magia de JPA! Él le asignará el ID y lo guardará en la tabla
        return insumoRepository.save(nuevoInsumo);
    }
    // NUEVO MÉTODO: Modificar el límite de stock crítico de un insumo existente
    public void modificarStockCritico(Long id, int nuevoCritico) {
        insumoRepository.findById(id).ifPresent(insumo -> {
            insumo.setStockCritico(nuevoCritico); // Actualizamos solo el valor crítico
            insumoRepository.save(insumo); // Guardamos en la base de datos H2
            System.out.println("Límite crítico modificado en BD: " + insumo.getNombre() + " - Nuevo límite: " + insumo.getStockCritico());
        });
    }
    // NUEVO MÉTODO: Editar el nombre de un insumo existente
    public void editarNombreInsumo(Long id, String nuevoNombre) {
        insumoRepository.findById(id).ifPresent(insumo -> {
            insumo.setNombre(nuevoNombre); // Cambiamos el nombre viejo por el nuevo
            insumoRepository.save(insumo); // Guardamos en la base de datos H2
            System.out.println("Nombre de insumo modificado en BD. ID: " + id + " - Nuevo nombre: " + nuevoNombre);
        });
    }


    // 4. Actualizar stock: buscamos por ID y guardamos el cambio
    // CORRECCIÓN: Eliminamos la palabra "static"
    public void registrarConsumo(Long id, int cantidad) {
        // .findById() busca el producto en la base de datos
        insumoRepository.findById(id).ifPresent(insumo -> {

            // Le restamos la cantidad consumida
            int nuevoStock = insumo.getStock() - cantidad;
            insumo.setStock(nuevoStock);

            // Guardamos el producto actualizado en la base de datos
            insumoRepository.save(insumo);

            System.out.println("Consumo registrado en BD: " + insumo.getNombre() + " - Stock restante: " + insumo.getStock());
        });
    }

    // NUEVO MÉTODO: Ajuste manual exacto de stock
    public void ajustarStock(Long id, int nuevoStock) {
        insumoRepository.findById(id).ifPresent(insumo -> {
            insumo.setStock(nuevoStock); // Reemplazamos el stock viejo por el nuevo
            insumoRepository.save(insumo); // Guardamos el cambio en la BD
            System.out.println("Ajuste físico en BD: " + insumo.getNombre() + " - Stock corregido a: " + insumo.getStock());
        });
    }





    // Nuevo método: Busca el producto por ID, le SUMA la cantidad y lo guarda
    public void registrarIngreso(Long id, int cantidad) {
        insumoRepository.findById(id).ifPresent(insumo -> {
            int nuevoStock = insumo.getStock() + cantidad;
            insumo.setStock(nuevoStock);
            insumoRepository.save(insumo);
            System.out.println("Ingreso registrado en BD: " + insumo.getNombre() + " - Nuevo stock: " + insumo.getStock());
        });
    }
    // MÉTODO ACTUALIZADO: Borrado Lógico
    public void eliminarInsumo(Long id) {
        insumoRepository.findById(id).ifPresent(insumo -> {
            insumo.setActivo(false); // Lo marcamos como inactivo
            insumoRepository.save(insumo); // Guardamos el cambio
            System.out.println("Producto desactivado lógicamente: " + id);
        });
    }
    // NUEVO MÉTODO: Editar la categoría de un insumo
    public void editarCategoria(Long id, String nuevaCategoria) {
        insumoRepository.findById(id).ifPresent(insumo -> {
            insumo.setCategoria(nuevaCategoria);
            insumoRepository.save(insumo);
        });
    }
}