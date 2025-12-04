package com.mycompany.proyecto.modelo;

/**
 * Representa un nino registrado para recibir regalos.
 * Almacena informacion personal y de ubicacion del nino.
 * 
 * @author Sistema Taller de Santa
 */
public class Niño {
    private String identificacion;
    private String nombreCompleto;
    private int edad;
    private String ciudad;
    private String direccionDetallada;
    
    /**
     * Constructor por defecto
     */
    public Niño() {
    }
    
    /**
     * Constructor con todos los parametros
     * @param identificacion Identificacion unica del nino
     * @param nombreCompleto Nombre completo del nino
     * @param edad Edad del nino (0-18)
     * @param ciudad Ciudad de residencia
     * @param direccionDetallada Direccion completa para envio
     */
    public Niño(String identificacion, String nombreCompleto, int edad, String ciudad, String direccionDetallada) {
        this.identificacion = identificacion;
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
        this.ciudad = ciudad;
        this.direccionDetallada = direccionDetallada;
    }
    
    public String getIdentificacion() {
        return identificacion;
    }
    
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
    
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    
    public int getEdad() {
        return edad;
    }
    
    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    public String getCiudad() {
        return ciudad;
    }
    
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
    public String getDireccionDetallada() {
        return direccionDetallada;
    }
    
    public void setDireccionDetallada(String direccionDetallada) {
        this.direccionDetallada = direccionDetallada;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Niño niño = (Niño) obj;
        return identificacion != null && identificacion.equals(niño.identificacion);
    }
    
    @Override
    public int hashCode() {
        return identificacion != null ? identificacion.hashCode() : 0;
    }
    
    @Override
    public String toString() {
        return String.format("ID: %s | Nombre: %s | Edad: %d | Ciudad: %s", 
                identificacion, nombreCompleto, edad, ciudad);
    }
}

