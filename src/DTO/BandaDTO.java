package DTO;

/**
 * Objeto utilizado para transportar los datos de una banda delictiva.
 *
 * Permite la comunicación entre la capa de acceso a datos y la lógica
 * del sistema.
 */
public class BandaDTO {

    private int numero;
    private int cantidadMiembros;

    public BandaDTO() {
    }

    public BandaDTO(int numero, int cantidadMiembros) {
        this.numero = numero;
        this.cantidadMiembros = cantidadMiembros;
    }

    public int getNumero() {
        return numero;
    }

    public int getCantidadMiembros() {
        return cantidadMiembros;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setCantidadMiembros(int cantidadMiembros) {
        this.cantidadMiembros = cantidadMiembros;
    }
}
