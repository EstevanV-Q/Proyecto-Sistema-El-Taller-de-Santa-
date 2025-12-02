package com.mycompany.proyecto.modelo;

/**
 * Clase que representa la asignación de un regalo a un niño
 */
public class Asignacion {
    private String identificacionNiño;
    private String codigoRegalo;
    private String nombreRegalo;
    private String marcaRegalo;
    
    public Asignacion() {
    }
    
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

