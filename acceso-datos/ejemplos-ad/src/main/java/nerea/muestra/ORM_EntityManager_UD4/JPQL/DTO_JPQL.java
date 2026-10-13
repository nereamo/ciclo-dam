package nerea.muestra.ORM_EntityManager_UD4.JPQL;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import nerea.muestra.Exception.AppException;
import nerea.muestra.ORM_EntityManager_UD4.JPA.Illes;
import nerea.muestra.ORM_EntityManager_UD4.JPA.Localitat;

/**
* @author Nerea
*/

public class DTO_JPQL {
    
    private EntityManagerFactory emf;
    
    public DTO_JPQL() throws AppException{
        
        emf = Persistence.createEntityManagerFactory("interins");
        
    }
    //***Método para obtener la conexión***
    public EntityManager getEntityManager() throws AppException {
       return emf.createEntityManager();
    }
    
    public void cerrarFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
    
    public List<Illes> getIllaList() throws AppException {
    try (EntityManager em = getEntityManager()) {
        TypedQuery<Illes> query = em.createQuery("SELECT i FROM Illes i", Illes.class);
        return query.getResultList();
    } catch (PersistenceException e) {
        throw new AppException("Error al obtener la lista de islas: " + e.getMessage());
    }
}

    //Método para obtener la isla por ID
    public Illes getIlla(String idIlla) throws AppException {

        try (
            EntityManager em = getEntityManager()){
            TypedQuery<Illes> query = em.createQuery("SELECT i FROM Illes i WHERE i.idIlla = :idIlla", Illes.class);
            query.setParameter("idIlla", idIlla);
            return query.getSingleResult();

        } catch (PersistenceException e) {
            throw new AppException("Error al obtener los datos de la isla" + e.getMessage());
        }
    }
    
    //Método para obtener las localidades de una isla
    public List<Localitat> getLocalitatIlla(String idIlla) throws AppException{
        
        List<Localitat> localitatList = new ArrayList<>();
        
        try(
            EntityManager em = getEntityManager()){
            TypedQuery<Localitat> query = em.createQuery("SELECT l FROM Localitat l WHERE l.idIlla.idIlla = :idIlla", Localitat.class);
            query.setParameter("idIlla", idIlla);
            localitatList = query.getResultList();

        }catch (PersistenceException e) {
            throw new AppException("Error al obtener las localidades de la isla" + e.getMessage());
        } 
        return localitatList;    
    }
}
