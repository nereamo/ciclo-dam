package nerea.muestra.ORM_EntityManager_UD4.JPA;
import jakarta.persistence.*;

import java.util.Objects;
import nerea.muestra.Exception.AppException;

@Entity
@Table(name = "Localitats")
public class Localitat {
    @Id
    @Column(name = "idLocalitat", nullable = false, length = 9)
    private String idLocalitat;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idIlla", nullable = false)
    private Illes idIlla;

    @Column(name = "nomLocalitat", nullable = false, length = 100)
    private String nomLocalitat;

    public Localitat() {
    }

    public Localitat(String idLocalitat, Illes idIlla, String nomLocalitat) throws AppException {
        this.setIdLocalitat(idLocalitat);
        this.setIdIlla(idIlla);
        this.setNomLocalitat(nomLocalitat);
    }

    public String getIdLocalitat() {
        return idLocalitat;
    }

    public void setIdLocalitat(String idLocalitat) throws AppException {
        if (idLocalitat == null || idLocalitat.isBlank()) {
            throw new AppException("L'identificador no pot ser una cadena nul·la, buida o formada només per espais.");
        }
        this.idLocalitat = idLocalitat;
    }

    public Illes getIdIlla() {
        return idIlla;
    }

    public void setIdIlla(Illes idIlla) throws AppException {
        if (idIlla == null) {
            throw new AppException("La illa no pot ser nul·la.");
        }
        this.idIlla = idIlla;
    }

    public String getNomLocalitat() {
        return nomLocalitat;
    }

    public void setNomLocalitat(String nomLocalitat) throws AppException {
        if (nomLocalitat == null || nomLocalitat.isBlank()) {
            throw new AppException("El nom no pot ser una cadena nul·la, buida o formada només per espais.");
        }
        this.nomLocalitat = nomLocalitat;
    }

    @Override
    public String toString() {
        return "Localitat{" +
                "idLocalitat='" + getIdLocalitat() + '\'' +
                ", idIlla=" + getIdIlla() +
                ", nomLocalitat='" + getNomLocalitat() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Localitat localitat = (Localitat) o;
        return Objects.equals(getIdLocalitat(), localitat.getIdLocalitat()) && Objects.equals(getIdIlla(), localitat.getIdIlla()) && Objects.equals(getNomLocalitat(), localitat.getNomLocalitat());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdLocalitat(), getIdIlla(), getNomLocalitat());
    }
}