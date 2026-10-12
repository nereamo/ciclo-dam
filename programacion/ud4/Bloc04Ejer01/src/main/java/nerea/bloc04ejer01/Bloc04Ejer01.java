package nerea.bloc04ejer01;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import nerea.Utilidades.Utilidades;

/**
 *
 * @author Nerea
 */

/*Programa que pide por teclado una frase y una letra, si aparece la letra, 
mostrará la cantidad de veces que aparece.*/

public class Bloc04Ejer01 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        boolean continuar = true;

        while (continuar) {
            try {

                String frase = Utilidades.pedirFrase();

                char letra = Utilidades.buscarLetra();

                int resultado = Utilidades.contador(frase, letra);

                if (resultado > 0) {
                    System.out.println("La letra '" + letra + "' aparece " + resultado + " veces en la frase.");
                } else {
                    System.out.println("La letra '" + letra + "' no aparece en la frase.");
                }

            } catch (Exception ex) {
                Logger.getLogger(Bloc04Ejer01.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println("\nQuieres volver a probar? (s/n)");
            String respuesta = sc.nextLine();

            if (respuesta.equalsIgnoreCase("n")) {
                continuar = false;
                System.out.println("Hasta la próxima");
            }
        }

        sc.close();
    }
}