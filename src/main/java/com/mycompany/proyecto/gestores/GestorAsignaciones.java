package com.mycompany.proyecto.gestores;

import com.mycompany.proyecto.modelo.Asignacion;
import com.mycompany.proyecto.modelo.Regalo;
import com.mycompany.proyecto.utilidades.UtilidadesJSON;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestiona las asignaciones de regalos a ninos.
 * Controla que un nino solo tenga un regalo y descuenta automaticamente del inventario.
 * Los datos se persisten automaticamente en archivo JSON.
 * 
 * @author Sistema Taller de Santa
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
     * Asigna un regalo a un nino.
     * Valida que el nino no tenga regalo asignado y que haya disponibilidad.
     * Descuenta automaticamente una unidad del inventario.
     * 
     * @param identificacionNiño Identificacion del nino a asignar
     * @param regalo Regalo a asignar
     * @param gestorRegalos Gestor para descontar del inventario
     * @return true si se asigno exitosamente, false si ya tiene regalo o no hay disponibilidad
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
     * Busca la asignacion de un nino por su identificacion.
     * 
     * @param identificacion Identificacion del nino
     * @return Asignacion encontrada o null si no tiene asignacion
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
     * Verifica si un nino tiene una asignacion de regalo.
     * 
     * @param identificacion Identificacion del nino
     * @return true si tiene asignacion, false en caso contrario
     */
    public boolean tieneAsignacionPorIdentificacion(String identificacion) {
        return buscarPorIdentificacion(identificacion) != null;
    }
    
    /**
     * Verifica si un regalo tiene ninos asignados.
     * Util para validar antes de eliminar un regalo.
     * 
     * @param codigoRegalo Codigo del regalo a verificar
     * @return true si tiene asignaciones, false en caso contrario
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

