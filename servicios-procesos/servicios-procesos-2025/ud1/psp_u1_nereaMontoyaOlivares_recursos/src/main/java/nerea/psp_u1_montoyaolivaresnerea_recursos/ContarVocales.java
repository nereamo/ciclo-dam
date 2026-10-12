
package nerea.psp_u1_montoyaolivaresnerea_recursos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
* @author Nerea
*/

public class ContarVocales {
    public ContarVocales() {
    }

    public static void main(String[] args) {
        char vocales = args[0].charAt(0);
        StringBuilder htmlContent = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            String line;
            try {
                while((line = reader.readLine()) != null) {
                    htmlContent.append(line).append("\n");
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
            System.out.println("Error al leer el contenido HTML: " + e.getMessage());
        }

        String html = htmlContent.toString();
        long count = html.chars().filter((ch) -> {
            return ch == vocales;
        }).count();
        System.out.println(count);
    }
}
