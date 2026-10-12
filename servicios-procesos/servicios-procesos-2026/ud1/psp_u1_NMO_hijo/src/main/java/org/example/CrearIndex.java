package org.example;

import java.io.*;
import java.nio.file.Path;

public class CrearIndex {

    private static StringBuilder sb = new StringBuilder();

    public CrearIndex() {}

    public static void main(String[] args) {

        try {

            leerArchivoEncrypted();
            mostrarContenidoBody();
            System.out.flush();

        } catch (IOException e) {
            System.out.println("HIJO -> ERROR: " + e.getMessage());
            System.out.flush();
        }
    }

    private static void leerArchivoEncrypted() throws IOException {
        Path txt = Path.of("encrypted.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(txt.toFile()))) {
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        }
        sb.toString();
    }

    private static void mostrarContenidoBody() throws IOException {

        Path html = Path.of("index.html");

        String contenido = sb.toString().toLowerCase();

        int inicio = contenido.indexOf("<body");
        int fin = contenido.indexOf("</body>");

        String soloBody = "";

        if (inicio != -1 && fin != -1 && fin > inicio) {
            soloBody = sb.toString().substring(inicio + 6, fin).trim();
        } else {
            System.out.println("NO SE ENCONTRÓ EL CONTENIDO <BODY> EN EL ARCHIVO " + html + ".");
        }

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(html.toFile()))) {
            bw.write("<body ");
            bw.write(soloBody);
            bw.write("\n</body>");
            bw.flush();
            System.out.println("ARCHIVO " + html + " CREADO");
        }
    }
}

