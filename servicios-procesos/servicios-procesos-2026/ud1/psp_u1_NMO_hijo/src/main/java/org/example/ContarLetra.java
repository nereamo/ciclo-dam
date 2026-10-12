package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ContarLetra {

    public ContarLetra() {}

    public static void main(String[] args) {

        try {
            char letra = args[0].charAt(0);

            String url = leer();

            if(url == null){
                System.out.println("FALLO DE LECTURA EN LOS DATOS RECIBIDOS.");
            }

            int count = contador(url, letra, 0);

            System.out.println("La letra " + Character.toUpperCase(letra) + " aparece " + count + " veces.");

            System.out.flush();

        } catch (IOException e) {
            System.out.println("HIJO -> ERROR: " + e.getMessage());
            System.out.flush();
        }
    }

    private static String leer() throws IOException {
        StringBuilder html = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = br.readLine()) != null) {
                html.append(line).append("\n");
            }
        }
        return html.toString();
    }

    private static int contador(String url, char letra, int contador){

        for (int i = 0; i < url.length(); i++) {
            if (url.charAt(i) == letra) {
                contador++;
            }
        }
        return contador;
    }

}
