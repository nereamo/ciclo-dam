
package nerea.protrainer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import nerea.protrainer.dataAccess.DataAccess;
import nerea.protrainer.dto.Workouts;

/**
 * Clase WorkoutsDAO maneja las operaciones con la base de datos relacionado con los Workouts {@code WorkoutsDAO}.
 * Consultar, insertar y eliminar datos de la base de datos de los Workouts. 
 * 
 * @author Nerea
 */

public class WorkoutsDAO {
    
    /**
     * Constructor por defecto.
     */
    public WorkoutsDAO(){
        
    }
    
    /**
      * Recupera todos los entrenamientos de la base de datos.
      * 
      * @return Devuelve una lista con todos los entrenamientos.
      */
    public static ArrayList<Workouts> workoutsBD() {
        
        ArrayList<Workouts> workoutsList = new ArrayList<>();
        
        String sql = "SELECT * FROM Workouts";

        try (Connection conn = DataAccess.getConnection(); 
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery()){

            while (rs.next()) {
                Workouts workout = new Workouts();
                workout.setId(rs.getInt("Id"));
                workout.setForDate(rs.getString("ForDate"));
                workout.setUserId(rs.getInt("UserId"));
                workout.setComments(rs.getString("Comments"));
                workoutsList.add(workout);
            }
            
            if (workoutsList.isEmpty()) {
            
            System.out.println("No se encontraron entrenamientos en la base de datos.");
        }

        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return workoutsList;
    }

    /**
     * Recupera todos los entrenamientos asignados a un usuario.
     * 
     * @param userId Id del usuario asociado al entrenamiento.
     * @return Devuelve la lista de los entrenamientos asociados al Id del usuario.
     */
    public static ArrayList<Workouts> workoutUsuari(int userId) {
        
        ArrayList<Workouts> workoutsListUser = new ArrayList<>();
        
        String sql = "SELECT * FROM Workouts WHERE UserId = ?";

        try (Connection conn = DataAccess.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Workouts workout = new Workouts();
                    workout.setId(rs.getInt("Id"));
                    workout.setForDate(rs.getString("ForDate"));
                    workout.setComments(rs.getString("Comments"));
                    workoutsListUser.add(workout);
                }
            }

            if (workoutsListUser.isEmpty()) {

                System.out.println("El usuario " + userId + " no tiene entrenamientos asignados.");

            }

        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return workoutsListUser;
    }
    
    /**
     * Insertar un nuevo entrenamiento en la base de datos.
     * 
     * @param userId Id del usuario asociado al entrenamiento insertado.
     * @param comments  Comentario asociado al entrenamiento.
     * @param forDate Fecha del entrenamiento.
     * @throws SQLException Si hay un error al insertar el entrenamiento en la base de datos. 
     */
    public static void insertarWorkoutBD(int userId, String comments, String forDate) throws SQLException {

        String sql = "INSERT INTO Workouts (ForDate, UserId, Comments) VALUES (?, ?, ?)";

        try (Connection conn = DataAccess.getConnection(); 
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, forDate);
            ps.setInt(2, userId);
            ps.setString(3, comments);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Workouts.class.getName()).log(Level.SEVERE, "Error al guardar el workout", ex);
            throw ex;
        }
    }
    
    /**
     * Eliminar un entrenamiento de la base de datos.
     * 
     * @param workoutId Id del workout a eliminar.
     * @return Devuelve {@code true} si se ha eliminado correctamente.
     */
    public static boolean eliminarWorkoutBD(int workoutId) {

        String sql = "DELETE FROM Workouts WHERE id = ?";
        
        try (Connection conn = DataAccess.getConnection(); 
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, workoutId);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                
                System.out.println("No se pudo eliminar el workout con ID: " + workoutId);
                
                return false;
            }
            return true;

        } catch (SQLException e) {
             Logger.getLogger(Workouts.class.getName()).log(Level.SEVERE, "Error al eliminar el workout", e);
            return false;
        }
    }
}
