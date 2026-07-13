package DTO;

/**
 * Objeto utilizado para transportar los datos de una persona detenida.
 *
 * Contiene la información necesaria para almacenar una persona y su
 * relación con una banda delictiva.
 */
public class PersonaDetenidaDTO {

    private int codigo;
    private String nombreCompleto;
    private Integer numeroBanda;

    public PersonaDetenidaDTO() {
    }

    public PersonaDetenidaDTO(int codigo, String nombreCompleto, Integer numeroBanda) {
        this.codigo = codigo;
        this.nombreCompleto = nombreCompleto;
        this.numeroBanda = numeroBanda;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public Integer getNumeroBanda() {
        return numeroBanda;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setNumeroBanda(Integer numeroBanda) {
        this.numeroBanda = numeroBanda;
    }
}
