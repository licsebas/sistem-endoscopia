package com.instituto.endoscopia.service;

import com.instituto.endoscopia.model.ControlDiario;
import com.instituto.endoscopia.model.Endoscopio;
import com.instituto.endoscopia.repository.ControlDiarioRepository;
import com.instituto.endoscopia.repository.EndoscopioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ControlService {

    @Autowired
    private EndoscopioRepository endoscopioRepository;

    @Autowired
    private ControlDiarioRepository controlDiarioRepository;

    // Lógica para Endoscopios
    public Endoscopio guardarEndoscopio(Endoscopio endoscopio) {
        return endoscopioRepository.save(endoscopio);
    }

    public List<Endoscopio> obtenerTodosLosEndoscopios() {
        return endoscopioRepository.findAll();
    }

    // NUEVO MÉTODO: Borrado Lógico para Endoscopios
    public void desactivarEndoscopio(Long id) {
        endoscopioRepository.findById(id).ifPresent(equipo -> {
            equipo.setActivo(false); // Lo marcamos como inactivo
            endoscopioRepository.save(equipo); // Guardamos el cambio
            System.out.println("Endoscopio desactivado lógicamente con ID: " + id);
        });
    }

    // Lógica para el Control Diario
    public ControlDiario guardarControl(ControlDiario control) {
        return controlDiarioRepository.save(control);
    }

    public List<ControlDiario> obtenerHistorialControles() {
        return controlDiarioRepository.findAll();
    }

}