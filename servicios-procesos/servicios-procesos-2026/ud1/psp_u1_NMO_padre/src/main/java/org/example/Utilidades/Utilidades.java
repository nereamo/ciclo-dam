package org.example.Utilidades;

import java.io.*;

public class Utilidades {


//    public static Process execPrograma(char letra){
//        ProcessBuilder pb = new ProcessBuilder(new String[]{"java", "-cp", "../psp_u1_NMO_hijo/out/artifacts/psp_u1_NMO_hijo_jar/psp_u1_NMO_hijo.jar", "org.example.ContarLetra", String.valueOf(letra)});
//        Process p = null;
//        Menu.procesoHijo = p;
//
//        try {
//            p = pb.start();
//            Menu.procesoHijo = p;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return p;
//    }

    public static void escribir(Process p, String url) throws IOException {
        try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()))) {
            bw.write(url);
            bw.newLine();
            bw.flush();
        }
    }

    public static void leer(Process p) throws IOException {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
