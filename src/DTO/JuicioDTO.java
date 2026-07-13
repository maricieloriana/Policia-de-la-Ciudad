package DTO;

/**
 * Objeto utilizado para transportar los datos de un juicio.
 *
 * Contiene la información del juicio y las claves necesarias para
 * relacionarlo con una persona detenida y un juez.
 */
public class JuicioDTO {

    private int id;
    private boolean condenado;
    private int aniosCarcel;
    private int codigoPersona;
    private int claveJuez;

    public JuicioDTO() {
    }

    public JuicioDTO(int id, boolean condenado, int aniosCarcel, int codigoPersona, int claveJuez) {
        this.id = id;
        this.condenado = condenado;
        this.aniosCarcel = aniosCarcel;
        this.codigoPersona = codigoPersona;
        this.claveJuez = claveJuez;
    }

    public int getId() {
        return id;
    }

    public boolean isCondenado() {
        return condenado;
    }

    public int getAniosCarcel() {
        return aniosCarcel;
    }

    public int getCodigoPersona() {
        return codigoPersona;
    }

    public int getClaveJuez() {
        return claveJuez;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCondenado(boolean condenado) {
        this.condenado = condenado;
    }

    public void setTiempoCarcel(int aniosCarcel) {
        this.aniosCarcel = aniosCarcel;
    }

    public void setCodigoPersona(int codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public void setClaveJuez(int claveJuez) {
        this.claveJuez = claveJuez;
    }
}
