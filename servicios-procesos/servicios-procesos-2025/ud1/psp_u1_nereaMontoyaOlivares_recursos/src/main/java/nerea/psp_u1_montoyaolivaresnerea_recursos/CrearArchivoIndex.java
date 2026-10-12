
package nerea.psp_u1_montoyaolivaresnerea_recursos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
* @author Nerea
*/

public class CrearArchivoIndex {
    public CrearArchivoIndex() {
    }

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("encrypted.txt"));
            StringBuilder bodyContent = new StringBuilder();

            String line;
            while((line = reader.readLine()) != null) {
                bodyContent.append(line).append("\n");
            }

            reader.close();
            BufferedWriter writer = new BufferedWriter(new FileWriter("index.html"));
            writer.write("<html>\n<head>\n<title>Contenido del Body</title>\n</head>\n<body>\n");
            writer.write(bodyContent.toString());
            writer.write("</body>\n</html>");
            writer.close();
            System.out.println("Archivo index.html creado.");
        } catch (IOException var5) {
            IOException e = var5;
            System.err.println("Error al leer el archivo o escribir el archivo index.html: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
