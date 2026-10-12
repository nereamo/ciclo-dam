
package nerea.montoya_olivares_nerea_ad02_tarea02.Json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

//Metodos para leer y escribir archivos .json

//Adaptación de ChatGPT

/**
* @author Nerea
*/

public class Json {

    static final ObjectMapper mapper = new ObjectMapper();  //Variable mapper

    //<T> permite que el método pueda devolver cualquier tipo de objeto. 
    //TypeReference<T> typeRef: manejar tipos genéricos y decirle a Jackson el tipo de objeto que debe crear al deserializar el JSON.
    //BufferedReader se usa para leer el archivo de forma eficiente
    
    //Método que lee un archivo JSON y lo convierte en un objeto Java.
    public static <T> T readJson(String ruta, TypeReference<T> typeRef) throws IOException {
        return mapper.readValue(new BufferedReader(new FileReader(ruta)), typeRef);
    }
    
    
    //Método que escribe un Objeto Java en un documento .json
    public static void writeJson(String ruta, Object obj) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(ruta), obj);
    }

}
