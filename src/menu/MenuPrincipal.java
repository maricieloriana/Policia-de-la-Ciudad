package menu;

import Services.*;
import java.util.Scanner;

/**
 * Clase encargada de administrar la interacción con el usuario mediante menús
 * por consola.
 *
 * Desde esta clase se accede a las distintas funcionalidades del sistema.
 */
public class MenuPrincipal {

    private final Scanner scanner;
    private final MenuEntidadBancaria menuEntidad;
    private final MenuVigilante menuVigilante;
    private final MenuBanda menuBanda;
    private final MenuJuez menuJuez;
    private final MenuSucursal menuSucursal;
    private final MenuPersonaDetenida menuPersona;
    private final MenuContratacion menuContratacion;
    private final MenuJuicio menuJuicio;
    private final MenuAsalto menuAsalto;
    private final MenuUsuario menuUsuario;

    public MenuPrincipal() {
        scanner = new Scanner(System.in);
        menuEntidad = new MenuEntidadBancaria();
        menuVigilante = new MenuVigilante();
        menuBanda = new MenuBanda();
        menuJuez = new MenuJuez();
        menuSucursal = new MenuSucursal();
        menuPersona = new MenuPersonaDetenida();
        menuContratacion = new MenuContratacion();
        menuJuicio = new MenuJuicio();
        menuAsalto = new MenuAsalto();
        menuUsuario = new MenuUsuario();
    }

    /**
     * Inicia la ejecución del menú principal del sistema.
     *
     * Muestra las opciones disponibles al usuario y controla la navegación
     * entre los distintos submenús hasta que se seleccione la opción de salir.
     */
    public void iniciar() {

        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = Integer.parseInt(scanner.nextLine());
            switch (opcion) {
                case 1:
                    menuEntidad.iniciar();
                    break;
                case 2:
                    menuSucursal.iniciar();
                    break;
                case 3:
                    menuVigilante.iniciar();
                    break;
                case 4:
                    menuContratacion.iniciar();
                    break;
                case 5:
                    menuBanda.iniciar();
                    break;
                case 6:
                    menuPersona.iniciar();
                    break;
                case 7:
                    menuJuez.iniciar();
                    break;
                case 8:
                    menuJuicio.iniciar();
                    break;
                case 9:
                    menuAsalto.iniciar();
                    break;
                case 10:
                    menuUsuario.iniciar();
                    break;
                case 0:
                    System.out.println("Hasta luego.");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        } while (opcion != 0);
    }

    private void mostrarMenuPrincipal() {

        System.out.println();
        System.out.println("==================================");
        System.out.println(" SISTEMA POLICIA DE LA CIUDAD");
        System.out.println("==================================");
        System.out.println("1. Entidades Bancarias");
        System.out.println("2. Sucursales");
        System.out.println("3. Vigilantes");
        System.out.println("4. Contrataciones");
        System.out.println("5. Bandas");
        System.out.println("6. Personas Detenidas");
        System.out.println("7. Jueces");
        System.out.println("8. Juicios");
        System.out.println("9. Asaltos");
        System.out.println("10. Usuarios");
        System.out.println("0. Salir");
        System.out.print("Opcion: ");

    }
}
