
package nerea.psp_u1_nereamontoyaolivares;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
* @author Nerea
*/

public class CrearArchivoIndex {
    public static final Scanner sc;

    public CrearArchivoIndex() {
    }

    public static void crearArchivoIndex() throws IOException {
        File file = new File("encrypted.txt");
        if (!file.exists()) {
            System.out.println("El archivo 'encrypted.txt' no existe. ¿Deseas realizar la opción 3 antes de crear el archivo index.html? (s/n)");
            String reply = sc.nextLine().trim().toLowerCase();
            if (reply.equals("s")) {
                SustituirLetras.sustituirLetras();
            }
        }

        ProcessBuilder pb = new ProcessBuilder(new String[]{"java", "-cp", "../psp_u2_montoyaOlivaresNerea_recursos/out/artifacts/psp_u2_montoyaOlivaresNerea_recursos_jar/psp_u2_montoyaOlivaresNerea_recursos.jar", "programaHijo.CrearArchivoIndex"});
        pb.redirectErrorStream(true);
        Process process = pb.start();
        Exit.setProcess(process);

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            try {
                String line;
                while((line = reader.readLine()) != null) {
                    System.out.println(line);
                }

                process.waitFor();
            } catch (Throwable var7) {
                try {
                    reader.close();
                } catch (Throwable var6) {
                    var7.addSuppressed(var6);
                }

                throw var7;
            }

            reader.close();
        } catch (InterruptedException | IOException var8) {
            Exception e = var8;
            ((Exception)e).printStackTrace();
        }

    }

    static {
        sc = new Scanner(System.in);
    }
}

