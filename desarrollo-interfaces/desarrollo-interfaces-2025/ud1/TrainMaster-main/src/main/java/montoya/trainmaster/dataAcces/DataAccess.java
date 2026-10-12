
package montoya.trainmaster.dataAcces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
* @author Nerea
*/

public class DataAccess {
    
    public static Connection getConnection(){
        Connection connection = null;   //connection: Se inicializa como null y almacenará la conexión que se establecerá con DB.
        Properties properties=new Properties(); //Se utiliza para cargar las propiedades desde un archivo, que contiene la configuración de la conexión a la base de datos.

        try {
            properties.load(DataAccess.class.getClassLoader().getResourceAsStream("properties/application.properties"));  //Carga de las propiedades desde un archivo:
            //String connectionString = "jdbc:sqlserver://localhost:1433;database=simulapdb;user=sa;" + "password=Pwd1234.;encrypt=false;trustServerCertificate=true;loginTimeOut=10;";
            String connectionUrl=properties.getProperty("connectionUrl"); //Se obtiene desde el archivo properties
            connection = DriverManager.getConnection(connectionUrl);  //método intenta establecer la conexión con la base de datos usando la URL
          
        } catch (Exception e) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        return connection;
    }

    
    
}

