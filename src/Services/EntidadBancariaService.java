package Services;

import DAO.EntidadBancariaDAO;
import DTO.EntidadBancariaDTO;
import java.util.ArrayList;
import modelo.EntidadBancaria;

/**
 * Clase encargada de gestionar la lógica de negocio relacionada con las
 * entidades bancarias.
 *
 * Se comunica con el DAO correspondiente para realizar operaciones de registro,
 * consulta, actualización y eliminación.
 */
public class EntidadBancariaService {

    private final EntidadBancariaDAO dao;

    public EntidadBancariaService() {
        dao = new EntidadBancariaDAO();
    }

    /**
     * Registra una nueva entidad bancaria verificando que no exista previamente
     * en el sistema.
     *
     * @param entidad objeto entidad bancaria que se desea registrar.
     */
    public void registrarEntidad(EntidadBancaria entidad) {
        if (entidad.getCodigo() <= 0) {
            System.out.println("Código inválido");
            return;
        }
        EntidadBancariaDTO existente = dao.buscarPorCodigo(entidad.getCodigo());
        if (existente != null) {
            System.out.println("La entidad ya existe");
            return;
        }
        EntidadBancariaDTO dto = new EntidadBancariaDTO(entidad.getCodigo(), entidad.getDomicilioCentral());
        dao.guardar(dto);
        System.out.println("Entidad registrada correctamente");
    }

    /**
     * Obtiene todas las entidades bancarias registradas.
     *
     * @return lista de entidades bancarias.
     */
    public ArrayList<EntidadBancaria> listarEntidades() {
        ArrayList<EntidadBancaria> entidades = new ArrayList<>();
        ArrayList<EntidadBancariaDTO> dtos = dao.listar();
        for (EntidadBancariaDTO dto : dtos) {
            EntidadBancaria entidad = new EntidadBancaria();
            entidad.setCodigo(dto.getCodigo());
            entidad.setDomicilioCentral(
                    dto.getDomicilioCentral());
            entidades.add(entidad);
        }
        return entidades;
    }

    /**
     * Busca una entidad bancaria por su código identificador.
     *
     * @param codigo código de la entidad bancaria.
     * @return entidad encontrada o null si no existe.
     */
    public EntidadBancaria buscarPorCodigo(int codigo) {
        EntidadBancariaDTO dto = dao.buscarPorCodigo(codigo);
        if (dto == null) {
            return null;
        }
        EntidadBancaria entidad = new EntidadBancaria();
        entidad.setCodigo(dto.getCodigo());
        entidad.setDomicilioCentral(dto.getDomicilioCentral());
        return entidad;
    }

    /**
     * Elimina una entidad bancaria existente.
     *
     * @param codigo código de la entidad bancaria a eliminar.
     */
    public void eliminarEntidad(int codigo) {
        EntidadBancariaDTO dto = dao.buscarPorCodigo(codigo);
        if (dto == null) {
            System.out.println("No existe la entidad");
            return;
        }
        dao.eliminar(codigo);
        System.out.println("Entidad eliminada");
    }

    /**
     * Actualiza los datos de una entidad bancaria existente.
     *
     * @param entidad entidad bancaria con los datos modificados.
     */
    public void actualizarEntidad(EntidadBancaria entidad) {
        if (dao.buscarPorCodigo(entidad.getCodigo()) == null) {
            System.out.println("La entidad no existe.");
            return;
        }
        EntidadBancariaDTO dto = new EntidadBancariaDTO(
                entidad.getCodigo(),
                entidad.getDomicilioCentral()
        );
        dao.actualizar(dto);
        System.out.println("Entidad actualizada correctamente.");
    }

}
