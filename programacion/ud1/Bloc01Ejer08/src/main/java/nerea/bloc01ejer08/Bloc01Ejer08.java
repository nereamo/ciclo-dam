
package nerea.bloc01ejer08;
import java.util.*;
/**
 *
 * @author Nerea
 */
public class Bloc01Ejer08 {

    public static void main(String[] args) {
        Scanner entrada = new Scanner (System.in);
        
        System.out.println("Introduce los segundos: ");
        
        int tiempo = entrada.nextInt();
        
        //'Tiempo' son los segundos introducidos por consola dividido por 60 segundos que tiene un minuto
        int minutos = tiempo / 60;  
        
        //Calcular el residuo de dividir los segundos por 60 asi obtenemos los segundos sobrantes.
        int segundos = tiempo % 60;
        
        //Los minutos se dividen entre 60 para obtener las horas.
        int horas = minutos/60;
        
        //Minutos sobrantes que no completan una hora
        minutos=minutos % 60;
        
        //Dividir las horas entre 24 que son las que tiene un dia completo
        int dias = horas / 24;
        
        //Residuo de las horas que no forman parte de un dia completo
        horas=horas % 24;
       
        
        System.out.println("10000 segundos equivalen a:");
        
        System.out.println(dias + " dias");
        System.out.println(horas + " horas");
        System.out.println(minutos + " minutos");
        System.out.println(segundos + " segundos");
    }
}
