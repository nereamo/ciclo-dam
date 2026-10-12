
package nerea.protrainer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import nerea.protrainer.dataAccess.DataAccess;
import nerea.protrainer.dto.Exercicis;
import nerea.protrainer.dto.Workouts;

/**
 * Clase ExerciciesWorkoutsDAO maneja las operaciones con la base de datos relacionado con los Workouts y sus ejercicios {@code ExerciciesWorkoutsDAO}.
 * Consultar e insertar datos de la base de datos de ExercicisWorkouts.
 * 
 * @author Nerea
 */

public class ExercicisWorkoutsDAO {
    
    /**
     * Constructor por defecto.
     */
    public ExercicisWorkoutsDAO(){
        
    }

    /**
     * Recupera todos los ejercicios asignados a un workout de la base de datos.
     * 
     * @param workoutId Id del workout al que estan asociados los ejercicios.
     * @return Devuelve una lista con los ejercicios asignados a un workout en concreto.
     */
    public static ArrayList<Exercicis> ejerciciosDelWorkout(int workoutId) {
        
        ArrayList<Exercicis> ejerciciosList = new ArrayList<>();

        String sql = """
                    SELECT * FROM ExercicisWorkouts ew
                    JOIN Exercicis e ON ew.IdExercici = e.Id
                    WHERE ew.IdWorkout = ?
                    """;

        try (Connection conn = DataAccess.getConnection(); 
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, workoutId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Exercicis exercise = new Exercicis();
                        exercise.setId(rs.getInt("Id"));
                        exercise.setNomExercici(rs.getString("NomExercici"));
                        exercise.setDescripcion(rs.getString("Descripcio"));
                ejerciciosList.add(exercise);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ejerciciosList;
    }

    /**
     * Asigna ejercicios a un workout.
     * 
     * @param workoutId Id del workout.
     * @param exerciseId Id de los ejercicios.
     * @throws SQLException Si hay un error al asignar los ejercicios en el workout en la base de datos.
     */
    public static void asignarEjercicioAWorkout(int workoutId, int exerciseId) throws SQLException {
        
        String sql = "INSERT INTO ExercicisWorkouts (IdWorkout, IdExercici) VALUES (?, ?)";

        try (Connection conn = DataAccess.getConnection(); 
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, workoutId);
            ps.setInt(2, exerciseId);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Workouts.class.getName()).log(Level.SEVERE, "Error al guardar el ejercicio en el workout", ex);
            throw ex;
        }
    }
}
