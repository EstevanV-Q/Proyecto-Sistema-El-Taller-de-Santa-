package com.mycompany.proyecto.modelo;

/**
 * Representa un usuario del sistema con sus credenciales de acceso.
 * Almacena el nombre de usuario y la contraseña para autenticacion.
 * 
 * @author Sistema Taller de Santa
 */
public class Usuario {
    private String nombreUsuario;
    private String Contrasena;
    
    /**
     * Constructor por defecto
     */
    public Usuario() {
    }
    
    /**
     * Constructor con parametros
     * @param nombreUsuario Nombre unico del usuario
     * @param Contrasena Contrasena del usuario
     */
    public Usuario(String nombreUsuario, String Contrasena) {
        this.nombreUsuario = nombreUsuario;
        this.Contrasena = Contrasena;
    }
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    public String getContrasena() {
        return Contrasena;
    }
    
    public void setContrasena(String Contrasena) {
        this.Contrasena = Contrasena;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Usuario usuario = (Usuario) obj;
        return nombreUsuario != null && nombreUsuario.equals(usuario.nombreUsuario);
    }
    
    @Override
    public int hashCode() {
        return nombreUsuario != null ? nombreUsuario.hashCode() : 0;
    }
}

