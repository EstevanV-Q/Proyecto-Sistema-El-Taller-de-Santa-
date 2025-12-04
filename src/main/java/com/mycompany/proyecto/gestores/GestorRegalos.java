package com.mycompany.proyecto.gestores;

import com.mycompany.proyecto.modelo.Regalo;
import com.mycompany.proyecto.utilidades.UtilidadesJSON;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestiona las operaciones relacionadas con regalos del inventario.
 * Maneja CRUD completo, reabastecimiento y consultas por codigo o marca.
 * Los datos se persisten automaticamente en archivo JSON.
 * 
 * @author Sistema Taller de Santa
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
     * Registra un nuevo regalo en el inventario.
     * Valida que el codigo sea unico y que los datos sean validos.
     * 
     * @param codigo Codigo unico del regalo
     * @param nombre Nombre del regalo
     * @param descripcion Descripcion del producto
     * @param marca Marca del regalo
     * @param cantidad Cantidad inicial disponible
     * @return true si se registro exitosamente, false si el codigo ya existe o datos invalidos
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
     * Modifica los datos de un regalo existente.
     * Los parametros null se ignoran (no se modifican).
     * 
     * @param codigo Codigo del regalo a modificar
     * @param nuevoNombre Nuevo nombre (null para mantener actual)
     * @param nuevaDescripcion Nueva descripcion (null para mantener actual)
     * @param nuevaMarca Nueva marca (null para mantener actual)
     * @param nuevaCantidad Nueva cantidad (null para mantener actual)
     * @return true si se modifico exitosamente, false si el regalo no existe
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
     * Elimina un regalo del inventario.
     * Solo permite eliminar si no tiene ninos asignados.
     * 
     * @param codigo Codigo del regalo a eliminar
     * @param gestorAsignaciones Gestor para verificar asignaciones
     * @return true si se elimino exitosamente, false si no existe o tiene asignaciones
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
     * Reabastece el inventario agregando cantidad a un regalo existente.
     * 
     * @param codigo Codigo del regalo a reabastecer
     * @param cantidad Cantidad a agregar (debe ser mayor a cero)
     * @return true si se reabastecio exitosamente, false si no existe o cantidad invalida
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
     * Busca un regalo por su codigo unico.
     * 
     * @param codigo Codigo del regalo a buscar
     * @return Regalo encontrado o null si no existe
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
     * Obtiene todos los regalos de una marca especifica.
     * La busqueda es case-insensitive (no distingue mayusculas/minusculas).
     * 
     * @param marca Marca a buscar
     * @return Lista de regalos de la marca especificada
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
     * Descuenta una unidad del inventario cuando se asigna un regalo.
     * Solo descuenta si hay disponibilidad.
     * 
     * @param codigo Codigo del regalo a descontar
     * @return true si se desconto exitosamente, false si no existe o no hay disponibilidad
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

