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
    static ArrayPeliculas arrayPeliculas = new ArrayPeliculas(40);
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int options = 0;

        while (options != 9) {

            System.out.println("\n----Menú---- ");
            System.out.println("1. Crear un director");
            System.out.println("2. Ver los datos del director");
            System.out.println("3. Aumentar el número  de galardones recibidos por el director");
            System.out.println("4. Crear una película");
            System.out.println("5. Ver los datos de las películas");
            System.out.println("6. Modificar la valoración de los espectadores de la película");
            System.out.println("7. Mostrar la clasificación de la película");
            System.out.println("8. Ver el número de películas con mejor valoración");
            System.out.println("9. Salir");
            System.out.println("--------------------");
            System.out.println("Elige una opción: ");

            options = sc.nextInt();

            try {

                switch (options) {

                    case 1:
                        System.out.println("1. Crear un director: ");
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
                            int aumentoGalardones = Utilidades.pideEntero();
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
                        arrayPeliculas.addPelicula(pelicula);
                        break;

                    case 5:
                        System.out.println("5. Datos de las películas: ");
                        arrayPeliculas.mostrarDatosPeliculas();
                        break;

                    case 6:
                        System.out.println("6. Modifica la valoración de la película: ");
                        try {
                            System.out.println("\n¿Qué película estás buscando?");
                            String titulo = Utilidades.pideString();
                            Pelicula pelicula = arrayPeliculas.getPeliculas(titulo);

                            if (pelicula != null) {
                                System.out.println("Asigna una nueva valoración: ");
                                double nuevaValoracion = Utilidades.pideDouble();
                                pelicula.setValoracion(nuevaValoracion);
                                System.out.println("Se ha actualizado la valoración correctamente.");
                            } else {
                                System.out.println("No se ha encontrado la película con el título proporcionado.");
                            }
                        } catch (Exception e) {

                            System.out.println(e.getMessage());
                        }
                        break;

                    case 7:
                        System.out.println("7. Clasificación de la película: ");
                        System.out.println("\nQué película estás buscando?");
                        String titulo = Utilidades.pideString();
                        Pelicula clasificacionPelicula = arrayPeliculas.getPeliculas(titulo);
                        if (clasificacionPelicula != null) {
                            generaInformeclasificación(clasificacionPelicula, director);
                        } else {
                            System.out.println("No se he encontrado la película.");
                        }
                        break;

                    case 8:
                        System.out.println("8. Ver el número de películas con mejor valoración");
                        try {
                            System.out.println("\nQue valoración deseas ver?");
                            double valoracion = Utilidades.pideDouble();
                            int numPeliculas = arrayPeliculas.contarPeliculasConMejorValoracion(valoracion);
                            System.out.println("El número de películas con una valoración mayor a " + valoracion + " es: " + numPeliculas);

                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 9:
                        System.out.println("Hasta la próxima!");
                        break;

                    default:
                        System.out.println("Elige una opción del 1 al 9.");
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
            director.setNombre(Utilidades.pideString());

            System.out.println("Introduce la fecha de nacimiento (dd/MM/yyyy): ");
            director.setFechaNacimiento(Utilidades.pideFecha());

            System.out.println("¿Cuantos galardones tiene el director? ");
            director.setGalardones(Utilidades.pideEntero());

            System.out.println("¿En qué año dirigió su última película? ");
            director.setAñoUltimaPelicula(Utilidades.pideEntero());

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static void creaPelicula() {

        try {
            pelicula = new Pelicula();

            System.out.println("Introduce el título de la película: ");
            pelicula.setTitulo(Utilidades.pideString());

            System.out.println("¿Cuantos minutos dura la película? ");
            pelicula.setDuracion(Utilidades.pideEntero());

            System.out.println("¿Cuantos espectadores han visto la película? ");
            pelicula.setVisualizaciones(Utilidades.pideEntero());

            System.out.println("¿Qué valoración ha recibido la película? ");
            pelicula.setValoracion(Utilidades.pideDouble());

            System.out.println("¿Es apta para todos los públicos? (si/no): ");
            pelicula.setAptaPublico(Utilidades.pideBoolean());

            System.out.println("¿Cuál es el nombre del director? ");
            String nombre = Utilidades.pideString();
            if (nombre.equals(director.getNombre())) {
                pelicula.setDirectorNombre(nombre);
            } else {
                System.out.println("El nombre introducido no corresponde a ningún director.");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static void generaInformeclasificación(Pelicula pelicula, Director director) throws Exception {

        if (director == null || pelicula == null) {
            throw new Exception("El director o la película no puede estar vacío");
        } else {

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
