package nerea.bloc06ejer02;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Scanner;

/**
 * Programa para generar números aleatorios de lotería y de ellos, elegir el momento en que se generará el Gordo.
 *
 * @author Nerea Montoya
 */
public class Bloc06Ejer02 {

    private static LinkedHashSet<Integer> numLoteria = new LinkedHashSet<>();
    private static Random randomNum = new Random();
    private static int cantidadNumeros;
    private static int posicionGordo;
    private static boolean continuar = true;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (continuar) {

            while (true) {
                System.out.println("Qué cantidad de números quieres? ");
                String entrada = sc.nextLine();

                if (entrada.matches("\\d+")) {
                    cantidadNumeros = Integer.parseInt(entrada);
                    if (cantidadNumeros > 0) {
                        break;
                    } else {
                        System.out.println("Por favor, ingrese un número mayor que 0.");
                    }
                } else {
                    System.out.println("Entrada inválida. Por favor, ingrese solo números.");
                }

            }

            while (true) {

                System.out.println("Cuando quieres que toque el gordo? ");
                String entrada = sc.nextLine();

                if (entrada.matches("\\d+")) {
                    posicionGordo = Integer.parseInt(entrada);
                    if (posicionGordo >= 1 && posicionGordo <= cantidadNumeros) {
                        break;
                    } else {
                        System.out.println("Lo siento, la posición no puede ser 0 ni ser mayor a la cantidad de números.");
                    }

                } else {
                    System.out.println("Entrada inválida. Por favor, ingrese solo números.");
                }

            }

            int contadorColisiones = 0;

            for (int i = 1; i <= cantidadNumeros; i++) {
                int numeros = randomNum.nextInt(99999);

                if (numLoteria.contains(numeros)) {
                    contadorColisiones++;
                } else {
                    numLoteria.add(numeros);

                }

                if (i == posicionGordo) {
                    System.out.println("El número premiado con el Gordo es: " + numeros);
                }
            }

            System.out.println("\nNúmeros generados: " + numLoteria);

            double porcentajeColisiones = (contadorColisiones * 100.0) / cantidadNumeros;
            System.out.println("Porcentaje de colisiones: " + porcentajeColisiones + "%");

            System.out.println("\nQuieres volver a probar? (s/n)");
            numLoteria.clear();
            String respuesta = sc.nextLine();

            if (respuesta.equalsIgnoreCase("n")) {
                continuar = false;
                System.out.println("Hasta la próxima");
            }
        }
        sc.close();
    }
}
