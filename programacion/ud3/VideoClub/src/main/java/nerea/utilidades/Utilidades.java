package nerea.utilidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * @author Nerea
 */
public class Utilidades {

    private static Scanner sc = new Scanner(System.in);

    public static int demanaSencer() {
        int numero = 0;
        boolean valido = false;
        while (!valido) {
            try {
                String input = sc.nextLine();
                numero = Integer.parseInt(input);
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduce un número entero válido.");
            }
        }
        return numero;
    }

    public static double demanaDouble() {
    double numero = 0.0;
    boolean valido = false;
    while (!valido) {
        try {
            String input = sc.nextLine();
            // Reemplaza las comas por puntos
            input = input.replace(',', '.');
            numero = Double.parseDouble(input);
            valido = true;
        } catch (NumberFormatException e) {
            System.out.println("Por favor, introduce un número decimal válido.");
        }
    }
    return numero;
}

    public static String demanaCadena() {
        return sc.nextLine();
    }

    public static boolean demanaBoolean() {
        String respuesta;
        boolean valido = false;
        boolean resultado = false;
        while (!valido) {
            respuesta = sc.nextLine();
            if (respuesta.equalsIgnoreCase("si") || respuesta.equalsIgnoreCase("sí")) {
                resultado = true;
                valido = true;
            } else if (respuesta.equalsIgnoreCase("no")) {
                resultado = false;
                valido = true;
            } else {
                System.out.println("Por favor, responde con 'si' o 'no'.");
            }
        }
        return resultado;
    }

    public static LocalDate demanaData() {
        LocalDate fecha = null;
        while (fecha == null) {
            String formatoFecha = sc.nextLine().trim();
            if (formatoFecha.isEmpty()) {
                System.out.println("Error: La fecha no puede estar vacía.");
                continue;
            }
            try {
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                fecha = LocalDate.parse(formatoFecha, formato);
            } catch (DateTimeParseException e) {
                System.out.println("Error: La fecha debe tener el formato dd/MM/yyyy");
            }
        }
        return fecha;
    }

}
