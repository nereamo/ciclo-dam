 package nerea.Utilidades;

import java.text.Normalizer;
import java.util.Scanner;

/**
 * @author Nerea
 */
public class Utilidades {

    private static final Scanner sc = new Scanner(System.in);

    public static String pedirFrase() throws Exception {

        while (true) {

            System.out.println("Introduce una frase: ");

            String frase = sc.nextLine();

            if (frase.trim().isEmpty()) {
                System.out.println("Debe introducir una frase.\n");
                continue;
            }

            return frase;
        }
    }

    public static char buscarLetra() throws Exception {

        while (true) {

            System.out.println("\nQue letra quieres buscar? ");

            String letra = sc.nextLine();

            if (letra.trim().isEmpty() || letra.length() > 1 || !Character.isLetter(letra.charAt(0))) {
                System.out.println("Debe introducir una letra");
                continue;
            }

            return letra.charAt(0);
        }
    }

    public static int contador(String frase, char letra) {

        String fraseSinAcentos = Normalizer.normalize(frase, Normalizer.Form.NFD);
        fraseSinAcentos = fraseSinAcentos.replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();

        // Normalizamos la letra a buscar
        letra = Character.toLowerCase(letra);

        int count = 0;

        for (int i = 0; i < fraseSinAcentos.length(); i++) {
            if (fraseSinAcentos.charAt(i) == letra) {
                count++;
            }
        }
        return count;
    }
}