package DAO;

import DTO.EntidadBancariaDTO;
import Util.ArchivoUtil;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Clase encargada de gestionar la persistencia de las entidades bancarias.
 *
 * Realiza las operaciones de lectura, escritura, búsqueda, actualización y
 * eliminación utilizando archivos de texto como medio de almacenamiento.
 */
public class EntidadBancariaDAO {

    private final Path archivo = Paths.get("datos/entidades.txt");

    /**
     * Guarda una entidad bancaria en el archivo de datos.
     *
     * @param entidad DTO que contiene la información de la entidad bancaria.
     */
    public void guardar(EntidadBancariaDTO entidad) {
        String linea
                = entidad.getCodigo()
                + ";"
                + entidad.getDomicilioCentral();
        ArchivoUtil.escribirLinea(archivo, linea);
    }

    /**
     * Obtiene todas las entidades bancarias almacenadas.
     *
     * @return lista de entidades bancarias en formato DTO.
     */
    public ArrayList<EntidadBancariaDTO> listar() {
        ArrayList<EntidadBancariaDTO> entidades = new ArrayList<>();
        ArrayList<String> lineas = ArchivoUtil.leerArchivo(archivo);

        for (String linea : lineas) {
            String[] datos = linea.split(";");
            EntidadBancariaDTO entidad = new EntidadBancariaDTO(Integer.parseInt(datos[0]), datos[1]);
            entidades.add(entidad);
        }
        return entidades;
    }

    /**
     * Busca una entidad bancaria mediante su código identificador.
     *
     * @param codigo código de la entidad bancaria a buscar.
     * @return DTO de la entidad encontrada o null si no existe.
     */
    public EntidadBancariaDTO buscarPorCodigo(int codigo) {
        ArrayList<EntidadBancariaDTO> entidades = listar();
        for (EntidadBancariaDTO entidad : entidades) {
            if (entidad.getCodigo() == codigo) {
                return entidad;
            }
        }
        return null;
    }

    /**
     * Elimina una entidad bancaria del archivo de datos.
     *
     * @param codigo código de la entidad bancaria que se desea eliminar.
     */
    public void eliminar(int codigo) {
        ArrayList<String> nuevasLineas = new ArrayList<>();
        ArrayList<EntidadBancariaDTO> entidades = listar();
        for (EntidadBancariaDTO entidad : entidades) {
            if (entidad.getCodigo() != codigo) {
                nuevasLineas.add(entidad.getCodigo() + ";" + entidad.getDomicilioCentral());
            }
        }
        ArchivoUtil.sobrescribirArchivo(archivo, nuevasLineas);
    }

    /**
     * Actualiza los datos de una entidad bancaria existente.
     *
     * @param entidad DTO con la información actualizada.
     */
    public void actualizar(EntidadBancariaDTO entidad) {
        ArrayList<String> nuevasLineas = new ArrayList<>();
        for (EntidadBancariaDTO dto : listar()) {
            if (dto.getCodigo() == entidad.getCodigo()) {
                nuevasLineas.add(entidad.getCodigo() + ";" + entidad.getDomicilioCentral());
            } else {
                nuevasLineas.add(dto.getCodigo() + ";" + dto.getDomicilioCentral());
            }
        }
        ArchivoUtil.sobrescribirArchivo(archivo, nuevasLineas);
    }

}
