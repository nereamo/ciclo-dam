package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import static org.example.Utilidades.Utilidades.leer;

public class EjecutarIndexHtml {

    public EjecutarIndexHtml() {}

    public static void ejecutarHtml() {

        try {
            Scanner sc = new Scanner(System.in);

            Path path = Path.of("index.htmL");

            if (!Files.exists(path)) {
                System.out.println("EL ARCHIVO " + path + " NO EXISTE");
                System.out.println("\n Deseas volver al paso nº6? (S/N)");
                String respuesta = sc.nextLine();

                while (!respuesta.equalsIgnoreCase("S") && !respuesta.equalsIgnoreCase("N")) {
                    System.out.println("Por favor, responda (S) si o (N) no.");
                    respuesta = sc.nextLine();
                }

                if(respuesta.equalsIgnoreCase("S")) {
                    CrearIndex.crearIndexHtml();
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
        ProcessBuilder pb = new ProcessBuilder(new String[]{"java", "-cp", "../psp_u1_NMO_hijo/out/artifacts/psp_u1_NMO_hijo_jar/psp_u1_NMO_hijo.jar", "org.example.EjecutarIndexHtml"});
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
