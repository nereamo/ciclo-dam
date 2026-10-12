package com.mycompany.transfer;
/**
 * Classe abstracta Vehicle, contiene atributos y métodos en común para las subclases Taxi y Vtc.
 * @author Nerea Montoya
 */
public abstract class Vehicle {
    
    protected String marca;
    protected int potencia;
    protected int velocitat;
    protected int velocitatMaxima;
    protected Matricula matricula;
    protected boolean libre;
    
    public Vehicle(String marca, int potencia, int velocitat, int velocitatMaxima, Matricula matricula, boolean libre) {
        this.marca = marca;
        this.potencia = potencia;
        this.velocitat = velocitat;
        this.velocitatMaxima = velocitatMaxima;
        this.matricula = new Matricula(matricula.getDigitos(), matricula.getLetras());
        this.libre = libre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public int getVelocitat() {
        return velocitat;
    }

    public void setVelocitat(int velocitat) {
        this.velocitat = velocitat;
    }

    public int getVelocitatMaxima() {
        return velocitatMaxima;
    }

    public void setVelocitatMaxima(int velocitatMaxima) {
        this.velocitatMaxima = velocitatMaxima;
    }
    
    public void augmentarVelocitat (int increment) {
        this.velocitat = this.velocitat + increment;
    }

    public void reduirVelocitat (int decrement) {
        this.velocitat = this.velocitat - decrement;
    } 
    
    public Matricula getMatricula(){
        return matricula;
    }
    
    public void setMatricula(Matricula matricula){
        this.matricula = matricula;
    }
    
    public boolean isLibre(){
        return libre;
    }
    
    public void setLibre(boolean libre){
        this.libre = libre;
    }

    @Override
    public String toString() {
        return "Vehicle:" + "marca=" + marca + ", potencia=" + potencia + ", velocitat=" + velocitat + ", velocitatMaxima=" + velocitatMaxima + ", matrícula=" + matricula + ", libre=" + libre;
    }
    
    public abstract double calculaPrecioTrayecto(double km); 
}
