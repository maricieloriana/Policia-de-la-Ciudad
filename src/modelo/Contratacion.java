package modelo;

import java.time.LocalDate;

/**
 * Representa la contratación de un vigilante para una sucursal.
 * 
 * Contiene la fecha de contratación, si posee arma y las referencias
 * al vigilante y la sucursal involucrados.
 */
public class Contratacion {

    private int id;
    private LocalDate fecha;
    private boolean conArma;

    private Vigilante vigilante;
    private Sucursal sucursal;

    public Contratacion() {
    }

    public Contratacion(int id, LocalDate fecha, boolean conArma,
            Vigilante vigilante, Sucursal sucursal) {

        this.id = id;
        this.fecha = fecha;
        this.conArma = conArma;
        this.vigilante = vigilante;
        this.sucursal = sucursal;
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

    public Vigilante getVigilante() {
        return vigilante;
    }

    public Sucursal getSucursal() {
        return sucursal;
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

    public void setVigilante(Vigilante vigilante) {
        this.vigilante = vigilante;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    @Override
    public String toString() {
        return id + ";" + fecha + ";" + conArma + ";" + vigilante.getCodigo() + ";" + sucursal.getCodigo();
    }
}
