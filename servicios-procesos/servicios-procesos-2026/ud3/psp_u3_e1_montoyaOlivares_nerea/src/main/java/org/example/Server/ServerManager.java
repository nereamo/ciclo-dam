package org.example.Server;

import org.example.Client.Client;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Crea, leer, buscar y eliminar clientes del archivo bbdd.txt
 */
public class ServerManager {

    private static String fileName = "bbdd.txt";

    /**
     * Recibe los datos y los separa por comas
     */
    private static Client partsOfClient(String line){

        String[] parts = line.split(",");
        int id = Integer.parseInt(parts[0]);
        String name = parts[1];
        String lastName = parts[2];

        //convierte los datos en un objeto Cliente
        return new Client(id, name, lastName);
    }

    /**
     * Recibe los datos solicitados por parámetro.
     * Si el archivo aún no existe, lo crea.
     * Guarda los objeto Client en el archivo
     */
    public static String createAndSaveClientDDBB(int id, String name, String lastName) throws IOException {

        //Comprueba si el archivo existe, sino, lo crea
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }

        //Lee el archivo linea a linea
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {

                Client existingClient = partsOfClient(line); //Metodo que separa los datos por comas

                //Evita duplicado: si ID ya existe no añade el cliente a la DDBB
                if(existingClient.getId() == id){
                    return "ERROR: ID already exists. Please try with another ID.";
                }

                //Evita duplicado: Si contiene el mismo nombre y apellido no añade el cliente a la DDBB
                if (existingClient.getName().equalsIgnoreCase(name) && existingClient.getLastName().equalsIgnoreCase(lastName)) {
                    return "ERROR: Client already exists";
                }
            }
        }

        //Si no existe, crea objeto y lo añade al archivo
        Client newClient = new Client(id, name, lastName);

        //Escritura del cliente en el archivo
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(id + "," + name + "," + lastName);
            bw.newLine();
        }
        return "Client saved -> " + newClient; //Devuelve respuesta si es correcto
    }

    /**
     * Obtiene el cliente solicitado mediante el id introducido
     */
    public static String selectClientDDBB(int id) throws IOException {

        //Lee archivo linea a linea
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                Client existingClient = partsOfClient(line); //Metodo que separa los datos por comas

                if (existingClient.getId() == id ) { //Si el id introducido existe, muestra los datos del cliente
                    return "Client found -> " + existingClient;
                }
            }
        }
        return "ERROR: Client not found"; //Si no se encontró el cliente solicitado
    }

    /**
     * Elimina el cliente solicitado mediante el id introducido
     */
    public static String deleteClientDDBB(int id) throws IOException {

        List<Client> clientList = new ArrayList<>(); //Guarda los clientes que se quedan
        Client clientDelete = null; //Guarda el cliente que será eliminado

        //Lee archivo linea a linea
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                Client c = partsOfClient(line);//Metodo que separa los datos por comas
                if (c.getId() == id) {
                    clientDelete = c; //Si coincide el "id", lo elimina
                } else {
                    clientList.add(c); //Añade los clientes restantes a la lista
                }
            }
        }

        if (clientDelete == null) { //Si no encontró el cliente devuelve msg
            return "ERROR: Client not found";
        }

        //Reescribe archivo con los clientes actuales de la lista
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, false))) {
            for (Client cli : clientList) {
                bw.write(cli.getId() + "," + cli.getName() + "," + cli.getLastName());
                bw.newLine();
            }
        }
        return "Client deleted -> " + clientDelete; //Devuelve respuesta si es correcto
    }
}