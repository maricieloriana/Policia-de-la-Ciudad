package modelo;

import java.util.ArrayList;

/**
 * Representa un vigilante del sistema.
 * 
 * Contiene los datos identificatorios y personales del vigilante
 * utilizado en contrataciones y usuarios del sistema.
 */
public class Vigilante {

    private int codigo;
    private int edad;
    private ArrayList<Contratacion> contrataciones;

    public Vigilante() {
        contrataciones = new ArrayList<>();
    }

    public Vigilante(int codigo, int edad) {
        this.codigo = codigo;
        this.edad = edad;
        this.contrataciones = new ArrayList<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public int getEdad() {
        return edad;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public ArrayList<Contratacion> getContrataciones() {
        return contrataciones;
    }

    public void agregarContratacion(Contratacion contratacion) {
        contrataciones.add(contratacion);
    }

    public void eliminarContratacion(Contratacion contratacion) {
        contrataciones.remove(contratacion);
    }

    @Override
    public String toString() {
        return codigo + ";" + edad;
    }

}
