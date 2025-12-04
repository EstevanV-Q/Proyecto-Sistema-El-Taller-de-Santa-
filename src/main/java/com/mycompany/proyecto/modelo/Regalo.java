package com.mycompany.proyecto.modelo;

/**
 * Representa un regalo en el inventario del taller.
 * Contiene informacion del producto y controla la cantidad disponible.
 * 
 * @author Sistema Taller de Santa
 */
public class Regalo {
    private String codigo;
    private String nombre;
    private String descripcion;
    private String marca;
    private int cantidadDisponible;
    
    /**
     * Constructor por defecto
     */
    public Regalo() {
    }
    
    /**
     * Constructor con todos los parametros
     * @param codigo Codigo unico del regalo
     * @param nombre Nombre del regalo
     * @param descripcion Descripcion del producto
     * @param marca Marca del regalo
     * @param cantidadDisponible Cantidad disponible en inventario
     */
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
     * Descuenta una unidad del inventario.
     * Solo descuenta si hay disponibilidad mayor a cero.
     */
    public void descontar() {
        if (cantidadDisponible > 0) {
            cantidadDisponible--;
        }
    }
    
    /**
     * Reabastece el inventario agregando la cantidad especificada.
     * @param cantidad Cantidad a agregar (debe ser mayor a cero)
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

