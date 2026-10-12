package nerea.montoya_olivares_nerea_bloc09_tarea;

import java.time.LocalDate;

/**
 * POJO de Incidencia, contiene los getters, setters y toString
 *
 * @author Nerea Montoya
 */

public class Incidencia {
    
    private String descripcion;
    private String localizacion;
    private String prioridad;
    private LocalDate fecha;

    public Incidencia(String descripcion, String localizacion, String prioridad) {
        this.descripcion = descripcion;
        this.localizacion = localizacion;
        this.prioridad = prioridad;
        this.fecha = LocalDate.now();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return fecha + "    " + descripcion + "    " + localizacion;
    }
}
