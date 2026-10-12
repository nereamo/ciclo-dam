package org.example.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Conexión del cliente con el servidor.
 * Contiene un menú para realizar operaciones de INSERT, SELECT y DELETE en la DDBB.
 */
public class ClientSocket {

    private static Scanner sc = new Scanner(System.in);
    private static String SERVER_IP = "localhost";
    private static int PORT = 5555;

    public static void main(String[] args) {

        //Conecta con servidor, PW para enviar datos, BR para recibir datos
        try (Socket socket = new Socket(SERVER_IP, PORT);
             PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            //Bucle principal
            while (true) {

                //Mostrar menú de opciones
                System.out.println("\nPlease select an option (1 to 3): ");
                System.out.println("1. Insert a new Client.");
                System.out.println("2. Select a Client.");
                System.out.println("3. Delete a Client.");
                System.out.println("4. Exit");
                System.out.print("\nOption: ");

                int select = sc.nextInt(); //Lee número de opción
                sc.nextLine();
                System.out.println();

                if (select == 4) { //Opción 4, salir
                    System.out.println("Exiting...");
                    break;
                }

                //Evalúa opción introducida
                switch (select) {
                    case 1:
                        insertNewClient(pw, br); //INSERT
                        break;
                    case 2:
                        selectOrDeleteClient(pw, br, "select"); //SELECT
                        break;
                    case 3:
                        selectOrDeleteClient(pw, br, "delete"); //DELETE
                        break;
                    default:
                        System.out.println("Invalid option! Please choose an option"); //EXIT
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Pide al usuario los datos necesarios para que el servidor pueda añadir un nuevo cliente
     * Solicita Id, name y lastName y los envía al servidor.
     */
    public static void insertNewClient(PrintWriter pw, BufferedReader br) throws IOException {

        System.out.println("1. Insert a new Client.");

        System.out.print("ID: ");//id
        String idInput = sc.nextLine().trim();

        while (idInput.isEmpty() || !idInput.matches("\\d+")) { //Valida que id sea válido (números)
            System.out.println("ID cannot be empty and must be a number!");
            System.out.print("ID: ");
            idInput = sc.nextLine().trim();
        }
        int id = Integer.parseInt(idInput); //Convierte el string en tipo int

        System.out.print("Name: ");//nombre
        String name = sc.nextLine();

        while (name.isEmpty()){ //Validar que el nombre no este vacío
            System.out.println("Name cannot be empty!");
            System.out.print("Name: ");
            name = sc.nextLine().trim();
        }

        System.out.print("LastName: ");//apellido
        String lastName = sc.nextLine();

        while (lastName.isEmpty()){ //Validar que el apellido no este vacío
            System.out.println("LastName cannot be empty!");
            System.out.print("LastName: ");
            lastName = sc.nextLine().trim();
        }

        pw.println(id + "," + name + "," + lastName); //Envía datos al servidor

        String response = br.readLine(); //Lee respuesta
        System.out.println(response);
    }

    /**
     * Pide id del cliente sobre el que realizar la acción de SELECT o DELETE
     * Solicita Id del cliente y lo envía al servidor.
     */
    public static void selectOrDeleteClient(PrintWriter pw, BufferedReader br, String action) throws IOException {

        //Dependiendo de la acción enviada al servidor obtiene o elimina el cliente
        if(action .equals("select")){
            System.out.println("2. Select a Client.");
        }else if(action .equals("delete")){
            System.out.println("3. Delete a Client.");
        }

        System.out.print("ID: ");//id
        String idInput = sc.nextLine().trim();

        while (idInput.isEmpty() || !idInput.matches("\\d+")) { //Valida que id sea válido (números)
            System.out.println("ID cannot be empty and must be a number!");
            System.out.print("ID: ");
            idInput = sc.nextLine().trim();
        }
        int id = Integer.parseInt(idInput);//Convierte el string en tipo int

        pw.println(id + "," + action); //Envía id y la acción

        String response = br.readLine(); //Lee respuesta del servidor
        System.out.println(response);
    }
}