package Util;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase utilitaria encargada de realizar operaciones sobre archivos de texto.
 *
 * Proporciona métodos para leer, escribir y sobrescribir archivos, facilitando
 * la persistencia de datos del sistema.
 */
public class ArchivoUtil {

    /**
     * Crea el archivo especificado si aún no existe.
     *
     * Si el archivo ya existe, el método no realiza ninguna acción.
     *
     * @param archivo ruta del archivo que se desea crear.
     */
    public static void crearArchivoSiNoExiste(Path archivo) {
        try {
            if (archivo.getParent() != null) {
                Files.createDirectories(archivo.getParent());
            }
            if (!Files.exists(archivo)) {
                Files.createFile(archivo);
            }
        } catch (IOException e) {
            System.out.println("Error al crear archivo: " + e.getMessage());
        }
    }

    /**
     * Agrega una línea al final del archivo indicado.
     *
     * @param archivo ruta del archivo.
     * @param linea información que se desea escribir.
     */
    public static void escribirLinea(Path archivo, String linea) {
        try {
            crearArchivoSiNoExiste(archivo);
            Files.writeString(
                    archivo,
                    linea + System.lineSeparator(),
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            System.out.println("Error al escribir archivo: " + e.getMessage());
        }
    }

    /**
     * Lee todas las líneas de un archivo de texto.
     *
     * @param archivo ruta del archivo que se desea leer.
     * @return lista con todas las líneas del archivo.
     */
    public static ArrayList<String> leerArchivo(Path archivo) {
        ArrayList<String> lineas = new ArrayList<>();
        try {
            if (Files.exists(archivo)) {
                lineas.addAll(
                        Files.readAllLines(archivo)
                );
            }
        } catch (IOException e) {
            System.out.println("Error al leer archivo: " + e.getMessage());
        }
        return lineas;
    }

    /**
     * Reemplaza completamente el contenido de un archivo.
     *
     * @param archivo ruta del archivo.
     * @param lineas lista de líneas que serán escritas en el archivo.
     */
    public static void sobrescribirArchivo(Path archivo, List<String> lineas) {
        try {
            crearArchivoSiNoExiste(archivo);
            Files.write(
                    archivo,
                    lineas,
                    StandardOpenOption.TRUNCATE_EXISTING
            );
        } catch (IOException e) {
            System.out.println("Error al sobrescribir archivo: " + e.getMessage());
        }
    }
}
