
package nerea.bloc01ejer07;

import java.util.*;

/**
 *
 * @author Nerea
 */
public class Bloc01Ejer07 {

    public static void main(String[] args) {
        Scanner entrada = new Scanner (System.in);
       
        System.out.println("Intrduce la temperatura en Celsius: ");
        int celsius = entrada.nextInt();
        
        double fahrenheit  = (celsius * 1.8) + 32;
        
        System.out.println("La temperatura en Fahrenheit es " + fahrenheit ); 
    }
}
