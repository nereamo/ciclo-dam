package nerea.bloc06ejer01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Programa en Java que pide por teclado la introducción de cadenas de caracteres 
 * y los va guardando en tres ArrayList diferentes según la cadena sea un 
 * valor numérico entero, un valor numérico real o una cadena no numérica
 *
 * @author Nerea Montoya
 */
public class Bloc06Ejer01 {

    private static ArrayList<Integer> arrayEnteros = new ArrayList<>();
    private static ArrayList<Double> arrayReales = new ArrayList<>();
    private static ArrayList<String> arrayCadenas = new ArrayList<>();
    private static String entrada;

    public static void main(String[] args) {

        compararCadena();

        mostrarColumnas(arrayEnteros, arrayReales, arrayCadenas);

        HashMap<Integer, Integer> dividir = dividirDouble(arrayEnteros, arrayReales);
        mostrarEnteros(dividir);

    }

    public static <T> void compararCadena() {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Introduce una cadena de carácteres(Introduzca 000 para salir): ");
            entrada = sc.nextLine();

            if (entrada.equalsIgnoreCase("000")) {
                break;
            }

            if (entrada.contains(",")) {
                entrada = entrada.replace(",", ".");
            }

            try {
                int numEntero = Integer.parseInt(entrada);
                arrayEnteros.add(numEntero);
            } catch (NumberFormatException e1) {
                try {
                    double numReal = Double.parseDouble(entrada);
                    arrayReales.add(numReal);
                } catch (NumberFormatException e2) {
                    arrayCadenas.add(entrada);
                }
            }
            Collections.sort(arrayEnteros);
            Collections.sort(arrayReales);
            Collections.sort(arrayCadenas);
        }
        sc.close();
    }

    public static void mostrarColumnas(ArrayList<Integer> arrayEnteros, ArrayList<Double> arrayReales, ArrayList<String> arrayCadenas) {

        int filas = Math.max(arrayEnteros.size(), Math.max(arrayReales.size(), arrayCadenas.size()));

        System.out.println("\nNúmeros guardados: ");
        System.out.printf("%-13s %-13s %-13s\n", "ENTEROS", "REALES", "CADENAS");

        for (int i = 0; i < filas; i++) {
            String entero = (i < arrayEnteros.size()) ? arrayEnteros.get(i).toString() : "";
            String real = (i < arrayReales.size()) ? String.format("%.2f", arrayReales.get(i)) : "";
            String cadena = (i < arrayCadenas.size()) ? arrayCadenas.get(i) : "";

            System.out.printf("%-13s %-13s %-13s\n", entero, real.replaceAll(",", "."), cadena);
        }

        System.out.printf("%-13s %-13s %-13s\n", ".......", "......", ".......");
        System.out.printf("%-13s %-13s %-13s %-13s\n", arrayEnteros.size(), arrayReales.size(), arrayCadenas.size(), "TOTALES");
    }

    public static HashMap<Integer, Integer> dividirDouble(ArrayList<Integer> arrayEnteros, ArrayList<Double> arrayReales) {

        HashMap<Integer, Integer> hash = new HashMap<>();

        for (double num : arrayReales) {

            int entero = (int) num;
            arrayEnteros.add(entero);
            hash.put(entero, hash.getOrDefault(entero, 0) + 1);
        }

        for (int entero : arrayEnteros) {

            hash.put(entero, hash.getOrDefault(entero, 0) + 1);
        }

        return hash;
    }

    public static void mostrarEnteros(HashMap<Integer, Integer> enteros) {
        System.out.println(" ");
        
        for (Integer key : enteros.keySet()) {
            System.out.printf("Número: %-10d Ocurrencias: %-10d\n", key, enteros.get(key));
        }

    }
}
