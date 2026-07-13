package Services;

import DAO.BandaDAO;
import DTO.BandaDTO;
import modelo.Banda;
import java.util.ArrayList;

/**
 * Clase encargada de gestionar la lógica de negocio relacionada con las bandas
 * delictivas.
 *
 * Realiza las validaciones necesarias antes de acceder a la capa de
 * persistencia.
 */
public class BandaService {

    private final BandaDAO dao;

    public BandaService() {
        this.dao = new BandaDAO();
    }

    /**
     * Registra una nueva banda verificando que no exista previamente.
     *
     * @param banda banda que se desea registrar.
     */
    public void registrarBanda(Banda banda) {
        if (banda.getNumero() <= 0) {
            System.out.println("El numero de banda es inválido.");
            return;
        }
        if (banda.getCantidadMiembros() < 0) {
            System.out.println("La cantidad de miembros no puede ser negativa.");
            return;
        }
        if (dao.buscarPorNumero(banda.getNumero()) != null) {
            System.out.println("La banda ya existe.");
            return;
        }
        BandaDTO dto = new BandaDTO(banda.getNumero(), banda.getCantidadMiembros());
        dao.guardar(dto);
        System.out.println("Banda registrada correctamente.");
    }

    /**
     * Obtiene todas las bandas registradas.
     *
     * @return lista de bandas.
     */
    public ArrayList<Banda> listarBandas() {
        ArrayList<Banda> bandas = new ArrayList<>();
        for (BandaDTO dto : dao.listar()) {
            Banda banda = new Banda();
            banda.setNumero(dto.getNumero());
            banda.setCantidadMiembros(dto.getCantidadMiembros());
            bandas.add(banda);
        }
        return bandas;
    }

    /**
     * Busca una banda mediante su código identificador.
     *
     * @param codigo código de la banda.
     * @return banda encontrada o null si no existe.
     */
    public Banda buscarPorNumero(int numero) {
        BandaDTO dto = dao.buscarPorNumero(numero);
        if (dto == null) {
            return null;
        }
        Banda banda = new Banda();
        banda.setNumero(dto.getNumero());
        banda.setCantidadMiembros(dto.getCantidadMiembros());
        return banda;
    }

    /**
     * Elimina una banda registrada.
     *
     * @param codigo código de la banda a eliminar.
     */
    public void eliminarBanda(int numero) {
        BandaDTO dto = dao.buscarPorNumero(numero);
        if (dto == null) {
            System.out.println("La banda no existe.");
            return;
        }
        dao.eliminar(numero);
        System.out.println("Banda eliminada correctamente.");
    }

    /**
     * Actualiza los datos de una banda existente.
     *
     * @param banda banda con la información modificada.
     */
    public void actualizarBanda(Banda banda) {
        if (dao.buscarPorNumero(banda.getNumero()) == null) {
            System.out.println("La banda no existe.");
            return;
        }
        BandaDTO dto = new BandaDTO(banda.getNumero(), banda.getCantidadMiembros());
        dao.actualizar(dto);
        System.out.println("Banda actualizada correctamente.");
    }

}
