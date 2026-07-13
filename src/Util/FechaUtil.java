package Util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FechaUtil {

    private static final DateTimeFormatter FORMATO= DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private FechaUtil() {
        // para evitar que no se pueda instanciar
    }

    public static String formatear(LocalDate fecha) {
        return fecha.format(FORMATO);
    }

    public static LocalDate parsear(String fecha) {
        return LocalDate.parse(fecha, FORMATO);
    }
}
