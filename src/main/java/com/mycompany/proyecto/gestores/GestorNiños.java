package com.mycompany.proyecto.gestores;

import com.mycompany.proyecto.modelo.Niño;
import com.mycompany.proyecto.utilidades.UtilidadesJSON;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase gestora para manejar niños registrados
 */
public class GestorNiños {
    private static final String ARCHIVO_NIÑOS = "niños.json";
    private List<Niño> niños;
    
    public GestorNiños() {
        cargarNiños();
    }
    
    /**
     * Carga los niños desde el archivo JSON
     */
    private void cargarNiños() {
        niños = UtilidadesJSON.cargarDesdeArchivo(ARCHIVO_NIÑOS, Niño.class);
    }
    
    /**
     * Guarda los niños en el archivo JSON
     */
    private void guardarNiños() {
        UtilidadesJSON.guardarEnArchivo(niños, ARCHIVO_NIÑOS);
    }
    
    /**
     * Registra un nuevo niño
     */
    public boolean registrarNiño(String identificacion, String nombreCompleto, 
                                 int edad, String ciudad, String direccionDetallada) {
        if (identificacion == null || identificacion.trim().isEmpty()) {
            return false;
        }
        if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
            return false;
        }
        if (edad < 0 || edad > 18) {
            return false;
        }
        if (ciudad == null || ciudad.trim().isEmpty()) {
            return false;
        }
        if (direccionDetallada == null || direccionDetallada.trim().isEmpty()) {
            return false;
        }
        
        // Verificar si la identificación ya existe
        for (Niño niño : niños) {
            if (niño.getIdentificacion().equals(identificacion)) {
                return false; // Identificación ya existe
            }
        }
        
        Niño nuevoNiño = new Niño(identificacion.trim(), nombreCompleto.trim(), 
                edad, ciudad.trim(), direccionDetallada.trim());
        niños.add(nuevoNiño);
        guardarNiños();
        return true;
    }
    
    /**
     * Modifica los datos de un niño
     */
    public boolean modificarNiño(String identificacion, String nuevoNombre, 
                                 Integer nuevaEdad, String nuevaCiudad, String nuevaDireccion) {
        Niño niño = buscarPorIdentificacion(identificacion);
        if (niño == null) {
            return false;
        }
        
        if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
            niño.setNombreCompleto(nuevoNombre.trim());
        }
        if (nuevaEdad != null && nuevaEdad >= 0 && nuevaEdad <= 18) {
            niño.setEdad(nuevaEdad);
        }
        if (nuevaCiudad != null && !nuevaCiudad.trim().isEmpty()) {
            niño.setCiudad(nuevaCiudad.trim());
        }
        if (nuevaDireccion != null && !nuevaDireccion.trim().isEmpty()) {
            niño.setDireccionDetallada(nuevaDireccion.trim());
        }
        
        guardarNiños();
        return true;
    }
    
    /**
     * Elimina un niño del registro (solo si no tiene regalo asignado)
     */
    public boolean eliminarNiño(String identificacion, GestorAsignaciones gestorAsignaciones) {
        Niño niño = buscarPorIdentificacion(identificacion);
        if (niño == null) {
            return false;
        }
        
        // Verificar si tiene asignación
        if (gestorAsignaciones.tieneAsignacionPorIdentificacion(identificacion)) {
            return false; // No se puede eliminar porque tiene asignación
        }
        
        niños.remove(niño);
        guardarNiños();
        return true;
    }
    
    /**
     * Busca un niño por su identificación
     */
    public Niño buscarPorIdentificacion(String identificacion) {
        for (Niño niño : niños) {
            if (niño.getIdentificacion().equals(identificacion)) {
                return niño;
            }
        }
        return null;
    }
    
    /**
     * Obtiene todos los niños
     */
    public List<Niño> getNiños() {
        return new ArrayList<>(niños);
    }
}

