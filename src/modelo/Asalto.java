package modelo;

import java.time.LocalDate;

/**
 * Representa un asalto ocurrido en una sucursal bancaria.
 * 
 * Registra la fecha del hecho y las referencias a la sucursal afectada
 * y la persona detenida relacionada.
 */
public class Asalto {
    private int id;
    private LocalDate fecha;
    private Sucursal sucursal;
    private PersonaDetenida persona;

    public Asalto() {
    }

    public Asalto(int id, LocalDate fecha, Sucursal sucursal, PersonaDetenida persona) {
        this.id = id;
        this.fecha = fecha;
        this.sucursal = sucursal;
        this.persona = persona;
    }

    public int getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public PersonaDetenida getPersona() {
        return persona;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public void setPersona(PersonaDetenida persona) {
        this.persona = persona;
    }


    @Override
    public String toString() {
        return id + ";" + fecha + ";" + sucursal.getCodigo() + ";" + persona.getCodigo();
    }
}
