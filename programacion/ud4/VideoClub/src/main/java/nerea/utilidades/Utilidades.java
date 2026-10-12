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

    public static int pideEntero() {
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

    public static double pideDouble() {
        double numero = 0.0;
        boolean valido = false;
        while (!valido) {
            try {
                String input = sc.nextLine();
                input = input.replace(',', '.');
                numero = Double.parseDouble(input);
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduce un número decimal válido.");
            }
        }
        return numero;
    }

    public static String pideString() {
        return sc.nextLine();
    }

    public static boolean pideBoolean() {
        String respuesta;
        while (true) {
            respuesta = sc.nextLine();
            if (respuesta.equalsIgnoreCase("si") || respuesta.equalsIgnoreCase("sí")) {
                return true;
            } else if (respuesta.equalsIgnoreCase("no")) {
                return false;
            } else {
                System.out.println("Por favor, responde con 'si' o 'no'.");
            }
        }
    }

    public static LocalDate pideFecha() {
        LocalDate fecha = null;
        boolean valido = false;
        while (!valido) {
            String formatoFecha = sc.nextLine().trim();
            if (formatoFecha.isEmpty()) {
                System.out.println("Error: La fecha no puede estar vacía.");
            }
            try {
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                fecha = LocalDate.parse(formatoFecha, formato);
                valido = true;
            } catch (DateTimeParseException e) {
                System.out.println("Error: La fecha debe tener el formato dd/MM/yyyy");
            }
        }
        return fecha;
    }

}
