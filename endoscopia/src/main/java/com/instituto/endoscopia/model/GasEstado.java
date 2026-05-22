package com.instituto.endoscopia.model;

import jakarta.persistence.*;

@Entity
public class GasEstado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;    // Ej: "O2 - 10 m3"
    private Integer llenos = 0;
    private Integer vacios = 0;

    // 1. Constructor vacío (Obligatorio para que la Base de Datos funcione)
    public GasEstado() {}

    // 2. NUEVO CONSTRUCTOR: Este es el que Java te estaba pidiendo
    public GasEstado(String tipo, Integer llenos, Integer vacios) {
        this.tipo = tipo;
        this.llenos = llenos;
        this.vacios = vacios;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public Integer getLlenos() { return llenos; }
    public void setLlenos(Integer llenos) { this.llenos = llenos; }

    public Integer getVacios() { return vacios; }
    public void setVacios(Integer vacios) { this.vacios = vacios; }
}