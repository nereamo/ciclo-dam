package nerea.bloc02ejer08;

import java.util.Scanner;

/**
 * Programa con menú que al introducir dos numeros y seleccionar una opción muestra el resultado
 *
 * @author Nerea Montoya
 */
public class Bloc02Ejer08 {

    //Objeto estático para utilizarlo en todo el programa
    private static Scanner scann = new Scanner(System.in);

    static double num1;
    static double num2;

    public static void main(String[] args) {

        System.out.println("Introduce un número: ");
        num1 = scann.nextInt();

        System.out.println("Introduce otro número: ");
        num2 = scann.nextInt();

        int opciones = 0;

        //Bucle que se repite hasta que se seleccione la opción de salir (5)
        while (opciones != 5) {
            System.out.println("\nMenú: ");
            System.out.println("--------------------");
            System.out.println("1. Sumar");
            System.out.println("2. Restar");
            System.out.println("3. Multiplicar");
            System.out.println("4. Dividir");
            System.out.println("5. Salir del programa");
            System.out.println("--------------------");
            System.out.println("Elige una opción: ");

            opciones = scann.nextInt();

            try {

                switch (opciones) {

                    case 1:
                        System.out.println("1. Sumar");
                        sumar();
                        break;

                    case 2:
                        System.out.println("2. Restar ");
                        restar();
                        break;

                    case 3:
                        System.out.println("3. Multiplicar");
                        multiplicar();
                        break;

                    case 4:
                        System.out.println("4. Dividir");
                        dividir();
                        break;

                    case 5:
                        System.out.println("Hasta la próxima!.");
                        break;

                    default:
                        System.out.println("Por favor, introduce una opción válida.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void sumar() {

        double suma = num1 + num2;

        System.out.println("El resultado es: " + suma);
    }

    private static void restar() {

        double rest = num1 - num2;

        System.out.println("El resultado es: " + rest);
    }

    private static void multiplicar() {

        double mult = num1 * num2;

        System.out.println("El resultado es: " + mult);
    }

    private static void dividir() {

        double divid = num1 / num2;

        System.out.println("El resultado es: " + divid);
    }
}
