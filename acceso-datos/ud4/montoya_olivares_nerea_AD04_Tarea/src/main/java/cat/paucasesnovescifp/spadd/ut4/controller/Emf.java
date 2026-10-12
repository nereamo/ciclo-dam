
package cat.paucasesnovescifp.spadd.ut4.controller;

import cat.paucasesnovescifp.spaddd.ut4.auxiliars.AplicacioException;
import cat.paucasesnovescifp.spaddd.ut4.model.Peatge;
import cat.paucasesnovescifp.spaddd.ut4.model.Trip;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
* Classe que contiene la conexión con la BD y metodos para obtener los datos necesarios para realizar la tarea.
* @author Nerea Montoya
*/

public class Emf {
    String unidadPersistencia;
    private EntityManagerFactory emf;
    private EntityManager em;
    
    public Emf(String unidadPersistencia){
        
        this.unidadPersistencia = unidadPersistencia;
        
        //Conexión BD
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(unidadPersistencia); 
        EntityManager em = emf.createEntityManager();

    }
    
    public String getUnidadPersistencia(){
        return unidadPersistencia;
    }
    
    
    //Método que devuelve el ID de la tabla TOLL.
    public Peatge getIdentifiedToll(String idToll) throws AplicacioException {

        emf = Persistence.createEntityManagerFactory(unidadPersistencia);
        em = emf.createEntityManager();

        try {
            Peatge toll = em.find(Peatge.class, idToll);

            if (toll != null) {
                toll.getIdToll();
            }
            
            return toll;  
        } catch (Exception e) {
            throw new AplicacioException("Error al buscar el toll.");
        } finally {
            em.close();
            emf.close();
        }
    }
    
    
    //Método que devuelve todos los nombres de los Peatge ordenados alfabéticamente.
    public List<Peatge> getOrderNameToll(String nameToll) throws AplicacioException {

        emf = Persistence.createEntityManagerFactory(unidadPersistencia);
        em = emf.createEntityManager();

        try {

            TypedQuery<Peatge> query = em.createNamedQuery("Peatge.findAll", Peatge.class);
            List<Peatge> listPeatge = query.getResultList();

            if (listPeatge != null) {
                for (Peatge p : listPeatge) {
                    p.getName();
                }
            }
            return listPeatge;

        } catch (Exception e) {
            throw new AplicacioException("Error, no se encontraron resultados");
        } finally {
            em.close();
            emf.close();
        }
    }
    
    
    //Método que devuelve los viajes de ida o vuelta de un Peatge.
    public List<Trip> getTrips(Peatge peatge, boolean trip) throws AplicacioException {
        
        emf = Persistence.createEntityManagerFactory(unidadPersistencia);
        em = emf.createEntityManager();
        
        try {

            Peatge peatgeTrips = em.find(Peatge.class, peatge.getIdToll());

            if (trip) {
                List<Trip> tripsInput = new ArrayList(peatgeTrips.getIdTollInput());
                for (Trip ti : tripsInput) {
                    ti.getId();
                    ti.getIdTollInput();
                    ti.getInputTime();
                }
                return tripsInput;
            } else {
                List<Trip> tripsOutput = new ArrayList(peatgeTrips.getIdTollOutput());
                for (Trip to : tripsOutput) {
                    to.getId();
                    to.getIdTollInput();
                    to.getOutputTime();
                }
                return tripsOutput;
            }

        } catch (Exception e) {
            throw new AplicacioException("Error, no se encontraron resultados");
        } finally {
            em.close();
            emf.close();
        }
    }
    
    
    //Método que devuelve todos los viajes de un Peatge.
    public List<Trip> getAllTripsForId(Peatge peatge) throws AplicacioException {

        emf = Persistence.createEntityManagerFactory(unidadPersistencia);
        em = emf.createEntityManager();

        try {

            TypedQuery<Trip> query = em.createNamedQuery("Trip.findAll", Trip.class);
            query.setParameter("peatge", peatge);
            List<Trip> listTrip = query.getResultList();

            if (listTrip != null) {
                for (Trip t : listTrip) {
                    
                    t.getId();
                    
                    t.getInputTime();
                    t.getIdTollInput().getIdToll();
                    t.getIdTollInput().getName();
                    
                    t.getIdTollOutput().getIdToll();
                    t.getIdTollOutput().getName();
                    t.getOutputTime();
                }
            }
            return listTrip;

        } catch (Exception e) {
            throw new AplicacioException("Error, no se encontraron resultados");
        } finally {
            em.close();
            emf.close();
        }
    }
}
