package DAO;

import DTO.ContratacionDTO;
import Util.ArchivoUtil;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Clase encargada de gestionar la persistencia de las contrataciones.
 *
 * Permite almacenar, consultar, modificar y eliminar contrataciones utilizando
 * archivos de texto como medio de persistencia.
 */
public class ContratacionDAO {

    private final Path archivo = Paths.get("datos/contrataciones.txt");

    /**
     * Guarda una contratación en el archivo de datos.
     *
     * @param contratacion DTO que contiene la información de la contratación.
     */
    public void guardar(ContratacionDTO contratacion) {
        String linea = contratacion.getId() + ";" + contratacion.getFecha() + ";" + contratacion.isConArma() + ";"
                + contratacion.getCodigoVigilante()
                + ";"
                + contratacion.getCodigoSucursal();
        ArchivoUtil.escribirLinea(archivo, linea);
    }

    /**
     * Obtiene todas las contrataciones registradas.
     *
     * @return lista de contrataciones en formato DTO.
     */
    public ArrayList<ContratacionDTO> listar() {
        ArrayList<ContratacionDTO> contrataciones = new ArrayList<>();
        ArrayList<String> lineas = ArchivoUtil.leerArchivo(archivo);

        for (String linea : lineas) {
            String[] datos = linea.split(";");
            ContratacionDTO dto = new ContratacionDTO(Integer.parseInt(datos[0]), java.time.LocalDate.parse(datos[1]),
                    Boolean.parseBoolean(datos[2]),
                    Integer.parseInt(datos[3]),
                    Integer.parseInt(datos[4]));
            contrataciones.add(dto);
        }
        return contrataciones;
    }

    /**
     * Busca una contratación mediante su identificador.
     *
     * @param id identificador de la contratación.
     * @return DTO de la contratación encontrada o null si no existe.
     */
    public ContratacionDTO buscarPorId(int id) {
        for (ContratacionDTO dto : listar()) {
            if (dto.getId() == id) {
                return dto;
            }
        }
        return null;
    }

    /**
     * Elimina una contratación del archivo de datos.
     *
     * @param id identificador de la contratación que se desea eliminar.
     */
    public void eliminar(int id) {
        ArrayList<String> nuevasLineas = new ArrayList<>();
        for (ContratacionDTO dto : listar()) {
            if (dto.getId() != id) {
                nuevasLineas.add(dto.getId() + ";" + dto.getFecha() + ";" + dto.isConArma() + ";" + dto.getCodigoVigilante()
                        + ";"
                        + dto.getCodigoSucursal()
                );
            }
        }
        ArchivoUtil.sobrescribirArchivo(archivo, nuevasLineas);
    }

    /**
     * Actualiza los datos de una contratación existente.
     *
     * @param contratacion DTO con la información actualizada.
     */
    public void actualizar(ContratacionDTO contratacion) {
        ArrayList<String> nuevasLineas = new ArrayList<>();
        for (ContratacionDTO dto : listar()) {
            if (dto.getId() == contratacion.getId()) {
                nuevasLineas.add(
                        contratacion.getId()
                        + ";"
                        + contratacion.getFecha()
                        + ";"
                        + contratacion.isConArma()
                        + ";"
                        + contratacion.getCodigoVigilante()
                        + ";"
                        + contratacion.getCodigoSucursal());
            } else {
                nuevasLineas.add(
                        dto.getId()
                        + ";"
                        + dto.getFecha()
                        + ";"
                        + dto.isConArma()
                        + ";"
                        + dto.getCodigoVigilante()
                        + ";"
                        + dto.getCodigoSucursal());
            }
        }

        ArchivoUtil.sobrescribirArchivo(archivo, nuevasLineas);

    }

}
