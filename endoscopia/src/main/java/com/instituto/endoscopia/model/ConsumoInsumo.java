package com.instituto.endoscopia.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class ConsumoInsumo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String insumoNombre;
    private Integer cantidad;
    private LocalDate fechaConsumo;

    public ConsumoInsumo() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getInsumoNombre() { return insumoNombre; }
    public void setInsumoNombre(String insumoNombre) { this.insumoNombre = insumoNombre; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public LocalDate getFechaConsumo() { return fechaConsumo; }
    public void setFechaConsumo(LocalDate fechaConsumo) { this.fechaConsumo = fechaConsumo; }
}