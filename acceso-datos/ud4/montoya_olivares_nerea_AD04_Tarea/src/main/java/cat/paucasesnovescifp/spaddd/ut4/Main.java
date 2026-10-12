package cat.paucasesnovescifp.spaddd.ut4;

import cat.paucasesnovescifp.spadd.ut4.controller.Emf;
import cat.paucasesnovescifp.spaddd.ut4.auxiliars.AplicacioException;
import cat.paucasesnovescifp.spaddd.ut4.model.Peatge;
import cat.paucasesnovescifp.spaddd.ut4.model.Trip;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe que contiene un switch con un menú de opciones.
 * @author Nerea Montoya
 */
public class Main {

    public static void main(String[] args) {

        Emf emf = new Emf("Peatges-PU");

        int options = 0;

        while (options != 5) {

            System.out.println("\nMenú");
            System.out.println("----------------");
            System.out.println("1. Mostrar un id de la tabla 'toll'.");
            System.out.println("2. Ordenar alfabéticamente los nombres de la tabla 'toll'.");
            System.out.println("3. Ver viajes de inicio o final de un Peatge.");
            System.out.println("4. Mostrar viajes de un Peatge.");
            System.out.println("5. Salir.");
            System.out.println("----------------");
            System.out.print("Elige una opción: ");

            Scanner sc = new Scanner(System.in);
            options = sc.nextInt();

            switch (options) {

                case 1:
                    System.out.println("1. Mostrar un id de la tabla 'toll'.");
                    try {
                        String idToll1 = "AP2-1";
                        Peatge idToll = emf.getIdentifiedToll(idToll1);
                        System.out.println("\nId encontrado: ");
                        System.out.println(idToll.getIdToll());
                    } catch (AplicacioException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;

                case 2:
                    System.out.println("2. Ordenar alfabéticamente los nombres de la tabla 'toll'.");
                    try {

                        String nameToll = "";
                        List<Peatge> nomToll = emf.getOrderNameToll(nameToll);
                        System.out.println("\nOrden alfabético: ");

                        for (Peatge p : nomToll) {
                            System.out.println(p.getName());
                        }

                    } catch (AplicacioException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    break;

                case 3:
                    System.out.println("3. Mostrar viajes de un Peage.");
                    try {

                        System.out.println("\nQue viajes quieres ver? ");
                        System.out.println("1. Salida. \n2. Llegada. ");

                        Scanner tf = new Scanner(System.in);
                        int option = tf.nextInt();
                        boolean salida = (option == 1);
                        
                        String idToll2 = "AP2-10";
                        Peatge peatge = emf.getIdentifiedToll(idToll2);

                        if (salida) {

                            List<Trip> tripInput = emf.getTrips(peatge, true);
                            System.out.println("\nInicio del viage: ");
                            for (Trip ti : tripInput) {
                                System.out.println("Trip: " + ti.getId() + " -> " + ti.getIdTollInput() + " -> Date: " + ti.getInputTime());
                            }

                        } else {
                            
                            List<Trip> tripOutput = emf.getTrips(peatge, false);
                            System.out.println("\nFin del viaje: ");
                            for (Trip to : tripOutput) {
                                System.out.println("Trip: " + to.getId() + " -> " + to.getIdTollOutput() + " -> Date: " + to.getOutputTime());
                            }
                        }

                    } catch (AplicacioException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    break;

                case 4:
                    System.out.println("4. Mostrar viajes de un Peage.");
                    try {

                        String idToll3 = "AP7-3";
                        Peatge peatge = emf.getIdentifiedToll(idToll3);
                        
                        List<Trip> tripList = emf.getAllTripsForId(peatge);
                        
                        System.out.println("\nLista de viajes del peage " + peatge.getIdToll() + ":");
                        for (Trip t : tripList) {

                            System.out.println(
                                    "Trip: " + t.getId()
                                    + " -> Fecha salida: " + t.getInputTime()
                                    + " -> Id peage inicial: " + t.getIdTollInput().getIdToll()
                                    + " -> Lugar Inicio: " + t.getIdTollInput().getName()
                                    + " -> Id peage final: " + t.getIdTollOutput().getIdToll()
                                    + " -> Lugar final: " + t.getIdTollOutput().getName()
                                    + " -> Fecha llegada: " + t.getOutputTime()
                            );
                        }

                    } catch (AplicacioException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;

                case 5:
                    System.out.println("5. Salir.");
                    System.out.println("Programa finalizado.");
                    break;

                default:
                    System.out.println("Elige una opción del menú.\n");
                    break;
            }
        }
    }
}
