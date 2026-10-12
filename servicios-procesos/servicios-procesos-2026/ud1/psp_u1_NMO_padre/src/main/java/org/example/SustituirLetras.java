package org.example;

import java.io.*;
import java.util.Scanner;
import static org.example.Utilidades.Utilidades.*;

public class SustituirLetras {

    public SustituirLetras() {}

    public static void sustituir(String url) {

        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("Que letra quieres sustituir? ");
            String letraOld = sc.nextLine();

            while (letraOld.length() != 1 || !Character.isLetter(letraOld.charAt(0))) {
                System.out.println("Por favor, introduce un solo carácter: ");
                letraOld = sc.nextLine();
            }

            System.out.println("Introduce la nueva letra: ");
            String letraNew = sc.nextLine();

            while (letraNew.length() != 1 || !Character.isLetter(letraNew.charAt(0))){
                System.out.println("Por favor, introduce un solo carácter: ");
                letraNew = sc.nextLine();
            };

            Process p = execPrograma(letraOld, letraNew);
            escribir(p, url);
            leer(p);
            p.waitFor();

        } catch (IOException e) {
            System.out.println("Error al obtener los datos del hijo: " + e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static Process execPrograma(String letraOld, String letraNew) throws IOException {
        ProcessBuilder pb = new ProcessBuilder(new String[]{"java", "-cp", "../psp_u1_NMO_hijo/out/artifacts/psp_u1_NMO_hijo_jar/psp_u1_NMO_hijo.jar", "org.example.SustituirLetra", letraOld, letraNew});
        Process p = null;
        Menu.procesoHijo = p;

        try {
            p = pb.start();
            Menu.procesoHijo = p;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return p;
    }
}