
package nerea.muestra.Ficheros_Path_Streams_UD2.Streams;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nerea.muestra.Ficheros_Path_Streams_UD2.Streams.BufferedStreams;
import nerea.muestra.Ficheros_Path_Streams_UD2.Streams.ByteStreams;
import nerea.muestra.Ficheros_Path_Streams_UD2.Streams.DataStreams;
import nerea.muestra.Ficheros_Path_Streams_UD2.Streams.Producte;

/**
 * @author Nerea
 */

public class MainStreams {

    public static void main(String[] args) throws ClassNotFoundException {

        try {

            //----------ByteStreams----------
            ByteStreams byteStreams = new ByteStreams();
            
            //1. Escribir datos en el archivo en formato byte[]
            String ruta = "C:\\Users\\Nerea\\Desktop\\Carpeta\\texto.txt";
            String datos = "Hola, este es un ejemplo de escritura.\n";
            byteStreams.escribeBytes(ruta, datos);
            System.out.println("Archivo escrito con éxito.");

            //2. Leer y mostrar contenido del archivo
            System.out.println("Contenido leído del archivo:");
            byteStreams.leeBytes(ruta); // También lo imprime por consol

            //----------BufferedStreams----------
            BufferedStreams bufferedStreams = new BufferedStreams();
            String ruta2 = "C:\\Users\\Nerea\\Desktop\\Carpeta\\texto.txt";
            
            //Escribe txt en el archivo de la ruta
            System.out.println("\nBufferWriter");
            String texto = "Ejemplo de BufferStreams";
            bufferedStreams.escribeBuffer(ruta2, texto);

            //Leer el archivo y mostrar el contenido
            System.out.println("\nBufferReader");
            String contenido = bufferedStreams.leeBuffer(ruta2);
            System.out.println(contenido);
            
            
            //----------DataStreams----------
            DataStreams dataStreams = new DataStreams();
            
            int num = 4;
            double decimal = 3.6666;
            String txt = "Hola a todos";
            String ruta3 = "C:\\Users\\Nerea\\Desktop\\Carpeta\\dataStreams.dat";
            
            //Escribir DataStream
            System.out.println("\nDataOutputStream");
            dataStreams.escribirDataStreams(ruta3,num,decimal,txt);
            System.out.println("Datos escritos correctamente");
            
            //LeerDataStream
            System.out.println("\nDataInputStream");
            String leerData = dataStreams.leerDataStreams(ruta3);
            System.out.println("Contenido leído:\n" + leerData);
            
            //Main
            //----------ObjectStreams----------
            ObjectStreams objectstreams = new ObjectStreams();
            String ruta4 = "C:\\Users\\Nerea\\Desktop\\Carpeta\\objects.dat";
            
            //Escribe object
            List<Producte> productes = new ArrayList<>();
            productes.add(new Producte("Llapis", 0.75));
            productes.add(new Producte("Bolígraf", 1.20));
            productes.add(new Producte("Quadern", 3.50));
            System.out.println("\nObjectOutputStream");
            objectstreams.escriureProductes(ruta4, productes);
            System.out.println("Objetos escritos correctamente");
            
            //Lee Object
            System.out.println("\nObjectInputStream");
            List<Producte> prodList = objectstreams.llegirProductes(ruta4);
            
            for(Producte product : prodList){
                System.out.println(product);
            }
            System.out.println("Objetos leidos correctamente");
            
            
            
            
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(MainStreams.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
