
package nerea.muestra.Ficheros_Path_Streams_UD2.Path_Files;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import nerea.muestra.Exception.AppException;


/**
* @author Nerea
*/

public class Path_Files{
    
    private static Path path = Path.of("C:\\Users\\Nerea\\Desktop\\Carpeta\\Texto.txt");
    
    public void recuperarInformacionPath(){

        System.out.println("Mostrar path: " + path.toString());
        System.out.println("Cuantos elementos hay en el path? " + path.getNameCount());
        System.out.println("Muestra elemento sa partir del índice: " + path.getName(2));
        System.out.println("Muestra nombre del archivo: " + path.getFileName());
        System.out.println("Muestra elementos entre dos índices: " + path.subpath(1, 4));   
    }
    
    public void consultasClasseFiles() throws AppException{
        
        if(Files.exists(path)){
            try{
                System.out.println("Es un fichero? " + Files.isRegularFile(path));
                System.out.println("Es un directorio? " + Files.isDirectory(path));
                System.out.println("El archivo se puede leer? " + Files.isReadable(path));
                System.out.println("El archivo se puede escribir? " + Files.isWritable(path));
                System.out.println("El archivo es ejecutable? " + Files.isExecutable(path));
                System.out.println("Tamaño del archivo en bytes: " + Files.size(path));
                System.out.println("El archivo es oculto? " + Files.isHidden(path));
                System.out.println("Última modificación del archivo: " + Files.getLastModifiedTime(path));
                System.out.println("Quien es el propietario del archivo? " + Files.getOwner(path));  
            }catch(IOException e){
                throw new AppException("No se puede mostrar la información del archivo" + e.getMessage());
            }
        }
    }
    
    public void crearDirectorio(Path path) throws AppException{
        
        try {
            Files.createDirectories(path);//Variable path con ruta a fichero o directorio
        } catch (IOException e) {
            throw new AppException("Error al crear el directorio" + e.getMessage());
        } 
    }
    
    public void eliminarFichero(Path path) throws AppException{
        
        try{
            Files.delete(path); //Variable path con ruta a fichero o directorio
        }catch(IOException e){
            throw new AppException("Error al eliminar el fichero" + e.getMessage());
        } 
    }
    
    public void copiarFichero(Path path1, Path path2) throws AppException{
        
        try {
            //Variable path1 ruta de origen y path2 es ruta de destino
            Files.copy(path1, path2, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
        } catch (IOException e) {
            throw new AppException("Error al crear el directorio" + e.getMessage());
        }  
    }
    
    public void moverFichero(Path path1, Path path2) throws AppException{
        
        try {
            //Variable path1 ruta de origen y path2 es ruta de destino
            Files.move(path1, path2, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
        } catch (IOException e) {
            throw new AppException("Error al mover el archivo" + e.getMessage());
        }  
    }
    
    public void escribeContenidoFichero(Path path, String texto) throws AppException{
        
        try {
            //Variable path ruta del fichero y texto es lo que se va a escribir en el fichero
            Files.write(path, texto.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.APPEND);  
        } catch (IOException e) {
            throw new AppException("Error al añadir el texto" + e.getMessage());
        }  
    }

    public byte[] leeContenidoFicheroBytes(Path path) throws AppException{
        
        try {
            //Lee el contenido de la ruta 'path' pasada por parámetro
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw new AppException("Error al leer el texto" + e.getMessage());
        }   
    }
    
    public List<String> leeContenidoFichero(Path path) throws AppException{
        
        try {
            //Lee el contenido de la ruta 'path' pasada por parámetro
            return Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new AppException("Error al leer el texto" + e.getMessage());
        }   
    }
    
    public List<Path> leeContenidoDirectorio(Path path) throws AppException{
        
        List<Path> archivos = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
            for (Path file : stream) {
                archivos.add(file);
                System.out.println(file.getFileName());
            }
        }catch(IOException e){
            throw new AppException("Error al leer el texto" + e.getMessage());
        }
        return archivos;
    }
}
