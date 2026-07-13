package DTO;

/**
 * Objeto utilizado para transportar los datos de una entidad bancaria
 * entre las distintas capas del sistema.
 *
 * Permite separar los datos almacenados de la lógica del modelo.
 */
public class EntidadBancariaDTO {

    private int codigo;
    private String domicilioCentral;

    public EntidadBancariaDTO() {
    }

    public EntidadBancariaDTO(int codigo, String domicilioCentral) {
        this.codigo = codigo;
        this.domicilioCentral = domicilioCentral;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDomicilioCentral() {
        return domicilioCentral;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setDomicilioCentral(String domicilioCentral) {
        this.domicilioCentral = domicilioCentral;
    }
}
