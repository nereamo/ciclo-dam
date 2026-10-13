
package nerea.muestra.MongoDB_UD6;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Accumulators.first;
import static com.mongodb.client.model.Accumulators.sum;
import static com.mongodb.client.model.Aggregates.group;
import static com.mongodb.client.model.Aggregates.out;
import static com.mongodb.client.model.Aggregates.sort;
import static com.mongodb.client.model.Aggregates.unwind;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.in;
import static com.mongodb.client.model.Filters.nin;
import static com.mongodb.client.model.Indexes.ascending;
import static com.mongodb.client.model.Indexes.descending;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Sorts.orderBy;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.List;
import java.util.Map;
import nerea.muestra.Exception.AppException;
import org.bson.Document;

/**
* @author Nerea
*/

public class MongoDB {
    private String uri = "mongodb://nmontoya:e41746431m@daw.paucasesnovescifp.cat:27017/?authSource=admin";
    private String database = "nmontoyadb";
    private String collection = "aspirantes";
    private MongoDatabase mongoDatabase;
    private MongoClient mongoClient;
    private MongoCollection<Document> mcd;
    

    // Constructor con parámetros directos
    public MongoDB() {
        mongoClient = MongoClients.create(uri);
        mongoDatabase = mongoClient.getDatabase(database);
        mcd = mongoDatabase.getCollection(collection);
    }

    // Getters
    public String getUrl() {
        return uri;
    }

    public String getDatabase() {
        return database;
    }

    public String getCollection() {
        return collection;
    }
    
    //Crear document y aspirante
    public void insertarDocumento(Document doc) throws AppException {
        if (doc == null || doc.isEmpty()) {
            throw new AppException("Documento null o vacío. No se puede insertar.");
        }
        mcd.insertOne(doc);
    }

    public void insertarAspirant(Aspirant asp) throws AppException {
        if (asp == null) {
            throw new AppException("Aspirant null. No se puede insertar.");
        }
        Map<String, Object> mapa = asp.toMap();
        Document doc = new Document(mapa);
        insertarDocumento(doc);
    }
    
    //Recuperar
    public ArrayList<Document> obtenerTodosDocumentos() throws AppException {

        try {
            ArrayList<Document> documentos = new ArrayList<>();
            mcd.find().into(documentos);
            return documentos;
        } catch (Exception e) {
            throw new AppException("Error al recuperar los documentos: " + e.getMessage());
        }
    }
    
    public ArrayList<Aspirant> obtenerTodosAspirantes() throws AppException {
        try {
            ArrayList<Aspirant> aspirantes = new ArrayList<>();
            for (Document doc : obtenerTodosDocumentos()) {
                Aspirant aspirant = new Aspirant(
                        doc.getString("nif"),
                        doc.getString("nom"),
                        doc.getString("llinatges"),
                        doc.getString("codiPostal")
                );
                aspirantes.add(aspirant);
            }
            return aspirantes;
        } catch (Exception e) {
            throw new AppException("Error al convertir los documentos en Aspirants: " + e.getMessage());
        }
    }

    // Método con paginación: obtener documentos desde una posición inicial y una cantidad específica
    public ArrayList<Aspirant> obtenerAspirantesPaginados(int inicio, int cantidad) throws AppException {
        try {
            ArrayList<Aspirant> aspirantes = new ArrayList<>();
            List<Document> documentos = mcd.find()
                    .skip(inicio)  // Saltamos los primeros "inicio" documentos
                    .limit(cantidad)  // Limita la cantidad de documentos recuperados
                    .into(new ArrayList<>());
            for (Document doc : documentos) {
                Aspirant aspirant = new Aspirant(
                        doc.getString("nif"),
                        doc.getString("nom"),
                        doc.getString("llinatges"),
                        doc.getString("codiPostal")
                );
                aspirantes.add(aspirant);
            }
            return aspirantes;
        } catch (Exception e) {
            throw new AppException("Error al recuperar los documentos paginados: " + e.getMessage());
        }
    }
    
