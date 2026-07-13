package Services;

import DAO.JuicioDAO;
import DTO.JuicioDTO;
import modelo.Juez;
import modelo.Juicio;
import modelo.PersonaDetenida;
import java.util.ArrayList;

/**
 * Clase encargada de gestionar la lógica de negocio relacionada con los
 * juicios.
 *
 * Administra la relación entre personas detenidas y jueces, realizando las
 * validaciones correspondientes.
 */
public class JuicioService {

    private final JuicioDAO dao;
    private final PersonaDetenidaService personaService;
    private final JuezService juezService;

    public JuicioService() {
        this.dao = new JuicioDAO();
        this.personaService = new PersonaDetenidaService();
        this.juezService = new JuezService();

    }

    /**
     * Registra un juicio verificando la existencia de la persona detenida y del
     * juez asociado.
     *
     * @param juicio juicio que se desea registrar.
     */
    public void registrarJuicio(Juicio juicio) {
        if (dao.buscarPorId(juicio.getId()) != null) {
            System.out.println("El juicio ya existe.");
            return;
        }
        if (juicio.getAniosCarcel() < 0) {
            System.out.println("Los años de cárcel no pueden ser negativos.");
            return;
        }
        JuicioDTO dto = new JuicioDTO(
                juicio.getId(),
                juicio.isCondenado(),
                juicio.getAniosCarcel(),
                juicio.getPersona().getCodigo(),
                juicio.getJuez().getClave());
        dao.guardar(dto);
        System.out.println("Juicio registrado correctamente.");
    }

    /**
     * Obtiene todos los juicios registrados.
     *
     * @return lista de juicios.
     */
    public ArrayList<Juicio> listarJuicios() {
        ArrayList<Juicio> juicios = new ArrayList<>();
        for (JuicioDTO dto : dao.listar()) {
            PersonaDetenida persona = personaService.buscarPorCodigo(
                    dto.getCodigoPersona());

            Juez juez = juezService.buscarPorClave(
                    dto.getClaveJuez());
            Juicio juicio = new Juicio(
                    dto.getId(),
                    dto.isCondenado(),
                    dto.getAniosCarcel(),
                    persona,
                    juez);
            juicios.add(juicio);
        }
        return juicios;
    }

    /**
     * Busca un juicio mediante su identificador.
     *
     * @param id identificador del juicio.
     * @return juicio encontrado o null si no existe.
     */
    public Juicio buscarPorId(int id) {
        JuicioDTO dto = dao.buscarPorId(id);
        if (dto == null) {
            return null;
        }

        PersonaDetenida persona = personaService.buscarPorCodigo(dto.getCodigoPersona());
        Juez juez = juezService.buscarPorClave(dto.getClaveJuez());
        return new Juicio(
                dto.getId(),
                dto.isCondenado(),
                dto.getAniosCarcel(),
                persona,
                juez);
    }

    /**
     * Elimina un juicio registrado.
     *
     * @param id identificador del juicio.
     */
    public void eliminarJuicio(int id) {
        if (dao.buscarPorId(id) == null) {
            System.out.println("El juicio no existe.");
            return;
        }
        dao.eliminar(id);
        System.out.println("Juicio eliminado correctamente.");
    }

    /**
     * Actualiza la información de un juicio existente.
     *
     * @param juicio juicio con los datos modificados.
     */
    public void actualizarJuicio(Juicio juicio) {
        if (dao.buscarPorId(juicio.getId()) == null) {
            System.out.println("El juicio no existe.");
            return;
        }
        JuicioDTO dto = new JuicioDTO(juicio.getId(), juicio.isCondenado(), juicio.getAniosCarcel(), juicio.getPersona().getCodigo(), juicio.getJuez().getClave());
        dao.actualizar(dto);
        System.out.println("Juicio actualizado correctamente.");
    }
}
