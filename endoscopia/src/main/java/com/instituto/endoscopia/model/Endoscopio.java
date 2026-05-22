package com.instituto.endoscopia.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Endoscopio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String alias;
    private String serie;
    private String tipo; // "Gastroscopio" o "Colonoscopio"
    private Boolean activo = true;
    private String estado = "Operativo";

    @ElementCollection
    private List<String> historialReparaciones = new ArrayList<>();

    public Endoscopio() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAlias() { return alias; }
    public void setAlias(String alias) { this.alias = alias; }

    public String getSerie() { return serie; }
    public void setSerie(String serie) { this.serie = serie; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public List<String> getHistorialReparaciones() { return historialReparaciones; }
    public void setHistorialReparaciones(List<String> historialReparaciones) { this.historialReparaciones = historialReparaciones; }
}