package DAO;

import DTO.UsuarioDTO;
import Util.ArchivoUtil;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Clase encargada de gestionar la persistencia de los usuarios del sistema.
 *
 * Administra el almacenamiento de usuarios de distintos tipos: Administrador,
 * Investigador y UsuarioVigilante.
 *
 * Utiliza archivos de texto para guardar y recuperar la información.
 */
public class UsuarioDAO {

    private final Path archivo = Paths.get("datos/usuarios.txt");

    /**
     * Guarda un usuario en el archivo de datos.
     *
     * El método permite almacenar usuarios de distintos tipos, incluyendo la
     * relación con un vigilante cuando corresponde.
     *
     * @param usuario DTO que contiene la información del usuario.
     */
    public void guardar(UsuarioDTO usuario) {
        String linea = usuario.getTipo() + ";" + usuario.getNombreUsuario() + ";" + usuario.getPassword() + ";"
                + (usuario.getCodigoVigilante() != null
                ? usuario.getCodigoVigilante()
                : "");
        ArchivoUtil.escribirLinea(archivo, linea);
    }

    /**
     * Obtiene todos los usuarios registrados.
     *
     * Convierte los datos almacenados en el archivo a objetos DTO.
     *
     * @return lista de usuarios en formato DTO.
     */
    public ArrayList<UsuarioDTO> listar() {
        ArrayList<UsuarioDTO> usuarios = new ArrayList<>();
        ArrayList<String> lineas = ArchivoUtil.leerArchivo(archivo);
        for (String linea : lineas) {
            String[] datos = linea.split(";", -1);
            Integer codigoVigilante = null;
            if (datos.length > 3 && !datos[3].isEmpty()) {
                codigoVigilante = Integer.parseInt(datos[3]);
            }
            UsuarioDTO dto
                    = new UsuarioDTO(
                            datos[1],
                            datos[2],
                            datos[0],
                            codigoVigilante);
            usuarios.add(dto);
        }
        return usuarios;
    }

    /**
     * Busca un usuario mediante su nombre de usuario.
     *
     * @param nombreUsuario nombre del usuario que se desea buscar.
     * @return DTO del usuario encontrado o null si no existe.
     */
    public UsuarioDTO buscarPorNombreUsuario(String nombreUsuario) {
        for (UsuarioDTO dto : listar()) {
            if (dto.getNombreUsuario().equals(nombreUsuario)) {
                return dto;
            }
        }
        return null;
    }

    /**
     * Elimina un usuario del archivo de datos.
     *
     * @param nombreUsuario nombre del usuario que se desea eliminar.
     */
    public void eliminar(String nombreUsuario) {
        ArrayList<String> nuevasLineas = new ArrayList<>();
        for (UsuarioDTO dto : listar()) {
            if (!dto.getNombreUsuario().equals(nombreUsuario)) {
                nuevasLineas.add(dto.getTipo() + ";" + dto.getNombreUsuario() + ";" + dto.getPassword() + ";"
                        + (dto.getCodigoVigilante() != null
                        ? dto.getCodigoVigilante()
                        : ""));
            }
        }
        ArchivoUtil.sobrescribirArchivo(archivo, nuevasLineas);
    }

    /**
     * Actualiza la información de un usuario existente.
     *
     * Permite modificar los datos de autenticación y la asociación con un
     * vigilante en caso de corresponder.
     *
     * @param usuario DTO con los datos actualizados del usuario.
     */
    public void actualizar(UsuarioDTO usuario) {
        ArrayList<String> nuevasLineas = new ArrayList<>();
        for (UsuarioDTO dto : listar()) {
            if (dto.getNombreUsuario().equals(usuario.getNombreUsuario())) {
                String codigoVigilante = "";
                if (usuario.getCodigoVigilante() != null) {
                    codigoVigilante = usuario.getCodigoVigilante().toString();
                }
                nuevasLineas.add(
                        usuario.getTipo()
                        + ";"
                        + usuario.getNombreUsuario()
                        + ";"
                        + usuario.getPassword()
                        + ";"
                        + codigoVigilante
                );
            } else {
                String codigoVigilante = "";

                if (dto.getCodigoVigilante() != null) {
                    codigoVigilante = dto.getCodigoVigilante().toString();
                }
                nuevasLineas.add(
                        dto.getTipo()
                        + ";"
                        + dto.getNombreUsuario()
                        + ";"
                        + dto.getPassword()
                        + ";"
                        + codigoVigilante);
            }
        }
        ArchivoUtil.sobrescribirArchivo(archivo, nuevasLineas);
    }

}
