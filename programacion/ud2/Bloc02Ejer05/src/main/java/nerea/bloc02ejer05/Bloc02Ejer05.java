package nerea.bloc02ejer05;

import java.util.Scanner;

/**
 * Programa con enum para elegir perro
 *
 * @author Nerea Montoya
 */
public class Bloc02Ejer05 {

    public static void main(String[] args) {

        try {
            //Enum Raza que contiene distintas razas de perro
            enum Raza {
                MASTIN, TERRIER, BULLDOG, PASTOR_ALEMAN, PASTOR_MALLORQUIN, CANICHE, GALGO
            };

            Scanner scann = new Scanner(System.in);

            System.out.println("Que raza de perro quieres? ");

            String razaElegida = scann.nextLine();

            //Si la raza no existe en el enum, se lanzará una excepción
            Raza perro = Raza.valueOf(razaElegida.toUpperCase());

            System.out.println("La raza elegida es: " + perro);

        } catch (Exception e) {
            System.out.println("Raza no encontrada");
        }
    }
}
