
package nerea.psp_u1_nereamontoyaolivares;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
* @author Nerea
*/

public class CargarPaginaWeb {
    public CargarPaginaWeb() {
    }

    public static void cargarPaginaWeb(String pagina) throws IOException {
        try {
            System.out.println(pagina);
            ProcessBuilder pb = new ProcessBuilder(new String[]{"java", "-cp", "../psp_u2_montoyaOlivaresNerea_recursos/out/artifacts/psp_u2_montoyaOlivaresNerea_recursos_jar/psp_u2_montoyaOlivaresNerea_recursos.jar", "programaHijo.CargarPaginaWeb"});
            Process process = pb.start();
            Exit.setProcess(process);

            IOException e;
            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));

                try {
                    writer.write(pagina);
                    writer.newLine();
                    writer.flush();
                } catch (Throwable var8) {
                    try {
                        writer.close();
                    } catch (Throwable var7) {
                        var8.addSuppressed(var7);
                    }

                    throw var8;
                }

                writer.close();
            } catch (IOException var9) {
                e = var9;
                e.printStackTrace();
            }

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                try {
                    StringBuilder htmlContent = new StringBuilder();

                    String line;
                    while((line = reader.readLine()) != null) {
                        htmlContent.append(line).append("\n");
                    }

                    Menu.html = htmlContent.toString();
                    System.out.println(Menu.html);
                    System.out.println("Html cargado con éxito!");
                } catch (Throwable var10) {
                    try {
                        reader.close();
                    } catch (Throwable var6) {
                        var10.addSuppressed(var6);
                    }

                    throw var10;
                }

                reader.close();
            } catch (IOException var11) {
                e = var11;
                e.printStackTrace();
            }

            process.waitFor();
        } catch (InterruptedException | IOException var12) {
            Exception e = var12;
            ((Exception)e).printStackTrace();
        }

    }
}
