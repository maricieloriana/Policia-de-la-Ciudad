package menu;

import Services.SucursalService;
import Services.EntidadBancariaService;
import java.util.Scanner;
import modelo.Sucursal;
import modelo.EntidadBancaria;

/**
 * Muestra el menú de gestión de sucursales.
 */
public class MenuSucursal {

    private final Scanner scanner;
    private final SucursalService service;
    private final EntidadBancariaService entidadService;

    public MenuSucursal() {

        scanner = new Scanner(System.in);
        service = new SucursalService();
        entidadService = new EntidadBancariaService();

    }

    public void iniciar() {
        int opcion;
        do {
            System.out.println();
            System.out.println("===== SUCURSALES =====");
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
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void registrar() {
        System.out.print("Codigo: ");
        int codigo = Integer.parseInt(scanner.nextLine());
        System.out.print("Domicilio: ");
        String domicilio = scanner.nextLine();
        System.out.print("Cantidad de empleados: ");
        int empleados = Integer.parseInt(scanner.nextLine());
        System.out.print("Codigo de entidad bancaria: ");
        int codigoEntidad = Integer.parseInt(scanner.nextLine());
        EntidadBancaria entidad = entidadService.buscarPorCodigo(codigoEntidad);
        if (entidad == null) {
            System.out.println("La entidad bancaria no existe.");
            return;
        }
        Sucursal sucursal = new Sucursal(codigo, domicilio, empleados, entidad);
        service.registrarSucursal(sucursal);
    }

    private void listar() {
        for (Sucursal s : service.listarSucursales()) {
            System.out.println();
            System.out.println("Codigo: " + s.getCodigo());
            System.out.println("Domicilio: " + s.getDomicilio());
            System.out.println("Cantidad empleados: " + s.getCantidadEmpleados());
            System.out.println("Entidad: " + s.getEntidad().getCodigo());
        }
    }

    private void buscar() {
        System.out.print("Codigo: ");
        int codigo = Integer.parseInt(scanner.nextLine());
        Sucursal sucursal = service.buscarPorCodigo(codigo);
        if (sucursal == null) {
            System.out.println("La sucursal no existe.");
            return;
        }
        System.out.println();
        System.out.println("Codigo: " + sucursal.getCodigo());
        System.out.println("Domicilio: " + sucursal.getDomicilio());
        System.out.println("Empleados: " + sucursal.getCantidadEmpleados());
        System.out.println("Entidad: " + sucursal.getEntidad().getCodigo());
    }

    private void actualizar() {
        System.out.print("Codigo: ");
        int codigo = Integer.parseInt(scanner.nextLine());
        Sucursal sucursal = service.buscarPorCodigo(codigo);
        if (sucursal == null) {
            System.out.println("La sucursal no existe.");
            return;
        }
        System.out.print("Nuevo domicilio: ");
        sucursal.setDomicilio(scanner.nextLine());
        System.out.print("Nueva cantidad de empleados: ");
        sucursal.setCantidadEmpleados(Integer.parseInt(scanner.nextLine()));
        System.out.print("Nuevo codigo entidad bancaria: ");
        int codigoEntidad = Integer.parseInt(scanner.nextLine());
        EntidadBancaria entidad = entidadService.buscarPorCodigo(codigoEntidad);
        if (entidad == null) {
            System.out.println("La entidad no existe.");
            return;
        }
        sucursal.setEntidad(entidad);
        service.actualizarSucursal(sucursal);
    }

    private void eliminar() {
        System.out.print("Codigo: ");
        int codigo = Integer.parseInt(scanner.nextLine());
        service.eliminarSucursal(codigo);
    }

}
