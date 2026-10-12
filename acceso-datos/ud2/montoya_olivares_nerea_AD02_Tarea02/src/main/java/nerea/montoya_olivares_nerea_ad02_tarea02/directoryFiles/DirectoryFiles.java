
package nerea.montoya_olivares_nerea_ad02_tarea02.directoryFiles;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.UserPrincipal;
import java.text.SimpleDateFormat;
import java.util.Date;
import nerea.montoya_olivares_nerea_ad02_tarea02.Exceptions.UD2Exceptions;

//Codigo que muestra el contenido de un directorio y la información detallada de los archivos y subdirectorios dentro de él.

//Adaptación de los ejercicios resueltos del profesor y chatGPT

/**
* @author Nerea
*/

public class DirectoryFiles {

    private static final String ruta = "\\Users\\Nerea\\Documents"; //Variable que lamacena la ruta

    public static void directoryContents() throws IOException, UD2Exceptions {
        Path path = Path.of(ruta); //La ruta se convierte en path

        if (!Files.isDirectory(path)) { //verifica si el path corresponde a un directorio utilizando
            throw new UD2Exceptions("La ruta especificada no és un directorio: ");
        }

        File[] files = path.toFile().listFiles(); //Convierte el directorio en File y obtiene listado de archivos

        if (files == null || files.length == 0) {
            System.out.println("El directori està buit.");
            return;
        }

        System.out.println("\nContingut del directori: " + ruta);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); //Fechas de última modificación de los archivos

        for (File file : files) {
            if (file.isDirectory()) {
                System.out.println(file.getName() + " +++ Directori +++"); //Si es directorio imprime +++ Directori +++
            } else {
                StringBuilder fileInfo = new StringBuilder();

                fileInfo.append(file.getName()).append(" ");

                //Permisos de lectura, escritura y ejecución (r w x), si no tiene permiso se muestra -
                fileInfo.append(file.canRead() ? "r" : "-");
                fileInfo.append(file.canWrite() ? "w" : "-");
                fileInfo.append(file.canExecute() ? "x" : "-");
                fileInfo.append(file.isHidden() ? "h " : " "); //archivo oculto

                //Fecha de última modificación, obtiene att del archivo y es formateado para mostrar la fecha en la forma especificada
                BasicFileAttributes attributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
                Date lastModified = new Date(attributes.lastModifiedTime().toMillis());
                fileInfo.append(dateFormat.format(lastModified)).append(" ");

                //Propietario del archivo
                UserPrincipal owner = Files.getOwner(file.toPath());
                fileInfo.append(owner.getName()).append(" "); //Nombre propietario 
                fileInfo.append(attributes.size()); //TAmaño del archivo

                System.out.println(fileInfo);
            }
        }
    }

}
