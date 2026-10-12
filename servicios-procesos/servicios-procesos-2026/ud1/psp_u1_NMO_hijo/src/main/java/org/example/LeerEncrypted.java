package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class LeerEncrypted {

    public LeerEncrypted() {}

    public static void main(String[] args) {

        try {

            String contenidoEncrypted = leerArchivoEncrypted();
            System.out.println(contenidoEncrypted);
            System.out.flush();

        } catch (IOException e) {
            System.out.println("HIJO -> ERROR: " + e.getMessage());
            System.out.flush();
        }
    }

    private static String leerArchivoEncrypted() throws IOException {

        Path txt = Path.of("encrypted.txt");

        if(!Files.exists(txt)) {
            return "\nNO SE HA ENCONTRADO EL ARCHIVO " + txt + " .\n";
        }else{

            StringBuilder sb = new StringBuilder();

            try(BufferedReader br = new BufferedReader(new FileReader(txt.toFile()))) {
                String line;

                System.out.println("\n--- CONTENIDO DEL ARCHIVO encrypted.txt ---\n");

                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                sb.append("\nEL ARCHIVO " + txt + " SE HA LEÍDO CON ÉXITO\n");
                return  sb.toString();
            }
        }
    }
}
