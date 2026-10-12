package cat.paucasesnovescifp.spaddd.ut4.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Objects;

/**
* Entidad de Peatge, contiene los atributos, getters/setters, referencia a la tabla 'toll' y las NamedQuery para las consultas a la BD.
* @author Nerea Montoya
*/

@Entity
@Table(name = "toll")
@NamedQuery(name = "Peatge.findAll", query = "SELECT p FROM Peatge p ORDER BY p.name")
@NamedQuery(name = "Trip.findAll", query = "SELECT t FROM Trip t WHERE (t.idTollInput = :peatge OR t.idTollOutput = :peatge)")

public class Peatge{
    @Id
    @Column(name = "IdToll",nullable = false, length = 7)
    private String idToll;
    
    @Column(name = "Name", nullable = false, length = 35)
    private String name;
    
    @OneToMany(mappedBy = "idTollInput", fetch = FetchType.LAZY)
    private List<Trip> idTollInput;
    
    @OneToMany(mappedBy = "idTollOutput", fetch = FetchType.LAZY)
    private List<Trip> idTollOutput;

    public Peatge(){
        
    }

    public Peatge(String idToll, String name) {
        this.idToll = idToll;
        this.name = name;
    }

    public String getIdToll() {
        return idToll;
    }

    public void setIdToll(String idToll) {
        this.idToll = idToll;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public List<Trip> getIdTollInput(){
        return idTollInput;
    }
    
    public void setIdTollInput(List<Trip> idTollInput){
        this.idTollInput = idTollInput;
    }
    
    public List<Trip> getIdTollOutput(){
        return idTollOutput;
    }
    
    public void setIdTollOutput(List<Trip> idTollOutput){
        this.idTollOutput = idTollOutput;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.idToll);
        hash = 97 * hash + Objects.hashCode(this.name);
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
        final Peatge other = (Peatge) obj;
        if (!Objects.equals(this.idToll, other.idToll)) {
            return false;
        }
        return Objects.equals(this.name, other.name);
    }

    @Override
    public String toString() {
        return "Peatge{" + "idToll=" + idToll + ", name=" + name + '}';
    }
}
