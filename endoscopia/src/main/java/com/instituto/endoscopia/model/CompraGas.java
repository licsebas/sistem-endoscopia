package com.instituto.endoscopia.model;
import jakarta.persistence.*;
@Entity
public class CompraGas {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fecha;
    private String remito;
    private String factura;
    private String proveedor;
    private String gas;
    private Double volumen;
    private Double precio;
    private String usuario;
    // Agrega los Getters y Setters
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getFecha() { return fecha; } public void setFecha(String fecha) { this.fecha = fecha; }
    public String getRemito() { return remito; } public void setRemito(String remito) { this.remito = remito; }
    public String getFactura() { return factura; } public void setFactura(String factura) { this.factura = factura; }
    public String getProveedor() { return proveedor; } public void setProveedor(String proveedor) { this.proveedor = proveedor; }
    public String getGas() { return gas; } public void setGas(String gas) { this.gas = gas; }
    public Double getVolumen() { return volumen; } public void setVolumen(Double volumen) { this.volumen = volumen; }
    public Double getPrecio() { return precio; } public void setPrecio(Double precio) { this.precio = precio; }
    public String getUsuario() { return usuario; } public void setUsuario(String usuario) { this.usuario = usuario; }
}