
package nerea.psp_u3_montoyaolivaresnerea.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.Scanner;

/**
* @author Nerea
*/

public class Client {

    static final String host = "localhost"; //Dirección del servidor
    static final int puerto_TCP = 2000;
    static final int puerto_UDP = 3000;
    static final String multicast = "230.0.0.0";
    private volatile boolean activo = true;

    public Client() {
        try (Socket skCliente = new Socket(host, puerto_TCP); //Nuevo socket TCP conectado al servidor mediante el puerto 2000
             DataInputStream flujo_entrada = new DataInputStream(skCliente.getInputStream());   //Entrada de datos
             DataOutputStream flujo_salida = new DataOutputStream(skCliente.getOutputStream())) {   //Salida de datos

            Scanner scanner = new Scanner(System.in);
            System.out.print("Introduce tu nombre de usuario: ");   //Solicitar el nombre del usuario
            String nombreUsuario = scanner.nextLine();

            flujo_salida.writeUTF(nombreUsuario);   //Envía al servidor el nombre del usuario

            //Hilo para recibir los mensajes de los usuarios y mostraros por consola
            Thread recibirMensajes = new Thread(new Runnable(){
                @Override
                public void run() {

                    try {
                        while (true) {

                            String mensaje = flujo_entrada.readUTF();   //Lee mesajes del servidor
                            if (!mensaje.startsWith("\n[" + nombreUsuario + "]")) {   //El mensaje no se mostrará en el propio usuario
                                System.out.println(mensaje);
                                System.out.print("\n" + nombreUsuario + ": ");
                            }

                        }
                    } catch (Exception e) {
                        System.out.println("Desconectado del servidor.");
                    }
                }
            });
            recibirMensajes.start();

            //Hilo para recibir las notificaciones y mostrarlas a los usuarios
            Thread recibirNotificacionesUDP = new Thread( new Runnable() {
                @Override
                public void run() {
                    try (MulticastSocket socketUDP = new MulticastSocket(puerto_UDP)) { //'MulticastSocket' permite la comunicación de varios usuarios
                        InetAddress grupo = InetAddress.getByName(multicast);   //Obtiene IP del multicast
                        socketUDP.joinGroup(grupo); //Socket recibe mensajes del multicast

                        byte[] buffer = new byte[1024]; //Buffer que almacena los datos del paquete UDP, con su tamaño
                        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);

                        //Bucle para recibir datos
                        while (activo) {

                            socketUDP.receive(paquete); //A la espera de recibir paquete
                            String mensaje = new String(paquete.getData(), 0, paquete.getLength()); //Extrae datos y se convierten en String
                            System.out.println(mensaje);
                        }
                    } catch (Exception e) {
                        System.out.println("Error en el cliente UDP: " + e.getMessage());
                    }
                }
            });
            recibirNotificacionesUDP.start();

            //Bucle para enviar los mensajes al servidor TCP
            while (true) {
                String mensaje = scanner.nextLine();
                flujo_salida.writeUTF(mensaje);

                if (mensaje.equalsIgnoreCase("exit")) {    //Si el mensaje del usuario es 'exit' cierra conexión
                    break;
                }
            }
            activo = false;
            recibirMensajes.interrupt();
            recibirNotificacionesUDP.interrupt();

            skCliente.close();
            scanner.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
