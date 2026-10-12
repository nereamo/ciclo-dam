 package com.mycompany.transfer;

import com.mycompany.transfer.Matricula;

/**
* Classe que gestiona un array de màxim maximVehicles de la classe Vehicle
* @author Nerea Montoya
*/

public class ArrayVehicles {
    
    private Vehicle[] vehicles;
    private int numVehicles;

    public ArrayVehicles(int maximVehicles) {
        vehicles = new Vehicle[maximVehicles];
        numVehicles = 0;
    }
    
    public Vehicle[] getVehicles() {
        Vehicle[] nou = new Vehicle[numVehicles];
        for (int i = 0; i < numVehicles; i++) {
            nou[i] = vehicles[i];
        }
        return nou;
    }

    public int getNumVehicles() {
        return numVehicles;
    }

    public boolean afegirVehicle(Vehicle nou) {
        if (numVehicles < vehicles.length) {
            vehicles[numVehicles] = nou;
            numVehicles++;
            return true;
        } else {
            return false;
        }
    }
    
    public int comptarVehiclesDeMarca(String marca) {
        int comptador=0;
        for (int i=0; i<numVehicles; i++) {
            if (vehicles[i].getMarca().equals(marca)) {
                comptador++;
            }
        }
        return comptador;
    }
    
    public void canviarMarca( String marca, String marcaNova) {
        for (int i=0; i<numVehicles; i++) {
            if (vehicles[i].getMarca().equals(marca)) {
                vehicles[i].setMarca(marcaNova);
            }
        }
    }
    
    public Vehicle getVehicle(Matricula matricula){
        
        Vehicle vehicle = null;
        
        for (int i = 0; i < numVehicles; i++) {
            if (vehicles[i] != null && vehicles[i].getMatricula().toString().equals(matricula.toString())) {
                vehicle = vehicles[i]; 
            }
        } 
        return vehicle;
    }
    
    public void cambiaEstadoVehiculo(Matricula matricula){
        
        if (matricula == null) {
            throw new IllegalArgumentException("Error: La matrícula no puede ser null.");
        }
        
        Vehicle vehicle;
        vehicle = getVehicle(matricula);
        
        if(vehicle.isLibre() == false){
            vehicle.setLibre(true);
        }else if(vehicle.isLibre() == true){
             vehicle.setLibre(false);
        }
    }
    
    public int cuentaVehiculosLibres(){
        int count = 0;
        
        for (int i = 0; i < numVehicles; i++) {
            if (vehicles[i] != null && vehicles[i].isLibre()) {
                count++; 
            }
        }
        return count;
    } 
    
    public double calculaMediaPotenciaMaximaVehiculos(){
        if (numVehicles == 0) {
            return 0;
        }
        
        double mediaPotencia = 0;
        for (int i = 0; i < numVehicles; i++) {
            if (vehicles[i] != null) {
                mediaPotencia += vehicles[i].getPotencia();
            }
        }
        return mediaPotencia / numVehicles;
    }
}