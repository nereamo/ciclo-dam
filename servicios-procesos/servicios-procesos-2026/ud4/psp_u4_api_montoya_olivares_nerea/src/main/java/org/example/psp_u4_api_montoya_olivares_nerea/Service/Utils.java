package org.example.psp_u4_api_montoya_olivares_nerea.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.psp_u4_api_montoya_olivares_nerea.Model.VideoGame;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Encargada de guardar y mostrar los videojuegos.
 * Utiliza ObjectMapper para convertir objetos Java a JSON y viceversa.
 */

public class Utils {

    private final File dataBase = new File("Videojocs_DB.txt"); //Nombre del archivo
    private final ObjectMapper mapper = new ObjectMapper(); //Convierte objetos Java en JSON

    //Guarda los datos en el archivo "Videojocs_DB.txt" en formato JSON
    public void save(List<VideoGame> list) {
        try {
            //Convierte la lista a formato JSON y la escribe físicamente en el archivo
            mapper.writeValue(dataBase, list);
            System.out.println("Buscando el archivo en: " + dataBase.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error al guardar: " + e.getMessage());
        }
    }

    //Devuelve los datos almacenados en el archivo "Videojocs_DB.txt" en formato JSON
    public List<VideoGame> load() {

        if (!dataBase.exists()) { //Si el archivo no existe devuelve una lista vacía
            return new ArrayList<>();
        }

        try {
            //Lee el archivo y mapea el JSON a objetos Java.
            return mapper.readValue(dataBase, new TypeReference<List<VideoGame>>() {});
        } catch (IOException e) {
            System.err.println("Error al cargar: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
