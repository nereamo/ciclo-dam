package org.example.Server.ChatManager;

import org.json.JSONObject;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Contiene los metodos necesários para la creación del archivo JSON, escritura y lectura.
 */
public class ChatManager {

    private static final String CHAT_DIR = "history_chat";

    /**
     * Crea JSON a partir de los parámetros solicitados (dni1, dni2, msg).
     * Retorna objeto JSON
     */
    public JSONObject createJSON(String dni1, String dni2, String msg){

        JSONObject json = new JSONObject();
        json.put("dni1", dni1); //Key - value
        json.put("dni2", dni2);
        json.put("msg", msg);
        return json;
    }

    /**
     * Guarda el chat entre clientes.
     * Guarda la fecha, hora, cliente que envía el mensaje y el contenido del mensaje.
     */
    public void saveJSON(JSONObject json) throws IOException {

        //Obtener los datos del JSON recibido
        String dni1 = json.getString("dni1");
        String dni2 = json.getString("dni2");
        String msg = json.getString("msg");

        if(msg == null || msg.trim().isEmpty()){
            return;
        }

        //Obtiene el archivo JSON que corresponda con los DNI's
        String fileName = getChatFileName(dni1, dni2);

        //Fecha y hora del mensaje
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        //Escritura en JSON con el formato (00000000L: textoDelMensajeAqui)
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
            bw.write("[" + timestamp + "] " + dni1 + ": " + msg);
            bw.newLine();
        }
    }

    /**
     * Lee contenido del archivo y lo muestra al cliente
     */
    public String readJSON(String dni1, String dni2) throws IOException {

        //Obtiene el archivo JSON que corresponda con los DNI's
        String fileName = getChatFileName(dni1, dni2);

        //Si el fichero no existe, devolverá vació
        File file = new File(fileName);
        if(!file.exists()){
            return "";
        }

        //Lee línea por línea el JSON y lo muestra al cliente
        StringBuilder sb = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) { //Siempre que contenga texto lo mostrará
                sb.append(line).append("\n");
            }
            return sb.toString(); //Devuelve la conversación completa
        }
    }

    /**
     * Crea directorio "history_chat" que almacena las conversaciones
     * Nombre de archivo único.
     * El orden de los DNI's no afecta al nombre de archivo.
     */
    private String getChatFileName(String dni1, String dni2){

        File dir = new File(CHAT_DIR); //Si el archivo no existe lo crea
        if(!dir.exists()){
            dir.mkdirs();
        }

        //Nombre del archivo que guarda el historial de mensajes entre clientes
        String file = dni1.compareTo(dni2) < 0 ? dni1 + "-" + dni2 : dni2 + "-" + dni1;

        return CHAT_DIR + File.separator + file + ".txt";
    }
}

