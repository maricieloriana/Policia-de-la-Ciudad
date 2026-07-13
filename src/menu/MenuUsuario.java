package menu;

import Services.UsuarioService;
import Services.VigilanteService;
import java.util.Scanner;
import modelo.Usuario;
import modelo.Administrador;
import modelo.Investigador;
import modelo.UsuarioVigilante;
import modelo.Vigilante;

/**
 * Muestra el menú de administración de usuarios del sistema.
 */
public class MenuUsuario {

    private final Scanner scanner;

    private final UsuarioService service;
    private final VigilanteService vigilanteService;

    public MenuUsuario() {

        scanner = new Scanner(System.in);

        service = new UsuarioService();
        vigilanteService = new VigilanteService();

    }

    public void iniciar() {
        int opcion;
        do {
            System.out.println();
            System.out.println("===== USUARIOS =====");
            System.out.println("1. Registrar");
            System.out.println("2. Listar");
            System.out.println("3. Buscar");
            System.out.println("4. Eliminar");
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
        System.out.print("Nombre usuario: ");
        String nombre = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.println();
        System.out.println("Tipo:");
        System.out.println("1. Administrador");
        System.out.println("2. Investigador");
        System.out.println("3. Vigilante");
        System.out.print("Opcion: ");
        int tipo = Integer.parseInt(scanner.nextLine());
        Usuario usuario;
        switch (tipo) {
            case 1:
                usuario = new Administrador(nombre, password);
                break;
            case 2:
                usuario = new Investigador(nombre, password);
                break;
            case 3:
                System.out.print("Codigo vigilante: ");
                int codigo = Integer.parseInt(scanner.nextLine());
                Vigilante vigilante = vigilanteService.buscarPorCodigo(codigo);
                if (vigilante == null) {
                    System.out.println("El vigilante no existe.");
                    return;
                }
                usuario = new UsuarioVigilante(nombre, password, vigilante);
                break;
            default:
                System.out.println("Tipo invalido.");
                return;
        }
        service.registrarUsuario(usuario);
    }

    private void listar() {
        for (Usuario u : service.listarUsuarios()) {
            System.out.println();
            System.out.println("Usuario: " + u.getNombreUsuario());
            System.out.println("Tipo: " + u.getClass().getSimpleName());
            if (u instanceof UsuarioVigilante) {
                UsuarioVigilante uv = (UsuarioVigilante) u;
                System.out.println("Vigilante: " + uv.getVigilante().getCodigo());
            }
        }
    }

    private void buscar() {
        System.out.print("Usuario: ");
        String nombre = scanner.nextLine();
        Usuario usuario = service.buscarPorNombre(nombre);
        if (usuario == null) {
            System.out.println("No existe.");
            return;
        }
        System.out.println();
        System.out.println("Usuario: " + usuario.getNombreUsuario());
        System.out.println("Tipo: " + usuario.getClass().getSimpleName());
    }

    private void eliminar() {
        System.out.print("Usuario: ");
        String nombre = scanner.nextLine();
        service.eliminarUsuario(nombre);
    }

}
