package com.mycompany.centreeducatiu;


/**
 * Class que hereda de la class Persona.
 *
 * @author Nerea Montoya
 */

public class Professor extends Persona{

    protected String especialitat;
    protected double salari;

    public Professor(String nif, String nom, String especialitat, double salari) {
        super(nif, nom);
        this.especialitat = especialitat;
        this.salari = salari;
    }

    public String getEspecialitat() {
        return especialitat;
    }

    public void setEspecialitat(String especialitat) {
        this.especialitat = especialitat;
    }

    public double getSalari() {
        return salari;
    }

    public void setSalari(double salari) {
        this.salari = salari;
    }
    
    @Override
    public String calcularHorari() {
        return ("L’horari es pot consultar al gestib");
    }
}
