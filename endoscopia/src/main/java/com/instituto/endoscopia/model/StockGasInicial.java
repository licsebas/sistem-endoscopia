package com.instituto.endoscopia.model;
import jakarta.persistence.*;
@Entity
public class StockGasInicial {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mes;
    private String gas;
    private Double volumen;
    private String usuario;
    // Agrega los Getters y Setters
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getMes() { return mes; } public void setMes(String mes) { this.mes = mes; }
    public String getGas() { return gas; } public void setGas(String gas) { this.gas = gas; }
    public Double getVolumen() { return volumen; } public void setVolumen(Double volumen) { this.volumen = volumen; }
    public String getUsuario() { return usuario; } public void setUsuario(String usuario) { this.usuario = usuario; }
}