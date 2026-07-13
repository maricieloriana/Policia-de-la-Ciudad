package DAO;

import DTO.BandaDTO;
import Util.ArchivoUtil;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Clase encargada de gestionar la persistencia de las bandas delictivas.
 *
 * Permite realizar operaciones CRUD sobre las bandas almacenadas mediante
 * archivos de texto.
 */
public class BandaDAO {

    private final Path archivo = Paths.get("datos/bandas.txt");

    /**
     * Guarda una banda en el archivo de datos.
     *
     * @param banda DTO que contiene la información de la banda a guardar.
     */
    public void guardar(BandaDTO banda) {
        String linea = banda.getNumero() + ";" + banda.getCantidadMiembros();
        ArchivoUtil.escribirLinea(archivo, linea);
    }

    /**
     * Obtiene todas las bandas registradas.
     *
     * @return lista de bandas en formato DTO.
     */
    public ArrayList<BandaDTO> listar() {
        ArrayList<BandaDTO> bandas = new ArrayList<>();
        ArrayList<String> lineas = ArchivoUtil.leerArchivo(archivo);
        for (String linea : lineas) {
            String[] datos = linea.split(";");
            BandaDTO banda = new BandaDTO(Integer.parseInt(datos[0]), Integer.parseInt(datos[1]));
            bandas.add(banda);
        }
        return bandas;
    }

    /**
     * Busca una banda mediante su código identificador.
     *
     * @param codigo código de la banda a buscar.
     * @return DTO de la banda encontrada o null si no existe.
     */
    public BandaDTO buscarPorNumero(int numero) {
        for (BandaDTO banda : listar()) {
            if (banda.getNumero() == numero) {
                return banda;
            }
        }
        return null;
    }

    /**
     * Elimina una banda del archivo de datos.
     *
     * @param codigo código de la banda que se desea eliminar.
     */
    public void eliminar(int numero) {
        ArrayList<String> nuevasLineas = new ArrayList<>();
        for (BandaDTO banda : listar()) {
            if (banda.getNumero() != numero) {
                nuevasLineas.add(banda.getNumero() + ";" + banda.getCantidadMiembros());
            }
        }
        ArchivoUtil.sobrescribirArchivo(archivo, nuevasLineas);
    }

    /**
     * Actualiza los datos de una banda existente.
     *
     * @param banda DTO con la información actualizada de la banda.
     */
    public void actualizar(BandaDTO banda) {
        ArrayList<String> nuevasLineas = new ArrayList<>();
        for (BandaDTO dto : listar()) {
            if (dto.getNumero() == banda.getNumero()) {
                nuevasLineas.add(banda.getNumero() + ";" + banda.getCantidadMiembros());
            } else {
                nuevasLineas.add(dto.getNumero() + ";" + dto.getCantidadMiembros());
            }
        }
        ArchivoUtil.sobrescribirArchivo(archivo, nuevasLineas);
    }

}
