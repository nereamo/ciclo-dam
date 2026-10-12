package org.example;

import java.io.*;
import java.nio.file.Path;

public class SustituirLetra {

    public SustituirLetra() {}

    public static void main(String[] args) {

        try {

            String url = leer();

            String letraOld = args[0];
            String letraNew = args[1];

            String htmlModificado = url.replace(letraOld, letraNew);

            String contenidoEncrypted = crearArchivoEncrypted(htmlModificado);

            System.out.println(contenidoEncrypted);
            System.out.flush();

        } catch (IOException e) {
            System.out.println("HIJO -> ERROR: " + e.getMessage());
            System.out.flush();
        }
    }

    private static String leer() throws IOException {
        StringBuilder sb = new StringBuilder();

        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        }
        return sb.toString();
    }

    private static String crearArchivoEncrypted(String contentArchivo) throws IOException {

        Path txt = Path.of("encrypted.txt");

        File f= new File(String.valueOf(txt));
        boolean creado = f.createNewFile();

        try (FileWriter fw = new FileWriter(f)) {
            fw.write(contentArchivo);
        }

        if (creado) {
            return "\nARCHIVO " + txt + " CREADO CON EXITO\n";
        } else {
            return "\nEL ARCHIVO " + txt + " YA EXISTE!\n";
        }
    }
}
