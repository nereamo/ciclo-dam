package com.mycompany.Vehiculos;

import com.mycompany.transfer.Matricula;
import com.mycompany.transfer.Vehicle;
import java.util.regex.Pattern;

/**
* Classe Taxi que hereda de Vehicle, contiene los atributos, validaciones y métodos.
* @author Nerea Montoya
*/

public class Taxi extends Vehicle{
    
    private String numeroLicencia;
    private final double bajadaBandera = 2.0;
    private final double tarifaKm = 1.5;
    
    private static final Pattern LICENCIA_PATTERN = Pattern.compile("^[A-Z]{2}-[0-9]{5}$"); 
    
    public Taxi(String marca, int potencia, int velocitat, int velocitatMaxima, Matricula matricula, boolean libre, String numeroLicencia){
        super(marca, potencia, velocitat, velocitatMaxima, matricula, libre);
        setNumeroLicencia(numeroLicencia);
    }

    
    public String getNumeroLicencia(){
        return numeroLicencia;
    }
    
    public void setNumeroLicencia(String numeroLicencia){
        
        if(!LICENCIA_PATTERN.matcher(numeroLicencia).matches()){
            throw new IllegalArgumentException("Error: El número de licencia no es correcto. El formato correcto es XX-12345");
        }
        this.numeroLicencia = numeroLicencia;
    }
    
    public double getBajadaBandera(){
        return bajadaBandera;
    }
    
    public double getTarifaKm(){
        return tarifaKm;
    }
    
    @Override
    public double calculaPrecioTrayecto(double km) {
        
        if (km < 0) {
            throw new IllegalArgumentException("Error: Los kilómetros introducidos no pueden ser menor a 0.");
        }
        return bajadaBandera + (tarifaKm * km);
    } 
    
    @Override
    public String toString() {
        return "Taxi: " + 
               "marca=" + getMarca() + 
               ", potencia=" + getPotencia() + 
               ", velocitat=" + getVelocitat() + 
               ", velocitatMaxima=" + getVelocitatMaxima() + 
               ", matrícula=" + getMatricula() + 
               ", libre=" + (isLibre() ? "Libre":"Ocupado") + 
               ", licencia=" + getNumeroLicencia();
    }
}
