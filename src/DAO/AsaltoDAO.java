package DAO;

import DTO.AsaltoDTO;
import Util.ArchivoUtil;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Clase encargada de gestionar la persistencia de los asaltos.
 *
 * Permite registrar, consultar, actualizar y eliminar información relacionada
 * con los asaltos ocurridos en el sistema.
 */
public class AsaltoDAO {

    private final Path archivo = Paths.get("datos/asaltos.txt");

    /**
     * Guarda un asalto en el archivo de datos.
     *
     * @param asalto DTO con la información del asalto.
     */
    public void guardar(AsaltoDTO asalto) {
        String linea = asalto.getId() + ";" + asalto.getFecha() + ";" + asalto.getCodigoSucursal() + ";" + asalto.getCodigoPersona();
        ArchivoUtil.escribirLinea(archivo, linea);
    }

    /**
     * Obtiene todos los asaltos registrados.
     *
     * @return lista de asaltos en formato DTO.
     */
    public ArrayList<AsaltoDTO> listar() {
        ArrayList<AsaltoDTO> asaltos = new ArrayList<>();
        ArrayList<String> lineas = ArchivoUtil.leerArchivo(archivo);
        for (String linea : lineas) {
            String[] datos = linea.split(";");
            AsaltoDTO dto = new AsaltoDTO(
                    Integer.parseInt(datos[0]),
                    java.time.LocalDate.parse(datos[1]),
                    Integer.parseInt(datos[2]),
                    Integer.parseInt(datos[3]));
            asaltos.add(dto);
        }
        return asaltos;
    }

    /**
     * Busca un asalto mediante su identificador.
     *
     * @param id identificador del asalto.
     * @return DTO del asalto encontrado o null si no existe.
     */
    public AsaltoDTO buscarPorId(int id) {
        for (AsaltoDTO dto : listar()) {
            if (dto.getId() == id) {
                return dto;
            }
        }
        return null;
    }

    /**
     * Elimina un asalto del archivo de datos.
     *
     * @param id identificador del asalto que se desea eliminar.
     */
    public void eliminar(int id) {
        ArrayList<String> nuevasLineas
                = new ArrayList<>();
        for (AsaltoDTO dto : listar()) {
            if (dto.getId() != id) {
                nuevasLineas.add(dto.getId() + ";" + dto.getFecha() + ";" + dto.getCodigoSucursal() + ";" + dto.getCodigoPersona());
            }
        }
        ArchivoUtil.sobrescribirArchivo(archivo, nuevasLineas);
    }

    /**
     * Actualiza los datos de un asalto existente.
     *
     * @param asalto DTO con la información actualizada.
     */
    public void actualizar(AsaltoDTO asalto) {
        ArrayList<String> nuevasLineas = new ArrayList<>();
        for (AsaltoDTO dto : listar()) {
            if (dto.getId() == asalto.getId()) {
                nuevasLineas.add(
                        asalto.getId()
                        + ";"
                        + asalto.getFecha()
                        + ";"
                        + asalto.getCodigoSucursal()
                        + ";"
                        + asalto.getCodigoPersona()
                );
            } else {
                nuevasLineas.add(
                        dto.getId()
                        + ";"
                        + dto.getFecha()
                        + ";"
                        + dto.getCodigoSucursal()
                        + ";"
                        + dto.getCodigoPersona());
            }
        }
        ArchivoUtil.sobrescribirArchivo(archivo, nuevasLineas);
    }
}
