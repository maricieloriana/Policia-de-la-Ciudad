package Services;

import DAO.JuezDAO;
import DTO.JuezDTO;
import modelo.Juez;
import java.util.ArrayList;

/**
 * Clase encargada de gestionar la lógica de negocio relacionada con los jueces
 * del sistema.
 *
 * Realiza las validaciones necesarias antes de almacenar o modificar la
 * información.
 */
public class JuezService {

    private final JuezDAO dao;

    public JuezService() {
        this.dao = new JuezDAO();
    }

    /**
     * Registra un nuevo juez.
     *
     * @param juez juez que se desea registrar.
     */
    public void registrarJuez(Juez juez) {
        if (juez.getClave() <= 0) {
            System.out.println("La clave del juez es invalida.");
            return;
        }
        if (juez.getAniosServicio() < 0) {

            System.out.println("Los años de servicio no pueden ser negativos.");
            return;
        }
        if (dao.buscarPorClave(juez.getClave()) != null) {
            System.out.println("El juez ya existe.");
            return;
        }
        JuezDTO dto = new JuezDTO(juez.getClave(), juez.getNombre(),
                juez.getAniosServicio());
        dao.guardar(dto);
        System.out.println("Juez registrado correctamente.");
    }

    /**
     * Obtiene todos los jueces registrados.
     *
     * @return lista de jueces.
     */
    public ArrayList<Juez> listarJueces() {
        ArrayList<Juez> jueces
                = new ArrayList<>();
        for (JuezDTO dto : dao.listar()) {
            Juez juez = new Juez(dto.getClave(), dto.getNombre(), dto.getAniosServicio());
            jueces.add(juez);
        }
        return jueces;
    }

    /**
     * Busca un juez mediante su clave identificadora.
     *
     * @param clave clave del juez.
     * @return juez encontrado o null si no existe.
     */
    public Juez buscarPorClave(int clave) {
        JuezDTO dto
                = dao.buscarPorClave(clave);
        if (dto == null) {
            return null;
        }
        return new Juez(dto.getClave(), dto.getNombre(), dto.getAniosServicio());
    }

    /**
     * Elimina un juez registrado.
     *
     * @param clave clave del juez a eliminar.
     */
    public void eliminarJuez(int clave) {
        if (dao.buscarPorClave(clave) == null) {
            System.out.println("El juez no existe.");
            return;
        }
        dao.eliminar(clave);
        System.out.println("Juez eliminado correctamente.");
    }

    /**
     * Actualiza los datos de un juez existente.
     *
     * @param juez juez con la información modificada.
     */
    public void actualizarJuez(Juez juez) {
        if (dao.buscarPorClave(juez.getClave()) == null) {
            System.out.println("El juez no existe.");
            return;
        }
        JuezDTO dto = new JuezDTO(juez.getClave(), juez.getNombre(), juez.getAniosServicio());
        dao.actualizar(dto);
        System.out.println("Juez actualizado correctamente.");
    }
}
