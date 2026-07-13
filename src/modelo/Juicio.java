package modelo;

/**
 * Representa un juicio realizado sobre una persona detenida.
 * 
 * Mantiene información sobre la condena, cantidad de años de cárcel
 * y las referencias hacia la persona detenida y el juez responsable.
 */
public class Juicio {

    private int id;
    private boolean condenado;
    private int aniosCarcel;
    private PersonaDetenida persona;
    private Juez juez;

    public Juicio() {
    }

    public Juicio(int id, boolean condenado, int aniosCarcel,
            PersonaDetenida persona, Juez juez) {
        this.id = id;
        this.condenado = condenado;
        this.aniosCarcel = aniosCarcel;
        this.persona = persona;
        this.juez = juez;
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

    public PersonaDetenida getPersona() {
        return persona;
    }

    public Juez getJuez() {
        return juez;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCondenado(boolean condenado) {
        this.condenado = condenado;
    }

    public void setaniosCarcel(int aniosCarcel) {
        this.aniosCarcel = aniosCarcel;
    }

    public void setPersona(PersonaDetenida persona) {
        this.persona = persona;
    }

    public void setJuez(Juez juez) {
        this.juez = juez;
    }

    @Override
    public String toString() {
        return id + ";"
                + condenado + ";"
                + aniosCarcel + ";"
                + persona.getCodigo() + ";"
                + juez.getClave();
    }
}
