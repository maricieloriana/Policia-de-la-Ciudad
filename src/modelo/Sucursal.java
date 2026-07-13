package modelo;

import java.util.ArrayList;

/**
 * Representa una sucursal bancaria perteneciente a una entidad.
 * 
 * Una sucursal mantiene una referencia hacia la entidad bancaria
 * a la que pertenece.
 */
public class Sucursal {

    private int codigo;
    private String domicilio;
    private int cantidadEmpleados;
    private EntidadBancaria entidad;
    private ArrayList<Contratacion> contrataciones;
    private ArrayList<Asalto> asaltos;

    public Sucursal() {
        contrataciones = new ArrayList<>();
        asaltos = new ArrayList<>();
    }

    public Sucursal(int codigo, String domicilio, int cantidadEmpleados, EntidadBancaria entidad) {
        this.codigo = codigo;
        this.domicilio = domicilio;
        this.cantidadEmpleados = cantidadEmpleados;
        this.entidad = entidad;
        contrataciones = new ArrayList<>();
        asaltos = new ArrayList<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public int getCantidadEmpleados() {
        return cantidadEmpleados;
    }

    public EntidadBancaria getEntidad() {
        return entidad;
    }

    public ArrayList<Contratacion> getContrataciones() {
        return contrataciones;
    }

    public ArrayList<Asalto> getAsaltos() {
        return asaltos;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public void setCantidadEmpleados(int cantidadEmpleados) {
        this.cantidadEmpleados = cantidadEmpleados;
    }

    public void setEntidad(EntidadBancaria entidad) {
        this.entidad = entidad;
    }

    public void agregarContratacion(Contratacion contratacion) {
        if(!contrataciones.contains(contratacion)){
            contrataciones.add(contratacion);
        }
    }

    public void eliminarContratacion(Contratacion contratacion) {
        contrataciones.remove(contratacion);
    }

    public void agregarAsalto(Asalto asalto) {
        if(!asaltos.contains(asalto)){
            asaltos.add(asalto);
        }
    }

    public void eliminarAsalto(Asalto asalto) {
        asaltos.remove(asalto);
    }

    @Override
    public String toString() {
        return codigo + ";" + domicilio + ";" + cantidadEmpleados + ";" + (entidad != null ? entidad.getCodigo() : "");
    }
       
}
