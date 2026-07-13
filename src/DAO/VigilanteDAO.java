package DAO;

import DTO.VigilanteDTO;
import Util.ArchivoUtil;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Clase encargada de gestionar la persistencia de los vigilantes.
 *
 * Permite almacenar y recuperar información de vigilantes mediante archivos de
 * texto.
 */
public class VigilanteDAO {

    private final Path archivo = Paths.get("datos/vigilantes.txt");

    /**
     * Guarda un vigilante en el archivo de datos.
     *
     * @param vigilante DTO con la información del vigilante a guardar.
     */
    public void guardar(VigilanteDTO vigilante) {
        String linea = vigilante.getCodigo() + ";" + vigilante.getEdad();
        ArchivoUtil.escribirLinea(archivo, linea);
    }

    /**
     * Obtiene todos los vigilantes registrados.
     *
     * @return lista de vigilantes en formato DTO.
     */
    public ArrayList<VigilanteDTO> listar() {
        ArrayList<VigilanteDTO> vigilantes = new ArrayList<>();
        ArrayList<String> lineas = ArchivoUtil.leerArchivo(archivo);
        for (String linea : lineas) {
            String[] datos = linea.split(";");
            VigilanteDTO vigilante = new VigilanteDTO(Integer.parseInt(datos[0]), Integer.parseInt(datos[1]));
            vigilantes.add(vigilante);
        }
        return vigilantes;
    }

    /**
     * Busca un vigilante por su código identificador.
     *
     * @param codigo código del vigilante a buscar.
     * @return DTO del vigilante encontrado o null si no existe.
     */
    public VigilanteDTO buscarPorCodigo(int codigo) {
        for (VigilanteDTO vigilante : listar()) {
            if (vigilante.getCodigo() == codigo) {
                return vigilante;
            }
        }
        return null;
    }

    /**
     * Elimina un vigilante del archivo de datos.
     *
     * @param codigo código del vigilante que se desea eliminar.
     */
    public void eliminar(int codigo) {
        ArrayList<String> nuevasLineas = new ArrayList<>();
        for (VigilanteDTO vigilante : listar()) {
            if (vigilante.getCodigo() != codigo) {
                nuevasLineas.add(vigilante.getCodigo() + ";" + vigilante.getEdad());
            }
        }
        ArchivoUtil.sobrescribirArchivo(archivo, nuevasLineas);
    }

    /**
     * Actualiza la información de un vigilante existente.
     *
     * @param vigilante DTO con los nuevos datos del vigilante.
     */
    public void actualizar(VigilanteDTO vigilante) {
        ArrayList<String> nuevasLineas = new ArrayList<>();
        for (VigilanteDTO dto : listar()) {
            if (dto.getCodigo() == vigilante.getCodigo()) {
                nuevasLineas.add(vigilante.getCodigo() + ";" + vigilante.getEdad());
            } else {
                nuevasLineas.add(dto.getCodigo() + ";" + dto.getEdad());
            }
        }
        ArchivoUtil.sobrescribirArchivo(archivo, nuevasLineas);
    }

}
