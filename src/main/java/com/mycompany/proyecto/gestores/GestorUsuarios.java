package com.mycompany.proyecto.gestores;

import com.mycompany.proyecto.modelo.Usuario;
import com.mycompany.proyecto.utilidades.UtilidadesJSON;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestiona las operaciones relacionadas con usuarios del sistema.
 * Maneja registro, autenticacion y persistencia en archivo JSON.
 * Los datos se cargan automaticamente al inicializar.
 * 
 * @author Sistema Taller de Santa
 */
public class GestorUsuarios {
    private static final String ARCHIVO_USUARIOS = "usuarios.json";
    private List<Usuario> usuarios;
    
    public GestorUsuarios() {
        cargarUsuarios();
    }
    
    /**
     * Carga los usuarios desde el archivo JSON al inicializar.
     * Si el archivo no existe, crea una lista vacia.
     */
    private void cargarUsuarios() {
        usuarios = UtilidadesJSON.cargarDesdeArchivo(ARCHIVO_USUARIOS, Usuario.class);
    }
    
    /**
     * Persiste la lista de usuarios en el archivo JSON.
     * Se ejecuta automaticamente despues de cada modificacion.
     */
    private void guardarUsuarios() {
        UtilidadesJSON.guardarEnArchivo(usuarios, ARCHIVO_USUARIOS);
    }
    
    /**
     * Registra un nuevo usuario en el sistema.
     * Valida que el nombre de usuario no exista y que los datos sean validos.
     * 
     * @param nombreUsuario Nombre unico del usuario
     * @param Contrasena Contrasena del usuario
     * @return true si se registro exitosamente, false si ya existe o datos invalidos
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
     * Valida las credenciales de un usuario para iniciar sesion.
     * 
     * @param nombreUsuario Nombre del usuario
     * @param Contrasena Contrasena a validar
     * @return true si las credenciales son correctas, false en caso contrario
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
     * Obtiene una copia de la lista de todos los usuarios registrados.
     * 
     * @return Lista de usuarios (copia para evitar modificaciones directas)
     */
    public List<Usuario> getUsuarios() {
        return new ArrayList<>(usuarios);
    }
}