    //Recupera docs por codigo postal
    public ArrayList<Document> obtenerPorCodigoPostal(String codigoPostal) throws AppException {
        try {
            ArrayList<Document> documentos = new ArrayList<>();
            mcd.find(new Document("codiPostal", codigoPostal)).into(documentos);
            return documentos;
        } catch (Exception e) {
            throw new AppException("Error al recuperar documentos por código postal: " + e.getMessage());
        }
    }

    //Recupera docs por codigo postal y nombre
    public ArrayList<Document> obtenerPorCodigoPostalYNombre(String codigoPostal, String nombre) throws AppException {
        try {
            ArrayList<Document> documentos = new ArrayList<>();
            mcd.find(new Document("codiPostal", codigoPostal).append("nom", nombre)).into(documentos);
            return documentos;
        } catch (Exception e) {
            throw new AppException("Error al recuperar documentos por código postal y nombre: " + e.getMessage());
        }
    }

    //Recupera docs que si contiene uno de los dos nombres
    public ArrayList<Document> obtenerPorNombres(String nombre1, String nombre2) throws AppException {
        try {
            ArrayList<Document> documentos = new ArrayList<>();
            mcd.find(in("nom", nombre1, nombre2)).into(documentos);
            return documentos;
        } catch (Exception e) {
            throw new AppException("Error al recuperar documentos por nombres: " + e.getMessage());
        }
    }

    //Recupera docs que no contiene ninguno d elos dos
    public ArrayList<Document> obtenerSinNombres(String nombre1, String nombre2) throws AppException {
        try {
            ArrayList<Document> documentos = new ArrayList<>();
            mcd.find(nin("nom", nombre1, nombre2)).into(documentos);
            return documentos;
        } catch (Exception e) {
            throw new AppException("Error al recuperar documentos sin nombres: " + e.getMessage());
        }
    }
    
    //Orden ascendente por nombre
    public ArrayList<Document> getDocSort() throws AppException {
        try {
            ArrayList<Document> documentos = new ArrayList<>();
            mcd.find().sort(ascending("nom")).into(documentos);
            return documentos;
        } catch (Exception e) {
            throw new AppException("Error al recuperar documentos sin nombres: " + e.getMessage());
        }
    }
    
    //Orden ascendente por apellido y nombre
    public ArrayList<Document> getDocSortNomLLinatges() throws AppException {
        try {
            ArrayList<Document> documentos = new ArrayList<>();
            mcd.find().sort(ascending("llinatges", "nom")).into(documentos);
            return documentos;
        } catch (Exception e) {
            throw new AppException("Error al recuperar documentos sin nombres: " + e.getMessage());
        }
    }
    
    //Orden ascendente por apellido y descendente por nombre
    public ArrayList<Document> getDocSortNomLLinatges2() throws AppException {
        try {
            ArrayList<Document> documentos = new ArrayList<>();
            mcd.find().sort(ascending("llinatges")).sort(descending("nom")).into(documentos);
            return documentos;
        } catch (Exception e) {
            throw new AppException("Error al recuperar documentos sin nombres: " + e.getMessage());
        }
    }
    
    //Orden, primero poir apellido ascendente y despues por nombre descendente
    public ArrayList<Document> getDocOrderBy() throws AppException {
        try {
            ArrayList<Document> documentos = new ArrayList<>();
            mcd.find().sort(orderBy(ascending("llinatges"), descending("nom"))).into(documentos);
            return documentos;
        } catch (Exception e) {
            throw new AppException("Error al recuperar documentos sin nombres: " + e.getMessage());
        }
    }
    
    //Orden segun parametro ("nom"...) y orden (ASC = true o DESC = false)
    public ArrayList<Document> getDocOrderBy(String param, boolean order) throws AppException {
        try {
            ArrayList<Document> documentos = new ArrayList<>();
            mcd.find().sort(order ? ascending(param) : descending(param)).into(documentos);
            return documentos;
        } catch (Exception e) {
            throw new AppException("Error al recuperar documentos sin nombres: " + e.getMessage());
        }
    }

