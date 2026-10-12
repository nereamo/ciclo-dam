package nerea.cat.paucasesnoves.ut5.practica.component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import nerea.cat.paucasesnoves.ut5.practica.auxiliares.AplicacioException;

/**
* //Class que opera sobre la base de datos. 
* @author Nerea Montoya
*/

public class OperacionesDB {
    
    public Connection getConnection() throws SQLException, AplicacioException {

        String url = "jdbc:postgresql://daw.paucasesnovescifp.cat:5432/nmontoyadb?currentSchema=ut5-practica";
        String usuario = "nmontoya";
        String password = "e41746431m";

        try {
            return DriverManager.getConnection(url, usuario, password);

        } catch (SQLException e) {
            throw new AplicacioException("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    public List<Servidor> recuperarServidoresDB() throws AplicacioException, SQLException {
        List<Servidor> servidores = new ArrayList<>();

        try (Connection con = getConnection(); 
                
            PreparedStatement ps = con.prepareStatement("""
                    SELECT s.codigo, s.nombre, s.usuaris, 
                    (s.conexion).sgbd AS conSgbd, 
                    (s.conexion).ip_dominio AS conIpDominio, 
                    (s.conexion).puerto AS conPuerto, 
                    s.activo 
                    FROM "ut5-practica".Servidors s
                    ORDER BY codigo ASC;
            """); 
                
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String codigo = rs.getString("codigo");
                String nombre = rs.getString("nombre");

                String[] usuariosArray = (String[]) rs.getArray("usuaris").getArray();
                List<String> usuarios = Arrays.asList(usuariosArray);

                Conexiondb conexion = new Conexiondb(
                        rs.getString("conSgbd"),
                        rs.getString("conIpDominio"),
                        rs.getInt("conPuerto")
                );
                boolean activo = rs.getBoolean("activo");

                servidores.add(new Servidor(codigo, nombre, usuarios, conexion, activo));
            }

        } catch (SQLException e) {
            throw new AplicacioException("Error al recuperar servidores: " + e.getMessage());
        }

        return servidores;
    }
    
    public Servidor recuperarServidorPorCodigoDB(String codigoServer) throws AplicacioException, SQLException {
        
        Servidor servidor = null;

        Connection con = getConnection();

        String sql = """
                SELECT s.codigo, s.nombre, s.usuaris,
                (s.conexion).sgbd AS conSgbd,
                (s.conexion).ip_dominio AS conIpDominio, 
                (s.conexion).puerto AS conPuerto, 
                s.activo FROM "ut5-practica".Servidors s
                WHERE s.codigo = ?;
                """;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codigoServer);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String codigo = rs.getString("codigo");
                    String nombre = rs.getString("nombre");
                    String[] usuariosArray = (String[]) rs.getArray("usuaris").getArray();
                    List<String> usuarios = Arrays.asList(usuariosArray);

                    Conexiondb conexion = new Conexiondb(
                            rs.getString("conSgbd"),
                            rs.getString("conIpDominio"),
                            rs.getInt("conPuerto")
                    );
                    boolean activo = rs.getBoolean("activo");

                    servidor = new Servidor(codigo, nombre, usuarios, conexion, activo);
                }
            }
        }

        if (servidor == null) {
            throw new AplicacioException("No se ha encontrado el servidor " + codigoServer + ".");
        }

        return servidor;
    }
    
    public List<Servidor> insertarServidor(String codigoServer, String nomServer, String[] usuarios, String sgbd, String ipDominio, int puerto, boolean activo) throws AplicacioException, SQLException {
        List<Servidor> newServidor = new ArrayList<>();

        Connection con = getConnection();

        try {

            recuperarServidorPorCodigoDB(codigoServer);
            throw new AplicacioException("El servidor " + codigoServer + " ya existe.");
        } catch (AplicacioException e) {
        }

        String insertSql = """
                        INSERT INTO "ut5-practica".Servidors(
                        codigo, nombre, usuaris, conexion, activo)
                        VALUES(?, ?, ?, ROW(?, ?, ?), ?);
                        """;

        try (PreparedStatement psInsert = con.prepareStatement(insertSql)) {
            psInsert.setString(1, codigoServer);
            psInsert.setString(2, nomServer);
            psInsert.setArray(3, con.createArrayOf("text", usuarios));
            psInsert.setString(4, sgbd);
            psInsert.setString(5, ipDominio);
            psInsert.setInt(6, puerto);
            psInsert.setBoolean(7, activo);

            int filasInsertadas = psInsert.executeUpdate();

            if (filasInsertadas > 0) {
                String sql = """
                    SELECT s.codigo, s.nombre, s.usuaris,
                    (s.conexion).sgbd AS conSgbd,
                    (s.conexion).ip_dominio AS conIpDominio, 
                    (s.conexion).puerto AS conPuerto, 
                    s.activo FROM "ut5-practica".Servidors s
                    WHERE s.codigo = ?;
                      """;

                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, codigoServer);

                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                        String codigo = rs.getString("codigo");
                        String nombre = rs.getString("nombre");
                        String[] usuariosArray = (String[]) rs.getArray("usuaris").getArray();
                        List<String> usuariosList = Arrays.asList(usuariosArray);

                        Conexiondb conexion = new Conexiondb(
                                rs.getString("conSgbd"),
                                rs.getString("conIpDominio"),
                                rs.getInt("conPuerto")
                        );
                        boolean isActivo = rs.getBoolean("activo");

                        newServidor.add(new Servidor(codigo, nombre, usuariosList, conexion, isActivo));
                    }
                }
            } else {
                throw new AplicacioException("No se pudo insertar el servidor.");
            }
        } catch (SQLException e) {
            throw new AplicacioException("El Servidor " + codigoServer + " ya existe");
        }

        return newServidor;
    }

    public List<Servidor> eliminarUsuarioServidorDB(String usuario, String codigoServer) throws AplicacioException, SQLException {
        List<Servidor> deleteUsuario = new ArrayList<>();

        Connection con = getConnection();

        Servidor servidor = recuperarServidorPorCodigoDB(codigoServer);

        if (servidor == null) {
            throw new AplicacioException("El servidor " + codigoServer + " no se ha encontrado.");
        }

        if (!servidor.getUsuarios().contains(usuario)) {
            throw new AplicacioException("No se ha encontrado el usuario " + usuario + " en el servidor " + codigoServer + ".");
        }

        String deleteSql = """
                            UPDATE "ut5-practica".Servidors
                            SET usuaris = array_remove(usuaris, ?)
                            WHERE codigo = ?;
                            """;

        try (PreparedStatement psUpdate = con.prepareStatement(deleteSql)) {
            psUpdate.setString(1, usuario);
            psUpdate.setString(2, codigoServer);

            int updateRows = psUpdate.executeUpdate();
            if (updateRows > 0) {
                System.out.println("Usuario " + usuario + " eliminado correctamente del servidor " + codigoServer);
            }
        }

        String sql = """
                    SELECT s.codigo, s.nombre, s.usuaris,
                    (s.conexion).sgbd AS conSgbd,
                    (s.conexion).ip_dominio AS conIpDominio, 
                    (s.conexion).puerto AS conPuerto, 
                    s.activo FROM "ut5-practica".Servidors s
                    WHERE s.codigo = ?;
                      """;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codigoServer);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String codigo = rs.getString("codigo");
                String nombre = rs.getString("nombre");
                String[] usuariosArray = (String[]) rs.getArray("usuaris").getArray();
                List<String> usuarios = Arrays.asList(usuariosArray);

                Conexiondb conexion = new Conexiondb(
                        rs.getString("conSgbd"),
                        rs.getString("conIpDominio"),
                        rs.getInt("conPuerto")
                );
                boolean activo = rs.getBoolean("activo");

                deleteUsuario.add(new Servidor(codigo, nombre, usuarios, conexion, activo));
            }
        }
        return deleteUsuario;
    }
    
    public Servidor modificarConexionServidorDB(String sgdb, String ipDominio, int puerto, String codigoServer) throws AplicacioException, SQLException {

        Connection con = getConnection();

        Servidor servidor = recuperarServidorPorCodigoDB(codigoServer);

        if (servidor == null) {
            throw new AplicacioException("El servidor " + codigoServer + " no existe.");
        }

        String updateSql = """
                           UPDATE "ut5-practica".Servidors
                           SET conexion = ROW(?, ?, ?) 
                           WHERE codigo = ?;;
                            """;

        try (PreparedStatement psUpdate = con.prepareStatement(updateSql)) {
            psUpdate.setString(1, sgdb);
            psUpdate.setString(2, ipDominio);
            psUpdate.setInt(3, puerto);
            psUpdate.setString(4, codigoServer);

            int updateRows = psUpdate.executeUpdate();

            if (updateRows == 0) {
                throw new AplicacioException("No se pudo modificar la conexión del servidor " + codigoServer + ".");
            }

            System.out.println("Servidor " + codigoServer + " modificado correctamente.");
        }

        return recuperarServidorPorCodigoDB(codigoServer);
    }
}
