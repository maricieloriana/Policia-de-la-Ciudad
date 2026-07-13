package modelo;

/**
 * Representa un usuario asociado a un vigilante.
 * 
 * Mantiene una referencia al vigilante correspondiente para
 * relacionar la cuenta del sistema con una persona registrada.
 */
public class UsuarioVigilante extends Usuario {
    private Vigilante vigilante;


    public UsuarioVigilante() {
    }


    public UsuarioVigilante(String nombreUsuario, String password, Vigilante vigilante) {
        super(nombreUsuario, password);
        this.vigilante = vigilante;
    }


    public Vigilante getVigilante() {
        return vigilante;
    }


    public void setVigilante(Vigilante vigilante) {
        this.vigilante = vigilante;
    }

}
