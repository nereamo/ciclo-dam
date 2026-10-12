package com.mycompany.centreeducatiu;

/**
 * Class que hereda de la class Persona
 *
 * @author NMerea Montoya
 */

public class Alumne extends Persona{

    private double notaMitjana;

    public Alumne(String nif, String nom, double notaMitjana) {
        super(nif, nom);
        this.notaMitjana = notaMitjana;

    }

    public double getNotaMitjana() {
        return notaMitjana;
    }

    public void setNotaMitjana(double notaMitjana) {
        this.notaMitjana = notaMitjana;
    }
    
    @Override
    public String calcularHorari() {
        return ("L’horari es troba publicat a la web del centre");
    }
}