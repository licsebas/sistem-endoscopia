package com.instituto.endoscopia.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/control")
@CrossOrigin(origins = "*")
public class ControlController {

    // Aquí manejaremos más adelante la lógica para guardar
    // los Checklist Diarios de la sala.

    @GetMapping("/test")
    public String testControl() {
        return "El controlador de Control de Sala está funcionando correctamente.";
    }

}