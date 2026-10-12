
package nerea.psp_u1_nereamontoyaolivares;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
* @author Nerea
*/

public class LeerEncrypted {
    public LeerEncrypted() {
    }

    public static void leerEncrypted() throws IOException {
        ProcessBuilder pb = new ProcessBuilder(new String[]{"java", "-cp", "../psp_u2_montoyaOlivaresNerea_recursos/out/artifacts/psp_u2_montoyaOlivaresNerea_recursos_jar/psp_u2_montoyaOlivaresNerea_recursos.jar", "programaHijo.LeerEncrypted"});
        pb.redirectErrorStream(true);
        Process process = pb.start();
        Exit.setProcess(process);

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            try {
                System.out.println("Este es el contenido del archivo encrypted.txt");

                while(true) {
                    String line;
                    if ((line = reader.readLine()) == null) {
                        process.waitFor();
                        break;
                    }

                    System.out.println(line);
                }
            } catch (Throwable var6) {
                try {
                    reader.close();
                } catch (Throwable var5) {
                    var6.addSuppressed(var5);
                }

                throw var6;
            }

            reader.close();
        } catch (InterruptedException | IOException var7) {
            Exception e = var7;
            ((Exception)e).printStackTrace();
        }

    }
}

