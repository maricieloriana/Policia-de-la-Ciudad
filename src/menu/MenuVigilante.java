package menu;

import Services.VigilanteService;
import java.util.Scanner;
import modelo.Vigilante;

/**
 * Muestra el menú de gestión de vigilantes.
 */
public class MenuVigilante {

    private final Scanner scanner;
    private final VigilanteService service;

    public MenuVigilante() {

        scanner = new Scanner(System.in);
        service = new VigilanteService();

    }

    public void iniciar() {
        int opcion;
        do {
            System.out.println();
            System.out.println("===== VIGILANTES =====");
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
        System.out.print("Código: ");
        int codigo = Integer.parseInt(scanner.nextLine());
        System.out.print("Edad: ");
        int edad = Integer.parseInt(scanner.nextLine());
        Vigilante vigilante = new Vigilante(codigo, edad);
        service.registrarVigilante(vigilante);

    }

    private void listar() {
        for (Vigilante v : service.listarVigilantes()) {
            System.out.println();
            System.out.println("Código: " + v.getCodigo());
            System.out.println("Edad: " + v.getEdad());
        }
    }

    private void buscar() {
        System.out.print("Código: ");
        int codigo = Integer.parseInt(scanner.nextLine());
        Vigilante vigilante = service.buscarPorCodigo(codigo);
        if (vigilante == null) {
            System.out.println("El vigilante no existe.");
            return;
        }
        System.out.println();
        System.out.println("Código: " + vigilante.getCodigo());
        System.out.println("Edad: " + vigilante.getEdad());

    }

    private void actualizar() {
        System.out.print("Código: ");
        int codigo = Integer.parseInt(scanner.nextLine());
        Vigilante vigilante = service.buscarPorCodigo(codigo);
        if (vigilante == null) {
            System.out.println("El vigilante no existe.");
            return;
        }
        System.out.print("Nueva edad: ");
        vigilante.setEdad(Integer.parseInt(scanner.nextLine()));
        service.actualizarVigilante(vigilante);
    }

    private void eliminar() {
        System.out.print("Código: ");
        int codigo = Integer.parseInt(scanner.nextLine());
        service.eliminarVigilante(codigo);
    }
}
