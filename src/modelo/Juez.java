package modelo;

import java.util.ArrayList;

/**
 * Representa un juez encargado de participar en juicios.
 * 
 * Contiene la clave identificadora, nombre y años de servicio
 * del juez.
 */
public class Juez {

    private int clave;
    private String nombre;
    private int aniosServicio;
    private ArrayList<Juicio> juicios;

    public Juez() {
        juicios = new ArrayList<>();
    }

    public Juez(int clave, String nombre, int aniosServicio) {
        this.clave = clave;
        this.nombre = nombre;
        this.aniosServicio = aniosServicio;
        this.juicios = new ArrayList<>();
    }

    public int getClave() {
        return clave;
    }

    public String getNombre() {
        return nombre;
    }

    public int getAniosServicio() {
        return aniosServicio;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAniosServicio(int aniosServicio) {
        this.aniosServicio = aniosServicio;
    }

    public ArrayList<Juicio> getJuicios() {
        return juicios;
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
        return clave + ";" + nombre + ";" + aniosServicio;
    }

}
