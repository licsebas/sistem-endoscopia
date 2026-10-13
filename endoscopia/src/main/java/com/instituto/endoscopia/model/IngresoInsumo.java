package com.instituto.endoscopia.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class IngresoInsumo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long insumoId;
    private String insumoNombre;

    // CAMBIO: Ahora es Double
    private Double cantidad;
    private String numeroRemito;
    // Agrega esta variable debajo de las otras (como cantidad, fechaIngreso, etc.)
    private String usuario;
    private Double precioUnitario; // El precio que tenía el insumo en ESE momento exacto
    private Double costoTotal;     // El resultado de (cantidad * precioUnitario)
    // Y sus getters y setters correspondientes:
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    private LocalDate fechaIngreso = LocalDate.now();
    private String numeroFactura;
    private java.time.LocalDate fechaFactura; // NUEVA VARIABLE PARA LA FECHA

    public IngresoInsumo() {}
    private String proveedorNombre; // NUEVA VARIABLE
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getInsumoId() { return insumoId; }
    public void setInsumoId(Long insumoId) { this.insumoId = insumoId; }

    public String getInsumoNombre() { return insumoNombre; }
    public void setInsumoNombre(String insumoNombre) { this.insumoNombre = insumoNombre; }

    public Double getCantidad() { return cantidad; }
    public void setCantidad(Double cantidad) { this.cantidad = cantidad; }

    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(LocalDate fechaIngreso) { this.fechaIngreso = fechaIngreso; }
    public Double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(Double precioUnitario) { this.precioUnitario = precioUnitario; }


    public String getProveedorNombre() { return proveedorNombre; }
    public void setProveedorNombre(String proveedorNombre) { this.proveedorNombre = proveedorNombre; }

    public java.time.LocalDate getFechaFactura() { return fechaFactura; }
    public void setFechaFactura(java.time.LocalDate fechaFactura) { this.fechaFactura = fechaFactura; }

    public Double getCostoTotal() { return costoTotal; }
    public void setCostoTotal(Double costoTotal) { this.costoTotal = costoTotal; }

    public String getNumeroRemito() { return numeroRemito; }
    public void setNumeroRemito(String numeroRemito) { this.numeroRemito = numeroRemito; }

    public void setNumeroFactura(String numeroFactura) {
    }
}