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
    private int stock;
    private int stockCritico;
    private String categoria;

    // Usamos esto para el borrado lógico (ocultar sin eliminar de la base de datos)
    private Boolean activo = true;

    // Constructor vacío obligatorio para Spring Boot / JPA
    public Insumo() {
    }

    // Constructor para inicializar rápidamente
    public Insumo(String nombre, int stock, int stockCritico, String categoria) {
        this.nombre = nombre;
        this.stock = stock;
        this.stockCritico = stockCritico;
        this.categoria = categoria;
        this.activo = true;
    }

    // ================= GETTERS Y SETTERS =================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStockCritico() {
        return stockCritico;
    }

    public void setStockCritico(int stockCritico) {
        this.stockCritico = stockCritico;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}