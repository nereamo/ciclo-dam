package nerea.Utilidades;

import static java.lang.Double.parseDouble;
import java.util.Scanner;

/**
 * @author Nerea
 */
public class Utilidades {

    public static Double pedirDecimales(String pantalla) {

        Scanner sc = new Scanner(System.in);
        String input;
        Double decimal;

        while (true) {
            System.out.println(pantalla);

            input = sc.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Debes introducir un número decimal.\n");
                continue;
            }

            if (!input.matches("\\d+\\.\\d+")) {
                System.out.println("Debes introducir un número decimal válido con un punto (ejemplo 1.1).\n");
                continue;
            }

            decimal = (Double) parseDouble(input);
            break;
        }

        return decimal;
    }

    public static void guardarDecimales(Double[][] numeros, Double[] maximos) {

        for (int i = 0; i < 2; i++) {
            System.out.println("\nColumna " + (i + 1) + ": ");

            for (int j = 0; j < 3; j++) {
                numeros[i][j] = pedirDecimales("Introduce un número decimal para la posición [" + i + "][" + j + "]: ");
            }
        }

        for (int i = 0; i < 3; i++) {
            maximos[i] = Math.max(numeros[0][i], numeros[1][i]);
        }
    }

    public static void mostrarDecimales(Double[][] numeros, Double[] maximos) {

        System.out.println("\nNúmeros guardados: ");
        System.out.printf("%-13s %-13s %-13s %-13s\n", "Fila", "Columna 1", "Columna 2", "Máximo");
        System.out.println("---------------------------------------------------");

        for (int i = 0; i < numeros[0].length; i++) {
            // Mostrar los valores de Columna 1, Columna 2 y el Máximo
            System.out.printf("Fila %-12d%-13s %-13s %-13.1s\n",
                    (i + 1),
                    numeros[0][i], // Columna 1
                    numeros[1][i], // Columna 2
                    maximos[i]);
        }
    }
}
