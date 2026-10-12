package montoya.trainmaster.dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import montoya.trainmaster.dataAcces.DataAccess;
import static montoya.trainmaster.dataAcces.DataAccess.getConnection;

//Codigo adaptado de chatGPT, ayuda de compañeros.
/**
 * @author Nerea
 */
//Codigo que contiene  las variables, getters-setters y ArrayList de Workouts de la DB.
public class Workouts {

    private int id;
    private String ForDate;
    private String Comments;
    private int userId;
    private List<Exercicis> exercises;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getForDate() {
        return ForDate;
    }

    public void setForDate(String ForDate) {
        this.ForDate = ForDate;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String Comments) {
        this.Comments = Comments;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return this.userId;
    }
    
    
    //ArrayList que almacena los workouts asignados a un usuario
    public static ArrayList<Workouts> getWorkoutsUser(int userId) {
        ArrayList<Workouts> workoutsUser = new ArrayList<>();
        String sql = "SELECT * FROM Workouts WHERE UserId = ?";

        Connection connection = getConnection();

        try {

            PreparedStatement selectStatement = connection.prepareStatement(sql);
            selectStatement.setInt(1, userId);
            ResultSet resultSet = selectStatement.executeQuery();

            while (resultSet.next()) {
                Workouts workout = new Workouts();
                workout.setId(resultSet.getInt("Id"));
                workout.setForDate(resultSet.getString("ForDate"));
                workout.setComments(resultSet.getString("Comments"));
                workoutsUser.add(workout);
            }

            selectStatement.close();
            connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return workoutsUser;
    }

    
    //Array que devuelve todos los Workouts de la BD
    public static ArrayList<Workouts> getAllWorkouts() {
        ArrayList<Workouts> allWorkouts = new ArrayList<>();
        String sql = "SELECT * FROM Workouts";

        Connection connection = getConnection();

        try {

            PreparedStatement selectStatement = connection.prepareStatement(sql);
            ResultSet resultSet = selectStatement.executeQuery();

            while (resultSet.next()) {
                Workouts workout = new Workouts();
                workout.setId(resultSet.getInt("Id"));
                workout.setForDate(resultSet.getString("ForDate"));
                workout.setUserId(resultSet.getInt("UserId"));
                workout.setComments(resultSet.getString("Comments"));
                allWorkouts.add(workout);
            }

            selectStatement.close();
            connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allWorkouts;
    }
    
    //Consulta en la BD para poder eliminar un workout existente
    public static boolean deleteWorkout(int workoutId) {
        try {
            
            String query = "DELETE FROM Workouts WHERE id = ?";
            Connection connection = getConnection();

            try (PreparedStatement stmt = connection.prepareStatement(query)) {

                stmt.setInt(1, workoutId);
                int rowsAffected = stmt.executeUpdate();
                return rowsAffected > 0;  // Si se eliminó al menos una fila, es exitoso
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // En caso de error, retornar false
        }
    }
} 
    