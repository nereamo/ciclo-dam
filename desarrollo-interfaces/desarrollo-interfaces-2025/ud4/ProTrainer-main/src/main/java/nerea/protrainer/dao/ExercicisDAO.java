
package nerea.protrainer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import nerea.protrainer.dataAccess.DataAccess;
import nerea.protrainer.dto.Exercicis;

/**
 * Clase ExercicisDAO maneja las operaciones con la base de datos relacionado con los ejercicios {@code ExercicisDAO}.
 * Recupera, inserta, elimina y actualiza datos de la base de datos de los ejercicios.
 * 
 * @author Nerea
 */

public class ExercicisDAO {
    
    /**
     * Constructor por defecto.
     */
    public ExercicisDAO(){
        
    }

    /**
     * Recupera todods los ejercicios de la base de datos.
     * 
     * @return Devuelve en una lista todos los ejercicios de la base de datos.
     */
    public static ArrayList<Exercicis> exercicisBD() {
        
        ArrayList<Exercicis> ejerciciosList = new ArrayList<>();
        
        String sql = "SELECT * FROM Exercicis";

        try (Connection conn = DataAccess.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Exercicis exercise = new Exercicis();
                exercise.setId(rs.getInt("Id"));
                exercise.setNomExercici(rs.getString("NomExercici"));
                exercise.setDescripcion(rs.getString("Descripcio"));
                ejerciciosList.add(exercise);
            }

            if (ejerciciosList.isEmpty()) {

                System.out.println("No hay ejercicios disponibles.");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ejerciciosList;
    }

    /**
     * Inserta nuevos ejercicios en la base de datos.
     * 
     * @param nombre Nombre del ejercicio.
     * @param descripcion Descripción del ejercicio.
     * @return Devuelve {@code true} si se ha insertado correctamente.
     */
    public static boolean insertarEjerciciosBD(String nombre, String descripcion) {
        
        String sql = "INSERT INTO Exercicis (NomExercici, Descripcio) VALUES (?, ?)";

        try (Connection conn = DataAccess.getConnection(); 
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, descripcion);
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
    }

    /**
     * Elimina ejercicios de la base de datos.
     * 
     * @param exerciseId Id del ejrcicio.
     * @return Devuelve {@code true} si se ha eliminado correctamente.
     */
    public static boolean eliminaExercicis(int exerciseId) {
        String sql = "DELETE FROM Exercicis WHERE Id = ?";

        try (Connection conn = DataAccess.getConnection(); 
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, exerciseId);
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Edita los ejercicios de la base de datos.
     * 
     * @param exercise Objeto Exercicis con nuevos valores.
     * @return Devuelve {@code true} si se ha editado correctamente.
     */
    public static boolean editarEjerciciosBD(Exercicis exercise) {
        String sql = "UPDATE Exercicis SET NomExercici = ?, Descripcio = ? WHERE Id = ?";

        try (Connection conn = DataAccess.getConnection(); 
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, exercise.getNomEjercicio());
            ps.setString(2, exercise.getDescripcion());
            ps.setInt(3, exercise.getId());

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
