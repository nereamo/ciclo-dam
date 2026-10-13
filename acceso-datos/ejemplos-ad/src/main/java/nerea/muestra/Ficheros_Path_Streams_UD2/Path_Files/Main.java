
package nerea.muestra.Ficheros_Path_Streams_UD2.Path_Files;

import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nerea.muestra.Exception.AppException;

/**
 * @author Nerea
 */

public class Main {

    public static void main(String[] args) {

        try {
            
            Path_Files pf = new Path_Files();

            // Main
            System.out.println("Obtener información del path:");
            pf.recuperarInformacionPath();

            // Main
            System.out.println("\nConsultas de la class Files:");
            pf.consultasClasseFiles();

            // Main
            System.out.println("\nCrear directorio:");
            Path path = Path.of("C:\\Users\\Nerea\\Desktop\\Carpeta\\Carpeta1");
            pf.crearDirectorio(path);

            // Main
            System.out.println("\nEliminar fichero:");
            Path pathDelete = Path.of("C:\\Users\\Nerea\\Desktop\\Carpeta\\delete.txt");
            pf.eliminarFichero(pathDelete);

            // Main
            System.out.println("\nCopiar fichero:");
            Path path1 = Path.of("C:\\Users\\Nerea\\Desktop\\Carpeta\\Text.txt");
            Path path2 = Path.of("C:\\Users\\Nerea\\Desktop\\Carpeta\\Carpeta1\\Text.txt");
            pf.copiarFichero(path1, path2);

            // Main
            System.out.println("\nMover fichero:");
            Path path3 = Path.of("C:\\Users\\Nerea\\Desktop\\Carpeta\\Text.txt");
            Path path4 = Path.of("C:\\Users\\Nerea\\Desktop\\Carpeta\\Carpeta1\\Text.txt");
            pf.moverFichero(path3, path4);

            // Main
            System.out.println("\nEscribir contenido en el fichero :");
            Path path5 = Path.of("C:\\Users\\Nerea\\Desktop\\Carpeta\\Carpeta1\\Text.txt");
            pf.escribeContenidoFichero(path5, "Buenos dias por la mañana!");

            // Main
            System.out.println("\nLeer contenido del fichero:");
            Path path6 = Path.of("C:\\Users\\Nerea\\Desktop\\Carpeta\\Carpeta1\\Text.txt");
            List<String> texto = pf.leeContenidoFichero(path6);
            for (String txt : texto) {
                System.out.println("Texto del fichero:" + txt);
            }

            // Main
            System.out.println("\nLeer contenido del directorio en formato bytes:");
            Path path7 = Path.of("C:\\Users\\Nerea\\Desktop\\Carpeta\\Texto.txt");
            byte[] pathDirectorio = pf.leeContenidoFicheroBytes(path7);
            for (byte p : pathDirectorio) {
                System.out.println(p);
            }

            // Main
            System.out.println("\nLeer contenido del directorio:");
            Path path8 = Path.of("C:\\Users\\Nerea\\Desktop\\Carpeta");
            List<Path> pathDirectorio2 = pf.leeContenidoDirectorio(path8);
            for (Path p : pathDirectorio2) {
                System.out.println("contenido del directorio:" + p);
            }

        } catch (AppException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
