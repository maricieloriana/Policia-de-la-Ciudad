package menu;

import Services.EntidadBancariaService;
import java.util.Scanner;
import modelo.EntidadBancaria;

/**
 * Muestra el menú de gestión de entidades bancarias y permite realizar
 * operaciones de alta, baja, modificación, consulta y listado.
 */
public class MenuEntidadBancaria {

    private final Scanner scanner;
    private final EntidadBancariaService service;

    public MenuEntidadBancaria() {
        scanner = new Scanner(System.in);
        service = new EntidadBancariaService();
    }

    public void iniciar() {
        int opcion;
        do {
            System.out.println();
            System.out.println("===== ENTIDADES BANCARIAS =====");
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

    public void registrar() {
        System.out.print("Codigo: ");
        int codigo = Integer.parseInt(scanner.nextLine());
        System.out.print("Domicilio: ");
        String domicilio = scanner.nextLine();
        EntidadBancaria entidad = new EntidadBancaria(codigo, domicilio, null);
        service.registrarEntidad(entidad);
    }

    private void listar() {
        for (EntidadBancaria e : service.listarEntidades()) {
            System.out.println();
            System.out.println("Codigo: " + e.getCodigo());
            System.out.println("Domicilio: " + e.getDomicilioCentral());
        }
    }

    private void buscar() {
        System.out.print("Codigo: ");
        int codigo = Integer.parseInt(scanner.nextLine());
        EntidadBancaria entidad = service.buscarPorCodigo(codigo);
        if (entidad == null) {
            System.out.println("No existe.");
            return;
        }
        System.out.println();
        System.out.println("Codigo: " + entidad.getCodigo());
        System.out.println("Domicilio: " + entidad.getDomicilioCentral());
    }

    private void actualizar() {
        System.out.print("Codigo: ");
        int codigo = Integer.parseInt(scanner.nextLine());
        EntidadBancaria entidad = service.buscarPorCodigo(codigo);
        if (entidad == null) {
            System.out.println("No existe.");
            return;
        }
        System.out.print("Nuevo domicilio: ");
        entidad.setDomicilioCentral(scanner.nextLine());
        service.actualizarEntidad(entidad);

    }

    private void eliminar() {
        System.out.print("Codigo: ");
        int codigo = Integer.parseInt(scanner.nextLine());
        service.eliminarEntidad(codigo);
    }

}
