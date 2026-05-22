package com.instituto.endoscopia.controller;

import com.instituto.endoscopia.model.GasEstado;
import com.instituto.endoscopia.model.ConsumoGas;
import com.instituto.endoscopia.repository.GasEstadoRepository;
import com.instituto.endoscopia.repository.ConsumoGasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/gases")
@CrossOrigin(origins = "*")
public class GasController {

    @Autowired
    private GasEstadoRepository gasEstadoRepository;

    @Autowired
    private ConsumoGasRepository consumoGasRepository;

    @GetMapping
    public List<GasEstado> obtenerTodos() {
        return gasEstadoRepository.findAll();
    }

    @PostMapping("/ingreso")
    public void registrarIngreso(@RequestParam String tipo, @RequestParam Integer llenosRecibidos, @RequestParam Integer vaciosEntregados) {
        // AQUÍ ESTÁ LA MAGIA: .orElse(new GasEstado())
        GasEstado gas = gasEstadoRepository.findByTipo(tipo).orElse(new GasEstado(tipo, 0, 0));

        gas.setTipo(tipo);
        gas.setLlenos((gas.getLlenos() != null ? gas.getLlenos() : 0) + llenosRecibidos);

        int nuevosVacios = (gas.getVacios() != null ? gas.getVacios() : 0) - vaciosEntregados;
        gas.setVacios(Math.max(nuevosVacios, 0));

        gasEstadoRepository.save(gas);
    }

    @PostMapping("/consumo")
    public void registrarConsumo(@RequestParam String tipo, @RequestParam Integer cantidad, @RequestParam String fecha) {
        // AQUÍ TAMBIÉN
        GasEstado gas = gasEstadoRepository.findByTipo(tipo).orElse(new GasEstado(tipo, 0, 0));

        gas.setTipo(tipo);
        gas.setLlenos(gas.getLlenos() - cantidad);
        gas.setVacios(gas.getVacios() + cantidad);
        gasEstadoRepository.save(gas);

        ConsumoGas consumo = new ConsumoGas();
        consumo.setTipo(tipo);
        consumo.setCantidad(cantidad);
        consumo.setFecha(LocalDate.parse(fecha));
        consumoGasRepository.save(consumo);
    }

    @PostMapping("/ajustar")
    public void ajustarGasManual(@RequestParam String tipo, @RequestParam Integer llenos, @RequestParam Integer vacios) {
        // Y AQUÍ TAMBIÉN
        GasEstado gas = gasEstadoRepository.findByTipo(tipo).orElse(new GasEstado(tipo, 0, 0));

        gas.setTipo(tipo);
        gas.setLlenos(llenos);
        gas.setVacios(vacios);
        gasEstadoRepository.save(gas);
    }

    @GetMapping("/reporte")
    public List<ConsumoGas> obtenerReporte(@RequestParam String inicio, @RequestParam String fin) {
        LocalDate fechaInicio = LocalDate.parse(inicio);
        LocalDate fechaFin = LocalDate.parse(fin);
        return consumoGasRepository.findByFechaGreaterThanEqualAndFechaLessThanEqual(fechaInicio, fechaFin);
    }
}