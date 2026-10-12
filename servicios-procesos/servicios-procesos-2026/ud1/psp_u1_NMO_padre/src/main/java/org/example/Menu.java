package org.example;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {

    public static String url = " ";
    public static Process procesoHijo = null;

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {

            boolean validarWeb = false;

            do {
                //Comprobación de la URL de la web
                System.out.println("Introduzca una Web como la del ejemplo: https://paucasesnovescifp.cat/");
                System.out.print("Introduzca la URL: ");

                url = sc.nextLine();

                String patron = "https?://[\\w.-]+(\\.[a-z]{2,})+/?.*";
                Pattern p = Pattern.compile(patron);
                Matcher m = p.matcher(url);
                validarWeb = m.matches();

                if (!validarWeb) {
                    System.out.println("Introduce una Web válida\n");
                }

            } while (!validarWeb);

            while (true) {

                System.out.println("\nMenú");
                System.out.println("...................................");
                System.out.println("1.Cargar pagina web.");
                System.out.println("2.Analizar el número de carácteres.");
                System.out.println("3.Sustituir letra.");
                System.out.println("4.Leer encrypted.txt.");
                System.out.println("5.Buscar palabras clave.");
                System.out.println("6.Crear archivo Index.");
                System.out.println("7.Ejecutar archivo Index.");
                System.out.println("8.Salir.\n");
                System.out.print("Elija una opción: ");

                String options = sc.nextLine();

                switch (options) {
                    case "1":
                        System.out.println("1.Cargar pagina web.\n");
                        PaginaWeb.cargarPaginaWeb(url);
                        break;

                    case "2":
                        System.out.println("2.Analizar el número de carácteres.\n");
                        ContarLetras.contar(url);
                        break;

                    case "3":
                        System.out.println("3.Sustituir letra.\n");
                        SustituirLetras.sustituir(url);
                        break;

                    case "4":
                        System.out.println("4.Leer encrypted.txt.\n");
                        LeerEncrypted.leerArchivoEncrypted();
                        break;

                    case "5":
                        System.out.println("5.Buscar palabras clave.\n");
                        BuscarPalabras.buscar();
                        break;

                    case "6":
                        System.out.println("6.Crear archivo Index.\n");
                        CrearIndex.crearIndexHtml();
                        break;

                    case "7":
                        System.out.println("7.Ejecutar archivo Index.\n");
                        EjecutarIndexHtml.ejecutarHtml();
                        break;

                    case "8":

                        System.out.println("\nSalir.");

                        if (Menu.procesoHijo != null && Menu.procesoHijo.isAlive()) {
                            System.out.println("Hay un proceso hijo activo. Esperando que termine...");
                            try {
                                Menu.procesoHijo.waitFor();
                            } catch (InterruptedException e) {
                                System.out.println("Error esperando el proceso hijo.");
                            }
                        }

                        System.out.println("\nPrograma finalizado correctamente.");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("\nOPCIÓN INVALIDA. POR FAVOR ELIJA UNA OPCIÓN DEL 1 AL 8.\n");
                }
            }
        }
    }
}