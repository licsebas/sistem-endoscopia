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
            gasEstadoRepository.save(new GasEstado("O2 - 10 m3", 0.0, 0.0));
            gasEstadoRepository.save(new GasEstado("O2 - 6.4 m3", 0.0, 0.0));
            gasEstadoRepository.save(new GasEstado("CO2 - 33 kg", 0.0, 0.0)); // Agregado nuevamente
            gasEstadoRepository.save(new GasEstado("CO2 - 25 kg", 0.0, 0.0));
            gasEstadoRepository.save(new GasEstado("CO2 - 12.5 kg", 0.0, 0.0));
        }
    }

    public List<GasEstado> obtenerEstadoGases() {
        return gasEstadoRepository.findAll();
    }

    public void registrarIngreso(String tipo, Double llenosRecibidos, Double vaciosEntregados) {
        gasEstadoRepository.findByTipo(tipo).ifPresent(gas -> {
            // Sumamos los llenos que llegaron
            gas.setLlenos(gas.getLlenos() + llenosRecibidos);

            // Restamos los vacíos que se llevó el proveedor
            double nuevosVacios = gas.getVacios() - vaciosEntregados;

            // SEGURIDAD: Evitar que los vacíos queden en negativo
            if (nuevosVacios < 0) {
                nuevosVacios = 0.0;
            }

            gas.setVacios(nuevosVacios);
            gasEstadoRepository.save(gas);
        });
    }

    public void registrarConsumo(String tipo, Double cantidad, LocalDate fecha) {
        gasEstadoRepository.findByTipo(tipo).ifPresent(gas -> {
            // Restamos los llenos
            double nuevosLlenos = gas.getLlenos() - cantidad;

            // SEGURIDAD: Evitar que los llenos queden en negativo
            if (nuevosLlenos < 0) {
                nuevosLlenos = 0.0;
            }
            gas.setLlenos(nuevosLlenos);

            // Sumamos a los vacíos
            gas.setVacios(gas.getVacios() + cantidad);
            gasEstadoRepository.save(gas);

            // Guardamos en el historial general (para el reporte de consumos)
            ConsumoHistorial historial = new ConsumoHistorial();
            historial.setNombre(tipo);
            historial.setCategoria("Gases Médicos");
            historial.setCantidad(cantidad);
            historial.setFecha(fecha);
            consumoHistorialRepository.save(historial);
        });
    }

    // Nuevo método para forzar un stock exacto de gases
    public void ajustarStockGas(String tipo, Double llenos, Double vacios) {
        gasEstadoRepository.findByTipo(tipo).ifPresent(gas -> {
            gas.setLlenos(llenos);
            gas.setVacios(vacios);
            gasEstadoRepository.save(gas);
            System.out.println("Ajuste de Gas en BD: " + tipo + " - Llenos: " + llenos + " Vacios: " + vacios);
        });
    }

}