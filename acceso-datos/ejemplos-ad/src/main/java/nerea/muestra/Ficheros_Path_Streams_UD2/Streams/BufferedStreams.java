
package nerea.muestra.Ficheros_Path_Streams_UD2.Streams;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
* @author Nerea
*/

public class BufferedStreams {
    
    public void escribeBuffer(String destino, String texto){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(destino))) {
            writer.write(texto);
            writer.newLine();
            writer.flush();  // Forzar escritura inmediata
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String leeBuffer(String destino) throws IOException {
        StringBuilder contenido = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(destino))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contenido.append(line);
            }
        }
        return contenido.toString();
    }
}

