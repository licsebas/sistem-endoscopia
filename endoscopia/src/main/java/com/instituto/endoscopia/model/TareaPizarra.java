package com.instituto.endoscopia.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TareaPizarra {
    @Id
    private String idObs; // ID único que genera el sistema visualmente
    private String fecha;
    private String texto;
    private String estado; // "en_pizarra", "resuelta", "desestimada"
    private String comentario;

    // Getters y Setters
    public String getIdObs() { return idObs; }
    public void setIdObs(String idObs) { this.idObs = idObs; }
    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }
}