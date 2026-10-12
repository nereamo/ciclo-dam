
package nerea.psp_u3_montoyaolivaresnerea.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
* @author Nerea
*/

public class Server {
    static final int Puerto_TCP = 2000;
    static final int Puerto_UDP = 3000;
    static final String multicast = "230.0.0.0";
    static List<DataOutputStream> clientesTCP = new ArrayList<>();
    static Set<String> listUsuarios = new HashSet<>();

    public Server() {
        try (ServerSocket sServidor = new ServerSocket(Puerto_TCP)) { //Servidor
            System.out.println("Servidor iniciado en el puerto " + Puerto_TCP);

            while (true) {   //Bucle para la conexión de usuarios de forma concurrente
                Socket skCliente = sServidor.accept();

                DataInputStream flujo_entrada = new DataInputStream(skCliente.getInputStream());   //Entrada de datos
                String nombreUser = flujo_entrada.readUTF();    //Recibe el nombre del usuario conectado

                //Muestra la IP, el puerto y el nombre del Usuario conectado
                System.out.println("Cliente conectado: " + skCliente + " - Usuario: " + nombreUser);

                DataOutputStream flujo_salida = new DataOutputStream(skCliente.getOutputStream()); //Salida de datos

                synchronized (clientesTCP) {    //'synchronized' evita problemas de concurrencia
                    clientesTCP.add(flujo_salida);
                    listUsuarios.add(nombreUser); //Se van añadiendo los usuarios
                }

                notificarConexion(nombreUser, true, skCliente); //Notifica al resto de usuarios que un nuevo usuario se ha unido

                new HilosServer(skCliente, nombreUser).start(); // Inicia un hilo para la comunicación
            }

        } catch (Exception e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }


    //Metodo para enviar los mensajes a todos los usuarios
    public static void broadcast(String nombreUsuario, String mensaje) {
        synchronized (clientesTCP) {    //'synchronized' evita problemas de concurrencia
            for (DataOutputStream cliente : clientesTCP) {
                try {
                    cliente.writeUTF("\n[" + nombreUsuario + "]: " + mensaje);
                } catch (Exception e) {
                    System.out.println("Error enviando mensaje a cliente: " + e.getMessage());
                }
            }
        }
    }

    //Metodo para enviar las notificaciones a los clientes usando UDP multicast
    public static void enviarNotificacionUDP(String mensaje) {
        try (DatagramSocket socketUDP = new DatagramSocket()) { //'DatagramSocket' sirve para enviar paquetes UDP
            byte[] buffer = mensaje.getBytes(); //El mensaje es enviado a través de la red en formato binario para
            InetAddress grupo = InetAddress.getByName(multicast); //Obtiene IP
            DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, grupo, Puerto_UDP);  //Paquete que se envía
            socketUDP.send(paquete);
        } catch (Exception e) {
            System.out.println("Error enviando notificación UDP: " + e.getMessage());
        }
    }

    //Metodo para notificar cuando un cliente se ha conectado/desconectado
    public static void notificarConexion(String nombreUsuario, boolean conectado, Socket skCliente) {
        String mensaje = conectado
                ? "\nNOTIFICACIÓ: [" + nombreUsuario + "] se ha unido al chat."
                : "\nNOTIFICACIÓ: [" + nombreUsuario + "] se ha desconectado del chat.";

        enviarNotificacionUDP(mensaje);

        if (!conectado) {
            System.out.println("Cliente desconectado: " + skCliente + " - Usuario: " + nombreUsuario);
        }
    }
}
