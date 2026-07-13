package modelo;

import java.util.ArrayList;

/**
 * Representa una entidad bancaria dentro del sistema.
 * 
 * Contiene la información básica de la entidad y sirve
 * como referencia para las sucursales asociadas.
 */
public class EntidadBancaria {

    private int codigo;
    private String domicilioCentral;
    private ArrayList<Sucursal> sucursales;

    public EntidadBancaria() {
        this.sucursales = new ArrayList<>();
    }

    public EntidadBancaria(int codigo, String domicilioCentral, ArrayList<Sucursal> sucursales) {
        this.codigo = codigo;
        this.domicilioCentral = domicilioCentral;
        this.sucursales = sucursales;
    }

    public EntidadBancaria(int codigo, String domicilioCentral) {
        this.codigo = codigo;
        this.domicilioCentral = domicilioCentral;
        this.sucursales = new ArrayList<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDomicilioCentral() {
        return domicilioCentral;
    }

    public ArrayList<Sucursal> getSucursales() {
        return sucursales;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setDomicilioCentral(String domicilioCentral) {
        this.domicilioCentral = domicilioCentral;
    }

    public void setSucursales(ArrayList<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }

    public void agregarSucursal(Sucursal sucursal) {
        sucursales.add(sucursal);
    }

    public boolean eliminarSucursal(Sucursal sucursal) {
        return sucursales.remove(sucursal);
    }

    @Override
    public String toString() {
        return codigo + ";" + domicilioCentral;
    }

}
