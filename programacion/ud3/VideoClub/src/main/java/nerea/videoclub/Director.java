package nerea.videoclub;

import java.time.LocalDate;
import java.time.Period;

/**
 * @author Nerea
 */
public class Director {

    String nombre;
    LocalDate fechaNacimiento;
    int galardones;
    int añoUltimaPelicula;

    public Director(String nombre, LocalDate fechaNacimiento, int galardones, int añoUltimaPelicula) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.galardones = galardones;
        this.añoUltimaPelicula = añoUltimaPelicula;
    }

    public Director() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null || fechaNacimiento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede estar en el futuro.");
        }
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getGalardones() {
        return galardones;
    }

    public void setGalardones(int galardones) throws Exception {
        if (galardones < 0) {
            throw new Exception("El número de galardones no puede ser inferior a 0");
        }
        this.galardones = galardones;
    }

    public int getAñoUltimaPelicula() {
        return añoUltimaPelicula;
    }

    public void setAñoUltimaPelicula(int añoUltimaPelicula) throws Exception {
        int currentYear = LocalDate.now().getYear();
        if (añoUltimaPelicula < 1895 || añoUltimaPelicula > currentYear) {
            throw new Exception("El año debe tener 4 cifras entre 1985 y año actual");
        }
        this.añoUltimaPelicula = añoUltimaPelicula;
    }

    @Override
    public String toString() {
        return "\nNombre: " + nombre
                + ", \nFecha de Nacimiento: " + fechaNacimiento
                + ", \nNúmero de galardones: " + galardones
                + ", \nAño de la última pelicula: " + añoUltimaPelicula;
    }

    public int calculaEdad() {
        LocalDate currentDate = LocalDate.now();
        return Period.between(fechaNacimiento, currentDate).getYears();
    }

}
