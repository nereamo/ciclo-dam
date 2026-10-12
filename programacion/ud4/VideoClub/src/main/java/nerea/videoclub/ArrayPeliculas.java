package nerea.videoclub;

/**
 * @author Nerea
 */
public class ArrayPeliculas {

    private Pelicula[] peliculas;
    private int numPeliculas;

    public ArrayPeliculas(int maximPeliculas) {

        peliculas = new Pelicula[maximPeliculas];
        numPeliculas = 0;
    }

    public Pelicula getPeliculas(String titulo) throws Exception {

        for (int i = 0; i < numPeliculas; i++) {
            if (peliculas[i].getTitulo().equalsIgnoreCase(titulo)) {
                return peliculas[i];
            }
        }
        throw new Exception("La pelicula con el título " + titulo + " no existe.");
    }

    public int getNumPeliculas() {
        return numPeliculas;
    }

    public boolean addPelicula(Pelicula pelicula) {

        if (numPeliculas < peliculas.length) {
            peliculas[numPeliculas] = pelicula;
            numPeliculas++;
            return true;
        } else {
            return false;
        }
    }

    public int contarPeliculasConMejorValoracion(double valoracion) {
        int contador = 0;

        for (int i = 0; i < numPeliculas; i++) {
            if (peliculas[i].getValoracion() > valoracion) {
                contador++;
            }
        }
        return contador;
    }

    public void mostrarDatosPeliculas() {
        if (peliculas != null && peliculas.length > 0) {
            for (Pelicula pelicula : peliculas) {
                if (pelicula != null) {
                    System.out.println("\n" + pelicula);
                }
            }

        } else {
            System.out.println("No se encontró ninguna película");
        }
    }
}
