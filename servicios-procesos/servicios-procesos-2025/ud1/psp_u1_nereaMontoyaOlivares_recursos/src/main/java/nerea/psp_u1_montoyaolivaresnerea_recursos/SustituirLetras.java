
package nerea.psp_u1_montoyaolivaresnerea_recursos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;

/**
* @author Nerea
*/

public class SustituirLetras {
    public SustituirLetras() {
    }

    public static void main(String[] args) {
        String letraAntigua = args[0];
        String letraNueva = args[1];
        StringBuilder htmlContent = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            String line;
            try {
                while((line = reader.readLine()) != null) {
                    htmlContent.append(line).append("\n");
                }
            } catch (Throwable var9) {
                try {
                    reader.close();
                } catch (Throwable var8) {
                    var9.addSuppressed(var8);
                }

                throw var9;
            }

            reader.close();
        } catch (IOException var10) {
            IOException e = var10;
            e.printStackTrace();
        }

        String htmlModificado = htmlContent.toString().replace(letraAntigua, letraNueva);

        try {
            Files.write(Paths.get("encrypted.txt"), htmlModificado.getBytes(), new OpenOption[0]);
            System.out.println("Archivo creado.");
        } catch (IOException var7) {
            IOException e = var7;
            e.printStackTrace();
        }

    }
}
