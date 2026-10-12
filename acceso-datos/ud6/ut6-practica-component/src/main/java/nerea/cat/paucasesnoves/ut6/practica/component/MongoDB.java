package nerea.cat.paucasesnoves.ut6.practica.component;

import com.mongodb.client.*;
import com.mongodb.client.model.Aggregates;
import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Aggregates.unwind;
import static com.mongodb.client.model.Filters.*;
import java.util.*;
import static java.util.Arrays.asList;
import nerea.cat.paucasesnoves.ut6.practica.auxiliares.AplicacionException;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 * Clase con conexión a la base de datos y las operaciones para obtener los datos solicitados.
 *
 * @author Nerea Montoya
 */

public class MongoDB {
    
    private static MongoDatabase mongoDatabase;
    private static MongoClient mongoClient;
    
    private static String uri = "mongodb://nmontoya:e41746431m@daw.paucasesnovescifp.cat:27017/?authSource=admin";
    private static String database = "nmontoyadb";
    private static String collectiondb = "sakila";

    public MongoDB(){
        
        getConnection (uri, database);
    }
    
    //Método para la connexión a la base de datos
    public static MongoDatabase getConnection (String uri, String database ){
        
        mongoClient = MongoClients.create(uri);
        mongoDatabase = mongoClient.getDatabase(database);
        
        return mongoDatabase;      
    }
    
    public static List<Categoria> getCategoria() throws AplicacionException {
        
        List<Categoria> categoryList = new ArrayList<>();
        List<Document> docList = new ArrayList<>();
        
        try {
            getConnection(uri, database);   //Método de conexión a la base de datos

            MongoCollection<Document> mngCollection = mongoDatabase.getCollection(collectiondb); //Acceder a la coleccion 'sakila'

            Bson filtra = match(exists("category")); //Filtra solo documentos que tienen 'category'
            Bson desfa = unwind("$category"); //Descompone array en documentos individuales
            Bson agrupa = Aggregates.group(new Document("categoryId", "$category.categoryId").append("value", "$category.value")); //Agrupa evitando duplicados

            mngCollection.aggregate(asList(filtra, desfa, agrupa)).into(docList); //Guarda resultados en 'docList'

            for (Document doc : docList) { //Recorremos el documento 

                Document categoriaDoc = (Document) doc.get("_id"); //El documento agrupado dentro de '_id'

                if (categoriaDoc != null) {

                    Integer categoryId = categoriaDoc.getInteger("categoryId");
                    String value = categoriaDoc.getString("value");

                    categoryList.add(new Categoria(categoryId, value)); //Nueva instancia de Categoria. 
                }
            }

            mongoClient.close();//Cierre de conexión
            return new ArrayList<>(categoryList);

        } catch (Exception e) {

            throw new AplicacionException("Error al obtener categorías: " + e.getMessage());
        }
    }
    
    public static List<Actor> getActores() throws AplicacionException {
        
        List<Actor> actorList = new ArrayList<>();
        List<Document> docList = new ArrayList<>();
        

        try {
            getConnection(uri, database); //Método de conexión a la base de datos
            
            MongoCollection<Document> mngCollection = mongoDatabase.getCollection(collectiondb); //Acceder a la coleccion 'sakila'
            
            Bson filtra = match(exists("actor")); //Filtra solo documentos que tienen 'actor'
            Bson desfa = unwind("$actor"); //Descompone array en documentos individuales
            Bson agrupaNom = Aggregates.group(new Document("actorId", "$actor.actorId").append("firstName", "$actor.firstName").append("lastName", "$actor.lastName")); //Agrupa evitando duplicados
            
            mngCollection.aggregate(asList(filtra, desfa, agrupaNom)).into(docList); //Guarda resultados en 'docList'
            
            for (Document doc : docList) { //Recorremos la colección
                
                Document actorDoc = (Document) doc.get("_id"); //Obtenemos el campo 'actor' del documento
                
                if(actorDoc != null){
                    
                    Integer actorId = actorDoc.getInteger("actorId");
                    String firstName = actorDoc.getString("firstName");
                    String lastName = actorDoc.getString("lastName");

                actorList.add(new Actor(actorId, firstName, lastName)); //Nueva instancia de Actor.
  
                }
            }

            mongoClient.close();//Cierre de conexión
            return new ArrayList<>(actorList); 
            
        } catch (Exception e) {
            throw new AplicacionException("Error al recuperar actores: " + e.getMessage());
        }
    }
    
    
    public static List<Film> getFilmCategoriaActor(Object categoryId, Object actorId) throws AplicacionException {
        
        List<Film> filmList = new ArrayList<>();

        try {

            getConnection(uri, database);   //Método de conexión a la base de datos

            MongoCollection<Document> mngCollection = mongoDatabase.getCollection(collectiondb); // Acceder a la colección 'sakila'

            Bson filter = new Document(); //Documento vacío

            if (categoryId != null) {
                filter = and(filter, eq("category.categoryId", categoryId));
            }

            if (actorId != null) {
                filter = and(filter, eq("actor.actorId", actorId));
            }

            FindIterable<Document> results = mngCollection.find(filter);  //Obtenemos los campos 'categoryId' y 'actorId' del documento si no son null

            for (Document doc : results) { //Recorremos el documento

                filmList.add(new Film(
                        doc.getInteger("_id"),
                        doc.getString("title"),
                        doc.getString("description"),
                        doc.getString("releaseYear"),
                        doc.getInteger("length")));
            }

            mongoClient.close();//Cierre de conexión
            return filmList;

        } catch (Exception e) {
            throw new AplicacionException("Error al recuperar las peliculas: " + e.getMessage());
        }
    }
}