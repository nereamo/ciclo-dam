
package nerea.muestra.Ficheros_Path_Streams_UD2.Streams;

import java.io.Serializable;

/**
* @author Nerea
*/

public class Producte implements Serializable {

    private String nom;
    private double preu;

    public Producte(String nom, double preu) {
        this.nom = nom;
        this.preu = preu;
    }

    public String getNom() {
        return nom;
    }

    public double getPreu() {
        return preu;
    }

    @Override
    public String toString() {
        return "Producte{nom='" + nom + "', preu=" + preu + "}";
    }
}
