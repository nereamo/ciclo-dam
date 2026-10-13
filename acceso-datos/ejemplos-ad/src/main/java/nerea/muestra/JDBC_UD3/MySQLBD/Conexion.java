
package nerea.muestra.JDBC_UD3.MySQLBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import nerea.muestra.Exception.AppException;

/**
* @author Nerea
*/

public class Conexion {
    
    private String url = "jdbc:mysql://daw.paucasesnovescifp.cat:3306/biblioteca?user=usuari&password=seCret_24";

    public Conexion(String url){
        this.url = url;
    }
    
    public Conexion(){
        
    }
    
    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url);
    }
    
    public List<String> getLlengues() throws AppException{
        List<String> llengues = new ArrayList<>();
        String sql = "SELECT * FROM LLENGUES";

        try (Connection con = getConnection(); 
                PreparedStatement ps = con.prepareStatement(sql); 
                ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                llengues.add(rs.getString("llengua"));
            }

        } catch (SQLException e) {
            throw new AppException("No se ha podido insertar. " + e.getMessage());
        } 
        return llengues;
    }
    
    //Devuelve int
    public int newLengua(String lengua) throws AppException {

        String sql = "INSERT INTO LLENGUES (LLENGUA) VALUES (?)";

        try (Connection con = getConnection(); 
                PreparedStatement ps = con.prepareStatement(sql)) {

            con.setAutoCommit(false);

            ps.setString(1, lengua);

            int newRows = ps.executeUpdate();

            if (newRows == 0 ) {
                con.rollback();
                return 0;
            }

            con.commit();
            return newRows;
            
        } catch (SQLException e) {
            throw new AppException("No se ha podido insertar. " + e.getMessage());
        }   
    }
    
    //Devuelve String
    public String newLengua2(String lengua) throws AppException{

        String sql = "INSERT INTO LLENGUES (LLENGUA) VALUES (?)";

        try (Connection con = getConnection(); 
                PreparedStatement ps = con.prepareStatement(sql)) {

            con.setAutoCommit(false);

            ps.setString(1, lengua);

            int newRows = ps.executeUpdate();

            if (newRows == 0 ) {
                con.rollback();
                return null;
            }

            con.commit();
            return lengua;
            
        } catch (SQLException e) {
            throw new AppException("No se ha podido insertar. " + e.getMessage());
        }    
    }
    
    public int updateLengua(String antiguaLengua, String nuevaLengua) throws AppException {
        String sql = "UPDATE LLENGUES SET LLENGUA = ? WHERE LLENGUA = ?";

        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            con.setAutoCommit(false);

            ps.setString(1, nuevaLengua);
            ps.setString(2, antiguaLengua);

            int updatedRows = ps.executeUpdate();

            if (updatedRows == 0) {
                con.rollback();
                return 0;
            }

            con.commit();
            return updatedRows;
        } catch (SQLException e) {
            throw new AppException("No se ha podido actualizar. " + e.getMessage());
        }   
    }
    
    public String deleteLLengua(String lengua) throws AppException {
        
        String sql = "DELETE FROM LLENGUES WHERE LLENGUA = ?";

        try (Connection con = getConnection(); 
                PreparedStatement ps = con.prepareStatement(sql)) {

            con.setAutoCommit(false);

            ps.setString(1, lengua);

            int delRows = ps.executeUpdate();

            if (delRows == 0) {
                con.rollback();
                return null;
            }
            con.commit();
            return lengua;
            
        } catch (SQLException e) {
            throw new AppException("No se ha podido eliminar. " + e.getMessage());
        }   
    }
}
