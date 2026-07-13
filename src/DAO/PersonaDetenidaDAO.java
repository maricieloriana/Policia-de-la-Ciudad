package DAO;

import DTO.PersonaDetenidaDTO;
import Util.ArchivoUtil;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Clase encargada de gestionar la persistencia de las personas detenidas.
 *
 * Administra el almacenamiento y recuperación de personas detenidas y sus
 * relaciones con bandas delictivas.
 */
public class PersonaDetenidaDAO {

    private final Path archivo = Paths.get("datos/personas_detenidas.txt");

    /**
     * Guarda una persona detenida en el archivo de datos.
     *
     * @param persona DTO con la información de la persona detenida.
     */
    public void guardar(PersonaDetenidaDTO persona) {
        String numeroBanda = "";
        if (persona.getNumeroBanda() != null) {
            numeroBanda = persona.getNumeroBanda().toString();
        }
        String linea = persona.getCodigo() + ";" + persona.getNombreCompleto() + ";" + numeroBanda;
        ArchivoUtil.escribirLinea(archivo, linea);
    }

    /**
     * Obtiene todas las personas detenidas almacenadas.
     *
     * @return lista de personas detenidas en formato DTO.
     */
    public ArrayList<PersonaDetenidaDTO> listar() {
        ArrayList<PersonaDetenidaDTO> personas = new ArrayList<>();
        ArrayList<String> lineas = ArchivoUtil.leerArchivo(archivo);
        for (String linea : lineas) {
            String[] datos = linea.split(";", -1);
            Integer numeroBanda = null;
            if (!datos[2].isEmpty()) {
                numeroBanda = Integer.parseInt(datos[2]);
            }
            PersonaDetenidaDTO dto = new PersonaDetenidaDTO(
                    Integer.parseInt(datos[0]),
                    datos[1],
                    numeroBanda);
            personas.add(dto);
        }
        return personas;
    }

    /**
     * Busca una persona detenida por su código identificador.
     *
     * @param codigo código de la persona detenida.
     * @return DTO de la persona encontrada o null si no existe.
     */
    public PersonaDetenidaDTO buscarPorCodigo(int codigo) {
        for (PersonaDetenidaDTO dto : listar()) {
            if (dto.getCodigo() == codigo) {
                return dto;
            }
        }
        return null;
    }

    /**
     * Elimina una persona detenida del archivo de datos.
     *
     * @param codigo código de la persona que se desea eliminar.
     */
    public void eliminar(int codigo) {
        ArrayList<String> nuevasLineas = new ArrayList<>();
        for (PersonaDetenidaDTO dto : listar()) {
            if (dto.getCodigo() != codigo) {
                String numeroBanda = "";
                if (dto.getNumeroBanda() != null) {
                    numeroBanda = dto.getNumeroBanda().toString();
                }
                nuevasLineas.add(dto.getCodigo() + ";" + dto.getNombreCompleto() + ";" + numeroBanda);
            }
        }
        ArchivoUtil.sobrescribirArchivo(archivo, nuevasLineas);
    }

    /**
     * Actualiza la información de una persona detenida existente.
     *
     * @param persona DTO con los datos actualizados.
     */
    public void actualizar(PersonaDetenidaDTO persona) {
        ArrayList<String> nuevasLineas = new ArrayList<>();
        for (PersonaDetenidaDTO dto : listar()) {
            if (dto.getCodigo() == persona.getCodigo()) {
                String numeroBanda = "";
                if (persona.getNumeroBanda() != null) {
                    numeroBanda = persona.getNumeroBanda().toString();
                }
                nuevasLineas.add(persona.getCodigo() + ";" + persona.getNombreCompleto() + ";" + numeroBanda);
            } else {
                String numeroBanda = "";
                if (dto.getNumeroBanda() != null) {
                    numeroBanda = dto.getNumeroBanda().toString();
                }
                nuevasLineas.add(dto.getCodigo() + ";" + dto.getNombreCompleto() + ";" + numeroBanda);
            }
        }
        ArchivoUtil.sobrescribirArchivo(archivo, nuevasLineas);
    }

}
