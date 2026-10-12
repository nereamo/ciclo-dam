
package com.mycompany.centreeducatiu;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
* Class que contiene los métodos necesarios para el funcionamiento de la aplicación
* @author Nerea Montoya
*/

public class LlistaPersones implements Serializable {

    private Set<Persona> persones;

    public LlistaPersones() {
        this.persones = new HashSet<>();
    }
    

    public Set<Persona> getPersones() {
        return persones;
    }

    public void añadirPersona(Persona persona) {
        
        if (persona instanceof Alumne) {
            Alumne alumne = (Alumne) persona;
            persones.add(new Alumne(alumne.getNif(), alumne.getNom(), alumne.getNotaMitjana()));
        } else if (persona instanceof Professor) {
            Professor profesor = (Professor) persona;
            persones.add(new Professor(profesor.getNif(), profesor.getNom(), profesor.getEspecialitat(), profesor.getSalari()));
        } else {
            System.out.println("No se ha podido añadir la persona correctamente");
        }
    }
}