
package nerea.bloc01ejer06;

import java.util.*;
/**
 *
 * @author Nerea
 */
public class Bloc01Ejer06 {

    public static void main(String[] args) {
        
        Scanner entrada = new Scanner(System.in);  
        
        System.out.println("Introduce el ancho: ");
        double ancho = entrada.nextDouble();
        
        System.out.println("Introduce la altura: ");
        double altura = entrada.nextDouble();
        
        double area = ancho * altura;
        
        double perimetro = 2* (ancho + altura);
        
        System.out.printf("El perimetro es: 2*(%.1f + %.1f) = %.2f \n", altura, ancho, perimetro);
        
        System.out.printf("El area es: %.1f * %.1f = %.2f \n", ancho, altura, area);  
    }
}
