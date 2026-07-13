package DTO;

/**
 * Objeto utilizado para transportar los datos de un vigilante.
 *
 * Contiene la información necesaria para guardar y recuperar un vigilante
 * desde la capa de persistencia.
 */
public class VigilanteDTO {

    private int codigo;
    private int edad;

    public VigilanteDTO() {
    }

    public VigilanteDTO(int codigo, int edad) {
        this.codigo = codigo;
        this.edad = edad;
    }

    public int getCodigo() {
        return codigo;
    }

    public int getEdad() {
        return edad;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
