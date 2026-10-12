package nerea.banconereamontoya.vista;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import nerea.banconereamontoya.controlador.AccountDAO;
import nerea.banconereamontoya.controlador.CustomerDAO;
import nerea.banconereamontoya.modelo.Account;
import nerea.banconereamontoya.modelo.Customer;
import nerea.banconereamontoya.utilidades.Utilidades;

/**
 * Clase BancoNereaMontoya, contiene un menú con las siguientes opciones: 
 * 1. Mostrar la lista de las cinco cuentas más antiguas, en formato tabulado, el ID de la cuenta, el día de apertura de la cuenta y el saldo actual. 
 * 2. Generar un fichero con el informe que muestre la información de un cliente específico pedido por teclado.
 * 3. Modificar el saldo de una cuenta pedida por teclado añadiendo o retirando la cantidad indicada.
 * 4. Salir.
 * 
 * @author Nerea Montoya
 */
public class BancoNereaMontoya {
    
    private static String url = "jdbc:mysql://localhost:3306/accounting?user=root&password=nerea";
    
    public static void main (String [] args){
        
        int option = 0;
        
        while (option != 4) {
            System.out.println("\n-----Menú-----");
            System.out.println("1. Mostrar las cinco cuentas más antiguas.");
            System.out.println("2. Generar informe del cliente.");
            System.out.println("3. Modificar saldo de una cuenta.");
            System.out.println("4. Salir");
            System.out.println("--------------");
            System.out.println("Elige un opción");
            
            option = Utilidades.pideEntero();
            
            switch (option){
                case 1:
                    System.out.println("1. Mostrar las cinco cuentas más antiguas.");
                    mostrarCuentasAntiguas();
                    break;
                case 2:
                    System.out.println("2. Generar informe del cliente.");
                   generarInformeCliente(url);
                    break;
                case 3:
                    System.out.println("3. Modificar saldo de una cuenta.");
                    modificarSaldo();
                    break;
                case 4:
                    System.out.println("Hasta la próxima!");
                    break;
                default:
                    System.out.println("Elige una opción válida.");
                    break;
            }
        }
    }
    
    private static void mostrarCuentasAntiguas() {
        try {
            ArrayList<Account> cuentas = AccountDAO.listaCuentas(url);
            if (!cuentas.isEmpty()) {
                System.out.println("\nLas cinco cuentas más antiguas:");
                for (Account cuenta : cuentas) {
                    System.out.println("ID cuenta: " + cuenta.getAccount_id() +
                                       " | Fecha de apertura: " + cuenta.getOpen_date() +
                                       " | Saldo disponible: $" + cuenta.getAvail_balance());
                }
            } else {
                System.out.println("No se encontraron cuentas.");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener las cuentas: " + e.getMessage());
        }
    }
    
    public static void generarInformeCliente(String url) {
        
        System.out.print("\nIngrese el ID del cliente: ");
        int custId = Utilidades.pideEntero();

        try {

            Customer customerExists = CustomerDAO.obtenerCustomerPorId(custId, url);

            if (customerExists == null) {
                System.out.println("No se ha podido encontrar la cuenta o no existe.");
                return;
            }
            ArrayList<Customer> clienteInfo = CustomerDAO.idCustomer(url, custId);
            
            ArrayList<Account> cuentas = AccountDAO.listaCuentasCustomerId(url, custId);
            double saldoTotal = AccountDAO.saldoTotalCustomerId(url, custId);

            File archivo = new File("notasNereaMontoya.txt");

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {

                writer.write("Información del Cliente con ID: "+ custId + "\n");
                for (Customer customer : clienteInfo) {
                    writer.write("\nCiudad: " + customer.getCity() + "\n");
                }

                writer.write("\nCuentas del Cliente:\n");
                for (Account cuenta : cuentas) {
                    writer.write("Cuenta: " + cuenta.getAccount_id() + " | Saldo disponible: $" + cuenta.getAvail_balance() + "\n");
                }

                writer.write("\nSaldo total del cliente: $" + saldoTotal + "\n");

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                writer.write("\nInforme generado por Nerea Montoya el " + sdf.format(new Date()));
                writer.newLine();
                
                System.out.println("Archivo generado correctamente en: " + archivo);
            }
        } catch (SQLException ex) {
            System.out.println("Error al acceder a la base de datos: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error al generar el informe: " + ex.getMessage());
        }
    }

    private static void modificarSaldo() {

        try {
            System.out.print("\nIngrese el ID de la cuenta: ");
            int accountId = Utilidades.pideEntero();

            Account cuenta = AccountDAO.obtenerCuentaPorId(accountId, url);

            if (cuenta == null) {
                System.out.println("No se ha podido encontrar la cuenta o no existe.");
                return;
            }

            System.out.println("Saldo antes de la modificación: $" + cuenta.getAvail_balance());

            double saldo = Utilidades.pideDouble("Ingrese la cantidad a modificar(500) o ingrese la cantidad a retirar(-500): ");

            AccountDAO.modificarSaldoCuenta(accountId, saldo, url);

            cuenta = AccountDAO.obtenerCuentaPorId(accountId, url);

            if (cuenta != null) {
                System.out.println("Saldo después de la modificación: $" + cuenta.getAvail_balance());
            }

        } catch (SQLException e) {
            System.out.println("Error al modificar el saldo: " + e.getMessage());
        }
    }
}

