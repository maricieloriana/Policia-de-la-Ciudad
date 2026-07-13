package Services;

import DAO.VigilanteDAO;
import DTO.VigilanteDTO;
import modelo.Vigilante;
import java.util.ArrayList;

/**
 * Clase encargada de gestionar la lógica de negocio relacionada con los
 * vigilantes.
 *
 * Controla las validaciones necesarias antes de comunicarse con el DAO.
 */
public class VigilanteService {

    private final VigilanteDAO dao;

    public VigilanteService() {
        this.dao = new VigilanteDAO();
    }

    /**
     * Registra un nuevo vigilante validando que el código sea válido y que no
     * exista previamente.
     *
     * @param vigilante vigilante que se desea registrar.
     */
    public void registrarVigilante(Vigilante vigilante) {
        if (vigilante.getCodigo() <= 0) {
            System.out.println("Código inválido");
            return;
        }
        VigilanteDTO existente = dao.buscarPorCodigo(vigilante.getCodigo());
        if (existente != null) {
            System.out.println("El vigilante ya existe");
            return;
        }
        VigilanteDTO dto = new VigilanteDTO(
                vigilante.getCodigo(),
                vigilante.getEdad()
        );
        dao.guardar(dto);
        System.out.println("Vigilante registrado correctamente");
    }

    /**
     * Obtiene todos los vigilantes registrados en el sistema.
     *
     * @return lista de vigilantes.
     */
    public ArrayList<Vigilante> listarVigilantes() {
        ArrayList<Vigilante> vigilantes = new ArrayList<>();
        ArrayList<VigilanteDTO> dtos = dao.listar();
        for (VigilanteDTO dto : dtos) {
            Vigilante vigilante = new Vigilante();
            vigilante.setCodigo(dto.getCodigo());
            vigilante.setEdad(dto.getEdad());
            vigilantes.add(vigilante);
        }
        return vigilantes;
    }

    /**
     * Busca un vigilante mediante su código.
     *
     * @param codigo código identificador del vigilante.
     * @return vigilante encontrado o null si no existe.
     */
    public Vigilante buscarPorCodigo(int codigo) {
        VigilanteDTO dto = dao.buscarPorCodigo(codigo);
        if (dto == null) {
            return null;
        }
        Vigilante vigilante = new Vigilante();
        vigilante.setCodigo(dto.getCodigo());
        vigilante.setEdad(dto.getEdad());
        return vigilante;
    }

    /**
     * Elimina un vigilante existente.
     *
     * @param codigo código del vigilante a eliminar.
     */
    public void eliminarVigilante(int codigo) {
        VigilanteDTO dto = dao.buscarPorCodigo(codigo);
        if (dto == null) {
            System.out.println("El vigilante no existe");
            return;
        }
        dao.eliminar(codigo);
        System.out.println("Vigilante eliminado correctamente");
    }

    /**
     * Actualiza la información de un vigilante.
     *
     * @param vigilante vigilante con los datos modificados.
     */
    public void actualizarVigilante(Vigilante vigilante) {
        if (dao.buscarPorCodigo(vigilante.getCodigo()) == null) {
            System.out.println("El vigilante no existe.");
            return;
        }
        VigilanteDTO dto = new VigilanteDTO(vigilante.getCodigo(), vigilante.getEdad());
        dao.actualizar(dto);
        System.out.println("Vigilante actualizado correctamente.");
    }

}
