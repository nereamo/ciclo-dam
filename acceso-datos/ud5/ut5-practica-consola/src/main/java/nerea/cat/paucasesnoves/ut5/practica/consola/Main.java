package nerea.cat.paucasesnoves.ut5.practica.consola;

import java.util.List;
import java.sql.SQLException;
import nerea.cat.paucasesnoves.ut5.practica.auxiliares.AplicacioException;
import nerea.cat.paucasesnoves.ut5.practica.component.OperacionesDB;
import nerea.cat.paucasesnoves.ut5.practica.component.Servidor;

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
            
            String codigoServer1 = "004";
            String nomServer = "Server04";
            String[] usuarios = {"Ana", "Juan", "Maria", "Pablo"};
            String sgbd1 = "PostgreSQL";
            String ipDominio1 = "192.168.1.1";
            int puerto1 = 5432;
            boolean activo = false;

            List<Servidor> servidoresNuevos = op.insertarServidor(codigoServer1, nomServer, usuarios, sgbd1, ipDominio1, puerto1, activo);
            for (Servidor servidor : servidoresNuevos) {
                System.out.println(servidor);
            }
            
            System.out.println("------------------------------------------");
            
            //Muestra el servidor modificado cuando se ha eliminado un usuario de un servidor en concreto
            System.out.println("\nEliminar un usuario de un servidor:");
            
            String usuario = "Maria";
            String codigoServer2 = "004";
            
            List<Servidor> eliminarUsuario = op.eliminarUsuarioServidorDB(usuario, codigoServer2);
            for (Servidor servidor : eliminarUsuario) {
                System.out.println(servidor);
            }
            
            System.out.println("------------------------------------------");
            
            //Muestra el servidor con los datos de conexion modificados
            System.out.println("\nModificar la conexión de un servidor:");

            String sgbd2 = "MySQL";
            String ipDominio2 = "192.168.1.99";
            int puerto2 = 8069;
            String codigoServer3 = "004";

            Servidor updateConnection = op.modificarConexionServidorDB(sgbd2, ipDominio2, puerto2, codigoServer3);
            System.out.println(updateConnection);
            
        }catch(SQLException e){
            System.out.println("Error de conexión: " + e.getMessage());
        } catch (AplicacioException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
