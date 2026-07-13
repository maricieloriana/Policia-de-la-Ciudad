package DAO;

import DTO.SucursalDTO;
import Util.ArchivoUtil;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Clase encargada de gestionar la persistencia de las sucursales.
 *
 * Maneja el almacenamiento de sucursales y sus relaciones con entidades
 * bancarias mediante archivos de texto.
 */
public class SucursalDAO {

    private final Path archivo = Paths.get("datos/sucursales.txt");

    /**
     * Guarda una sucursal en el archivo de datos.
     *
     * @param sucursal DTO con la información de la sucursal.
     */
    public void guardar(SucursalDTO sucursal) {
        String linea = sucursal.getCodigo() + ";" + sucursal.getDomicilio() + ";" + sucursal.getCantidadEmpleados() + ";" + sucursal.getCodigoEntidad();
        ArchivoUtil.escribirLinea(archivo, linea);
    }

    /**
     * Obtiene todas las sucursales almacenadas.
     *
     * @return lista de sucursales en formato DTO.
     */
    public ArrayList<SucursalDTO> listar() {
        ArrayList<SucursalDTO> sucursales = new ArrayList<>();
        ArrayList<String> lineas = ArchivoUtil.leerArchivo(archivo);
        for (String linea : lineas) {
            String[] datos = linea.split(";");
            SucursalDTO sucursal = new SucursalDTO(Integer.parseInt(datos[0]), datos[1], Integer.parseInt(datos[2]), Integer.parseInt(datos[3]));
            sucursales.add(sucursal);
        }
        return sucursales;
    }

    /**
     * Busca una sucursal mediante su código.
     *
     * @param codigo código identificador de la sucursal.
     * @return DTO de la sucursal encontrada o null si no existe.
     */
    public SucursalDTO buscarPorCodigo(int codigo) {
        ArrayList<SucursalDTO> sucursales = listar();
        for (SucursalDTO sucursal : sucursales) {
            if (sucursal.getCodigo() == codigo) {
                return sucursal;
            }
        }
        return null;
    }

    /**
     * Elimina una sucursal del archivo de datos.
     *
     * @param codigo código de la sucursal a eliminar.
     */
    public void eliminar(int codigo) {
        ArrayList<String> nuevasLineas = new ArrayList<>();
        ArrayList<SucursalDTO> sucursales = listar();
        for (SucursalDTO sucursal : sucursales) {
            if (sucursal.getCodigo() != codigo) {
                nuevasLineas.add(sucursal.getCodigo() + ";" + sucursal.getDomicilio() + ";" + sucursal.getCantidadEmpleados() + ";" + sucursal.getCodigoEntidad());
            }
        }
        ArchivoUtil.sobrescribirArchivo(archivo, nuevasLineas);
    }

    /**
     * Actualiza una sucursal existente.
     *
     * @param sucursal DTO con la información actualizada.
     */
    public void actualizar(SucursalDTO sucursal) {
        ArrayList<String> nuevasLineas = new ArrayList<>();
        for (SucursalDTO dto : listar()) {
            if (dto.getCodigo() == sucursal.getCodigo()) {
                nuevasLineas.add(sucursal.getCodigo() + ";" + sucursal.getDomicilio() + ";"
                        + sucursal.getCantidadEmpleados()
                        + ";"
                        + sucursal.getCodigoEntidad());
            } else {
                nuevasLineas.add(dto.getCodigo() + ";" + dto.getDomicilio() + ";" + dto.getCantidadEmpleados() + ";" + dto.getCodigoEntidad());
            }
        }
        ArchivoUtil.sobrescribirArchivo(archivo, nuevasLineas);
    }

}
