/*
 */

package nerea.psp_u5_cliente_montoyaolivaresnerea;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.util.Scanner;
import javax.crypto.SecretKey;
import nerea.psp_u5_cliente_montoyaolivaresnerea.crypto.AES_Simetric;
import nerea.psp_u5_cliente_montoyaolivaresnerea.crypto.RSA_Asimetric;
import nerea.psp_u5_cliente_montoyaolivaresnerea.model.packet;

/**
 *
 * @author Nerea
 */
public class Cliente {
    public Cliente() {
    }

    public static void main(String[] args) {
        try {
            // Conexión con servidor y solicita clave pública
            Socket socket = new Socket("localhost", 12345);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            // Recibe clave pública del servidor
            PublicKey serverPublicKey = (PublicKey)in.readObject();
            System.out.println("Clave pública del servidor recibida.");

            // Genera clave simétrica AES
            SecretKey secretKey = AES_Simetric.keygenKeyGeneration(128);

            // Enviar clave simétrica cifrada con clave pública del servidor
            byte[] encryptedSymmetricKey = RSA_Asimetric.encryptData(secretKey.getEncoded(), serverPublicKey);
            out.writeObject(encryptedSymmetricKey);
            System.out.println("Clave simétrica cifrada enviada al servidor.");

            // Enviar mensaje cifrado con AES
            Scanner sc = new Scanner(System.in);
            System.out.println("Escribir mensaje: ");
            String message = sc.nextLine();

            // Calcula el hash (SHA-256) del mensaje y cifra el contenido
            byte[] encryptedMessage = AES_Simetric.encryptData(secretKey, message.getBytes());
            byte[] messageHash = MessageDigest.getInstance("SHA-256").digest(message.getBytes());

            // Crear objeto 'packet' que contiene el mensaje cifrado y su hash
            packet packetToSend = new packet(encryptedMessage, messageHash);
            out.writeObject(packetToSend);
            out.flush();

            // Esperar respuesta del servidor
            String serverResponse = (String)in.readObject();
            System.out.println("Respuesta del servidor: " + serverResponse);
            
            socket.close();
        } catch (Exception var13) {
            Exception e = var13;
            e.printStackTrace();
        }

    }
}
