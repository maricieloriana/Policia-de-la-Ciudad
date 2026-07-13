package modelo;

import java.util.ArrayList;

/**
 * Representa una persona detenida dentro del sistema policial.
 * 
 * Puede estar asociada a una banda delictiva.
 */
public class PersonaDetenida {

    private int codigo;
    private String nombreCompleto;
    private Banda banda;
    private ArrayList<Asalto> asaltos;
    private ArrayList<Juicio> juicios;

    public PersonaDetenida() {
        asaltos = new ArrayList<>();
        juicios = new ArrayList<>();
    }

    public PersonaDetenida(int codigo, String nombreCompleto, Banda banda) {
        this.codigo = codigo;
        this.nombreCompleto = nombreCompleto;
        this.banda = banda;
        this.asaltos = new ArrayList<>();
        this.juicios = new ArrayList<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public Banda getBanda() {
        return banda;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setBanda(Banda banda) {
        this.banda = banda;
    }

    public ArrayList<Asalto> getAsaltos() {
        return asaltos;
    }

    public ArrayList<Juicio> getJuicios() {
        return juicios;
    }

    public void agregarAsalto(Asalto asalto) {
        if (!asaltos.contains(asalto)) {
            asaltos.add(asalto);
        }
    }

    public void eliminarAsalto(Asalto asalto) {
        asaltos.remove(asalto);
    }

    public void agregarJuicio(Juicio juicio) {
        if (!juicios.contains(juicio)) {
            juicios.add(juicio);
        }
    }

    public void eliminarJuicio(Juicio juicio) {
        juicios.remove(juicio);
    }

    @Override
    public String toString() {
        return codigo + ";" + nombreCompleto + ";" + (banda != null ? banda.getNumero() : "");
    }

}
