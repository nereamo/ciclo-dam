package org.example.Server.Client;

import org.example.Server.ChatManager.ChatManager;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Crea un cliente que se conecta a un puerto para poder mantener conversaciones con otros clientes.
 */
public class Client {

    private ChatManager chatManager = new ChatManager();
    private int PUERTO = 5555;
    private String SERVER_IP = "localhost";

    public Client() {

            //Conecta con servidor, PW para enviar datos, BR para recibir datos, SC lee entrada
        try(Socket socket = new Socket (SERVER_IP, PUERTO);
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner sc = new Scanner(System.in)){

            //DNI que inicia conversación, uso de metodo para comprobar formato DNI
            String dni1 = ClientUtils.comprobarDni(sc, "Please enter your DNI (format 00000000L): ");

            //DNI al que enviar mensajes, uso de metodo para comprobar formato DNI
            String dni2 = ClientUtils.comprobarDni(sc, "Please enter the other DNI (format 00000000L): ");

            //Enviar un JSON vacío para pedir el historial
            JSONObject jsonInit = chatManager.createJSON(dni1, dni2, "");
            pw.println(jsonInit.toString());

            //Muestra chat inicial si lo hubiera
            ClientUtils.mostrarChat(br);

            //Bucle principal
            while (true) {

                System.out.print("Send: ");
                String msg = sc.nextLine(); //Mensaje que se va a enviar

                //Enviar el json al servidor
                JSONObject json = chatManager.createJSON(dni1, dni2, msg);
                pw.println(json.toString());

                //Escribir "adeu" cierra conexión y para el hilo
                if (msg.equalsIgnoreCase("adeu")) {
                    System.out.println("Chat is close");
                    break;
                }

                //Mostrar chat completo
                ClientUtils.mostrarChat(br);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main (String[] args){
        new Client(); //Iniciar cliente
    }
}