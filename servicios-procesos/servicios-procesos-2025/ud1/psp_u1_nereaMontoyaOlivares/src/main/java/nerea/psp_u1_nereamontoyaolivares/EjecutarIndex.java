
package nerea.psp_u1_nereamontoyaolivares;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
* @author Nerea
*/

public class EjecutarIndex {
    public static final Scanner sc;

    public EjecutarIndex() {
    }

    public static void ejecutarIndex() throws IOException {
        File file = new File("encrypted.txt");
        if (!file.exists()) {
            System.out.println("El archivo encrypted.txt no existe. ¿Deseas realizar la opción 6 antes de abrir el archivo index.html? (s/n)");
            String reply = sc.nextLine().trim().toLowerCase();
            if (reply.equals("s")) {
                CrearArchivoIndex.crearArchivoIndex();
            } else {
                System.out.println("No se puede proceder sin 'encrypted.txt'.");
            }
        }

        try {
            ProcessBuilder pb = new ProcessBuilder(new String[]{"java", "-cp", "../psp_u2_montoyaOlivaresNerea_recursos/out/artifacts/psp_u2_montoyaOlivaresNerea_recursos_jar/psp_u2_montoyaOlivaresNerea_recursos.jar", "programaHijo.EjecutarIndex"});
            pb.redirectErrorStream(true);
            Process process = pb.start();
            Exit.setProcess(process);
            process.getInputStream().transferTo(System.out);
            process.waitFor();
        } catch (InterruptedException | IOException var3) {
            Exception e = var3;
            ((Exception)e).printStackTrace();
        }

    }

    static {
        sc = new Scanner(System.in);
    }
}