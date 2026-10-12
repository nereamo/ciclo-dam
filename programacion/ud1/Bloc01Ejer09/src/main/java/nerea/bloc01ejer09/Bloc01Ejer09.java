
package nerea.bloc01ejer09;
import java.util.*;
/**
 *
 * @author Nerea
 */

public class Bloc01Ejer09 {

    public static void main(String[] args) {
        Scanner entrada = new Scanner (System.in);
        
        // Introduzca el precio de un artículo
        System.out.print("Introduzca el precio: ");
        double price = entrada.nextDouble();
        
        // Introduzca la cantidad de unidades
        System.out.print("Introduzca las unidades: ");
        int unidad = entrada.nextInt();
        
        // El precio total es la multiplicación de precio por unidades
        double totalPrice= unidad * price;
        
        // Muestra el precio total con dos decimales
        System.out.printf("El precio por las unidades es de " + "'%.2f'%n", totalPrice);
        
        //Variable del porcentaje
        int porcentaje = 5;
        
        // Calcular el descuento del 5% 
        double descuento = (porcentaje/100.0)*totalPrice;
        
        // Muestra el descuento aplicado con dos decimales
        System.out.printf("El descuento aplicado es de: " + "'%.2f'%n", descuento);
        
        // Calcular y mostrar el precio final después de aplicar el descuento
        System.out.printf("El precio final con descuento es de: " + "'%.2f'%n", totalPrice - descuento);  
    }
}

