package Services;

import DAO.SucursalDAO;
import DTO.SucursalDTO;
import modelo.EntidadBancaria;
import modelo.Sucursal;
import java.util.ArrayList;

/**
 * Clase encargada de administrar la lógica de negocio de las sucursales.
 *
 * Gestiona las operaciones relacionadas con sucursales y sus vínculos con
 * entidades bancarias.
 */
public class SucursalService {

    private final SucursalDAO dao;
    private final EntidadBancariaService entidadService;

    public SucursalService() {
        dao = new SucursalDAO();
        entidadService = new EntidadBancariaService();
    }

    /**
     * Registra una nueva sucursal validando que no exista previamente y que la
     * entidad bancaria asociada sea válida.
     *
     * @param sucursal sucursal que se desea registrar.
     */
    public void registrarSucursal(Sucursal sucursal) {
        if (sucursal.getCodigo() <= 0) {
            System.out.println("Código de sucursal inválido");
            return;
        }
        if (sucursal.getEntidad() == null) {
            System.out.println("La sucursal debe pertenecer a una entidad");
            return;
        }
        SucursalDTO existente
                = dao.buscarPorCodigo(sucursal.getCodigo());
        if (existente != null) {
            System.out.println("La sucursal ya existe");
            return;
        }
        EntidadBancaria entidad
                = entidadService.buscarPorCodigo(sucursal.getEntidad().getCodigo());
        if (entidad == null) {
            System.out.println("La entidad bancaria no existe");
            return;
        }
        SucursalDTO dto = new SucursalDTO(sucursal.getCodigo(),
                sucursal.getDomicilio(),
                sucursal.getCantidadEmpleados(),
                entidad.getCodigo());
        dao.guardar(dto);
        entidad.agregarSucursal(sucursal);
        System.out.println("Sucursal registrada correctamente");
    }

    /**
     * Obtiene todas las sucursales registradas.
     *
     * @return lista de sucursales.
     */
    public ArrayList<Sucursal> listarSucursales() {
        ArrayList<Sucursal> sucursales = new ArrayList<>();
        ArrayList<SucursalDTO> dtos = dao.listar();
        for (SucursalDTO dto : dtos) {
            EntidadBancaria entidad = entidadService.buscarPorCodigo(
                    dto.getCodigoEntidad());
            Sucursal sucursal = new Sucursal(
                    dto.getCodigo(),
                    dto.getDomicilio(),
                    dto.getCantidadEmpleados(),
                    entidad);
            sucursales.add(sucursal);
            if (entidad != null) {
                entidad.agregarSucursal(sucursal);
            }
        }
        return sucursales;
    }

    /**
     * Busca una sucursal mediante su código.
     *
     * @param codigo código identificador de la sucursal.
     * @return sucursal encontrada o null si no existe.
     */
    public Sucursal buscarPorCodigo(int codigo) {
        SucursalDTO dto = dao.buscarPorCodigo(codigo);
        if (dto == null) {
            return null;
        }
        EntidadBancaria entidad = entidadService.buscarPorCodigo(
                dto.getCodigoEntidad()
        );

        return new Sucursal(
                dto.getCodigo(),
                dto.getDomicilio(),
                dto.getCantidadEmpleados(),
                entidad
        );
    }

    /**
     * Elimina una sucursal existente.
     *
     * @param codigo código de la sucursal a eliminar.
     */
    public void eliminarSucursal(int codigo) {
        SucursalDTO sucursal = dao.buscarPorCodigo(codigo);
        if (sucursal == null) {
            System.out.println("La sucursal no existe");
            return;
        }
        dao.eliminar(codigo);
        System.out.println("Sucursal eliminada correctamente");
    }

    /**
     * Actualiza los datos de una sucursal registrada.
     *
     * @param sucursal sucursal con la información modificada.
     */
    public void actualizarSucursal(Sucursal sucursal) {

        if (dao.buscarPorCodigo(sucursal.getCodigo()) == null) {
            System.out.println("La sucursal no existe.");
            return;
        }
        SucursalDTO dto = new SucursalDTO(sucursal.getCodigo(), sucursal.getDomicilio(),
                sucursal.getCantidadEmpleados(),
                sucursal.getEntidad().getCodigo());
        dao.actualizar(dto);
        System.out.println("Sucursal actualizada correctamente.");
    }

}
