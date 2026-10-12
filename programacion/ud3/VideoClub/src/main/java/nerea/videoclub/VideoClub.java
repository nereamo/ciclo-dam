/*
 */
package nerea.videoclub;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import nerea.utilidades.Utilidades;

/**
 *
 * @author Nerea
 */
public class VideoClub {

    static Director director;
    static Pelicula pelicula;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int options = 0;

        while (options != 8) {

            System.out.println("\nElige una opción: ");
            System.out.println("--------------------");
            System.out.println("1. Crear un director");
            System.out.println("2. Ver los datos del director");
            System.out.println("3. Aumentar el numero de galardones recibidos por el director");
            System.out.println("4. Crear una pelicula");
            System.out.println("5. Ver los datos de la pelicula");
            System.out.println("6. Modificar la valoración de los espectadores de la pelicula");
            System.out.println("7. Mostrar la clasificación de la pelicula");
            System.out.println("8. Salir");
            System.out.println("--------------------");
            System.out.println("Elige una opción: ");

            options = sc.nextInt();

            try {

                switch (options) {

                    case 1:
                        System.out.println("1. Creación de un director: ");
                        creaDirector();
                        break;

                    case 2:
                        if (director != null) {
                            System.out.println("2. Datos del director: ");
                            System.out.println(director + ", \nEdad: " + director.calculaEdad() + " años.");
                        } else {
                            System.out.println("Para mostrar los datos del director, primero debe crear uno.");
                        }

                        break;

                    case 3:
                        if (director != null) {
                            System.out.println("¿Cuantos galardones ha aumentado?: ");
                            int aumentoGalardones = Utilidades.demanaSencer();
                            int galardonesActuales = director.getGalardones();
                            director.setGalardones(aumentoGalardones + galardonesActuales);
                            System.out.println("El numero total de galardones actuales es de: " + director.getGalardones());
                        } else {
                            System.out.println("Para mostrar los datos del director, primero debe crear uno.");
                        }

                        break;

                    case 4:
                        System.out.println("4. Crea la película: ");
                        creaPelicula();
                        break;

                    case 5:
                        if (pelicula != null) {
                            System.out.println("5. Datos de la película: ");
                            System.out.println(pelicula);
                        } else {
                            System.out.println("Para mostrar los datos de la película, primero debe crear una.");
                        }
                        break;

                    case 6:
                        if (pelicula != null) {
                            System.out.println("6. Modifica la valoración de los espectadores: ");
                            pelicula.setValoracion(Utilidades.demanaDouble());
                        } else {
                            System.out.println("Para modificar la valoración de los espectadores, primero debe crear una película.");
                        }
                        break;

                    case 7:
                        System.out.println("7. Clasificación de la película: ");
                        generaInformeclasificación(pelicula, director);
                        break;

                    case 8:
                        System.out.println("Hasta la próxima!");
                        break;
                        
                    default:
                        System.out.println("Elige una opción del 1 al 8.");
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void creaDirector() {

        try {
            director = new Director();

            System.out.println("Introduce el nombre del director: ");
            director.setNombre(Utilidades.demanaCadena());

            System.out.println("Introduce la fecha de nacimiento (dd/MM/yyyy): ");
            director.setFechaNacimiento(Utilidades.demanaData());

            System.out.println("¿Cuantos galardones tiene el director? ");
            director.setGalardones(Utilidades.demanaSencer());

            System.out.println("¿En qué año dirigió su última película? ");
            director.setAñoUltimaPelicula(Utilidades.demanaSencer());

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static void creaPelicula() {

        try {
            pelicula = new Pelicula();

            System.out.println("Introduce el título de la película: ");
            pelicula.setTitulo(Utilidades.demanaCadena());

            System.out.println("¿Cuantos minutos dura la película? ");
            pelicula.setDuracion(Utilidades.demanaSencer());

            System.out.println("¿Cuantos espectadores han visto la película? ");
            pelicula.setVisualizaciones(Utilidades.demanaSencer());

            System.out.println("¿Qué valoración ha recibido la película? ");
            pelicula.setValoracion(Utilidades.demanaDouble());

            System.out.println("¿Es apta para todos los públicos? (si/no): ");
            pelicula.setAptaPublico(Utilidades.demanaBoolean());

            System.out.println("¿Cuál es el nombre del director? ");
            String nombre = Utilidades.demanaCadena();
            if (nombre.equals(director.getNombre())) {
                pelicula.setDirectorNombre(nombre);
            } else {
                System.out.println("El nombre introducido no corresponde a ningún director.");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static void generaInformeclasificación(Pelicula pelicula, Director director)throws Exception{
        
        if(director == null || pelicula == null){
            throw new Exception ("El director o la película no puede estar vacío");
        }else{

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println("\n---- Informe de clasificación ----");
        System.out.println("Fecha y hora: " + dateFormat.format(date));
        System.out.println("Realizado por: Nerea Montoya");
        System.out.println("Correo corporativo: nereamontoya@paucasesnovescifp.cat");
        System.out.println("El título de la película es " + pelicula.getTitulo() + ", con una duración de " + pelicula.getDuracion() + " minutos.");
        System.out.println("El director de la película es " + director.getNombre() + ", y tiene " + director.calculaEdad() + " años.");
        System.out.println("Su clasificación es " + pelicula.clasificacion() + ", con " + pelicula.getVisualizaciones() + " espectadores, y una valoración de " + pelicula.getValoracion() + " puntos.");
        System.out.println("-------------------------------------");
        }
    }
}
