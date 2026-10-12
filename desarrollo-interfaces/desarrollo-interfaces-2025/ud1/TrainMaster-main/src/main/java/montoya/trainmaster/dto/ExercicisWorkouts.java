package montoya.trainmaster.dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import montoya.trainmaster.dataAcces.DataAccess;

//Codigo adaptado de chatGPT, ayuda de compañeros.

/**
 * @author Nerea
 */
public class ExercicisWorkouts {

    private int id;
    private int idWorkout;
    private int idExercici;

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdWorkout() {
        return idWorkout;
    }

    public void setIdWorkout(int idWorkout) {
        this.idWorkout = idWorkout;
    }

    public int getIdExercici() {
        return idExercici;
    }

    public void setIdExercici(int idExercici) {
        this.idExercici = idExercici;
    }

    //Arraylist que contiene los ejercicios de un workout
    public List<Exercicis> getExercisesByWorkout(int workoutId) {
        List<Exercicis> exercises = new ArrayList<>();
        String query = "SELECT e.Id, e.NomExercici, e.Descripcio "
                + "FROM Exercicis e "
                + "JOIN ExercicisWorkouts ew ON e.Id = ew.IdExercici "
                + "WHERE ew.IdWorkout = ?";
        try (Connection connection = DataAccess.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, workoutId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Exercicis exercici = new Exercicis(
                        resultSet.getInt("Id"),
                        resultSet.getString("NomExercici"),
                        resultSet.getString("Descripcio")
                );
                exercises.add(exercici);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exercises;
    }

    //Arraylist que almacena los workouts asociados a un ejercicio
    public List<Workouts> getWorkoutsByExercise(int exerciciId) {
        List<Workouts> workouts = new ArrayList<>();
        String query = "SELECT w.* FROM Workouts w "
                + "JOIN EjercicisWorkouts ew ON w.Id = ew.IdWorkout "
                + "WHERE ew.IdExercici = ?";

        try (Connection connection = DataAccess.getConnection(); 
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, exerciciId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Workouts workout = new Workouts();
                workout.setId(resultSet.getInt("Id"));
                workout.setForDate(resultSet.getString("ForDate"));  
                workout.setComments(resultSet.getString("Comments")); 
                workouts.add(workout);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return workouts;
    }
    
    
    //Metodo para insertar ejercicios en un Workout seleccionado
    public void insertExerciseInWorkout(ExercicisWorkouts workoutExercici) throws SQLException {
        // Establecer conexión a la base de datos
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DataAccess.getConnection();

            // Crear la consulta SQL para insertar el ejercicio en el workout
            String sql = "INSERT INTO ExercicisWorkouts (idWorkout, idExercici) VALUES (?, ?)";
            ps = conn.prepareStatement(sql);

            // Establecer los parámetros del PreparedStatement
            ps.setInt(1, workoutExercici.getIdWorkout());
            ps.setInt(2, workoutExercici.getIdExercici());

            // Ejecutar la inserción
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Ejercicio añadido al workout con éxito.");
            } else {
                System.out.println("No se pudo añadir el ejercicio al workout.");
            }

        } catch (SQLException e) {
            // Manejo de errores de base de datos
            throw new SQLException("Error al insertar en la base de datos", e);
        } finally {
            // Cerrar los recursos
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}
