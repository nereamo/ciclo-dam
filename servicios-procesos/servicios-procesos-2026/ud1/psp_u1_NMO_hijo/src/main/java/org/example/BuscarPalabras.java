package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class BuscarPalabras {

    public BuscarPalabras() {}

    public static void main(String[] args) {

        try {
            String palabra = args[0].toLowerCase().trim();
            buscarPalabra(palabra);
            System.out.flush();

        } catch (IOException e) {
            System.out.println("HIJO -> ERROR: " + e.getMessage());
            System.out.flush();
        }
    }

    private static void buscarPalabra(String palabra) throws IOException {

        Path txt = Path.of("encrypted.txt");

        if (!Files.exists(txt)) {
            System.out.println("NO SE HA ENCONTRADO NINGÚN ARCHIVO " + txt + ".");
        }

        try (BufferedReader br = new BufferedReader(new FileReader(txt.toFile()))) {

            boolean exists = false;
            String line;

            while ((line = br.readLine()) != null) {
                if (line.toLowerCase().contains(palabra)) {
                    exists = true;
                    break;
                }
            }
            if (exists) {
                System.out.println("La palabra encontrada es: " + palabra);
            } else {
                System.out.println("No se ha encontrado la palabra :" + palabra);
            }
        }
    }
}
