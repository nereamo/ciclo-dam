package org.example;

import org.example.Utilidades.Utilidades;

import java.io.*;
import java.util.Scanner;

public class ContarLetras {

    public ContarLetras(){}

    public static void contar(String url) {

        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Que letra quieres contar? ");
            String letras = sc.nextLine();

            while (letras.length() != 1 || !Character.isLetter(letras.charAt(0))) {
                System.out.println("Por favor, introduce un solo carácter: ");
                letras = sc.nextLine();
            }

            char letra = letras.charAt(0);

            Process p = execPrograma(letra);
            Utilidades.escribir(p,url);
            p.waitFor();
            Utilidades.leer(p);

        } catch (IOException e) {
            System.out.println("Error al obtener los datos del hijo: " + e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static Process execPrograma(char letra){
        ProcessBuilder pb = new ProcessBuilder(new String[]{"java", "-cp", "../psp_u1_NMO_hijo/out/artifacts/psp_u1_NMO_hijo_jar/psp_u1_NMO_hijo.jar", "org.example.ContarLetra", String.valueOf(letra)});
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