
package nerea.muestra.MongoDB_UD6;

import java.util.HashMap;
import java.util.Map;

/**
* @author Nerea
*/

public class Aspirant {
    private String nif;
    private String nom;
    private String llinatges;
    private String codiPostal;

    public Aspirant(String nif, String nom, String llinatges, String codiPostal) {
        this.nif = nif;
        this.nom = nom;
        this.llinatges = llinatges;
        this.codiPostal = codiPostal;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLlinatges() {
        return llinatges;
    }

    public void setLlinatges(String llinatges) {
        this.llinatges = llinatges;
    }

    public String getCodiPostal() {
        return codiPostal;
    }

    public void setCodiPostal(String codiPostal) {
        this.codiPostal = codiPostal;
    }
    
    public Map<String, Object> toMap() {
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("nif", nif);
        mapa.put("nom", nom);
        mapa.put("llinatges", llinatges);
        mapa.put("codiPostal", codiPostal);
        return mapa;
    }

    @Override
    public String toString() {
        return String.format("%s - %s %s (%s)", nif, nom, llinatges, codiPostal);
    }
}
