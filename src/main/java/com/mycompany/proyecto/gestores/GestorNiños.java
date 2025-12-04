package com.mycompany.proyecto.gestores;

import com.mycompany.proyecto.modelo.Niño;
import com.mycompany.proyecto.utilidades.UtilidadesJSON;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestiona las operaciones relacionadas con ninos registrados.
 * Maneja CRUD completo y validaciones de edad (0-18 años).
 * Los datos se persisten automaticamente en archivo JSON.
 * 
 * @author Sistema Taller de Santa
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
     * Registra un nuevo nino en el sistema.
     * Valida que la identificacion sea unica y que la edad sea valida (0-18).
     * 
     * @param identificacion Identificacion unica del nino
     * @param nombreCompleto Nombre completo del nino
     * @param edad Edad del nino (debe estar entre 0 y 18)
     * @param ciudad Ciudad de residencia
     * @param direccionDetallada Direccion completa para envio
     * @return true si se registro exitosamente, false si ya existe o datos invalidos
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
     * Modifica los datos de un nino existente.
     * Los parametros null se ignoran (no se modifican).
     * 
     * @param identificacion Identificacion del nino a modificar
     * @param nuevoNombre Nuevo nombre (null para mantener actual)
     * @param nuevaEdad Nueva edad (null para mantener actual, debe ser 0-18)
     * @param nuevaCiudad Nueva ciudad (null para mantener actual)
     * @param nuevaDireccion Nueva direccion (null para mantener actual)
     * @return true si se modifico exitosamente, false si el nino no existe
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
     * Elimina un nino del registro.
     * Solo permite eliminar si no tiene regalo asignado.
     * 
     * @param identificacion Identificacion del nino a eliminar
     * @param gestorAsignaciones Gestor para verificar asignaciones
     * @return true si se elimino exitosamente, false si no existe o tiene asignacion
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
     * Busca un nino por su identificacion unica.
     * 
     * @param identificacion Identificacion del nino a buscar
     * @return Nino encontrado o null si no existe
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

