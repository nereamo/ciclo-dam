/*
 */
package nerea.bloc02ejer01;

import java.util.Scanner;

/**
 * Programa Java para resolver ecuaciones cuadráticas (use if, else if y else).
 *
 * @author Nerea Montoya
 */
public class Bloc02Ejer01 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //Pedir por teclado los coeficientes
        System.out.print("Input a: ");
        double a = input.nextDouble();
        System.out.print("Input b: ");
        double b = input.nextDouble();
        System.out.print("Input c: ");
        double c = input.nextDouble();

        //Calcular: b² - 4ac
        double result = b * b - 4.0 * a * c;

        //Evaluar el 'result' para determinar el número de soluciones.
        if (result > 0.0) {

            double r1 = (-b + Math.sqrt(result)) / (2.0 * a);
            double r2 = (-b - Math.sqrt(result)) / (2.0 * a);
            System.out.println("The roots are " + r1 + " and " + r2);

        } else if (result == 0.0) {

            double r1 = -b / (2.0 * a);
            System.out.println("The root is " + r1);

        } else {

            System.out.println("The equation has no real roots.");
        }

        System.out.println("Nerea Montoya Olivares");
    }
}
