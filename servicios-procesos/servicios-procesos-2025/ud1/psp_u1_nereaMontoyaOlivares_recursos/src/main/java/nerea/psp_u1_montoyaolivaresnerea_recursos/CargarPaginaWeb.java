
package nerea.psp_u1_montoyaolivaresnerea_recursos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
* @author Nerea
*/

public class CargarPaginaWeb {
    public CargarPaginaWeb() {
    }

    public static void main(String[] args) {
        try {
            StringBuilder htmlContent = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String urlContent = reader.readLine();
            URL urlPage = new URL(urlContent);
            HttpURLConnection connection = (HttpURLConnection)urlPage.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            BufferedReader urlReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while((line = urlReader.readLine()) != null) {
                htmlContent.append(line).append("\n");
            }

            urlReader.close();
            connection.disconnect();
            String html = htmlContent.toString();
            System.out.println(html);
        } catch (IOException var9) {
            IOException e = var9;
            System.out.println("Error al cargar la página: " + e.getMessage());
        }

    }
}