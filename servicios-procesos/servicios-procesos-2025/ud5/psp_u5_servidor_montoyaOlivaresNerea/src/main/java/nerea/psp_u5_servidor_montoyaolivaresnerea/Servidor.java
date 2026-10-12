/*
 */

package nerea.psp_u5_servidor_montoyaolivaresnerea;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import nerea.psp_u5_servidor_montoyaolivaresnerea.crypto.AES_Simetric;
import nerea.psp_u5_servidor_montoyaolivaresnerea.crypto.Hash;
import nerea.psp_u5_servidor_montoyaolivaresnerea.crypto.RSA_Asimetric;
import nerea.psp_u5_servidor_montoyaolivaresnerea.model.packet;

/**
 *
 * @author Nerea
 */
public class Servidor {
    public Servidor() {
    }

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            Socket clientSocket = serverSocket.accept();
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            KeyPair keyPair = RSA_Asimetric.randomGenerate(2048);
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();
            System.out.println("Enviando clave publica a cliente.");
            out.writeObject(publicKey);
            byte[] encryptedSymmetricKey = (byte[])in.readObject();
            byte[] symmetricKeyBytes = RSA_Asimetric.decryptData(encryptedSymmetricKey, privateKey);
            SecretKey secretKey = new SecretKeySpec(symmetricKeyBytes, "AES");
            packet receivedPacket = (packet)in.readObject();
            byte[] receivedMessage = receivedPacket.message;
            byte[] receivedHash = receivedPacket.hash;
            byte[] decryptedMessage = AES_Simetric.decryptData(secretKey, receivedMessage);
            byte[] secretKeyHash = MessageDigest.getInstance("SHA-256").digest(decryptedMessage);
            SecretKey calculatedHashKey = new SecretKeySpec(secretKeyHash, "AES");
            SecretKey receivedHashKey = new SecretKeySpec(receivedHash, "AES");
            boolean isValid = Hash.compareHash(receivedHashKey, calculatedHashKey);
            if (isValid) {
                PrintStream var10000 = System.out;
                String var10001 = new String(decryptedMessage);
                var10000.println("Mensaje recibido correctamente: " + var10001);
            } else {
                System.out.println("Mensaje modificado.");
            }

            out.writeObject("Mensaje Recibido");
            out.flush();
            clientSocket.close();
            serverSocket.close();
        } catch (Exception var19) {
            Exception e = var19;
            e.printStackTrace();
        }

    }
}

