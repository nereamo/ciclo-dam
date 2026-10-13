
package nerea.muestra.Ficheros_Path_Streams_UD2.Streams;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Nerea
 */

public class ByteStreams {

    // Método para crear un archivo con texto por bytes.
    public void escribeBytes(String destino, String datos) {
        try (FileOutputStream out = new FileOutputStream(destino)) {
            out.write(datos.getBytes());
        } catch (IOException e) {
            System.out.println("Error al escribir: " + e.getMessage());
        }
    }

    public void leeBytes(String origen) {
        try (FileInputStream in = new FileInputStream(origen)) {
            int c;
            while ((c = in.read()) != -1) {
                byte b = (byte)c;
                char ch = (char) c;
                System.out.print(b);
                System.out.print(ch);
            }
        } catch (IOException e) {
            System.out.println("Error al leer: " + e.getMessage());
        }
    }
}
