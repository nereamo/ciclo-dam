package org.example;

import java.util.Scanner;

/**
 * Validar los datos introducidos de tipo String e int
 */

public class Utils {

    //Validación para int
    public static int readInt(Scanner sc, String message) {
        while (true) {
            System.out.println(message);
            String line = sc.nextLine();
            try {
                return Integer.parseInt(line); //Convierte el texto introducido a entero
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number!");
            }
        }
    }

    //Validación para String
    public static String readString(Scanner sc, String message) {
        while (true) {
            System.out.println(message);
            String input = sc.nextLine();

            //Comprueba que el texto no sea nulo ni vacío
            if (input != null && !input.trim().isEmpty()) {
                return input;
            } else {
                System.out.println("Error: This field cannot be empty!");
            }
        }
    }
}
