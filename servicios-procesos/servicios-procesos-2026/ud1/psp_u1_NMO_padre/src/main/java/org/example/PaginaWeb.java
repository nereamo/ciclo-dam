package org.example;

import java.io.*;

public class PaginaWeb {

    public PaginaWeb() {}

    public static void cargarPaginaWeb(String url) {

        try {
            System.out.println("Contenido de la Web: " + url);
            System.out.println(".....................\n");

            Process p = execPrograma();
            escribir(p, url);
            String html = leer(p);

            if (html != null) {
                System.out.println(html);
                System.out.println("Contenido de la web cargado correctamente.");
            } else {
                System.out.println("Error: El proceso hijo no devolvió ningún contenido.");
            }

            p.waitFor();

        } catch (IOException e) {
            System.out.println("Error al cargar la página: " + e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static Process execPrograma(){
        ProcessBuilder pb = new ProcessBuilder(new String[]{"java", "-cp", "../psp_u1_NMO_hijo/out/artifacts/psp_u1_NMO_hijo_jar/psp_u1_NMO_hijo.jar", "org.example.PaginaWeb"});
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

    private static void escribir(Process p, String url) throws IOException {
        try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()))) {
            bw.write(url);
            bw.newLine();
            bw.flush();
        }
    }

    private static String leer(Process p) throws IOException {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return Menu.url = sb.toString();
        }
    }
}
