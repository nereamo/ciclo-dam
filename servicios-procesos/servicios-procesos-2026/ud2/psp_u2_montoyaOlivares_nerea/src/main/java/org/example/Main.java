package org.example;

import java.util.Scanner;

/**
 * Clase Main, encargada de ejecutar los hilos y el programa
 */

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean repetir = true;

        //Bucle principal
        while (repetir) {

            //Solicita el número de caballos y la distáncia que van a recorrer
            int numCaballos = validarEntrada(sc, "Cuántos caballos participarán? (Mínimo 10) ?", 10);
            int distancia = validarEntrada(sc, "Que distáncia van a recorrer? ", 1);

            //Nueva carrera con parámetros
            Carrera carrera = new Carrera(numCaballos, distancia);

            //Muestra el formato de carrera
            carrera.formatoCarrera();

            //Cuando han llegado los tres primeros caballos se queda en pausa
            if(carrera.isPausa()){

                System.out.println("\nDeseas continuar con la carrera? (S/N)");
                String respuesta = sc.nextLine().trim().toLowerCase();

                //Si la respuesta es SI, reanudamos la carrera
                if (respuesta.equalsIgnoreCase("s")) {
                    carrera.reiniciarCaballos();
                    carrera.formatoCarrera();

                //De lo contrario detenemos la carrera y mostramos clasificación final
                } else {
                    System.out.println("Carrera detenida.");
                    carrera.mostrarClasificacionFinal();
                    break;
                }
            }

            //Preguntar si quiere volver a probar
            System.out.println("\nQuieres volver a probar? (S/N)");
            String respuesta = sc.nextLine().trim().toLowerCase();

            //Si la respuesta es NO, terminamos programa
            if(respuesta.equalsIgnoreCase("N")) {
                repetir = false;
                System.out.println("Hasta la próxima!");
            }
        }
        sc.close();
    }

    //metodo que valida la entrada del usuario
    private static int validarEntrada(Scanner sc, String mensaje, int minimo) {

        while (true) {
            System.out.println(mensaje);
            String linea = sc.nextLine();
            try {
                int num = Integer.parseInt(linea);
                if (num >= minimo) return num; //entrada válida
                System.out.println("Debe ser mayor o igual a " + minimo);
            } catch (NumberFormatException e) {
                //Si la entrada no es válida
                System.out.println("Por favor, introduce un número válido");
            }
        }
    }
}