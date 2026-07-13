package Services;

import DAO.ContratacionDAO;
import DTO.ContratacionDTO;
import modelo.Contratacion;
import modelo.Sucursal;
import modelo.Vigilante;
import java.util.ArrayList;

/**
 * Clase encargada de gestionar la lógica de negocio relacionada con las
 * contrataciones de vigilantes.
 *
 * Administra la relación entre vigilantes y sucursales.
 */
public class ContratacionService {

    private final ContratacionDAO dao;
    private final VigilanteService vigilanteService;
    private final SucursalService sucursalService;

    public ContratacionService() {
        this.dao = new ContratacionDAO();
        this.vigilanteService = new VigilanteService();
        this.sucursalService = new SucursalService();
    }

    /**
     * Registra una contratación verificando la existencia del vigilante y la
     * sucursal asociada.
     *
     * @param contratacion contratación que se desea registrar.
     */
    public void registrarContratacion(Contratacion contratacion) {
        if (contratacion.getId() <= 0) {
            System.out.println("ID de contratacion inválido.");
            return;
        }
        if (dao.buscarPorId(contratacion.getId()) != null) {
            System.out.println("La contratacion ya existe.");
            return;
        }
        Vigilante vigilante = vigilanteService.buscarPorCodigo(
                contratacion.getVigilante().getCodigo());
        if (vigilante == null) {
            System.out.println("El vigilante no existe.");
            return;
        }
        Sucursal sucursal = sucursalService.buscarPorCodigo(
                contratacion.getSucursal().getCodigo());
        if (sucursal == null) {
            System.out.println("La sucursal no existe.");
            return;
        }
        ContratacionDTO dto = new ContratacionDTO(contratacion.getId(),
                contratacion.getFecha(),
                contratacion.isConArma(),
                vigilante.getCodigo(),
                sucursal.getCodigo());
        dao.guardar(dto);
        vigilante.agregarContratacion(contratacion);
        sucursal.agregarContratacion(contratacion);
        System.out.println("Contratacion registrada correctamente.");
    }

    /**
     * Obtiene todas las contrataciones registradas.
     *
     * @return lista de contrataciones.
     */
    public ArrayList<Contratacion> listarContrataciones() {
        ArrayList<Contratacion> contrataciones = new ArrayList<>();
        for (ContratacionDTO dto : dao.listar()) {
            Vigilante vigilante = vigilanteService.buscarPorCodigo(dto.getCodigoVigilante());
            Sucursal sucursal = sucursalService.buscarPorCodigo(
                    dto.getCodigoSucursal());
            Contratacion contratacion = new Contratacion(
                    dto.getId(),
                    dto.getFecha(),
                    dto.isConArma(),
                    vigilante,
                    sucursal);
            contrataciones.add(contratacion);
            if (vigilante != null) {
                vigilante.agregarContratacion(contratacion);
            }
            if (sucursal != null) {
                sucursal.agregarContratacion(contratacion);
            }
        }
        return contrataciones;
    }

    /**
     * Busca una contratación mediante su identificador.
     *
     * @param id identificador de la contratación.
     * @return contratación encontrada o null si no existe.
     */
    public Contratacion buscarPorId(int id) {
        ContratacionDTO dto = dao.buscarPorId(id);
        if (dto == null) {
            return null;
        }
        Vigilante vigilante = vigilanteService.buscarPorCodigo(
                dto.getCodigoVigilante());
        Sucursal sucursal = sucursalService.buscarPorCodigo(
                dto.getCodigoSucursal());
        return new Contratacion(
                dto.getId(),
                dto.getFecha(),
                dto.isConArma(),
                vigilante,
                sucursal);
    }

    /**
     * Elimina una contratación existente.
     *
     * @param id identificador de la contratación.
     */
    public void eliminarContratacion(int id) {
        Contratacion contratacion = buscarPorId(id);
        if (contratacion == null) {
            System.out.println("La contratacion no existe.");
            return;
        }
        dao.eliminar(id);
        if (contratacion.getVigilante() != null) {
            contratacion.getVigilante().eliminarContratacion(contratacion);
        }
        if (contratacion.getSucursal() != null) {
            contratacion.getSucursal().eliminarContratacion(contratacion);
        }
        System.out.println("Contratacion eliminada correctamente.");
    }

    /**
     * Actualiza una contratación existente.
     *
     * @param contratacion contratación con los datos modificados.
     */
    public void actualizarContratacion(Contratacion contratacion) {
        if (dao.buscarPorId(contratacion.getId()) == null) {
            System.out.println("La contratación no existe.");
            return;
        }
        ContratacionDTO dto = new ContratacionDTO(
                contratacion.getId(),
                contratacion.getFecha(),
                contratacion.isConArma(),
                contratacion.getVigilante().getCodigo(),
                contratacion.getSucursal().getCodigo()
        );
        dao.actualizar(dto);
        System.out.println("Contratación actualizada correctamente.");
    }

}
