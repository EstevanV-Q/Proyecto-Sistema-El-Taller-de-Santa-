package com.mycompany.proyecto.gestores;

import com.mycompany.proyecto.modelo.Regalo;
import com.mycompany.proyecto.utilidades.UtilidadesJSON;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase gestora para manejar regalos del inventario
 */
public class GestorRegalos {
    private static final String ARCHIVO_REGALOS = "regalos.json";
    private List<Regalo> regalos;
    
    public GestorRegalos() {
        cargarRegalos();
    }
    
    /**
     * Carga los regalos desde el archivo JSON
     */
    private void cargarRegalos() {
        regalos = UtilidadesJSON.cargarDesdeArchivo(ARCHIVO_REGALOS, Regalo.class);
    }
    
    /**
     * Guarda los regalos en el archivo JSON
     */
    private void guardarRegalos() {
        UtilidadesJSON.guardarEnArchivo(regalos, ARCHIVO_REGALOS);
    }
    
    /**
     * Registra un nuevo regalo
     */
    public boolean registrarRegalo(String codigo, String nombre, String descripcion, String marca, int cantidad) {
        if (codigo == null || codigo.trim().isEmpty()) {
            return false;
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            return false;
        }
        if (cantidad < 0) {
            return false;
        }
        
        // Verificar si el código ya existe
        for (Regalo regalo : regalos) {
            if (regalo.getCodigo().equals(codigo)) {
                return false; // Código ya existe
            }
        }
        
        Regalo nuevoRegalo = new Regalo(codigo.trim(), nombre.trim(), 
                descripcion != null ? descripcion.trim() : "", 
                marca != null ? marca.trim() : "", cantidad);
        regalos.add(nuevoRegalo);
        guardarRegalos();
        return true;
    }
    
    /**
     * Modifica los datos de un regalo
     */
    public boolean modificarRegalo(String codigo, String nuevoNombre, String nuevaDescripcion, 
                                   String nuevaMarca, Integer nuevaCantidad) {
        Regalo regalo = buscarPorCodigo(codigo);
        if (regalo == null) {
            return false;
        }
        
        if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
            regalo.setNombre(nuevoNombre.trim());
        }
        if (nuevaDescripcion != null) {
            regalo.setDescripcion(nuevaDescripcion.trim());
        }
        if (nuevaMarca != null) {
            regalo.setMarca(nuevaMarca.trim());
        }
        if (nuevaCantidad != null && nuevaCantidad >= 0) {
            regalo.setCantidadDisponible(nuevaCantidad);
        }
        
        guardarRegalos();
        return true;
    }
    
    /**
     * Elimina un regalo del inventario (solo si no tiene niños asignados)
     */
    public boolean eliminarRegalo(String codigo, GestorAsignaciones gestorAsignaciones) {
        Regalo regalo = buscarPorCodigo(codigo);
        if (regalo == null) {
            return false;
        }
        
        // Verificar si tiene asignaciones
        if (gestorAsignaciones.tieneAsignacionPorCodigoRegalo(codigo)) {
            return false; // No se puede eliminar porque tiene asignaciones
        }
        
        regalos.remove(regalo);
        guardarRegalos();
        return true;
    }
    
    /**
     * Reabastece el inventario de un regalo
     */
    public boolean reabastecerInventario(String codigo, int cantidad) {
        Regalo regalo = buscarPorCodigo(codigo);
        if (regalo == null) {
            return false;
        }
        if (cantidad <= 0) {
            return false;
        }
        
        regalo.reabastecer(cantidad);
        guardarRegalos();
        return true;
    }
    
    /**
     * Busca un regalo por su código
     */
    public Regalo buscarPorCodigo(String codigo) {
        for (Regalo regalo : regalos) {
            if (regalo.getCodigo().equals(codigo)) {
                return regalo;
            }
        }
        return null;
    }
    
    /**
     * Obtiene todos los regalos
     */
    public List<Regalo> getRegalos() {
        return new ArrayList<>(regalos);
    }
    
    /**
     * Obtiene regalos por marca
     */
    public List<Regalo> getRegalosPorMarca(String marca) {
        List<Regalo> resultado = new ArrayList<>();
        for (Regalo regalo : regalos) {
            if (regalo.getMarca().equalsIgnoreCase(marca)) {
                resultado.add(regalo);
            }
        }
        return resultado;
    }
    
    /**
     * Descuenta una unidad del inventario de un regalo
     */
    public boolean descontarRegalo(String codigo) {
        Regalo regalo = buscarPorCodigo(codigo);
        if (regalo == null || regalo.getCantidadDisponible() <= 0) {
            return false;
        }
        regalo.descontar();
        guardarRegalos();
        return true;
    }
}

