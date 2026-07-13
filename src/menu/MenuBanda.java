package menu;

import Services.BandaService;
import java.util.Scanner;
import modelo.Banda;

/**
 * Muestra el menú de gestión de bandas delictivas.
 */
public class MenuBanda {

    private final Scanner scanner;
    private final BandaService service;

    public MenuBanda() {

        scanner = new Scanner(System.in);
        service = new BandaService();

    }

    public void iniciar() {
        int opcion;
        do {
            System.out.println();
            System.out.println("===== BANDAS =====");
            System.out.println("1. Registrar");
            System.out.println("2. Listar");
            System.out.println("3. Buscar");
            System.out.println("4. Actualizar");
            System.out.println("5. Eliminar");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
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
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void registrar() {
        System.out.print("Número: ");
        int numero = Integer.parseInt(scanner.nextLine());
        System.out.print("Cantidad de miembros: ");
        int cantidad = Integer.parseInt(scanner.nextLine());
        Banda banda = new Banda(numero, cantidad);
        service.registrarBanda(banda);
    }

    private void listar() {
        for (Banda b : service.listarBandas()) {
            System.out.println();
            System.out.println("Número: " + b.getNumero());
            System.out.println("Cantidad de miembros: " + b.getCantidadMiembros());
        }
    }

    private void buscar() {
        System.out.print("Número: ");
        int numero = Integer.parseInt(scanner.nextLine());
        Banda banda = service.buscarPorNumero(numero);
        if (banda == null) {
            System.out.println("La banda no existe.");
            return;
        }
        System.out.println();
        System.out.println("Número: " + banda.getNumero());
        System.out.println("Cantidad de miembros: " + banda.getCantidadMiembros());
    }

    private void actualizar() {
        System.out.print("Número: ");
        int numero = Integer.parseInt(scanner.nextLine());
        Banda banda = service.buscarPorNumero(numero);
        if (banda == null) {
            System.out.println("La banda no existe.");
            return;
        }
        System.out.print("Nueva cantidad de miembros: ");
        banda.setCantidadMiembros(
                Integer.parseInt(scanner.nextLine()));
        service.actualizarBanda(banda);
    }

    private void eliminar() {
        System.out.print("Número: ");
        int numero = Integer.parseInt(scanner.nextLine());
        service.eliminarBanda(numero);
    }

}
