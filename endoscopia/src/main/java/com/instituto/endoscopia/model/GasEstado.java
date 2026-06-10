package com.instituto.endoscopia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class GasEstado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;
    private Double llenos; // Cambiado a Double
    private Double vacios; // Cambiado a Double

    public GasEstado() {}

    public GasEstado(String tipo, Double llenos, Double vacios) {
        this.tipo = tipo;
        this.llenos = llenos;
        this.vacios = vacios;
    }

    // Getters y Setters ASEGÚRATE que sean Double
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public Double getLlenos() { return llenos; }
    public void setLlenos(Double llenos) { this.llenos = llenos; }

    public Double getVacios() { return vacios; }
    public void setVacios(Double vacios) { this.vacios = vacios; }
}