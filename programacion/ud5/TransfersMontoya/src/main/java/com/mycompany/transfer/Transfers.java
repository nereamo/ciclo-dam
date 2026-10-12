package com.mycompany.transfer;

import com.mycompany.Utilidades.Utilidades;
import com.mycompany.Vehiculos.Taxi;
import com.mycompany.Vehiculos.Vtc;

/**
 * Classe Main, contiene un menú de opciones para crear un vehiculo, cambiar el estado del vehiculo, mostra el precio del trayecto y ver los vehiculos de la empresa.
 * @author Nerea Montoya
 */

public class Transfers {

    private static final int MAXIM_VEHICLES = 1;
    private static ArrayVehicles arrayVehicles = new ArrayVehicles(MAXIM_VEHICLES);

    public static void main(String[] args) {

        int opciones = 0;

        while (opciones != 7) {

            System.out.println("\n------MENÚ------");
            System.out.println("1.Añadir un nuevo vehículo.");
            System.out.println("2.Mostrar los datos de todos los vehículos de la empresa.");
            System.out.println("3.Cambiar el estado de un vehículo.");
            System.out.println("4.Generar informe del precio de un trayecto con el primer vehículo libre de la empresa.");
            System.out.println("5.Mostrar el número total de vehículos y cuántos están libres en este momento.");
            System.out.println("6.Mostrar la media de la potencia máxima de todos los vehículos.");
            System.out.println("7.Salir.");
            System.out.println("------------------");
            System.out.print("Elige una opción: ");

            opciones = Utilidades.pideEntero();

            switch (opciones) {

                case 1:
                    System.out.println("1.Añadir un nuevo vehículo.");
                    introduirVehicles(arrayVehicles);
                    break;

                case 2:
                    System.out.println("2.Mostrar los datos de todos los vehículos de la empresa.");
                    Vehicle[] vehicles = arrayVehicles.getVehicles();
                    if (vehicles.length == 0) {
                        System.out.println("No hay vehículos registrados.");
                    } else {
                        for (Vehicle v : vehicles) {
                            System.out.println(v.toString());
                        }
                    }
                    break;

                case 3:
                    System.out.println("3.Cambiar el estado de un vehículo.");
                    cambiarEstadoVehiculo(arrayVehicles);
                    break;

                case 4:
                    System.out.println("4.Generar informe del precio de un trayecto con el primer vehículo libre de la empresa.");
                    generarInformeTrayecto(arrayVehicles);
                    break;

                case 5:
                    System.out.println("5.Mostrar el número total de vehículos y cuántos están libres en este momento.");

                    System.out.println("\nLa empresa cuenta con un total de " + arrayVehicles.getNumVehicles() + " vehiculos.");
                    int libres = arrayVehicles.cuentaVehiculosLibres();
                    System.out.println("Total vehiculos libres: " + libres);
                    break;

                case 6:
                    System.out.println("6.Mostrar la media de la potencia máxima de todos los vehículos.");

                    double mediaPotencia = arrayVehicles.calculaMediaPotenciaMaximaVehiculos();
                    System.out.println("\nLa media de la potencia máxima de todos los vehículos es: " + mediaPotencia);
                    break;

                case 7:
                    System.out.println("7.Salir.");
                    System.out.println("Hasta la próxima!");
                    break;

                default:
                    System.out.println("Elige una opción del 1 al 7.");
                    break;
            }
        }
    }

