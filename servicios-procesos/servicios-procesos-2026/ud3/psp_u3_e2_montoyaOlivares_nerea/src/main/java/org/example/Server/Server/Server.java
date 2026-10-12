package org.example.Server.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Dedicada a la escucha continua de clientes que se conecten.
 * Acepta las conexiones.
 * Crea un hilo por cada cliente conectado permitiendo que puedan mantener una conversación.
 */
public class Server {

    //Puerto al que se queda escuchando
    private int PUERTO = 5555;

    public Server() {

        //Abre el puerto donde los clientes se conectan
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {

            //El servidor siempre se queda escuchando
            while (true) {

                //Espera que los clientes se conecten
                Socket socket = serverSocket.accept();

                //Mensaje en servidor cuando el cliente se conecte
                System.out.println("Client connected from " + socket.getInetAddress() + ":" + socket.getPort());

                //Crear un hilo por cada cliente conectado
                ClientThreads hilo = new ClientThreads(socket);
                hilo.start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main (String[] args){
        new Server(); //Inicia Servidor
    }
}