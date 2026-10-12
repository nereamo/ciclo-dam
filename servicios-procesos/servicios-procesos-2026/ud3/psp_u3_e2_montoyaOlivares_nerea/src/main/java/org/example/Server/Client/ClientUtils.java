package org.example.Server.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Contiene los metodos para comprobar formato de DNI y mostrar el chat
 * Clase perteneciente a Client
 */
public class ClientUtils {

    /**
     * Metodo utilizado para la comprobación del DNI introducido.
     * EL DNI debe tener un formato de ocho dígitos numéricos y una letra mayúscula final.
     */
    public static String comprobarDni(Scanner sc, String msg) {

        System.out.println(msg); //Solicita DNI a usuario
        String dni = sc.nextLine(); //Lee DNI enviado por usuario

        //Mientras el DNI no sea correcta se volverá a pedir
        while (dni == null || !dni.matches("^[0-9]{8}[A-Z]$")) {
            System.out.println("Invalid DNI. Try again: ");
            dni = sc.nextLine();
        }
        System.out.println("This DNI is correct.");
        return dni;
    }

    /**
     * Metodo utilizado para mostrar la conversación completa.
     */
    public static void mostrarChat(BufferedReader br) throws IOException {
        String line;

        //Almacena la conversación y la muestra línea por línea
        StringBuilder chat = new StringBuilder();
        while ((line = br.readLine()) != null && !line.equals("END")) {
            chat.append(line).append("\n");
        }

        //Si hay conversación guardada en el archivo se mostrará al cliente
        if(chat.length() > 0){
            System.out.println("\n======== CHAT ========");
            System.out.println(chat.toString());
            System.out.println();
        }
    }
}
