package com.mycompany.proyecto.modelo;

/**
 * Representa la asignacion de un regalo a un nino.
 * Relaciona un nino con un regalo especifico del inventario.
 * 
 * @author Sistema Taller de Santa
 */
public class Asignacion {
    private String identificacionNiño;
    private String codigoRegalo;
    private String nombreRegalo;
    private String marcaRegalo;
    
    /**
     * Constructor por defecto
     */
    public Asignacion() {
    }
    
    /**
     * Constructor con todos los parametros
     * @param identificacionNiño Identificacion del nino asignado
     * @param codigoRegalo Codigo del regalo asignado
     * @param nombreRegalo Nombre del regalo
     * @param marcaRegalo Marca del regalo
     */
    public Asignacion(String identificacionNiño, String codigoRegalo, String nombreRegalo, String marcaRegalo) {
        this.identificacionNiño = identificacionNiño;
        this.codigoRegalo = codigoRegalo;
        this.nombreRegalo = nombreRegalo;
        this.marcaRegalo = marcaRegalo;
    }
    
    public String getIdentificacionNiño() {
        return identificacionNiño;
    }
    
    public void setIdentificacionNiño(String identificacionNiño) {
        this.identificacionNiño = identificacionNiño;
    }
    
    public String getCodigoRegalo() {
        return codigoRegalo;
    }
    
    public void setCodigoRegalo(String codigoRegalo) {
        this.codigoRegalo = codigoRegalo;
    }
    
    public String getNombreRegalo() {
        return nombreRegalo;
    }
    
    public void setNombreRegalo(String nombreRegalo) {
        this.nombreRegalo = nombreRegalo;
    }
    
    public String getMarcaRegalo() {
        return marcaRegalo;
    }
    
    public void setMarcaRegalo(String marcaRegalo) {
        this.marcaRegalo = marcaRegalo;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Asignacion asignacion = (Asignacion) obj;
        return identificacionNiño != null && identificacionNiño.equals(asignacion.identificacionNiño);
    }
    
    @Override
    public int hashCode() {
        return identificacionNiño != null ? identificacionNiño.hashCode() : 0;
    }
    
    @Override
    public String toString() {
        return String.format("Niño ID: %s | Regalo: %s (%s) - Marca: %s", 
                identificacionNiño, nombreRegalo, codigoRegalo, marcaRegalo);
    }
}

