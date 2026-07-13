package DTO;

import java.time.LocalDate;

/**
 * Objeto utilizado para transportar los datos de un asalto.
 *
 * Permite almacenar la información del hecho y las referencias hacia
 * la sucursal y la persona detenida involucradas.
 */
public class AsaltoDTO {

    private int id;
    private LocalDate fecha;
    private int codigoSucursal;
    private int codigoPersona;

    public AsaltoDTO() {
    }

    public AsaltoDTO(int id, LocalDate fecha, int codigoSucursal, int codigoPersona) {
        this.id = id;
        this.fecha = fecha;
        this.codigoSucursal = codigoSucursal;
        this.codigoPersona = codigoPersona;
    }

    public int getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public int getCodigoSucursal() {
        return codigoSucursal;
    }

    public int getCodigoPersona() {
        return codigoPersona;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setCodigoSucursal(int codigoSucursal) {
        this.codigoSucursal = codigoSucursal;
    }

    public void setCodigoPersona(int codigoPersona) {
        this.codigoPersona = codigoPersona;
    }
}
