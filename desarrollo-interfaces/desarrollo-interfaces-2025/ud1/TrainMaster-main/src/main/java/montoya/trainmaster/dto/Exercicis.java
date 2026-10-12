
package montoya.trainmaster.dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import montoya.trainmaster.dataAcces.DataAccess;
import static montoya.trainmaster.dataAcces.DataAccess.getConnection;

//Codigo adaptado de chatGPT, ayuda de compañeros.

/**
* @author Nerea
*/
//Codigo que contiene  las variables, getters-setters y ArrayList de Exercicis de la DB.
public class Exercicis {
    
    private int id;
    private String NomExercici;
    private String Descripcio;
    private byte[] DemoFoto;
    
    // Constructor sin parámetros
    public Exercicis() {}
 
    //inicializar los atributos de una instancia de la clase cuando se crea un nuevo objeto
    public Exercicis(int id, String NomExercici, String Descripcio) {
        this.id = id;
        this.NomExercici = NomExercici;
        this.Descripcio = Descripcio;
    }
    
    public String toString(){
        return this.NomExercici;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomExercici() {
        return NomExercici;
    }

    public void setNomExercici(String NomExercici) {
        this.NomExercici = NomExercici;
    }

    public String getDescripcio() {
        return Descripcio;
    }

    public void setDescripcio(String Descripcio) {
        this.Descripcio = Descripcio;
    }
    
    public byte[] getDemoFoto() {
        return DemoFoto;
    }

    public void setDemoFoto(byte[] DemoFoto) {
        this.DemoFoto = DemoFoto;
    }
    
    //ArrayList que almacena los ejercicios de un workout
    public static ArrayList<Exercicis> getExercisesInWorkout(int workoutId) {
    ArrayList<Exercicis> exercises = new ArrayList<>();
    String query = """
        SELECT * FROM ExercicisWorkouts ew
        JOIN Exercicis e ON ew.IdExercici = e.Id
        WHERE ew.IdWorkout = ?
        """;
    Connection connection = DataAccess.getConnection();
    
    try {
         PreparedStatement selectStatement = connection.prepareStatement(query);

        selectStatement.setInt(1, workoutId);
        ResultSet resultSet = selectStatement.executeQuery();

        while (resultSet.next()) {
            Exercicis exercise = new Exercicis(
                resultSet.getInt("Id"),
                resultSet.getString("NomExercici"),
                resultSet.getString("Descripcio")
            );
            exercises.add(exercise);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return exercises;
}
    
    //Array que muestra todos los ejercicios de la BD
    public static ArrayList<Exercicis> getAllExercises() {
        ArrayList<Exercicis> exercises = new ArrayList<>();
        String query = "SELECT * FROM Exercicis";
        Connection connection = DataAccess.getConnection();
    
        try {
            PreparedStatement selectStatement = connection.prepareStatement(query);
            ResultSet resultSet = selectStatement.executeQuery();

            while (resultSet.next()) {
                Exercicis exercise = new Exercicis(
                    resultSet.getInt("Id"),
                    resultSet.getString("NomExercici"),
                    resultSet.getString("Descripcio")
                );
                exercises.add(exercise);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return exercises;
    }
    
    // Método para actualizar el ejercicio en la base de datos
    public boolean updateExercise() {
        // Conexión a la base de datos
        try (Connection conn = DataAccess.getConnection()) {
            
            String sql = "UPDATE exercises SET nomExercici = ?, descripcio = ?, demoFoto = ? WHERE id = ?";
            
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                
                stmt.setString(1, this.NomExercici);
                stmt.setString(2, this.Descripcio);
                stmt.setBytes(3, this.DemoFoto);  // Asumiendo que 'demoFoto' es un campo binario en la base de datos
                stmt.setInt(4, this.id);

                int rowsUpdated = stmt.executeUpdate();
                return rowsUpdated > 0;  // Devuelve true si se actualizó al menos una fila
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // En caso de error, retorna false
        }
    }
    
    //metodo para eliminar un ejercicio de la BD
    public static boolean deleteExercise(int exerciseId) {
        try {
            // Suponiendo que tienes una conexión a la base de datos llamada 'conn'
            String query = "DELETE FROM Exercicis WHERE Id = ?";
            Connection connection = getConnection();
            
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                
                stmt.setInt(1, exerciseId);
                int rowsAffected = stmt.executeUpdate();
                return rowsAffected > 0;  // Si se eliminó al menos una fila, es exitoso
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // En caso de error, retornar false
        }
    }
}
