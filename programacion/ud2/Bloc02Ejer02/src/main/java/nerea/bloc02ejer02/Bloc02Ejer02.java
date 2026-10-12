/*
 */
package nerea.bloc02ejer02;

import java.util.Scanner;

/**
 * Programa Java que muestre la suma de n números naturales impares.
 *
 * @author Nerea Montoya
 */
public class Bloc02Ejer02 {

    public static void main(String[] args) {

        int i, n, sum = 0;

        System.out.print("Input number of terms is: ");

        Scanner in = new Scanner(System.in);
        n = in.nextInt();

        System.out.print("Los 4 primeros números pares son: ");

        //Bucle para calcular y mostrar los números pares
        for (i = 1; i <= n; i++) {
            System.out.print(" " + (2 * i));
            sum += 2 * i - 2;   //Acumula los valores en la suma
        }

        System.out.print("\ny los que son múltiplos de " + n + " suman: " + sum);

        in.close();
    }
}