    public static void introduirVehicles(ArrayVehicles arrayVehicles) {
        
        System.out.print("Marca: ");
        String marca = Utilidades.pideString();

        System.out.print("Potencia: ");
        int potencia = Utilidades.pideEntero();

        System.out.print("Velocidad: ");
        int velocitat = Utilidades.pideEntero();

        System.out.print("Velocidad máxima: ");
        int velocitatMaxima = Utilidades.pideEntero();

        Matricula matricula = new Matricula();
        boolean matriculaValida = false;
        while (!matriculaValida) {
            try {
                System.out.print("Numero de matrícula (formato 1234-XXX): ");
                String matriculaCompleta = Utilidades.pideString();

                if (matricula.validarMatricula(matriculaCompleta)) {
                    matriculaValida = true;
                    System.out.println("Matrícula correcta.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        int opciones = 0;
        boolean vehiculoAñadido = false;
        
        while (!vehiculoAñadido) {
            System.out.println("Seleccione el tipo de vehículo:");
            System.out.println("1.Taxi.");
            System.out.println("2.Vtc.");
            opciones = Utilidades.pideEntero();

            switch (opciones) {

                case 1:
                    String numLicencia;
                    while (true) {
                        try {
                            System.out.print("Número de licencia (formato XX-12345): ");
                            numLicencia = Utilidades.pideString();
                            Vehicle licenciaTaxi = new Taxi(marca, potencia, velocitat, velocitatMaxima, matricula, false, numLicencia);
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage()); 
                        }
                    }
                    System.out.println("Licencia correcta.\n");
                    Vehicle taxi = new Taxi(marca, potencia, velocitat, velocitatMaxima, matricula, false, numLicencia);
                    if (arrayVehicles.afegirVehicle(taxi)) {
                        System.out.println("Taxi añadido correctamente.");
                        vehiculoAñadido = true;
                    } else {
                        System.out.println("No se pudo añadir el vehículo. El array está lleno.");
                        return;
                    }
                    break;

                case 2:
                    String distintivo;
                    while (true) {
                        try {
                            System.out.print("Número de distintivo (formato 2026/11-XX): ");
                            distintivo = Utilidades.pideString();
                            Vehicle ditintivoVtc = new Vtc(marca, potencia, velocitat, velocitatMaxima, matricula, false, distintivo);
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage()); 
                        }
                    }
                    System.out.print("Distintivo correcto.\n");
                    Vehicle vtc = new Vtc(marca, potencia, velocitat, velocitatMaxima, matricula, false, distintivo);
                    if (arrayVehicles.afegirVehicle(vtc)) {
                        System.out.println("VTC añadido correctamente.");
                        vehiculoAñadido = true;
                    } else {
                        System.out.println("No se pudo añadir el vehículo. El array está lleno.");
                        return;
                    }
                break;
                    
                default:
                    System.out.println("Elige una opción del 1 al 2.");
                    break;
            }
        }
    }

    public static void cambiarEstadoVehiculo(ArrayVehicles arrayVehicles) {
        Matricula matricula = new Matricula();
        boolean matriculaValida = false;
        
        while (!matriculaValida) {
            try {
                System.out.print("Introduce la matrícula completa (4 dígitos y 3 letras, Ejemplo: 1234-ABC): ");
                String matriculaCompleta = Utilidades.pideString();

                if (matricula.validarMatricula(matriculaCompleta)) {
                    matriculaValida = true;
                    System.out.println("Matrícula correcta.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                
                Vehicle vehiculo = arrayVehicles.getVehicle(matricula);
                arrayVehicles.cambiaEstadoVehiculo(matricula);
                System.out.println("El estado del vehículo ha sido cambiado a: " + (vehiculo.isLibre() ? "Libre" : "Ocupado"));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void generarInformeTrayecto(ArrayVehicles arrayVehicles) {

        System.out.println("¿Cuantos kilómetros se va a recorrer?");
        double km = Utilidades.pideDouble();

        if (km < 0) {
            System.out.println("Error: Los kilómetros no pueden ser negativos.");
            return;
        }

        for (Vehicle vehicle : arrayVehicles.getVehicles()) {
            if (vehicle.isLibre()) {

                double precio = vehicle.calculaPrecioTrayecto(km);

                System.out.println("===== INFORME DE TRAYECTO =====");
                System.out.println("Matrícula: " + vehicle.getMatricula());
                System.out.println("Clase: " + vehicle.getClass().getSimpleName());
                System.out.println("Kilómetros a recorrer: " + km);
                System.out.println("Precio final: " + precio + "€");
                System.out.println("===============================");

                return;
            }
        }
        System.out.println("No hay vehículos disponibles en este momento.");
    }
}