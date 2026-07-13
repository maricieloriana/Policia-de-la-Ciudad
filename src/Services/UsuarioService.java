package Services;

import DAO.UsuarioDAO;
import DTO.UsuarioDTO;
import java.util.ArrayList;
import modelo.*;

/**
 * Clase encargada de gestionar la lógica de negocio relacionada con los
 * usuarios del sistema.
 *
 * Administra usuarios de tipo Administrador, Investigador y UsuarioVigilante,
 * realizando las conversiones entre el modelo y los objetos DTO.
 */
public class UsuarioService {

    private final UsuarioDAO dao;
    private final VigilanteService vigilanteService;

    public UsuarioService() {
        this.dao = new UsuarioDAO();
        this.vigilanteService = new VigilanteService();
    }

    /**
     * Registra un nuevo usuario verificando que el nombre de usuario no exista
     * previamente.
     *
     * @param usuario usuario que se desea registrar.
     */
    public void registrarUsuario(Usuario usuario) {
        if (dao.buscarPorNombreUsuario(usuario.getNombreUsuario()) != null) {
            System.out.println("El usuario ya existe.");
            return;
        }

        String tipo = "";
        Integer codigoVigilante = null;

        if (usuario instanceof Administrador) {
            tipo = "ADMIN";

        } else if (usuario instanceof Investigador) {
            tipo = "INVESTIGADOR";

        } else if (usuario instanceof UsuarioVigilante) {
            tipo = "VIGILANTE";
            UsuarioVigilante uv = (UsuarioVigilante) usuario;

            if (uv.getVigilante() != null) {
                codigoVigilante = uv.getVigilante().getCodigo();
            }
        }
        UsuarioDTO dto = new UsuarioDTO(usuario.getNombreUsuario(), usuario.getPassword(), tipo, codigoVigilante);
        dao.guardar(dto);
        System.out.println("Usuario registrado correctamente.");

    }

    /**
     * Obtiene todos los usuarios registrados.
     *
     * Convierte los objetos DTO recuperados desde el DAO en objetos del modelo
     * correspondientes.
     *
     * @return lista de usuarios del sistema.
     */
    public ArrayList<Usuario> listarUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        for (UsuarioDTO dto : dao.listar()) {
            Usuario usuario = convertirDTO(dto);
            if (usuario != null) {
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }

    /**
     * Busca un usuario mediante su nombre de usuario.
     *
     * @param nombreUsuario nombre del usuario.
     * @return usuario encontrado o null si no existe.
     */
    public Usuario buscarPorNombre(String nombreUsuario) {
        UsuarioDTO dto
                = dao.buscarPorNombreUsuario(nombreUsuario);
        if (dto == null) {
            return null;
        }
        return convertirDTO(dto);
    }

    /**
     * Convierte un objeto DTO en un objeto del modelo correspondiente.
     *
     * @param dto objeto de transferencia de datos.
     * @return objeto del modelo.
     */
    private Usuario convertirDTO(UsuarioDTO dto) {
        switch (dto.getTipo()) {
            case "ADMIN":
                return new Administrador(dto.getNombreUsuario(), dto.getPassword());
            case "INVESTIGADOR":
                return new Investigador(dto.getNombreUsuario(), dto.getPassword());
            case "VIGILANTE":
                if (dto.getCodigoVigilante() == null) {
                    System.out.println("El usuario vigilante no tiene vigilante asociado.");
                    return null;
                }
                Vigilante vigilante = vigilanteService.buscarPorCodigo(dto.getCodigoVigilante());
                return new UsuarioVigilante(dto.getNombreUsuario(), dto.getPassword(), vigilante);
            default:
                return null;
        }
    }

    /**
     * Elimina un usuario registrado.
     *
     * @param nombreUsuario nombre del usuario a eliminar.
     */
    public void eliminarUsuario(String nombreUsuario) {
        if (dao.buscarPorNombreUsuario(nombreUsuario) == null) {
            System.out.println("El usuario no existe.");
            return;
        }
        dao.eliminar(nombreUsuario);
        System.out.println("Usuario eliminado correctamente.");
    }

    /**
     * Actualiza la información de un usuario existente.
     *
     * @param usuario usuario con los datos modificados.
     */
    public void actualizarUsuario(Usuario usuario) {
        if (dao.buscarPorNombreUsuario(usuario.getNombreUsuario()) == null) {
            System.out.println("El usuario no existe.");
            return;
        }
        UsuarioDTO dto;
        if (usuario instanceof Administrador) {
            dto = new UsuarioDTO(
                    usuario.getNombreUsuario(),
                    usuario.getPassword(),
                    "ADMIN",
                    null
            );
        } else if (usuario instanceof Investigador) {
            dto = new UsuarioDTO(
                    usuario.getNombreUsuario(),
                    usuario.getPassword(),
                    "INVESTIGADOR",
                    null
            );
        } else {
            UsuarioVigilante uv = (UsuarioVigilante) usuario;
            dto = new UsuarioDTO(
                    usuario.getNombreUsuario(),
                    usuario.getPassword(),
                    "VIGILANTE",
                    uv.getVigilante().getCodigo());
            dto.setCodigoVigilante(uv.getVigilante().getCodigo());
        }
        dao.actualizar(dto);
        System.out.println("Usuario actualizado correctamente.");
    }

}
