
package nerea.montoya_olivares_nerea_ad02_tarea02.Fluxos;

import java.io.IOException;
import java.util.ArrayList;
import nerea.montoya_olivares_nerea_ad02_tarea02.Exceptions.UD2Exceptions;
import nerea.montoya_olivares_nerea_ad02_tarea02.recursosTarea.Modul;

//Pruebas para verificar que los datos/objetos son añadidos correctamente

//Adaptación de los ejercicios resueltos del profesor

/**
* @author Nerea
*/

public class pruebasFluxos {
    
    private static final String path = "\\Users\\Nerea\\Documents\\";
    
    //Añadir datos a ArrayList
    public static void pruebaDataStreams() throws UD2Exceptions{
        try{
            ArrayList<Modul> save = new ArrayList<>();
            save.add(new Modul("M04", "Acceso a Datos", 70));
            save.add(new Modul("M02", "Programación", 120));
            save.add(new Modul("M05", "Desarrollo de Interfaces", 90));

            Fluxos.saveInfoData(path + "datos.dat", save); //Lamar al método, buscar archivo y guardar los datos

            ArrayList<Modul> read =  Fluxos.readInfoData(path + "datos.dat");   //Uso del metodo readInfoData para obtener la información del archivo
            
            System.out.println("\nLista original: datos.dat");
            
            for (Modul modulo : read) {
                System.out.println(modulo);
            }

        } catch (IOException e) {
            throw new UD2Exceptions("Error al guardar los datos.");
        }
    }

    //Añadir Objetos a ArrayList
    public static void pruebasObjetoList() throws UD2Exceptions{
        try{
            ArrayList<Modul> save = new ArrayList<>();
            save.add(new Modul("M01", "Matemáticas", 100));
            save.add(new Modul("M02", "Programación", 200));
            save.add(new Modul("M03", "Base de Datos", 150));

            Fluxos.saveInfoObject(path + "arraylist.dat", save);    //Lamar al método, buscar archivo y guardar los objetos

            ArrayList<Modul> read =  Fluxos.readInfoObject(path + "arraylist.dat"); //Uso del metodo readInfoObject para obtener la información del archivo
            
            System.out.println("\nLista original: arraylist.dat");
            
            for (Modul modulo : read) {
                System.out.println(modulo);
            }
            
        } catch (IOException e) {
            throw new UD2Exceptions("Error al guardar los datos.");
        }
    }

}
