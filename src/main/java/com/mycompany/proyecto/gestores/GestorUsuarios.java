package com.mycompany.proyecto.gestores;

import com.mycompany.proyecto.modelo.Usuario;
import com.mycompany.proyecto.utilidades.UtilidadesJSON;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase gestora para manejar usuarios del sistema
 */
public class GestorUsuarios {
    private static final String ARCHIVO_USUARIOS = "usuarios.json";
    private List<Usuario> usuarios;
    
    public GestorUsuarios() {
        cargarUsuarios();
    }
    
    /**
     * Carga los usuarios desde el archivo JSON
     */
    private void cargarUsuarios() {
        usuarios = UtilidadesJSON.cargarDesdeArchivo(ARCHIVO_USUARIOS, Usuario.class);
    }
    
    /**
     * Guarda los usuarios en el archivo JSON
     */
    private void guardarUsuarios() {
        UtilidadesJSON.guardarEnArchivo(usuarios, ARCHIVO_USUARIOS);
    }
    
    /**
     * Registra un nuevo usuario
     */
    public boolean registrarUsuario(String nombreUsuario, String Contrasena) {
        if (nombreUsuario == null || nombreUsuario.trim().isEmpty()) {
            return false;
        }
        if (Contrasena == null || Contrasena.trim().isEmpty()) {
            return false;
        }
        
        // Verificar si el usuario ya existe
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario)) {
                return false; // Usuario ya existe
            }
        }
        
        Usuario nuevoUsuario = new Usuario(nombreUsuario.trim(), Contrasena);
        usuarios.add(nuevoUsuario);
        guardarUsuarios();
        return true;
    }
    
    /**
     * Valida las credenciales de un usuario
     */
    public boolean validarCredenciales(String nombreUsuario, String Contrasena) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario) && 
                usuario.getContrasena().equals(Contrasena)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Obtiene todos los usuarios
     */
    public List<Usuario> getUsuarios() {
        return new ArrayList<>(usuarios);
    }
}

