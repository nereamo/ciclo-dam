
package nerea.muestra.JDBC_UD3.MySQLBD;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nerea.muestra.Exception.AppException;

/**
 * @author Nerea
 */

public class NewMain {

    public static void main(String[] args) {
        try {
            
            Conexion con = new Conexion();
            //Obtener lengua
            System.out.println("\nLenguas de BD: ");
            List<String> llengues = con.getLlengues();
            for (String ll : llengues) {
                System.out.println(ll);
            }
            int count;
            
            //Insertar lengua
            System.out.println("\nAñadir idioma: ");
            count = con.newLengua("Alemana");
            System.out.println(count);
            
            //Modificar lengua
            System.out.println("\nModificar idioma: ");
            count = con.updateLengua("Alemana", "ppppppp");
            System.out.println(count);
            
            //Eliminar lengua
            System.out.println("\nEliminar idioma: ");
            String lengua = con.deleteLLengua("ppppppp");
            System.out.println(lengua);
            
        } catch (AppException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
