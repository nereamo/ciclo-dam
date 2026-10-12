/*
 */

package nerea.psp.u4_ftp_montoyaolivaresnerea;

import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;

/**
 *
 * @author Nerea
 */
public class ClienteFTP {
    public ClienteFTP() {
    }

    public static void main(String[] args) {
        String server = "test.rebex.net";
        int port = 21;
        String user = "demo";
        String pass = "password";
        FTPClient ftpClient = new FTPClient();

        try {
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            System.out.println("Conectado al servidor FTP: " + server);
            System.out.println("\nContenido del servidor FTP:");
            String[] files = ftpClient.listNames();
            if (files != null && files.length > 0) {
                String[] var7 = files;
                int var8 = files.length;

                for(int var9 = 0; var9 < var8; ++var9) {
                    String file = var7[var9];
                    System.out.println(" - " + file);
                }
            } else {
                System.out.println("No hay archivos en el servidor.");
            }

            ftpClient.logout();
            ftpClient.disconnect();
            System.out.println("\nConexión cerrada.");
        } catch (IOException var11) {
            IOException ex = var11;
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }

    }
}
