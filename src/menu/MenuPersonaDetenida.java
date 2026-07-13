package menu;

import Services.PersonaDetenidaService;
import Services.BandaService;
import java.util.Scanner;
import modelo.PersonaDetenida;
import modelo.Banda;

/**
 * Muestra el menú de gestión de personas detenidas.
 */
public class MenuPersonaDetenida {

    private final Scanner scanner;
    private final PersonaDetenidaService service;
    private final BandaService bandaService;

    public MenuPersonaDetenida() {

        scanner = new Scanner(System.in);
        service = new PersonaDetenidaService();
        bandaService = new BandaService();

    }

    public void iniciar() {
        int opcion;
        do {
            System.out.println();
            System.out.println("===== PERSONAS DETENIDAS =====");
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
        System.out.print("Codigo: ");
        int codigo = Integer.parseInt(scanner.nextLine());
        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine();
        System.out.print("Numero de banda (0 si no pertenece): ");
        int numeroBanda = Integer.parseInt(scanner.nextLine());
        Banda banda = null;
        if (numeroBanda != 0) {
            banda = bandaService.buscarPorNumero(numeroBanda);
            if (banda == null) {
                System.out.println("La banda no existe.");
                return;
            }
        }
        PersonaDetenida persona = new PersonaDetenida(codigo, nombre, banda);
        service.registrarPersona(persona);
    }

    private void listar() {
        for (PersonaDetenida p : service.listarPersonas()) {
            System.out.println();
            System.out.println("Codigo: " + p.getCodigo());
            System.out.println("Nombre: " + p.getNombreCompleto());
            if (p.getBanda() != null) {
                System.out.println("Banda: " + p.getBanda().getNumero());
            } else {
                System.out.println("Banda: Sin banda");
            }
        }
    }

    private void buscar() {
        System.out.print("Codigo: ");
        int codigo = Integer.parseInt(scanner.nextLine());
        PersonaDetenida persona = service.buscarPorCodigo(codigo);
        if (persona == null) {
            System.out.println("La persona no existe.");
            return;
        }
        System.out.println();
        System.out.println("Codigo: " + persona.getCodigo());
        System.out.println("Nombre: " + persona.getNombreCompleto());
        if (persona.getBanda() != null) {
            System.out.println("Banda: " + persona.getBanda().getNumero());
        } else {
            System.out.println("Banda: Sin banda");
        }
    }

    private void actualizar() {

        System.out.print("Codigo: ");
        int codigo = Integer.parseInt(scanner.nextLine());
        PersonaDetenida persona
                = service.buscarPorCodigo(codigo);
        if (persona == null) {
            System.out.println("La persona no existe.");
            return;
        }
        System.out.print("Nuevo nombre: ");
        persona.setNombreCompleto(scanner.nextLine());
        System.out.print("Nuevo número de banda (0 para quitar): ");
        int numeroBanda = Integer.parseInt(scanner.nextLine());
        if (numeroBanda == 0) {

            persona.setBanda(null);
        } else {
            Banda banda = bandaService.buscarPorNumero(numeroBanda);
            if (banda == null) {
                System.out.println("La banda no existe.");
                return;
            }
            persona.setBanda(banda);
        }
        service.actualizarPersona(persona);
    }

    private void eliminar() {
        System.out.print("Codigo: ");
        int codigo = Integer.parseInt(scanner.nextLine());
        service.eliminarPersona(codigo);
    }

}
