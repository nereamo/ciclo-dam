/*
 */

package nerea.psp_u1_montoyaolivaresnerea_recursos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Nerea
 */
public class BuscarPalabra {
    public BuscarPalabra() {
    }

    public static void main(String[] args) {
        String palabra = args[0].toLowerCase().trim();

        try {
            File file = new File("encrypted.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            boolean encontrada = false;

            String line;
            while((line = reader.readLine()) != null) {
                if (line.toLowerCase().contains(palabra)) {
                    encontrada = true;
                    break;
                }
            }

            reader.close();
            if (encontrada) {
                System.out.println("La palabra encontrada es: " + palabra);
            } else {
                System.out.println("No se ha encontrado la palabra :" + palabra);
            }
        } catch (IOException var6) {
            IOException e = var6;
            e.printStackTrace();
        }

    }
}
