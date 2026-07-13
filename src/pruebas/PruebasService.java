package pruebas;

import Services.*;
import java.time.LocalDate;
import modelo.*;

public class PruebasService {

    public static void main(String[] args) {
        System.out.println("===== PRUEBA ENTIDAD BANCARIA =====");
        EntidadBancariaService entidadService = new EntidadBancariaService();
        EntidadBancaria entidad = new EntidadBancaria(1, "Av. Corrientes 123", null);
        entidadService.registrarEntidad(entidad);
        System.out.println("\n===== LISTADO ENTIDADES =====");

        for (EntidadBancaria e : entidadService.listarEntidades()) {
            System.out.println(e);
        }
        System.out.println("\n===== PRUEBA VIGILANTE =====");
        VigilanteService vigilanteService
                = new VigilanteService();
        Vigilante vigilante = new Vigilante(100, 35);
        vigilanteService.registrarVigilante(vigilante);
        for (Vigilante v : vigilanteService.listarVigilantes()) {
            System.out.println(v);
        }

        System.out.println("\n===== PRUEBA SUCURSAL =====");
        SucursalService sucursalService = new SucursalService();
        EntidadBancaria entidadEncontrada = entidadService.buscarPorCodigo(1);
        Sucursal sucursal = new Sucursal(10, "Av. Santa Fe 100", 30, entidadEncontrada);
        sucursalService.registrarSucursal(sucursal);

        for (Sucursal s : sucursalService.listarSucursales()) {
            System.out.println(s.getCodigo() + " - " + s.getDomicilio() + " - Entidad: " + s.getEntidad().getCodigo());
        }

        System.out.println("\n===== PRUEBA CONTRATACION =====");
        ContratacionService contratacionService = new ContratacionService();
        Vigilante vigilanteEncontrado = vigilanteService.buscarPorCodigo(100);
        Sucursal sucursalEncontrada = sucursalService.buscarPorCodigo(10);
        Contratacion contratacion = new Contratacion(1, LocalDate.now(), true, vigilanteEncontrado, sucursalEncontrada);
        contratacionService.registrarContratacion(contratacion);
        for (Contratacion c : contratacionService.listarContrataciones()) {
            System.out.println(
                    "Contratacion: "
                    + c.getId()
                    + " - Fecha: "
                    + c.getFecha()
                    + " - Con arma: "
                    + c.isConArma()
                    + " - Vigilante: "
                    + c.getVigilante().getCodigo()
                    + " - Sucursal: "
                    + c.getSucursal().getCodigo());
        }

        System.out.println("\n===== PRUEBA BANDA =====");
        BandaService bandaService = new BandaService();
        Banda banda = new Banda(50, 5);
        bandaService.registrarBanda(banda);
        for (Banda b : bandaService.listarBandas()) {
            System.out.println("Banda: " + b.getNumero() + " - Miembros: " + b.getCantidadMiembros());
        }

        System.out.println("\n===== PRUEBA PERSONA DETENIDA =====");
        PersonaDetenidaService personaService = new PersonaDetenidaService();
        Banda bandaEncontrada = bandaService.buscarPorNumero(50);
        PersonaDetenida persona = new PersonaDetenida(200, "Juan Perez", bandaEncontrada);
        personaService.registrarPersona(persona);
        for (PersonaDetenida p : personaService.listarPersonas()) {
            System.out.println("Persona: " + p.getCodigo() + " - " + p.getNombreCompleto() + " - Banda: " + p.getBanda().getNumero());
        }

        System.out.println("\n===== PRUEBA JUEZ =====");
        JuezService juezService = new JuezService();
        Juez juez = new Juez(300, "Carlos Rodriguez", 15);
        juezService.registrarJuez(juez);
        for (Juez j : juezService.listarJueces()) {
            System.out.println("Juez: " + j.getClave() + " - " + j.getNombre() + " - Anios servicio: " + j.getAniosServicio());
        }

        System.out.println("\n===== PRUEBA JUICIO =====");
        JuicioService juicioService = new JuicioService();
        PersonaDetenida personaEncontrada = personaService.buscarPorCodigo(200);
        Juez juezEncontrado = juezService.buscarPorClave(300);
        Juicio juicio = new Juicio(1, true, 10, personaEncontrada, juezEncontrado);
        juicioService.registrarJuicio(juicio);
        for (Juicio j : juicioService.listarJuicios()) {
            System.out.println("Juicio: " + j.getId() + " - Condenado: " + j.isCondenado() + " - Anios: " + j.getAniosCarcel()
                    + " - Persona: "
                    + j.getPersona().getCodigo()
                    + " - Juez: "
                    + j.getJuez().getClave());
        }

        System.out.println("\n===== PRUEBA ASALTO =====");
        AsaltoService asaltoService = new AsaltoService();
        PersonaDetenida personaAsalto = personaService.buscarPorCodigo(200);
        Sucursal sucursalAsalto = sucursalService.buscarPorCodigo(10);
        Asalto asalto = new Asalto(1, LocalDate.now(), sucursalAsalto, personaAsalto);
        asaltoService.registrarAsalto(asalto);
        for (Asalto a : asaltoService.listarAsaltos()) {
            System.out.println("Asalto: " + a.getId() + " - Fecha: " + a.getFecha() + " - Sucursal: " + a.getSucursal().getCodigo() + " - Persona: " + a.getPersona().getCodigo());
        }

        System.out.println("\n===== PRUEBA USUARIOS =====");
        UsuarioService usuarioService = new UsuarioService();
        Administrador admin = new Administrador("admin", "1234");
        usuarioService.registrarUsuario(admin);

        Investigador investigador = new Investigador("investigador", "abcd");
        usuarioService.registrarUsuario(investigador);

        Vigilante vigilanteUsuario
                = vigilanteService.buscarPorCodigo(100);

        UsuarioVigilante usuarioVigilante = new UsuarioVigilante("vigilante1", "5678", vigilanteUsuario);
        usuarioService.registrarUsuario(usuarioVigilante);

        for (Usuario u : usuarioService.listarUsuarios()) {
            System.out.println("Usuario: " + u.getNombreUsuario() + " - Tipo: " + u.getClass().getSimpleName());
        }

        Juicio juicio1 = juicioService.buscarPorId(1);

        juicio1.setaniosCarcel(25);

        juicioService.actualizarJuicio(juicio1);

        Juicio actualizado = juicioService.buscarPorId(1);

        System.out.println(
                "Anioos actualizados: "
                + actualizado.getAniosCarcel()
        );

    }
}
