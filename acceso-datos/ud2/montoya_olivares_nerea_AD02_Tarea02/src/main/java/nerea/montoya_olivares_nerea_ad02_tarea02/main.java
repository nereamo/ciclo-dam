
package nerea.montoya_olivares_nerea_ad02_tarea02;

import nerea.montoya_olivares_nerea_ad02_tarea02.Json.Json;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import nerea.montoya_olivares_nerea_ad02_tarea02.Exceptions.UD2Exceptions;
import static nerea.montoya_olivares_nerea_ad02_tarea02.Fluxos.pruebasFluxos.pruebaDataStreams;
import static nerea.montoya_olivares_nerea_ad02_tarea02.Fluxos.pruebasFluxos.pruebasObjetoList;
import nerea.montoya_olivares_nerea_ad02_tarea02.directoryFiles.DirectoryFiles;
import nerea.montoya_olivares_nerea_ad02_tarea02.recursosTarea.Curs;
import nerea.montoya_olivares_nerea_ad02_tarea02.recursosTarea.Modul;

/**
* @author Nerea
*/

public class main {

    public static void main(String[] args) throws UD2Exceptions, IOException {

        //Exercici 1 -> Informacion de los archivos.
        DirectoryFiles.directoryContents();
        

        //Exercici 2 -> Fluxos
        pruebaDataStreams();
        pruebasObjetoList();

        
        //Exercici 3 -> Leer y copiar archivos .json
        try {
            String path2 = "\\Users\\Nerea\\Desktop\\DAM\\AD\\UD2\\JSON\\"; //Variable de la ubicacion de los archivos .json

            //Leer archivo modul.json y convertirlo a objeto Modul, mostrarlo y seguidamente guardarlo en una copia del archivo.
            Modul modul = Json.readJson(path2 + "modul.json", new TypeReference<Modul>() {});
            System.out.println("\nContenido de modul.json: \n" + modul);
            Json.writeJson(path2 + "modul_copy.json", modul);

            //Leer archivo curs.json y convertirlo a objeto Curs, mostrarlo y seguidamente guardarlo en una copia del archivo.
            Curs curs = Json.readJson(path2 + "curs.json", new TypeReference<Curs>() {});
            System.out.println("\ncontenido de curs.json: \n" + curs);
            Json.writeJson(path2 + "curs_copy.json", curs);

            //Leer ArrayList<Modul> y mostrar resultado.
            ArrayList<Modul> modulsArray = Json.readJson(path2 + "arrayModuls.json", new TypeReference<ArrayList<Modul>>() {}); 
            System.out.println("\nContenido de arrayModuls.json como lista: \n" + modulsArray);
            
            //Guardar el ArrayList como Array[] en una copia de arrayModuls y mostrar resultado
            Modul[] arrayModuls = modulsArray.toArray(new Modul[0]);
            System.out.println("\nContenido de arrayModuls.json como Modul[]: \n" + Arrays.toString(arrayModuls));
            Json.writeJson(path2 + "arrayModuls_copy.json", arrayModuls);
            
            System.out.println("\nDiferencia entre ArrayList y Array, visiblemente no hay diferencias, lo único distinto esque ArrayList es redimensionable y Array de tamaño predefinido.");

        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
