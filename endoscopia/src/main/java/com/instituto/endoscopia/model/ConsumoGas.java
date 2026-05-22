package com.instituto.endoscopia.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class ConsumoGas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;      // Ej: "O2 - 10 m3"
    private Integer cantidad;  // Cuántos tubos se vaciaron
    private LocalDate fecha;   // Qué día ocurrió

    public ConsumoGas() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
}