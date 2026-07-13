package modelo;

/**
 * Representa un usuario administrador del sistema.
 * 
 * Hereda los atributos básicos de Usuario y posee permisos
 * administrativos.
 */
public class Administrador extends Usuario {
    public Administrador() {
    }


    public Administrador(String nombreUsuario, String password) {
        super(nombreUsuario, password);
    }


   
}
