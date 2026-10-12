
package nerea.psp_u3_montoyaolivaresnerea.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
* @author Nerea
*/

public class HilosServer extends Thread{

    private Socket skCliente;   //conexión de red con el usuario
    private String nombreUser;

    public HilosServer(Socket sCliente, String nombreUser) {
        skCliente=sCliente; //Inicia un socket para cada usuario
        this.nombreUser = nombreUser;
    }

    //Metodo run que inicia un hilo por cada usuario conectado
    @Override
    public void run() {
        try {
            DataInputStream flujo_entrada = new DataInputStream(skCliente.getInputStream());    //Lee datos desde el socket (lle mensajes enviados po el usuario)
            DataOutputStream flujo_salida = new DataOutputStream(skCliente.getOutputStream()); //Envia datos al usuario

            String mensaje;
            do {
                mensaje = flujo_entrada.readUTF();  //lee el mensaje enviado por el usuario
                System.out.println("Mensaje recibido de " + "[" + nombreUser + "]" +": " + mensaje);  //Línea que se muestra en el servidor
                Server.broadcast(nombreUser, mensaje);  //Cuando el server recibe el mensaje, lo reenvía a los usuarios
            } while (!mensaje.equalsIgnoreCase("exit"));   //Si un usuario envía 'exit' se cierra el bucle

            synchronized (Server.clientesTCP) {
                Server.clientesTCP.remove(flujo_salida);    //Elimina flujo_salida si un usuario no está
            }

            flujo_salida.close();
            skCliente.close();

            Server.notificarConexion(nombreUser, false, skCliente); //Notifica si un usuario se ha desconectado.

        } catch (IOException e) {
            System.out.println("Error en el hilo del servidor: " + e.getMessage());
        }
    }
}
