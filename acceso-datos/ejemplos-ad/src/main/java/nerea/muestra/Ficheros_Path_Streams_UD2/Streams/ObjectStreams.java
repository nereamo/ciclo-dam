
package nerea.muestra.Ficheros_Path_Streams_UD2.Streams;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
* @author Nerea
*/

public class ObjectStreams{
    
    public void escriureProductes(String destino, List<Producte> productes) {
    try (ObjectOutputStream out = new ObjectOutputStream(
            new BufferedOutputStream(new FileOutputStream(destino)))) {

        for (Producte p : productes) {
            out.writeObject(p);
        }

    } catch (IOException e) {
        System.err.println("Error en escriure: " + e.getMessage());
    }
}
    
    public List<Producte> llegirProductes(String destino) {
        List<Producte> productes = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(destino)))) {

            while (true) {
                    Producte p = (Producte) in.readObject();
                    productes.add(p); 
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error en llegir: " + e.getMessage());
        }
        return productes;
    }
}
