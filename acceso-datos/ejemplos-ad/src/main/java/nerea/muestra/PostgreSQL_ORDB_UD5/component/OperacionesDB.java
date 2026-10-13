package nerea.muestra.PostgreSQL_ORDB_UD5.component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import nerea.muestra.Exception.AppException;

/**
 * //Class que opera sobre la base de datos.
 *
 * @author Nerea Montoya
 */

public class OperacionesDB {

    private String url = "jdbc:postgresql://daw.paucasesnovescifp.cat:5432/nmontoyadb?currentSchema=ut5-practica";
    private String usuario = "nmontoya";
    private String password = "e41746431m";

    public Connection getConnection() throws SQLException, AppException {
        return DriverManager.getConnection(url, usuario, password);
    }

    public List<Servidor> recuperarServidoresDB() throws AppException, SQLException {
        List<Servidor> servidores = new ArrayList<>();

        String sql = """
                    SELECT s.codigo, s.nombre, s.usuaris, 
                    (s.conexion).sgbd AS conSgbd, 
                    (s.conexion).ip_dominio AS conIpDominio, 
                    (s.conexion).puerto AS conPuerto, 
                    s.activo 
                    FROM "ut5-practica".Servidors s
                    ORDER BY codigo ASC;
                    """;

        try (Connection con = getConnection(); 
                PreparedStatement ps = con.prepareStatement(sql); 
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
            throw new AppException("Error al recuperar servidores: " + e.getMessage());
        }

        return servidores;
    }

    public Servidor recuperarServidorPorCodigoDB(String codigoServer) throws AppException, SQLException {

        Servidor servidor = null;

        String sql = """
                SELECT s.codigo, s.nombre, s.usuaris,
                (s.conexion).sgbd AS conSgbd,
                (s.conexion).ip_dominio AS conIpDominio, 
                (s.conexion).puerto AS conPuerto, 
                s.activo FROM "ut5-practica".Servidors s
                WHERE s.codigo = ?; """;

        try (Connection con = getConnection(); 
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codigoServer);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    servidor = new Servidor(
                            rs.getString("codigo"),
                            rs.getString("nombre"),
                            Arrays.asList((String[]) rs.getArray("usuaris").getArray()),
                            new Conexiondb(
                                    rs.getString("conSgbd"),
                                    rs.getString("conIpDominio"),
                                    rs.getInt("conPuerto")
                            ),
                            rs.getBoolean("activo")
                    );
                }
            }
        }

        if (servidor == null) {
            throw new AppException("No se ha encontrado el servidor " + codigoServer + ".");
        }

        return servidor;
    }

    public int insertarServidor(Servidor servidor) throws AppException, SQLException {

        int rowsAff = 0;
        String insertSql = """
                        INSERT INTO "ut5-practica".Servidors(
                        codigo, nombre, usuaris, conexion, activo)
                        VALUES(?,?,?, ROW(?,?,?), ?);""";

        try (Connection con = getConnection();
                PreparedStatement psInsert = con.prepareStatement(insertSql)) {
            psInsert.setString(1, servidor.getCodigo());
            psInsert.setString(2, servidor.getNombre());
            psInsert.setArray(3, con.createArrayOf("text", servidor.getUsuarios().toArray(new String[0])));
            psInsert.setString(4, servidor.getConexiondb().getSgbd());
            psInsert.setString(5, servidor.getConexiondb().getIpDominio());
            psInsert.setInt(6, servidor.getConexiondb().getPuerto());
            psInsert.setBoolean(7, servidor.isActivo());

            rowsAff = psInsert.executeUpdate();

        } catch (SQLException e) {
            throw new AppException("No se ha podido insertar el nuevo servidor " + e.getMessage());
        }

        return rowsAff;
    }

    public int eliminarUsuarioServidorDB(String usuario, String codigoServer) throws AppException, SQLException {

        int rowsAff = 0;
        String deleteSql = """
                            UPDATE "ut5-practica".Servidors
                            SET usuaris = array_remove(usuaris, ?)
                            WHERE codigo = ?; """;

        try (Connection con = getConnection();
                PreparedStatement psUpdate = con.prepareStatement(deleteSql)) {
            psUpdate.setString(1, usuario);
            psUpdate.setString(2, codigoServer);

            rowsAff = psUpdate.executeUpdate();

        } catch (SQLException e) {
            throw new AppException("No se ha podido eliminar el usuario " + e.getMessage());
        }
        return rowsAff;
    }

    public Servidor modificarConexionServidorDB(String sgdb, String ipDominio, int puerto, String codigoServer) throws AppException, SQLException {

        String updateSql = """
                           UPDATE "ut5-practica".Servidors
                           SET conexion = ROW(?, ?, ?) 
                           WHERE codigo = ?;""";

        try (Connection con = getConnection();
                PreparedStatement psUpdate = con.prepareStatement(updateSql)) {
            psUpdate.setString(1, sgdb);
            psUpdate.setString(2, ipDominio);
            psUpdate.setInt(3, puerto);
            psUpdate.setString(4, codigoServer);

            int updateRows = psUpdate.executeUpdate();

            if (updateRows == 0) {
                throw new AppException("No se pudo modificar la conexión del servidor " + codigoServer + ".");
            }
        }
        return recuperarServidorPorCodigoDB(codigoServer);
    }
}
