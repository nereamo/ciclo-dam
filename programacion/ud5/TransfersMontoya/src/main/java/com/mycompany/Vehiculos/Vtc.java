package com.mycompany.Vehiculos;

import com.mycompany.transfer.Matricula;
import com.mycompany.transfer.Vehicle;

/**
* Classe Vtc que hereda de Vehicle, contiene los atributos, validaciones y métodos. 
* @author Nerea Montoya
*/

public class Vtc extends Vehicle{
    
    private String distintivo;
    public static final double[] tablaTarifas = {7.80, 12.50, 22, 35};
    
    public Vtc(String marca, int potencia, int velocitat, int velocitatMaxima, Matricula matricula, boolean libre, String distintivo){
        super(marca, potencia, velocitat, velocitatMaxima, matricula, libre);
        setDistintivo(distintivo); 
    }
    
    public String getDistintivo(){
        return distintivo;
    }
    
    public void setDistintivo(String distintivo) {
        if (!distintivo.matches("^[0-9]{4}/[0-9]{2}-[A-Z]{2}$")) {
            throw new IllegalArgumentException("Error: El formato del distintivo no es correcto. El formato correcto es AAAA/MM-XX.");
        }
        this.distintivo = distintivo;
    }
    
    public double[] getTablaTarifas(){
        return tablaTarifas;
    }
    
    double precio = 0;
    
    @Override
    public double calculaPrecioTrayecto(double km){
        if (km < 0) {
            throw new IllegalArgumentException("Error: Los kilómetros introducidos no pueden ser menor a 0.");
        } 
        
        if (km == 0) {
            return 0;
        }
        
        if(km <= 2){
            precio = tablaTarifas[0];
        }else if(km > 2 && km <= 5){
            precio = tablaTarifas[1];
        }else if(km > 5 && km <= 10){
            precio = tablaTarifas[2];
        }else if(km > 10 && km <=20){
            precio = tablaTarifas[3];
        }else{
            precio = 0;
        }
        
      return precio;
    }
    
    @Override
    public String toString() {
        return "Vtc: " + 
               "marca=" + getMarca() + 
               ", potencia=" + getPotencia() + 
               ", velocitat=" + getVelocitat() + 
               ", velocitatMaxima=" + getVelocitatMaxima() + 
               ", matrícula=" + getMatricula() + 
               ", libre=" + (isLibre() ? "Libre":"Ocupado") +
               ", distintivo=" + getDistintivo();
    }
}
