package org.example;

import java.io.*;

import static org.example.Utilidades.Utilidades.leer;

public class LeerEncrypted {

    public LeerEncrypted (){}

    public static void leerArchivoEncrypted() {

        try {
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
        ProcessBuilder pb = new ProcessBuilder(new String[]{"java", "-cp", "../psp_u1_NMO_hijo/out/artifacts/psp_u1_NMO_hijo_jar/psp_u1_NMO_hijo.jar", "org.example.LeerEncrypted"});
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
