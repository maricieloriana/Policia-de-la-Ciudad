package Services;

import DAO.PersonaDetenidaDAO;
import DTO.PersonaDetenidaDTO;
import modelo.Banda;
import modelo.PersonaDetenida;
import java.util.ArrayList;

/**
 * Clase encargada de gestionar la lógica de negocio relacionada con las
 * personas detenidas.
 *
 * Administra las operaciones de registro, consulta, modificación y eliminación
 * de personas detenidas.
 */
public class PersonaDetenidaService {

    private final PersonaDetenidaDAO dao;
    private final BandaService bandaService;

    public PersonaDetenidaService() {
        this.dao = new PersonaDetenidaDAO();
        this.bandaService = new BandaService();
    }

    /**
     * Registra una persona detenida verificando la existencia de la banda
     * asociada cuando corresponda.
     *
     * @param persona persona detenida que se desea registrar.
     */
    public void registrarPersona(PersonaDetenida persona) {
        if (persona.getCodigo() <= 0) {
            System.out.println("Código inválido.");
            return;
        }

        if (dao.buscarPorCodigo(persona.getCodigo()) != null) {
            System.out.println("La persona ya existe.");
            return;
        }

        Integer numeroBanda = null;
        if (persona.getBanda() != null) {
            numeroBanda = persona.getBanda().getNumero();
        }
        PersonaDetenidaDTO dto = new PersonaDetenidaDTO(persona.getCodigo(), persona.getNombreCompleto(), numeroBanda);
        dao.guardar(dto);
        System.out.println("Persona detenida registrada correctamente.");
    }

    /**
     * Obtiene todas las personas detenidas registradas.
     *
     * @return lista de personas detenidas.
     */
    public ArrayList<PersonaDetenida> listarPersonas() {
        ArrayList<PersonaDetenida> personas = new ArrayList<>();
        for (PersonaDetenidaDTO dto : dao.listar()) {
            Banda banda = null;
            if (dto.getNumeroBanda() != null) {
                banda = bandaService.buscarPorNumero(dto.getNumeroBanda());
            }
            PersonaDetenida persona = new PersonaDetenida(dto.getCodigo(), dto.getNombreCompleto(), banda);
            personas.add(persona);
        }
        return personas;
    }

    /**
     * Busca una persona detenida por su código.
     *
     * @param codigo código identificador de la persona.
     * @return persona encontrada o null si no existe.
     */
    public PersonaDetenida buscarPorCodigo(int codigo) {
        PersonaDetenidaDTO dto = dao.buscarPorCodigo(codigo);
        if (dto == null) {
            return null;
        }
        Banda banda = null;
        if (dto.getNumeroBanda() != null) {
            banda = bandaService.buscarPorNumero(dto.getNumeroBanda());
        }

        return new PersonaDetenida(dto.getCodigo(), dto.getNombreCompleto(), banda);
    }

    /**
     * Elimina una persona detenida existente.
     *
     * @param codigo código de la persona a eliminar.
     */
    public void eliminarPersona(int codigo) {
        PersonaDetenida persona = buscarPorCodigo(codigo);
        if (persona == null) {
            System.out.println("La persona no existe.");
            return;
        }
        dao.eliminar(codigo);
        System.out.println("Persona eliminada correctamente.");
    }

    /**
     * Actualiza la información de una persona detenida.
     *
     * @param persona persona detenida con los datos modificados.
     */
    public void actualizarPersona(PersonaDetenida persona) {

        if (dao.buscarPorCodigo(persona.getCodigo()) == null) {
            System.out.println("La persona no existe.");
            return;
        }
        Integer numeroBanda = null;
        if (persona.getBanda() != null) {
            numeroBanda = persona.getBanda().getNumero();
        }
        PersonaDetenidaDTO dto = new PersonaDetenidaDTO(persona.getCodigo(), persona.getNombreCompleto(), numeroBanda);
        dao.actualizar(dto);
        System.out.println("Persona actualizada correctamente.");
    }

}
