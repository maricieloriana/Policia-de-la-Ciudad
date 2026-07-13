package Services;

import DAO.AsaltoDAO;
import DTO.AsaltoDTO;
import modelo.Asalto;
import modelo.PersonaDetenida;
import modelo.Sucursal;
import java.util.ArrayList;

/**
 * Clase encargada de gestionar la lógica de negocio relacionada con los
 * asaltos.
 *
 * Administra la información de los hechos ocurridos y sus relaciones con
 * sucursales y personas detenidas.
 */
public class AsaltoService {

    private final AsaltoDAO dao;
    private final SucursalService sucursalService;
    private final PersonaDetenidaService personaService;

    public AsaltoService() {
        this.dao = new AsaltoDAO();
        this.sucursalService = new SucursalService();
        this.personaService = new PersonaDetenidaService();
    }

    /**
     * Registra un nuevo asalto.
     *
     * @param asalto asalto que se desea registrar.
     */
    public void registrarAsalto(Asalto asalto) {
        if (dao.buscarPorId(asalto.getId()) != null) {
            System.out.println("El asalto ya existe.");
            return;
        }
        AsaltoDTO dto = new AsaltoDTO(asalto.getId(), asalto.getFecha(), asalto.getSucursal().getCodigo(), asalto.getPersona().getCodigo());
        dao.guardar(dto);
        System.out.println("Asalto registrado correctamente.");
    }

    /**
     * Obtiene todos los asaltos registrados.
     *
     * @return lista de asaltos.
     */
    public ArrayList<Asalto> listarAsaltos() {
        ArrayList<Asalto> asaltos = new ArrayList<>();
        for (AsaltoDTO dto : dao.listar()) {
            Sucursal sucursal = sucursalService.buscarPorCodigo(dto.getCodigoSucursal());
            PersonaDetenida persona = personaService.buscarPorCodigo(dto.getCodigoPersona());
            Asalto asalto = new Asalto(dto.getId(), dto.getFecha(), sucursal, persona);
            asaltos.add(asalto);
        }
        return asaltos;
    }

    /**
     * Busca un asalto mediante su identificador.
     *
     * @param id identificador del asalto.
     * @return asalto encontrado o null si no existe.
     */
    public Asalto buscarPorId(int id) {
        AsaltoDTO dto = dao.buscarPorId(id);
        if (dto == null) {
            return null;
        }
        Sucursal sucursal
                = sucursalService.buscarPorCodigo(
                        dto.getCodigoSucursal()
                );
        PersonaDetenida persona
                = personaService.buscarPorCodigo(
                        dto.getCodigoPersona()
                );
        return new Asalto(
                dto.getId(),
                dto.getFecha(),
                sucursal,
                persona
        );
    }

    /**
     * Elimina un asalto existente.
     *
     * @param id identificador del asalto.
     */
    public void eliminarAsalto(int id) {
        if (dao.buscarPorId(id) == null) {
            System.out.println("El asalto no existe.");
            return;
        }
        dao.eliminar(id);
        System.out.println("Asalto eliminado correctamente.");
    }

    /**
     * Actualiza la información de un asalto registrado.
     *
     * @param asalto asalto con los datos modificados.
     */
    public void actualizarAsalto(Asalto asalto) {
        if (dao.buscarPorId(asalto.getId()) == null) {
            System.out.println("El asalto no existe.");
            return;
        }
        AsaltoDTO dto = new AsaltoDTO(asalto.getId(),
                asalto.getFecha(),
                asalto.getSucursal().getCodigo(),
                asalto.getPersona().getCodigo());
        dao.actualizar(dto);
        System.out.println("Asalto actualizado correctamente.");
    }

}
