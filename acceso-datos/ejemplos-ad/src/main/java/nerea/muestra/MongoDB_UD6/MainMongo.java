
package nerea.muestra.MongoDB_UD6;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nerea.muestra.Exception.AppException;
import org.bson.Document;

/**
* @author Nerea
*/

public class MainMongo {
    public static void main(String[] args) {
        try {
            
            MongoDB bd = new MongoDB();
            System.out.println("URL: " + bd.getUrl());
            System.out.println("BD: " + bd.getDatabase());
            System.out.println("Colección: " + bd.getCollection());

            //Nuevo Aspirante
            Aspirant a1 = new Aspirant("12345678A", "Nerea", "Montoya García", "07702");
            Document doc = new Document(a1.toMap());
            bd.insertarDocumento(doc);

            System.out.println("Insertado con Document correctamente.");
            Aspirant a3 = new Aspirant("12345678A", "Nerea", "Montoya García", "07702");

            //Nuevo aspirante
            Document doc2 = new Document()
                    .append("nif", a3.getNif())
                    .append("nom", a3.getNom())
                    .append("llinatges", a3.getLlinatges())
                    .append("codiPostal", a3.getCodiPostal());
            bd.insertarDocumento(doc2);
            System.out.println("Insertado con Document (append) correctamente.");

            // 4. Insertar usando método directo con Aspirant
            Aspirant a2 = new Aspirant("87654321Z", "Carlos", "Pérez López", "07001");
            bd.insertarAspirant(a2);
            System.out.println("Insertado con Aspirant correctamente.");
            
            // Recuperar todos los documentos y mostrarlos
            ArrayList<Document> documentos = bd.obtenerTodosDocumentos();
            System.out.println("Documentos:");
            for (Document doc1 : documentos) {
                System.out.println(doc1.toJson());
            }

            // Recuperar todos los aspirantes y mostrarlos
            ArrayList<Aspirant> aspirantes = bd.obtenerTodosAspirantes();
            System.out.println("\nAspirantes:");
            for (Aspirant aspirant : aspirantes) {
                System.out.println(aspirant);
            }

            // Recuperar aspirantes con paginación: obtener 2 aspirantes a partir del documento 1
            ArrayList<Aspirant> aspirantesPaginados = bd.obtenerAspirantesPaginados(1, 2);
            System.out.println("\nAspirantes Paginados:");
            for (Aspirant aspirant : aspirantesPaginados) {
                System.out.println(aspirant);
            }
            
            //Recuperar docs por codigo postal
            System.out.println("\nDocumentos por codigo postal:");
            List<Document> docu = bd.obtenerPorCodigoPostal("07002");
            for(Document d : docu){
               System.out.println(d.toJson()); 
            }
            
            System.out.println("\nDocumentos por codigo postal y nombre:");
            List<Document> dd = bd.obtenerPorCodigoPostalYNombre("07002", "Catalina");
            for(Document d : dd){
               System.out.println(d.toJson()); 
            }
            
            System.out.println("\nDocumentos por codigo postal y nombre:");
            List<Document> ddn = bd.obtenerPorNombres("Catalina", "Maria");
            for(Document d : ddn){
               System.out.println(d.toJson()); 
            }
            
            System.out.println("\nDocumentos por codigo postal y nombre:");
            List<Document> ddnn = bd.obtenerSinNombres("Catalina", "Maria");
            for(Document d : ddnn){
               System.out.println(d.toJson()); 
            }
            
            //Ordenar
            System.out.println("\nOrden:");
            List<Document> ddnnn = bd.getDocSortNomLLinatges2();
            for(Document d : ddnnn){
               System.out.println(d.toJson()); 
            }
            
            System.out.println("\nOrden orderBY:");
            List<Document> ddnnnn = bd.getDocOrderBy();
            for(Document d : ddnnnn){
               System.out.println(d.toJson()); 
            }
            
            //Proyecciones
            System.out.println("\nProyeccion1:");
            List<Document> dddnnnn = bd.getDocNomPRoyeccion();
            for(Document d : dddnnnn){
               System.out.println(d.toJson()); 
            }
            
            System.out.println("\nProyeccion2:");
            List<Document> ddddnnnn = bd.getDocNomExcludeId();
            for (Document d : ddddnnnn) {
                System.out.println(d.toJson());
            }

            System.out.println("\nProyeccion3:");
            List<String> campo = Arrays.asList("nom");
            List<Document> dddddnnnn = bd.getDocCampos(campo);
            for (Document d : dddddnnnn) {
                System.out.println(d.toJson());
            }

            //Agregaciones
            System.out.println("\nCantidad alumnos:");
            bd.getDocumentsAspirantQuantitat().forEach(document -> System.out.println(document));

            //
            System.out.println("\nMuestra centro:");
            bd.getCentres().forEach(document -> System.out.println(document));

            //Crear doc centro
            System.out.println("\nCrea centro:");
            bd.creaCentresCollection();//.forEach(document -> System.out.println(document));
            
        
            //Eliminar
            DeleteResult resultat = bd.deleteMany("Nerea");
            if (resultat.getDeletedCount() > 0) {
                System.out.println("El documento se ha eliminado.");
            } else {
                System.out.println("No se ha eliminado ningún documento.");
            }

            boolean delete = bd.deleteOneBoolean("Nerea");
            if(delete){
                System.out.println("Se ha borrado correctamente");
            }else{
                System.out.println("Error, no ha sido posible borrarlo");
            }

            //Modificar
            UpdateResult modifica = bd.modifica("nif", "14140460F", "nom", "Catalina");
            System.out.println("Modificas = " + modifica.getModifiedCount());

            UpdateResult modificaMolts = bd.modificaCP("07300", "07301");
            System.out.println("Modificats = " + modificaMolts.getModifiedCount());

           } catch (AppException ex) {
            Logger.getLogger(MainMongo.class.getName()).log(Level.SEVERE, null, ex);
        } 
            
    }

}
