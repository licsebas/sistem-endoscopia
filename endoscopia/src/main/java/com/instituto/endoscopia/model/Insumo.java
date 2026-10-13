package com.instituto.endoscopia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Insumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    // CAMBIO: Ahora son Double para aceptar decimales
    private Double stock;
    private Double stockCritico;
    private String categoria;

    private Boolean activo = true;
    // AGREGA ESTAS VARIABLES DEBAJO DE LAS QUE YA TIENES (ej. nombre, stock, etc.)
    private Double precioActual = 0.0;
    private Long proveedorId;
    private String proveedorNombre;

    public Insumo() {
    }

    public Insumo(String nombre, Double stock, Double stockCritico, String categoria) {
        this.nombre = nombre;
        this.stock = stock != null ? stock : 0.0;
        this.stockCritico = stockCritico != null ? stockCritico : 0.0;
        this.categoria = categoria;
        this.activo = true;
    }

    // ================= GETTERS Y SETTERS =================

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Double getStock() { return stock; }
    public void setStock(Double stock) { this.stock = stock; }

    public Double getStockCritico() { return stockCritico; }
    public void setStockCritico(Double stockCritico) { this.stockCritico = stockCritico; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    // AGREGA ESTOS GETTERS Y SETTERS AL FINAL DEL ARCHIVO
    public Double getPrecioActual() { return precioActual; }
    public void setPrecioActual(Double precioActual) { this.precioActual = precioActual; }

    public Long getProveedorId() { return proveedorId; }
    public void setProveedorId(Long proveedorId) { this.proveedorId = proveedorId; }

    public String getProveedorNombre() { return proveedorNombre; }
    public void setProveedorNombre(String proveedorNombre) { this.proveedorNombre = proveedorNombre; }


    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
}