package com.mycompany.proyecto.modelo;

/**
 * Clase que representa un usuario del sistema
 */
public class Usuario {
    private String nombreUsuario;
    private String Contrasena;
    
    public Usuario() {
    }
    
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

