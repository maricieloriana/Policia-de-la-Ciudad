package DTO;

/**
 * Objeto utilizado para transportar los datos de un juez.
 *
 * Permite transferir la información del juez entre las capas del sistema.
 */
public class JuezDTO {

    private int clave;
    private String nombre;
    private int aniosServicio;

    public JuezDTO() {
    }

    public JuezDTO(int clave, String nombre, int aniosServicio) {
        this.clave = clave;
        this.nombre = nombre;
        this.aniosServicio = aniosServicio;
    }

    public int getClave() {
        return clave;
    }

    public String getNombre() {
        return nombre;
    }

    public int getAniosServicio() {
        return aniosServicio;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAniosServicio(int aniosServicio) {
        this.aniosServicio = aniosServicio;
    }
}
