package com.instituto.endoscopia.model;
import jakarta.persistence.*;
@Entity
public class ConteoGas {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fecha;
    private String usuario;
    private Integer o2Llenos;
    private Integer o2Vacios;
    private Integer co2Llenos;
    private Integer co2Vacios;
    // Agrega los Getters y Setters
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getFecha() { return fecha; } public void setFecha(String fecha) { this.fecha = fecha; }
    public String getUsuario() { return usuario; } public void setUsuario(String usuario) { this.usuario = usuario; }
    public Integer getO2Llenos() { return o2Llenos; } public void setO2Llenos(Integer o2Llenos) { this.o2Llenos = o2Llenos; }
    public Integer getO2Vacios() { return o2Vacios; } public void setO2Vacios(Integer o2Vacios) { this.o2Vacios = o2Vacios; }
    public Integer getCo2Llenos() { return co2Llenos; } public void setCo2Llenos(Integer co2Llenos) { this.co2Llenos = co2Llenos; }
    public Integer getCo2Vacios() { return co2Vacios; } public void setCo2Vacios(Integer co2Vacios) { this.co2Vacios = co2Vacios; }
}