package modelo;

/**
 * Representa un usuario investigador del sistema.
 * 
 * Hereda los datos de Usuario y permite identificar usuarios
 * encargados de tareas de investigación.
 */
public class Investigador extends Usuario {
    public Investigador() {
    }


    public Investigador(String nombreUsuario, String password) {
        super(nombreUsuario, password);
    }


    
}
