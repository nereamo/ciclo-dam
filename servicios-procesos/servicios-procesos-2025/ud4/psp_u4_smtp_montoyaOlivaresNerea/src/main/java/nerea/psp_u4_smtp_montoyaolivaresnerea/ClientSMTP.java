/*
 */

package nerea.psp_u4_smtp_montoyaolivaresnerea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Base64;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 *
 * @author Nerea
 */
public class ClientSMTP {
    public ClientSMTP() {
    }

    public static void main(String[] args) {
        String smtpServer = "smtp.gmail.com";
        int port = 25;
        String remitente = "nmolivares.nm@gmail.com";
        String destinatario = "nmo.olivares@gmail.com";
        String asuntoMsg = "Asunto de prueba";
        String msg = "Este es un mensaje de prueba enviado desde el cliente SMTP.";
        String password = "lhvgjbaqswcrylzn";

        try {
            Socket socket = new Socket(smtpServer, port);

            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                try {
                    PrintWriter pW = new PrintWriter(socket.getOutputStream(), true);

                    try {
                        System.out.println(br.readLine());
                        pW.println("HELO example.com");
                        pW.flush();
                        System.out.println(br.readLine());
                        pW.println("STARTTLS");
                        System.out.println(br.readLine());
                        SSLSocketFactory f = (SSLSocketFactory)SSLSocketFactory.getDefault();
                        SSLSocket sslS = (SSLSocket)f.createSocket(socket, smtpServer, port, true);
                        BufferedReader brIn = new BufferedReader(new InputStreamReader(sslS.getInputStream()));
                        PrintWriter pwOut = new PrintWriter(sslS.getOutputStream(), true);
                        pwOut.println("HELO example.com");
                        pwOut.flush();
                        System.out.println(brIn.readLine());
                        pwOut.println("AUTH LOGIN");
                        pwOut.flush();
                        System.out.println(brIn.readLine());
                        String encodedEmail = Base64.getEncoder().encodeToString(remitente.getBytes());
                        String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());
                        pwOut.println(encodedEmail);
                        System.out.println(brIn.readLine());
                        pwOut.println(encodedPassword);
                        System.out.println(brIn.readLine());
                        pwOut.println("MAIL FROM: <" + remitente + ">");
                        pwOut.flush();
                        System.out.println(brIn.readLine());
                        pwOut.println("RCPT TO: <" + destinatario + ">");
                        pwOut.flush();
                        System.out.println(brIn.readLine());
                        pwOut.println("DATA");
                        pwOut.flush();
                        System.out.println(brIn.readLine());
                        System.out.println("\nEnviando mensaje...\n");
                        pwOut.println("Subject: " + asuntoMsg);
                        pwOut.println();
                        pwOut.println(msg);
                        pwOut.println(".");
                        pwOut.flush();
                        System.out.println(brIn.readLine());
                        pwOut.println("QUIT");
                        pwOut.flush();
                        System.out.println(brIn.readLine());
                    } catch (Throwable var20) {
                        try {
                            pW.close();
                        } catch (Throwable var19) {
                            var20.addSuppressed(var19);
                        }

                        throw var20;
                    }

                    pW.close();
                } catch (Throwable var21) {
                    try {
                        br.close();
                    } catch (Throwable var18) {
                        var21.addSuppressed(var18);
                    }

                    throw var21;
                }

                br.close();
            } catch (Throwable var22) {
                try {
                    socket.close();
                } catch (Throwable var17) {
                    var22.addSuppressed(var17);
                }

                throw var22;
            }

            socket.close();
        } catch (IOException var23) {
            IOException ex = var23;
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }

    }
}
