package nerea.bloc02ejer04;

import java.util.Scanner;

/**
 * Programa que pide y lee dos números enteros que representan un intervalo [x..y] en formato numérico entero
 *
 * @author Nerea Montoya
 */
public class Bloc02Ejer04 {

    public static void main(String[] args) {

        Scanner scann = new Scanner(System.in);

        int randomNum = (int) (Math.random() * 100);

        int numero = 0;

        int intentos = 0;

        int maxIntentos = 10;

        System.out.println("Nerea, introduce tu intento 1 para adivinar el número del intervalo [0-100]: ");

        //Mientras que 'numero' sea distinto de 'aleatorio' entonces...
        while (numero != randomNum) {

            intentos++;

            numero = scann.nextInt();

            if (randomNum < numero) {
                System.out.println("El número que buscas es más bajo.");

            } else if (randomNum > numero) {
                System.out.println("El número que buscas es más alto.");
            }

            if (intentos == maxIntentos) {
                System.out.println("Lo siento, has llegado al máximo de intentos.");
                return;
            }

        }

        System.out.println("Correcto, lo has conseguido en " + intentos + " intentos!");

    }
}
