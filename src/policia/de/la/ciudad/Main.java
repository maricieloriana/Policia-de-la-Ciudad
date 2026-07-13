package policia.de.la.ciudad;

import menu.MenuPrincipal;

/**
 * Clase principal del sistema.
 *
 * Contiene el método main que inicia la ejecución del programa y muestra el
 * menú principal de la aplicación.
 */
public class Main {

    /**
     * Punto de entrada del sistema.
     *
     * @param args argumentos recibidos desde la línea de comandos.
     */
    public static void main(String[] args) {
        MenuPrincipal menu = new MenuPrincipal();
        menu.iniciar();
    }
}
