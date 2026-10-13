
package nerea.muestra.JDBC_UD3.Properties;

import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import nerea.muestra.Exception.AppException;

/**
* @author Nerea
*/

public class PruebasJDBC {
    
    public static void main(String[] args) {

        try {
            
            
            String url = "jdbc:mysql://daw.paucasesnovescifp.cat:3306/biblioteca?user=usuari&password=seCret_24";
            Properties properties = new Properties();

            BibliotecaDao biblioteca = new BibliotecaDao(url, properties);

            //Obtener lenguas
            List<String> lenguas = biblioteca.mostrarLenguas();
            for (String lengua : lenguas) {
                System.out.println(lengua);
            }
            
            //Insertar lengua
            System.out.println("\nAñadir idioma: ");
            int count = biblioteca.newLengua("Sueca");
            System.out.println(count);
        
        } catch (AppException ex) {
            Logger.getLogger(PruebasJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
