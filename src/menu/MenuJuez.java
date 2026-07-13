package menu;

import Services.JuezService;
import java.util.Scanner;
import modelo.Juez;

/**
 * Muestra el menú de gestión de jueces.
 */
public class MenuJuez {

    private final Scanner scanner;
    private final JuezService service;

    public MenuJuez() {

        scanner = new Scanner(System.in);
        service = new JuezService();

    }

    public void iniciar() {
        int opcion;
        do {
            System.out.println();
            System.out.println("===== JUECES =====");
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
        System.out.print("Clave: ");
        int clave = Integer.parseInt(scanner.nextLine());
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Años de servicio: ");
        int anios = Integer.parseInt(scanner.nextLine());
        Juez juez = new Juez(clave, nombre, anios);
        service.registrarJuez(juez);
    }

    private void listar() {
        for (Juez j : service.listarJueces()) {
            System.out.println();
            System.out.println("Clave: " + j.getClave());
            System.out.println("Nombre: " + j.getNombre());
            System.out.println("Años de servicio: " + j.getAniosServicio());
        }
    }

    private void buscar() {
        System.out.print("Clave: ");
        int clave = Integer.parseInt(scanner.nextLine());
        Juez juez = service.buscarPorClave(clave);
        if (juez == null) {
            System.out.println("El juez no existe.");
            return;
        }
        System.out.println();
        System.out.println("Clave: " + juez.getClave());
        System.out.println("Nombre: " + juez.getNombre());
        System.out.println("Años de servicio: " + juez.getAniosServicio());
    }

    private void actualizar() {
        System.out.print("Clave: ");
        int clave = Integer.parseInt(scanner.nextLine());
        Juez juez = service.buscarPorClave(clave);
        if (juez == null) {
            System.out.println("El juez no existe.");
            return;
        }
        System.out.print("Nuevo nombre: ");
        juez.setNombre(scanner.nextLine());
        System.out.print("Nuevos años de servicio: ");
        juez.setAniosServicio(Integer.parseInt(scanner.nextLine()));
        service.actualizarJuez(juez);
    }

    private void eliminar() {
        System.out.print("Clave: ");
        int clave = Integer.parseInt(scanner.nextLine());
        service.eliminarJuez(clave);
    }

}
