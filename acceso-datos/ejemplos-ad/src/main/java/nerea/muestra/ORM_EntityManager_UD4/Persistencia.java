
package nerea.muestra.ORM_EntityManager_UD4;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import nerea.muestra.ORM_EntityManager_UD4.JPA.Aspirant;
import nerea.muestra.ORM_EntityManager_UD4.JPA.Centre;
import nerea.muestra.ORM_EntityManager_UD4.JPA.Especialitat;
import nerea.muestra.ORM_EntityManager_UD4.JPA.EspecialitatPK;
import nerea.muestra.ORM_EntityManager_UD4.JPA.Illes;
import nerea.muestra.ORM_EntityManager_UD4.JPA.Localitat;
import nerea.muestra.ORM_EntityManager_UD4.JPA.Preferencia;
import nerea.muestra.Exception.AppException;

/**
 * @author Nerea
 */

public class Persistencia {

    private String unidadPersistencia = "interins";
    private EntityManagerFactory emf;

    public Persistencia() throws AppException {

        setUnidadPersistencia(unidadPersistencia);
        emf = Persistence.createEntityManagerFactory(unidadPersistencia);

    }

    public String getUnidadPersistencia(String unidadPersistencia) {
        return unidadPersistencia;
    }

    public void setUnidadPersistencia(String unidadPersistencia) throws AppException {
        if (unidadPersistencia == null || unidadPersistencia.isBlank()) {
            throw new AppException("La unidad de persistencia no pued eestar vacía ni ser null");
        }
        this.unidadPersistencia = unidadPersistencia;
    }

    // Método conexión BD
    public EntityManager getEntityManager() throws AppException {
            return emf.createEntityManager(); 
    }

    // Cerrar EntityManagerFactory al finalizar la aplicación
    public void cerrarFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    // Mëtodo parea obtener aspirante
    public Aspirant getAspirant(String nif) throws AppException {

        try (EntityManager em = getEntityManager()) {
            Aspirant aspirant = em.find(Aspirant.class, nif);
            return aspirant;

        } catch (PersistenceException e) {
            throw new AppException("Error al obtener aspirante: " + e.getMessage());
        }
    }
    
    // Mëtodo parea obtener aspirante
    public Aspirant getAspirantSingl(String nif) throws AppException {

        try (EntityManager em = getEntityManager()){
            
            TypedQuery<Aspirant> query = em.createQuery("SELECT a FROM Aspirant a WHERE a.nif = :nif", Aspirant.class);
            query.setParameter("nif", nif);
            return query.getSingleResult();

        } catch (PersistenceException e) {
            throw new AppException("Error al obtener aspirante: " + e.getMessage());
        }
    }
    
    public Aspirant getAspirantName(String nif) throws AppException {

    try (EntityManager em = getEntityManager()) {
        TypedQuery<Aspirant> query = em.createNamedQuery("Aspirant.findByNif", Aspirant.class);
        query.setParameter("nif", nif);
        return query.getSingleResult();
        
    } catch (PersistenceException e) {
        throw new AppException("Error al obtener el aspirante: " + e.getMessage());
    }
}

    // Método para modificar aspirante opcion 1
    public Aspirant modificarAspirante(String nif, String apellidos) throws AppException {
        try (
            EntityManager em = getEntityManager()){
            em.getTransaction().begin();
            
            Aspirant aspirant = em.find(Aspirant.class, nif);

            if (aspirant == null) {
            throw new AppException("No s'ha trobat cap aspirant amb el NIF proporcionat.");
        }

        aspirant.setLlinatges(apellidos);
        em.merge(aspirant);
        em.getTransaction().commit();

        return aspirant;

        } catch (PersistenceException e) {
            throw new AppException("Error al modificar els llinatges de l'aspirant: " + e.getMessage());
        }
    }

    // Método para modificar aspirante opcion 2
    public Aspirant modificarAspirante2(String nif) throws AppException {

        try (
            EntityManager em = getEntityManager()){
            em.getTransaction().begin();

            TypedQuery<Aspirant> query = em.createQuery("SELECT a FROM Aspirant a WHERE a.nif = :nif", Aspirant.class);

            query.setParameter("nif", nif);
            Aspirant aspirant = query.getSingleResult();

            if (aspirant == null) {
                throw new AppException("No s'ha trobat cap aspirant amb el NIF proporcionat.");
            }

            em.merge(aspirant);
            em.getTransaction().commit();

            return aspirant;

        } catch (PersistenceException e) {
            throw new AppException("Error al modificar els llinatges de l'aspirant: " + e.getMessage());
        }
    }

    // Método para actualizar el aspirante
    public void actualitzaAspirant(Aspirant aspirant) throws AppException {

        try (
            EntityManager em = getEntityManager()){
            em.getTransaction().begin();
            em.merge(aspirant);
            em.getTransaction().commit();

        } catch (PersistenceException e) {
            throw new AppException("Error al actualitzar l'aspirant: " + e.getMessage());
        }
    }

