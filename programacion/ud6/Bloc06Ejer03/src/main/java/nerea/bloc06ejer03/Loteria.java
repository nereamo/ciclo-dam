package nerea.bloc06ejer03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase que pide por teclado una serie de números de 5 dígitos y le asigna un premio a cada número, Después se muestran ordenados de forma descendente.
 * @author Nerea Montoya
 */
public class Loteria {

    private static ArrayList<Premio> arrayPremio = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    private static Random random = new Random();
    private static int[] cantidades = {400000, 125000, 50000, 20000};
    private static boolean premioMayorAsignado = false;

    public static void main(String[] args) {

        while (true) {
            System.out.println("Cual es el número premiado? (5 dígitos) o escriba -1 para salir: ");
            String numPremiado = sc.nextLine();

            if (numPremiado.equals("-1")) {
                System.out.println("Hasta la próxima!");
                break;
            }else if(numPremiado.startsWith("-")){
                System.out.println("Porfavor, escriba una opción válida.");
                continue;
            }

            Premio premio = new Premio();
            try{
                premio.setNumero(numPremiado);
            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
                continue;
            } 

            int cantidad;
            do {
                cantidad = cantidades[random.nextInt(cantidades.length)];
            } while (cantidad == 400000 && premioMayorAsignado);

            if (cantidad == 400000) {
                premioMayorAsignado = true;
            }

            premio.setCantidad(cantidad);
            arrayPremio.add(premio);
            System.out.println("Premio añadido: Número " + numPremiado + ", Cantidad " + cantidad);

        }
        
        sc.close();

        if (!arrayPremio.isEmpty()) {
            Collections.sort(arrayPremio);
            System.out.println("\nLista de premios ordenada por cantidad:");
            for (Premio premio : arrayPremio) {
                System.out.println("Número: " + premio.getNumero() + ", Cantidad: " + premio.getCantidad());

            }
        }

    }

}
