
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

public class SustituirLetras {
    public static final Scanner sc;

    public SustituirLetras() {
    }

    public static void sustituirLetras() throws IOException {
        try {
            System.out.println("Introduce la letra a sustituir:");
            String letraAntigua = sc.nextLine();
            System.out.println("Introduce una nueva letra:");
            String letraNueva = sc.nextLine();
            if (letraAntigua.length() != 1 || letraNueva.length() != 1 || !Character.isLetter(letraAntigua.charAt(0)) || !Character.isLetter(letraNueva.charAt(0))) {
                System.out.println("Error: Debes introducir dos letras válidas.");
                return;
            }

            ProcessBuilder pb = new ProcessBuilder(new String[]{"java", "-cp", "../psp_u2_montoyaOlivaresNerea_recursos/out/artifacts/psp_u2_montoyaOlivaresNerea_recursos_jar/psp_u2_montoyaOlivaresNerea_recursos.jar", "programaHijo.SustituirLetras", letraAntigua, letraNueva});
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
                        System.out.println(line);
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

