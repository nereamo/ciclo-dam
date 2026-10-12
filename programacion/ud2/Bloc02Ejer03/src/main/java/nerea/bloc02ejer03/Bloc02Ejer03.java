package nerea.bloc02ejer03;

import java.util.Scanner;

/**
 * Programa que pide dos nombres completos y dos booleanos 
 * que indicaran si cada nombres está formado o no por un nombre compuesto.
 *
 * @author Nerea Montoya
 */
public class Bloc02Ejer03 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Primer nombre completo:");
        String fullName1 = scanner.nextLine();

        if (fullName1.trim().isEmpty()) {
            System.out.println("Error: El nombre completo no puede estar en blanco.");
            return;
        } else if (!fullName1.trim().contains(" ")) {
            System.out.println("Alerta: Debes introducir al menos un apellido.");
            return;
        }

        System.out.print("¿Es un nombre compuesto? (true/false)");
        boolean isCompuesto1 = scanner.nextBoolean();
        scanner.nextLine(); 

       
        
        System.out.print("Segundo nombre completo:");
        String fullName2 = scanner.nextLine();

        if (fullName2.trim().isEmpty()) {
            System.out.println("Error: El nombre completo no puede estar en blanco.");
            return;
        } else if (!fullName2.trim().contains(" ")) {
            System.out.println("Alerta: Debes introducir al menos un apellido.");
            return;
        }
        System.out.print("¿Es un nombre compuesto? (true/false)");
        boolean isCompuesto2 = scanner.nextBoolean();
        scanner.nextLine(); 

        //Obtener apellidos
        String lastName1 = getSecondName(fullName1, isCompuesto1);
        String lastName2 = getSecondName(fullName2, isCompuesto2);

        //Ordenar nombres
        if (lastName1.compareToIgnoreCase(lastName2) < 0) {
            System.out.println("Resultado por orden alfabético:");
            System.out.println(nameOrder(fullName1, isCompuesto1));
            System.out.println(nameOrder(fullName2, isCompuesto2));
        } else {
            System.out.println("Resultado por orden alfabético:");
            System.out.println(nameOrder(fullName2, isCompuesto2));
            System.out.println(nameOrder(fullName1, isCompuesto1));
        }
    }

    private static String getSecondName(String nombreCompleto, boolean esCompuesto) {
        int indicePrimerEspacio = nombreCompleto.indexOf(' ');
        if (indicePrimerEspacio == -1) {
            return nombreCompleto;
        }
        if (esCompuesto) {
            int indiceSegundoEspacio = nombreCompleto.indexOf(' ', indicePrimerEspacio + 1);
            if (indiceSegundoEspacio == -1) {
                return nombreCompleto.substring(indicePrimerEspacio + 1);
            }
            return nombreCompleto.substring(indiceSegundoEspacio + 1);
        } else {
            return nombreCompleto.substring(indicePrimerEspacio + 1);
        }
    }

    private static String nameOrder(String nombreCompleto, boolean esCompuesto) {
        int indicePrimerEspacio = nombreCompleto.indexOf(' ');
        if (indicePrimerEspacio == -1) {
            return nombreCompleto;
        }
        String nombre;
        String apellidos;
        if (esCompuesto) {
            int indiceSegundoEspacio = nombreCompleto.indexOf(' ', indicePrimerEspacio + 1);
            if (indiceSegundoEspacio == -1) {
                nombre = nombreCompleto.substring(0, indicePrimerEspacio);
                apellidos = nombreCompleto.substring(indicePrimerEspacio + 1);
            } else {
                nombre = nombreCompleto.substring(0, indiceSegundoEspacio);
                apellidos = nombreCompleto.substring(indiceSegundoEspacio + 1);
            }
        } else {
            nombre = nombreCompleto.substring(0, indicePrimerEspacio);
            apellidos = nombreCompleto.substring(indicePrimerEspacio + 1);
        }
        return apellidos + ", " + nombre;
    }

}
