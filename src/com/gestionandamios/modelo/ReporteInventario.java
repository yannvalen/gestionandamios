package com.gestionandamios.modelo;

import java.sql.Date;

public class ReporteInventario {

    private int idAndamio;
    private String tipo;
    private String estado;
    private String ubicacion;
    private Date fechaMantenimiento;
    private boolean disponible;

    public int getIdAndamio() { return idAndamio; }
    public void setIdAndamio(int idAndamio) { this.idAndamio = idAndamio; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public Date getFechaMantenimiento() { return fechaMantenimiento; }
    public void setFechaMantenimiento(Date fechaMantenimiento) { this.fechaMantenimiento = fechaMantenimiento; }

    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
}
