
package nerea.muestra.JDBC_UD3.Properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import nerea.muestra.Exception.AppException;

/**
* @author Nerea
*/

public class BibliotecaDao {
    
    String url;
    Properties properties = new Properties();
    
    //Construcor de class con params url y properties
    public BibliotecaDao(String url, Properties properties){
        
        this.url = url; 
        this.properties = properties;
    }

    //Gett/Sett de URL
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) throws AppException {
        if(url == null || url.isEmpty()){
            throw new AppException ("La url no puede estar vacía o ser null.");
        }
        this.url = url;
    }
    
    //Gett/Sett de PROPERTIES
    public Properties getproperties(){
        return properties;
    }
    
    public void setProperties(Properties properties) throws AppException{
        if(properties == null || properties.isEmpty()){
            throw new AppException ("El objeto properties no puede estar vacío o ser null.");
        }
        this.properties = properties;
    }
    
    //Obtener los datos de la BD
    public List<String> mostrarLenguas() throws AppException {
        List <String> lenguas = new ArrayList<>();
        String sql = "SELECT * FROM LLENGUES";
        
        try (Connection con = DriverManager.getConnection(url, properties);
            PreparedStatement ps = con.prepareStatement(sql); 
                ResultSet rs = ps.executeQuery()) {
            
            while(rs.next()){
                lenguas.add(rs.getString("llengua"));
            } 
            
        } catch (SQLException ex) {
            Logger.getLogger(BibliotecaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lenguas;
    }
    
    //Insertar nuevos datos en BD
    public int newLengua(String lengua) throws AppException {
        String sql = "INSERT INTO LLENGUES (LLENGUA) VALUES (?)";

        try (Connection con = DriverManager.getConnection(url, properties); PreparedStatement ps = con.prepareStatement(sql)) {

            con.setAutoCommit(false);
            ps.setString(1, lengua);

            int newRows = ps.executeUpdate();
            if (newRows == 0) {
                con.rollback();
                return 0;
            }

            con.commit();
            return newRows;

        } catch (SQLException e) {
            throw new AppException("Error al insertar la lengua: " + e.getMessage());
        }
    }
}

