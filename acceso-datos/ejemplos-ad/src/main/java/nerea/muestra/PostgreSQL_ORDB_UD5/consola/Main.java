package nerea.muestra.PostgreSQL_ORDB_UD5.consola;

import java.util.List;
import java.sql.SQLException;
import java.util.Arrays;
import nerea.muestra.Exception.AppException;
import nerea.muestra.PostgreSQL_ORDB_UD5.component.Conexiondb;
import nerea.muestra.PostgreSQL_ORDB_UD5.component.OperacionesDB;
import nerea.muestra.PostgreSQL_ORDB_UD5.component.Servidor;

/**
 * Class Main, establece conexión con BD.
 *
 * @author Nerea Montoya
 */
public class Main {

    private static OperacionesDB op = new OperacionesDB();

    public static void main(String[] args) {

        try{
            
            //Muestra la lista con todos los servidores de la base de datos
            System.out.println("Lista completa de los servidores:");
            List<Servidor> servidores = op.recuperarServidoresDB();
            for (Servidor servidor : servidores) {
                System.out.println(servidor);
            }
            
            System.out.println("------------------------------------------");
            
            //Muestra un servidor filtrado por su código
            System.out.println("\nServidor filtrado por código:");
            String codigoServer = "002";
            Servidor servidorCodigo = op.recuperarServidorPorCodigoDB(codigoServer);
            System.out.println(servidorCodigo);
            
            System.out.println("------------------------------------------");
            
            //Muestra el nuevo servidor añadido y la lista completa
            System.out.println("\nInserción de un nuevo servidor:");

            Conexiondb conexion = new Conexiondb("PostgreSQL", "192.168.1.1", 5432);
            Servidor newServer = new Servidor("008", "Server06", Arrays.asList("Ana", "Juan", "Maria", "Pablo"), conexion, true);

             int servidoresNuevos = op.insertarServidor(newServer);
             System.out.println(servidoresNuevos);
        
            
            System.out.println("------------------------------------------");
            
            //Muestra el servidor modificado cuando se ha eliminado un usuario de un servidor en concreto
            System.out.println("\nEliminar un usuario de un servidor:");
            
            String usuario = "Juan";
            String codigoServer2 = "007";
            
            int eliminarUsuario = op.eliminarUsuarioServidorDB(usuario, codigoServer2);
            System.out.println(eliminarUsuario);
            
            
            System.out.println("------------------------------------------");
            
            //Muestra el servidor con los datos de conexion modificados
            System.out.println("\nModificar la conexión de un servidor:");

            String sgbd2 = "MySQL";
            String ipDominio2 = "192.168.1.99";
            int puerto2 = 8069;
            String codigoServer3 = "007";

            Servidor updateConnection = op.modificarConexionServidorDB(sgbd2, ipDominio2, puerto2, codigoServer3);
            System.out.println(updateConnection);
            
        }catch(SQLException e){
            System.out.println("Error de conexión: " + e.getMessage());
        } catch (AppException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
