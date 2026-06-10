package com.instituto.endoscopia.controller;

import com.instituto.endoscopia.model.ConsumoGas;
import com.instituto.endoscopia.model.GasEstado;
import com.instituto.endoscopia.repository.ConsumoGasRepository;
import com.instituto.endoscopia.service.GasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/gases")
@CrossOrigin(origins = "*")
public class GasController {

    @Autowired
    private GasService gasService;

    @Autowired
    private ConsumoGasRepository consumoGasRepository;

    @GetMapping
    public List<GasEstado> obtenerGases() {
        return gasService.obtenerEstadoGases();
    }

    @PostMapping("/ingreso")
    public void registrarIngreso(@RequestParam String tipo,
                                 @RequestParam Double llenosRecibidos,
                                 @RequestParam Double vaciosEntregados,
                                 @RequestParam String fecha) {
        gasService.registrarIngreso(tipo, llenosRecibidos, vaciosEntregados);
    }

    @PostMapping("/consumo")
    public void consumirGas(@RequestParam String tipo,
                            @RequestParam Double cantidad,
                            @RequestParam String fecha) {

        // 1. Llama al Service pasando los 3 parámetros correctos (tipo, cantidad y fecha como LocalDate)
        gasService.registrarConsumo(tipo, cantidad, LocalDate.parse(fecha));

        // 2. Guarda el registro en la tabla específica para el reporte operativo de gases
        ConsumoGas historial = new ConsumoGas();
        historial.setTipo(tipo);
        historial.setCantidad(cantidad);
        historial.setFecha(LocalDate.parse(fecha));
        consumoGasRepository.save(historial);
    }

    @PostMapping("/ajustar")
    public void ajustarGas(@RequestParam String tipo,
                           @RequestParam Double llenos,
                           @RequestParam Double vacios) {
        gasService.ajustarStockGas(tipo, llenos, vacios);
    }

    @GetMapping("/reporte")
    public List<ConsumoGas> obtenerReporteGases(@RequestParam String inicio, @RequestParam String fin) {
        return consumoGasRepository.findByFechaBetween(
                LocalDate.parse(inicio),
                LocalDate.parse(fin)
        );
    }
}