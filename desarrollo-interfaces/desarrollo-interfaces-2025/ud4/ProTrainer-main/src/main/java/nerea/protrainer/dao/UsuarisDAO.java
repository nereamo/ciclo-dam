
package nerea.protrainer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nerea.protrainer.ProTrainer;
import nerea.protrainer.dataAccess.DataAccess;
import nerea.protrainer.dto.Usuari;

/**
 * Clase UsuarisDAO maneja las operaciones con la base de datos relacionado con los usuarios {@code UsuarisDAO}.
 * Recupera los usuarios que sean instructores.
 *
 * @author Nerea
 */

public class UsuarisDAO {
    
    /**
     * Constructor por defecto.
     */
    public UsuarisDAO(){
        
    }
    
    /**
     * Recupera una lista de usuarios por su email.
     * 
     * @param emailInstructor Email del usuario.
     * @return Devuelve el usuario que tiene un email asignado.
     */
    public static Usuari inicioSesionUsuario(String emailInstructor) {
        
        Usuari usuario = new Usuari();
        
        String sql = "SELECT * FROM Usuaris WHERE Email=?";

        try (Connection conn = DataAccess.getConnection(); 
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, emailInstructor);
            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    usuario.setId(rs.getInt("Id"));
                    usuario.setNom(rs.getString("Nom"));
                    usuario.setEmail(rs.getString("Email"));
                    usuario.setPasswordHash(rs.getString("PasswordHash"));
                    usuario.setInstructor(rs.getBoolean("Instructor"));
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }
    
    /**
     * Recupera los usuarios asignados a un instructor.
     * 
     * @return Devuelve la lista de los usuarios asignados a un instructor.
     */
    public static List<Usuari> usuariosAsignadosInstructor() {
        List<Usuari> usuariosList = new ArrayList<>();
        
        Usuari instructor = ProTrainer.getLoggedInstructor();

        if (instructor == null) {
            return usuariosList;
        }

        int instructorId = instructor.getId();

        String sql = "SELECT * FROM Usuaris WHERE AssignedInstructor = ? AND Instructor != 1";

        try (Connection conn = DataAccess.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, instructorId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Usuari user = new Usuari();
                    user.setId(rs.getInt("Id"));
                    user.setNom(rs.getString("Nom"));
                    user.setEmail(rs.getString("Email"));
                    user.setPasswordHash(rs.getString("PasswordHash"));
                    user.setFoto(rs.getBytes("Foto"));
                    user.setInstructor(rs.getBoolean("Instructor"));
                    usuariosList.add(user);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuariosList;
    }
}
