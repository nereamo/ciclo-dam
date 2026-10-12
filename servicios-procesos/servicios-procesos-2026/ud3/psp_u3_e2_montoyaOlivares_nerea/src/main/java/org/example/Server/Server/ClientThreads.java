package org.example.Server.Server;

import org.example.Server.ChatManager.ChatManager;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Crea un hilo por cada cliente conectado al servidor.
 * Esta clase es parte del servidor.
 */
public class ClientThreads extends Thread{

    private ChatManager chatManager = new ChatManager();
    private Socket socket;

    public ClientThreads(Socket socket) {
        this.socket = socket;
    }

    //Metodo que inicia de los hilos (clientes)
    @Override
    public void run() {

        //PW para enviar datos, BR para recibir datos
        try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter pw = new PrintWriter(socket.getOutputStream(), true)){

            //Bucle principal, procesa los mensajes que envían los clientes
            String line;
            while((line = br.readLine()) != null) {

                //Convertir JSON en Objeto, enviando los tres parámetros (DNI1, DNI2, MSG)
                JSONObject json = new JSONObject(line);
                String dni1 = json.getString("dni1");
                String dni2 = json.getString("dni2");
                String msg = json.getString("msg");

                //Guarda los DNI's y el mensaje en JSON
                if (!msg.trim().isEmpty()) {
                    chatManager.saveJSON(json);
                }

                //Lee chat correspondiente a los DNI's
                String chat = chatManager.readJSON(dni1, dni2);

                //Si el JSON no etá vació, envía el contenido completo al cliente
                if(!chat.isEmpty()) {
                    for(String linesChat : chat.split("\n")){
                        pw.println(linesChat);
                    }
                }

                pw.println("END"); //Indicador de que no hay más líneas que mostrar

                //Si escribe "adeu", detiene bucle y cierra conexión.
                if ("adeu".equalsIgnoreCase(msg)) {
                    System.out.println("Client disconnected from " + socket.getInetAddress() + ":" + socket.getPort());
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Client disconnected unexpectedly.");
        }
    }
}

