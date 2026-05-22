package com.instituto.endoscopia.service;


import com.instituto.endoscopia.model.ConsumoHistorial;
import com.instituto.endoscopia.model.GasEstado;
import com.instituto.endoscopia.repository.ConsumoHistorialRepository;
import com.instituto.endoscopia.repository.GasEstadoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GasService {
    @Autowired
    private GasEstadoRepository gasEstadoRepository;

    @Autowired
    private ConsumoHistorialRepository consumoHistorialRepository;

    @PostConstruct
    public void inicializarGases() {
        if (gasEstadoRepository.count() == 0) {
            // Inicializa los 5 tipos de tubos con sus distintas capacidades [cite: 3258]
            gasEstadoRepository.save(new GasEstado("O2 - 10 m3", 0, 0));
            gasEstadoRepository.save(new GasEstado("O2 - 6 m3", 0, 0));
            gasEstadoRepository.save(new GasEstado("CO2 - 25 kg", 0, 0));
            gasEstadoRepository.save(new GasEstado("CO2 - 9 kg", 0, 0));
            gasEstadoRepository.save(new GasEstado("CO2 - 5 kg", 0, 0));
        }
    }

    public List<GasEstado> obtenerEstadoGases() {
        return gasEstadoRepository.findAll();
    }

    // Registra el ingreso calculando tubos llenos y vacíos por separado [cite: 3241]
    public void registrarIngreso(String tipo, int llenosRecibidos, int vaciosEntregados) {
        GasEstado gas = gasEstadoRepository.findByTipo(tipo).orElse(new GasEstado(tipo, 0, 0));
        if (gas != null) {
            gas.setLlenos(gas.getLlenos() + llenosRecibidos);
            gas.setVacios(gas.getVacios() - vaciosEntregados);
            gasEstadoRepository.save(gas);
        }
    }

    public void registrarConsumo(String tipo, int cantidad, LocalDate fecha) {
        GasEstado gas = gasEstadoRepository.findByTipo(tipo).orElse(new GasEstado(tipo, 0, 0));;
        if (gas != null) {
            gas.setLlenos(gas.getLlenos() - cantidad);
            gas.setVacios(gas.getVacios() + cantidad);
            gasEstadoRepository.save(gas);

            // Guarda el registro en el historial para las estadísticas [cite: 3219]
            ConsumoHistorial historial = new ConsumoHistorial();
            historial.setNombre(tipo);
            historial.setCategoria("Gases Médicos");
            historial.setCantidad(cantidad);
            historial.setFecha(fecha);
            consumoHistorialRepository.save(historial);
        }
    }



}
