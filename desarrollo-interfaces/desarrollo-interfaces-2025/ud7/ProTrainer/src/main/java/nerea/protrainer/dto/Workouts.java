package nerea.protrainer.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase que representa un entrenamiento {@code Workouts}.
 * Contiene la información de un workout como el id, fecha, comentario y el id usuario asignado.
 * 
 * @author Nerea
 */
public class Workouts {

    /**
     * Variables relacionadas con los workouts de la aplicación.
     * <p>
     * - `id` almacena el id del workout. 
     * - `forDate` almacena la fecha del workout.
     * - `comments` almacena el comentario del workout.
     * - `userId` almacena el id del usuario asignado a un workout.
     */
    private int id;
    private String forDate;
    private String comments;
    private int userId;
    
    /**
     * Constructor por defecto.
     */
    public Workouts(){
        
    }

    /**
     * Obtiene el Id del entrenamiento.
     * @return Devuelve el Id del entrenamiento.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el id del entrenmiento.
     * @param id Id del entrenamiento.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene la fecha y hora del entrenamiento en formato yyyy-MM-dd HH:mm:ss.
     * @return Devuelve la fecha y la hora en un formato simple dd/MM/yyyy HH:mm.
     */
    public String getForDate() {
        if (forDate == null || forDate.isEmpty()) {
            return "";
        }

        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            Date date = inputDateFormat.parse(forDate);
            return outputDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return forDate;
        }
    }

    /**
     * Establece la fecha y la hora del entrenamiento.
     * @param forDate Fecha y hora asignada al entrenamiento.
     */
    public void setForDate(String forDate) {
        this.forDate = forDate;
    }

    /**
     * Obtiene el comentario del entrenamiento.
     * @return Devuelve el comentario asignado a un entrenamiento.
     */
    public String getComments() {
        return comments;
    }

    /**
     * Establece un comentario para un entrenamiento.
     * @param comments Comentario asignado.
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * Establece el id del usuario asigando a un entrenamiento.
     * @param userId Id del usuario asignado al entrenamiento.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Obtiene el id del usuario asignado al entrenamiento.
     * @return Devuelve el id asociado al entrenamiento.
     */
    public int getUserId() {
        return this.userId;
    }

    /**
     * Devuelve una cadena con los datos obtenidos de la fecha y hora más los comentarios.
     * @return Cadena con fecha, hora y comentarios.
     */
    @Override
    public String toString(){
        return getForDate() + " " + getComments();
    } 
}
