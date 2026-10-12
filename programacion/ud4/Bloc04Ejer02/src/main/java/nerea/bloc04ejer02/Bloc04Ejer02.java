package nerea.bloc04ejer02;

import java.util.Scanner;
import nerea.utilidades.Utilidades;

/**
 *
 * @author Nerea
 */

/*Programa que pide por teclado un código formato NNN-LLLL (N = número entre 0 y 9 | L = letras) hasta completar el array.
Si el código introducido no cumple el formato, no entrará en el array.
Si el código es válido pero no empieza por la letra 'a' o 'A' en su parte no numérica, será false. 
Al finalizar el programa mostrará cuántos son false y cuántos son erróneos.*/

public class Bloc04Ejer02 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean continuar = true;
        String[] codigo = new String[5];
        boolean[] booleanos = new boolean[5];

        while (continuar) {

            Utilidades.guardarCadenas(codigo);

            Utilidades.mostrarCodigos(codigo);

            Utilidades.codigoNoValido();

            Utilidades.comprobarBoolean(codigo, booleanos);

            Utilidades.mostrarResultadoArray(codigo, booleanos);

            Utilidades.mostrarResultadoFinal(codigo, booleanos);

            System.out.println("\nQuieres volver a probar? (s/n)");
            String respuesta = sc.nextLine();

            if (respuesta.equalsIgnoreCase("n")) {
                continuar = false;
                System.out.println("¡Hasta luego!");
            }
        }

        sc.close();
    }
}
