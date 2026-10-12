
package nerea.psp_u1_nereamontoyaolivares;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
* @author Nerea
*/

public class BuscarPalabra {
    public static final Scanner sc;

    public BuscarPalabra() {
    }

    public static void buscarPalabra() throws IOException {
        try {
            System.out.println("¿Que palabra quieres buscar?: ");
            String palabra = sc.nextLine().trim();
            if (palabra.isEmpty() || palabra.contains(" ")) {
                System.out.println("Error! Debes introducir una palabra!");
                return;
            }

            ProcessBuilder pb = new ProcessBuilder(new String[]{"java", "-cp", "../psp_u2_montoyaOlivaresNerea_recursos/out/artifacts/psp_u2_montoyaOlivaresNerea_recursos_jar/psp_u2_montoyaOlivaresNerea_recursos.jar", "programaHijo.BuscarPalabra", palabra});
            pb.redirectErrorStream(true);
            Process process = pb.start();
            Exit.setProcess(process);

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                String line;
                try {
                    while((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (Throwable var7) {
                    try {
                        reader.close();
                    } catch (Throwable var6) {
                        var7.addSuppressed(var6);
                    }

                    throw var7;
                }

                reader.close();
            } catch (IOException var8) {
                IOException e = var8;
                e.printStackTrace();
            }

            process.waitFor();
        } catch (InterruptedException | IOException var9) {
            Exception e = var9;
            ((Exception)e).printStackTrace();
        }

    }

    static {
        sc = new Scanner(System.in);
    }
}
