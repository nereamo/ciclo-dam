package com.mycompany.centreeducatiu;

import java.io.Serializable;
import java.util.Objects;

/**
 * Classe pare Persona implementa serializable, conté atributs, getts/setts, métode equals y hashCode
 *
 * @author Nerea Montoya
 */

public abstract class Persona implements Serializable{

    protected String nif;
    protected String nom;

    public Persona(String nif, String nom) {
        this.nif = nif;
        this.nom = nom;
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

    public abstract String calcularHorari();

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.nif);
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
        final Persona other = (Persona) obj;
        return Objects.equals(this.nif, other.nif);
    }
}