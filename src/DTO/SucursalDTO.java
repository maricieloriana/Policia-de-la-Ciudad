package DTO;

/**
 * Objeto utilizado para transportar la información de una sucursal.
 *
 * Contiene los datos necesarios para almacenar y recuperar una sucursal
 * junto con la referencia a la entidad bancaria asociada.
 */
public class SucursalDTO {

    private int codigo;
    private String domicilio;
    private int cantidadEmpleados;
    private int codigoEntidad;

    public SucursalDTO() {
    }

    public SucursalDTO(int codigo, String domicilio, int cantidadEmpleados, int codigoEntidad) {
        this.codigo = codigo;
        this.domicilio = domicilio;
        this.cantidadEmpleados = cantidadEmpleados;
        this.codigoEntidad = codigoEntidad;
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

    public int getCodigoEntidad() {
        return codigoEntidad;
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

    public void setCodigoEntidad(int codigoEntidad) {
        this.codigoEntidad = codigoEntidad;
    }
}
