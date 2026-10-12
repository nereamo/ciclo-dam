package org.example;

import java.io.IOException;
import java.util.Scanner;

/**
 * Interfaz del usuario.
 * Contiene un menú de opciones.
 */

public class Client {

    private static Scanner sc = new Scanner(System.in); //Lee entrada por teclado
    private static ApiClient apiclient = new ApiClient(); //Instáncia de la clase ApiClient

    public static void main(String[] args) {

        int option = 0;

        while (option != 7) {

            showMenu(); //Menú de opciones que se mostrará al cliente

            if (!sc.hasNextInt()) {
                System.out.println("Please enter a valid option!");
                sc.next();
            } else {

                option = sc.nextInt();
                sc.nextLine();

                try {
                    switch (option) {
                        case 1:
                            System.out.println("\n1. Show all games: ");
                            apiclient.getAllGames();
                            break;
                        case 2:
                            System.out.println("\n2. Show game for ID: ");
                            showGameForId();
                            break;
                        case 3:
                            System.out.println("\n3. Show game for enterprise: ");
                            showGameForEnterprise();
                            break;
                        case 4:
                            System.out.println("\n4. Add new game: ");
                            createNewGame();
                            break;
                        case 5:
                            System.out.println("\n5. Update game: ");
                            updateGame();
                            break;
                        case 6:
                            System.out.println("\n6. Delete game: ");
                            deleteGame();
                            break;
                        case 7:
                            System.out.println("\n7. Exit");
                            break;
                        default:
                            System.out.println("Please enter a valid option!");
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    //Menu de opciones
    private static void showMenu(){
        System.out.println("\n========== MENU ==========");
        System.out.println("1. Show all games");
        System.out.println("2. Show game for ID");
        System.out.println("3. Show game for enterprise");
        System.out.println("4. Add new game");
        System.out.println("5. Update game");
        System.out.println("6. Delete game");
        System.out.println("7. Exit");
        System.out.println("==========================");
        System.out.println("Enter your option: ");
    }

    //Pide "ID" para obtener el videojuego
    private static void showGameForId() throws IOException, InterruptedException {
        int gameId = Utils.readInt(sc, "Enter game ID: ");
        apiclient.getGameById(gameId);
    }

    //Pide "Enterprise" para obtener el videojuego
    private static void showGameForEnterprise() throws IOException, InterruptedException {
        String gameEnterprise = Utils.readString(sc, "Enter game enterprise: ");
        apiclient.getGameByEnterprise(gameEnterprise);
    }

    //Pide todos los datos para añadir un videojuego
    private static void createNewGame() throws IOException, InterruptedException {
        int gameId = Utils.readInt(sc, "Enter game ID: ");
        String gameTitle = Utils.readString(sc, "Enter game title: ");
        String gameYear = Utils.readString(sc, "Enter game year: ");
        String gameModality = Utils.readString(sc, "Enter game modality: ");
        String gameEnterprise = Utils.readString(sc, "Enter game enterprise: ");
        apiclient.createNewGame(gameId, gameTitle, gameYear, gameModality, gameEnterprise);
    }

    //Pide todos los datos para actualizar un videojuego
    private static void updateGame() throws IOException, InterruptedException {
        int gameId = Utils.readInt(sc, "Enter game ID: ");
        String gameTitle = Utils.readString(sc, "Enter game title: ");
        String gameYear = Utils.readString(sc, "Enter game year: ");
        String gameModality = Utils.readString(sc, "Enter game modality: ");
        String gameEnterprise = Utils.readString(sc, "Enter game enterprise: ");
        apiclient.updateGame(gameId, gameTitle, gameYear, gameModality, gameEnterprise);
    }

    //Pide "ID" para eliminar un videojuego
    private static void deleteGame() throws IOException, InterruptedException {
        int gameId = Utils.readInt(sc, "Enter game ID: ");
        apiclient.deleteGame(gameId);
    }
}