package com.mycompany.proyecto.modelo;

/**
 * Clase que representa un regalo en el inventario
 */
public class Regalo {
    private String codigo;
    private String nombre;
    private String descripcion;
    private String marca;
    private int cantidadDisponible;
    
    public Regalo() {
    }
    
    public Regalo(String codigo, String nombre, String descripcion, String marca, int cantidadDisponible) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.marca = marca;
        this.cantidadDisponible = cantidadDisponible;
    }
    
    public String getCodigo() {
        return codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getMarca() {
        return marca;
    }
    
    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    public int getCantidadDisponible() {
        return cantidadDisponible;
    }
    
    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }
    
    /**
     * Descuenta una unidad del inventario
     */
    public void descontar() {
        if (cantidadDisponible > 0) {
            cantidadDisponible--;
        }
    }
    
    /**
     * Reabastece el inventario con la cantidad especificada
     */
    public void reabastecer(int cantidad) {
        if (cantidad > 0) {
            cantidadDisponible += cantidad;
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Regalo regalo = (Regalo) obj;
        return codigo != null && codigo.equals(regalo.codigo);
    }
    
    @Override
    public int hashCode() {
        return codigo != null ? codigo.hashCode() : 0;
    }
    
    @Override
    public String toString() {
        return String.format("Código: %s | Nombre: %s | Marca: %s | Disponible: %d", 
                codigo, nombre, marca, cantidadDisponible);
    }
}

