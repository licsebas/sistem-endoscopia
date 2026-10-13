package com.instituto.endoscopia.model;
import jakarta.persistence.*;

@Entity
public class ConsumoGas {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mes; // NUEVO: Periodo al que corresponde (Ej: 2023-10)
    private String fecha; // Fecha en que se midió el stock final
    private String gas;
    private Double inicial;
    private Double compras;
    private Double finalVol;
    private Double consumo;
    private String usuario;

    // Getters y Setters
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getMes() { return mes; } public void setMes(String mes) { this.mes = mes; } // NUEVO
    public String getFecha() { return fecha; } public void setFecha(String fecha) { this.fecha = fecha; }
    public String getGas() { return gas; } public void setGas(String gas) { this.gas = gas; }
    public Double getInicial() { return inicial; } public void setInicial(Double inicial) { this.inicial = inicial; }
    public Double getCompras() { return compras; } public void setCompras(Double compras) { this.compras = compras; }
    public Double getFinalVol() { return finalVol; } public void setFinalVol(Double finalVol) { this.finalVol = finalVol; }
    public Double getConsumo() { return consumo; } public void setConsumo(Double consumo) { this.consumo = consumo; }
    public String getUsuario() { return usuario; } public void setUsuario(String usuario) { this.usuario = usuario; }
}