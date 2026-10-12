package cat.paucasesnovescifp.spaddd.jdbc.baseDades;

import cat.paucasesnovescifp.spaddd.jdbc.dades.Editor;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Nerea
 */
public class BaseDades {

    private String url;
    private Set<Editor> buffer; //Guardará les noves dades sense duplicats
    private Connection con;

    public BaseDades(String url) {

        this.url = url;
        this.buffer = new HashSet<>();
    }

    public BaseDades() {

        this("jdbc:mysql://daw.paucasesnovescifp.cat:3306/biblioteca?user=usuari&password=seCret_24");  //Url de conexión con la BD

    }
    
    //-------------Métode per fer la conexió amb la BD------------------

    public Connection conexioBaseDades() throws SQLException {
        
        con = DriverManager.getConnection(url); 
        return con;
    }
    
    //-------------Métode per guardar els nous editors al 'buffer' sense duplicats------------------

    public Set<Editor> guardarEditors() throws SQLException {
        return buffer;
    }
    
    //-------------Métode per afegir editors al 'buffer'------------------

    public void afegirEditor(Editor editor) throws SQLException {
        
        boolean afegir = buffer.add(editor);

        if (afegir) {   
            System.out.println("\nL'editor " + editor + " s'ha afegit correctament.");
        } else {
            System.out.println("\nL'editor " + editor + " ja existeix.");
        }
    }
    
    //-------------Métode per eliminar editors del 'buffer'------------------

    public void eliminarEditor(Editor editor) {
        boolean eliminar = buffer.remove(editor);

        if (eliminar) { 
            System.out.println("\nL'editor: " + editor.getNom() + " s'ha eliminat correctament.");
        } else {
            System.out.println("\nL'editor " + editor.getNom() + " no s'ha pogut eliminar.");
        }
    }
    
    //-------------Métode per buidar el 'buffer'------------------

    public void buidarBuffer() {

        buffer.clear();
        System.out.println("\nEL Buffer s'ha buidat.");
    }
    
    //-------------Métode per obtenir el següent Id------------------

    private int obtenirSeguentId(Connection conn) throws SQLException {

        int nextId = -1;

        try (CallableStatement cs = conn.prepareCall("{ CALL GET_NEXT_ID_EDIT(?) }")) { //Cridar al procediment de la BD qie asigna l'Id

            cs.registerOutParameter(1, java.sql.Types.INTEGER); //Parámetre del procediment de la BD.
            cs.execute();
            nextId = cs.getInt(1);

            System.out.println("Següent ID asignat: " + nextId);

        } catch (SQLException e) {

            System.err.println("Error al obtenir el següent ID: " + e.getMessage());
            throw e;
        }
        return nextId;
    }
    
    //-------------Métode per insertar editors a la BD------------------

    public int inserirEditorsBD() throws SQLException {

        int filesInserides = 0; 

        try (Connection con = conexioBaseDades()) { //Conexió amb la BD.

            con.setAutoCommit(false); //Desactivar la confirmació dels camvis fins que tot sigui correcte.

            String sql = "INSERT INTO EDITORS (ID_EDIT, NOM_EDIT, EMAIL_EDIT) VALUES (?, ?, ?)"; //Query per insertar els editors

            try (PreparedStatement ps = con.prepareStatement(sql)) {    //Executar senténcia sql, les dades les red de l'usuari

                for (Editor editor : buffer) {

                    int nextId = obtenirSeguentId(con); //Cridar al método obtenirSeguentId

                    ps.setInt(1, nextId);   //Dades que s'insertarán
                    ps.setString(2, editor.getNom());
                    ps.setString(3, editor.getEmail());

                    int filesInsertades = ps.executeUpdate(); //Retorna el número de files que s'han insertat

                    if (filesInsertades > 0) {  //Si les files insertades es major a 0, mostrará la cuantitat de files

                        System.out.println("Editor insertat: " + editor); //Dades de l'editor insertat
                        filesInserides += filesInsertades;

                    } else {

                        System.out.println("Error al insertar el editor: " + editor);
                        con.rollback(); //Si no es pot insertar la final, tornará enrere
                        return 0;
                    }
                }

                con.commit(); //Si la transacció s'ha fet correctament es confirmen els canvis.

                buidarBuffer(); //Cridam al métode buidarBuffer si tot es correcte.

            } catch (SQLException e) {
                con.rollback(); //Si la transacció no s'ha fet correctament, torna enrere.
                throw e;
            }
        }
        return filesInserides;
    }
    
    //-------------Métode per eliminar editors de la BD------------------

    public int eliminarEditorsBD(int id) throws SQLException {

        String sql = "DELETE FROM EDITORS WHERE ID_EDIT = ?";   

        try (Connection con = conexioBaseDades()) {

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, id);   //Referéncia al parametre de Id

                int filesEliminades = ps.executeUpdate();

                return filesEliminades; //Retorna la cuantitat de files eliminades.

            } catch (SQLException e) {

                throw e;
            }
        }

    }
}
