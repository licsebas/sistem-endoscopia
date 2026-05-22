package com.instituto.endoscopia.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ControlDiario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaHora = LocalDateTime.now();
    private String usuarioResponsable;

    // Equipamiento de Sala
    private String pc;
    private String impresora;
    private String liquidoDesinfectante;
    private String lavadora;
    private String nivelO2;
    private String nivelCO2;
    private Integer frascosBiopsia;

    @Column(length = 1000)
    private String observacionesSala;

    // --- GASTROSCOPIO ---
    private Long gastroId;
    private String gastroAlias;
    private String gastroLuz;
    private String gastroMov;
    private String gastroFuga;
    private String gastroCanal;
    @Column(length = 500)
    private String gastroObs;

    // --- COLONOSCOPIO ---
    private Long colonoId;
    private String colonoAlias;
    private String colonoLuz;
    private String colonoMov;
    private String colonoFuga;
    private String colonoCanal;
    @Column(length = 500)
    private String colonoObs;

    public ControlDiario() {}

    // GETTERS Y SETTERS
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
    public String getUsuarioResponsable() { return usuarioResponsable; }
    public void setUsuarioResponsable(String usuarioResponsable) { this.usuarioResponsable = usuarioResponsable; }
    public String getPc() { return pc; }
    public void setPc(String pc) { this.pc = pc; }
    public String getImpresora() { return impresora; }
    public void setImpresora(String impresora) { this.impresora = impresora; }
    public String getLiquidoDesinfectante() { return liquidoDesinfectante; }
    public void setLiquidoDesinfectante(String liquidoDesinfectante) { this.liquidoDesinfectante = liquidoDesinfectante; }
    public String getLavadora() { return lavadora; }
    public void setLavadora(String lavadora) { this.lavadora = lavadora; }
    public String getNivelO2() { return nivelO2; }
    public void setNivelO2(String nivelO2) { this.nivelO2 = nivelO2; }
    public String getNivelCO2() { return nivelCO2; }
    public void setNivelCO2(String nivelCO2) { this.nivelCO2 = nivelCO2; }
    public Integer getFrascosBiopsia() { return frascosBiopsia; }
    public void setFrascosBiopsia(Integer frascosBiopsia) { this.frascosBiopsia = frascosBiopsia; }
    public String getObservacionesSala() { return observacionesSala; }
    public void setObservacionesSala(String observacionesSala) { this.observacionesSala = observacionesSala; }

    // Getters/Setters Gastro
    public Long getGastroId() { return gastroId; }
    public void setGastroId(Long gastroId) { this.gastroId = gastroId; }
    public String getGastroAlias() { return gastroAlias; }
    public void setGastroAlias(String gastroAlias) { this.gastroAlias = gastroAlias; }
    public String getGastroLuz() { return gastroLuz; }
    public void setGastroLuz(String gastroLuz) { this.gastroLuz = gastroLuz; }
    public String getGastroMov() { return gastroMov; }
    public void setGastroMov(String gastroMov) { this.gastroMov = gastroMov; }
    public String getGastroFuga() { return gastroFuga; }
    public void setGastroFuga(String gastroFuga) { this.gastroFuga = gastroFuga; }
    public String getGastroCanal() { return gastroCanal; }
    public void setGastroCanal(String gastroCanal) { this.gastroCanal = gastroCanal; }
    public String getGastroObs() { return gastroObs; }
    public void setGastroObs(String gastroObs) { this.gastroObs = gastroObs; }

    // Getters/Setters Colono
    public Long getColonoId() { return colonoId; }
    public void setColonoId(Long colonoId) { this.colonoId = colonoId; }
    public String getColonoAlias() { return colonoAlias; }
    public void setColonoAlias(String colonoAlias) { this.colonoAlias = colonoAlias; }
    public String getColonoLuz() { return colonoLuz; }
    public void setColonoLuz(String colonoLuz) { this.colonoLuz = colonoLuz; }
    public String getColonoMov() { return colonoMov; }
    public void setColonoMov(String colonoMov) { this.colonoMov = colonoMov; }
    public String getColonoFuga() { return colonoFuga; }
    public void setColonoFuga(String colonoFuga) { this.colonoFuga = colonoFuga; }
    public String getColonoCanal() { return colonoCanal; }
    public void setColonoCanal(String colonoCanal) { this.colonoCanal = colonoCanal; }
    public String getColonoObs() { return colonoObs; }
    public void setColonoObs(String colonoObs) { this.colonoObs = colonoObs; }
}