package menu;

import Services.ContratacionService;
import Services.VigilanteService;
import Services.SucursalService;
import java.time.LocalDate;
import java.util.Scanner;
import modelo.Contratacion;
import modelo.Vigilante;
import modelo.Sucursal;

/**
 * Muestra el menú de gestión de contrataciones.
 */
public class MenuContratacion {

    private final Scanner scanner;

    private final ContratacionService service;
    private final VigilanteService vigilanteService;
    private final SucursalService sucursalService;

    public MenuContratacion() {
        scanner = new Scanner(System.in);
        service = new ContratacionService();
        vigilanteService = new VigilanteService();
        sucursalService = new SucursalService();
    }

    public void iniciar() {
        int opcion;
        do {
            System.out.println();
            System.out.println("===== CONTRATACIONES =====");
            System.out.println("1. Registrar");
            System.out.println("2. Listar");
            System.out.println("3. Buscar");
            System.out.println("4. Actualizar");
            System.out.println("5. Eliminar");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");
            opcion = Integer.parseInt(scanner.nextLine());
            switch (opcion) {
                case 1:
                    registrar();
                    break;
                case 2:
                    listar();
                    break;
                case 3:
                    buscar();
                    break;
                case 4:
                    actualizar();
                    break;
                case 5:
                    eliminar();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion inválida.");
            }
        } while (opcion != 0);
    }

    private void registrar() {

        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Codigo vigilante: ");
        int codigoVigilante = Integer.parseInt(scanner.nextLine());
        Vigilante vigilante = vigilanteService.buscarPorCodigo(codigoVigilante);
        if (vigilante == null) {
            System.out.println("El vigilante no existe.");
            return;
        }
        System.out.print("Codigo sucursal: ");
        int codigoSucursal = Integer.parseInt(scanner.nextLine());
        Sucursal sucursal = sucursalService.buscarPorCodigo(codigoSucursal);
        if (sucursal == null) {
            System.out.println("La sucursal no existe.");
            return;
        }
        System.out.print("¿Tiene arma? (true/false): ");
        boolean conArma = Boolean.parseBoolean(scanner.nextLine());
        Contratacion contratacion
                = new Contratacion(id,
                        LocalDate.now(),
                        conArma,
                        vigilante,
                        sucursal);
        service.registrarContratacion(contratacion);
    }

    private void listar() {
        for (Contratacion c : service.listarContrataciones()) {
            System.out.println();
            System.out.println("ID: " + c.getId());
            System.out.println("Fecha: " + c.getFecha());
            System.out.println("Con arma: " + c.isConArma());
            System.out.println("Vigilante: " + c.getVigilante().getCodigo());
            System.out.println("Sucursal: " + c.getSucursal().getCodigo());
        }
    }

    private void buscar() {
        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        Contratacion c = service.buscarPorId(id);
        if (c == null) {
            System.out.println("La contratacion no existe.");
            return;
        }
        System.out.println();
        System.out.println("Fecha: " + c.getFecha());
        System.out.println("Con arma: " + c.isConArma());
        System.out.println("Vigilante: " + c.getVigilante().getCodigo());
        System.out.println("Sucursal: " + c.getSucursal().getCodigo());
    }

    private void actualizar() {
        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        Contratacion c = service.buscarPorId(id);
        if (c == null) {
            System.out.println("La contratacion no existe.");
            return;
        }
        System.out.print("¿Tiene arma? (true/false): ");
        c.setConArma(Boolean.parseBoolean(scanner.nextLine()));
        service.actualizarContratacion(c);
    }

    private void eliminar() {
        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        service.eliminarContratacion(id);
    }
}
