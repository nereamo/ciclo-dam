package nerea.bloc04ejer03;

import java.util.Scanner;
import nerea.Utilidades.Utilidades;

/**
 *
 * @author Nerea
 */

/*Programa que pide por teclado números decimales, se irá rellenando el array multidimensional.
Mostrará por pantalla los números introducidos en formato tabulado de columnas. 
La última columna mostrará cual es la parte entera con valor máximo*/

public class Bloc04Ejer03 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {

            Double[][] numeros = new Double[2][3];
            Double[] maximos = new Double[3];

            Utilidades.guardarDecimales(numeros, maximos);

            Utilidades.mostrarDecimales(numeros, maximos);

            System.out.println("\nQuieres volver a probar? (s/n)");
            String respuesta = sc.nextLine();

            if (respuesta.equalsIgnoreCase("n")) {
                continuar = false;
                System.out.println("Hasta la próxima!");
            }

        }
        sc.close();
    }
}
