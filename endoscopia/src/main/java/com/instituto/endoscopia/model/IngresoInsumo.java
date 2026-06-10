package com.instituto.endoscopia.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class IngresoInsumo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long insumoId;
    private String insumoNombre;

    // CAMBIO: Ahora es Double
    private Double cantidad;
    private String numeroRemito;
    private LocalDate fechaIngreso = LocalDate.now();

    public IngresoInsumo() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getInsumoId() { return insumoId; }
    public void setInsumoId(Long insumoId) { this.insumoId = insumoId; }

    public String getInsumoNombre() { return insumoNombre; }
    public void setInsumoNombre(String insumoNombre) { this.insumoNombre = insumoNombre; }

    public Double getCantidad() { return cantidad; }
    public void setCantidad(Double cantidad) { this.cantidad = cantidad; }

    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(LocalDate fechaIngreso) { this.fechaIngreso = fechaIngreso; }

    public String getNumeroRemito() { return numeroRemito; }
    public void setNumeroRemito(String numeroRemito) { this.numeroRemito = numeroRemito; }
}