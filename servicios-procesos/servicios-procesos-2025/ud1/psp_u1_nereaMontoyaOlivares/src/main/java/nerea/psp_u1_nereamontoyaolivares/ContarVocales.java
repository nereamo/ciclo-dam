
package nerea.psp_u1_nereamontoyaolivares;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
* @author Nerea
*/

public class ContarVocales {
    public static final Scanner sc;

    public ContarVocales() {
    }

    public static void contarVocales() throws IOException {
        try {
            System.out.println("Introduce una vocal: ");
            String vocales = sc.nextLine();
            if (vocales.length() != 1 || !"aeiouAEIOU".contains(vocales)) {
                System.out.println("Error, debes introducir una vocal");
                return;
            }

            char vocal = vocales.charAt(0);
            ProcessBuilder pb = new ProcessBuilder(new String[]{"java", "-cp", "../psp_u2_montoyaOlivaresNerea_recursos/out/artifacts/psp_u2_montoyaOlivaresNerea_recursos_jar/psp_u2_montoyaOlivaresNerea_recursos.jar", "programaHijo.ContarVocales", String.valueOf(vocal)});
            Process process = pb.start();
            Exit.setProcess(process);

            IOException e;
            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));

                try {
                    writer.write(Menu.html);
                    writer.flush();
                } catch (Throwable var9) {
                    try {
                        writer.close();
                    } catch (Throwable var8) {
                        var9.addSuppressed(var8);
                    }

                    throw var9;
                }

                writer.close();
            } catch (IOException var10) {
                e = var10;
                e.printStackTrace();
            }

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                try {
                    String line;
                    if ((line = reader.readLine()) != null) {
                        System.out.println("La vocal: " + vocal + " aparece " + line + " veces.");
                    }
                } catch (Throwable var11) {
                    try {
                        reader.close();
                    } catch (Throwable var7) {
                        var11.addSuppressed(var7);
                    }

                    throw var11;
                }

                reader.close();
            } catch (IOException var12) {
                e = var12;
                e.printStackTrace();
            }

            process.waitFor();
        } catch (InterruptedException | IOException var13) {
            Exception e = var13;
            ((Exception)e).printStackTrace();
        }

    }

    static {
        sc = new Scanner(System.in);
    }
}
