package org.example.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Servidor a la escucha de clientes y acepta la conexión.
 * Acciones que realiza: INSERT, SELECT y DELETE.
 * Devuelve respuesta al cliente.
 */
public class Server {

    private static int PORT = 5555;

    public static void main(String[] args) {

        //Conexión a puerto
        try (ServerSocket ss = new ServerSocket(PORT)) {

            //Acepta la conexión del cliente, PW para enviar datos, BR para recibir datos
            try (Socket s = ss.accept();
                 BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                 PrintWriter pw = new PrintWriter(s.getOutputStream(), true)) {

                //Mensaje en servidor cuando el cliente se conecte
                System.out.println("Client connected from " + s.getInetAddress() + ":" + s.getPort());

                //Bucle principal
                String line;
                while ((line = br.readLine()) != null) {

                    //Divide los datos con comas
                    String[] parts = line.split(",");

                    //1. Si se reciben tres datos: INSERT
                    if (parts.length == 3) {

                        int id = Integer.parseInt(parts[0]);
                        String name = parts[1];
                        String lastName = parts[2];

                        //Crea, guarda y envía respuesta al cliente
                        pw.println(ServerManager.createAndSaveClientDDBB(id, name, lastName));

                        //2. Si se reciben dos datos (id + accion): SELECT o DELETE
                    } else if (parts.length == 2) {

                        int id = Integer.parseInt(parts[0]);
                        String action = parts[1];

                        if (action.equals("select")) { //Acción SELECT, muestra el cliente solicitado
                            pw.println(ServerManager.selectClientDDBB(id));

                        } else if (action.equals("delete")) { //Acción DELETE, elimina el cliente solicitado
                            pw.println(ServerManager.deleteClientDDBB(id));
                        }
                    } else {
                        pw.println("Error: Not all required data provided");
                    }
                }

                //Mensaje en servidor cuando el cliente se desconecte
                System.out.println("Client disconnected from " + s.getInetAddress() + ":" + s.getPort());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
