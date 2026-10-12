package nerea.bloc02ejer06;

import java.util.Scanner;

/**
 * Programa que pide 3 númerosa y muestra cual es más pequeño
 *
 * @author Nerea Montoya
 */
public class Bloc02Ejer06 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Input the first number: ");
        double x = in.nextDouble();
        System.out.print("Input the Second number: ");
        double y = in.nextDouble();
        System.out.print("Input the third number: ");
        double z = in.nextDouble();

        //Comparación entre los 3 valores, si son iguales para el programa 
        if (x == y && x == z && y == z) {
            System.out.print("Los números son iguales. Programa finalizado.");
            return;

        } else {
            System.out.print("The smallest value is " + smallest(x, y, z) + "\n");
        }
    }

    public static double smallest(double x, double y, double z) {

        //Condición if, compara los valores para saber cual es el más pequeño.
        if (x <= y && x <= z) {
            return x;

        } else if (y <= x && y <= z) {
            return y;

        } else {
            return z;
        }
    }
}


