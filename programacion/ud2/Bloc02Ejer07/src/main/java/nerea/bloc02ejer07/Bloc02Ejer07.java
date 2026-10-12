/*
 */
package nerea.bloc02ejer07;

import java.util.Scanner;

/**
 * Programa Java para mostrar números pentagonales
 *
 * @author Nerea
 */
public class Bloc02Ejer07 {

    public static void main(String[] args) {

        Scanner scann = new Scanner(System.in);

        System.out.println("Cuantos números quieres calcular? ");
        int numPenta = scann.nextInt();

        System.out.println("En cuántas filas quieres que se muestren? ");
        int numFilas = scann.nextInt();

        int count = 1;

        //Bucle para calcular y mostrar los números pentagonales
        for (int i = 1; i <= numPenta; i++) {

            //Imprime número pentagonal, formato de 6 espacios
            System.out.printf("%-6d", getPentagonalNumber(i));

            if (count % numFilas == 0) {
                System.out.println();
            }
            count++;
        }
    }

    //Fórmula para calcular el número pentagonal
    public static int getPentagonalNumber(int i) {
        return (i * (3 * i - 1)) / 2;

    }
}
