/*
 */
package nerea.empresa;

import java.util.Scanner;

/**
 * Proyecto llamado Empresa para gestionar una franquicia de sucursales.
 *
 * @author Nerea Montoya
 */
public class Empresa {

    public static void main(String[] args) {

        Scanner scann = new Scanner(System.in);

        Sucursal sucursal1 = new Sucursal("Movistar Ciutadella", 5, 1015.50);

        System.out.println("Sucursal: " + sucursal1.getNombre() + ", Empleados: " + sucursal1.getNumeroEmpleados()
                + ", Beneficios: " + sucursal1.getBeneficios() + "; hay una paga extra de tipo "
                + sucursal1.clasificar() + " por empleado de " + sucursal1.calcularPagaExtra() + " €.");

        Sucursal sucursal2 = new Sucursal();

        /*Objeto sucursal, llama al set y al introducir los datos por teclado se almacena en el set.*/
        System.out.println("Introduce el nombre de la sucursal: ");
        sucursal2.setNombre(scann.nextLine());

        System.out.println("Introduce el número de empleados: ");
        sucursal2.setNumeroEmpleados(scann.nextInt());

        System.out.println("Introduce los beneficios obtenidos: ");
        sucursal2.setBeneficios(scann.nextDouble());

        System.out.println("Sucursal: " + sucursal2.getNombre() + ", Empleados: " + sucursal2.getNumeroEmpleados()
                + ", Beneficios: " + sucursal2.getBeneficios() + "; hay una paga extra de tipo "
                + sucursal2.clasificar() + " por empleado de " + sucursal2.calcularPagaExtra() + " €.");
    }
}
