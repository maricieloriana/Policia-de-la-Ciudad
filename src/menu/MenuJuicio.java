package menu;

import Services.JuicioService;
import Services.PersonaDetenidaService;
import Services.JuezService;
import java.util.Scanner;
import modelo.Juicio;
import modelo.PersonaDetenida;
import modelo.Juez;

/**
 * Muestra el menú de gestión de juicios.
 */
public class MenuJuicio {

    private final Scanner scanner;

    private final JuicioService service;
    private final PersonaDetenidaService personaService;
    private final JuezService juezService;

    public MenuJuicio() {
        scanner = new Scanner(System.in);
        service = new JuicioService();
        personaService = new PersonaDetenidaService();
        juezService = new JuezService();

    }

    public void iniciar() {
        int opcion;
        do {
            System.out.println();
            System.out.println("===== JUICIOS =====");
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
        System.out.print("¿Condenado? (true/false): ");
        boolean condenado = Boolean.parseBoolean(scanner.nextLine());
        System.out.print("Anios de carcel: ");
        int anios = Integer.parseInt(scanner.nextLine());
        System.out.print("Codigo persona detenida: ");
        int codigoPersona = Integer.parseInt(scanner.nextLine());
        PersonaDetenida persona = personaService.buscarPorCodigo(codigoPersona);
        if (persona == null) {
            System.out.println("La persona no existe.");
            return;
        }
        System.out.print("Clave juez: ");
        int claveJuez = Integer.parseInt(scanner.nextLine());
        Juez juez = juezService.buscarPorClave(claveJuez);
        if (juez == null) {
            System.out.println("El juez no existe.");
            return;
        }
        Juicio juicio = new Juicio(id, condenado, anios, persona, juez);
        service.registrarJuicio(juicio);
    }

    private void listar() {
        for (Juicio j : service.listarJuicios()) {
            System.out.println();
            System.out.println("ID: " + j.getId());
            System.out.println("Condenado: " + j.isCondenado());
            System.out.println("Anios carcel: " + j.getAniosCarcel());
            System.out.println("Persona: " + j.getPersona().getCodigo());
            System.out.println("Juez: " + j.getJuez().getClave());
        }
    }

    private void buscar() {
        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        Juicio juicio = service.buscarPorId(id);
        if (juicio == null) {
            System.out.println("El juicio no existe.");
            return;
        }
        System.out.println();
        System.out.println("ID: " + juicio.getId());
        System.out.println("Condenado: " + juicio.isCondenado());
        System.out.println("Anios cárcel: " + juicio.getAniosCarcel());
        System.out.println("Persona: " + juicio.getPersona().getCodigo());
        System.out.println("Juez: " + juicio.getJuez().getClave());
    }

    private void actualizar() {

        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        Juicio juicio = service.buscarPorId(id);
        if (juicio == null) {
            System.out.println("El juicio no existe.");
            return;
        }
        System.out.print("Nuevo estado condenado (true/false): ");
        juicio.setCondenado(
                Boolean.parseBoolean(scanner.nextLine()));
        System.out.print("Nuevos años de cárcel: ");
        juicio.setaniosCarcel(
                Integer.parseInt(scanner.nextLine()));
        service.actualizarJuicio(juicio);
    }

    private void eliminar() {
        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        service.eliminarJuicio(id);
    }

}
