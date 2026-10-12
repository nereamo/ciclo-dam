package nerea.protrainer.dto;

/**
 * Clase que representa los ejercicios asignados a un entrenamiento {@code ExercicisWorkouts}.
 * Contiene la información del id de ExercicisWorkouts, el id del workout y el id del ejercicio.
 * 
 * @author Nerea
 */
public class ExercicisWorkouts {

    /**
     * Variables relacionadas con los ejercicios/entrenamientos de la aplicación.
     * <p>
     * - `id` almacena el id del ejercicio/entrenamiento. 
     * - `idWorkout` almacena el id del workout asignado a los ejercicios.
     * - `idEjercicio` almacena el id del ejercicio asignado a los workouts.
     */
    private int id;
    private int idWorkout;
    private int idEjercicio;
    
    /**
     * Constructor por defecto.
     */
    public ExercicisWorkouts(){
        
    }

    /**
     * Obtiene el Id de exercicicsWorkouts.
     * @return Devuelve el Id de exercicicsWorkouts.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el Id de exercicicsWorkouts
     * @param id Id de exercicicsWorkouts.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el id del workout asignado a uno o más jercicios.
     * @return Devuelve el Id asignado a uno o más jercicios.
     */
    public int getIdWorkout() {
        return idWorkout;
    }

    /**
     * Establece el Id del workout asignado a uno o más jercicios.
     * @param idWorkout Id del workout asignado a uno o más jercicios.
     */
    public void setIdWorkout(int idWorkout) {
        this.idWorkout = idWorkout;
    }

    /**
     * Obtiene el Id del ejercicio asignado a uno a omas wokouts.
     * @return Devuelve el Id del ejercicio asignado a uno o más wokouts.
     */
    public int getIdEjercicios() {
        return idEjercicio;
    }

    /**
     * Establece el Id del ejercicio asignado a uno a omas wokouts.
     * @param idEjercicios Id del ejercicio asignado a uno o más wokouts.
     */
    public void setIdExercici(int idEjercicios) {
        this.idEjercicio = idEjercicios;
    }
}
