
package nerea.muestra.Ficheros_Path_Streams_UD2.JSON;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import nerea.muestra.Ficheros_Path_Streams_UD2.JSON.POJO.Modul;

/**
* @author Nerea
*/

public class Json {

    /**
     * Escribe objeto dentro de un fichero en formato JSON.
     * @param ruta
     * @param object
     * @throws IOException 
     */
    public void escribeJson(String ruta, Object object) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new BufferedWriter(new FileWriter(ruta)), object);
    }

    /**
     * Escribe objeto dentro de un fichero en formato JSON.
     * @param ruta
     * @param array
     * @throws IOException 
     */
    public void escribeJsonArray(String ruta, Modul[] array) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new BufferedWriter(new FileWriter(ruta)), array);
    }
    
    /**
     * Método genérico para leer cualquier tipo de estructura simple (una clase) JSON usando TypeReference
     * @param <T>
     * @param ruta
     * @param tipo
     * @return
     * @throws IOException 
     */
    public <T> T leerGenérico(String ruta, Class<T> tipo) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new BufferedReader(new FileReader(ruta)), tipo);
    }
    
    /**
     * Método genérico para leer cualquier tipo de estructura compleja (ArrayList, Array[], maps...) JSON usando TypeReference
     * @param <T>
     * @param ruta
     * @param tipo
     * @return
     * @throws IOException 
     */
    public <T> T leerGenérico2(String ruta, TypeReference<T> tipo) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new BufferedReader(new FileReader(ruta)), tipo);
    }
    
    /**
     * Lee un objeto JSON desde una URL y lo convierte en una instancia de la clase proporcionada.
     * @param <T>
     * @param uriStr
     * @param tipo
     * @return
     * @throws Exception 
     */
    public <T> T leerDesdeUrl(String uriStr, Class<T> tipo) throws Exception {
        ObjectMapper om = new ObjectMapper();
        URI uri = new URI(uriStr);
        URL url = uri.toURL();
        return om.readValue(url, tipo);
    }
}
