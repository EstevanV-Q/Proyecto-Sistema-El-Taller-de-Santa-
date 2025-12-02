package com.mycompany.proyecto.gestores;

import com.mycompany.proyecto.modelo.Asignacion;
import com.mycompany.proyecto.modelo.Regalo;
import com.mycompany.proyecto.utilidades.UtilidadesJSON;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase gestora para manejar asignaciones de regalos a niños
 */
public class GestorAsignaciones {
    private static final String ARCHIVO_ASIGNACIONES = "asignaciones.json";
    private List<Asignacion> asignaciones;
    
    public GestorAsignaciones() {
        cargarAsignaciones();
    }
    
    /**
     * Carga las asignaciones desde el archivo JSON
     */
    private void cargarAsignaciones() {
        asignaciones = UtilidadesJSON.cargarDesdeArchivo(ARCHIVO_ASIGNACIONES, Asignacion.class);
    }
    
    /**
     * Guarda las asignaciones en el archivo JSON
     */
    private void guardarAsignaciones() {
        UtilidadesJSON.guardarEnArchivo(asignaciones, ARCHIVO_ASIGNACIONES);
    }
    
    /**
     * Asigna un regalo a un niño
     */
    public boolean asignarRegalo(String identificacionNiño, Regalo regalo, GestorRegalos gestorRegalos) {
        if (identificacionNiño == null || identificacionNiño.trim().isEmpty()) {
            return false;
        }
        if (regalo == null) {
            return false;
        }
        
        // Verificar si el niño ya tiene un regalo asignado
        if (tieneAsignacionPorIdentificacion(identificacionNiño)) {
            return false; // Ya tiene un regalo asignado
        }
        
        // Verificar si hay disponibilidad
        if (regalo.getCantidadDisponible() <= 0) {
            return false; // No hay disponibilidad
        }
        
        // Crear la asignación
        Asignacion nuevaAsignacion = new Asignacion(
                identificacionNiño.trim(),
                regalo.getCodigo(),
                regalo.getNombre(),
                regalo.getMarca()
        );
        
        asignaciones.add(nuevaAsignacion);
        guardarAsignaciones();
        
        // Descontar del inventario
        gestorRegalos.descontarRegalo(regalo.getCodigo());
        
        return true;
    }
    
    /**
     * Busca asignaciones por identificación del niño
     */
    public Asignacion buscarPorIdentificacion(String identificacion) {
        for (Asignacion asignacion : asignaciones) {
            if (asignacion.getIdentificacionNiño().equals(identificacion)) {
                return asignacion;
            }
        }
        return null;
    }
    
    /**
     * Verifica si un niño tiene una asignación
     */
    public boolean tieneAsignacionPorIdentificacion(String identificacion) {
        return buscarPorIdentificacion(identificacion) != null;
    }
    
    /**
     * Verifica si un regalo tiene asignaciones
     */
    public boolean tieneAsignacionPorCodigoRegalo(String codigoRegalo) {
        for (Asignacion asignacion : asignaciones) {
            if (asignacion.getCodigoRegalo().equals(codigoRegalo)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Obtiene todas las asignaciones
     */
    public List<Asignacion> getAsignaciones() {
        return new ArrayList<>(asignaciones);
    }
    
    /**
     * Obtiene lista de identificaciones de niños sin asignación
     */
    public List<String> getNiñosConAsignacion() {
        List<String> resultado = new ArrayList<>();
        for (Asignacion asignacion : asignaciones) {
            resultado.add(asignacion.getIdentificacionNiño());
        }
        return resultado;
    }
}

