package modelo;

/**
 * Clase base que representa un usuario del sistema.
 * 
 * Contiene los datos comunes de autenticación:
 * nombre de usuario y contraseña.
 */
public class Usuario {
  private String nombreUsuario;
    private String password;


    public Usuario() {
    }


    public Usuario(String nombreUsuario, String password) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
    }


    public String getNombreUsuario() {
        return nombreUsuario;
    }


    public String getPassword() {
        return password;
    }


    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }


    public void setPassword(String password) {
        this.password = password;
    }


     
}
