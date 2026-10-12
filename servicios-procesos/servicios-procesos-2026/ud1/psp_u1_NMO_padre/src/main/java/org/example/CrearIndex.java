package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.util.Scanner;

import static org.example.Utilidades.Utilidades.leer;

public class CrearIndex {

    public CrearIndex() {}

    public static void crearIndexHtml() {

        try {
            Scanner sc = new Scanner(System.in);

            Path txt = Path.of("encrypted.txt");

            if (!Files.exists(txt)) {
                System.out.println("EL ARCHIVO " + txt + " NO EXISTE");
                System.out.println("\nDeseas volver al paso nº3? (S/N)");
                String respuesta = sc.nextLine();

                while (!respuesta.equalsIgnoreCase("S") && !respuesta.equalsIgnoreCase("N")) {
                    System.out.println("Por favor, responda (S) si o (N) no.");
                    respuesta = sc.nextLine();
                }

                if(respuesta.equalsIgnoreCase("S")) {
                    SustituirLetras.sustituir(Menu.url);
                }
            }

            Process p = execPrograma();
            leer(p);
            p.waitFor();

        } catch (IOException e) {
            System.out.println("Error al obtener los datos del hijo: " + e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static Process execPrograma() throws IOException {
        ProcessBuilder pb = new ProcessBuilder(new String[]{"java", "-cp", "../psp_u1_NMO_hijo/out/artifacts/psp_u1_NMO_hijo_jar/psp_u1_NMO_hijo.jar", "org.example.CrearIndex"});
        Process p = null;
        Menu.procesoHijo = p;

        try {
            p = pb.start();
            Menu.procesoHijo = p;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return p;
    }
}
