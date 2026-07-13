package DTO;

import java.time.LocalDate;

/**
 * Objeto utilizado para transportar los datos de una contratación.
 *
 * Contiene la información de la contratación y los códigos necesarios
 * para relacionarla con un vigilante y una sucursal.
 */
public class ContratacionDTO {

    private int id;
    private LocalDate fecha;
    private boolean conArma;
    private int codigoVigilante;
    private int codigoSucursal;

    public ContratacionDTO() {
    }

    public ContratacionDTO(int id, LocalDate fecha, boolean conArma, int codigoVigilante, int codigoSucursal) {
        this.id = id;
        this.fecha = fecha;
        this.conArma = conArma;
        this.codigoVigilante = codigoVigilante;
        this.codigoSucursal = codigoSucursal;
    }

    public int getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public boolean isConArma() {
        return conArma;
    }

    public int getCodigoVigilante() {
        return codigoVigilante;
    }

    public int getCodigoSucursal() {
        return codigoSucursal;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setConArma(boolean conArma) {
        this.conArma = conArma;
    }

    public void setCodigoVigilante(int codigoVigilante) {
        this.codigoVigilante = codigoVigilante;
    }

    public void setCodigoSucursal(int codigoSucursal) {
        this.codigoSucursal = codigoSucursal;
    }
}
