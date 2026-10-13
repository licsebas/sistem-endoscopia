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
                                @RequestParam Double cantidad,
                                @RequestParam String fecha,
                                @RequestParam(required = false, defaultValue = "0.0") Double precio,
                                @RequestParam(required = false) String numeroRemito,
                                @RequestParam(required = false) String numeroFactura,
                                @RequestParam(required = false) String fechaFactura,
                                @RequestParam(required = false) String proveedorNombre, // NUEVO PARÁMETRO
                                @RequestParam(required = false, defaultValue = "Sistema") String usuario) {

        return insumoRepository.findById(id).map(insumo -> {
            insumo.setStock(insumo.getStock() + cantidad);

            // INTELIGENCIA: Si la técnica cargó un precio nuevo en el remito, actualizamos el catálogo maestro
            if (precio != null && precio > 0) {
                insumo.setPrecioActual(precio);
            }

            Insumo actualizado = insumoRepository.save(insumo);

            IngresoInsumo registro = new IngresoInsumo();
            registro.setInsumoId(insumo.getId());
            registro.setInsumoNombre(insumo.getNombre());
            registro.setCantidad(cantidad);
            registro.setFechaIngreso(java.time.LocalDate.parse(fecha));
            registro.setNumeroRemito(numeroRemito);
            registro.setNumeroFactura(numeroFactura);

            if (fechaFactura != null && !fechaFactura.isEmpty()) {
                registro.setFechaFactura(java.time.LocalDate.parse(fechaFactura));
            }

            // GUARDAMOS EL PROVEEDOR
            registro.setProveedorNombre(proveedorNombre);
            registro.setUsuario(usuario);

            // Calcula los costos para la auditoría usando el precio que acaba de ingresar
            Double precioFinal = (precio != null && precio > 0) ? precio : (insumo.getPrecioActual() != null ? insumo.getPrecioActual() : 0.0);
            registro.setPrecioUnitario(precioFinal);
            registro.setCostoTotal(cantidad * precioFinal);

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
    public Insumo consumirStock(@PathVariable Long id,
                                @RequestParam Double cantidad,
                                @RequestParam String fecha,
                                @RequestParam(required = false, defaultValue = "Sistema") String usuario) {

        return insumoRepository.findById(id).map(insumo -> {
            insumo.setStock(insumo.getStock() - cantidad);
            Insumo actualizado = insumoRepository.save(insumo);

            ConsumoInsumo consumo = new ConsumoInsumo();
            consumo.setInsumoNombre(insumo.getNombre());
            consumo.setCantidad(cantidad);
            consumo.setFechaConsumo(LocalDate.parse(fecha));

            // AQUÍ SE AGREGA EL USUARIO AL HISTORIAL
            consumo.setUsuario(usuario);

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
    public Insumo ajustarStock(@PathVariable Long id,
                               @RequestParam Double cantidad,
                               @RequestParam(required = false) Double precio, // NUEVO: Captura el precio si se envía
                               @RequestParam(required = false, defaultValue = "Sistema") String usuario) {

        return insumoRepository.findById(id).map(insumo -> {
            Double stockAnterior = insumo.getStock();

            // 1. Actualizamos el Stock Físico
            insumo.setStock(cantidad);

            // 2. NUEVO: Actualizamos el precio si la técnica ingresó uno válido en la ventanita
            if (precio != null && precio >= 0) {
                insumo.setPrecioActual(precio);
            }

            Insumo actualizado = insumoRepository.save(insumo);

            // 3. MANTENIDO: Registrar el ajuste como un movimiento de auditoría en el historial
            ConsumoInsumo auditoria = new ConsumoInsumo();
            auditoria.setInsumoNombre(insumo.getNombre() + " (Ajuste Manual)");
            auditoria.setCantidad(stockAnterior - cantidad); // Registra la diferencia matemática
            auditoria.setFechaConsumo(java.time.LocalDate.now());
            auditoria.setUsuario(usuario);
            consumoInsumoRepository.save(auditoria);

            return actualizado;
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

    @PostMapping("/editar-precio/{id}")
    public Insumo editarPrecio(@PathVariable Long id, @RequestParam Double nuevoPrecio) {
        return insumoRepository.findById(id).map(insumo -> {
            insumo.setPrecioActual(nuevoPrecio);
            return insumoRepository.save(insumo);
        }).orElse(null);
    }
    @PostMapping("/modificar-critico/{id}")
    public void modificarCritico(@PathVariable Long id, @RequestParam Double nuevoCritico) { // CAMBIO A DOUBLE
        insumoRepository.findById(id).ifPresent(i -> { i.setStockCritico(nuevoCritico); insumoRepository.save(i); });
    }
}