    // Método para obtener la localidad
    public Localitat getLocalitat(String idLocalitat) throws AppException {

        try (
            EntityManager em = getEntityManager()){
            TypedQuery<Localitat> query = em.createQuery("SELECT l FROM Localitat l WHERE l.idLocalitat = :idLocalitat",
                    Localitat.class);

            query.setParameter("idLocalitat", idLocalitat);
            Localitat localitat = query.getSingleResult();

            return localitat;

        } catch (PersistenceException e) {
            throw new AppException("Error al obtener la localidad: " + e.getMessage());
        } 
    }

    // Método para insertar objeto
    public void insertObject(String id, String nom) throws AppException {
        EntityManager em = getEntityManager();
        try {

            Illes illa = new Illes();
            illa.setIdIlla(id);
            illa.setNomIlla(nom);
            em.getTransaction().begin();
            em.persist(illa);
            em.getTransaction().commit();

        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            throw new AppException("Error al insertar el objeto: " + e.getMessage());
        }
    }

    // Método para actualizar objeto
    public void updateObject(String id, String nom) throws AppException {
        EntityManager em = getEntityManager();
        try {
            Illes illa = em.find(Illes.class, id);
            illa.setNomIlla(nom);
            em.getTransaction().begin();
            em.merge(illa);
            em.getTransaction().commit();

        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            throw new AppException("Error al actualitzar l'aspirant: " + e.getMessage());
        }
    }

    // Método para eliminar objeto
    public void deleteObject(String id) throws AppException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Illes illa = em.find(Illes.class, id);
            if(illa != null){
                em.remove(illa);
            }
            em.getTransaction().commit();

        } catch (PersistenceException e) {
            throw new AppException("Error al actualitzar l'aspirant: " + e.getMessage());
        }
    }

    // Método para crear aspirante con parametros en el el método
    public Aspirant creaAspiranteParametros1(String nif, String nom, String llinatges, String adreca, String codiPostal,
        Localitat localitat) throws AppException {

        try (
            EntityManager em = getEntityManager()){
            em.getTransaction().begin();

            Aspirant aspirantExistente = em.find(Aspirant.class, nif);
            if (aspirantExistente != null) {
                // Si el aspirante ya existe, lanza una excepción
                throw new AppException("El aspirante con el NIF " + nif + " ya existe.");
            }

            // Verifica si la localitat ya está persistida antes de persistir el aspirant
            if (localitat.getIdLocalitat() == null) {
                em.persist(localitat); // Persistir la localitat si no está guardada
            }

            Aspirant aspirant = new Aspirant();
            aspirant.setNif(nif);
            aspirant.setNom(nom);
            aspirant.setLlinatges(llinatges);
            aspirant.setAdreca(adreca);
            aspirant.setCodiPostal(codiPostal);
            aspirant.setLocalitat(localitat);

            // Persistir el objeto Aspirant en la base de datos
            em.persist(aspirant);
            em.getTransaction().commit();
            return aspirant;

        } catch (PersistenceException e) {
            throw new AppException("Error al crear el aspirante: " + e.getMessage());
        }
    }

    // Método para crear preferencia con parametros en el el método
    public Preferencia creaPreferencia(String nif, int ordre, String idCos, String idEspecialitat, String idCentre) throws AppException {

        try (
            EntityManager em = getEntityManager()){
            em.getTransaction().begin();

            TypedQuery<Preferencia> query = em.createQuery(
                    "SELECT p FROM Preferencia p " +
                            "WHERE p.nif = :nif " +
                            "AND p.ordre = :ordre " +
                            "AND p.especialitat.idCos = :idCos " +
                            "AND p.especialitat.idEspecialitat = :idEspecialitat " +
                            "AND p.centre.idCentre = :idCentre",
                    Preferencia.class);
            query.setParameter("nif", nif);
            query.setParameter("ordre", ordre);
            query.setParameter("idCos", idCos);
            query.setParameter("idEspecialitat", idEspecialitat);
            query.setParameter("idCentre", idCentre);

            Preferencia existingPreferencia = query.getResultList().stream().findFirst().orElse(null);

            if (existingPreferencia != null) {
                // Si la preferencia ya existe
                System.out.println("La preferencia ya existe: " + existingPreferencia);
                throw new AppException("La preferència ja existeix.");
            }

            Aspirant aspirant2 = em.find(Aspirant.class, nif);
            if (aspirant2 == null) {
                throw new AppException("Aspirant no trobat");
            }

            EspecialitatPK pk = new EspecialitatPK(idCos, idEspecialitat);
            Especialitat especialitat2 = em.find(Especialitat.class, pk);
            if (especialitat2 == null) {
                throw new AppException("Especialitat no trobada");
            }

            Centre centre = em.find(Centre.class, idCentre);
            if (centre == null) {
                throw new AppException("Centre no trobat");
            }

            Preferencia preferencia = new Preferencia();
            preferencia.setNif(nif);
            preferencia.setOrdre(ordre);
            preferencia.setAspirant(aspirant2); // ja relacionat per @ManyToOne
            preferencia.setEspecialitat(especialitat2);
            preferencia.setCentre(centre);

            em.persist(preferencia);
            em.getTransaction().commit();

            return preferencia;

        } catch (PersistenceException e) {
            throw new AppException("No s'ha pogut crear la preferència: " + e.getMessage());
        }
    }
}
