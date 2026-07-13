package DAO;

import DTO.JuezDTO;
import Util.ArchivoUtil;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Clase encargada de gestionar la persistencia de los jueces.
 *
 * Permite realizar operaciones CRUD sobre los jueces registrados en el sistema
 * utilizando archivos de texto.
 */
public class JuezDAO {

    private final Path archivo = Paths.get("datos/jueces.txt");

    /**
     * Guarda un juez en el archivo de datos.
     *
     * @param juez DTO con la información del juez.
     */
    public void guardar(JuezDTO juez) {
        String linea = juez.getClave() + ";" + juez.getNombre() + ";" + juez.getAniosServicio();
        ArchivoUtil.escribirLinea(archivo, linea);
    }

    /**
     * Obtiene todos los jueces almacenados.
     *
     * @return lista de jueces en formato DTO.
     */
    public ArrayList<JuezDTO> listar() {
        ArrayList<JuezDTO> jueces = new ArrayList<>();
        ArrayList<String> lineas = ArchivoUtil.leerArchivo(archivo);

        for (String linea : lineas) {
            String[] datos = linea.split(";");
            JuezDTO dto = new JuezDTO(Integer.parseInt(datos[0]), datos[1], Integer.parseInt(datos[2]));
            jueces.add(dto);
        }
        return jueces;
    }

    /**
     * Busca un juez mediante su clave identificadora.
     *
     * @param clave clave del juez a buscar.
     * @return DTO del juez encontrado o null si no existe.
     */
    public JuezDTO buscarPorClave(int clave) {
        for (JuezDTO dto : listar()) {
            if (dto.getClave() == clave) {
                return dto;
            }
        }
        return null;
    }

    /**
     * Elimina un juez del archivo de datos.
     *
     * @param clave clave del juez que se desea eliminar.
     */
    public void eliminar(int clave) {
        ArrayList<String> nuevasLineas = new ArrayList<>();
        for (JuezDTO dto : listar()) {
            if (dto.getClave() != clave) {
                nuevasLineas.add(dto.getClave() + ";" + dto.getNombre() + ";" + dto.getAniosServicio());
            }
        }
        ArchivoUtil.sobrescribirArchivo(archivo, nuevasLineas);
    }

    /**
     * Actualiza la información de un juez existente.
     *
     * @param juez DTO con los nuevos datos del juez.
     */
    public void actualizar(JuezDTO juez) {
        ArrayList<String> nuevasLineas = new ArrayList<>();
        for (JuezDTO dto : listar()) {
            if (dto.getClave() == juez.getClave()) {
                nuevasLineas.add(juez.getClave() + ";" + juez.getNombre() + ";" + juez.getAniosServicio());
            } else {
                nuevasLineas.add(dto.getClave() + ";" + dto.getNombre() + ";" + dto.getAniosServicio());
            }
        }
        ArchivoUtil.sobrescribirArchivo(archivo, nuevasLineas);
    }

}
