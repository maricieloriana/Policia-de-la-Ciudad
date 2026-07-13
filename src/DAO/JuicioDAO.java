package DAO;

import DTO.JuicioDTO;
import Util.ArchivoUtil;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import modelo.Juicio;

/**
 * Clase encargada de gestionar la persistencia de los juicios.
 *
 * Permite realizar operaciones CRUD sobre los juicios registrados, almacenando
 * la información mediante archivos de texto.
 */
public class JuicioDAO {

    private final Path archivo = Paths.get("datos/juicios.txt");

    /**
     * Guarda un juicio en el archivo de datos.
     *
     * @param juicio DTO que contiene la información del juicio.
     */
    public void guardar(JuicioDTO juicio) {

        String linea = juicio.getId() + ";" + juicio.isCondenado() + ";" + juicio.getAniosCarcel() + ";"
                + juicio.getCodigoPersona()
                + ";"
                + juicio.getClaveJuez();
        ArchivoUtil.escribirLinea(archivo, linea);
    }

    /**
     * Obtiene todos los juicios almacenados.
     *
     * @return lista de juicios en formato DTO.
     */
    public ArrayList<JuicioDTO> listar() {
        ArrayList<JuicioDTO> juicios = new ArrayList<>();
        ArrayList<String> lineas = ArchivoUtil.leerArchivo(archivo);
        for (String linea : lineas) {
            String[] datos = linea.split(";");
            JuicioDTO dto = new JuicioDTO(Integer.parseInt(datos[0]), Boolean.parseBoolean(datos[1]), Integer.parseInt(datos[2]), Integer.parseInt(datos[3]), Integer.parseInt(datos[4]));
            juicios.add(dto);
        }
        return juicios;
    }

    /**
     * Busca un juicio mediante su identificador.
     *
     * @param id identificador del juicio.
     * @return DTO del juicio encontrado o null si no existe.
     */
    public JuicioDTO buscarPorId(int id) {
        for (JuicioDTO dto : listar()) {
            if (dto.getId() == id) {
                return dto;
            }
        }
        return null;
    }

    /**
     * Elimina un juicio del archivo de datos.
     *
     * @param id identificador del juicio que se desea eliminar.
     */
    public void eliminar(int id) {
        ArrayList<String> nuevasLineas = new ArrayList<>();
        for (JuicioDTO dto : listar()) {
            if (dto.getId() != id) {
                nuevasLineas.add(dto.getId() + ";" + dto.isCondenado() + ";" + dto.getAniosCarcel() + ";"
                        + dto.getCodigoPersona()
                        + ";"
                        + dto.getClaveJuez());
            }
        }
        ArchivoUtil.sobrescribirArchivo(
                archivo,
                nuevasLineas
        );
    }

    /**
     * Actualiza la información de un juicio existente.
     *
     * @param juicio DTO con los datos actualizados del juicio.
     */
    public void actualizar(JuicioDTO juicio) {
        ArrayList<String> nuevasLineas = new ArrayList<>();
        for (JuicioDTO dto : listar()) {
            if (dto.getId() == juicio.getId()) {
                nuevasLineas.add(juicio.getId() + ";" + juicio.isCondenado() + ";" + juicio.getAniosCarcel() + ";"
                        + juicio.getCodigoPersona() + ";"
                        + juicio.getClaveJuez());
            } else {
                nuevasLineas.add(dto.getId() + ";" + dto.isCondenado() + ";" + dto.getAniosCarcel() + ";"
                        + dto.getCodigoPersona() + ";"
                        + dto.getClaveJuez());
            }
        }
        ArchivoUtil.sobrescribirArchivo(archivo, nuevasLineas);
    }

}
