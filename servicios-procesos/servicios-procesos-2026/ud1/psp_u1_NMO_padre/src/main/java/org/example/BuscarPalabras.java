package org.example;

import java.io.*;
import java.util.Scanner;
import static org.example.Utilidades.Utilidades.leer;

public class BuscarPalabras {

    public BuscarPalabras() {}

    public static void buscar() {

        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Que palabra quieres buscar? ");
            String palabra = sc.nextLine();

            while (palabra.trim().isEmpty() || !palabra.matches("[a-zA-Z]+")) {
                System.out.println("Por favor, introduce una palabra válida: ");
                palabra = sc.nextLine();
            }

            Process p = execPrograma(palabra);
            leer(p);
            p.waitFor();

        } catch (IOException e) {
            System.out.println("Error al obtener los datos del hijo: " + e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static Process execPrograma(String palabra){
        ProcessBuilder pb = new ProcessBuilder(new String[]{"java", "-cp", "../psp_u1_NMO_hijo/out/artifacts/psp_u1_NMO_hijo_jar/psp_u1_NMO_hijo.jar", "org.example.BuscarPalabras", palabra});
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