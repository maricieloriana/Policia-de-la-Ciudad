package DTO;

/**
 * Objeto utilizado para transportar los datos de un usuario asociado
 * a un vigilante.
 *
 * Contiene la información de autenticación del usuario y el código
 * del vigilante relacionado.
 *
 * Se utiliza para manejar específicamente usuarios de tipo VIGILANTE
 * dentro de la capa de transferencia de datos.
 */
public class UsuarioVigilanteDTO {

    private String usuario;
    private String password;
    private int codigoVigilante;

    public UsuarioVigilanteDTO() {
    }

    public UsuarioVigilanteDTO(String usuario, String password, int codigoVigilante) {
        this.usuario = usuario;
        this.password = password;
        this.codigoVigilante = codigoVigilante;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        return password;
    }

    public int getCodigoVigilante() {
        return codigoVigilante;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCodigoVigilante(int codigoVigilante) {
        this.codigoVigilante = codigoVigilante;
    }
}
