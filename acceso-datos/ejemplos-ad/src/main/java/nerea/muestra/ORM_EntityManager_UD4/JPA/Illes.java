
package nerea.muestra.ORM_EntityManager_UD4.JPA;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import nerea.muestra.Exception.AppException;

@Entity
@Table(name = "Illes")

public class Illes {
    
    @Id
    @Column(name = "idIlla", nullable = false)
    private String idIlla;
    
    @Column(name = "nomIlla", length = 100, nullable = false)
    private String nomIlla;
    
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "idIlla")
    private List<Localitat> localitat;
    
    public Illes(){
        this.localitat = new ArrayList<>();
        
    }
    
    public Illes(String idIlla, String nomIlla) throws AppException{
        this.idIlla = idIlla;
        setNomIlla(nomIlla);
        
    }

    public String getIdIlla() {
        return idIlla;
    }

    public void setIdIlla(String idIlla) {
        this.idIlla = idIlla;
    }

    public String getNomIlla() {
        return nomIlla;
    }

    public void setNomIlla(String nomIlla) throws AppException {
        if(nomIlla == null || nomIlla.isBlank()){
            throw new AppException("El nombre de la isla lno puede estar vacío.");
        }
        this.nomIlla = nomIlla;
    }
    
    public List<Localitat> getLocalitat() {
        return localitat;
    }

    public void setLocalitat(List<Localitat> localitat) {
        this.localitat = localitat;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.idIlla);
        hash = 67 * hash + Objects.hashCode(this.nomIlla);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Illes other = (Illes) obj;
        if (!Objects.equals(this.idIlla, other.idIlla)) {
            return false;
        }
        return Objects.equals(this.nomIlla, other.nomIlla);
    }

    
    @Override
    public String toString() {
        return "Illes{" + "idIlla=" + idIlla + ", nomIlla=" + nomIlla + '}';
    }
    
}
