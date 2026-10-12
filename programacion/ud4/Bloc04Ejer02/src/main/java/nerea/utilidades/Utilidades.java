package nerea.utilidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Nerea
 */
public class Utilidades {

    public static final List<String> codigoError = new ArrayList<>();

    public static String pedirCodigo(String pantalla) {

        Scanner sc = new Scanner(System.in);

        String codigo;

        Pattern pattern = Pattern.compile("^\\d{3}-[a-zA-Z]{4}$");

        while (true) {
            System.out.println(pantalla);

            codigo = sc.nextLine().trim();

            if (codigo.isEmpty()) {
                System.out.println("El código no puede estar vacío.\n");
                codigoError.add("vacío");
                continue;
            }

            Matcher matcher = pattern.matcher(codigo);

            if (matcher.matches()) {
                System.out.println("Código válido\n");
                break;
            } else {
                System.out.println("El código no tiene el formato NNN-LLLL. Vuelve a probar.\n");
                codigoError.add(codigo);
            }

        }
        return codigo;
    }

    public static void guardarCadenas(String[] codigos) {

        for (int i = 0; i < 5; i++) {
            codigos[i] = pedirCodigo("Introduce un código " + (i + 1) + ": ");
        }
    }

    public static void mostrarCodigos(String[] codigos) {

        System.out.println("Códigos introducidos: ");
        System.out.println("----------------------");

        for (String codigo : codigos) {
            System.out.println(codigo);
        }
    }

    public static void codigoNoValido() {

        if (codigoError.isEmpty()) {
            System.out.println("No hay códigos erroneos");

        } else {
            System.out.println("\nCódigos erroneos: ");
            System.out.println("----------------------");

            for (String codigoTotal : codigoError) {
                System.out.println(codigoTotal);
            }
        }
    }

    public static boolean generarBooleanos(String codigos) {
        String[] booleanos = codigos.split("-");

        if (booleanos.length == 2) {

            char primeraLetra = booleanos[1].charAt(0);
            return primeraLetra == 'a' || primeraLetra == 'A';
        }
        return false;
    }

    public static void comprobarBoolean(String[] codigo, boolean[] booleanos) {

        for (int i = 0; i < codigo.length; i++) {
            booleanos[i] = generarBooleanos(codigo[i]);

            if (codigo[i] == null) {
                booleanos[i] = false;
            }
        }
    }

    public static void mostrarResultadoArray(String[] codigo, boolean[] booleanos) {

        System.out.println("\nResultado del Array:");

        System.out.printf("%-15s\t%-15s\n", "Código", "Valor");
        System.out.println("----------------------");

        for (int i = 0; i < codigo.length; i++) {
            System.out.printf("%-15s\t%-15s\n", codigo[i], booleanos[i]);
        }
    }

    public static void mostrarResultadoFinal(String[] codigo, boolean[] booleanos) {

        int countFalse = 0;
        int countInvalidos = codigoError.size();

        // Contar cuántos son false
        for (boolean valor : booleanos) {
            if (!valor) {
                countFalse++;
            }
        }

        System.out.println("\nResultado Final:");
        System.out.println("----------------------");
        System.out.printf("Total de códigos false: %d\n", countFalse);
        System.out.printf("Total de códigos erroneos: %d\n", countInvalidos);
        System.out.printf("Nerea Montoya Olivares");
    }
}
