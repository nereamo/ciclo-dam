package org.example;

import java.awt.*;
import java.io.*;
import java.nio.file.Path;

public class EjecutarIndexHtml {

    private static StringBuilder sb = new StringBuilder();
    private static Path html = Path.of("index.html");

    public EjecutarIndexHtml() {}

    public static void main(String[] args) {

        try {

            abrirNavegador();
            System.out.flush();

        } catch (IOException e) {
            System.out.println("HIJO -> ERROR: " + e.getMessage());
            System.out.flush();
        }
    }

    private static void abrirNavegador() throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader(html.toFile()))) {
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        }

        Desktop.getDesktop().open(html.toFile());
        sb.toString();

    }
}
