/*
 */

package nerea.psp_u2_montoyaolivaresnerea;

import java.util.Scanner;

/**
 *
 * @author Nerea
 */
public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String respuesta;
        for(boolean continuar = true; continuar; continuar = respuesta.equalsIgnoreCase("s")) {
            System.out.println("¿Cuantos caballos participan? El mínimo son 10: ");

            int numCavalls;
            for(numCavalls = scanner.nextInt(); numCavalls < 10; numCavalls = scanner.nextInt()) {
                System.out.println("El mínimo de caballos es 10: ");
            }

            System.out.println("¿Que distáncia van a correr (metros)?: ");
            double distancia = scanner.nextDouble();
            Carrera carrera = new Carrera(numCavalls, distancia);

            try {
                carrera.iniciar();
            } catch (InterruptedException var8) {
                InterruptedException e = var8;
                throw new RuntimeException(e);
            }

            System.out.print("¿Quieres volver a jugar? (s/n): ");
            respuesta = scanner.next();
            carrera.detenerCarrera();
        }

        System.out.println("Hasta la próxima!");
    }
}
