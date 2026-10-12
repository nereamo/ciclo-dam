package nerea.DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import nerea.componentcalendar.Workout;

/**
 * @author Nerea
 */
public class DataAccess {

    public static Connection getConnection() throws SQLException {

        Connection connection = null;

        try {
            String connectionUrl="jdbc:sqlserver://simulapsqlserver.database.windows.net:1433;database=simulapdb25;user=simulapdbadmin@simulapsqlserver;password=Pwd1234.;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30";
            //String connectionUrl = "jdbc:sqlserver://localhost:1433;database=simulapdb;user=sa;" +"password=Pwd1234.;encrypt=false;";

            connection = DriverManager.getConnection(connectionUrl);

        } catch (Exception e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }

    public static List<LocalDate> getWorkoutDatesForMonth(int year, int month) {
        List<LocalDate> workoutDates = new ArrayList<>();
        String sql = "SELECT CONVERT(DATE, ForDate) AS workout_date FROM Workouts "
                + "WHERE YEAR(ForDate) = ? AND MONTH(ForDate) = ?";

        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, year);
            ps.setInt(2, month);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                workoutDates.add(rs.getDate("workout_date").toLocalDate());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            e.getMessage();
        }

        return workoutDates;
    }

    public static List<Workout> getWorkoutDetailsForDay(int year, int month, int day) {
        List<Workout> workouts = new ArrayList<>();

        // Consulta SQL que obtiene los detalles requeridos (usuario, comentario, workoutId, número de ejercicios)
        String sql = """
        SELECT W.Id AS WorkoutId, W.Comments, U.Nom AS UserName,
               COUNT(EW.IdExercici) AS NumberOfExercises
        FROM Workouts W
        INNER JOIN Usuaris U ON W.UserId = U.Id
        LEFT JOIN ExercicisWorkouts EW ON W.Id = EW.IdWorkout
        WHERE YEAR(W.ForDate) = ? AND MONTH(W.ForDate) = ? AND DAY(W.ForDate) = ?
        GROUP BY W.Id, W.Comments, U.Nom
    """;

        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, year);
            ps.setInt(2, month);
            ps.setInt(3, day);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int workoutId = rs.getInt("WorkoutId");
                String comments = rs.getString("Comments");
                String userName = rs.getString("UserName");
                int numberOfExercises = rs.getInt("NumberOfExercises");

                workouts.add(new Workout(workoutId, userName, comments, numberOfExercises));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return workouts;
    }

}
