package nerea.protrainer.dto;

/**
 * Clase que representa un ejercicio {@code Exercicis}.
 * Contiene la información de un ejercicio como el id, el nombre, la descripción y la foto del ejercicio.
 * 
 * @author Nerea
 */
public class Exercicis {

    /**
     * Variables relacionadas con los ejercicios de la aplicación.
     * <p>
     * - `id` almacena el id del ejercicio. 
     * - `nomEjercicio` almacena el nombre del ejercicio.
     * - `descripcion` almacena la descripcion del ejercicio.
     * - `demoFoto` almacena las imagenes del ejercicio.
     */
    private int id;
    private String nomEjercicio;
    private String descripcion;
    private byte[] demoFoto;
    
    /**
     * Constructor por defecto.
     */
    public Exercicis(){
        
    }

    /**
     * Obtiene el Id del ejercicio.
     * @return Devuelve el Id del ejercicio.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el Id del dejercicio.
     * @param id Id del ejercicio
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del ejercicio.
     * @return Devuelve el nombre del ejercicio.
     */
    public String getNomEjercicio() {
        return nomEjercicio;
    }

    /**
     * Establece el nombre del dejercicio.
     * @param nomEjercicio Nombre del ejercicio
     */
    public void setNomExercici(String nomEjercicio) {
        this.nomEjercicio = nomEjercicio;
    }

    /**
     * Obtiene la descripción del ejercicio.
     * @return Devuelve la descripción del ejercicio.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripcion para el ejercicio.
     * @param descripcion Descripcion del ejercicio.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la foto del ejercicio.
     * @return Devuelve la foto del ejercicio.
     */
    public byte[] getDemoFoto() {
        return demoFoto;
    }

    /**
     * Establece la fotografía del ejercicio.
     * @param demoFoto DemoFoto del erjercicio.
     */
    public void setDemoFoto(byte[] demoFoto) {
        this.demoFoto = demoFoto;
    }

    /**
     * Devuelve una cadena con los datos obtenidos del nombre y la descripción del ejercicio.
     * @return Cadena con nombre y descripción.
     */
    @Override
    public String toString() {
        return getNomEjercicio() + " " + getDescripcion();
    }
}
