
package nerea.montoya_olivares_nerea_ad02_tarea02.Fluxos;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import nerea.montoya_olivares_nerea_ad02_tarea02.Exceptions.UD2Exceptions;
import nerea.montoya_olivares_nerea_ad02_tarea02.recursosTarea.Modul;

//Almacenar datos / objetos en ArrayList

//Adaptación de los ejercicios resueltos del profesor

/**
* @author Nerea
*/

public class Fluxos {

    //Guardar datos simples como primitivos en un archivo.
    public static void saveInfoData(String ruta, ArrayList<Modul> modulos) throws IOException, UD2Exceptions {

        //DataOutputStream escribe los datos en binario. El flujo está envuelto en BufferedOutputStream que optimiza operaciones de escritura.
        try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(ruta)))) {

            out.writeInt(modulos.size());//cantidad de modulos

            for (Modul modulo : modulos) {
                out.writeUTF(modulo.getCodi()); //String en binario
                out.writeUTF(modulo.getNom());
                out.writeInt(modulo.getHores());
            }

        } catch (IOException e) {
            throw new UD2Exceptions("Error al guardar los datos: ");
        }

    }

    //Leer datos primitivos desde un archivo
    public static ArrayList<Modul> readInfoData(String ruta) throws IOException, UD2Exceptions {

        ArrayList<Modul> modulos = new ArrayList<>();

        //DataInputStream lee los datos binarios del archivo. El flujo está envuelto en un BufferedInputStream para mejorar la lectura.
        try (DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(ruta)))) {

            int numModulos = in.readInt();  //EL valor entero indica cuántos objetos 'Modulo' están guardados en el archivo.

            for (int i = 0; i < numModulos; i++) { //Leer los atributos de cada módulo:
                String codi = in.readUTF();
                String nom = in.readUTF();
                int hores = in.readInt();

                Modul modulo = new Modul(codi, nom, hores); //Crear objeto modulo

                modulos.add(modulo); //Agragar 'Modulo' a la lista
            }

        } catch (IOException e) {
            throw new UD2Exceptions("Error al leer los datos: ");
        }

        return modulos;
    }

    //Guardar un objeto en un archivo
    //Guarda un objeto Serializable en un archivo usando un flujo de salida de objetos. Útil para guardar objeto en disco para ser recuperado más tarde.
    public static void saveInfoObject(String ruta, Serializable object) throws IOException, UD2Exceptions {

        if (object == null) {
            throw new IllegalArgumentException("El objeto a guardar no puede ser nulo.");
        }

        /*ObjectOutputStream para escribir el objeto en un archivo.BufferedOutputStream mejora la escritura de datos,y FileOutputStream escribe en el archivo especificado.*/
        try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(ruta)))) {

            out.writeObject(object);

        } catch (IOException e) {
            throw new UD2Exceptions("Error al guardar el objeto en el archivo: ");
        }

    }

    //Leer un objeto desde un archivo
    public static ArrayList<Modul> readInfoObject(String ruta) throws IOException, UD2Exceptions {

        //ObjectInputStream deserializa el objeto guardado previamente.
        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(ruta)))) {

            Object obj = in.readObject(); //lee el objeto y lo devuelve como un Object

            if (obj instanceof ArrayList) { //se comprueba si el objeto leído es una instancia de ArrayList
                return (ArrayList<Modul>) obj;

            } else {
                throw new IOException("El objeto leído no es un ArrayList<Modulo> válido.");
            }

        } catch (ClassNotFoundException | IOException e) {
            throw new UD2Exceptions("Error al leer el objeto desde el archivo: ");
        }
    }

}
