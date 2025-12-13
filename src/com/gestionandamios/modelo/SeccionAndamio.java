package com.gestionandamios.modelo;

/**
 * Clase de Modelo (POJO) para representar una Seccion de Andamio.
 * Contiene los atributos que corresponden a las columnas de la tabla 
 * 'secciones_andamio' en la base de datos.
 */
public class SeccionAndamio {
    
    // Atributos que mapean a las columnas de la base de datos
    private int idSeccion; 
    private String codigoSeccion;
    private String tipo; 
    private double alturaMetros; 
    private String estado;      // Ejemplo: "Disponible", "Alquilada", "Mantenimiento"
    private String ubicacion;
    private double precio;

    // -------------------------------------------------------------------
    // 1. Constructor Vacío (necesario para la deserialización del DAO)
    // -------------------------------------------------------------------
    public SeccionAndamio() {
        // Constructor por defecto
    }
    
    // -------------------------------------------------------------------
    // 2. Getters y Setters (para acceder y modificar los atributos)
    // -------------------------------------------------------------------

    public int getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(int idSeccion) {
        this.idSeccion = idSeccion;
    }

    public String getCodigoSeccion() {
        return codigoSeccion;
    }

    public void setCodigoSeccion(String codigoSeccion) {
        this.codigoSeccion = codigoSeccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getAlturaMetros() {
        return alturaMetros;
    }

    public void setAlturaMetros(double alturaMetros) {
        this.alturaMetros = alturaMetros;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    /**
     * Opcional: Sobreescritura del método toString() para una mejor impresión en consola.
     */
    @Override
    public String toString() {
        return "SeccionAndamio{" + "idSeccion=" + idSeccion + ", codigoSeccion=" + codigoSeccion + ", tipo=" + tipo + ", alturaMetros=" + alturaMetros + ", estado=" + estado + ", ubicacion=" + ubicacion + ", precio=" + precio + '}';
    }
}