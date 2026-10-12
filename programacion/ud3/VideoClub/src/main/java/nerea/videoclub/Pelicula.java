package nerea.videoclub;

/**
 * @author Nerea
 */
public class Pelicula {

    String titulo;
    int duracion;
    int visualizaciones;
    double valoracion;
    boolean aptaPublico;
    String directorNombre;

    static int contador = 0;

    public Pelicula(String titulo, int duracion, int visualizaciones, double valoracion, boolean aptaPublico, String directorNombre) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.visualizaciones = visualizaciones;
        this.valoracion = valoracion;
        this.aptaPublico = aptaPublico;
        this.directorNombre = directorNombre;

    }

    public Pelicula() {
        contador++;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) throws Exception {
        if (titulo.length() < 1 || titulo.length() > 100) {
            throw new Exception("El título de la película debe tener entre 1 y 100 caracteres");
        }
        this.titulo = titulo;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getVisualizaciones() {
        return visualizaciones;
    }

    public void setVisualizaciones(int visualizaciones) {
        this.visualizaciones = visualizaciones;
    }

    public double getValoracion() {
        return valoracion;
    }

    public void setValoracion(double valoracion) throws Exception {
        if (valoracion < 0.0 || valoracion > 10.0) {
            throw new Exception("La valoración de la película no puede ser inferior a 0 y tampoco mayor de 10");
        }
        this.valoracion = valoracion;
    }

    public boolean getAptaPublico() {
        return aptaPublico;
    }

    public void setAptaPublico(boolean aptaPublico) {
        this.aptaPublico = aptaPublico;
    }

    public String getDirectorNombre() {
        return directorNombre;
    }

    public void setDirectorNombre(String directorNombre) throws Exception {
        if (directorNombre == null || directorNombre.trim().isEmpty() || directorNombre.length() < 1 || directorNombre.length() > 50) {
            throw new Exception("El nombre del director debe tener entre 1 y 50 caracteres");
        }

        this.directorNombre = directorNombre;
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Pelicula.contador = contador;
    }

    @Override
    public String toString() {
        return "Titulo: " + titulo
                + ", \nDuración: " + duracion
                + ", \nNúmero de visualizaciones: " + visualizaciones
                + ", \nValoración: " + valoracion
                + ", \nEs apta para todos los públicos? " + aptaPublico
                + ", \nNombre del director: " + directorNombre
                + ", \nNúmero de películas: " + contador;
    }

    public String clasificacion() {

        if (valoracion >= 8.0 && visualizaciones >= 500000) {
            return "Excelente";
        } else if (valoracion >= 5.5 && valoracion < 8.0 && visualizaciones >= 10000 && visualizaciones < 500000) {
            return "Recomendada";
        } else if (valoracion < 5.5 && visualizaciones >= 10000 && visualizaciones < 500000) {
            return "No Recomendada";
        } else if (visualizaciones < 1000) {
            return "Sin Clasificar";
        }
        return "Sin Clasificación";
    }

}
    

    