    //Proyeccion, recupera solo nombre y apellidos y el id incluido
    public ArrayList<Document> getDocNomPRoyeccion() throws AppException {
        try {
            ArrayList<Document> documentos = new ArrayList<>();
            mcd.find().projection(include("nom", "llinatges")).sort(ascending("llinatges", "nom")).into(documentos);
            return documentos;
        } catch (Exception e) {
            throw new AppException("Error al recuperar documentos sin nombres: " + e.getMessage());
        }
    }
    
    //Proyeccion, excluye explicitamente el id
    public ArrayList<Document> getDocNomExcludeId() throws AppException {
        try {
            ArrayList<Document> documentos = new ArrayList<>();
            mcd.find().projection(fields(include("nom", "llinatges"), excludeId())).sort(ascending("llinatges", "nom")).into(documentos);
            return documentos;
        } catch (Exception e) {
            throw new AppException("Error al recuperar documentos sin nombres: " + e.getMessage());
        }
    }
    
    //Deveulve solo los campos pasados por parametro
    public ArrayList<Document> getDocCampos(List<String> camps) throws AppException {
        try {
            ArrayList<Document> documentos = new ArrayList<>();
            mcd.find().projection(include(camps)).sort(ascending("llinatges", "nom")).into(documentos);
            return documentos;
        } catch (Exception e) {
            throw new AppException("Error al recuperar documentos sin nombres: " + e.getMessage());
        }
    }
    
    //Agregaciones, descompone array en docs, agrupa rersultados por nif, ordena por apellido y cuanta cuantas preferencias hay
    public ArrayList<Document> getDocumentsAspirantQuantitat() {
        ArrayList<Document> documentos = new ArrayList<>();

        mcd.aggregate(List.of(
                unwind("$preferencies"),
                group("$nif", first("nom", "$nom"), first("llinatges", "$llinatges"), sum("quantitat", 1)),
                sort(ascending("llinatges"))
        )).into(documentos);

        return documentos;
    }

     //Agregaciones, descompone array en docs, agrupa rersultados por centro, no ordena
    public ArrayList<Document> getCentres() {
        ArrayList<Document> documentos = new ArrayList<>();
        mcd.aggregate(List.of(
                unwind("$preferencies"), //descompone en docs
                group("$preferencies.centre.idcentre", //Agrupa por campo
                        first("nom", "$preferencies.centre.nomCentre"), //Guarda el primer centro
                        first("localitat", "$preferencies.centre.localitat"))
        )).into(documentos);

        return documentos;
    }

    //Igual que getCentres pero para crear, guarda resultado en nueva coleccion centres33, exporta (out) los docs agrupados
    public void creaCentresCollection() {

        mcd.aggregate(asList(
                unwind("$preferencies"),
                group("$preferencies.centre.idcentre",
                        first("nom", "$preferencies.centre.nomCentre"),
                        first("localitat", "$preferencies.centre.localitat")),
                out("centres33")
        )).toCollection();

    }
    
    //Modificaciones
    public UpdateResult modifica(String campFiltre, String valorFiltre, String campModificar, String nouValor) {
       
        return mcd.updateMany(eq(campFiltre, valorFiltre), Updates.set(campModificar, nouValor));  
    }

    public UpdateResult modificaCP(String antic, String nou) {
        return mcd.updateOne(eq("codiPostal", antic),Updates.set( "codiPostal", nou));
    }

    //Delete
    public DeleteResult deleteMany(String nom) {
            return mcd.deleteMany(eq("nom", nom));    
    }
    
    public DeleteResult deleteMany(String nom, String llinatges) {
        return mcd.deleteMany(and(eq("nom", nom), eq("llinatges", llinatges)));
    }
    
    public boolean deleteOneBoolean(String nom) {
        return mcd.deleteOne(eq("nom", nom)).getDeletedCount() > 0;
    }
    
    public DeleteResult deleteOne(String nom) {
        return mcd.deleteOne(eq("nom", nom));
    }
}

