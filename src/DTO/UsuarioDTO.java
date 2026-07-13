package DTO;

/**
 * Objeto utilizado para transportar los datos de los usuarios del sistema.
 *
 * Permite manejar los diferentes tipos de usuarios:
 * Administrador, Investigador y UsuarioVigilante.
 *
 * En caso de ser un UsuarioVigilante contiene el código del vigilante
 * asociado.
 */
public class UsuarioDTO {

    private String nombreUsuario;
    private String password;
    private String tipo;
    private Integer codigoVigilante;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String nombreUsuario, String password, String tipo, Integer codigoVigilante) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.tipo = tipo;
        this.codigoVigilante = codigoVigilante;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public String getTipo() {
        return tipo;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getCodigoVigilante() {
        return codigoVigilante;
    }

    public void setCodigoVigilante(Integer codigoVigilante) {
        this.codigoVigilante = codigoVigilante;
    }
     
}
