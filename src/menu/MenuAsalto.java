package menu;

import Services.AsaltoService;
import Services.SucursalService;
import Services.PersonaDetenidaService;
import java.time.LocalDate;
import java.util.Scanner;
import modelo.Asalto;
import modelo.Sucursal;
import modelo.PersonaDetenida;

/**
 * Muestra el menú de gestión de asaltos.
 */
public class MenuAsalto {

    private final Scanner scanner;

    private final AsaltoService service;
    private final SucursalService sucursalService;
    private final PersonaDetenidaService personaService;

    public MenuAsalto() {
        scanner = new Scanner(System.in);
        service = new AsaltoService();
        sucursalService = new SucursalService();
        personaService = new PersonaDetenidaService();
    }

    public void iniciar() {
        int opcion;
        do {
            System.out.println();
            System.out.println("===== ASALTOS =====");
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
        System.out.print("Codigo sucursal: ");
        int codigoSucursal = Integer.parseInt(scanner.nextLine());
        Sucursal sucursal = sucursalService.buscarPorCodigo(codigoSucursal);
        if (sucursal == null) {
            System.out.println("La sucursal no existe.");
            return;
        }
        System.out.print("Código persona detenida: ");
        int codigoPersona = Integer.parseInt(scanner.nextLine());
        PersonaDetenida persona = personaService.buscarPorCodigo(codigoPersona);
        if (persona == null) {
            System.out.println("La persona no existe.");
            return;
        }
        Asalto asalto = new Asalto(id, LocalDate.now(), sucursal, persona);
        service.registrarAsalto(asalto);
    }

    private void listar() {
        for (Asalto a : service.listarAsaltos()) {
            System.out.println();
            System.out.println("ID: " + a.getId());
            System.out.println("Fecha: " + a.getFecha());
            System.out.println("Sucursal: " + a.getSucursal().getCodigo());
            System.out.println("Persona: " + a.getPersona().getCodigo());
        }
    }

    private void buscar() {
        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        Asalto asalto = service.buscarPorId(id);
        if (asalto == null) {
            System.out.println("El asalto no existe.");
            return;
        }
        System.out.println();
        System.out.println("ID: " + asalto.getId());
        System.out.println("Fecha: " + asalto.getFecha());
        System.out.println("Sucursal: " + asalto.getSucursal().getCodigo());
        System.out.println("Persona: " + asalto.getPersona().getCodigo());
    }

    private void actualizar() {
        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        Asalto asalto = service.buscarPorId(id);
        if (asalto == null) {
            System.out.println("El asalto no existe.");
            return;
        }
        System.out.print("Nuevo codigo sucursal: ");
        int codigoSucursal = Integer.parseInt(scanner.nextLine());
        Sucursal sucursal = sucursalService.buscarPorCodigo(codigoSucursal);
        if (sucursal == null) {
            System.out.println("La sucursal no existe.");
            return;
        }
        asalto.setSucursal(sucursal);
        System.out.print("Nuevo codigo persona: ");
        int codigoPersona = Integer.parseInt(scanner.nextLine());
        PersonaDetenida persona = personaService.buscarPorCodigo(codigoPersona);
        if (persona == null) {
            System.out.println("La persona no existe.");
            return;
        }
        asalto.setPersona(persona);
        service.actualizarAsalto(asalto);
    }

    private void eliminar() {
        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        service.eliminarAsalto(id);
    }

}
