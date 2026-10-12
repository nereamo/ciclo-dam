package nerea.banconereamontoya.utilidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
* Classe que contiene los métodos utilizados al pedir datos por teclado.
* 
* @author Nerea Montoya
*/

public class Utilidades {

    public static int pideEntero() {
        Scanner sc = new Scanner(System.in);
        
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

    public static double pideDouble(String txt) {
        Scanner sc = new Scanner(System.in);
        System.out.print(txt);
        
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

    public static String pideString(String txt) {
        Scanner sc = new Scanner(System.in);
        System.out.print(txt);
        return sc.nextLine();
    }

    public static boolean pideBoolean() {
        Scanner sc = new Scanner(System.in);
        
        String respuesta;
        
        while (true) {
            respuesta = sc.nextLine();
            if (respuesta.equalsIgnoreCase("si") || respuesta.equalsIgnoreCase("sí")) {
                return true;
            } else if (respuesta.equalsIgnoreCase("no")) {
                return false;
            } else {
                throw new IllegalArgumentException("Por favor, responde con 'si' o 'no'.");
            }
        }
    }

    public static LocalDate pideFecha() {
        Scanner sc = new Scanner(System.in);
        
        LocalDate fecha = null;
        boolean valido = false;
        
        while (!valido) {
            String formatoFecha = sc.nextLine().trim();
            if (formatoFecha.isEmpty()) {
                throw new IllegalArgumentException("Error: La fecha no puede estar vacía.");
            }
            try {
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                fecha = LocalDate.parse(formatoFecha, formato);
                valido = true;
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Error: La fecha debe tener el formato dd/MM/yyyy");
            }
        }
        return fecha;
    }
}

